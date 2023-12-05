package chapter_3.review_questions;


import java.util.HashSet;
import java.util.Iterator;

/**
 * Question 7
 *
 * Correct A and D.
 * The code compiles fine. It allows any implementation of Number to be added.
 * Lines 21 and 24 successfully autobox the primitives into a Integer and Long, respectively.
 * HashSet does not guarantee any iteration order, making options A and D correct.
 */
public class Question_7 {

    public static void main(String[] args) {

        var numbers = new HashSet<Number>();
        numbers.add(Integer.valueOf(86));
        numbers.add(75);
        numbers.add(Integer.valueOf(86));
        numbers.add(null);
        numbers.add(309L);
        Iterator iter = numbers.iterator();
        while (iter.hasNext())
            System.out.println(iter.next());

    }
}
