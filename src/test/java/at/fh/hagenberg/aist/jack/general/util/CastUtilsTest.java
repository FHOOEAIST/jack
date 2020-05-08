package at.fh.hagenberg.aist.jack.general.util;

import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.*;
import static at.fh.hagenberg.aist.jack.general.util.CastUtils.cast;

/**
 * <p>Created by Andreas Pointner on 08.05.2020</p>
 * <p>Test class for {@link CastUtils}</p>
 *
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */

public class CastUtilsTest {

    @Test
    public void testCast1() {
        // given
        List<Object> list = new ArrayList<>();
        list.add("abc");

        // when
        List<String> stringList = cast(list);

        // then
        assertNotNull(stringList);
        assertEquals(stringList.get(0), "abc");
    }

    @Test(expectedExceptions = ClassCastException.class)
    public void testCast2() {
        // given
        String x = "abc";

        // when
        Integer y = cast(x);

        // then
    }
}