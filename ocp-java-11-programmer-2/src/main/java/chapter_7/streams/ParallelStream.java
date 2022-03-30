package chapter_7.streams;

import java.util.List;
import java.util.stream.Stream;

/**
 * Working with Parallel Streams
 *
 * One of the most powerful features of the Stream API is built-in concurrency support.
 * A parallel stream is a stream that is capable of processing results concurrently, using multiple threads.
 * Using a parallel stream can change not only the performance of your application but also the expected results.
 *
 * Tip: The number of threads available in a parallel stream is proportional to the number of available CPUs in your
 * environment
 *
 * Note: The Stream interface includes a method isParallel() that can be used to test if the instance of a stream
 * supports parallel processing. Some operations on streams preserve the parallel attribute, while others not.
 * For example, the Stream.concat(Stream s1, Stream s2) is parallel if either s1 or s2 is parallel. On the other hand,
 * flatMap() creates a new stream that is not parallel by default, regardless of whether the underlying elements were
 * parallel.
 *
 *
 */
public class ParallelStream {

    public static void main(String[] args) {
        callParallelStreamOnExistStream();
        callParallelStreamOnCollectionObject();
    }

    /**
     * The first way to create a parallel stream is from an existing stream. You just call parallel() on an existing
     * stream to convert it to one that supports multithreaded processing.
     * Be aware that parallel() is an intermediate operation that operates on the original stream. For example,
     * applying a terminal operation to s2 also makes s1 unavailable for further use.
     */
    private static void callParallelStreamOnExistStream() {
        Stream<Integer> s1 = List.of(1,2).stream();
        Stream<Integer> s2 = s1.parallel();
    }

    /**
     * The second way to create a parallel stream is from a Java Collection class.
     * The Collection interface includes a method parallelStream() that can be called on any collection and returns
     * a parallel stream.
     */
    private static void callParallelStreamOnCollectionObject() {
        Stream<Integer> s3 = List.of(1,2).parallelStream();

    }



}
