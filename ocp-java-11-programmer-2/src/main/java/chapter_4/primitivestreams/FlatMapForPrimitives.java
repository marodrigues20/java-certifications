package chapter_4.primitivestreams;

import java.util.ArrayList;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * The flatMap() method exists on primitives streams as well. It works the same way as on a regular Stream except
 * the method name is different.
 */
public class FlatMapForPrimitives {

    public static void main(String[] args) {
        flatMapExample();
    }

    private static void flatMapExample() {
        var integerList = new ArrayList<Integer>();

        IntStream ints = integerList.stream()
                .flatMapToInt(x -> IntStream.of(x));

        DoubleStream doubles = integerList.stream()
                .flatMapToDouble(x -> DoubleStream.of(x));

        LongStream longs = integerList.stream()
                .flatMapToLong(x -> LongStream.of(x));
    }

    private static Stream<Integer> mapping(IntStream stream){
        return stream.mapToObj(x -> x);
    }

    private static Stream<Integer> boxing(IntStream stream){
        return stream.boxed();
    }
}
