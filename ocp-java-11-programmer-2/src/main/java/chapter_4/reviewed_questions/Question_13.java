package chapter_4.reviewed_questions;


import java.util.List;
import java.util.stream.Stream;

/**
 * Which of the following is true?
 * Answer F
 * The code does not compile
 *
 * if the map() and flatMap() calls were reversed, option B would be correct. In this case, the Stream created from the
 * source is of type Stream<List>. Trying to use the addition operator (+) on a List is not supported in Java. Therefore,
 * the code does not compile, and option F is correct.
 *
 */

public class Question_13 {

    public static void main(String[] args) {

        /*List<Integer> x1 = List.of(1, 2, 3);
        List<Integer> x2 = List.of(4, 5, 6);
        List<Integer> x3 = List.of();
        Stream.of(x1, x2, x3).map(x -> x + 1)
                .flatMap(x -> x.stream())
                .forEach(System.out::println);*/
    }
}
