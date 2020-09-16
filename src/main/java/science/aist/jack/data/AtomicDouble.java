package science.aist.jack.data;

import java.util.concurrent.atomic.AtomicLong;

import static java.lang.Double.doubleToLongBits;
import static java.lang.Double.longBitsToDouble;

/**
 * <p>Implementation of AtomicFloat based on {@link AtomicLong}</p>
 *
 * @author Andreas Pointner
 * @since 1.0
 */
public class AtomicDouble extends Number {
    /**
     * The bits of the float object
     */
    private final AtomicLong bits;

    /**
     * Default constructor which initializes the object with 0d
     */
    public AtomicDouble() {
        this(0d);
    }

    /**
     * Constructor which initialized the object with initialValue
     *
     * @param initialValue the initial value for the object
     */
    public AtomicDouble(double initialValue) {
        bits = new AtomicLong(doubleToLongBits(initialValue));
    }

    /**
     * Atomically sets the value to the given updated value
     * if the current value {@code ==} the expected value.
     *
     * @param expect the expected value
     * @param update the new value
     * @return {@code true} if successful. False return indicates that
     * the actual value was not equal to the expected value.
     * @see AtomicLong#compareAndSet(long, long)
     */
    public final boolean compareAndSet(float expect, float update) {
        return bits.compareAndSet(doubleToLongBits(expect),
                doubleToLongBits(update));
    }

    /**
     * Sets to the given value.
     *
     * @param newValue the new value
     * @see AtomicLong#set(long)
     */
    public final void set(double newValue) {
        bits.set(doubleToLongBits(newValue));
    }

    /**
     * Gets the current value.
     *
     * @return the current value
     * @see AtomicLong#get()
     */
    public final double get() {
        return longBitsToDouble(bits.get());
    }


    /**
     * Atomically sets to the given value and returns the old value.
     *
     * @param newValue the new value
     * @return the previous value
     * @see AtomicLong#getAndSet(long)
     */
    public final double getAndSet(double newValue) {
        return longBitsToDouble(bits.getAndSet(doubleToLongBits(newValue)));
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
     * @see AtomicLong#weakCompareAndSetPlain(long, long)
     */
    public final boolean weakCompareAndSetPlain(double expect, double update) {
        return bits.weakCompareAndSetPlain(doubleToLongBits(expect),
                doubleToLongBits(update));
    }

    /**
     * returns the value as float
     *
     * @return value as float
     */
    @Override
    public float floatValue() {
        return (float) get();
    }

    /**
     * returns the value as double
     *
     * @return value as double
     */
    @Override
    public double doubleValue() {
        return get();
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
