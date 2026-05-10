package chapter_9.factory_methods;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class MyExample {

    public static void main(String[] args) {

        System.out.println("************** Arrays.asList() method - Fixed size, partially mutable ****************");
        String[] names = new String[3];
        names[0] = "Alex";
        names[1] = "Rodrigues";
        names[2] = "Philip";
        List<String> nameList = Arrays.asList(names);
        nameList.set(1, "Jully");
        //nameList.add("Margareth"); // UnsupportedOperationException
        //nameList.remove("Alex");   // UnsupportedOperationException
        nameList.forEach(System.out::println);

        System.out.println();
        System.out.println("************** This change in the array affects the ArrayList because Arrays.asList() creates a view **********");
        names[0] = "Samantha";
        nameList.forEach(System.out::println);

        System.out.println();
        System.out.println("************** List.of() method - Immutable, does not allow null **************");
        // List.of() does not allow null elements — throws NullPointerException at runtime on creation
        // List<String> nullList = List.of("Alex", null, "Philip"); // NullPointerException at runtime
        List<String> immutableList = List.of("Elephant", "Tiger", "Lion", "Hippo");
        //immutableList.remove("Elephant"); // UnsupportedOperationException
        //immutableList.set(1, "Crocodile"); // UnsupportedOperationException
        //immutableList.add("Panda");        // UnsupportedOperationException
        immutableList.forEach(System.out::println);

        System.out.println();
        System.out.println("**************** List.copyOf() method - Immutable independent copy ********************");
        List<String> originalAnimals = new ArrayList<>(List.of("Elephant", "Tiger", "Lion", "Hippo"));
        List<String> listAnimals = List.copyOf(originalAnimals);
        //listAnimals.add("Bird");      // UnsupportedOperationException
        //listAnimals.remove("Lion");   // UnsupportedOperationException
        //listAnimals.set(0, "Snake");  // UnsupportedOperationException
        listAnimals.forEach(System.out::println);

        System.out.println();
        System.out.println("************** List.copyOf() is an independent copy — changes to original do NOT affect the copy **************");
        originalAnimals.add("Bird");
        System.out.println("originalAnimals: " + originalAnimals); // Bird appears
        System.out.println("listAnimals:     " + listAnimals);     // Bird does NOT appear

        System.out.println();

        System.out.println("************* Map.ofEntries() - Immutable Map used to more than 10 entries ************** ");
        Map<Integer, String> map = Map.ofEntries(
                Map.entry(1, "Alex"),
                Map.entry(2, "Robson"),
                Map.entry(3, "Philip")
        );

        //map.put(4, "Leonard");  // UnsupportedOperationException
        //map.remove(3); // UnsupportedOperationException

        System.out.println("************* Map.ofEntries() - Immutable Map Accept 10 pairs directly. ************** ");
        Map<Integer, String> map2 = Map.of(
                1, "Alex",
                2, "Robson",
                3, "Philip"
        );

        // map.put(4, "Mario"); // UnsupportedOperationException
        // map.remove(2); // UnsupportedOperationException
    }
}