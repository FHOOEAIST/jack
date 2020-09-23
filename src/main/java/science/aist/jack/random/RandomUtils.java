/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.random;

import java.util.Random;

/**
 * <p>Provides functions to generate random numbers</p>
 *
 * @author Lukas Reithmeier
 * @since 2.0
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
