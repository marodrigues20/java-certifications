package chapter_5.reviewed_questions.question_7;


import java.text.DecimalFormat;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

/**
 * 7. For what value of pattern will the following print <005.21> <008.49> <1,234.0>
 * Correct answer is D
 *
 * When working with a custom number formatter, the 0 symbol display the digit as 0, even if it's not present,
 * while the #  symbol omits the digit from the start or end of the String if it is not present.
 * Based on the requested output, a String that displays at least three digits before the decimal (including a comma)
 * and at least one after three digits before the decimal (including a comma) and at least one after the decimal if one
 * is available. For this reason, option D is the correct answer. In case you are curious, option A display at most only
 * one value to the right of the decimal, printing <5.2> <8.5><1234>. Option B is closed to the correct answer but always
 * display four digitis to the left of the decimal, printing <0,005.21> <0,008.49><1,234.0>.
 * Finally, option C is missing the zeros padded to the left of the decimal and optional two values to the right of the
 * decimal, printing <5.2> <8.5> <1,234.0>
 *
 */
public class Question_7 {

    public static void main(String[] args) {
        String pattern = "#,###,000.0#";
        var message = DoubleStream.of(5.21, 8.49, 1234)
                .mapToObj(v -> new DecimalFormat(pattern).format(v))
                .collect(Collectors.joining("> <"));
        System.out.println("<"+message+">");
    }
}
