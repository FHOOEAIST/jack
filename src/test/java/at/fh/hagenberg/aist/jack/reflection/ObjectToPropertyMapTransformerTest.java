package at.fh.hagenberg.aist.jack.reflection;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

/**
 * <p>Created by Andreas Pointner on 17.03.2020</p>
 * <p>Test class for {@link ObjectToPropertyMapTransformer}</p>
 *
 * @author Andreas Pointner
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
        public double a3 = 3.1415;
        protected String a2 = "2";
        private final int a1 = 1;
    }

    @SuppressWarnings("unused")
    private static class B extends A {
        public double b3 = 2.7182;
        protected String b2 = "3";
        private final int b1 = 2;
    }
}