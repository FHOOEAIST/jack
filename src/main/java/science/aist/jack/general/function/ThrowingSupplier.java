package science.aist.jack.general.function;

/**
 * <p>Implementation of {@link java.util.function.Supplier} to allow also exceptions</p>
 *
 * @param <T> the supplied result type
 * @author Andreas Pointner
 * @since 1.0
 */
@FunctionalInterface
public interface ThrowingSupplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     * @throws Exception in case of any error
     */
    @SuppressWarnings("java:S112")
    // Exception is exactly what this should be declaring to allow to use it for all sorts of exceptions
    T get() throws Exception;
}
