package chapter_4.streams;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.BinaryOperator;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * You can perform a terminal operation without any intermediate operation but not the other way around.
 * Methods:
 * count()
 * min()
 * max()
 * findAny()
 * findFirst()
 * allMatch()
 * anyMatch()
 * noneMatch()
 * forEach()
 * reduce()
 * collect()
 */
public class TerminalOperationsExamples {

    public static void main(String[] args) {
        //methodCount();
        //methodMin();
        //methodFindAny();
        //methodMatches();
        //methodForEach();
        //methodReduce();
        //methodReduce2();
        methodReduce3();
        //methodCollect();
        //methodCollect2();
        //methodCollect3();
    }

    //Pre-defined Collectors
    private static void methodCollect3() {
        Stream<String> stream = Stream.of("w", "o", "l", "f");
        TreeSet<String> set = stream.collect(Collectors.toCollection(TreeSet::new));
        System.out.println(set);

        Stream<String> stream2 = Stream.of("w", "o", "l", "f");
        Set<String> set2 = stream2.collect(Collectors.toSet());
        System.out.println(set2);

    }

    private static void methodCollect2() {
        Stream<String> stream = Stream.of("w", "o", "l", "f");
        //Writing our own collector
        TreeSet<String> set = stream.collect(TreeSet::new, TreeSet::add, TreeSet::addAll);
        System.out.println(set); //[f, l, o, w]
    }

    // The collection() method is a special type of reduction called a mutable reduction.
    // Like: StringBuilder, ArrayList. This is a really useful method, because it lets us get data out of
    // streams and into another form.
    private static void methodCollect() {
        Stream<String> stream = Stream.of("w", "o", "l", "f");
        StringBuilder word = stream.collect(StringBuilder::new,
                StringBuilder::append,
                StringBuilder::append);

        System.out.println(word);

    }

    private static void methodReduce3() {
        Stream<String> stream = Stream.of("w", "o", "l", "f!");
        // The first parameter (0) is the value for the initializer.
        // The second parameter is the accumulator. First argument is Integer the another is a String
        // The third parameter is called the combiner, which combines any intermediate totals. In this case
        // a and b are both Integer values.
        int length = stream.reduce(0, (i, s) ->  i+s.length(), (a, b) -> a + b);
        System.out.println(length);
        //Note: The three-argument reduce() operation is useful when working with parallel streams because it allows
        //the stream to be decomposed and reassembled by separate threads.
    }

    private static void methodReduce2() {
        BinaryOperator<Integer> op = (a, b) -> a * b;
        Stream<Integer> empty = Stream.empty();
        Stream<Integer> oneElement = Stream.of(3);
        Stream<Integer> threeElements = Stream.of(3, 5, 6);

        empty.reduce(op).ifPresent(System.out::println); // no output
        oneElement.reduce(op).ifPresent(System.out::println); //3
        threeElements.reduce(op).ifPresent(System.out::println); //90
    }

    //The reduce() method combines a stream into a single object.
    //It is a reduction, which means, it will process all elements.
    private static void methodReduce() {

        // Conventional way
        var array = new String[] {"w", "o", "l","f"};
        var result = "";
        for (var s : array) result = result + s;
        System.out.println(result); //wolf

        //Stream way
        Stream<String> stream = Stream.of("w","o","l","f");
        String word = stream.reduce("", (s, c) -> s + c);
        //String word = stream.reduce("", String::concat); //another way
        System.out.println(word);

        Stream<Integer> stream2 = Stream.of(3,5,6);
        System.out.println(stream2.reduce(1, (a,b) -> a*b));
    }

    private static void methodForEach() {
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        s.forEach(System.out::print);
    }

    // Methods search a stream and return information about how the stream pertains to the predicate.
    private static void methodMatches() {

        var list = List.of("monkey", "2", "chimp");
        Stream<String> infinite = Stream.generate(() -> "chimp");
        Predicate<String> pred = x -> Character.isLetter(x.charAt(0));

        System.out.println(list.stream().anyMatch(pred));     // true
        System.out.println(list.stream().allMatch(pred));     // false
        System.out.println(list.stream().noneMatch(pred));    // false
        System.out.println(infinite.anyMatch(pred));          // true

    }


    // These methods are terminal operations but not reductions. The reason is that they sometimes return without
    // processing all of the elements. This means that they return a value based on the stream but do not reduce
    // the entire stream into one value.
    private static void methodFindAny() {
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        Stream<String> infinite = Stream.generate(() -> "chimp");

        s.findAny().ifPresent(System.out::println); //monkey (usually)
        infinite.findAny().ifPresent(System.out::println); //chimp
    }


    // Min() method is a reduction because it returns a single value after looking at the entire stream.
    private static void methodMin() {
        Stream<String> s = Stream.of("monkey", "ape", "bonobo");
        Optional<String> min = s.min((s1, s2) -> s1.length() - s2.length());
        min.ifPresent(System.out::println); //ape
    }

    // The count() method determines the number of elements in a finite stream.
    // For an infinite stream, it never terminates.
    private static void methodCount() {
        Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
        System.out.println(s.count());
    }
}
