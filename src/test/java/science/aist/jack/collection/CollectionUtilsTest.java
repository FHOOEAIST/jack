/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.collection;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

/**
 * <p>Test class for {@link CollectionUtils}</p>
 *
 * @author Christoph Praschl
 * @since 2.0
 */
@SuppressWarnings("ConstantConditions") // we exactly want to test that
public class CollectionUtilsTest {

    @Test
    public void testIsNullOrEmpty() {
        // given
        List<String> s1 = Arrays.asList("a", "b", "c");

        // when
        boolean nullOrEmpty = CollectionUtils.isNullOrEmpty(s1);

        // then
        Assert.assertFalse(nullOrEmpty);
    }

    @Test
    public void testIsNullOrEmpty2() {
        // given
        List<String> s1 = Collections.emptyList();

        // when
        boolean nullOrEmpty = CollectionUtils.isNullOrEmpty(s1);

        // then
        Assert.assertTrue(nullOrEmpty);
    }

    @Test
    public void testIsNullOrEmpty3() {
        // given
        List<String> s1 = null;

        // when
        boolean nullOrEmpty = CollectionUtils.isNullOrEmpty(s1);

        // then
        Assert.assertTrue(nullOrEmpty);
    }

    @Test
    public void testIsNullOrEmpty4() {
        // given
        Map<String, String> s1 = null;

        // when
        boolean nullOrEmpty = CollectionUtils.isNullOrEmpty(s1);

        // then
        Assert.assertTrue(nullOrEmpty);
    }

    @Test
    public void testIsNullOrEmpty5() {
        // given
        Map<String, String> s1 = new HashMap<>();
        s1.put("a", "a");
        s1.put("b", "b");

        // when
        boolean nullOrEmpty = CollectionUtils.isNullOrEmpty(s1);

        // then
        Assert.assertFalse(nullOrEmpty);
    }
}