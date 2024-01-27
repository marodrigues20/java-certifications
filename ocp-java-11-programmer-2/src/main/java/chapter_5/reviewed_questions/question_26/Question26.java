package chapter_5.reviewed_questions.question_26;

/**
 * Which of the following are true of the code?
 * (Choose all that apply)
 *
 * Correct answer is: A and E
 *
 * Line 18 does not compile because assert is a keyword, making option A correct.
 * Options B and C are both incorrect because the parentheses and message are both optional.
 * Optional D is incorrect because assertions should never alter outcomes, as they may be disabled at runtime.
 * Option E is correct because checking an argument passed from elsewhere in the program is an appropriate use of an
 * assertion.
 */
public class Question26 {

    private int addPlusOne(int a, int b){
        //boolean assert = false;
        assert a++ > 0;
        assert b> 0;
        return a + b;

    }
}
