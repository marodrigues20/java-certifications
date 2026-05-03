package chapter_10.block_05;


import chapter_10.block_04.Person;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Collectors are just recipes that tell collect() how to group the results.
 * collect()            ← terminal operator
 * Collectors.toList()  ← recipe passed to collect()
 */
public class CollectorsExample {

    public static void main(String[] args) {

        System.out.println("************* joining() method - Joining Names without delimiters");
        String joiningNames = Stream.of("Michael", "July", "Rachel")
                .filter(name -> name.length() > 3)
                .collect(Collectors.joining());
        System.out.println("Joining Names without delimiters: " + joiningNames);

        System.out.println("************* joining() method - Joining Names with delimiters");
        String namesDelimiter = Stream.of("Michael", "July", "Rachel")
                .filter(name -> name.length() > 3)
                .collect(Collectors.joining(", "));
        System.out.println("Joining Names with delimiters: " + namesDelimiter);

        System.out.println("************* joining() method - Joining Names with prefix and suffix");
        String delimitersNames = Stream.of("Michael", "July", "Rachel")
                .filter(name -> name.length() > 3)
                .collect(Collectors.joining(", ", "[ ", " ]"));
        System.out.println("Joining Names with prefix and suffix: " + delimitersNames);

        System.out.println("************* toMap() method - If two keys clash the JVM will throw IllegalStateException");
        Map<String, String> toMap = Stream.of("Michael", "July", "Rachel")
                .collect(Collectors.toMap(t -> UUID.randomUUID().toString(), Function.identity()));
        System.out.println(toMap);

        System.out.println("************* toMap() method - Merge function: if keys clash, new value = existing + replacement");
        Map<Integer, String> toMap2 = Stream.of("Michael", "July", "Rachel")
                .collect(Collectors.toMap(String::length, Function.identity(), (existing, replacement) -> existing + replacement));
        System.out.println(toMap2);

        System.out.println("************ toList() method - mutable list ******************");
        List<String> toList = Stream.of("Michael", "July", "Rachel")
                .collect(Collectors.toList());
        System.out.println("toList Method: " + toList);

        System.out.println("************ averagingInt() method - average age of people ***************");
        List<Person> people = List.of(
                new Person(18, "Alex"),
                new Person(25, "Margaratti"),
                new Person(30, "Miguel")
        );
        Double averageAge = people.stream()
                .collect(Collectors.averagingInt(Person::age));
        System.out.println("Average age: " + averageAge);

        System.out.println("************ averagingInt() method - average of numbers ***************");
        Double average = Stream.of(1, 34, 54, 22, 9, 4, 80, 7)
                .collect(Collectors.averagingInt(i -> i));
        System.out.println("Average: " + average);

        System.out.println("*************** groupingBy() method ****************************");
        Map<Integer, List<String>> simpleGroupingBy = Stream.of("Michael", "July", "Rachel", "Mario", "Alex", "Rodrigues", "Ana")
                .collect(Collectors.groupingBy(String::length));
        System.out.println(simpleGroupingBy);

        System.out.println("************** mapping() method - groups first letter by name length *************************");
        Map<Integer, List<Character>> mapped = Stream.of("Alex", "Peter", "Arnold", "Ana")
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.mapping(
                                s -> s.charAt(0),
                                Collectors.toList()
                        )));
        System.out.println(mapped);

        System.out.println("************** partitioningBy() method - always two keys: true and false *************************");
        Map<Boolean, List<String>> partitionByName = Stream.of("Alex", "Peter", "Arnold", "Ana")
                .collect(Collectors.partitioningBy(t -> t.startsWith("A")));
        System.out.println(partitionByName);

        System.out.println("************** counting() method - standalone *************************");
        Long counting = Stream.of("Alex", "Peter", "Arnold", "Ana")
                .collect(Collectors.counting());
        System.out.println("Counting -> " + counting);

        System.out.println("************** counting() method - inside groupingBy() *************************");
        Map<Integer, Long> countByLength = Stream.of("Alex", "Peter", "Arnold", "Ana")
                .collect(Collectors.groupingBy(
                        String::length,
                        Collectors.counting()
                ));
        System.out.println(countByLength);

        System.out.println("************** averagingInt() method - inside groupingBy() *************************");
        List<Person> people2 = List.of(
                new Person(18, "Alex"),
                new Person(25, "Ana"),
                new Person(30, "Peter"),
                new Person(40, "Arnold")
        );
        Map<Integer, Double> avgAgeByNameLength = people2.stream()
                .collect(Collectors.groupingBy(
                        p -> p.name().length(),
                        Collectors.averagingInt(Person::age)
                ));
        System.out.println(avgAgeByNameLength);

        System.out.println("************** toCollection() method - TreeSet: sorted, no duplicates ***********************");
        TreeSet<String> treeSet = Stream.of("Alex", "Peter", "Arnold", "Ana", "Alex")
                .collect(Collectors.toCollection(TreeSet::new));
        System.out.println("TreeSet: " + treeSet);

        System.out.println("************** toCollection() method - LinkedList: maintains insertion order ***********************");
        LinkedList<String> linkedList = Stream.of("Alex", "Peter", "Arnold", "Ana")
                .collect(Collectors.toCollection(LinkedList::new));
        System.out.println("LinkedList: " + linkedList);

        System.out.println("************** toCollection() method - ArrayDeque ***********************");
        ArrayDeque<String> deque = Stream.of("Alex", "Peter", "Arnold", "Ana")
                .collect(Collectors.toCollection(ArrayDeque::new));
        System.out.println("ArrayDeque: " + deque);

        System.out.println("*********** toSet() method - no duplicates, no guaranteed order ***************************");
        Set<String> set = Stream.of("Alex", "Peter", "Arnold", "Ana", "Alex", "Ana")
                .collect(Collectors.toSet());
        System.out.println(set);
    }
}