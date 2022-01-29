package chapter_3.commoncollections;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;

public class SetInterfaceMethods {

    public static void main(String[] args) {

        //copyAndOfExamples();
        hashSetExamples();
        treeSetExamples();
    }

    private static void copyAndOfExamples() {
        // Create a immutable Set but in this case throws exception.
        // IllegalArgumentException: duplicate element: o
        Set<Character> letters = Set.of('z','o','o');
        Set<Character> copy = Set.copyOf(letters); // Make a copy of Set
        System.out.println(copy);

    }

    private static void treeSetExamples() {

        Set<Integer> set = new TreeSet<>();
        boolean b1 = set.add(66); //true
        boolean b2 = set.add(10); //true
        boolean b3 = set.add(66); //true
        boolean b4 = set.add(8); //true
        set.forEach(System.out::println); // Printed in natural sorted order.
    }

    private static void hashSetExamples() {

        Set<Integer> set = new HashSet<>();
        boolean b1 = set.add(66);  // true
        boolean b2 = set.add(10);  // true
        boolean b3 = set.add(66);  // false
        boolean b5 = set.add(8);   // true
        set.forEach(System.out::println); // No ordered.
    }
}
