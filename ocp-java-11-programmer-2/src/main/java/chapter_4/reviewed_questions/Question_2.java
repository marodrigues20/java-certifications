package chapter_4.reviewed_questions;


import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/**
 * What could be the output of the following?
 *
 * Answer F.
 *
 * Both streams created in this code snippet are infinite streams.
 * The variable b1 is set to true since anyMatch() terminates. Even though the stream is infinite, Java finds a match on
 * the first element and stops looking. However, when allMatch() runs, it needs to keep going until the end of the stream
 * since it keeps finding matches. Since all elements continue to match, the program hangs.
 */
public class Question_2 {

    public static void main(String[] args) {

        Predicate<String> predicate = s -> s.startsWith("g");
        var stream1 = Stream.generate(() -> "growl!");
        var stream2 = Stream.generate(() -> "growl!");
        var b1 = stream1.anyMatch(predicate);
        var b2 = stream2.allMatch(predicate);
        System.out.println(b1 + " " + b2);
    }
}
