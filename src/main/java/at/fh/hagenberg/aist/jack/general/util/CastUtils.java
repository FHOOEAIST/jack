package at.fh.hagenberg.aist.jack.general.util;

import lombok.experimental.UtilityClass;

/**
 * <p>Created by Andreas Pointner on 08.05.2020</p>
 * <p>Helper class for casting</p>
 *
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
@UtilityClass
public class CastUtils {
    /**
     * <p>Cast any given source to any type</p>
     * Supports to cast elements:
     * <pre>
     *     Object o = "abc";
     *     String s = cast(o);
     * </pre>
     *
     * Supports to cast generic types:
     * <pre>
     *     List&lt;Object&gt; oList = ...
     *     List&lt;String&gt; sList = cast(oList);
     * </pre>
     *
     * @param source the element to be casted
     * @param <S>    the source type
     * @param <T>    the target type
     * @return the casted result
     * @throws ClassCastException if the cast fails
     */
    @SuppressWarnings("unchecked")
    public <S, T> T cast(S source) {
        return (T) source;
    }
}
