package science.aist.jack.data;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * <p>Created by Andreas Pointner on 28/08/2019</p>
 * <p>Test class for {@link Pair}</p>
 *
 * @author Andreas Pointner
 */
public class PairTest {

    @Test
    public void testOf() {
        // given

        // when
        Pair<String, Integer> a = Pair.of("a", 1);

        // then
        Assert.assertEquals(a.getFirst(), "a");
        Assert.assertEquals(a.getSecond(), Integer.valueOf(1));
    }

    @Test
    public void testToMap() {
        // given
        List<Pair<String, Integer>> list = Collections.singletonList(Pair.of("a", 1));

        // when
        Map<String, Integer> collect = list.stream().collect(Pair.toMap());

        // then
        Assert.assertTrue(collect.containsKey("a"));
        Assert.assertEquals(collect.get("a"), Integer.valueOf(1));
    }
}
