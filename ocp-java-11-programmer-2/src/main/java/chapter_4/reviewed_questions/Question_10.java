package chapter_4.reviewed_questions;

import org.w3c.dom.ls.LSOutput;

import java.util.stream.Stream;

/**
 * Given the four statements (L, M, N, O), select and order the ones that would complete the expression and cause
 * the code to output 10 lines. (Choose all that apply.)
 * Answer F.
 *
 *
 * Only one of the method calls, forEach(), is a terminal operation, so any answer in which M is not the last line will
 * not execute the pipeline.
 * This eliminates all but options C, E, and F. Option C is incorrect because filter() is called before limit(). Since
 * none of the elements of the stream meets the requirement for the Predicate<String>, the filter() operation will run
 * infinitely, never passing any elements to limit(). Option E is incorrect because there is no limit() operation,
 * which means that the code would run infinitely. Only option F is correct. It first limits the infinite stream to a
 * finite stream of 10 elements and then prints the result.
 *
 */
public class Question_10 {

    public static void main(String[] args) {
        Stream.generate(() -> "1")
                .limit(10)
                .forEach(System.out::println);
    }
}
