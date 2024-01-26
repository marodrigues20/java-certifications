package chapter_5.reviewed_questions.question_22;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;


/**
 * 22. What is the output of the following code?
 *
 * Correct Answer. E
 *
 * Even though ltd has both a data and time, the formatter outputs only time.
 */
public class Question22 {

    public static void main(String[] args) {

        LocalDateTime ldt = LocalDateTime.of(2020, 5, 10, 11, 22, 33);
        var f = DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT);
        System.out.println(ldt.format(f));
    }
}
