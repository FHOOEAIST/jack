/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.general.function;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>Test class for {@link ToBooleanBiFunction}</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */

public class ToBooleanBiFunctionTest {

    @Test
    public void testApplyAsBoolean() {
        // given

        // when
        ToBooleanBiFunction<String, Integer> x = (s, i) -> true;

        // then
        Assert.assertTrue(x.applyAsBoolean("", 1));
    }
}