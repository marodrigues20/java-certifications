package chapter_7.assessment;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Question5 {
    public static void main(String[] args) {
        var value1 = new AtomicInteger(0);
        final long[] value2 = {0};
        IntStream.iterate(1, i -> 1).limit(100).parallel()
                .forEach(i -> value1.getAndIncrement());
        IntStream.iterate(1, i -> 1).limit(100).parallel()
                .forEach(i -> ++value2[0]);
        System.out.println(value1 + " "+value2[0]);
    }
}
