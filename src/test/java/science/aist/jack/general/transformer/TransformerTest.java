/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.general.transformer;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;

/**
 * <p>Test class for {@link Transformer}</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */
public class TransformerTest {
    private final Transformer<A, B> abTransformer = new Transformer<>() {

        @Override
        public A transformTo(B b) {
            return new A();
        }

        @Override
        public B transformFrom(A a) {
            return new B();
        }
    };

    @Test
    public void testTransformToCollection() {
        // given
        Collection<B> bs = Arrays.asList(new B(), new B());

        // when
        Collection<A> as = abTransformer.transformToCollection(bs);

        // then
        Assert.assertNotNull(as);
        as.forEach(Assert::assertNotNull);
    }

    @Test
    public void testTransformFromCollection() {
        // given
        Collection<A> as = Arrays.asList(new A(), new A());

        // when
        Collection<B> bs = abTransformer.transformFromCollection(as);

        // then
        Assert.assertNotNull(bs);
        bs.forEach(Assert::assertNotNull);
    }
}

class A {

}

class B {

}