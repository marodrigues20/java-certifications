package chapter_5.reviewed_questions.question_21;


/**
 * 21 - Which changes, when made independently, allow the following program to compile?
 * (Choose all that apply)
 *
 * Correct answers: C
 *
 * The code does not compile because the multi-catch on line 27 cannot catch both a superclass and a related subclass.
 *  Option A and B do not address this problem, so they are incorrect.
 *  Since the try body throws SneezeException, it can be caught in a catch block, making option C correct.
 *  Option D allows the catch block to compile but causes a compiler error on line 26.
 *  Both of the custom exceptions are checked and must be handled or declared in the main() method.
 *  A SneezeException is not a SniffleException, so the exception is not handled. Likewise, option E leads to an unhandled
 *  exception compiler error on line 26.
 */
public class AhChoo {

    static class SneezeException extends Exception {}

    static class SniffleException extends SneezeException {}

    public static void main(String[] args) {
        /*try{
            throw new SneezeException();
        } catch (SneezeException | SniffleException e){

        }finally {

        }*/
    }
}
