package chapter_13.block_01;

// Runnable is a @FunctionalInterface — defines only one abstract method: run()
// Preferred over "extends Thread" because it does not consume the class inheritance slot
public class MyRunnableImpl implements Runnable {

    public static void main(String[] args) {

        // new Thread(Runnable, String)
        // → 1st argument: who defines the work (our Runnable implementation)
        // → 2nd argument: thread name (useful for debugging)
        // At this point the thread is in state: NEW
        Thread t = new Thread(new MyRunnableImpl(), "My Thread Implementing Run");

        // start() → asks the OS to create a new thread
        // Internally, start() calls run(), but on a different thread
        // ⚠️ Never call run() directly — it would execute on the main thread, without creating a new one
        // After start(), the thread moves to state: RUNNABLE
        t.start();

        // ❌ WRONG — calls run() directly on the main thread, does NOT create a new thread
        // t.run();

    }

    @Override
    public void run() {
        try {

            // Pauses the thread for 10 seconds (10,000 ms)
            // During this time the thread is in state: TIMED_WAITING
            // CPU is released — the thread does not consume processing while sleeping
            // After 10s, moves back to state: RUNNABLE
            Thread.sleep(10000);

            // Thread.currentThread() → returns the reference of the thread executing this code
            // getName() → returns the name passed in the constructor ("My Thread Implementing Run")
            System.out.println("Running: " + Thread.currentThread().getName());

        } catch (InterruptedException e) {
            // InterruptedException is a checked exception — compiler forces you to handle it
            // Thrown when another thread calls t.interrupt() while this thread is sleeping
            throw new RuntimeException(e);
        }
    }
}

/**
 * NEW ──► RUNNABLE ──► TIMED_WAITING ──► RUNNABLE ──► TERMINATED
 * (new)   (start)       (sleep 10s)       (wakes)      (run ends)
 */