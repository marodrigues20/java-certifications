package chapter_3.review_questions;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Question 21
 * Correct is A and E.
 *
 * The key to this question is keeping track of the types.
 * Line 26 is a Map<Integer, Integer>.
 * Line 27 builds a List out of a Set of Entry objects, giving us List<Entry<Integer, Integer>>.
 * This causes a compile error on line 34 since we can't multiply an Entry object by two.
 *
 * Line 29 through 32 are all of type List<Integer>. The first three are immutable, and the one on line 32 is immutable.
 * This means line 35 throws an UnsupportedOperationException since we attempt to modify the list.
 * Line 36 would work if we could get to it.
 * Since there is one compiler error and one runtime error, options A and E are correct.
 *
 */
public class Question_21 {

    public static void main(String[] args) {

        var map = Map.of(1, 2, 3, 6);
        var list = List.copyOf(map.entrySet());

        List<Integer> one = List.of(8, 16, 2);
        var copy = List.copyOf(one);
        var copyOfCopy = List.copyOf(copy);
        var thirdCopy = new ArrayList<>(copyOfCopy);

        //list.replaceAll(x -> x * 2);
        one.replaceAll(x -> x * 2);  //Exception in thread "main" java.lang.UnsupportedOperationException
        thirdCopy.replaceAll(x -> x * 2);

        System.out.println(thirdCopy);
    }
}
