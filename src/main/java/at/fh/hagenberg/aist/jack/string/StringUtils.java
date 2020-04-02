package at.fh.hagenberg.aist.jack.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

/**
 * <p>Created by Andreas Pointner on 12/09/2019</p>
 * <p>Different Util methods for {@link String}</p>
 *
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
// Weaker access cannot be provided, as this is a library and the functions are used outside this library too
@SuppressWarnings("WeakerAccess")
public final class StringUtils {
    private StringUtils() {
    }

    /**
     * Constant to define when an index is not found
     */
    public static final int INDEX_NOT_FOUND = -1;

    public static final String REPLACEMENT_PLACEHOLDER = "{}";

    /**
     * Calls StringUtils{@link #format(String, Function, Object[])} with function {@link Object#toString()}
     *
     * @param s            the string where the {@link StringUtils#REPLACEMENT_PLACEHOLDER} should be replaced
     * @param replacements the replacement elements
     * @param <T>          the type of the replacements
     * @return the string with the replace elements
     * @see StringUtils#format(String, Function, Object[])
     */
    @SafeVarargs
    public static <T> String format(final String s, final T... replacements) {
        return format(s, Object::toString, replacements);
    }

    /**
     * Replaces {@link StringUtils#REPLACEMENT_PLACEHOLDER} with replacements
     *
     * @param s            the string where the {@link StringUtils#REPLACEMENT_PLACEHOLDER} should be replaced
     * @param extractor    the extractor function, that converts the replacement object into a string
     * @param replacements the replacement elements
     * @param <T>          the type of the replacements
     * @return the string with the replace elements
     * @throws IllegalArgumentException if the number of placeholders does not match the number of replacements
     */
    @SafeVarargs
    public static <T> String format(final String s, final Function<T, String> extractor, final T... replacements) {
        if (StringUtils.countMatches(s, "{}") != replacements.length) {
            throw new IllegalArgumentException("Number of replacements do not match number of placeholders");
        }
        return Arrays.stream(replacements)
                .map(extractor)
                .reduce(s, (a, b) -> a.replaceFirst("\\" + REPLACEMENT_PLACEHOLDER, b));
    }

    /**
     * Counts how often sub is included in str
     *
     * @param str the string to be check
     * @param sub the sub string to be search in str
     * @return the number of times sub occurs in str
     */
    public static int countMatches(final String str, final String sub) {
        if (str == null || str.isEmpty() || sub == null || sub.isEmpty()) {
            return 0;
        }
        int count = 0;
        int idx = 0;
        while ((idx = str.indexOf(sub, idx)) != INDEX_NOT_FOUND) {
            count++;
            idx += sub.length();
        }
        return count;
    }

    /**
     * Returns the indices of every not overlapping occurrence of the substring in the given string
     * @param string which contains the substring
     * @param substring which is contained in the string
     * @return a list of all indices of every substring occurence. List is empty if substring is not contained in string
     */
    public static List<Integer> indicesOf(String string, String substring){
        return indicesOf(string, substring, true);
    }

    /**
     * Returns the indices of every occurrence of the substring in the given string
     * @param string which contains the substring
     * @param substring which is contained in the string
     * @param dontOverlap flag which decides if the occurence of a substring can be overlap with another occurce (e.g. bb in bbb could result in the indices 0 and 1 or just in 0 if overlapping is not allowed)
     * @return a list of all indices of every substring occurence. List is empty if substring is not contained in string
     */
    public static List<Integer> indicesOf(String string, String substring, boolean dontOverlap){
        List<Integer> occurrences = new ArrayList<>();

        int offset = dontOverlap ? 1 + substring.length() : 1;
        int index = string.indexOf(substring);
        while (index >= 0) {
            occurrences.add(index);
            index = string.indexOf(substring, index + offset);
        }
        return occurrences;
    }
}
