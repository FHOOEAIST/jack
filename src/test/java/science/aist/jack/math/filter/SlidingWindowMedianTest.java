/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.math.filter;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * <h2>SlidingWindowMedianTest</h2>
 * <p>Test class for {@link SlidingWindowMedian}</p>
 *
 * @author Rainer Meindl 16.09.2020
 */
@Test
public class SlidingWindowMedianTest {

    public void testFilter() {
        // given
        SlidingWindow<Double> medianWindow = new SlidingWindowMedian<>();
        List<Double> data = List.of(1D, 3D, 8D, 9D, 5D, 3D, 2D);
        int k = 3;
        // when

        // when
        var res = medianWindow.filter(data, k);

        // then
        Assert.assertNotNull(res);
        Assert.assertEquals(res.size(), 5);
        Assert.assertEquals(List.of(3D, 8D, 8D, 5D, 3D), res);

    }

    public void testFilterEmptyData() {
        // given
        SlidingWindow<Double> medianWindow = new SlidingWindowMedian<>();
        List<Double> data = List.of();
        int k = 3;

        // when
        var res = medianWindow.filter(data, k);

        // then
        Assert.assertNotNull(res);
        Assert.assertEquals(res.size(), 0);
    }

    public void testFilterNoKernel() {
        // given
        SlidingWindow<Double> medianWindow = new SlidingWindowMedian<>();
        List<Double> data = List.of(1D, 3D, 8D, 9D, 5D, 3D, 2D);
        int k = 0;

        // when
        var res = medianWindow.filter(data, k);

        // then
        Assert.assertNotNull(res);
        Assert.assertEquals(res.size(), 0);
    }

    public void testFilterNegativeKernel() {
        // given
        SlidingWindow<Double> medianWindow = new SlidingWindowMedian<>();
        List<Double> data = List.of(1D, 3D, 8D, 9D, 5D, 3D, 2D);
        int k = -3;

        // when
        var res = medianWindow.filter(data, k);

        // then
        Assert.assertNotNull(res);
        Assert.assertEquals(res.size(), 0);
    }

    public void testFilterDataSmallerThanKernel() {
        // given
        SlidingWindow<Double> medianWindow = new SlidingWindowMedian<>();
        List<Double> data = List.of(1D, 3D, 2D);
        int k = 5;
        // when

        // when
        var res = medianWindow.filter(data, k);

        // then
        Assert.assertNotNull(res);
        Assert.assertEquals(res.size(), 0);
    }
}
