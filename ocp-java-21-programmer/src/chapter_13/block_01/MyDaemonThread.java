package chapter_13.block_01;

public class MyDaemonThread {

    public static void main(String[] args) {

        // Creates the thread object with a lambda (Runnable) and a custom name
        // At this point the thread is in state: NEW
        Thread daemon = new Thread(() -> System.out.println("Daemon running"), "daemon-thread");

        // Marks this thread as a daemon thread
        // A daemon thread does NOT prevent the JVM from shutting down
        // When only daemon threads are left running, the JVM exits automatically
        // ⚠️ Must be called BEFORE start() — otherwise throws IllegalThreadStateException
        daemon.setDaemon(true);

        // start() → asks the OS to create a new thread
        // After start(), the thread moves to state: RUNNABLE
        daemon.start();

        // ⚠️ NOTE: if the main thread finishes before the daemon thread prints,
        // the output may never appear — the JVM shuts down without waiting for daemon threads
    }
}

/**
 * NEW ──► RUNNABLE ──► TERMINATED
 * (new)   (start)      (run ends — or JVM shuts down first)
 */