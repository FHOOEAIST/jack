package at.fh.hagenberg.aist.jack.random;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>Created by Andreas Pointner on 28/08/2019</p>
 * <p>Test class for {@link RandomUtils}</p>
 *
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
public class RandomUtilsTest {

    @Test
    public void testInRange() {
        // given
        int min = 100;
        int max = 1000;

        // when
        double v = RandomUtils.inRange(min, max);

        // then
        Assert.assertTrue(v <= max);
        Assert.assertTrue(v >= min);
    }
}
