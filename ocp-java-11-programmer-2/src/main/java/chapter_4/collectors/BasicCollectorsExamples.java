package chapter_4.collectors;

import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Many of these collectors work in the same way.
 * Predefined collectors are in the Collectors class rather than the Collector interface.
 */
public class BasicCollectorsExamples {

    public static void main(String[] args) {

        joininCollector();
        averingIntCollector();
        toCollectionCollector();
        mapsCollector();
        maps2Collector();
        maps3Collector();

    }

    private static void maps3Collector() {
        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map = ohMy.collect(
                Collectors.toMap(String::length, s -> s,
                        (s1,s2) -> s1 + "," + s2,
                        TreeMap::new)
        );

        System.out.println(map); // {5=lions,bears, 6=tigers}
        System.out.println(map.getClass());  //class java.util.TreeMap
    }

    private static void maps2Collector() {
        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, String> map = ohMy.collect(
                Collectors.toMap(String::length, s -> s,
                (s1,s2) -> s1 + "," + s2)
        );

        System.out.println(map); // {5=lions,bears, 6=tigers}
        System.out.println(map.getClass()); //class java.util.HashMap

    }

    private static void mapsCollector() {

        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<String, Integer> map = ohMy.collect(
                Collectors.toMap(s -> s, String::length)
        );
        System.out.println(map); //{lions=5, bears=5, tigers=6}

        //note: We use the provided String as a key. The second function tells the collector how to create the value.
        // In our example we use the length of the String as the value.
    }

    private static void toCollectionCollector() {

        var ohMy = Stream.of("lions", "tigers", "bears");
        TreeSet<String> result = ohMy
                .filter(s -> s.startsWith("t"))
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println(result); // [tigers]

        //note: If we don't care which implementation of Set we got, we could have written Collectors.toSet() instead.
    }

    // With primitive streams, the result of an average was always a double regardless of what type is being averaged.
    private static void averingIntCollector() {
        var ohMy = Stream.of("lions", "tigers", "bears");
        Double result = ohMy.collect(Collectors.averagingInt(String::length));
        System.out.println(result);
    }

    private static void joininCollector() {

        var ohMy = Stream.of("lions", "tigers", "bears");
        String result = ohMy.collect(Collectors.joining(", "));
        System.out.println(result); //lions, tigers, bears
    }
}
