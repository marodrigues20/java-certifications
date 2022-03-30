package chapter_7.assessment;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

/**
 * If the code does output anything, the order cannot be determined.
 * The code compiles but may produce a livestock at runtime.
 */
public class Question14 {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Object o1 = new Object();
        Object o2 = new Object();
        var service = Executors.newFixedThreadPool(2);

        var f1 = service.submit(() -> {
            synchronized (o1) {
                synchronized (o2) {
                    System.out.println("Tortoise");
                }
            }
        });

        var f2 = service.submit(() -> {
            synchronized (o2) {
                synchronized (o1) {
                    System.out.println("Hare");
                }
            }
        });

        f1.get();
        f1.get();
    }
}
