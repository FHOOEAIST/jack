package at.fh.hagenberg.aist.jack.collection;

import java.util.Collection;
import java.util.Map;

/**
 * <p>Created by Christoph Praschl on 14/04/2020</p>
 * <p>Util class for {@link java.util.Collection} and {@link java.lang.Iterable}</p>
 *
 * @author Christoph Praschl
 */
public class CollectionUtils {
    private CollectionUtils() {
    }

    /**
     * @param collection to check
     * @return true iff collection is null or empty else false
     */
    public static boolean isNullOrEmpty(Collection<?> collection){
        return collection == null || collection.isEmpty();
    }

    /**
     * @param map to check
     * @return true iff collection is null or empty else false
     */
    public static boolean isNullOrEmpty(Map<?,?> map){
        return map == null || map.isEmpty();
    }

}
