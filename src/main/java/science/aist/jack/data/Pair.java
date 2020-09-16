package science.aist.jack.data;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Pair class to store two value</p>
 *
 * @param <S> type of the first value
 * @param <T> type of the second value
 * @author Andreas Pointner
 * @since 1.0
 */
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor(staticName = "of")
public class Pair<S, T> {
    private final S first;
    private final T second;

    /**
     * A collector to create a {@link Map} from a {@link Stream} of {@link Pair}s.
     *
     * @param <S> type of the first element
     * @param <T> type of the second element
     * @return pair collector
     */
    @SuppressWarnings("java:S1452") // Because Collectors.toMap already returns it in that way...
    public static <S, T> Collector<Pair<S, T>, ?, Map<S, T>> toMap() {
        return Collectors.toMap(Pair::getFirst, Pair::getSecond);
    }
}
