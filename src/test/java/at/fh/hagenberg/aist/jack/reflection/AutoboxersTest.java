package at.fh.hagenberg.aist.jack.reflection;

import at.fh.hagenberg.aist.jack.data.Pair;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

import static org.testng.Assert.*;

/**
 * <p>Created by Christoph Praschl on 17/04/2020</p>
 * <p>Test class for {@link Autoboxers}</p>
 *
 * @author Christoph Praschl christoph.praschl@fh-hagenberg.at
 */
public class AutoboxersTest {

    @Test
    public void testGetBoxingClasses() {
        // given

        // when
        Map<Class<?>, Class<?>> boxingClasses = Autoboxers.getBoxingClasses();

        // then
        Assert.assertEquals(boxingClasses.size(), 8);
        for (Class<? extends Serializable> aClass : Arrays.asList(Boolean.class, Byte.class, Character.class, Double.class, Float.class, Integer.class, Long.class, Short.class)) {
            Assert.assertTrue(boxingClasses.containsKey(aClass));
        }

    }

    @Test
    public void testGetPrimitiveDefaultValues() {
        // given

        // when
        Map<Class<?>, Class<?>> boxingClasses = Autoboxers.getPrimitiveClasses();

        // then
        Assert.assertEquals(boxingClasses.size(), 8);
        for (Class<? extends Serializable> aClass : Arrays.asList(boolean.class, byte.class, char.class, double.class, float.class, int.class, long.class, short.class)) {
            Assert.assertTrue(boxingClasses.containsKey(aClass));
        }
    }

    @Test
    public void testGetPrimitiveClasses() {
        // given

        // when
        Map<Class<?>, Object> primitiveDefaultValues = Autoboxers.getPrimitiveDefaultValues();

        // then
        Assert.assertEquals(primitiveDefaultValues.get(boolean.class), false);
        Assert.assertEquals(primitiveDefaultValues.get(byte.class), (byte)0);
        Assert.assertEquals(primitiveDefaultValues.get(char.class), (char)0);
        Assert.assertEquals(primitiveDefaultValues.get(double.class), 0d);
        Assert.assertEquals(primitiveDefaultValues.get(float.class), 0f);
        Assert.assertEquals(primitiveDefaultValues.get(int.class), 0);
        Assert.assertEquals(primitiveDefaultValues.get(long.class), 0L);
        Assert.assertEquals(primitiveDefaultValues.get(short.class), (short)0);
    }

    @Test
    public void testGetPrimitiveDefaultValue() {
        // given
        Class<?> c = double.class;

        // when
        Object primitiveDefaultValue = Autoboxers.getPrimitiveDefaultValue(c);

        // then
        Assert.assertEquals(primitiveDefaultValue, 0d);
    }

    @Test
    public void testGetPrimitiveDefaultValue2() {
        // given
        Class<Integer> c = Integer.class;

        // when
        Integer primitiveDefaultValue = Autoboxers.getPrimitiveDefaultValue(c);

        // then
        Assert.assertNull(primitiveDefaultValue);
    }

    @Test
    public void testIsBoxingClass() {
        // given
        Class<Integer> c = Integer.class;

        // when
        boolean boxingClass = Autoboxers.isBoxingClass(c);

        // then
        Assert.assertTrue(boxingClass);
    }

    @Test
    public void testIsBoxingClass2() {
        // given
        Class<?> c = int.class;

        // when
        boolean boxingClass = Autoboxers.isBoxingClass(c);

        // then
        Assert.assertFalse(boxingClass);
    }

    @Test
    public void testIsPrimitiveClass() {
        // given
        Class<?> c = int.class;

        // when
        boolean primitiveClass = Autoboxers.isPrimitiveClass(c);

        // then
        Assert.assertTrue(primitiveClass);
    }

    @Test
    public void testIsPrimitiveClass2() {
        // given
        Class<Double> c = Double.class;

        // when
        boolean primitiveClass = Autoboxers.isPrimitiveClass(c);

        // then
        Assert.assertFalse(primitiveClass);
    }


    @Test
    public void testIsPrimitiveOrBoxingClass() {
        // given
        Class<?> c = boolean.class;

        // when
        boolean primitiveClass = Autoboxers.isPrimitiveOrBoxingClass(c);

        // then
        Assert.assertTrue(primitiveClass);
    }

    @Test
    public void testIsPrimitiveOrBoxingClass2() {
        // given
        Class<?> c = Iterator.class;

        // when
        boolean primitiveClass = Autoboxers.isPrimitiveOrBoxingClass(c);

        // then
        Assert.assertFalse(primitiveClass);
    }

    @Test
    public void testGetBoxingClass() {
        // given
        Class<?> c = int.class;

        // when
        Class<?> boxingClass = Autoboxers.getBoxingClass(c);

        // then
        Assert.assertEquals(boxingClass, Integer.class);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetBoxingClass2() {
        // given
        Class<?> c = Integer.class;

        // when
        Autoboxers.getBoxingClass(c);

        // then - exception
    }

    @Test
    public void testGetPrimitiveClass() {
        // given
        Class<?> c = Integer.class;

        // when
        Class<?> boxingClass = Autoboxers.getPrimitiveClass(c);

        // then
        Assert.assertEquals(boxingClass, int.class);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testGetPrimitiveClass2() {
        // given
        Class<?> c = int.class;

        // when
        Autoboxers.getPrimitiveClass(c);

        // then - exception
    }
}