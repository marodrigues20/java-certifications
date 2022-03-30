package chapter_7.streams.reductions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Avoiding Stateful Operations
 * <p>
 * Side effects can appear in parallel stream if your lambda expression are stateful. A stateful lambda expression
 * is one whose result depends on any state that might change during the execution of a pipeline. On the other hand,
 * a stateless lambda expression is whose result does not depend on any state that might change during the execution
 * of a pipeline.
 * <p>
 * Imagine we require a method that keeps only even numbers in a stream and adds them to the list.
 * <p>
 * The output:
 * [2, 4, 6, 8, 10]
 * [6, 8, 4, 2, 10]
 * <p>
 * The problem is that our lambda expression is stateful and modifiers a list that is outside our stream. We could
 * use forEachOrdered() to add elements to the list, but that forces the parallel stream to be serial, potentially
 * losing concurrency enhancements.
 * <p>
 * We can fix this solution by rewriting our stream operation to no longer have a stateful lambda expression.
 */
public class AvoidStatefulOperation {

    public static void main(String[] args) {

        var list = addValues(IntStream.range(1, 11));
        var list2 = addValues(IntStream.range(1, 11).parallel());
        var list3 = addValuesFixed(IntStream.range(1, 11).parallel());
        System.out.println(list);
        System.out.println(list2);
        System.out.println(list3);
    }

    public static List<Integer> addValues(IntStream source) {
        var data = Collections.synchronizedList(new ArrayList<Integer>());
        source.filter(s -> s % 2 == 0)
                .forEach(i -> {
                    data.add(i);
                }); // STATEFUL: DON'T DO THIS
        return data;
    }

    /**
     * This method processes the stream and then collects all the results into a new list. It produces the same
     * result on both serial and parallel stream.
     *
     * This implementation removes the stateful operation and relies on the collector to assemble the elements.
     * We could also use a concurrent collector to parallelize the building of the list. The goal is to write
     * our code to allow for parallel processing and let the JVM handle the rest.
     *
     * It is strongly recommended that you avoid stateful operations when using parallel stream, to remove
     * any potential data side effects.
     */
    public static List<Integer> addValuesFixed(IntStream source) {
        return source.filter(s -> s % 2 == 0)
                .boxed()
                .collect(Collectors.toList());

    }


}
