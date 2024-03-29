/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.stream;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * <p>Class that contains different filter helper functions for {@link java.util.stream.Stream}s</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */
public final class FilterStreamUtils {
    private FilterStreamUtils() {
    }

    /**
     * <p>Method that helps to distinct a stream by multiple properties</p>
     * <pre>
     *     Stream&lt;Xyz&gt; s = ...;
     *     Stream&lt;Xyz&gt; sDistinct = s.filter(FilterStreamUtils.distinctByKeys(Xyz::getAbc, Xyz::getDef, ...));
     * </pre>
     *
     * @param keyExtractors array of key extractor functions
     * @param <T>           the type of the class of the stream
     * @return a predicate that is used inside filter methods of streams.
     * @see <a href="https://howtodoinjava.com/java8/stream-distinct-by-multiple-fields/">Java 8 stream distinct by multiple fields</a>
     */
    @SafeVarargs
    public static <T> Predicate<T> distinctByKeys(Function<? super T, ?>... keyExtractors) {
        // this map has to be outside of the lambda, because the lambda is called from the stream multiple
        // times, and with this workaround the map is always the same.
        final Map<List<?>, Boolean> seen = new ConcurrentHashMap<>();

        return t -> seen.putIfAbsent(Arrays.stream(keyExtractors)
                        .map(ke -> ke.apply(t))
                        .collect(Collectors.toList())
                , Boolean.TRUE) == null;
    }
}
