package chapter_4.streams;


import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * filter()
 * distinct()
 * limit() and skip()
 * map()
 * flatMap()
 * sorted()
 * peek()
 */
public class IntermediateOperationsExample {

    public static void main(String[] args) {

        //methodFilter();
        //methodDistinct();
        //methodLimitAndSkip();
        //methodMap();
        //methodFlatMap();
        //methodSorted();
        methodPeek();


    }

    //It is useful for debugging.
    //It is intended to perform an operation without changing the result but if we add malicious code inside of peed()
    //method we can change the stream. Java doesn't prevent us about it.
    private static void methodPeek() {
        Stream<String> stream = Stream.of("black bear", "brown bear", "grizzly");
        long count = stream.filter(s -> s.startsWith("g"))
                .peek(System.out::println).count();

        System.out.println(count);

    }

    // The sorted() method returns a stream with the elements sorted. Just like sorting arrays, Java
    // uses natural ordering unless we specify a comparator.
    private static void methodSorted() {
        Stream<String> s = Stream.of("brown-", "bear-");
        s.sorted()
                .forEach(System.out::print); //bear-brown-

        Stream<String> s2 = Stream.of("brown bear-", "grizzly-");
        s2.sorted(Comparator.reverseOrder())
                .forEach(System.out::print);
    }

    // The flatMap() method takes each element in the stream and makes any elements it contains top-level
    // elements in a single stream.
    private static void methodFlatMap() {
        List<String> zero = List.of();
        var one = List.of("Bonobo");
        var two = List.of("Mama Gorilla", "Baby Gorilla");
        Stream<List<String>> animals = Stream.of(zero, one, two);

        animals.flatMap(m -> m.stream())
                .forEach(System.out::println);
    }

    // The map() method creates a one-to-one mapping from the elements in the stream to the elements of the
    // next step in the stream.
    private static void methodMap() {
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        s.map(String::length) // x -> x.length()
                .forEach(System.out::print);
    }

    private static void methodLimitAndSkip() {

        Stream<Integer> s = Stream.iterate(1, n -> n + 1);
        s.skip(5)
                .limit(2)
                .forEach(System.out::println);

    }

    //The distinct() method returns a stream with duplicate values removed
    private static void methodDistinct() {
        Stream<String> s = Stream.of("duck", "duck", "duck", "goose");
        s.distinct()
                .forEach(System.out::println);
    }


    private static void methodFilter() {
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        s.filter(x -> x.startsWith("m"))
                .forEach(System.out::println); //monkey
    }
}
