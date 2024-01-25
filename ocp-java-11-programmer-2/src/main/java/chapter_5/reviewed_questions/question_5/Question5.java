package chapter_5.reviewed_questions.question_5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**
 * What is the output of the following code?
 *
 * Correct is E
 *
 * A LocalDate does not have a time element. Therefore, a Date/Time formatter is not approprieate.
 * The code compiles but throws an exception at runtime.
 * If ISO_LOCAL_DATE was used, then the code would compile and option B (2020 APRIL 30) would be the correct answer.
 */
public class Question5 {

    public static void main(String[] args) {

        LocalDate date = LocalDate.parse("2020-04-30",
                DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        System.out.println(date.getYear() + " "
            + date.getMonth() + " " + date.getDayOfMonth());
    }
}
