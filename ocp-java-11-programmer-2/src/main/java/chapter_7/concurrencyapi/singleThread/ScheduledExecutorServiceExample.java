package chapter_7.concurrencyapi.singleThread;

import java.util.concurrent.*;

/**
 * Like ExecutorService, we obtain an instance of ScheduledExecutorService using a factory method in the Executors class,
 * as shown bellow.
 * The ScheduleFuture interface is identical to the Future interface, except that it includes a getDelay() method that
 * returns the remaining delay.
 *
 * Note: While these tasks are scheduled in the future, the actual execution may be delayed. For example, there may be
 * no threads available to perform the task, at which point they will just wait in the queue. Also, if the
 * ScheduledExecutorService is shut down by the time the scheduled tasks execution time is reached, then these tasks
 * will be discarded.
 *
 *
 * The scheduleAtFixedRate() method creates a new task and submits it to the executor every period, regardless of
 * whether the previous task finished. The following example executes a Runnable task every minute, following an
 * initial five-minute delay.
 *
 * Note: Bad thing can happen with scheduleAtFixedRate() if each task consistently takes longer to run than the
 * execution interval. Image your boss came by your desk every minute and dropped off a piece of paper. Now imagine
 * it took you five minutes to read each piece of paper. Before long, you would be drowning in piles of paper. This
 * is how an executor feels. Given enough time, the program would submit more tasks to the executor service than
 * could fit in memory, causing the program to crash.
 *
 * The scheduleWithFixedDelay() method creates a new task only after the previous task has finished. For example, if a
 * task at 12:00 and takes five minutes to finish, with a period between executions of two minutes, then the next task
 * will start at 12:07.
 *
 * Note: The scheduleWithFixedRate() is the closest built-in Java to Linux Cron jobs.
 */
public class ScheduledExecutorServiceExample {

    public static void main(String[] args) {
        ScheduledExecutorService service = null;
        try {
            service = Executors.newSingleThreadScheduledExecutor();
            Runnable task1 = () -> System.out.println("Hello Zoo");
            Callable<String> task2 = () -> "Monkey";
            ScheduledFuture<?> r1 = service.schedule(task1, 10, TimeUnit.SECONDS);
            ScheduledFuture<?> r2 = service.schedule(task2, 8, TimeUnit.MINUTES);
            //service.scheduleAtFixedRate(task1, 5, 1, TimeUnit.MINUTES); // Initial delay 5. Every 1 minute.
            //service.scheduleWithFixedDelay(task1, 0, 2, TimeUnit.MINUTES); // Initial delay 0. Every 2 minutes
        }finally {
            if(service != null) service.shutdown();
        }





    }
}
