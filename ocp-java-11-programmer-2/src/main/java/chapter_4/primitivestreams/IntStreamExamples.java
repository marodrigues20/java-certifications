package chapter_4.primitivestreams;

import java.util.stream.Stream;

/**
 * Java actually includes other stream classes besides Stream that you can use to work with select primitives:
 * int, double, and long.
 * An IntStream has many of the same intermediate and terminal methods as a Stream but includes specialized methods
 * for working with numeric data. The primitive streams know how to perform certain common operations automatically.
 */
public class IntStreamExamples {

    public static void main(String[] args) {

        sumUsingReduceOperator();
        sumUsingIntStream();

    }

    // Using primitive Stream
    private static void sumUsingIntStream() {
        Stream<Integer> stream = Stream.of(1,2,3);
        System.out.println(stream.mapToInt(x -> x).sum());
    }


    private static void sumUsingReduceOperator() {
        Stream<Integer> stream = Stream.of(1,2,3);
        System.out.println(stream.reduce(0, (s,n) -> s + n));
    }
}
