package chapter_7.concurrencyapi.threadPool;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Orchestration Tasks with a CyclicBarrier
 *
 * We complete our discussion of thread-safety by discussing how to orchestrate complex tasks across many things.
 *
 * Like synchronizing on the same object, coordinating a task with a CyclicBarrier requires the object to be static
 * or passed to the thread performing the task.
 *
 * Example Bellow:
 * As you can see, all of the results are now organized. Removing the lions all happens in one step, as does
 * cleaning the pen and adding the lions back in. In this example, we used two different constructors for our
 * CyclicBarrier objects, the latter of which called a Runnable method upon completion.
 *
 * The CyclicBarrier class allows us to perform complex, multithreaded tasks, while all threads stop and wait
 * at logical barriers. This solution is superior to a single-threaded solution, as the individual tasks, such as
 * removing the lions, can be completed in parallel by all four zoo workers.
 *
 * Note 1: If you are using a thread pool, make sure that you set the number of available threads to be at least
 * as large as your CyclicBarrier limit value. For example, what if we changed the code in the previous example
 * to allocate only two threads, such as in following snippet?
 * ExecutorService service = Executors.newFixedThreadPool(2);
 * In this case, the code will hang indefinitely. The barrier would never be reached as the only threads available
 * in the pool are stuck waiting for the barrier to be complete. This would result in a deadlock, which will be
 * discussed shortly.
 *
 * Reusing CyclicBarrier
 * After a CyclicBarrier is broken, all threads are released, and the number of threads waiting on the CyclicBarrier
 * goes back to zero. At this point, the CyclicBarrier may be used again for a new set of waiting threads.
 */
public class LionPenManager {

    private void removeLions() {
        System.out.println("Removing lions");
    }

    private void cleanPen() {
        System.out.println("Cleaning the pen");
    }

    private void addLions() {
        System.out.println("Adding Lions");
    }

    public void performTask(CyclicBarrier c1, CyclicBarrier c2) {

        try {
            removeLions();
            c1.await();
            cleanPen();
            c2.await();
            addLions();
        } catch (InterruptedException | BrokenBarrierException e) {
           // Handle checked exceptions here
        }

    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            var manager = new LionPenManager();

            var c1 = new CyclicBarrier(4);
            var c2 = new CyclicBarrier(4, () -> System.out.println("*** Pen Cleaned!"));

            for (int i = 0; i < 4; i++) {
                service.submit(() -> manager.performTask(c1, c2));
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
