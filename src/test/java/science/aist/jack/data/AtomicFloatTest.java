/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.data;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>Test class for {@link AtomicFloat}</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */

public class AtomicFloatTest {

    @Test
    public void testConstruct() {
        // given

        // when
        AtomicFloat af = new AtomicFloat();

        // then
        Assert.assertNotNull(af);
    }

    @Test
    public void testConstruct2() {
        // given

        // when
        AtomicFloat af = new AtomicFloat(2);

        // then
        Assert.assertNotNull(af);
    }

    @Test
    public void testGet() {
        // given
        AtomicFloat af = new AtomicFloat(2);

        // when
        float f = af.get();

        // then
        Assert.assertEquals(f, 2.0f);
    }

    @Test
    public void testSet() {
        // given
        AtomicFloat af = new AtomicFloat(2);

        // when
        af.set(3);

        // then
        Assert.assertEquals(af.get(), 3.0f);
    }

    @Test
    public void testGetAndSet() {
        // given
        AtomicFloat af = new AtomicFloat(2);

        // when
        float pref = af.getAndSet(3);

        // then
        Assert.assertEquals(pref, 2.0f);
        Assert.assertEquals(af.get(), 3.0f);
    }

    @Test
    public void testCompareAndSetTrue() {
        // given
        AtomicFloat af = new AtomicFloat(2);

        // when
        boolean b = af.compareAndSet(2, 3);

        // then
        Assert.assertTrue(b);
        Assert.assertEquals(af.get(), 3.0f);
    }

    @Test
    public void testCompareAndSetFalse() {
        // given
        AtomicFloat af = new AtomicFloat(2);

        // when
        boolean b = af.compareAndSet(-1, 3);

        // then
        Assert.assertFalse(b);
        Assert.assertEquals(af.get(), 2.0f);
    }

    @Test
    public void testWeakCompareAndSetPlainTrue() {
        // given
        AtomicFloat af = new AtomicFloat(2);

        // when
        boolean b = af.weakCompareAndSetPlain(2, 3);

        // then
        Assert.assertTrue(b);
        Assert.assertEquals(af.get(), 3.0f);
    }

    @Test
    public void testWeakCompareAndSetPlainFalse() {
        // given
        AtomicFloat af = new AtomicFloat(2);

        // when
        boolean b = af.weakCompareAndSetPlain(-1, 3);

        // then
        Assert.assertFalse(b);
        Assert.assertEquals(af.get(), 2.0f);
    }

    @Test
    public void testFloatValue() {
        // given
        AtomicFloat af = new AtomicFloat(123456789.123456789f);

        // when
        float v = af.floatValue();

        // then
        Assert.assertEquals(v, 1.23456792E8f);
    }

    @Test
    public void testDoubleValue() {
        // given
        AtomicFloat af = new AtomicFloat(123456789.123456789f);

        // when
        double v = af.doubleValue();

        // then
        Assert.assertEquals(v, 1.23456792E8d);
    }

    @Test
    public void testIntValue() {
        // given
        AtomicFloat af = new AtomicFloat(123456789.123456789f);

        // when
        int v = af.intValue();

        // then
        Assert.assertEquals(v, 123456792);
    }

    @Test
    public void testLongValue() {
        // given
        AtomicFloat af = new AtomicFloat(123456789.123456789f);

        // when
        long v = af.longValue();

        // then
        Assert.assertEquals(v, 123456792L);
    }
}