package at.fh.hagenberg.aist.jack.math;

import java.util.stream.IntStream;

/**
 * Created by Lukas Reithmeier on 11.07.2018
 *
 * @author Lukas Reithmeier lukas.reithmeier@fh-hagenberg.at
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
// Weaker access cannot be provided, as this is a library and the functions are used outside this library too
@SuppressWarnings("WeakerAccess")
public class MathUtils {
    /**
     * double difference
     */
    private static final double EPSILON = 9.99999993922529E-9D;
    public static final String VALUES_MUST_NOT_BE_NULL = "Values must not be null";

    private MathUtils() {
        // hide constructor
    }

    /**
     * determines if a candidate is higher than a highest number
     *
     * @param highest   highest
     * @param candidate candidate
     * @return true, if a candidate is higher than a highest number
     */
    public static boolean higher(Double highest, Double candidate) {
        return (highest == null && candidate != null) || (candidate != null && highest < candidate);
    }

    /**
     * determines if a candidate is lower than a highest number
     *
     * @param lowest    lowest
     * @param candidate candidate
     * @return true, if a candidate is lower than a highest number
     */
    public static boolean lower(Double lowest, Double candidate) {
        return (lowest == null && candidate != null) || (candidate != null && lowest > candidate);
    }

    /**
     * determines if  two double values are equal
     *
     * @param a       first value
     * @param b       second value
     * @param epsilon the epsilon to be used to check if two double values are equal
     * @return a == b
     */
    public static boolean equals(Double a, Double b, double epsilon) {
        if (a == null || b == null) {
            throw new IllegalArgumentException(VALUES_MUST_NOT_BE_NULL);
        }
        return Math.abs(a - b) < epsilon;
    }

    /**
     * determines if two double values are equal
     *
     * @param a first value
     * @param b second value
     * @return a == b
     * @see MathUtils#equals(Double, Double, double)
     */
    public static boolean equals(Double a, Double b) {
        return equals(a, b, EPSILON);
    }


    /**
     * determines if  one value is bigger than an other value
     *
     * @param a       first value
     * @param b       second value
     * @param epsilon the epsilon to be used to check which double value is bigger
     * @return true, if the first value is bigger than the second value
     */
    public static boolean biggerThan(Double a, Double b, double epsilon) {
        if (a == null || b == null) {
            throw new IllegalArgumentException(VALUES_MUST_NOT_BE_NULL);
        }
        return (a - b) > epsilon;
    }


    /**
     * determines if  one value is bigger than an other value
     *
     * @param a first value
     * @param b second value
     * @return true, if the first value is bigger than the second value
     * @see MathUtils#biggerThan(Double, Double, double)
     */
    public static boolean biggerThan(Double a, Double b) {
        return biggerThan(a, b, EPSILON);
    }

    /**
     * determines if  one value is bigger than an other value
     *
     * @param a       first value
     * @param b       second value
     * @param epsilon the epsilon to be used to check which double value is lower
     * @return true, if the second value is bigger than the first value
     */
    public static boolean lowerThan(Double a, Double b, double epsilon) {
        if (a == null || b == null) {
            throw new IllegalArgumentException(VALUES_MUST_NOT_BE_NULL);
        }
        return (b - a) > epsilon;
    }

    /**
     * determines if  one value is bigger than an other value
     *
     * @param a first value
     * @param b second value
     * @return true, if the second value is bigger than the first value
     * @see MathUtils#lowerThan(Double, Double, double)
     */
    public static boolean lowerThan(Double a, Double b) {
        return lowerThan(a, b, EPSILON);
    }

    /**
     * Calculates the percentage value of a given value between a given maximum and a given minimum
     *
     * @param min   the minimum value
     * @param max   the maximum value
     * @param value the value to calculate the percentage
     * @return the percentage of value in relation to min and max
     */
    public static double percent(double min, double max, double value) {
        return (value - min) / (max - min);
    }

    /**
     * Executes math.cos for degrees
     *
     * @param angleDeg angle in degrees
     * @return Math.cos() result
     */
    public static double cosd(double angleDeg) {
        return Math.cos(Math.toRadians(angleDeg));
    }

    /**
     * Executes math.sin for degrees
     *
     * @param angleDeg angle in degrees
     * @return Math.sin() result
     */
    public static double sind(double angleDeg) {
        return Math.sin(Math.toRadians(angleDeg));
    }

    /**
     * Calculates the maximum of all given values.
     *
     * @param val the values
     * @return the maximum value
     */
    public static int max(int... val) {
        int max = Integer.MIN_VALUE;
        for (int v : val)
            if (v > max)
                max = v;
        return max;
    }

    /**
     * Calculates the minimum of all given values. If the values are empty, {@link Integer#MAX_VALUE} is returned.
     *
     * @param val the values
     * @return the min value of all values
     */
    public static int min(int... val) {
        return IntStream.of(val).min().orElse(Integer.MAX_VALUE);
    }

    /**
     * Gets the min and the max value of the given values
     * @param values for which min and max should be determined
     * @return MinMax for the given values
     */
    public static MinMax<Integer> minMax(int... values){
        // code duplication to avoid time consuming boxing of primitive data typs
        if(values == null || values.length == 0){
            return MinMax.empty();
        }

        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;

        for (int i : values) {
            if(i < min){
                min = i;
            }
            if(i > max){
                max = i;
            }
        }
        return MinMax.of(min, max);
    }

    /**
     * Gets the min and the max value of the given values
     * @param values for which min and max should be determined
     * @return MinMax for the given values
     */
    public static MinMax<Double> minMax(double... values){
        // code duplication to avoid time consuming boxing of primitive data typs
        if(values == null || values.length == 0){
            return MinMax.empty();
        }

        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;

        for (double i : values) {
            if(i < min){
                min = i;
            }
            if(i > max){
                max = i;
            }
        }
        return MinMax.of(min, max);
    }
}
