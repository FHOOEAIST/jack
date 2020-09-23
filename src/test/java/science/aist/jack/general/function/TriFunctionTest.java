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
 * <p>Test class for {@link TriFunction}</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */

public class TriFunctionTest {

    @Test
    public void testApply() {
        // given
        // when
        TriFunction<Integer, Integer, Integer, String> function = (a, b, c) -> String.format("%d, %d, %d", a, b, c);

        // then
        Assert.assertEquals(function.apply(1, 2, 3), "1, 2, 3");
    }

    @Test
    public void testAndThen() {
        // given
        TriFunction<String, String, String, String> function = (a, b, c) -> String.format("%s, %s, %s", a, b, c);

        // when
        function = function.andThen(String::toUpperCase);

        // then
        Assert.assertEquals(function.apply("a", "b", "C"), "A, B, C");
    }
}