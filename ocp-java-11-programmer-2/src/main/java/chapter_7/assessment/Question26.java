package chapter_7.assessment;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


/**
 * It outputs a null value 10 times
 * It outputs Exception! 10 times
 *
 * The return type of performCount() is void, so submit() is interpreted as being applied to a Runnable expression.
 * While submit(Runnable) does return a Future<?>, calling get() on it always returns null.
 * The performCount() method can also throw a runtime exception, which will then be thrown by the get() call as an
 * ExecutionException
 *
 */
public class Question26 {

    public static void performCount(int animal) {
        // IMPLEMENTATION OMITTED
    }

    public static void printResults(Future<?> f) {
        try {
            System.out.println(f.get(1, TimeUnit.DAYS)); // get include timeout value
        } catch (Exception e) {

        }
        System.out.println("Exception");
    }

    public static void main(String[] args) {
        ExecutorService s = null;
        final var r = new ArrayList<Future<?>>();
        try{
            s = Executors.newSingleThreadExecutor();
            for(int i = 0; i < 10; i++){
                final int animal = i;
                r.add(s.submit(() -> performCount(animal)));
            }
            r.forEach(f -> printResults(f));
        }finally {
            if(s != null) s.shutdown();
        }

    }
}
