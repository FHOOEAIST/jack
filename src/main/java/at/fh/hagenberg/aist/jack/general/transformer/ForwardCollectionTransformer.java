package at.fh.hagenberg.aist.jack.general.transformer;

import java.util.Collection;

/**
 * Generic interface for a unidirectional Transformer between collection of a class to collection of another
 *
 * @param <FROM> FROM which should be transformed to TO
 * @param <TO>   TO which is transformed from FROM
 * @author Christoph Praschl christoph.praschl@fh-hagenberg.at
 */
@FunctionalInterface
public interface ForwardCollectionTransformer<FROM, TO> {
    /**
     * Transforms a Collection of FROM to a Collection of TO
     *
     * @param collection the collection to be transformed
     * @return the resulting collection
     */
    Collection<TO> transformFromCollection(Collection<FROM> collection);
}
