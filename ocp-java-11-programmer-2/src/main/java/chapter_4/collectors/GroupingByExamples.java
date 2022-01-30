package chapter_4.collectors;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class GroupingByExamples {

    public static void main(String[] args) {

        groupingByExample();
        groupingBySetExample();
        groupingByTreeMapExample();
        groupingByTreeMapListExample();
        groupingByCountingExample();
        groupingByMappingExample();


    }

    private static void groupingByMappingExample() {

        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Optional<Character>> map = ohMy.collect(
                Collectors.groupingBy(String::length,
                        Collectors.mapping(
                                s -> s.charAt(0),
                                Collectors.minBy((a,b) -> a - b)))
        );
        System.out.println(map); //{5=Optional[b], 6=Optional[t]}
    }

    private static void groupingByCountingExample() {

        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Long> map = ohMy.collect(
                Collectors.groupingBy(String::length,
                        Collectors.counting())
        );
        System.out.println(map); //{false=[tigers], true=[lions, bears]}
    }

    private static void groupingByTreeMapListExample() {

        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, List<String>> map = ohMy.collect(
                Collectors.groupingBy(
                        String::length,
                        TreeMap::new,
                        Collectors.toList()));
        System.out.println(map); // {5=[lions, bears], 6=[tigers]}

        //Note: groupingBy cannot return null. It doesn't allow null keys.
    }

    private static void groupingByTreeMapExample() {
        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Set<String>> map = ohMy.collect(
                Collectors.groupingBy(
                        String::length,
                        TreeMap::new,
                        Collectors.toSet()));
        System.out.println(map); // {5=[lions, bears], 6=[tigers]}

        //Note: groupingBy cannot return null. It doesn't allow null keys.
    }

    private static void groupingBySetExample() {

        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, Set<String>> map = ohMy.collect(
                Collectors.groupingBy(
                        String::length,
                        Collectors.toSet()));
        System.out.println(map); // {5=[lions, bears], 6=[tigers]}

        //Note: groupingBy cannot return null. It doesn't allow null keys.
    }

    private static void groupingByExample() {
        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Integer, List<String>> map = ohMy.collect(
                Collectors.groupingBy(String::length)
        );
        System.out.println(map); // {5=[lions, bears], 6=[tigers]}

        //Note: groupingBy cannot return null. It doesn't allow null keys.
    }


}
