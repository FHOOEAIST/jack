/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.random;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>Test class for {@link RandomUtils}</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */
public class RandomUtilsTest {

    @Test
    public void testInRange() {
        // given
        int min = 100;
        int max = 1000;

        // when
        double v = RandomUtils.inRange(min, max);

        // then
        Assert.assertTrue(v <= max);
        Assert.assertTrue(v >= min);
    }
}
