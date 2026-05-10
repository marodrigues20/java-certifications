package chapter_9.method_reference;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;

public class MethodReferenceExample {

    public static void main(String[] args) {

        // 1. Static method — ClassName::staticMethod
        System.out.println("************ Static Method Reference — Integer::parseInt ***************");
        List<String> numbers = List.of("1", "2", "3", "4");
        numbers.stream()
                .map(Integer::parseInt) // equivalent: s -> Integer.parseInt(s)
                .forEach(System.out::println);

        // 2. Instance method on a particular object — instance::method
        System.out.println("************ Instance Method on Particular Object — System.out::println ***************");
        List<String> nameList = new ArrayList<>(List.of("Alex", "Bob", "David", "Xaquira"));
        nameList.forEach(System.out::println); // System.out is the particular instance

        // 3. Instance method on a parameter — ClassName::instanceMethod
        System.out.println("************ Instance Method on Parameter — String::length ***************");
        nameList.stream()
                .map(String::length) // equivalent: s -> s.length()
                .forEach(System.out::println);

        // 4. Constructor reference — ClassName::new
        System.out.println("************ Constructor Reference — ArrayList::new ***************");
        Supplier<List<String>> listSupplier = ArrayList::new; // equivalent: () -> new ArrayList<>()
        List<String> newList = listSupplier.get();
        newList.add("Alex");
        newList.add("Bob");
        newList.forEach(System.out::println);
    }
}