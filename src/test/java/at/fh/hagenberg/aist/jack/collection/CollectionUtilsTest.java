package at.fh.hagenberg.aist.jack.collection;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.*;

/**
 * <p>Created by Christoph Praschl on 14/04/2020</p>
 * <p>Test class for {@link CollectionUtils}</p>
 *
 * @author Christoph Praschl
 */
public class CollectionUtilsTest {

    @Test
    public void testIsNullOrEmpty() {
        // given
        List<String> s1 = Arrays.asList("a", "b", "c");

        // when
        boolean nullOrEmpty = CollectionUtils.isNullOrEmpty(s1);

        // then
        Assert.assertFalse(nullOrEmpty);
    }

    @Test
    public void testIsNullOrEmpty2() {
        // given
        List<String> s1 = Collections.emptyList();

        // when
        boolean nullOrEmpty = CollectionUtils.isNullOrEmpty(s1);

        // then
        Assert.assertTrue(nullOrEmpty);
    }

    @Test
    public void testIsNullOrEmpty3() {
        // given
        List<String> s1 = null;

        // when
        boolean nullOrEmpty = CollectionUtils.isNullOrEmpty(s1);

        // then
        Assert.assertTrue(nullOrEmpty);
    }

    @Test
    public void testIsNullOrEmpty4() {
        // given
        Map<String, String> s1 = null;

        // when
        boolean nullOrEmpty = CollectionUtils.isNullOrEmpty(s1);

        // then
        Assert.assertTrue(nullOrEmpty);
    }

    @Test
    public void testIsNullOrEmpty5() {
        // given
        Map<String, String> s1 = new HashMap<>();
        s1.put("a", "a");
        s1.put("b", "b");

        // when
        boolean nullOrEmpty = CollectionUtils.isNullOrEmpty(s1);

        // then
        Assert.assertFalse(nullOrEmpty);
    }
}