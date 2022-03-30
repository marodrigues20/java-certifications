package chapter_7.thread;


/**
 * Pooling with Sleep
 * - Polling is the process of intermittently checking data at some fixed intervals
 *
 * Thread.sleep() throws the checked InterruptedException
 *
 * About the code bellow:
 * How many times does this program print Not reached yet? The answer is, we don't know! It could output zero, ten,
 * or a million times. If our thread scheduler is particularly poor, it could operate infinitely! Using a while()
 * loop to check for data without some kind of delay is considered a bad coding practice as it ties up CPU resources
 * for no reason.
 * We can improve this result by using the Thread.sleep() method to implement polling. The Thread.sleep() method
 * requests the current thread of execution rest for a specified number of milliseconds. When used inside the body of
 * the main() method the thread associated with the main() method will pause, while the separate thread  will continue
 * to run
 */
public class CheckResults {

    private static int counter = 0;
    public static void main(String[] a) throws InterruptedException {
        new Thread(
                () -> {
                    for(int i = 0; i < 500; i++){
                        CheckResults.counter++;
                    }
                }
        ).start();

        while (CheckResults.counter < 100){
            System.out.println("Not reached yet");
            Thread.sleep(1000); // 1 SECOND
        }

        System.out.println("Reached!");
    }
}
