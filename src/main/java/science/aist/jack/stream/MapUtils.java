/*
 * Copyright (c) 2020 the original author or authors.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

package science.aist.jack.stream;

import science.aist.jack.data.Pair;

import java.util.Map;
import java.util.stream.Stream;

/**
 * <p>Class that helps adds some comfort functions to {@link Map}s with the usage of {@link Stream}s</p>
 *
 * @author Andreas Pointner
 * @since 2.0
 */
public final class MapUtils {
    private MapUtils() {
    }

    /**
     * This function clones the map and add a prefix to all keys from the new map.
     * so if input is a map with one element e.g. test -&gt; myObj and prefix is aist_
     * the result would be a map with one element with aist_test -&gt; myObj
     *
     * @param input  the map
     * @param prefix the prefix to be added
     * @param <V>    type of the value of the map
     * @return the new map
     */
    public static <V> Map<String, V> prefixMapKey(Map<String, V> input, String prefix) {
        return input.entrySet().stream()
                .map(e -> Pair.of(prefix + e.getKey(), e.getValue()))
                .collect(Pair.toMap());
    }

    /**
     * Creates a Map out of pairs
     *
     * @param pairs the key value pairs
     * @param <K>   the type of the key
     * @param <V>   the type of the value
     * @return the resulting map
     */
    @SafeVarargs
    public static <K, V> Map<K, V> mapOfValues(Pair<K, V>... pairs) {
        return Stream.of(pairs).collect(Pair.toMap());
    }
}
