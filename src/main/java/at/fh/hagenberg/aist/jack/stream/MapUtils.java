package at.fh.hagenberg.aist.jack.stream;

import at.fh.hagenberg.aist.jack.data.Pair;

import java.util.Map;
import java.util.stream.Stream;

/**
 * <p>Created by Andreas Pointner on 12.02.2018</p>
 * <p>Class that helps adds some comfort functions to {@link Map}s with the usage of {@link Stream}s</p>
 *
 * @author Andreas Pointner
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
