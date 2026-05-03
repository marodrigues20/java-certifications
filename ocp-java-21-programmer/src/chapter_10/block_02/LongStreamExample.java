package chapter_10.block_02;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class LongStreamExample {

    public static void main(String[] args) {

        System.out.println(" ************** sum() terminal operator over LongStream");
        long sum = LongStream.iterate(0, i -> i <= 10, i -> i + 1).sum();
        System.out.println(sum);

        System.out.println(" ************** filter() intermediate operator over LongStream to check the number is even");
        LongStream.iterate(0, i -> i <= 10, i -> i + 1)
                .filter(t -> t % 2 == 0)
                .forEach(System.out::println);

        System.out.println("************ noneMatch() terminal operator to check if the number is prime");
        long n = 7;
        boolean isPrime = LongStream.iterate(2, i -> i < n, i -> i + 1)
                .noneMatch(i -> n % i == 0);
        System.out.println(isPrime);

        System.out.println("********** range() static method to produce a finite LongStream ****************");
        LongStream.range(0, 11).forEach(System.out::println); // [0, 10] — exclusive upper bound

        System.out.println("********** rangeClosed() static method to produce a finite LongStream ****************");
        LongStream.rangeClosed(0, 10).forEach(System.out::println); // [0, 10] — inclusive upper bound

        System.out.println("********* Total of Numbers using count() terminal operator");
        long totalOfItems = LongStream.of(1, 3, 6, 8, 10).count();
        System.out.println(totalOfItems);

        System.out.println("************** generate() — static method, creates infinite LongStream ***************");
        LongStream.generate(() -> (long) (Math.random() * 10))
                .limit(5)
                .forEach(System.out::println);

        System.out.println("******** iterate() — static method, creates infinite LongStream ****************");
        LongStream.iterate(0, t -> t + 1)
                .limit(5)
                .forEach(System.out::println);

        System.out.println(" ************** terminal operation min() — returns OptionalLong");
        OptionalLong longOptionalMin = LongStream.of(90, 110, 30, 10, 70, 150).min();
        longOptionalMin.ifPresent(t -> System.out.println(t));
        if (longOptionalMin.isPresent()) {
            long result = longOptionalMin.getAsLong();
            System.out.println(result);
        }

        System.out.println(" ************** terminal operation max() — returns OptionalLong");
        OptionalLong longOptionalMax = LongStream.of(90, 110, 30, 10, 70, 150).max();
        longOptionalMax.ifPresent(t -> System.out.println(t));
        if (longOptionalMax.isPresent()) {
            long result = longOptionalMax.getAsLong();
            System.out.println(result);
        }

        System.out.println(" ************** terminal operation average() returns OptionalDouble **************");
        OptionalDouble doubleOptionalAverage = LongStream.of(90, 110, 30, 10, 70, 150).average();
        doubleOptionalAverage.ifPresent(t -> System.out.println(t));
        if (doubleOptionalAverage.isPresent()) {
            double result = doubleOptionalAverage.getAsDouble();
            System.out.println(result);
        }

        System.out.println(" ************** summaryStatistics() — terminal operation with all stats");
        LongSummaryStatistics summaryStatistics = LongStream.of(90, 110, 30, 10, 70, 150).summaryStatistics();
        System.out.println("summaryStatistics getMax: " + summaryStatistics.getMax());
        System.out.println("summaryStatistics getAverage: " + summaryStatistics.getAverage());
        System.out.println("summaryStatistics getCount: " + summaryStatistics.getCount());
        System.out.println("summaryStatistics getMin: " + summaryStatistics.getMin());
        System.out.println("summaryStatistics getSum: " + summaryStatistics.getSum());

        System.out.println("*** boxed() — converts LongStream to Stream<Long>");
        Stream<Long> boxed = LongStream.of(1, 2, 3, 4, 6, 7).boxed();
        boxed.forEach(System.out::println);

        System.out.println("*** mapToObj() — converts LongStream to Stream<Long>");
        Stream<Long> longToObject = LongStream.of(1, 2, 3, 4, 6, 7).mapToObj(i -> i);
        List<Long> listLong = longToObject.collect(Collectors.toList());
        listLong.forEach(System.out::println);

        System.out.println("*** mapToLong() — converts Stream<String> to LongStream");
        Stream.of("Alex", "Richard", "Nick", "George")
                .mapToLong(String::length)
                .forEach(System.out::println);

        System.out.println("*** mapToLong() — converts Stream<String> to LongStream to use sum()");
        long totalChars = Stream.of("Alex", "Richard", "Nick", "George")
                .mapToLong(String::length)
                .sum();
        System.out.println(totalChars); // 21
    }
}