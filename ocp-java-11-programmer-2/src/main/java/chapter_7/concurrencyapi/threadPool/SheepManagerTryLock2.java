package chapter_7.concurrencyapi.threadPool;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Attempting to Acquire a Lock
 *
 * The Lock interface includes an overloaded version of tryLock(long, TimeUnit) that acts like a hybrid of lock()
 * and tryLock(). Like the other two methods, if the lock is available, then it will immediately return with it.
 * If a lock is unavailable, though, it will wait up to the specified time limit for the lock.
 *
 * The code is the same as before, except this time one of the threads waits up to 10 seconds to acquire the lock.
 *
 * The ReentrantLock class maintains a counter of the number of times a lock has been given to a thread. To release
 * the lock for other threads to use, unlock() must be called the same number of times the lock was granted.
 *
 * The thread obtains the lock twice but releases it only once. For calls with tryLock(), you need to call unlock()
 * only if the method return true.
 *
 * The Concurrency API includes other lock-based classes, although ReentrantLock is the only you need to know for the
 * exam.
 *
 * ReentrantReadWriteLock is a really useful class. But it is not required for certification.
 *
 */
public class SheepManagerTryLock2 {

    public static void main(String[] args) throws InterruptedException {
        Lock lock = new ReentrantLock();
        new Thread(() -> printMessage(lock)).start();
        if (lock.tryLock(10, TimeUnit.SECONDS)) {
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
