package at.fh.hagenberg.aist.jack.general.transformer;

import java.util.Collection;
import java.util.stream.Collectors;

/**
 * Generic interface for a bidirectional Transformer between a domain class and a TO class or collections of those classes
 *
 * @param <FROM> Item to be transferred to TO
 * @param <TO>   TO to be transferred to Item
 * @author Oliver Krauss on 19.02.2015.
 * @author Christoph Praschl on 30.10.2018.
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
