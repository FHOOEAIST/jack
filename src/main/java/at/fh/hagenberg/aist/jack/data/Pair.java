package at.fh.hagenberg.aist.jack.data;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>Created by Andreas Pointner on 27/08/2019</p>
 * <p>Pair class to store two value</p>
 *
 * @param <S> type of the first value
 * @param <T> type of the second value
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
@Getter
@EqualsAndHashCode
@ToString
@AllArgsConstructor(staticName = "of")
public class Pair<S, T> {
    private S first;
    private T second;

    /**
     * A collector to create a {@link Map} from a {@link Stream} of {@link Pair}s.
     *
     * @param <S> type of the first element
     * @param <T> type of the second element
     * @return pair collector
     */
    public static <S, T> Collector<Pair<S, T>, ?, Map<S, T>> toMap() {
        return Collectors.toMap(Pair::getFirst, Pair::getSecond);
    }
}
