package chapter_7.runnable;

/**
 * java.lang.Runnable is a functional interface that takes no arguments and returns no data.
 * The Runnable interface is commonly used to define the task or work a thread will execute,
 * separate from the application thread. We will be relying on the Runnable interface
 * throughout this chapter, especially when we discuss applying parallel operations to streams.
 */
public class PrintData implements Runnable {


    @Override
    public void run() {
        for (int i = 0; i < 3; i++) {
            System.out.println("Printing record: " + i);
        }
    }

    public static void main(String[] args){
        (new Thread(new PrintData())).start();
    }
}
