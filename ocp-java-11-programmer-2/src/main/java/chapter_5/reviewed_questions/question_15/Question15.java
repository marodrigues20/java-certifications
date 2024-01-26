package chapter_5.reviewed_questions.question_15;


import java.time.format.DateTimeFormatter;

/**
 * Which of the following can be inserted into the blank to allow the code to compile and run
 * without throwing an exception? (Choose all that apply)
 *
 * Correct is F
 *
 * The code compiles, but the first line produces a runtime exception regardless of what is inserted into the blank.
 * When creating a custom formatter, any nonsymbol code must be properly escaped using pairs of single quotes(').
 * In this case, it fails because o is not a symbol. Even if you didn't know a wasn't a symbol, the code contains an
 * unmatched single quote. if the properly escaped value of "hh' o''clock;'" was used, then the correct answers would be
 * ZonedDateTime, LocalDateTime, and LocalTime.
 * Option B would not be correct because LocalDate values do not have an hour part.
 *
 */
public class Question15 {

    public static void main(String[] args) {
        var f = DateTimeFormatter.ofPattern("hh o'clock");
        //System.out.println(f.format());
    }
}
