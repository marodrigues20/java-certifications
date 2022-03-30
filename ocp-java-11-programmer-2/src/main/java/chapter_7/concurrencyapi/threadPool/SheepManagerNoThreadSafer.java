package chapter_7.concurrencyapi.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Increasing Concurrency with Pools
 *
 * A thread pool is a group of pre-instantiated reusable threads that are available to perform a set of arbitrary
 * tasks.
 *
 * The difference between a single-thread and a pooled-thread executor is what happens when a task is already running.
 * While a single-thread executor will wait for a thread to become available before running the next task, a
 * pooled-thread executor can execute the next task concurrently. If the pool runs out of available threads, the task
 * will be queued by the thread executor and wait to be completed.
 *
 * The newFixedThreadPool() takes a number of threads and allocates them all upon creation. As long as our number of
 * tasks is less than our number of threads, all tasks will be executed concurrently. If at any point the number of
 * tasks exceeds the number of threads in the pool, they will wait in a similar manner as you saw with a single-thread
 * executor. In fact, calling newFixedThreadPool() with a value of 1 is equivalent to calling newSingleThreadExecutor().
 *
 * The newCachedThreadPool() method will create a thread pool of unbounded size, allocating a new thread anytime one is
 * required or all existing threads are busy. This is commonly used for pools that require executing many short-lived
 * asynchronous tasks. For long-lived process, usage of this executor is strongly discouraged, as it could grow to
 * encompass a large of threads over the application life cycle.
 *
 * The newScheduledThreadPool() is identical to the newFixedThreadPool() method, except that it returns an instance of
 * ScheduledExecutorService and is therefore compatible with scheduling tasks.
 *
 * Oftentimes, the number of CPUs available is used to determine the thread pool size using this command:
 * Runtime.getRuntime().availableProcessors()
 * It is a common practice to allocate threads based on the number of CPUs.
 *
 * Thread-safety is the property of one object that guarantees safe execution by multiple threads at the same time.
 * There are a variety of techniques to protect data including: atomic classes, synchronized blocks, the Lock framework,
 * and cyclic barriers.
 *
 *
 * Example bellow result:
 * You might think it will output numbers from 1 to 10, in order. Worse yet, it may print some numbers twice and not
 * print some numbers at all!
 * In this example, we use the pre-increment (++) operator to update the sheepCount variable. A problem occurs when two
 * threads both execute the right side of the expression, reading the "old" value before either threads writes the "new"
 * value of the variable. The two assignments become redundant; they both assign the same new value, with one thread
 * overwriting the results of the other, assuming that sheepCount has a starting value of 1.
 * Therefore, the increment operator ++ is not thread-safe.
 */
public class SheepManagerNoThreadSafer {

    private int sheepCount = 0;

    private void incrementAndReport(){
        System.out.print((++sheepCount) + " ");
    }

    public static void main(String[] args) {
        ExecutorService service = null;

        try{
            service = Executors.newFixedThreadPool(20);
            SheepManagerNoThreadSafer manager = new SheepManagerNoThreadSafer();
            for(int i = 0; i < 10; i++){
                service.submit(() -> manager.incrementAndReport());
            }
        }finally {
            if(service != null) service.shutdown();
        }
    }


}
