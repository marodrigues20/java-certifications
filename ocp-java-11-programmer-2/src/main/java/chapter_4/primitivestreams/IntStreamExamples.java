package chapter_4.primitivestreams;

import java.util.OptionalDouble;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Java actually includes other stream classes besides Stream that you can use to work with select primitives:
 * int, double, and long.
 * An IntStream has many of the same intermediate and terminal methods as a Stream but includes specialized methods
 * for working with numeric data. The primitive streams know how to perform certain common operations automatically.
 *
 * Three Types of Primitieve Streams
 * - IntStream: Used for the primitive types int, short, byte, and char
 * - LongStream: Used for the primitive type long
 * - DoubleStream: Used for the primitive types double and float.
 */
public class IntStreamExamples {

    public static void main(String[] args) {
        sumUsingReduceOperator();
        sumUsingIntStream();
        avarageIntStream();
    }

    private static void avarageIntStream() {
        IntStream intStream = IntStream.of(1,2,3);
        OptionalDouble avg = intStream.average();
        System.out.println(avg.getAsDouble()); //2.0
    }

    // We convert our Stream<Integer> to a primitive Stream
    private static void sumUsingIntStream() {
        Stream<Integer> stream = Stream.of(1,2,3);
        System.out.println(stream.mapToInt(x -> x).sum());
    }


    private static void sumUsingReduceOperator() {
        Stream<Integer> stream = Stream.of(1,2,3);
        System.out.println(stream.reduce(0, (s,n) -> s + n));
    }
}
