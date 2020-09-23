/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.reflection;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * <p>Test class for {@link ObjectToPropertyMapTransformer}</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */

public class ObjectToPropertyMapTransformerTest {

    @Test
    public void testTransformFrom() {
        // given
        ObjectToPropertyMapTransformer transformer = new ObjectToPropertyMapTransformer();
        B b = new B();

        // when
        Map<String, Object> stringObjectMap = transformer.transformFrom(b);

        // then
        Assert.assertEquals(stringObjectMap.size(), 3);
        Assert.assertTrue(stringObjectMap.containsKey("b1"));
        Assert.assertEquals(stringObjectMap.get("b1"), 2);
        Assert.assertTrue(stringObjectMap.containsKey("b2"));
        Assert.assertEquals(stringObjectMap.get("b2"), "3");
        Assert.assertTrue(stringObjectMap.containsKey("b3"));
        Assert.assertEquals((Double) stringObjectMap.get("b3"), Math.E, 0.0001);
    }

    @Test
    public void testTransformFrom2() {
        // given
        ObjectToPropertyMapTransformer transformer = new ObjectToPropertyMapTransformer();
        transformer.setIncludeParentFields(true);
        B b = new B();

        // when
        Map<String, Object> stringObjectMap = transformer.transformFrom(b);

        // then
        Assert.assertEquals(stringObjectMap.size(), 6);
        Assert.assertTrue(stringObjectMap.containsKey("a1"));
        Assert.assertEquals(stringObjectMap.get("a1"), 1);
        Assert.assertTrue(stringObjectMap.containsKey("a2"));
        Assert.assertEquals(stringObjectMap.get("a2"), "2");
        Assert.assertTrue(stringObjectMap.containsKey("a3"));
        Assert.assertEquals((Double) stringObjectMap.get("a3"), Math.PI, 0.0001);
        Assert.assertTrue(stringObjectMap.containsKey("b1"));
        Assert.assertEquals(stringObjectMap.get("b1"), 2);
        Assert.assertTrue(stringObjectMap.containsKey("b2"));
        Assert.assertEquals(stringObjectMap.get("b2"), "3");
        Assert.assertTrue(stringObjectMap.containsKey("b3"));
        Assert.assertEquals((Double) stringObjectMap.get("b3"), Math.E, 0.0001);
    }

    @SuppressWarnings("unused")
    private static class A {
        private final int a1 = 1;
        public double a3 = 3.1415;
        protected String a2 = "2";
    }

    @SuppressWarnings("unused")
    private static class B extends A {
        private final int b1 = 2;
        public double b3 = 2.7182;
        protected String b2 = "3";
    }
}