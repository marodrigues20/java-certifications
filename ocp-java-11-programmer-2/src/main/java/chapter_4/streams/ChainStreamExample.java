package chapter_4.streams;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChainStreamExample {

    public static void main(String[] args) {

        traditionalWay();
        streamChain();
        //infiniteStream();
        infiniteStreamWithLimit();
        //infiniteStream2();
        twoPipelineTogether();
        twoPipelineTogether2();
        peek();


    }

    private static void peek() {

        var infinitive = Stream.iterate(1, x-> x + 1);
        infinitive.limit(5)
                .peek(System.out::print)
                .filter(x -> x % 2 == 1)
                .forEach(System.out::print);



    }

    private static void twoPipelineTogether2() {
        var helper = Stream.of("goldfish", "finch")
                .filter(s -> s.length() > 5)
                .collect(Collectors.toList());

        long count = helper.stream()
                .count();

        System.out.println(count);
    }

    // Chain two pipeline together.
    private static void twoPipelineTogether() {
        long count = Stream.of("goldfish", "finch")
                .filter(s -> s.length() > 5)
                .collect(Collectors.toList())
                .stream()
                .count();

        System.out.println(count);

    }

    // It actually hangs until you kill the program, or it throws an exception after running out of memory.
    private static void infiniteStream2() {
        Stream.generate(() -> "Elsa")
                .filter(n -> n.length() == 4)
                .limit(2)
                .sorted()
                .forEach(System.out::println);
    }

    // This print Elsa twice.
    private static void infiniteStreamWithLimit() {
        Stream.generate(() -> "Elsa")
                .filter(n -> n.length() == 4)
                .limit(2)
                .sorted()
                .forEach(System.out::println);
    }

    // It actually hangs until you kill the program, or it throws an exception after running out of memory.
    private static void infiniteStream() {
        Stream.generate(() -> "Elsa")
                .filter(n -> n.length() == 4)
                .sorted()
                .limit(2)
                .forEach(System.out::println);
    }

    private static void streamChain() {
        var list = List.of("Toby", "Anna", "Leroy", "Alex");
        list.stream()
                .filter(n -> n.length() == 4)
                .sorted()
                .limit(2)
                .forEach(System.out::println);
    }

    private static void traditionalWay() {
        var list = List.of("Toby", "Anna", "Leroy", "Alex");
        List<String> filtered = new ArrayList<>();
        for (String name : list) {
            if (name.length() == 4) filtered.add(name);
        }

        Collections.sort(filtered);
        var iter = filtered.iterator();
        if (iter.hasNext())
            System.out.println(iter.hasNext());
        if (iter.hasNext())
            System.out.println(iter.next());
    }
}
