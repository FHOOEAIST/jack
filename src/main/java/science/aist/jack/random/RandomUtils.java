package science.aist.jack.random;

import java.util.Random;

/**
 * <p>Provides functions to generate random numbers</p>
 *
 * @author Lukas Reithmeier
 * @since 1.0
 */
public class RandomUtils {
    /**
     * Seed for random
     */
    public static final long SEED = 42L;
    /**
     * random
     */
    public static final Random random = new Random(SEED);

    private RandomUtils() {
        //hide constructor
    }

    /**
     * calculates a random number that is in a range from min to max
     *
     * @param min min
     * @param max max
     * @return random
     */
    public static double inRange(double min, double max) {
        return min + random.nextDouble() * (max - min);
    }
}
