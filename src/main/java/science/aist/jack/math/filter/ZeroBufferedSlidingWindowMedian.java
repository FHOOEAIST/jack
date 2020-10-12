package science.aist.jack.math.filter;

import science.aist.seshat.Logger;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <h2>ZeroBufferedSlidingWindowMedian</h2>
 * <p>
 * Extension of the {@link SlidingWindowMedian}, that only allows uneven kernels. This ensures that, by 0 buffering
 * the data, the resulting filtered list is the same length as the input list. Useful for stuff like elementwise
 * subtraction
 * </p>
 *
 * @author Rainer Meindl, rainer.meindl@fh-hagenberg.at, 22.09.2020
 * @see SlidingWindowMedian
 */
public class ZeroBufferedSlidingWindowMedian extends SlidingWindowMedian<Double> {

    private static final Logger logger = Logger.getInstance();

    @Override
    public List<Double> filter(List<Double> data, int kernel) {
        if (kernel % 2 == 0) {
            logger.warn("Kernel needs to be uneven.");
            return List.of();
        }
        var necessaryZeros = kernel / 2;
        var bufferedData = new ArrayList<>(data);
        bufferedData.addAll(Collections.nCopies(necessaryZeros, 0D));
        bufferedData.addAll(0, Collections.nCopies(necessaryZeros, 0D));
        return super.filter(bufferedData, kernel);
    }
}
