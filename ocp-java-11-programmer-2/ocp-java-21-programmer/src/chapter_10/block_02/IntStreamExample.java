package chapter_10.block_02;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class IntStreamExample {

    public static void main(String[] args) {

        System.out.println(" ************** sum() terminal operator over IntStream");
        int sum = IntStream.iterate(0, i -> i <= 10, i -> i + 1).sum();
        System.out.println(sum);

        System.out.println(" ************** filter() intermediate operator over IntStream to check the number is even");
        IntStream.iterate(0, i -> i <= 10, i -> i + 1)
                .filter(t -> t % 2 == 0)
                .forEach(System.out::println);

        System.out.println("************ noneMatch() terminate operator to check if the number is prime");
        int n = 7;
        boolean isPrime = IntStream.iterate(2, i -> i < n, i -> i + 1)
                .noneMatch(i -> n % i == 0);
        System.out.println(isPrime);

        System.out.println("********** range() static method to produce a finite Stream data ****************");
        IntStream.range(0, 11).forEach(System.out::println); // [0, 10] — exclusive upper bound

        System.out.println("********** rangeClosed() static method to produce a finite Stream data ****************");
        IntStream.rangeClosed(0, 10).forEach(System.out::println); // [0, 10] — inclusive upper bound

        System.out.println("********* Total of Numbers using count() terminal operator");
        long totalOfItems = IntStream.of(1, 3, 6, 8, 10).count();
        System.out.println(totalOfItems);

        System.out.println("************** generate static method / infinite data ***************");
        IntStream.generate(() -> (int) (Math.random() * 10))
                .limit(5)
                .forEach(System.out::println);

        System.out.println("******** iterate static method / infinite data ****************");
        IntStream.iterate(0, t -> t + 1)
                .limit(5)
                .forEach(System.out::println);


        System.out.println(" ************** static method min() and getAsInt()");
        //min
        OptionalInt intOptionalMin = IntStream.of(90, 110, 30, 10, 70, 150).min();
        intOptionalMin.ifPresent(t -> System.out.println(t));
        if (intOptionalMin.isPresent()) {
            int result = intOptionalMin.getAsInt();
            System.out.println(result);
        }


        //max
        System.out.println(" ************** static method max() and getAsInt()");
        OptionalInt intOptionalMax = IntStream.of(90, 110, 30, 10, 70, 150).max();
        intOptionalMax.ifPresent(t -> System.out.println(t));
        if (intOptionalMax.isPresent()) {
            int result = intOptionalMax.getAsInt();
            System.out.println(result);
        }

        //average
        System.out.println(" ************** terminal operation average() returns OptionalDouble **************");
        OptionalDouble doubleOptionalAvarage = IntStream.of(90, 110, 30, 10, 70, 150).average();
        doubleOptionalAvarage.ifPresent(t -> System.out.println(t));
        if (doubleOptionalAvarage.isPresent()) {
            double result = doubleOptionalAvarage.getAsDouble();
            System.out.println(result);
        }


        // summaryStatistics() is a terminal operation, not a static method
        System.out.println(" ************** summaryStatistics() — terminal operation with all stats");
        IntSummaryStatistics summaryStatistics = IntStream.of(90, 110, 30, 10, 70, 150).summaryStatistics();
        System.out.println("summaryStatistics getMax" + summaryStatistics.getMax());
        System.out.println("summaryStatistics getAverage" + summaryStatistics.getAverage());
        System.out.println("summaryStatistics getCount" + summaryStatistics.getCount());
        System.out.println("summaryStatistics getMin" + summaryStatistics.getMin());
        System.out.println("summaryStatistics getSum" + summaryStatistics.getSum());


        System.out.println("*** Parse IntStream to Stream<Integer> when you want to use methods that are not available in IntStream");
        Stream<Integer> boxed = IntStream.of(1, 2, 3, 4, 6, 7).boxed();
        boxed.forEach(System.out::println);

        System.out.println("*** Parse mapToObj to Stream<Integer> when you want to use methods that are not available in IntStream");
        Stream<Integer> intToObject = IntStream.of(1, 2, 3, 4, 6, 7).mapToObj(i -> i);
        List<Integer> listInteger = intToObject.collect(Collectors.toList());
        listInteger.forEach(System.out::println);

        System.out.println("*** mapToInt() — converts Stream<String> to IntStream");
        Stream.of("Alex", "Richard", "Nick", "George")
                .mapToInt(String::length)
                .forEach(System.out::println);
        // 4, 7, 4, 6

        System.out.println("*** mapToInt() — converts Stream<String> to IntStream because I want to use sum method but sum method is not available in Stream<String>");
        // total characters across all names
        int totalChars = Stream.of("Alex", "Richard", "Nick", "George")
                .mapToInt(String::length)
                .sum();
        System.out.println(totalChars); // 21

    }
}
