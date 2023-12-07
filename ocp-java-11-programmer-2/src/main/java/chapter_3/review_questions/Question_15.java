package chapter_3.review_questions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * Question 15
 *
 * Correct is A.
 *
 * When using binarySearch(), the List must be sorted in the same order that the Comparator uses.
 * Since the binarySearch() method does not specify a Comparator explicitly, the default sort order is used.
 * Only c2 uses that sort order and correctly identifies that the value 2 is at index 0.
 * Therefore, option A is correct. The other two comparators sort in descending order.
 * Therefore, the precondition for binarySearch() is not met, and the result is undefined for those two.
 */
public class Question_15 {



    public static void main(String[] args) {

        Comparator<Integer> c1 = (o1, o2) -> o2 - o1;
        Comparator<Integer> c2 = Comparator.naturalOrder();
        Comparator<Integer> c3 = Comparator.reverseOrder();

        var list = Arrays.asList(5, 4, 7, 2);
        Collections.sort(list, c3);
        System.out.println(Collections.binarySearch(list, 2));



    }
}
