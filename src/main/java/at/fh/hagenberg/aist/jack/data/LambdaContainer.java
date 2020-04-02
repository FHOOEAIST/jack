package at.fh.hagenberg.aist.jack.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * <p>Helper class that contains a value so it can be modified inside of a lambda expression</p>
 *
 * @param <T> The type of the contained value
 * @author Oliver Krauss on 12.10.2015.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LambdaContainer<T> {
    /**
     * the stored value inside the container
     */
    private T val;
}
