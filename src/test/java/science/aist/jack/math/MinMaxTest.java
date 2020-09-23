/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.math;

import lombok.Getter;
import lombok.Setter;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;
import java.util.function.BiConsumer;

/**
 * <p>Test class for {@link MinMax}</p>
 *
 * @author Christoph Praschl
 * @since 2.0
 */
public class MinMaxTest {

    @Test
    public void testEmpty() {
        // given

        // when
        MinMax<Integer> empty = MinMax.empty();

        // then
        Assert.assertFalse(empty.isPresent());
    }

    @Test
    public void testOf() {
        // given
        Integer x = 0;
        Integer y = 10;

        // when
        MinMax<Integer> of = MinMax.of(x, y);

        // then
        Assert.assertTrue(of.isPresent());
        Assert.assertEquals(of.getMin(), x);
        Assert.assertEquals(of.getMax(), y);
    }

    @Test
    public void testIfPresent() {
        // given
        MinMax<Integer> of = MinMax.of(15, 20);
        TestSignal signal = new TestSignal();

        BiConsumer<Integer, Integer> consumer = (i, i2) -> signal.setSet(true);

        // when
        of.ifPresent(consumer);

        // then
        Assert.assertTrue(signal.isSet());
    }

    @Test
    public void testIfPresent2() {
        // given
        MinMax<Integer> of = MinMax.empty();
        TestSignal signal = new TestSignal();

        BiConsumer<Integer, Integer> consumer = (i, i2) -> signal.setSet(true);

        // when
        of.ifPresent(consumer);

        // then
        Assert.assertFalse(signal.isSet());
    }

    @Test
    public void testMap() {
        // given
        Integer i = 15;
        Integer i2 = 20;

        MinMax<Integer> of = MinMax.of(i, i2);

        // when
        Optional<Integer> map = of.map(Integer::sum);

        // then
        Assert.assertTrue(map.isPresent());
        Assert.assertEquals((int) map.get(), i + i2);
    }

    @Test
    public void testMap2() {
        // given
        MinMax<Integer> of = MinMax.empty();

        // when
        Optional<Integer> map = of.map(Integer::sum);

        // then
        Assert.assertFalse(map.isPresent());
    }

    @Getter
    @Setter
    private static class TestSignal {
        private boolean set = false;

    }
}