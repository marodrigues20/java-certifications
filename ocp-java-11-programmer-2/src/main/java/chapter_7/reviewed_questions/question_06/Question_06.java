package chapter_7.reviewed_questions.question_06;

import java.util.List;

public class Question_06 {

    public static void main(String[] args) throws Exception {
        var data = List.of(2, 5, 1, 9, 8);
        data.stream().parallel()
                .mapToInt(s -> s)
                .peek(System.out::println)
                .forEachOrdered(System.out::println);
    }
}
