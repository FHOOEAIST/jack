package science.aist.jack.exception;

import org.testng.Assert;
import org.testng.annotations.Test;
import science.aist.jack.general.function.ThrowingFunction;
import science.aist.jack.general.function.ThrowingSupplier;

import java.io.IOException;
import java.util.function.Function;
import java.util.function.Supplier;

import static science.aist.jack.exception.ExceptionUtils.failure;
import static science.aist.jack.exception.ExceptionUtils.unchecked;

/**
 * <p>Test class for {@link ExceptionUtils}</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
public class ExceptionUtilsTest {

    @Test(expectedExceptions = IOException.class)
    public void testUnchecked() {
        // given
        // when
        throw unchecked(new IOException());
        // then
        // exception is thrown
    }


    @Test(expectedExceptions = IOException.class)
    public void testFailure() {
        // given
        // when
        testMethodWithReturn();
        // then
        Assert.fail();
    }

    @Test(expectedExceptions = IOException.class)
    public void testFailure2() {
        // given
        // when
        failure(new IOException());
        // then
        Assert.fail();
    }

    @Test
    public void testThrowingFunctionConversionNoFailure() {
        // given
        ThrowingFunction<Integer, Integer> correct = i -> i + 1;

        // when
        Function<Integer, Integer> uncheck = ExceptionUtils.uncheck(correct);

        // then
        Assert.assertEquals(Integer.valueOf(2), uncheck.apply(1));
    }

    @Test(expectedExceptions = Exception.class)
    public void testThrowingFunctionConversionFailure() {
        // given
        ThrowingFunction<Integer, Integer> incorrect = i -> {
            throw new Exception("");
        };

        // when
        Function<Integer, Integer> uncheck = ExceptionUtils.uncheck(incorrect);

        // then
        uncheck.apply(7);
    }

    @Test
    public void testThrowingSupplierConversionNoFailure() {
        // given
        ThrowingSupplier<Integer> correct = () -> 1;

        // when
        Supplier<Integer> uncheck = ExceptionUtils.uncheck(correct);

        // then
        Assert.assertEquals(Integer.valueOf(1), uncheck.get());
    }

    @Test(expectedExceptions = Exception.class)
    public void testThrowingSupplierConversionFailure() {
        // given
        ThrowingSupplier<Integer> correct = () -> {
            throw new Exception("failure");
        };

        // when
        Supplier<Integer> uncheck = ExceptionUtils.uncheck(correct);

        // then
        uncheck.get();
    }


    /**
     * Just some random return type, because a return is required for testing the method and test
     * methods with return values are not supported.
     */
    @SuppressWarnings("UnusedReturnValue")
    private String testMethodWithReturn() {
        return failure(new IOException());
    }
}