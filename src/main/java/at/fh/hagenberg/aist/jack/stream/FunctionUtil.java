package at.fh.hagenberg.aist.jack.stream;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <p>Created by Andreas Pointner on 29.04.2019</p>
 * <p>Util class to provide different helper function for {@link java.util.function}</p>
 *
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
public final class FunctionUtil {
    @SuppressWarnings("rawtypes")
    private static final Consumer EMPTY_CONSUMER = x -> {
    };
    @SuppressWarnings("rawtypes")
    private static final BiConsumer EMPTY_BI_CONSUMER = (x, y) -> {
    };
    @SuppressWarnings("rawtypes")
    private static final Function IDENTITY = x -> x;

    private FunctionUtil() {
    }

    /**
     * <p>Returns an empty consumer</p>
     * <p>This helps to avoid the creation of a huge amount of new consumer objects</p>
     *
     * @param <T> of any type
     * @return empty consumer, that does basically nothing.
     */
    @SuppressWarnings("unchecked")
    public static <T> Consumer<T> emptyConsumer() {
        return EMPTY_CONSUMER;
    }

    /**
     * <p>returns an empty bi consumer</p>
     * <p>This helps to avoid the creation of a huge amount of new consumer objects</p>
     *
     * @param <T> any type
     * @param <U> any type
     * @return empty bi-consumer, that does nothing
     */
    @SuppressWarnings("unchecked")
    public static <T, U> BiConsumer<T, U> emptyBiConsumer() {
        return EMPTY_BI_CONSUMER;
    }

    /**
     * <p>Does the same as {@link Function#identity()} but does not always create a new lambda object, instead
     * always returns the same one</p>
     *
     * @param <T> any type
     * @return a function that returns the input value as output
     */
    @SuppressWarnings("unchecked")
    public static <T> Function<T, T> identity() {
        return IDENTITY;
    }
}
