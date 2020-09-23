/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.persistence.filesystem.implementation;

import lombok.CustomLog;
import lombok.SneakyThrows;
import science.aist.jack.reflection.ReflectionUtils;

import java.io.File;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * <p>Reflection based implementation for reading/writing CSV files. </p>
 *
 * @author Christoph Praschl
 * @since 2.0
 */
@CustomLog
public class ReflectionCSVProcessor<T> extends CSVProcessor<T> {
    private final Set<String> ignoredFields = new HashSet<>();
    private final Map<Class<?>, Function<String, Object>> converters = new HashMap<>();

    /**
     * Constructor of a ReflectionCSVProcessor for domain types with simple-typed properties only (int, double, float, long, boolean, char, Integer, Float, Double, Long, String, Character, Boolean)
     *
     * @param separator        separator used in the csv file
     * @param columnDefinition columnDefinition of the csv file (can be null for reading if the CSV file contains a header which is used in {@link CSVProcessor#read(File, boolean, boolean)})
     * @param clazz            clazz of domain type
     */
    @SneakyThrows
    @SuppressWarnings("java:S3776") // method complexity
    public ReflectionCSVProcessor(char separator, List<String> columnDefinition, Class<T> clazz) {
        super(separator, columnDefinition);
        try {
            clazz.getConstructor();
        } catch (NoSuchMethodException e) {
            throw new IllegalArgumentException("Given class (" + clazz.getName() + ") doesn't have the required default constructor");
        }

        converters.put(double.class, s -> parseOrDefault(s, Double::parseDouble, 0.0));
        converters.put(Double.class, s -> parseOrDefault(s, Double::parseDouble, null));
        converters.put(int.class, s -> parseOrDefault(s, Integer::parseInt, 0));
        converters.put(Integer.class, s -> parseOrDefault(s, Integer::parseInt, null));
        converters.put(float.class, s -> parseOrDefault(s, Float::parseFloat, 0.0f));
        converters.put(Float.class, s -> parseOrDefault(s, Float::parseFloat, null));
        converters.put(long.class, s -> parseOrDefault(s, Long::parseLong, 0L));
        converters.put(Long.class, s -> parseOrDefault(s, Long::parseLong, null));
        converters.put(boolean.class, s -> parseOrDefault(s, Boolean::parseBoolean, false));
        converters.put(Boolean.class, s -> parseOrDefault(s, Boolean::parseBoolean, null));
        converters.put(String.class, s -> s);
        converters.put(char.class, s -> {
            if (s.length() > 0) {
                return s.charAt(0);
            } else {
                return '\u0000';
            }
        });
        converters.put(Character.class, s -> {
            if (s.length() > 0) {
                return s.charAt(0);
            } else {
                return null;
            }
        });


        Map<String, Field> classFields = ReflectionUtils.getAllFieldsOfClass(clazz).stream().collect(Collectors.toMap(Field::getName, p -> p));

        this.elementToFunc = (elem, columns) -> {
            List<String> result = new ArrayList<>();
            for (String column : columns) {
                try {
                    if (ignoredFields.contains(column)) {
                        continue;
                    }
                    Field field = classFields.getOrDefault(column, null);
                    if (field != null) {
                        boolean accessible = field.canAccess(elem);
                        field.setAccessible(true);
                        Object o = field.get(elem);
                        field.setAccessible(accessible);

                        if (o != null) {
                            result.add(o.toString());
                        } else {
                            result.add(null);
                        }
                    } else {
                        throw new IllegalArgumentException("Column " + column + " does not exist in class " + clazz.getName());
                    }
                } catch (IllegalAccessException e) {
                    String error = "Reflective access of field (" + column + ") was not able.";
                    log.error(error, e);
                    throw new IllegalArgumentException(error);
                }
            }

            return result;
        };

        this.columnsToElementFunc = (splitLines, columns) -> {
            T obj;
            try {
                obj = clazz.getDeclaredConstructor().newInstance();
            } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException | InstantiationException e) {
                String error = "Can't access the constructor of the declared class " + clazz;
                log.error(error, e);
                throw new IllegalArgumentException(error);
            }
            for (int i = 0; i < columns.size(); i++) {
                String columnName = columns.get(i);

                if (ignoredFields.contains(columnName)) {
                    continue;
                }

                String columnValue = splitLines.get(i);
                try {
                    Field field = classFields.getOrDefault(columnName, null);
                    if (field != null) {
                        boolean accessible = field.canAccess(obj);
                        field.setAccessible(true);
                        Object castedColumnValue;
                        Class<?> fieldType = field.getType();

                        if (columnValue == null) {
                            castedColumnValue = null;
                        } else {
                            castedColumnValue = converters.getOrDefault(fieldType, fieldType::cast).apply(columnValue);
                        }

                        field.set(obj, castedColumnValue);
                        field.setAccessible(accessible);
                    } else {
                        throw new IllegalArgumentException("Column " + columnName + " does not exist in class " + clazz.getName());
                    }
                } catch (IllegalAccessException e) {
                    String error = "Reflective access of field (" + columnName + ") was not able.";
                    log.error(error, e);
                    throw new IllegalArgumentException(error);
                }
            }
            return obj;
        };
    }

    private static Object parseOrDefault(String value, Function<String, Object> parseFunction, Object defaultValue) {
        try {
            return parseFunction.apply(value);
        } catch (Exception e) {
            log.error("Could not parse value (" + value + ") with given parse function. So using default value", e);
            return defaultValue;
        }
    }

    /**
     * Add a field which will be ignored so it won't be read or written
     *
     * @param fieldName name of the ignored field
     */
    public void addIgnoredField(String fieldName) {
        ignoredFields.add(fieldName);
    }

    /**
     * Add a converter for the given class. This converter will be used for converting the CSV string to the expected field type.
     * Using this method you can also override the default converters for simple-typed properties only (int, double, float, long, boolean, char, Integer, Float, Double, Long, String, Character, Boolean)
     *
     * @param clazz     for which the converter will be used
     * @param converter converter which transforms the given csv value to an object of the expected type
     */
    public void addFieldConverter(Class<?> clazz, Function<String, Object> converter) {
        converters.put(clazz, converter);
    }
}
