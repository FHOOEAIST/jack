package science.aist.jack.data;

import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.Float.floatToIntBits;
import static java.lang.Float.intBitsToFloat;

/**
 * <p>Implementation of AtomicFloat based on {@link AtomicInteger}</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
public class AtomicFloat extends Number {
    /**
     * The bits of the float object
     */
    private final AtomicInteger bits;

    /**
     * Default constructor which initializes the object with 0f
     */
    public AtomicFloat() {
        this(0f);
    }

    /**
     * Constructor which initialized the object with initialValue
     *
     * @param initialValue the initial value for the object
     */
    public AtomicFloat(float initialValue) {
        bits = new AtomicInteger(floatToIntBits(initialValue));
    }

    /**
     * Atomically sets the value to the given updated value
     * if the current value {@code ==} the expected value.
     *
     * @param expect the expected value
     * @param update the new value
     * @return {@code true} if successful. False return indicates that
     * the actual value was not equal to the expected value.
     * @see AtomicInteger#compareAndSet(int, int)
     */
    public final boolean compareAndSet(float expect, float update) {
        return bits.compareAndSet(floatToIntBits(expect),
                floatToIntBits(update));
    }

    /**
     * Sets to the given value.
     *
     * @param newValue the new value
     * @see AtomicInteger#set(int)
     */
    public final void set(float newValue) {
        bits.set(floatToIntBits(newValue));
    }

    /**
     * Gets the current value.
     *
     * @return the current value
     * @see AtomicInteger#get()
     */
    public final float get() {
        return intBitsToFloat(bits.get());
    }


    /**
     * Atomically sets to the given value and returns the old value.
     *
     * @param newValue the new value
     * @return the previous value
     * @see AtomicInteger#getAndSet(int)
     */
    public final float getAndSet(float newValue) {
        return intBitsToFloat(bits.getAndSet(floatToIntBits(newValue)));
    }

    /**
     * Atomically sets the value to the given updated value
     * if the current value {@code ==} the expected value.
     *
     * <p><a href="package-summary.html#weakCompareAndSet">May fail
     * spuriously and does not provide ordering guarantees</a>, so is
     * only rarely an appropriate alternative to {@code compareAndSet}.
     *
     * @param expect the expected value
     * @param update the new value
     * @return {@code true} if successful
     * @see AtomicInteger#weakCompareAndSetPlain(int, int)
     */
    public final boolean weakCompareAndSetPlain(float expect, float update) {
        return bits.weakCompareAndSetPlain(floatToIntBits(expect),
                floatToIntBits(update));
    }

    /**
     * returns the value as float
     *
     * @return value as float
     */
    @Override
    public float floatValue() {
        return get();
    }

    /**
     * returns the value as double
     *
     * @return value as double
     */
    @Override
    public double doubleValue() {
        return floatValue();
    }

    /**
     * returns the value as int
     *
     * @return value as int
     */
    @Override
    public int intValue() {
        return (int) get();
    }

    /**
     * returns the value as long
     *
     * @return value as long
     */
    @Override
    public long longValue() {
        return (long) get();
    }

}
