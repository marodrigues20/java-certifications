package chapter_4.reviewed_questions;


import java.util.stream.DoubleStream;

/**
 * What is the result of the following?
 * Answer D.
 *
 * The terminal operation is count().
 * Since there is a terminal operation, the intermediate operations run.
 * The peek() operation comes before the filter(), so both numbers are printed.
 * After the filter(), the count() happens to be 1 since one of the numbers is filtered out.
 * However, the result of the stream pipeline isn't stored is in a variable or printed, and it is
 * ignored.
 */
public class Question_18 {

    public static void main(String[] args) {
        var s = DoubleStream.of(1.2, 2.4);
                s.peek(System.out::println)
                .filter(x -> x > 2)
                .count();
    }
}
