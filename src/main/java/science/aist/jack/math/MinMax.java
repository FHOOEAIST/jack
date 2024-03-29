/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.math;

import lombok.Getter;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;

/**
 * <p>Optional-like Wrapper class for wrapping two values: a min and a max value</p>
 *
 * @author Christoph Praschl
 * @since 2.0
 */
@Getter
public class MinMax<T> {
    private final boolean present;
    private T min;
    private T max;

    private MinMax(T min, T max) {
        this.min = min;
        this.max = max;
        this.present = true;
    }

    private MinMax() {
        this.present = false;
    }

    public static <I> MinMax<I> empty() {
        return new MinMax<>();
    }

    public static <I> MinMax<I> of(I min, I max) {
        return new MinMax<>(min, max);
    }

    /**
     * Method which executes the given consumer if this is present
     *
     * @param consumer function which consumes the min (first value) and the max (second value) if present
     */
    public void ifPresent(BiConsumer<T, T> consumer) {
        if (isPresent()) {
            consumer.accept(min, max);
        }
    }

    /**
     * Maps the given min and max value if present using the mapper
     *
     * @param mapper used to map min and max if present
     * @param <I>    result type of the mapping
     * @return Optional containing the mapping result iff present else empty
     */
    public <I> Optional<I> map(BiFunction<T, T, I> mapper) {
        if (isPresent()) {
            return Optional.of(mapper.apply(min, max));
        } else {
            return Optional.empty();
        }
    }

}
