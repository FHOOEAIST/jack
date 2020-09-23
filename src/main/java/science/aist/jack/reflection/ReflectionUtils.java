/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.reflection;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <p>Utility class for reflection based stuff</p>
 *
 * @author Christoph Praschl
 * @since 2.0
 */
public class ReflectionUtils {
    private ReflectionUtils() {
    }

    /**
     * Collects all fields of class together with all fields of the super classes
     *
     * @param clazz Class to start with
     * @return list of all fields of the class and it`s parents
     */
    public static List<Field> getAllFieldsOfClass(Class<?> clazz) {
        List<Field> fields = new ArrayList<>();
        Collections.addAll(fields, clazz.getDeclaredFields());
        Class<?> superclass = clazz.getSuperclass();
        if (superclass != null) {
            fields.addAll(getAllFieldsOfClass(superclass));
        }
        return fields;
    }

}
