package chapter_4.reviewed_questions;


import java.util.stream.Stream;

/**
 * 1. What could be the output of the following?
 *
 * Answer is D
 * No terminal operation is called, so the stream never executes.
 * The first line creates an infinite stream reference. If the stream were executed on the second line, it would get the
 * first two elements from that infinite stream, "" and "1", and add an extra character, resulting "2" and "12", res-
 * pectively. Since the stream is not executed, the reference is printed instead.
 */

public class Question_1 {
    public static void main(String[] args) {
        var stream = Stream.iterate("", (s) -> s + "1");
        System.out.println(stream.limit(2)
                .map(x -> x + "2"));
    }
}
