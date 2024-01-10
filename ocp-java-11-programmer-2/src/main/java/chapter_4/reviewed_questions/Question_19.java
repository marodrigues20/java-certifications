package chapter_4.reviewed_questions;

import java.util.function.Function;

/**
 * What does the following code output?
 * Answer A.
 *
 * The a.compose(b) method calls the Function parameter b before the reference Function variable a.
 * In this case, that means that we multiply by 3 before adding 4.
 * This gives a result of 7, making option A correct.
 */
public class Question_19 {

    public static void main(String[] args) {

        Function<Integer, Integer> s = a -> a + 4;
        Function<Integer, Integer> t = a -> a * 3;
        Function<Integer, Integer> c = s.compose(t);
        System.out.println(c.apply(1));

    }
}
