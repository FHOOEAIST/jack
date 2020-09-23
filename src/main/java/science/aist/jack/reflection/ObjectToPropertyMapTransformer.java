/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.reflection;

import lombok.Setter;
import science.aist.jack.data.Pair;
import science.aist.jack.exception.ExceptionUtils;
import science.aist.jack.general.transformer.ForwardTransformer;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Stream;

/**
 * <p>Transformer to transform an kind of object into a property map</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */
@Setter
public class ObjectToPropertyMapTransformer implements ForwardTransformer<Object, Map<String, Object>> {

    /**
     * If the fields of the parent objects should be included as well
     */
    private boolean includeParentFields = false;

    /**
     * if synthetic fields should be included as well. Note: this will cause jenkins to get different results, as
     * sonar adds some synthetic fields
     */
    private boolean includeSyntheticFields = false;

    /**
     * Returns a Property Map for the given object, where the key is the name of the field and the value the value of the field
     *
     * @param o the object
     * @return a property map
     */
    @Override
    public Map<String, Object> transformFrom(Object o) {
        Class<?> clazz = o.getClass();
        Stream<Field> fieldsForClass = includeParentFields ? ReflectionUtils.getAllFieldsOfClass(clazz).stream() : Arrays.stream(clazz.getDeclaredFields());
        if (!includeSyntheticFields) fieldsForClass = fieldsForClass.filter(f -> !f.isSynthetic());

        return fieldsForClass.map(ExceptionUtils.uncheck(f -> {
            boolean access = f.canAccess(o);
            try {
                f.setAccessible(true);
                return Pair.of(f.getName(), f.get(o));
            } finally {
                f.setAccessible(access);
            }
        })).collect(Pair.toMap());
    }
}
