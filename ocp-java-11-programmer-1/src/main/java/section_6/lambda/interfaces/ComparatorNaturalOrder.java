package section_6.lambda.interfaces;

import java.util.Comparator;

public class ComparatorNaturalOrder {

    public static void main(String[] args) {

        Comparator<Integer> ints = (i1, i2) -> i1 - i2;
        Comparator<String> strings = (s1, s2) -> s2.compareTo(s1);
        Comparator<String> moreStrings = (s1, s2) -> -s1.compareTo(s2);

        compareThings(ints, 1, 10);
        compareDescendingStrings(strings, "Mario", "Alexandre");
        compareAscendingStrings(moreStrings, "Mario", "Alexandre");

    }

    private static void compareThings(Comparator<Integer> comparator, Integer number1, Integer number2) {
        System.out.println("Let's compare: " + comparator.compare(number1, number2));
    }

    private static void compareDescendingStrings(Comparator<String> comparator, String val1, String val2) {
        System.out.println("Let's see Descending: " + comparator.compare(val1, val2));
    }

    private static void compareAscendingStrings(Comparator<String> comparator, String val1, String val2) {
        System.out.println("Let's see Ascending: " + comparator.compare(val1, val2));
    }

}
