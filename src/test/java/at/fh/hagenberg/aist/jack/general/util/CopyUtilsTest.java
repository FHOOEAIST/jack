package at.fh.hagenberg.aist.jack.general.util;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.Serializable;

/**
 * <p>Created by Andreas Pointner on 28/08/2019</p>
 * <p>Test class for {@link CopyUtils}</p>
 *
 * @author Andreas Pointner
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