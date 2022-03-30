package chapter_7.concurrencyapi.singleThread;

import java.util.concurrent.*;

/**
 * This example is similar to our earlier polling implementation, but it does not use the Thread class directly.
 * In parts, this is the essence of the Concurrency API: to do complex things with threads, without having to
 * manage threads directly. It also waits at most 10 seconds, throwing a TimeoutException on the call to result.get()
 * if the task is not done.
 *
 * As Future<V> is a generic interface, the type V is determined by the return type of the Runnable method. Since the
 * return type of Runnable.run() is void, the get() method always returns null when working with Runnable expressions.
 *
 * In your own code we recommend submit() over execute() whenever possible.
 */
public class CheckResults2 {

    private static int counter = 0;

    public static void main(String[] unused) throws Exception {

        ExecutorService service = null;

        try{
            service = Executors.newSingleThreadExecutor();
            Future<?> result = service.submit(() -> {
               for(int i = 0; i < 500; i++){
                   CheckResults2.counter++;
                   System.out.println("Lest see: " + CheckResults2.counter);
               }
            });

            result.get(10, TimeUnit.SECONDS);
            System.out.println("Reached!");
        }catch (TimeoutException e){
            System.out.println("Not reached in time");
        } finally{
            if(service != null){
                service.shutdown();
            }
        }

    }
}
