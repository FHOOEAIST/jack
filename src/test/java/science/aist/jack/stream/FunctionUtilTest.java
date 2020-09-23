/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.stream;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <p>Test class for {@link FunctionUtil}</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */
public class FunctionUtilTest {

    @Test
    public void testEmptyConsumer() {
        // given

        // when
        Consumer<Object> objectConsumer = FunctionUtil.emptyConsumer();

        // then
        Assert.assertNotNull(objectConsumer);
        objectConsumer.accept(null); // should not throw an exception
    }

    @Test
    public void testEmptyBiConsumer() {
        // given

        // when
        BiConsumer<Object, Object> objectObjectBiConsumer = FunctionUtil.emptyBiConsumer();

        // then
        Assert.assertNotNull(objectObjectBiConsumer);
        objectObjectBiConsumer.accept(null, null); // should not throw an exception
    }

    @Test
    public void testIdentity() {
        // given
        String x = "123";

        // when
        Function<String, String> identity = FunctionUtil.identity();

        // then
        Assert.assertNotNull(identity);
        Assert.assertEquals(x, identity.apply(x));
    }
}
