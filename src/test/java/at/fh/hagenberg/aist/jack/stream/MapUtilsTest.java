package at.fh.hagenberg.aist.jack.stream;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Map;

import static at.fh.hagenberg.aist.jack.data.Pair.of;

/**
 * <p>Created by Andreas Pointner on 28/08/2019</p>
 * <p>Test class for {@link MapUtils}</p>
 *
 * @author Andreas Pointner
 */
public class MapUtilsTest {

    @Test
    public void testPrefixMapKey() {
        // given
        Map<String, Object> map = MapUtils.mapOfValues(of("x", 1), of("y", 2d), of("z", "3"));

        // when
        Map<String, Object> aMap = MapUtils.prefixMapKey(map, "a_");

        // then
        Assert.assertTrue(aMap.containsKey("a_x"));
        Assert.assertEquals(aMap.get("a_x"), 1);
        Assert.assertTrue(aMap.containsKey("a_y"));
        Assert.assertEquals(aMap.get("a_y"), 2d);
        Assert.assertTrue(aMap.containsKey("a_z"));
        Assert.assertEquals(aMap.get("a_z"), "3");
    }

    @Test
    public void testMapOfValues() {
        // given

        // when
        Map<String, Object> map = MapUtils.mapOfValues(of("x", 1), of("y", 2d), of("z", "3"));

        // then
        Assert.assertTrue(map.containsKey("x"));
        Assert.assertEquals(map.get("x"), 1);
        Assert.assertTrue(map.containsKey("y"));
        Assert.assertEquals(map.get("y"), 2d);
        Assert.assertTrue(map.containsKey("z"));
        Assert.assertEquals(map.get("z"), "3");
    }
}
