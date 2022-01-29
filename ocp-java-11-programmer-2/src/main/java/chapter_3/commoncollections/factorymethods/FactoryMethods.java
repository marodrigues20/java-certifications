package chapter_3.commoncollections.factorymethods;

import java.util.Arrays;
import java.util.List;

public class FactoryMethods {

    public static void main(String[] args) {

        String[] array = new String[] {"a", "b", "c"};
        List<String> asList = Arrays.asList(array); //[a, b, c] - Reference the array variable in memory
        List<String> of = List.of(array); // [a, b, c] - Return immutable list
        List<String> copy = List.copyOf(asList); // [a, b, c] - copyOf return immutable list
        array[0] = "z";
        System.out.println(asList); //[z, b, c]
        System.out.println(of); //[a, b, c]
        System.out.println(copy); //[a, b, c]

        asList.set(0, "x");
        System.out.println(Arrays.toString(array)); //[x, b, c]

        copy.add("y"); // UnsupportedOperationException
    }
}
