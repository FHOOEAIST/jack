package at.fh.hagenberg.aist.jack.math;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * <p>Created by Andreas Pointner on 28/08/2019</p>
 * <p>Test class for {@link MathUtils}</p>
 *
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
public class MathUtilsTest {

    @Test
    public void testHigher() {
        // given
        double lower = 10;
        double higher = 15;

        // when
        boolean isHigher = MathUtils.higher(lower, higher);

        // then
        Assert.assertTrue(isHigher);
    }

    @Test
    public void testLower() {
        // given
        double lower = 10;
        double higher = 15;

        // when
        boolean isLower = MathUtils.lower(higher, lower);

        // then
        Assert.assertTrue(isLower);
    }

    @Test
    public void testTestEquals() {
        // given
        double x = 0.00000000000000000000000001;
        double y = 0.00000000000000000000000002;

        // when
        boolean equals = MathUtils.equals(x, y);

        // then
        Assert.assertTrue(equals);
    }

    @Test
    public void testEquals2() {
        // given
        double x = 0.000001;
        double y = 0.000002;

        // when
        boolean equals = MathUtils.equals(x, y);

        // then
        Assert.assertFalse(equals);
    }

    @Test
    public void testEquals3() {
        // given
        double x = 0.000001;
        double y = 0.000002;

        // when
        boolean equals = MathUtils.equals(x, y, 0.00001);

        // then
        Assert.assertTrue(equals);
    }

    @Test
    public void testBiggerThan1() {
        // given
        double x = 0.000001;
        double y = 0.000002;

        // when
        boolean bigger = MathUtils.biggerThan(y, x);

        // then
        Assert.assertTrue(bigger);
    }

    @Test
    public void testBiggerThan2() {
        // given
        double x = 0.000001;
        double y = 0.000002;

        // when
        boolean bigger = MathUtils.biggerThan(y, x, 0.000001);

        // then
        Assert.assertFalse(bigger);
    }

    @Test
    public void testLowerThan1() {
        // given
        double x = 0.000001;
        double y = 0.000002;

        // when
        boolean lower = MathUtils.lowerThan(x, y);

        // then
        Assert.assertTrue(lower);
    }

    @Test
    public void testLowerThan2() {
        // given
        double x = 0.000001;
        double y = 0.000002;

        // when
        boolean lower = MathUtils.lowerThan(x, y, 0.000001);

        // then
        Assert.assertFalse(lower);
    }

    @Test
    public void testPercent() {
        // given
        double min = 10;
        double max = 100;
        double val = 55;

        // when
        double percentage = MathUtils.percent(min, max, val);

        // then
        Assert.assertEquals(percentage, 0.5, 0.0001);
    }

    @Test
    public void testCosd() {
        // given
        double alpha = 45;

        // when
        double cosd = MathUtils.cosd(alpha);

        // then
        Assert.assertEquals(cosd, Math.sqrt(2) / 2.0, 0.0001);
    }

    @Test
    public void testSind() {
        // given
        double alpha = 30;

        // when
        double sind = MathUtils.sind(alpha);

        // then
        Assert.assertEquals(sind, 0.5, 0.0001);
    }

    @Test
    public void testMax() {
        // given

        // when
        int max = MathUtils.max(1, 7, 6, 3, 4, 2, 1, 9);

        // then
        Assert.assertEquals(max, 9);
    }

    @Test
    public void testMin() {
        // given

        // when
        int max = MathUtils.min(1, 7, 6, 3, 4, 2, 1, 9);

        // then
        Assert.assertEquals(max, 1);
    }

    @Test
    public void testMinMax() {
        // given

        // when
        MinMax<Integer> integerMinMax = MathUtils.minMax(1, 7, 6, 3, 4, 2, 1, 9);

        // then
        Assert.assertTrue(integerMinMax.isPresent());
        Assert.assertEquals((int) integerMinMax.getMin(), 1);
        Assert.assertEquals((int) integerMinMax.getMax(), 9);
    }


    @Test
    public void testMinMax2() {
        // given

        // when
        MinMax<Integer> integerMinMax = MathUtils.minMax();

        // then
        Assert.assertFalse(integerMinMax.isPresent());
    }

    @Test
    public void testMinMax3() {
        // given

        // when
        MinMax<Double> integerMinMax = MathUtils.minMax(5.0, 8.9, 1.0, 0.0);

        // then
        Assert.assertTrue(integerMinMax.isPresent());
        Assert.assertTrue(MathUtils.equals(integerMinMax.getMin(), 0.0));
        Assert.assertTrue(MathUtils.equals(integerMinMax.getMax(), 8.9));
    }
}
