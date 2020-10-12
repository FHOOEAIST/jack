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
 * <h2>ZeroBufferedSlidingWindowMedianTest</h2>
 * <p>Test class for {@link ZeroBufferedSlidingWindowMedian}</p>
 *
 * @author Rainer Meindl 22.09.2020
 */
@Test
public class ZeroBufferedSlidingWindowMedianTest {

    public void testFilterCorrectKernel() {
        // given
        SlidingWindow<Double> medianWindow = new ZeroBufferedSlidingWindowMedian();
        List<Double> data = List.of(1D, 3D, 8D, 9D, 5D, 3D, 2D);
        int k = 3;
        // when

        // when
        var res = medianWindow.filter(data, k);

        // then
        Assert.assertNotNull(res);
        Assert.assertEquals(res.size(), data.size());
        Assert.assertEquals(List.of(1D, 3D, 8D, 8D, 5D, 3D, 2D), res);
    }

    public void testFilterCorrectKernelAgain() {
        // given
        SlidingWindow<Double> medianWindow = new ZeroBufferedSlidingWindowMedian();
        List<Double> data = List.of(1D, 3D, 8D, 9D, 5D, 3D, 2D);
        int k = 5;
        // when

        // when
        var res = medianWindow.filter(data, k);

        // then
        Assert.assertNotNull(res);
        Assert.assertEquals(res.size(), data.size());
        Assert.assertEquals(List.of(1D, 3D, 5D, 5D, 5D, 3D, 2D), res);
    }

    public void testFilterInvalidKernel() {
        // given
        SlidingWindow<Double> medianWindow = new ZeroBufferedSlidingWindowMedian();
        List<Double> data = List.of(1D, 3D, 8D, 9D, 5D, 3D, 2D);
        int k = 4;
        // when

        // when
        var res = medianWindow.filter(data, k);

        // then
        Assert.assertNotNull(res);
        Assert.assertEquals(res.size(), 0);
    }
}
