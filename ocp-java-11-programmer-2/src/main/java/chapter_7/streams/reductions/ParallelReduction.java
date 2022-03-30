package chapter_7.streams.reductions;

import java.util.List;

/**
 * Processing Parallel Reductions
 *
 * Reduction operations on parallel stream are referred to as parallel reductions. The results for parallel reductions
 * can be different from what you expect when working with serial streams.
 *
 * Note: The results of ordered operations on a parallel stream will be consistent with a serial stream. For example,
 * calling skip(5).limit(2).findFirst() will return the same result on ordered serial and parallel streams.
 *
 */
public class ParallelReduction {

    public static void main(String[] args) {
        serialStream();
        parallelStream();
    }

    /**
     * Since order is not guaranteed with parallel streams, methods such as findAny() on parallel streams may result
     * in unexpected behavior
     * This code frequently outputs the first value in the serial stream, 1, although this is not guaranteed. The
     * findAny() method is free to select any element on either serial or parallel streams.
     */
    private static void serialStream() {
        System.out.println(List.of(1,2,3,4,5,6)
                .stream()
                .findAny().get());
    }

    /**
     * With a parallel stream, the JVM can create any number of threads to process the stream. When you call findAny()
     * on a parallel stream, the JVM selects the first thread to finish the task and retrieves its data.
     * The result is that the output could be 4,1, or really any value in the stream.
     * Any stream operation that is based on order, including  findFirst(), limit(), or skip(), may actually perform
     * more slowly in a parallel environment.
     */
    private static void parallelStream() {
        System.out.println(List.of(1,2,3,4,5,6)
                .parallelStream()
                .findAny().get());
    }
}
