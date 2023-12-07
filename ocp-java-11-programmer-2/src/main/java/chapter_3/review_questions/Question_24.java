package chapter_3.review_questions;

import java.util.Set;

/**
 * Question 24
 * Correct is F.
 *
 * The first two lines correctly create a Set and make a copy of it.
 * Option A is incorrect because forEach takes a Consumer parameter, which requires one parameter.
 * Option B and C are close. The syntax for a lambda is correct. However, s is already defined as a local variable,
 * and therefore the lambda can't redefine it.
 * Option D and E use incorrect syntax for a method reference.
 * Option F is correct.
 *
 */
public class Question_24 {

    public static void main(String[] args) {
        Set<?> set = Set.of("lion", "tiger", "bear");
        var s = Set.copyOf(set);
        s.forEach(System.out::println);
    }
}
