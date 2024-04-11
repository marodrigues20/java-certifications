package chapter_7.concurrencyapi.future;

import java.util.List;
import java.util.concurrent.*;


/**
 * Submitting Tasks Collections
 *
 * invokeAll() and invokeAny(). Both of these methods execute synchronously and take a Collection of tasks.
 *
 * The invokeAll() method executes all tasks in a provided collection and returns a List of ordered Future
 * instance, with one Future instance corresponding to each submitted task, in the order they were in the
 * original collection.
 *
 * In this example, the JVM waits on line 29 for all tasks to finish before moving on to line 31. Unlike our earlier
 * examples, this means that end will always be printed last. Also, even though future.isDone() returns true for each
 * element in the returned List, a task could have completed normally or thrown an exception.
 */
public class InvokeAll {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("begin");
            Callable<String> tasks = () -> "result";
            List<Future<String>> list = service.invokeAll(
                    List.of(tasks, tasks, tasks));
            for (Future<String> future : list) {
                System.out.println(future.get());
            }
            System.out.println("end");
        }finally {
            if(service != null) service.shutdown();
        }
    }



}
