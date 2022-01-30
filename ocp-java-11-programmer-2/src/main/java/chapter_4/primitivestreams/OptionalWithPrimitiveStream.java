package chapter_4.primitivestreams;

import java.util.OptionalDouble;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

/**
 * We know primitive Streams, you can calculate the average in one line.
 */
public class OptionalWithPrimitiveStream {

    public static void main(String[] args) {

        example1();
        example2();
    }

    private static void example1() {
        System.out.println("Starting method example1.");
        var stream = IntStream.rangeClosed(1,10);
        OptionalDouble optional = stream.average();

        optional.ifPresent(System.out::println); // 5.5
        System.out.println(optional.getAsDouble()); // 5.5  We get getAsDouble() rather than get()
        System.out.println(optional.orElseGet(() -> Double.NaN)); //5.5 orElseGet() take DoubleSupplier instead of Supplier
    }

    private static void example2() {
        System.out.println("Starting method example2.");
        LongStream longs = LongStream.of(5,10);
        long sum = longs.sum();
        System.out.println(sum);

        //DoubleStream doubles = DoubleStream.generate(() -> Math.PI);
        //OptionalDouble min = doubles.min(); // runs infinitely


    }


}
