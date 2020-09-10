package at.fh.hagenberg.aist.jack.general.transformer;

/**
 * Generic interface for a reversed unidirectional Transformer between a class to another
 *
 * @param <FROM> TO which should be transformed to FROM
 * @param <TO>   FROM which is transformed from TO
 * @author Christoph Praschl
 */
@FunctionalInterface
@SuppressWarnings("java:S119") // Naming of type arguments
public interface BackwardTransformer<FROM, TO> {
    /**
     * Transform TO object to a FROM object
     *
     * @param to object to be transformed
     * @return transformed type
     */
    FROM transformTo(TO to);
}
