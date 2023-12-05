package chapter_3.review_questions;


import java.io.FileNotFoundException;

/**
 * Question 12
 *
 * Correct A, B and D.
 * The generic type must be Exception or a subclass of Exception since this is an upper bound.
 * Options C and E are wrong because Throwable is a superclass of Exception.
 * Option D uses an odd syntax by explicitly listing type, but you should be able to recognize it as acceptable
 *
 */
public class Helper {

    public static <U extends  Exception> void printException(U u){
        System.out.println(u.getMessage());
    }

    public static void main(String[] args) {
        Helper.printException(new FileNotFoundException("A"));

        Helper.printException(new Exception("B"));

        Helper.<NullPointerException>printException(new NullPointerException("D"));
    }
}
