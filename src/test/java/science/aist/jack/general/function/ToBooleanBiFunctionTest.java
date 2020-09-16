package science.aist.jack.general.function;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>Test class for {@link ToBooleanBiFunction}</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */

public class ToBooleanBiFunctionTest {

    @Test
    public void testApplyAsBoolean() {
        // given

        // when
        ToBooleanBiFunction<String, Integer> x = (s, i) -> true;

        // then
        Assert.assertTrue(x.applyAsBoolean("", 1));
    }
}