package chapter_10.block_01;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Stream<T> is a pipeline, not a data source.
 * Source (List, array, generator) → Stream pipeline → Terminal operation
 * <p>
 * iterate() and generate() are static factory methods on the Stream class used to create sources.
 * They are not "operators" — that term applies only to intermediate and terminal operations.
 */
public class StreamSource {

    public static void main(String[] args) {

        // stream() is a default method from the Collection interface.
        // It converts any Collection (List, Set, Queue) into a finite Stream.
        System.out.println("========== stream() — Collection to finite Stream ==========");
        List<String> listNames = List.of("Alex", "Richard", "Nick", "George");
        listNames.stream().forEach(System.out::println);

        // Stream.of() is a static factory method that creates a finite stream from fixed elements.
        System.out.println("========== of() — finite stream from fixed elements ==========");
        List<Integer> myStream = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .collect(Collectors.toList());

        // Stream.generate() creates an infinite stream.
        // findFirst() terminates it immediately, always returning a non-empty Optional.
        System.out.println("========== generate() + findFirst() — infinite stream terminated early ==========");
        Optional<UUID> myId = Stream.generate(() -> UUID.randomUUID()).findFirst();
        myId.ifPresent(System.out::println);

        // Stream.of() creates a finite stream with a single element.
        // startsWith("z") never matches — UUID is hexadecimal (chars 0-9 and a-f only).
        // findFirst() always returns Optional.empty() here.
        // orElseGet() is lazy — UUID.randomUUID() is only called when the Optional is empty.
        System.out.println("========== of() + filter() + orElseGet() — lazy fallback ==========");
        Optional<UUID> myEmpty = Stream.of(UUID.randomUUID())
                .filter(t -> t.toString().startsWith("z"))
                .findFirst();
        UUID uuid = myEmpty.orElseGet(UUID::randomUUID);
        System.out.println("Provided by orElseGet(): " + uuid);

        // Stream.iterate() with 2 args creates an infinite stream.
        // limit() is an intermediate operation that makes it finite (elements 0 to 10).
        // forEach() is the terminal operation that triggers the pipeline.
        System.out.println("========== iterate() 2-arg — infinite stream + limit() ==========");
        Stream.iterate(0, t -> t + 1)
                .limit(11)
                .forEach(System.out::println);

        // Stream.iterate() with 3 args: seed, Predicate (stop condition), UnaryOperator (next value).
        // Equivalent to: for (int i = 0; i < 10; i++)
        System.out.println("========== iterate() 3-arg — finite stream with stop condition (Java 9+) ==========");
        Stream.iterate(0, i -> i < 10, i -> i + 1)
                .forEach(System.out::println);

        // iterate() works with any type — the seed defines the Stream type.
        // String: starts with "a", stops when length reaches 5, appends "a" each step.
        System.out.println("========== iterate() 3-arg — String type ==========");
        Stream.iterate("a", s -> s.length() < 5, s -> s + "a")
                .forEach(System.out::println);

        // LocalDate: starts from today, stops after 5 days, advances one day per step.
        System.out.println("========== iterate() 3-arg — LocalDate type ==========");
        Stream.iterate(LocalDate.now(), date -> date.isBefore(LocalDate.now().plusDays(5)), date -> date.plusDays(1))
                .forEach(System.out::println);

        // BigDecimal: starts at 0.0, stops before 1.0, increments by 0.2 each step.
        System.out.println("========== iterate() 3-arg — BigDecimal type ==========");
        Stream.iterate(BigDecimal.ZERO, n -> n.compareTo(new BigDecimal("1.0")) < 0, n -> n.add(new BigDecimal("0.2")))
                .forEach(System.out::println);
    }
}