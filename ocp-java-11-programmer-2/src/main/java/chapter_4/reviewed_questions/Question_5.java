package chapter_4.reviewed_questions;

import java.util.stream.Collectors;
import java.util.stream.LongStream;

/**
 * Which of the following sets result to 8.0?
 *
 * Answer C, F.
 */
public class Question_5 {

    public static void main(String[] args) {

        answerC();
        answerF();
    }

    private static void answerC() {
        double result = LongStream.of(6L, 8L, 10L)
                .mapToInt(x -> (int) x)
                .boxed()
                .collect(Collectors.groupingBy(x -> x))
                .keySet()
                .stream()
                .collect(Collectors.averagingInt(x -> x));

        System.out.println(result);
    }

    private static void answerF() {
        double result = LongStream.of(6L, 8L, 10L)
                .mapToInt(x -> (int) x)
                .boxed()
                .collect(Collectors.groupingBy(x -> x,
                        Collectors.toSet()))
                .keySet()
                .stream()
                .collect(Collectors.averagingInt(x -> x));

        System.out.println(result);
    }
}
