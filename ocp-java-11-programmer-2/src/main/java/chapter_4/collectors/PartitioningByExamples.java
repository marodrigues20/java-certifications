package chapter_4.collectors;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PartitioningByExamples {

    public static void main(String[] args) {

        partitioningByExample();
        partitioningBySetExample();
    }



    private static void partitioningBySetExample() {
        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Boolean, Set<String>> map = ohMy.collect(
                Collectors.partitioningBy(s -> s.length() <= 5,
                        Collectors.toSet())
        );
        System.out.println(map); //{false=[tigers], true=[lions, bears]}
    }

    private static void partitioningByExample() {

        var ohMy = Stream.of("lions", "tigers", "bears");
        Map<Boolean, List<String>> map = ohMy.collect(
                Collectors.partitioningBy(s -> s.length() <= 5)
        );
        System.out.println(map); //{false=[tigers], true=[lions, bears]}
    }


}
