/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.reflection;

import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.jack.reflection.domain.A;
import science.aist.jack.reflection.domain.B;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Collectors;


/**
 * <p>Test class for {@link ReflectionUtils}</p>
 *
 * @author Christoph Praschl
 * @since 2.0
 */
public class ReflectionUtilsTest {

    @Test
    public void testGetAllFieldsOfClass() {
        // given

        // when
        List<Field> allFieldsOfClass = ReflectionUtils.getAllFieldsOfClass(B.class)
                // remove synthetic fields because execution otherwise fails on CI
                .stream().filter(f -> !f.isSynthetic()).collect(Collectors.toList());

        // then
        Assert.assertEquals(allFieldsOfClass.size(), 6);
    }

    @Test
    public void testGetAllFieldsOfClass2() {
        // given

        // when
        List<Field> allFieldsOfClass = ReflectionUtils.getAllFieldsOfClass(A.class)
                // remove synthetic fields because execution otherwise fails on CI
                .stream().filter(f -> !f.isSynthetic()).collect(Collectors.toList());

        // then
        Assert.assertEquals(allFieldsOfClass.size(), 3);

    }
}