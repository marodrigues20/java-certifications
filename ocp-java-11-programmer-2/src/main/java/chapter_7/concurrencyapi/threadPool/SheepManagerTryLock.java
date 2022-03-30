package chapter_7.concurrencyapi.threadPool;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Attempting to Acquire a Lock
 * <p>
 * The tryLock() method will attempt to acquire a lock and immediately return a boolean result indicating whether
 * the lock was obtained. Unlike the lock() method, it does not wait if another thread already holds the lock.
 *
 * The code bellow:
 * When you run this code, it could produce either message, depending on the order of execution. A fun exercise is
 * to insert some Threads.sleep() delays into this snipped. A fun exercise is to insert some Thread.sleep() delays
 * into this snipped to encourage a particular message to be displayed.
 *
 *
 * Like lock(), the tryLock() method should be used with a try/ finally block.
 * Fortunately, you need to release the lock only if it was successfully acquired.
 */
public class SheepManagerTryLock {

    public static void main(String[] args) {
        Lock lock = new ReentrantLock();
        new Thread(() -> printMessage(lock)).start();
        if (lock.tryLock()) {
            try {
                System.out.println("Lock obtained, entering protected code");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("Enable to acquire lock, doing something else");
        }
    }

    private static void printMessage(Lock lock) {
        try {
            lock.lock();
            System.out.println("My locked code!");
        } finally {
            lock.unlock();
        }
    }


}
