package chapter_1.review_questions.question24;


/**
 * Question 24
 * Letter E is the correct.
 * Option A does not compile because the second statement within the block is missing a semicolon (;) at the end.
 * Option B is an invalid lambda expression because t is defined twice: in the parameter list and within the lambda
 * expression.
 * Options C and D are both missing a return statement and semicolon.
 * Option E and F are both valid lambda expression, although only option E matches the behavior of the Sloth class.
 * In particular, option F only prints Sleep:, not Sleep 10.0
 *
 */
public class Vet {

    public static String takeNap(Yawn y){
        return y.yawn(10, null);
    }

    public static void main(String... unused){
        System.out.println(takeNap(new Sloth()));

        // Substitui the line 17
        System.out.println(takeNap((a,b) -> "Sleep: " + (double)(b==null ? a : a)));
    }
}
