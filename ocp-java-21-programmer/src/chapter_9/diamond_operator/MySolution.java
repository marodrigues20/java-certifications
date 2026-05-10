package chapter_9.diamond_operator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MySolution {

    public static void main(String[] args) {

        // Before Java 7 — type repeated on both sides
        List<String> list1 = new ArrayList<String>(); // verbose

        // Java 7+ — diamond operator infers the type from the left side
        List<String> list2 = new ArrayList<>(); // compiler infers <String>

        // Works with any generic type
        Map<String, List<Integer>> map = new HashMap<>(); // compiler infers <String, List<Integer>>

        // OCP trap — diamond operator is only valid on the RIGHT side
        // List<> list3 = new ArrayList<String>(); // Does not compile — <> not allowed on left side

        // Anonymous classes — diamond operator not allowed before Java 9
        // Java 9+ allows diamond operator with anonymous classes
        Comparable<String> comparable = new Comparable<>() {
            @Override
            public int compareTo(String o) {
                return 0;
            }
        };
    }
}