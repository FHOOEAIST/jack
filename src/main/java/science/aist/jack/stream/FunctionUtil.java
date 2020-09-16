package science.aist.jack.stream;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <p>Created by Andreas Pointner on 29.04.2019</p>
 * <p>Util class to provide different helper function for {@link java.util.function}</p>
 *
 * @author Andreas Pointner
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

    /**
     * <p>Some sort of hack to convert a method reference to a Function.</p>
     * <p>So instead of casting anything to use it as a function: </p>
     * <pre>
     *     Function&lt;In, Out&gt; func = (Function&lt;In, Out&gt;)obj::method
     * </pre>
     * you can use:
     * <pre>
     *     Function&lt;In, Out&gt; func = toFunction(obj::method);
     * </pre>
     * This is especially useful when using andThen:
     * <pre>
     *     Function&lt;In, Out2&gt; func = ((Function&lt;In, Out&gt;)obj::method).andThen(obj2:method2);
     * </pre>
     * simply use:
     * <pre>
     *     Function&lt;In, Out2&gt; func = toFunction(obj::method).andThen(obj2:method2);
     * </pre>
     *
     * @param func the function
     * @param <T>  the input type
     * @param <R>  the result type
     * @return the converted function
     */
    public static <T, R> Function<T, R> toFunction(Function<T, R> func) {
        return func;
    }

}
