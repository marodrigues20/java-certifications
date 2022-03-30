package chapter_7.concurrencyapi.singleThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Creating Threads with the Concurrency API
 * Java includes the Concurrency API to handle the complicated work of managing threads for you. The Concurrency API
 * includes the ExecutorService interface, which defines services that create and manage for you.
 * The Concurrency API includes the Executors factory class that can be used to create instances of the ExecutorServices
 * object.
 * With a single-thread executor, results are guaranteed to be executed sequentially.
 * Once you have finished using a thread executor, it is important that you call the shutdown() method. A thread
 * executor creates a non-daemon thread on the first task is executed, so failing to call shutdown() will result in your
 * application never terminating.
 *
 * The shutdown process for a thread executor involves first rejecting any new tasks submitted to the thread executor
 * while continuing to execute any previously submitted tasks. During this time, calling isShutdown() will return true,
 * while isTerminated() will return false.
 *
 * If you want to cancel all running and upcoming tasks? The ExecutorService provides a method called shutdownNow(),
 * which attempts to stop all running tasks and discards any that have not been started yet.
 *
 * Note: ExecutorService interface does not extend the AutoCloseable interface. so you cannot use try-with-resources
 * statement.
 */
public class ZooInfo {

    public static void main(String[] args) {

        ExecutorService service = null;
        Runnable task1 = () ->
                System.out.println("Printing zoo inventory");

        Runnable task2 = () -> { for(int i = 0; i < 3; i++){
            System.out.println("Printing record: " + i);}
        };

        try{
            service = Executors.newSingleThreadExecutor();
            System.out.println("begin");
            service.execute(task1);
            service.execute(task2);
            service.execute(task1);
            System.out.println("end");
        }finally {
            if(service != null) service.shutdown();
        }

    }
}
