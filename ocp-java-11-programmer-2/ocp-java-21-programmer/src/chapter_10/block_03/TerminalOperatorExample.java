package chapter_10.block_03;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TerminalOperatorExample {

    public static void main(String[] args) {

        System.out.println("************* Method count() terminal operator ******************");
        long countLength = Stream.of("Alex", "Peter", "Arnold")
                .filter(t -> t.startsWith("A"))
                .count();
        System.out.println(countLength);

        System.out.println("************* Method max() terminal operator [Order: lexicographic - same as dictionary] ******************");
        Optional<String> maxOptional = Stream.of("Alex", "Peter", "Arnold")
                .max(Comparator.naturalOrder());
        String maxName = maxOptional.isPresent() ? maxOptional.get() : null;
        System.out.println(maxName); // Peter

        System.out.println("************* Method max() terminal operator [Order: length of word] ******************");
        Optional<String> maxLengthName = Stream.of("Alex", "Peter", "Arnold")
                .max(Comparator.comparing(String::length));
        String maxResult = maxLengthName.isPresent() ? maxLengthName.get() : null;
        System.out.println(maxResult); // Arnold

        System.out.println("************* Method min() terminal operator [Order: lexicographic - same as dictionary] ******************");
        Optional<String> minOptional = Stream.of("Alex", "Peter", "Arnold")
                .min(Comparator.naturalOrder());
        String minName = minOptional.isPresent() ? minOptional.get() : null;
        System.out.println(minName); // Alex

        System.out.println("************* Method min() terminal operator [Order: length of word] ******************");
        Optional<String> minLengthName = Stream.of("Alex", "Peter", "Arnold")
                .min(Comparator.comparing(String::length));
        String minResult = minLengthName.isPresent() ? minLengthName.get() : null;
        System.out.println(minResult); // Alex

        System.out.println(" *************** findFirst() terminal operator *************");
        Optional<Integer> findFirst = Stream.iterate(1, i -> i + 1)
                .findFirst();
        Integer findFirstResult = findFirst.isPresent() ? findFirst.get() : null;
        System.out.println(findFirstResult); // 1

        System.out.println(" *************** findAny() terminal operator *************");
        // findAny() is meaningful in parallel streams — returns any element without order guarantee
        Optional<Integer> findAny = Stream.iterate(1, i -> i + 1)
                .findAny();
        Integer findAnyResult = findAny.isPresent() ? findAny.get() : null;
        System.out.println(findAnyResult); // 1 (sequential stream)

        System.out.println(" *************** allMatch() terminal operator *************");
        boolean allMatch = Stream.of("Alex", "Rodrigues", "Felipe", "Ana", "Julia")
                .allMatch(t -> t.startsWith("Ju"));
        System.out.println(allMatch); // false

        System.out.println(" *************** anyMatch() terminal operator *************");
        boolean anyMatch = Stream.of("Alex", "Rodrigues", "Felipe", "Ana", "Julia")
                .anyMatch(t -> t.startsWith("Ju"));
        System.out.println(anyMatch); // true

        System.out.println(" *************** noneMatch() terminal operator *************");
        boolean noneMatch = Stream.of("Alex", "Rodrigues", "Felipe", "Ana", "Julia")
                .noneMatch(t -> t.startsWith("Ju"));
        System.out.println(noneMatch); // false

        System.out.println(" *************** forEach() terminal operator *************");
        Stream.of("Alex", "Rodrigues", "Felipe", "Ana", "Julia")
                .forEach(System.out::println);

        System.out.println(" *************** reduce() terminal operator *************");
        // Use reduce() when you need to collapse the stream into a single value
        // and no other terminal operator covers your case.
        Optional<String> names = Stream.of("Alex", "Peter", "Arnold")
                .reduce((accumulator, current) -> accumulator + ", " + current);
        System.out.println(names.get()); // Alex, Peter, Arnold

        System.out.println(" *************** collect() terminal operator *************");
        List<String> list = Stream.of("Alex", "Peter", "Arnold")
                .collect(Collectors.toList());
        list.forEach(System.out::println);
    }
}