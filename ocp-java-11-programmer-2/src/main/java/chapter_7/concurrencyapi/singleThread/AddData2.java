package chapter_7.concurrencyapi.singleThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * In this example, we submit a number of tasks to the thread executor and then shut down the thread executor and
 * wait up to one minute for the results.
 *
 * If awaitTermination() is called before shutdown() within the same thread, then that thread will wait the full
 * timeout value sent with awaitTermination().
 */
public class AddData2 {

    public static void main(String[] args) throws Exception {

        ExecutorService service = null;

        try{
            service = Executors.newSingleThreadExecutor();
            // Add tasks to the thread executor
            // ...

        } finally {
            if(service != null) service.shutdown();
        }

        if(service != null){
            service.awaitTermination(1, TimeUnit.MINUTES);

            // Checked  whether all tasks are finished
            if(service.isTerminated()) System.out.println("Finished");
            else System.out.println("At least one task is still running");
        }

    }


}
