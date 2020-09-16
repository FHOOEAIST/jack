package science.aist.jack.general.function;

/**
 * <p>Implementation of {@link java.util.function.Function} to allow also exceptions</p>
 *
 * @param <R> the result of the function
 * @param <T> the input of the function
 * @author Andreas Pointner
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingFunction<T, R> {

    /**
     * Applies a function to t
     *
     * @param t the input of the function
     * @return the result of the function
     * @throws Exception the exception if one is thrown
     */
    @SuppressWarnings("java:S112")
    // Exception is exactly what this should be declaring to allow to use it for all sorts of exceptions
    R apply(T t) throws Exception;
}
