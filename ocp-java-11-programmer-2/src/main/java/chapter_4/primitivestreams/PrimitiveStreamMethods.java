package chapter_4.primitivestreams;


import java.util.stream.DoubleStream;
import java.util.stream.IntStream;

/**
 * Common Primitive Stream Methods:
 * OptionalDouble average()
 * Stream<t> boxed()
 * OptionalInt max()
 * OptionalInt min()
 * IntStream range(int a, int b)
 * IntStream rangeClosed(int a, int b)
 * IntSummaryStatistics summaryStatistics()
 *
 * The above methods have to: IntStream, LongStream and DoubleStream
 */
public class PrimitiveStreamMethods {

    public static void main(String[] args) {
        ofFactory();
        infiniteStreams();
        oneThroughFiveRangeMethod();
    }

    private static void oneThroughFiveRangeMethod() {
        System.out.println("Starting method oneThroughFiveRangeMethod");
        IntStream count = IntStream.iterate(1, n -> n + 1).limit(5);
        count.forEach(System.out::print);
        System.out.println("\n");
        IntStream range = IntStream.range(1,6);
        range.forEach(System.out::print);
        System.out.println("\n");
        IntStream rangeClosed = IntStream.rangeClosed(1,5);
        rangeClosed.forEach(System.out::print);
    }

    //Since the streams are infinite, we added a limit intermediate operation so that the output doesn't print
    //values forever.
    private static void infiniteStreams() {
        System.out.println("Starting method infiniteStreams");
        var random = DoubleStream.generate(Math::random);
        var fractions = DoubleStream.iterate(.5, d -> d / 2);

        random.limit(3).forEach(System.out::println);
        System.out.println("\n");
        fractions.limit(3).forEach(System.out::println);
        System.out.println("\n");
    }

    private static void ofFactory() {
        System.out.println("Starting method ofFactory");
        DoubleStream empty = DoubleStream.empty();

        DoubleStream oneValue = DoubleStream.of(3.14);
        oneValue.forEach(System.out::println);
        System.out.println("\n");
        DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
        varargs.forEach(System.out::println);
    }
}
