package chapter_3.review_questions;


/**
 * Question 22
 *
 * Correct is B
 *
 * When using generic types in a method, the generic specification goes before the return type.
 */
public class Question_22 {

    public static <T> T identity(T t){
        return t;
    }
}
