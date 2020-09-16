package science.aist.jack.general.transformer;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * <p>Generic interface for a bidirectional Transformer between a domain class and a TO class or collections of those classes</p>
 *
 * @param <FROM> Item to be transferred to TO
 * @param <TO>   TO to be transferred to Item
 * @author Oliver Krauss
 * @author Christoph Praschl
 * @since 1.0
 */
@SuppressWarnings("java:S119") // Naming of type arguments
public interface Transformer<FROM, TO> extends ForwardTransformer<FROM, TO>, BackwardTransformer<FROM, TO>,
        ForwardCollectionTransformer<FROM, TO>, BackwardCollectionTransformer<FROM, TO> {
    @Override
    default Collection<FROM> transformToCollection(Collection<TO> c) {
        return c.stream().map(this::transformTo).collect(Collectors.toList());
    }

    @Override
    default Collection<TO> transformFromCollection(Collection<FROM> f) {
        return f.stream().map(this::transformFrom).collect(Collectors.toList());
    }
}
