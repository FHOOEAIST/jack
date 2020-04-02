package at.fh.hagenberg.aist.jack.string;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

/**
 * <p>Created by Andreas Pointner on 12/09/2019</p>
 * <p>Test class for {@link StringUtils}</p>
 *
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
public class StringUtilsTest {

    @Test
    public void testFormat1() {
        // given
        String x = "This is a test ("+StringUtils.REPLACEMENT_PLACEHOLDER+") with a placeholder";
        String y = "test";

        // when
        String res = StringUtils.format(x, y);

        // then
        Assert.assertEquals(res, "This is a test (test) with a placeholder");
    }

    @Test
    public void testFormat2() {
        // given
        String x = "This is a test ("+StringUtils.REPLACEMENT_PLACEHOLDER+") with a placeholder";
        Object y = "test";

        // when
        String res = StringUtils.format(x, val -> val.toString() + "add",  y);

        // then
        Assert.assertEquals(res, "This is a test (testadd) with a placeholder");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testFormatException() {
        // given
        String x = "This is a test ("+StringUtils.REPLACEMENT_PLACEHOLDER+") with a placeholder";
        Object y = "test";
        Object z = "test";

        // when
        String res = StringUtils.format(x, Object::toString,  y, z);

        // then
        // expect exception
    }

    @Test
    public void testCountMatches1() {
        // given
        String x  = "abcabcabc";
        String sub = "bc";

        // when
        int i = StringUtils.countMatches(x, sub);

        // then
        Assert.assertEquals(i, 3);
    }

    @Test
    public void testCountMatches2() {
        // given
        String x  = "abcabcabc";
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
}
