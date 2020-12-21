/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.reflection;

import lombok.SneakyThrows;
import science.aist.jack.general.util.CastUtils;

import java.lang.reflect.Field;
import java.util.Optional;

/**
 * <h2>SafeFieldHandle</h2>
 * <p>Allows typesafe access to {@link Field}s. The value is wrapped into {@link Optional} to allow functional error
 * handling, if the {@link Field} is either of the wrong type, or not initialized.
 * </p>
 *
 * <p>
 * This handle also checks initialisation of the {@link Field} by comparing the value with the datatype default value.
 * The value is either derived from the datatype, or explicitly passed for application specific use cases.
 * </p>
 *
 * @param <T> The {@link Class}, that contains the {@link Field}.
 * @param <U> The type of the {@link Field}s value.
 * @author Rainer Meindl
 * @since 2.3.0
 */
public class SafeFieldHandle<T, U> {
    private final Field unsafeField;
    private final U defaultValue;

    public SafeFieldHandle(Field field) {
        this(field, Autoboxers.isPrimitiveClass(field.getType()) ?
                CastUtils.cast(Autoboxers.getPrimitiveDefaultValue(field.getType())) :
                null);
    }

    /**
     * <p>
     * Creates a new instance of {@link SafeFieldHandle}, where the defaultValue is derived from the type, if possible.
     * Might fail, if no {@link Field} witht he provided name is found.
     * </p>
     *
     * @param clazz The {@link Class}, which should contain a {@link Field} with the provided name
     * @param name  The name of the {@link Field}, which should be declared in the clazz
     */
    @SneakyThrows
    public SafeFieldHandle(Class<T> clazz, String name) {
        // cannot delegate to this, as calls to sibling / super constructors are always excluded from @SneakyThrows
        this.unsafeField = clazz.getDeclaredField(name);
        this.defaultValue = Autoboxers.isPrimitiveClass(clazz.getDeclaredField(name).getType()) ?
                CastUtils.cast(Autoboxers.getPrimitiveDefaultValue(clazz.getDeclaredField(name).getType())) :
                null;
    }

    public SafeFieldHandle(Field field, U defaultValue) {
        this.unsafeField = field;
        this.defaultValue = defaultValue;
    }

    /**
     * <p>
     * Creates a new instance of {@link SafeFieldHandle}. Might fail, if no {@link Field} with he provided name is found.
     * </p>
     *
     * @param clazz        The {@link Class}, which should contain a {@link Field} with the provided name
     * @param name         The name of the {@link Field}, which should be declared in the clazz
     * @param defaultValue Custom default value of the {@link Field}. If the {@link Field} holds this value, it is
     *                     considered to be uninitialized
     */
    @SneakyThrows
    public SafeFieldHandle(Class<T> clazz, String name, U defaultValue) {
        this.unsafeField = clazz.getDeclaredField(name);
        this.defaultValue = defaultValue;

    }


    /**
     * <p>Accesses the unsafe {@link Field} and wraps it in an {@link Optional} of the type this class thinks it is.
     * If either the access, or the conversion of the value fails, as well as if the {@link Field}s value is equal to
     * the defaultValue defined by this handle</p>
     *
     * @param object The instance, whose fields needs to be accessed safely.
     * @return Optional, either containing the typesafe value of the field or Optional.empty()
     * s
     */
    public Optional<U> get(T object) {
        var accessible = unsafeField.canAccess(object);
        unsafeField.setAccessible(true);
        Optional<U> ret;
        try {
            ret = tryCast(unsafeField.get(object))
                    .filter(u -> !u.equals(getDefaultValue()));
        } catch (Exception ignored) {
            ret = Optional.empty();
        } finally {
            unsafeField.setAccessible(accessible);
        }

        return ret;
    }


    /**
     * <p>
     * Tries to cast the fieldValue into the designated type U. If the cast fails, or the value is null, an empty
     * {@link Optional} is returned.
     * </p>
     *
     * @param fieldValue an extracted value of the unsafeField, which should be casted into a type of U
     * @return an {@link Optional} of U, either holding the mapped value of fieldValue, or empty, if the cast fails.
     */
    protected Optional<U> tryCast(Object fieldValue) {
        return Optional.ofNullable(CastUtils.cast(fieldValue));
    }

    /**
     * <p>
     * Returns the default value for the given type U.
     * </p>
     *
     * @return the default value for the given type U.
     */
    protected U getDefaultValue() {
        return defaultValue;
    }
}
