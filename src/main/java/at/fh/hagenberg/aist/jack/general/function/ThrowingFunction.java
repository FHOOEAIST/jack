package at.fh.hagenberg.aist.jack.general.function;

/**
 * <p>Created by Andreas Pointner on 05.02.2020</p>
 * <p>Implementation of {@link java.util.function.Function} to allow also exceptions</p>
 *
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
public interface ThrowingFunction<T, R> {

    /**
     * Applies a function to t
     *
     * @param t the input of the function
     * @return the result of the function
     * @throws Exception the exception if one is thrown
     */
    @SuppressWarnings("java:S112") // Exception is exactly what this should be declaring to allow to use it for all sorts of exceptions
    R apply(T t) throws Exception;
}
