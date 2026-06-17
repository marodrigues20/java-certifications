package chapter_13.block_01;

// Extending Thread is one way to create a thread
// ⚠️ Downside: consumes the class inheritance slot — cannot extend another class
public class MyThread extends Thread {

    // Constructor receives a name and passes it to the Thread constructor via super()
    // This allows identifying the thread by name during debugging
    public MyThread(String name) {
        super(name);
    }

    public static void main(String[] args) {

        // Creates the thread object with a custom name
        // At this point the thread is in state: NEW
        MyThread myThread = new MyThread("my-first-thread");

        // ❌ WRONG — calls run() directly on the main thread, does NOT create a new thread
        // myThread.run();

        // ✅ start() → asks the OS to create a new thread
        // Internally, start() calls run(), but on a different thread
        // After start(), the thread moves to state: RUNNABLE
        myThread.start();

        // ❌ WRONG — calls run() directly on the main thread, does NOT create a new thread
        // myThread.run();
    }

    // Defines the work this thread will perform
    // Called internally by start() — never call run() directly
    @Override
    public void run() {
        try {

            // Pauses the thread for 10 seconds (10,000 ms)
            // During this time the thread is in state: TIMED_WAITING
            // CPU is released — the thread does not consume processing while sleeping
            // After 10s, moves back to state: RUNNABLE
            Thread.sleep(10000);

            // Thread.currentThread() → returns the reference of the thread executing this code
            // getName() → returns the name passed in the constructor ("my-first-thread")
            System.out.println("Running: " + Thread.currentThread().getName());

        } catch (InterruptedException e) {
            // InterruptedException is a checked exception — compiler forces you to handle it
            // Thrown when another thread calls myThread.interrupt() while this thread is sleeping
            throw new RuntimeException(e);
        }
    }
}