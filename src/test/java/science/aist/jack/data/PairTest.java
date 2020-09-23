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

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>Test class for {@link Pair}</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */
public class PairTest {

    @Test
    public void testOf() {
        // given

        // when
        Pair<String, Integer> a = Pair.of("a", 1);

        // then
        Assert.assertEquals(a.getFirst(), "a");
        Assert.assertEquals(a.getSecond(), Integer.valueOf(1));
    }

    @Test
    public void testToMap() {
        // given
        List<Pair<String, Integer>> list = Collections.singletonList(Pair.of("a", 1));

        // when
        Map<String, Integer> collect = list.stream().collect(Pair.toMap());

        // then
        Assert.assertTrue(collect.containsKey("a"));
        Assert.assertEquals(collect.get("a"), Integer.valueOf(1));
    }
}
