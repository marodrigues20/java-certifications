package chapter_7.assessment;

import java.util.concurrent.CyclicBarrier;
import java.util.stream.IntStream;

/**
 * It compiles but waits forever at runtime.
 * The limit on the cyclic barrier is 10, but the stream can generate only up to 9 threads that reach
 * the barrier; therefore, the limit can never be reached.
 *
 */
public class Question23 {

    public static void main(String[] args) {
        var cb = new CyclicBarrier(10, () -> System.out.println("Stock Room Full!"));

        IntStream.iterate(1, i -> 1).limit(9).parallel()
                .forEach(i -> await(cb));
    }

    public static void await(CyclicBarrier cb){
        try{
            cb.await();
        }catch (Exception e){

        }
    }
}
