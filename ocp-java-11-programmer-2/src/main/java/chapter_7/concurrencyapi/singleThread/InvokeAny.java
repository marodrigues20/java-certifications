package chapter_7.concurrencyapi.singleThread;

import java.util.List;
import java.util.concurrent.*;

/**
 * On the other hand, the invokeAny() method executes a collection of tasks and returns the result of one of the tasks
 * that successfully completes execution, cancelling  all unfinished tasks. While the first task to finish is often
 * returned, this behavior is not guaranteed, as any completed task can be returned by this method.
 */
public class InvokeAny {

    public static void main(String[] args) throws InterruptedException, ExecutionException {

        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("begin");
            Callable<String> tasks = () -> "result";
            String data = service.invokeAny(
                    List.of(tasks, tasks, tasks));
            System.out.println(data);
            System.out.println("end");
        }finally {
            if(service != null) service.shutdown();
        }
    }
}
