package chapter_7.assessment;

import java.util.concurrent.Executors;
import java.util.stream.DoubleStream;

/**
 * It compiles, but the output cannot be determined ahead of time.
 * It compiles but waits forever at runtime because we are not using shutdown() method
 */
public class Question19 {

    public static void main(String[] args) {
        var s = Executors.newScheduledThreadPool(10);
        DoubleStream.of(3.14159,2.71828)
                .forEach(c -> s.submit(
                        () -> System.out.println(10*c)
                ));
        s.execute(() -> System.out.println("Printed"));
    }
}
