package chapter_4.reviewed_questions;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * What is the simplest way of rewriting this code?
 * Answer B.
 *
 * Both lists and streams have forEach() methods.
 * There is no reason to collect into a list just to loop through it.
 * Option A incorrect because it does not contain a terminal operation or print anything.
 * Options B and C both work.
 * However, the question asks about the simpliest way, which is option B.
 */
public class Question_21 {

    public static void main(String[] args) {

        initialCode();
        simpliestWay();
    }

    private static void initialCode() {
        List<Integer> x = IntStream.range(1, 6)
                .mapToObj(i -> i)
                .collect(Collectors.toList());

        x.forEach(System.out::println);
    }

    private static void simpliestWay() {
        IntStream.range(1, 6)
                .forEach(System.out::println);
    }

}
