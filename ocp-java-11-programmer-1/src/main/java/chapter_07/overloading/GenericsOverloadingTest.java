package chapter_07.overloading;

import java.util.ArrayList;
import java.util.List;

public class GenericsOverloadingTest {

    // ❌ Descomente para ver o erro de compilação — type erasure!
    //public static void process(List<String> list)  { System.out.println("List<String>"); }
    //public static void process(List<Integer> list) { System.out.println("List<Integer>"); }

    // ✅ Overload válido — parâmetros extras diferentes
    public static void process(List<String> list, String s) {
        System.out.println("List<String> + String: " + s);
    }

    public static void process(List<Integer> list, int i) {
        System.out.println("List<Integer> + int: " + i);
    }

    public static void main(String[] args) {
        List<String> strings = new ArrayList<>();
        strings.add("Java");

        List<Integer> integers = new ArrayList<>();
        integers.add(11);

        process(strings, "OCP");   // List<String> + String: OCP
        process(integers, 42);     // List<Integer> + int: 42
    }
}
// Output:
// List<String> + String: OCP
// List<Integer> + int: 42
