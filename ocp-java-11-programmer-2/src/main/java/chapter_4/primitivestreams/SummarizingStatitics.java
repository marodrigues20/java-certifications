package chapter_4.primitivestreams;

import java.util.IntSummaryStatistics;
import java.util.OptionalInt;
import java.util.stream.IntStream;


/**
 * Summary statistics include the following:
 * - Smallest number (minimum): getMin()
 * - Largest number (maximum): getMax()
 * - Sum: getSum()
 * - Number of values: getCount()
 */
public class SummarizingStatitics {

    public static void main(String[] args) {

        System.out.println(getMaxInt(IntStream.of(1,2,3,4,5)));
        System.out.println(range(IntStream.of(1,2,3,4,5)));
    }

    private static int getMaxInt(IntStream ints) {
        OptionalInt optionalInt = ints.max();
        return optionalInt.orElseThrow(RuntimeException::new);
    }

    private static int range(IntStream ints) {
        IntSummaryStatistics stats = ints.summaryStatistics();
        if( stats.getCount() == 0 ) throw new RuntimeException();
        return stats.getMax() - stats.getMin();
    }
}
