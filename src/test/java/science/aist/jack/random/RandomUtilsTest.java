package science.aist.jack.random;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>Test class for {@link RandomUtils}</p>
 *
 * @author Andreas Pointner
 * @since 1.0
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
