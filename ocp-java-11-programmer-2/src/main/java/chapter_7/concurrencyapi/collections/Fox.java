package chapter_7.concurrencyapi.collections;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Identifying Threading Problems
 * <p>
 * A threading problem can occur in multithreading application when two or more threads interact in an unexpected
 * and undesirable way. For example, two threads my block each other from accessing a particular segment of code.
 * <p>
 * Although the Concurrency API reduces the potencial for threading issues, it does not eliminate it.
 * <p>
 * Understand Liveness
 * As you have seen in this chapter, many thread operations can be performed independently, but some require
 * coordinate. For example, synchronized on a method requires all threads that call the method to wait for
 * other threads to finish before continuing. You also saw earlier in the chapter that threads in CyclicBarrier will
 * each wait for the barrier limit to be reached before continuing.
 * <p>
 * Liveness is the ability of an application to be able in a timely manner. Liveness problems, then, are those in which
 * the application becomes unresponsible or in some kind of "stuck" state. For the exam, there are three types of
 * liveness issues with which you should be familiar: deadlock, starvation, and livelock.
 * <p>
 * Deadlock
 * Deadlock occurs when two or more threads are blocked forever, each waiting on the other.
 * <p>
 * <p>
 * The Example bellow:
 * Foxy obtains the food and then moves to the other side of the environment to obtain the water. Unfortunately,
 * Tails already drank the water and is waiting for the food to become available. The result is that our program
 * outputs the following, and it hangs indefinitely.
 * Got Food!
 * Got Water!
 * This example is considered a deadlock because both participants are permanently blocked, waiting on resources that
 * will never become available.
 * <p>
 * <p>
 * Preventing Deadlocks
 * <p>
 * How do you fix a deadlock once it has occurred? The answer is that you can't in most situations. On the other hand,
 * there are numerous strategies to help prevent deadlocks from ever happening in the first place.
 * <p>
 * Starvation
 * Starvation occurs when a single thread is perpetually denied access to a shared resource or lock. The thread is still
 * active, but it is unable to complete its work as a result of other threads constantly taking the resource that they
 * are trying to access.
 * <p>
 * Livelock
 * Livelock occurs when two or more threads are conceptually blocked forever, although they are each still active and
 * trying to complete their task. Livelock is a special case of resource starvation in which two or more threads actively
 * try to acquire a set of locks, are unable to do so, and restart part of the process.
 * Livelock is often a result of two threads trying to resolve a deadlock.
 * <p>
 * In practice, livelock is often a difficult issue to detect. Threads in a livelock state appear active and able
 * to respond to request, even when they are in fact stuck in an endless cycle.
 * <p>
 * Managing Race Conditions
 * <p>
 * A race condition is an undesirable result that occurs when two tasks, which should be completed sequentially, are
 * completed at the same time.
 * <p>
 * For the exam, you should understand that race conditions lead to invalid data if they are not properly handled.
 * Race conditions tend to appear in highly concurrent application. As a software system grows and more users are
 * added, they tend to appear more frequently. One solution is to use a monitor to synchronize  on the relevant
 * overlapping task.
 */


class Food {
}

class Water {
}

public class Fox {

    public void eatAndDrink(Food food, Water water) {
        synchronized (food) {
            System.out.println("Got Food");
            moved();
            synchronized (water) {
                System.out.println("Got Water");
            }
        }
    }

    public void drinkAndEat(Food food, Water water) {
        synchronized (water) {
            System.out.println("Got Water!");
            moved();
            synchronized (food) {
                System.out.println("Got Food!");
            }
        }
    }

    private void moved() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // Handle Exception
        }
    }

    public static void main(String[] args) {
        // Create participants and resources
        Fox foxy = new Fox();
        Fox tails = new Fox();
        Food food = new Food();
        Water water = new Water();
        // Process Data
        ExecutorService service = null;
        try {
            service = Executors.newScheduledThreadPool(10);
            service.submit(() -> foxy.eatAndDrink(food, water));
            service.submit(() -> tails.drinkAndEat(food, water));
        } finally {
            if (service != null) service.shutdown();
        }
    }


}
