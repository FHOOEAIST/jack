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
import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.jack.general.util.CastUtils;
import science.aist.jack.reflection.domain.A;

/**
 * <h2>SafeFieldHandle</h2>
 * <p>Test class for {@link SafeFieldHandle}</p>
 *
 * @author Rainer Meindl
 * @since 2.3.0
 */
public class SafeFieldHandleTest {

    @Test
    public void testSimple() {
        // given
        var handle = new SafeFieldHandle<>(A.class, "a", 0);
        var testData = new A(null, 37, "Hello there");

        // when
        var res = handle.get(testData);

        // then
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals(res.get(), CastUtils.cast(testData.getA()));
    }

    @SneakyThrows
    @Test
    public void testSimpleFieldCtor() {
        // given
        var handle = new SafeFieldHandle<>(A.class.getDeclaredField("a"), 0);
        var testData = new A(null, 37, "Hello there");

        // when
        var res = handle.get(testData);

        // then
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals(res.get(), CastUtils.cast(testData.getA()));
    }

    @SneakyThrows
    @Test
    public void testCtorNoDefaultValue() {
        // given
        var testClass = new A();
        var field = testClass.getClass()
                .getDeclaredField("a");

        // when
        var handle = new SafeFieldHandle<A, Integer>(field);
        var ret = handle.get(testClass);

        // then
        Assert.assertTrue(ret.isEmpty());
    }

    @Test
    public void testCtorNameNoDefaultValue() {
        // given
        var fieldName = "a";
        var testClass = new A();
        // when
        var handle = new SafeFieldHandle<A, Integer>(A.class, fieldName);
        var ret = handle.get(testClass);

        // then
        Assert.assertTrue(ret.isEmpty());
    }

    @Test
    public void testDefaultValue() {
        // given
        var handle = new SafeFieldHandle<>(A.class, "a", 0);
        var testData = new A(null, 0, "27Ab");

        // when
        var res = handle.get(testData);

        // then
        Assert.assertTrue(res.isEmpty());
    }

    @Test
    public void testStringNull() {
        // given
        var handle = new SafeFieldHandle<A, String>(A.class, "b", null);
        var testData = new A(null, 3, null);

        // when
        var res = handle.get(testData);

        // then
        Assert.assertTrue(res.isEmpty());
    }

    @Test
    public void testString() {
        // given
        var handle = new SafeFieldHandle<A, String>(A.class, "b", null);
        var testData = new A(null, 3, "General Kenobi!");

        // when
        var res = handle.get(testData);

        // then
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals(res.get(), testData.getB());
    }

    @Test
    public void testStringEmptyDefault() {
        // given
        var handle = new SafeFieldHandle<>(A.class, "b", "");
        var testData = new A(null, 3, "You are a bold one");

        // when
        var res = handle.get(testData);

        // then
        Assert.assertTrue(res.isPresent());
        Assert.assertEquals(res.get(), testData.getB());
    }

    @Test
    public void testStringEmptyDefaultHit() {
        // given
        var handle = new SafeFieldHandle<>(A.class, "b", "");
        var testData = new A();

        // when
        var res = handle.get(testData);

        // then
        Assert.assertTrue(res.isEmpty());
    }
}
