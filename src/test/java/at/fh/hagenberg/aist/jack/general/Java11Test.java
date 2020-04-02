package at.fh.hagenberg.aist.jack.general;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Optional;

/**
 * <p>Created by Andreas Pointner on 02.04.2020</p>
 * <p>Tests if java 11 is working</p>
 *
 * @author Andreas Pointner andreas.pointner@fh-hagenberg.at
 */
public class Java11Test {
    @Test
    public void testJava11() {
        // given
        var optional = Optional.empty();

        // when
        boolean empty = optional.isEmpty();

        // then
        Assert.assertTrue(empty);
    }
}
