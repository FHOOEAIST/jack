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
 * <h2>SubsetSlidingWindowTest</h2>
 * <p>Test class for {@link SubsetSlidingWindow}</p>
 *
 * @author David Baumgartner
 * @since 2.2.0
 */
@Test
public class SubsetSlidingWindowTest {

    public void testFilter() {
        // given
        GenericSlidingWindow<Double, List<Double>> medianWindow = new SubsetSlidingWindow<>();
        List<Double> data = List.of(1D, 3D, 8D, 9D, 5D, 3D, 2D);
        int k = 3;

        // when
        var res = medianWindow.filter(data, k);

        // then
        Assert.assertNotNull(res);
        Assert.assertEquals(res.size(), 5);
        Assert.assertEquals(List.of(1D, 3D, 8D), res.get(0));
        Assert.assertEquals(List.of(3D, 8D, 9D), res.get(1));

    }

    public void testFilterEmptyData() {
        // given
        GenericSlidingWindow<Double, List<Double>> medianWindow = new SubsetSlidingWindow<>();
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
        GenericSlidingWindow<Double, List<Double>> medianWindow = new SubsetSlidingWindow<>();
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
        GenericSlidingWindow<Double, List<Double>> medianWindow = new SubsetSlidingWindow<>();
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
        GenericSlidingWindow<Double, List<Double>> medianWindow = new SubsetSlidingWindow<>();
        List<Double> data = List.of(1D, 3D, 2D);
        int k = 5;

        // when
        var res = medianWindow.filter(data, k);

        // then
        Assert.assertNotNull(res);
        Assert.assertEquals(res.size(), 0);
    }
}
