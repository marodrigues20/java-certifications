package chapter_4.streams;

import java.util.List;
import java.util.stream.Stream;

/**
 * A stream in Java is a sequence of data. A stream pipeline consist of the operations that run on a stream
 * to produce a result.
 * There are three parts to a stream pipeline, as shown in:
 *
 * Source: Where the stream comes from
 * Intermediate operations: Transforms the stream into another one. There can be as few or as many intermediate
 * operations as you'd like. Since streams use lazy evaluation, the intermediate operations do not run until the
 * terminal operation run.
 * Terminal operation: Actually produces a result. Since streams can be used only once, the stream is no longer
 * valid after a terminal operation completes. (Reductions: Are a special type of Terminal Operation)
 *
 */
public class CreateStreamExample {

    public static void main(String[] args) {

        createStreamSource();
        createStreamFromCollection();
        createParallelStream();
        createInfiniteStream();
        createStreamOddNumbers();
    }

    private static void createStreamOddNumbers() {
        // Create Stream by using the seed for the first element and then calling the UnaryOperator for each subsequent
        // element upon request. Stops if the Predicate returns false.
        Stream<Integer> oddNumberUnder100 = Stream.iterate(1, n -> n < 100, n -> n + 2);
    }

    private static void createInfiniteStream() {
        // Creates Stream by calling the Supplier for each element upon request
        Stream<Double> randoms = Stream.generate(Math::random);
        // Create Stream by using the seed for the first element and then calling the UnaryOperator for each subsequent
        // element upon request.
        Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
    }

    private static void createParallelStream() {
        var list = List.of("a","b","c");
        // Creates Streams from a Collection where the stream can run in parallel
        Stream<String> fromListParallel = list.parallelStream();
    }

    private static void createStreamFromCollection() {
        var list = List.of("a", "b", "c");
        // Create Stream from a Collection
        Stream<String> fromList = list.stream();
    }

    private static void createStreamSource() {

        Stream<String> empty = Stream.empty();
        // count = 0
        Stream<Integer> singleElement = Stream.of(1); // Create Stream with elements listed
        // count = 1
        Stream<Integer> fromArray = Stream.of(1, 2, 3);
        // count = 3
    }
}
