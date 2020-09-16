package science.aist.jack.general.transformer;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.Collection;

/**
 * <p>Created by Andreas Pointner on 28/08/2019</p>
 * <p>Test class for {@link Transformer}</p>
 *
 * @author Andreas Pointner
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