package at.fh.hagenberg.aist.jack.stream;

import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;

/**
 * <p>Created by Andreas Pointner on 03/09/2019</p>
 * <p>Test class for {@link FunctionUtil}</p>
 *
 * @author Andreas Pointner
 */
public class FunctionUtilTest {

    @Test
    public void testEmptyConsumer() {
        // given

        // when
        Consumer<Object> objectConsumer = FunctionUtil.emptyConsumer();

        // then
        Assert.assertNotNull(objectConsumer);
        objectConsumer.accept(null); // should not throw an exception
    }

    @Test
    public void testEmptyBiConsumer() {
        // given

        // when
        BiConsumer<Object, Object> objectObjectBiConsumer = FunctionUtil.emptyBiConsumer();

        // then
        Assert.assertNotNull(objectObjectBiConsumer);
        objectObjectBiConsumer.accept(null, null); // should not throw an exception
    }

    @Test
    public void testIdentity() {
        // given
        String x = "123";

        // when
        Function<String, String> identity = FunctionUtil.identity();

        // then
        Assert.assertNotNull(identity);
        Assert.assertEquals(x, identity.apply(x));
    }
}
