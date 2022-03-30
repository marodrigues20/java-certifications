package chapter_7.streams;


import java.util.List;

/**
 * With a parallel stream the map() and forEach() operations are applied concurrently.
 * As you can see, the results are no longer ordered or predictable. The map() and forEach() operations on a
 * parallel stream are equivalent to submitting multiple Runnable lambda expression to a pooled thread executor
 * and then waiting for the results.
 * If you ran this code on a computer with fewer processors , it might output 10 seconds, 15 seconds, or some other
 * value. The key is that we've written our code to take advantage of parallel processing when available.
 *
 * 5 2 1 3 4
 * Time: 5 seconds
 */
public class ParallelDecomposition2 {

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        List.of(1,2,3,4,5)
                .parallelStream()
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
