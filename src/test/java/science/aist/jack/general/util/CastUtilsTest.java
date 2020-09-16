package science.aist.jack.general.util;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * <p>Test class for {@link CastUtils}</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */

public class CastUtilsTest {

    @Test
    public void testCast1() {
        // given
        List<Object> list = new ArrayList<>();
        list.add("abc");

        // when
        List<String> stringList = CastUtils.cast(list);

        // then
        assertNotNull(stringList);
        assertEquals(stringList.get(0), "abc");
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void testCast2() {
        // given
        String x = "abc";

        // when
        Integer y = CastUtils.cast(x);

        // then
    }
}