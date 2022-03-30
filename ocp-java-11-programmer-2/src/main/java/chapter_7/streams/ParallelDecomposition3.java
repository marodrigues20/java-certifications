package chapter_7.streams;


import java.util.List;

/**
 * Ordering forEach Results
 *
 * The Stream API includes an alternative version of the forEach() operation called forEachOrdered(), which forces
 * a parallel stream to process the results in order at the cost of performance.
 *
 * Output
 * 5 2 1 4 3
 * Time: 5 seconds
 *
 *
 * With this change, the forEachOrdered() operation forces our stream into a single-threaded process. While we've lost
 * some of the performance gains of using a parallel stream, our map() operation is still able to take advantage of the
 * parallel and performance a parallel decomposition in 5 seconds instead of 25 seconds.
 */
public class ParallelDecomposition3 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List.of(5,2,1,4,3)
                .parallelStream()
                .map(w -> doWork(w))
                .forEachOrdered(s -> System.out.print(s + " "));

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
