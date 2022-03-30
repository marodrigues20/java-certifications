package chapter_7.streams.reductions;

import java.util.List;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Performing a Parallel Reduction on a Collector
 *
 * Every Collector instance defines a  characteristics() method that returns a set of Collector.Characteristics
 * attributes. When using a Collector to perform a parallel reduction, a number of properties must hold true.
 * Otherwise, the collect() operation will execute in a single-threaded fashion.
 *
 * Requirements for Parallel Reduction with collect()
 *
 * - The stream is parallel
 * - The parameter of the collect() operation has the Characteristics.CONCURRENT characteristic.
 * - Either the stream is unordered or the collector has the characteristics Characteristics.UNORDERED
 *
 * For example, while Collector.toSet() does have the UNORDERED characteristics, it does not have the CONCURRENT
 * characteristics. Therefore, the following is not parallel reduction even with a parallel stream:
 *
 * stream.collect(Collectors.toSet()); // Not a parallel reduction
 *
 * The Collector class includes two sets of static methods for retrieving collectors, toConcurrentMap() and
 * groupingByConcurrent(), that are both UNORDERED and CONCURRENT. These methods produce Collector instances
 * capable  of performing parallel reductions efficiently.
 *
 *
 * Here is a rewrite of an example from Chapter 4 to use a parallel stream and parallel reduction
 */
public class ParallelReductionOnCollector {


    public static void main(String[] args) {

       parallelStreamAndParallelReduction();




    }

    /**
     * We use a ConcurrentMap reference, although the actual class returned is likely ConcurrentHashMap
     */
    private static void parallelStreamAndParallelReduction(){
        Stream<String> ohMy = Stream.of("lions","tigers","bears").parallel();
        ConcurrentMap<Integer, String> map = ohMy
                .collect(Collectors.toConcurrentMap(String::length,
                        k->k,
                        (s1, s2) -> s1 + "," + s2));

        System.out.println(map); //{5=lions, bears, 6=tigers}
        System.out.println(map.getClass()); // java.util.concurrent.ConcurrentHashMap
    }

    /**
     * Finally, we can rewrite our groupingBy() example Chapter 4 to use a parallel stream and parallel reduction
     */
    private static void parallelStreamAndParallelReductionGroupBy(){

        var ohMy = Stream.of("lions", "tigers", "bears").parallel();
        ConcurrentMap<Integer, List<String>> map = ohMy.collect(
                Collectors.groupingByConcurrent(String::length));

        System.out.println(map); // 5 = [lions, bears], 6=[tigers]

    }
}
