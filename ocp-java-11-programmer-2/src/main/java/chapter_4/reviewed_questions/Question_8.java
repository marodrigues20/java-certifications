package chapter_4.reviewed_questions;


import java.util.stream.IntStream;

/**
 * Which of the following are true given this declaration?
 * (Choose all that apply)
 *
 * Answer D, E.
 *
 * The average() method returns an OptionalDouble since averages of any type can result in a fraction.
 * Therefore, options A and B are both incorrect.
 * The findAny() method returns an OptionalInt because there might not be any elements to find.
 * Therefore, option D is correct.
 * The sum() method returns an int rather than an OptionalInt because the sum of an empty list is zero.
 * Therefore, option E is correct.
 */
public class Question_8 {

    public static void main(String[] args) {
        var is = IntStream.empty();
    }
}
