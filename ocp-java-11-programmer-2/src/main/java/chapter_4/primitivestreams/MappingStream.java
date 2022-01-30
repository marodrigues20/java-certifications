package chapter_4.primitivestreams;


import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Another way to create a primitive stream is by mapping from another stream type.
 *
 * Source Stream class | To create Stream | To create DoubleStream | To create IntStream | To create LongStream
 * Stream<T>           | map()            | mapToDouble()          | mapToInt()          | mapToLong()
 * DoubleStream        | mapToObj()       | map()                  | mapToInt()          | mapToLong()
 * IntStream           | mapToObj()       | mapToDouble()          | map()               | mapToLong()
 * LongStream          | mapToObj()       | mapToDouble()          | mapToInt()          | map()
 */
public class MappingStream {

    public static void main(String[] args) {

        Stream<String> objStream = Stream.of("penguin", "fish");
        IntStream intStream = objStream.mapToInt(s -> s.length());
        intStream.forEach(System.out::println);
    }
}
