/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.string;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Map;

/**
 * <p>Test class for {@link StringUtils}</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */
public class StringUtilsTest {

    @Test
    public void testFormat1() {
        // given
        String x = "This is a test (" + StringUtils.REPLACEMENT_PLACEHOLDER + ") with a placeholder";
        String y = "test";

        // when
        String res = StringUtils.format(x, y);

        // then
        Assert.assertEquals(res, "This is a test (test) with a placeholder");
    }

    @Test
    public void testFormat2() {
        // given
        String x = "This is a test (" + StringUtils.REPLACEMENT_PLACEHOLDER + ") with a placeholder";
        Object y = "test";

        // when
        String res = StringUtils.format(x, val -> val.toString() + "add", y);

        // then
        Assert.assertEquals(res, "This is a test (testadd) with a placeholder");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFormatException() {
        // given
        String x = "This is a test (" + StringUtils.REPLACEMENT_PLACEHOLDER + ") with a placeholder";
        Object y = "test";
        Object z = "test";

        // when
        String res = StringUtils.format(x, Object::toString, y, z);

        // then
        // expect exception
    }

    @Test
    public void testCountMatches1() {
        // given
        String x = "abcabcabc";
        String sub = "bc";

        // when
        int i = StringUtils.countMatches(x, sub);

        // then
        Assert.assertEquals(i, 3);
    }

    @Test
    public void testCountMatches2() {
        // given
        String x = "abcabcabc";
        String sub = "";

        // when
        int i = StringUtils.countMatches(x, sub);

        // then
        Assert.assertEquals(i, 0);
    }

    @Test
    public void testIndicesOf() {
        // given
        String x = "aaabbcaaabbabbacaa";

        // when
        List<Integer> aa = StringUtils.indicesOf(x, "aa");

        // then
        Assert.assertEquals(aa.size(), 3);
    }

    @Test
    public void testIndicesOf2() {
        // given
        String x = "annannannannanna";

        // when
        List<Integer> aa = StringUtils.indicesOf(x, "anna", false);

        // then
        Assert.assertEquals(aa.size(), 5);
    }

    @Test
    public void testIsNullOrEmpty() {
        // given
        String x = "Somebody once told me the world is gonna roll me";

        // when
        boolean isValid = StringUtils.isNullOrEmpty(x);

        // then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testIsNullOrEmptyEmptyString() {
        // given
        String x = "";

        // when
        boolean isValid = StringUtils.isNullOrEmpty(x);

        // then
        Assert.assertTrue(isValid);
    }

    @Test
    public void testIsNullOrEmptyNullString() {
        // given
        String x = null;

        // when
        boolean isValid = StringUtils.isNullOrEmpty(x);

        // then
        Assert.assertTrue(isValid);
    }

    @Test
    public void testIsNullOrBlank() {
        // given
        String x = "HELLO WORLD!";

        // when
        boolean isValid = StringUtils.isNullOrBlank(x);

        // then
        Assert.assertFalse(isValid);
    }

    @Test
    public void testIsNullOrBlankBlankString() {
        // given
        String x = "   \t \n  ";

        // when
        boolean isValid = StringUtils.isNullOrBlank(x);

        // then
        Assert.assertTrue(isValid);
    }

    @Test
    public void testIsNullOrBlankEmptyString() {
        // given
        String x = "";

        // when
        boolean isValid = StringUtils.isNullOrBlank(x);

        // then
        Assert.assertTrue(isValid);
    }

    @Test
    public void testIsNullOrBlankNullString() {
        // given
        String x = null;

        // when
        boolean isValid = StringUtils.isNullOrBlank(x);

        // then
        Assert.assertTrue(isValid);
    }

    @Test
    public void testRemoveAll() {
        // given
        String x = "Some Test to test the test";

        // when
        var res = StringUtils.removeAll(x, List.of("ome", " ", "test"));

        // then
        Assert.assertEquals(res, "STesttothe");
    }

    @Test
    public void testRemoveAllEmptyString() {
        // given
        String x = "";

        // when
        var res = StringUtils.removeAll(x, List.of("ome", " ", "test"));

        // then
        Assert.assertEquals(res, "");

    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testRemoveAllOnNull() {
        // given
        String x = null;

        // when
        var res = StringUtils.removeAll(x, List.of("ome", " ", "test"));

        // then
        Assert.assertNull(res);

    }

    @Test
    public void testRemoveAllEmptyCollection() {
        // given
        String x = "Some Test to test the test";

        // when
        var res = StringUtils.removeAll(x, List.of(""));

        // then
        Assert.assertEquals(res, x);

    }

    @Test(expectedExceptions = NullPointerException.class)
    public void testRemoveAllNullCollection() {
        // given
        String x = "Some Test to test the test";

        // when
        var res = StringUtils.removeAll(x, null);

        // then
        Assert.assertEquals(res, x);
    }

    @Test
    public void testReplaceAll() {
        // given
        String x = "Some Test to test the test";

        // when
        var res = StringUtils.replaceAll(x, Map.of("Test", "Duck", "test", "duck"));

        // then
        Assert.assertEquals(res, "Some Duck to duck the duck");
    }

    @Test
    public void testReplaceAllEmptyString() {
        // given
        String x = "";

        // when
        var res = StringUtils.replaceAll(x, Map.of("Test", "Duck", "test", "duck"));

        // then
        Assert.assertEquals(res, x);
    }


    @Test(expectedExceptions = NullPointerException.class)
    public void testReplaceAllNullString() {
        // given
        String x = null;

        // when
        StringUtils.replaceAll(x, Map.of("Test", "Duck", "test", "duck"));

        // then

    }


    @Test
    public void testReplaceAllEmptyCollection() {
        // given
        String x = "Some Test to test the test";

        // when
        var res = StringUtils.replaceAll(x, Map.of());

        // then
        Assert.assertEquals(res, x);
    }


    @Test(expectedExceptions = NullPointerException.class)
    public void testReplaceAllNullCollection() {
        // given
        String x = "Some Test to test the test";

        // when
        var res = StringUtils.replaceAll(x, null);

        // then
        Assert.assertEquals(res, x);
    }
}
