package science.aist.jack.general.function;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>Test class for {@link TriFunction}</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */

public class TriFunctionTest {

    @Test
    public void testApply() {
        // given
        // when
        TriFunction<Integer, Integer, Integer, String> function = (a, b, c) -> String.format("%d, %d, %d", a, b, c);

        // then
        Assert.assertEquals(function.apply(1, 2, 3), "1, 2, 3");
    }

    @Test
    public void testAndThen() {
        // given
        TriFunction<String, String, String, String> function = (a, b, c) -> String.format("%s, %s, %s", a, b, c);

        // when
        function = function.andThen(String::toUpperCase);

        // then
        Assert.assertEquals(function.apply("a", "b", "C"), "A, B, C");
    }
}