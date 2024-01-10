package chapter_4.reviewed_questions;

import java.util.stream.Stream;


/**
 * Which of the following can fill in the blank so that the code prints out false?
 * (Choose all that apply.)
 * Answer A.
 *
 * Options C and D do not compile because these methods do not take a Predicate and do not return a boolean.
 * When working with streams, it is important remember the behavior of the underlying functional interfaces.
 * Options B and E are incorrect it is safe to return false as soon as one element passes through the stream that doesn't
 * match.
 *
 *
 * A. allMatch
 * B. anyMatch
 * C. findAny
 * D. findFirst
 * E. noneMatch
 * F. None of the above
 */
public class Question_6 {
    public static void main(String[] args) {
        var s = Stream.generate(() -> "meow");
        var match = s.allMatch(String::isBlank);
        System.out.println(match);
    }
}
