package chapter_7.streams.reductions;


import java.util.List;

/**
 * Combining Results with reduce()
 *
 * As you learned in Chapter_4, the stream operation reduce() combines a stream into a single object.
 * Recall that the first parameter to the reduce() method is called the identity, the second parameter is called
 * the accumulator, and the third parameter is called the combiner.
 *
 * <U> U reduce(U identity, BiFunction<U, ? super T,U> accumulator, BinaryOperator<U> combiner)
 *
 * We can concatenate a list of char values, using reduce() method, as shown in the following example:
 *
 * Note: The naming of the variables in this stream example is not accidental. We used c for char, whereas s1, s2,
 * and s3 are String values.
 *
 * On parallel streams, the reduce() methods works by applying the reduction to pairs of elements within the stream
 * to create intermediate values and then combining those intermediate values to produce a final result. Put another
 * way, in a serial stream, wolf is built one character at a time. In a parallel stream, the intermediate values wo
 * and lf are created and then combined.
 *
 * With parallel streams, we now have to be concerned about the order. What if the elements of a string are combined
 * in the wrong order to produce wlfo or flwo? The Stream API prevents this problem, while still allowing streams to
 * be processed in parallel, as long as you follow one simple rule: make sure that the accumulator and combiner work
 * regardless of the order they are called in.
 *
 *
 * Although the one- and two-argument version of reduce() do support parallel processing, it is recommended that you
 * use the three-argument version of reduce() when working with parallel streams. Providing an explicit combiner
 * method allows the JVM to partition the operations in the stream more efficiently.
 *
 *
 * Note: In serial stream the order is predicted. However, with parallel streams, though order is no longer guaranteed,
 * and any argument that violates these rules is much more likely to produce side effects or unpredictable results.
 */
public class CombiningResultsWithReduce {

    public static void main(String[] args) {

        parallelStreamReduceMethod();
        parallelStreamProblematicReduceMethod();


    }

    private static void parallelStreamReduceMethod(){
        String word = List.of('w','o','l','f')
                .parallelStream()
                .reduce("",(s1,c) -> s1 + c, (s2, s3) -> s2 + s3);
        System.out.println(word); //wolf
    }

    /**
     * Let's take a look at an example using problematic accumulator. In particular, order matters when subtracting
     * numbers; therefore, the following code can output different values depending on whether you use a serial or
     * parallel stream
     * It may output -21, 3, or some other value.
     *
     * Second example:
     * On a serial stream, it prints Xwolf, but on a parallel stream the result is XwXoXlXf. As part of the parallel
     * process, the identity is applied to multiple elements in the stream, resulting in very unexpected data.
     */
    private static void parallelStreamProblematicReduceMethod(){
        var result = List.of(1,2,3,4,5,6)
                .parallelStream()
                .reduce(0, (a,b) -> (a-b));

        System.out.println(result);

        String word = List.of("w","o","l","f")
                .parallelStream()
                .reduce("X", String::concat); //XwXoXlXf

        System.out.println(word);
    }
}
