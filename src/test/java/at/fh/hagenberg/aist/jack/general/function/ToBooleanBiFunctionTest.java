package at.fh.hagenberg.aist.jack.general.function;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>Created by Andreas Pointner on 03.12.2019</p>
 * <p>Test class for {@link ToBooleanBiFunction}</p>
 *
 * @author Andreas Pointner
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