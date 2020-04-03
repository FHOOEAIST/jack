package at.fh.hagenberg.aist.jack.general.transformer;

/**
 * Generic interface for a unidirectional Transformer between a class to another
 *
 * @param <FROM> FROM which should be transformed to TO
 * @param <TO>   TO which is transformed from FROM
 * @author Christoph Praschl christoph.praschl@fh-hagenberg.at
 */
@FunctionalInterface
@SuppressWarnings("java:S119") // Naming of type arguments
public interface ForwardTransformer<FROM, TO> {
    /**
     * Transform FROM object to a TO object
     *
     * @param from object to be transformed
     * @return transformed type
     */
    TO transformFrom(FROM from);
}
