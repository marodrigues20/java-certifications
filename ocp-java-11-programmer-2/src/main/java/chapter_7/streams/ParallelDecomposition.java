package chapter_7.streams;


import java.util.List;

/**
 * A parallel decomposition is the process of taking a task, breaking it up into smaller pieces that can be performed
 * concurrently, and then reassembling the result. The more concurrent a decomposition, the greater the performance
 * improvement of using parallel stream.
 *
 * The code bellow:
 * As you might expected, the results are ordered and predicable because we are using serial stream. It also took
 * around 25 seconds to process all five results, one at a time.
 *
 * Output:
 * 1 2 3 4 5
 * Time: 25 seconds
 *
 */
public class ParallelDecomposition {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List.of(1,2,3,4,5)
                .stream()
                .map(w -> doWork(w))
                .forEach(s -> System.out.print(s + " "));

        System.out.println();
        var timeTaken = (System.currentTimeMillis()-start)/1000;
        System.out.println("Time: " + timeTaken+" seconds");
    }

    private static int doWork(int input){
        try{
            Thread.sleep(5000);
        }catch (InterruptedException e){}
        return input;
    }
}
