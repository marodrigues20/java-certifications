package chapter_5.reviewed_questions.question_16;


/**
 * 16. Which of the following command lines cause this program to produce an error when executed?
 * (Choose all that apply.)
 *
 * Correct answers are: B and C.
 *
 * The code compiles, so option E is incorrect.
 * While it is a poor practice to modify variables in an assertion statement, it is allowed.
 * To enable assertions, use the flag -ea or -enableassertions.
 * To disable use the flag -da or -disableassertions.
 * The colon indicates a specific class.
 * Option A is incorrect, as assertions are already disabled by default.
 * Option B is correct because it turns on assertions for all classes (except system classes).
 * Option C is correct this class.
 * Finally, option D is incorrect as it enables assertions everywhere except the On class.
 *
 *
 * B = java -ea ON
 * C = java -da -ea:On On
 */
public class On {
    public static void main(String[] args) {
        String s = null;
        int check = 10;
        assert s != null : check++;
    }
}
