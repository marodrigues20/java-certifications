package chapter_3.review_questions;

import java.util.List;


/**
 * Correct answers: C and G.
 * Line 15 creates a List<?>, which means it is treated as if all the elements are of type
 * Object rather than String.
 * Line 20 and 21 do not compile since they call the String methods isEmpty() and length(),
 * which are not defined on Object.
 * Line 21 creates a List<String> because var uses the type that it deduces from the context.
 * Lines 25 and 26 do compile. However, List.of() creates an immutable list, so both of those
 * lines would throw an UnsupportedOperationException if run.
 * Therefore, options C and G are correct.
 */
public class Question_3 {
    public static void main(String[] args) {
        List<?> q = List.of("mouse","parrot");
        var v = List.of("mouse", "parrot");

        //q.removeIf(String::isEmpty);
        //q.removeIf(s -> s.length() == 4);
        v.removeIf(String::isEmpty);
        v.removeIf(s -> s.length() == 4);
    }
}
