package chapter_7.concurrencyapi.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Understanding the Lock Framework
 * <p>
 * The Concurrency API includes the Lock interfaces that is conceptually similar to using the synchronized keyword,
 * but with a lot more bells and whistles. Instead of synchronizing on any Object, though, we can "lock" only on an
 * object that implements the Lock interfaces.
 * When you need to protect a piece of code from multithreaded processing, create an instance of Lock that all threads
 * have access to. Each thread then calls lock() before it enters the protected code and calls unlock() before it
 * exists the protected code.
 * <p>
 * Lock solution has a number of features not available to the synchronized block.
 *
 * Note: The ReentrantLock class contains a constructor that can be used to send a boolean "fairness" parameter.
 * If set to true, then the lock will usually be granted to each thread in the order it was requested.
 * It is false by default when using the no-argument constructor. In practice, you should enable fairness only when
 * ordering is absolutely required, as it could lead to a significant slowdown.
 *
 * Method | Description
 * void lock()                    | Requests a lock and blocks until lock is acquired.
 * void unlock()                  | Release a lock
 * boolean tryLock()              | Requests a lock and returns immediately. Returns a boolean indicating whether the lock was
 *                                  successfully acquired.
 * boolean tryLock(long, TimeUnit | Requests a lock and blocks up to the specified time until lock is required. Returns a
 *                                  boolean indicating whether the lock was successfully acquired.
 *
 */
public class SheepManagerLockFramework {

    private int sheepCount = 0;
    Lock lock = new ReentrantLock();

    private void incrementAndReport() {
        try {
            lock.lock();
            System.out.print((++sheepCount) + " ");
        } finally {
            lock.unlock(); // IllegalMonitorStateException
        }
    }

    public static void main(String[] args) {
        ExecutorService service = null;

        try {
            service = Executors.newFixedThreadPool(20);
            SheepManagerLockFramework manager = new SheepManagerLockFramework();
            for (int i = 0; i < 10; i++) {
                service.submit(() -> manager.incrementAndReport());
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }


}
