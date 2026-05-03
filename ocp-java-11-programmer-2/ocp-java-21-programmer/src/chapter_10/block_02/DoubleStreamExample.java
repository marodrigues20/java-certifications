package chapter_10.block_02;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.Stream;

/**
 * DoubleStream does not have range() and rangeClosed() because these methods need a natural increment between two values.
 *
 * With integers and longs the next value is obvious:
 * 5 → 6 → 7 → 8 ...
 *
 * With doubles there is no natural next value:
 * 5.0 → 5.1? → 5.01? → 5.001? → 5.0000001?
 *
 * How many doubles exist between 0.0 and 1.0? Infinite — it depends on the precision you choose.
 * That is why it is not possible to define a discrete range for DoubleStream.
 *
 * If you need a range with doubles, use iterate() and define the increment yourself:
 * DoubleStream.iterate(0.0, d -> d < 1.0, d -> d + 0.1)
 */
public class DoubleStreamExample {

    public static void main(String[] args) {

        System.out.println(" ************** sum() terminal operator over DoubleStream");
        double sum = DoubleStream.iterate(0, i -> i <= 10, i -> i + 1).sum();
        System.out.println(sum);

        System.out.println(" ************** filter() intermediate operator over DoubleStream to check the number is even");
        DoubleStream.iterate(0, i -> i <= 10, i -> i + 1)
                .filter(t -> t % 2 == 0)
                .forEach(System.out::println);

        System.out.println("************ noneMatch() terminal operator to check if the number is prime");
        double n = 7;
        boolean isPrime = DoubleStream.iterate(2, i -> i < n, i -> i + 1)
                .noneMatch(i -> n % i == 0);
        System.out.println(isPrime);

        System.out.println("********* Total of Numbers using count() terminal operator");
        long totalOfItems = DoubleStream.of(1.0, 3.0, 6.0, 8.0, 10.0).count();
        System.out.println(totalOfItems);

        System.out.println("************** generate() — static method, creates infinite DoubleStream ***************");
        DoubleStream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);

        System.out.println("******** iterate() — static method, creates infinite DoubleStream ****************");
        DoubleStream.iterate(0.0, t -> t + 0.5)
                .limit(5)
                .forEach(System.out::println);

        System.out.println(" ************** terminal operation min() — returns OptionalDouble");
        OptionalDouble doubleOptionalMin = DoubleStream.of(90.0, 110.0, 30.0, 10.0, 70.0, 150.0).min();
        doubleOptionalMin.ifPresent(t -> System.out.println(t));
        if (doubleOptionalMin.isPresent()) {
            double result = doubleOptionalMin.getAsDouble();
            System.out.println(result);
        }

        System.out.println(" ************** terminal operation max() — returns OptionalDouble");
        OptionalDouble doubleOptionalMax = DoubleStream.of(90.0, 110.0, 30.0, 10.0, 70.0, 150.0).max();
        doubleOptionalMax.ifPresent(t -> System.out.println(t));
        if (doubleOptionalMax.isPresent()) {
            double result = doubleOptionalMax.getAsDouble();
            System.out.println(result);
        }

        System.out.println(" ************** terminal operation average() returns OptionalDouble **************");
        OptionalDouble doubleOptionalAverage = DoubleStream.of(90.0, 110.0, 30.0, 10.0, 70.0, 150.0).average();
        doubleOptionalAverage.ifPresent(t -> System.out.println(t));
        if (doubleOptionalAverage.isPresent()) {
            double result = doubleOptionalAverage.getAsDouble();
            System.out.println(result);
        }

        System.out.println(" ************** summaryStatistics() — terminal operation with all stats");
        DoubleSummaryStatistics summaryStatistics = DoubleStream.of(90.0, 110.0, 30.0, 10.0, 70.0, 150.0).summaryStatistics();
        System.out.println("summaryStatistics getMax: " + summaryStatistics.getMax());
        System.out.println("summaryStatistics getAverage: " + summaryStatistics.getAverage());
        System.out.println("summaryStatistics getCount: " + summaryStatistics.getCount());
        System.out.println("summaryStatistics getMin: " + summaryStatistics.getMin());
        System.out.println("summaryStatistics getSum: " + summaryStatistics.getSum());

        System.out.println("*** boxed() — converts DoubleStream to Stream<Double>");
        Stream<Double> boxed = DoubleStream.of(1.0, 2.0, 3.0, 4.0, 6.0, 7.0).boxed();
        boxed.forEach(System.out::println);

        System.out.println("*** mapToObj() — converts DoubleStream to Stream<Double>");
        Stream<Double> doubleToObject = DoubleStream.of(1.0, 2.0, 3.0, 4.0, 6.0, 7.0).mapToObj(i -> i);
        List<Double> listDouble = doubleToObject.collect(Collectors.toList());
        listDouble.forEach(System.out::println);

        System.out.println("*** mapToDouble() — converts Stream<String> to DoubleStream");
        Stream.of("Alex", "Richard", "Nick", "George")
                .mapToDouble(String::length)
                .forEach(System.out::println);

        System.out.println("*** mapToDouble() — converts Stream<String> to DoubleStream to use sum()");
        double totalChars = Stream.of("Alex", "Richard", "Nick", "George")
                .mapToDouble(String::length)
                .sum();
        System.out.println(totalChars); // 21.0
    }
}