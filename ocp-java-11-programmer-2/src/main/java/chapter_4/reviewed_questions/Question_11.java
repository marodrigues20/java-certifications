package chapter_4.reviewed_questions;


import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * What changes need to be made together for this code to print the string 12345?
 * (Choose all that apply)
 * Answer: B, C, E.
 *
 *
 *
 */
public class Question_11 {

    public static void main(String[] args) {
        System.out.println(Stream.iterate(1, x -> ++x)
                .limit(5)
                .map(x -> "" + x)
                .collect(Collectors.joining()));
    }
}
