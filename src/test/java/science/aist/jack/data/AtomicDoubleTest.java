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
 * <p>Test class for {@link AtomicDouble}</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */

public class AtomicDoubleTest {

    @Test
    public void testConstruct() {
        // given

        // when
        AtomicDouble ad = new AtomicDouble();

        // then
        Assert.assertNotNull(ad);
    }

    @Test
    public void testConstruct2() {
        // given

        // when
        AtomicDouble ad = new AtomicDouble(2);

        // then
        Assert.assertNotNull(ad);
    }

    @Test
    public void testGet() {
        // given
        AtomicDouble ad = new AtomicDouble(2);

        // when
        double f = ad.get();

        // then
        Assert.assertEquals(f, 2.0d);
    }

    @Test
    public void testSet() {
        // given
        AtomicDouble ad = new AtomicDouble(2);

        // when
        ad.set(3);

        // then
        Assert.assertEquals(ad.get(), 3.0d);
    }

    @Test
    public void testGetAndSet() {
        // given
        AtomicDouble ad = new AtomicDouble(2);

        // when
        double pref = ad.getAndSet(3);

        // then
        Assert.assertEquals(pref, 2.0d);
        Assert.assertEquals(ad.get(), 3.0d);
    }

    @Test
    public void testCompareAndSetTrue() {
        // given
        AtomicDouble ad = new AtomicDouble(2);

        // when
        boolean b = ad.compareAndSet(2, 3);

        // then
        Assert.assertTrue(b);
        Assert.assertEquals(ad.get(), 3.0d);
    }

    @Test
    public void testCompareAndSetFalse() {
        // given
        AtomicDouble ad = new AtomicDouble(2);

        // when
        boolean b = ad.compareAndSet(-1, 3);

        // then
        Assert.assertFalse(b);
        Assert.assertEquals(ad.get(), 2.0d);
    }

    @Test
    public void testWeakCompareAndSetPlainTrue() {
        // given
        AtomicDouble ad = new AtomicDouble(2);

        // when
        boolean b = ad.weakCompareAndSetPlain(2, 3);

        // then
        Assert.assertTrue(b);
        Assert.assertEquals(ad.get(), 3.0d);
    }

    @Test
    public void testWeakCompareAndSetPlainFalse() {
        // given
        AtomicDouble ad = new AtomicDouble(2);

        // when
        boolean b = ad.weakCompareAndSetPlain(-1, 3);

        // then
        Assert.assertFalse(b);
        Assert.assertEquals(ad.get(), 2.0d);
    }

    @Test
    public void testFloatValue() {
        // given
        AtomicDouble ad = new AtomicDouble(123456789.123456789d);

        // when
        float v = ad.floatValue();

        // then
        Assert.assertEquals(v, 1.23456792E8f);
    }

    @Test
    public void testDoubleValue() {
        // given
        AtomicDouble ad = new AtomicDouble(123456789.123456789d);

        // when
        double v = ad.doubleValue();

        // then
        Assert.assertEquals(v, 1.2345678912345679E8);
    }

    @Test
    public void testIntValue() {
        // given
        AtomicDouble ad = new AtomicDouble(123456789.123456789d);

        // when
        int v = ad.intValue();

        // then
        Assert.assertEquals(v, 123456789);
    }

    @Test
    public void testLongValue() {
        // given
        AtomicDouble ad = new AtomicDouble(123456789.123456789d);

        // when
        long v = ad.longValue();

        // then
        Assert.assertEquals(v, 123456789L);
    }
}