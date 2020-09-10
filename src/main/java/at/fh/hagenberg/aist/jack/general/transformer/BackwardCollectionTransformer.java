package at.fh.hagenberg.aist.jack.general.transformer;

import java.util.Collection;

/**
 * Generic interface for a reversed unidirectional Transformer between a collection of a class to collection of another
 *
 * @param <FROM> TO which should be transformed to FROM
 * @param <TO>   FROM which is transformed from TO
 * @author Christoph Praschl
 */
@FunctionalInterface
@SuppressWarnings("java:S119") // Naming of type arguments
public interface BackwardCollectionTransformer<FROM, TO> {
    /**
     * Transforms a Collection of TO to a Collection of FROM
     *
     * @param collection the collection to be transformed
     * @return the resulting collection
     */
    Collection<FROM> transformToCollection(Collection<TO> collection);
}
