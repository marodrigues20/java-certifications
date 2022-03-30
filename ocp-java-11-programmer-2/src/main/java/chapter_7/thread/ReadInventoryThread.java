package chapter_7.thread;

import chapter_7.runnable.PrintData;

/**
 * The simplest way to execute a thread is by using the java.lang.Thread class. Executing a task with Thread  is a
 * two-steps process. First, you define the Thread with the corresponding task to be done. Then, you start the task by
 * using the Thread.start() method.
 * As we will discuss later in the chapter, Java does not provide any guarantees about the order in which a thread will
 * be processed once it is started. It may be executed immediately or delayed for a significant amount of time.
 *
 * Defining the task that a Thread instance will be executed can be done two ways in Java:
 * - Provide a Runnable object or lambda expression to the Thread constructor.
 * - Created a class that extends Thread and overrides the run() method.
 *
 *
 * Anytime you create a Thread instance, make sure that you remember to start the task with the Thread.start() method.
 * This starts the task in a separate operating system thread.
 *
 * Be careful with code that attempts to start a thread by calling run() instead of start().Calling run() on a Thread()
 * or a Runnable does not actually start a new tread.
 *
 * Extend a Thread class when you are creating your own priority-based thread. In most situations, you should implement
 * the Runnable interface rather than extend the Thread class.
 *
 * Object.wait(), Object.notify(), Thread.join(), etc. In fact, you should avoid them in general and use the Concurrency
 * API.
 */
public class ReadInventoryThread extends Thread {

    public void run() {
        System.out.println("Printing zoo inventory");
    }

    public static void main(String[] args) {
        System.out.println("begin");
        (new ReadInventoryThread()).start();
        (new Thread(new PrintData())).start();
        (new ReadInventoryThread()).start();
        System.out.println("end");
    }
}
