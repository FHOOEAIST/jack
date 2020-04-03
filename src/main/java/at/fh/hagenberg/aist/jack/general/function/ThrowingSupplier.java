package at.fh.hagenberg.aist.jack.general.function;

/**
 * <p>Created by Andreas Pointner on 14.02.2020</p>
 * <p>Implementation of {@link java.util.function.Supplier} to allow also exceptions</p>
 *
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
@FunctionalInterface
public interface ThrowingSupplier<T> {

    /**
     * Gets a result.
     *
     * @return a result
     * @throws Exception in case of any error
     */
    @SuppressWarnings("java:S112") // Exception is exactly what this should be declaring to allow to use it for all sorts of exceptions
    T get() throws Exception;
}
