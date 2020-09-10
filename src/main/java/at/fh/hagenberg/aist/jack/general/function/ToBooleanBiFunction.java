package at.fh.hagenberg.aist.jack.general.function;

import java.util.function.BiFunction;

/**
 * <p>Created by Andreas Pointner on 12.03.2019</p>
 * <p>Represents a function that accepts two arguments and produces an boolean-valued
 * result.  This is the {@code boolean}-producing primitive specialization for
 * {@link BiFunction}.</p>
 *
 * <p>This is a <a href="package-summary.html">functional interface</a>
 * whose functional method is {@link #applyAsBoolean(Object, Object)}.</p>
 *
 * @param <T> the type of the first argument to the function
 * @param <U> the type of the second argument to the function
 * @author Andreas Pointner
 * @see BiFunction
 */
@FunctionalInterface
public interface ToBooleanBiFunction<T, U> {
    /**
     * Applies this function to the given arguments.
     *
     * @param t the first function argument
     * @param u the second function argument
     * @return the function result
     */
    boolean applyAsBoolean(T t, U u);
}
