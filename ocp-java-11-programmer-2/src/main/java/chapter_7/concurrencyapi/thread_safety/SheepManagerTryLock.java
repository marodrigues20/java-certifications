package chapter_7.concurrencyapi.thread_safety;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

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
