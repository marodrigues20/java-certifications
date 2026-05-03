package chapter_10.block_06;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Java21Extras {

    public static void main(String[] args) {

        // ============================================================
        // Stream.toList() — Java 16+
        // Returns an IMMUTABLE list — no add() or remove() allowed
        // ============================================================
        System.out.println("************** Stream.toList() — immutable list (Java 16+) **************");
        List<String> immutableList = Stream.of("Alex", "Peter", "Arnold")
                .toList();
        System.out.println(immutableList);
        // immutableList.add("test"); // throws UnsupportedOperationException


        // ============================================================
        // takeWhile() — Java 9+
        // Keeps elements WHILE predicate is true — stops at first false
        // ============================================================
        System.out.println("************** takeWhile() — stops at first false (Java 9+) **************");
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .takeWhile(n -> n < 5)
                .forEach(System.out::println);
        // 1, 2, 3, 4


        // ============================================================
        // dropWhile() — Java 9+
        // Drops elements WHILE predicate is true — keeps the rest
        // ============================================================
        System.out.println("************** dropWhile() — drops while true, keeps the rest (Java 9+) **************");
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .dropWhile(n -> n < 5)
                .forEach(System.out::println);
        // 5, 6, 7, 8, 9, 10


        // ============================================================
        // mapMulti() — Java 16+
        // Alternative to flatMap — more flexible, push-based
        // ============================================================
        System.out.println("************** mapMulti() — alternative to flatMap (Java 16+) **************");
        Stream.of("Alex", "Peter", "Arnold")
                .<String>mapMulti((name, consumer) -> {
                    consumer.accept(name.toUpperCase());
                    consumer.accept(name.toLowerCase());
                })
                .forEach(System.out::println);
        // ALEX, alex, PETER, peter, ARNOLD, arnold


        // ============================================================
        // Optional.ifPresentOrElse() — Java 9+
        // if/else for Optional in one line
        // ============================================================
        System.out.println("************** Optional.ifPresentOrElse() — Java 9+ **************");
        Optional<String> present = Optional.of("Alex");
        Optional<String> empty = Optional.empty();

        present.ifPresentOrElse(
                v  -> System.out.println("Found: " + v),
                () -> System.out.println("Not found")
        ); // Found: Alex

        empty.ifPresentOrElse(
                v  -> System.out.println("Found: " + v),
                () -> System.out.println("Not found")
        ); // Not found


        // ============================================================
        // Optional.or() — Java 9+
        // Fallback to another Optional if empty
        // ============================================================
        System.out.println("************** Optional.or() — fallback Optional (Java 9+) **************");
        Optional<String> result = empty
                .or(() -> Optional.of("default value"));
        System.out.println(result.get()); // default value


        // ============================================================
        // Optional.stream() — Java 9+
        // Converts Optional to Stream — 0 or 1 element
        // ============================================================
        System.out.println("************** Optional.stream() — converts Optional to Stream (Java 9+) **************");
        long count = Stream.of(
                        Optional.of("Alex"),
                        Optional.empty(),
                        Optional.of("Peter")
                )
                .flatMap(Optional::stream)  // empty Optionals are discarded
                .count();
        System.out.println("Non-empty optionals: " + count); // 2


        // ============================================================
        // Collectors.teeing() — Java 12+
        // Applies TWO collectors to the same stream and combines results
        // ============================================================
        System.out.println("************** Collectors.teeing() — two collectors, one result (Java 12+) **************");
        Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
                .collect(Collectors.teeing(
                        Collectors.summingInt(Integer::intValue),  // collector 1 — sum
                        Collectors.counting(),                      // collector 2 — count
                        (sum, count2) -> "Sum: " + sum + ", Count: " + count2
                ))
                .describeConstable()
                .ifPresent(System.out::println);
        // Sum: 55, Count: 10
    }
}
