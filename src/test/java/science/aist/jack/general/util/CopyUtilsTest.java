/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.general.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.Serializable;

/**
 * <p>Test class for {@link CopyUtils}</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */
public class CopyUtilsTest {
    @Test
    public void testDeepCopy() {
        // given
        B b = new B();
        b.s = "test";
        A a = new A();
        a.b = b;
        a.s = "test2";

        // when
        A copy = CopyUtils.deepCopy(a);

        // then
        Assert.assertNotNull(copy);
        Assert.assertEquals(a.s, "test2");
        Assert.assertEquals(a.b.s, "test");
    }

    @Test
    public void testDeepFail() {
        // given
        X x = new X();

        // when
        X copy = CopyUtils.deepCopy(x);

        // then
        Assert.assertNull(copy);
    }

}

class A implements Serializable {
    B b;
    String s;
}

class B implements Serializable {
    String s;
}

class X {

}