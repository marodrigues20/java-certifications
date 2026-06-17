package chapter_13.block_01;

public class MyThreadLambda {

    public static void main(String[] args) {

        // Lambda expression implements the Runnable @FunctionalInterface
        // → 1st argument: lambda replaces the need to create a separate Runnable class
        // → 2nd argument: thread name (useful for debugging)
        // .start() → asks the OS to create a new thread — moves to state: RUNNABLE
        // ⚠️ Never call .run() directly — it would execute on the main thread, without creating a new one
        new Thread(() -> System.out.println("Lambda thread: " + Thread.currentThread().getName()), "lambda-thread").start();
    }
}

/**
 * NEW ──► RUNNABLE ──► TERMINATED
 * (new)   (start)      (run ends)
 */