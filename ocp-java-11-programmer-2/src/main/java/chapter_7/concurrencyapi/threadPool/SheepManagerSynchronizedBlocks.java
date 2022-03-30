package chapter_7.concurrencyapi.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Improving Access with Synchronized Blocks
 * <p>
 * While atomic classes are great at protecting single variables, they aren't particularly useful if you need to
 * execute a series of commands or call a method. How do we improve the results so that worker is able to increment
 * and report the results in order? The most common technique is to use a monitor, also called a lock, to synchronize
 * access. A monitor is structure that supports mutual exclusion, which is property that most one thread is executing
 * a particular segment of code  at a given time.
 * <p>
 * In Java, any Object can be used as a monitor, along with synchronized keyword, as shown in the following example:
 * SheepManager manager = new SheepManager();
 * synchronized(manager){
 * // Work to be completed by one thread at a time,
 * }
 * <p>
 * Note 1: The example above, we've synchronized the creation of the threads but the execution of the threads.
 * <p>
 * Note 2: To synchronize access across multiple threads, each thread must have access to the same Object. For example,
 * synchronize on different objects would not actually order the result.
 * <p>
 * Note 3: We could have used an atomic variable along with the synchronized block in this example, although it is
 * unnecessary. Since synchronized blocks allow one thread to enter, we're not gaining any improvement by using an
 * atomic variable if the only time that we access the variable is within a synchronized block.
 *
 *
 * Note 3: The cost to use synchronized keyword can be performance cost. Concurrency API that are a lot easier to use
 * and more performant than synchronization. Some you have seen already, like the atomic classes, and others we'll
 * be covering shortly, including the Lock framework, concurrent collection, and cyclic barries.
 */
public class SheepManagerSynchronizedBlocks {

    private int sheepCount = 0;

    private void incrementAndReport() {
        synchronized (this) {
            System.out.print((++sheepCount) + " ");
        }
    }

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(20);
            SheepManagerSynchronizedBlocks manager = new SheepManagerSynchronizedBlocks();
            for (int i = 0; i < 10; i++) {
                service.submit(() -> manager.incrementAndReport());
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }

    private synchronized void incrementAndReport2() {
        System.out.print((++sheepCount) + " ");
    }

    // Example of static method to use synchronized. Example 1.
    private static void printDaysWork() {
        synchronized (SheepManagerSynchronizedBlocks.class){
            System.out.println("Finished work");
        }
    }

    // Example of static method to use synchronized. Example 2.
    private static synchronized void printDaysWork2(){
        System.out.println("Finished work");
    }


}
