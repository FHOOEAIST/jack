package at.fh.hagenberg.aist.jack.stream;

import at.fh.hagenberg.aist.jack.data.Pair;
import at.fh.hagenberg.aist.seshat.Logger;

import java.util.Arrays;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * <p>Created by Andreas Pointner on 12.02.2018</p>
 * <p>Class that helps adds some comfort functions to {@link Map}s with the usage of {@link Stream}s</p>
 *
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
public final class MapUtils {
    private static final Logger logger = Logger.getInstance(MapUtils.class);

    private MapUtils() {
    }

    /**
     * Returns all properties of a specific object. Therefore it extracts the fields via reflections and get its value.
     * This function only returns the values from the "main"-class. So if the class is e.g. String, then it returns only
     * fields from the class String and not from its superclass Object!
     * Then it create a Map with Key = FieldName, Value = FieldValue
     *
     * @param o      the where the value should be extracted
     * @param prefix the prefix which should be added to the map
     * @return a map with Key = FieldName, Value = FieldValue
     */
    public static Map<String, Object> elementPropertiesToMap(Object o, String prefix) {
        return Arrays.stream(o.getClass().getDeclaredFields())
                .filter(f -> !f.isSynthetic())
                .map(f -> {
                    boolean acc = f.isAccessible();
                    f.setAccessible(true);
                    try {
                        return Pair.of(prefix + f.getName(), f.get(o));
                    } catch (IllegalAccessException e) {
                        logger.debug(e.getMessage(), e);
                        return null;
                    } finally {
                        f.setAccessible(acc);
                    }
                })
                .filter(Objects::nonNull)
                .collect(Pair.toMap());
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
