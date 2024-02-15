package Appendix_A.review_question;


/**
 * 1. Which of the following code snippets about *var* compile without issue when used in a method?
 *    (Choose all that apply.)
 *
 *    Answer: B; D; E; H
 *
 *    A var cannot be initialized with a null value, but it can be assigned a null value if the underlying type is not a
 *    primitive.
 *    For these reasons, option H is correct, but options A and C are incorrect. Options B and D are correct as the
 *    underlying types are String and Object, respectively. Option E is correct, as this is a valid numeric expression.
 *    You might know that diving by zero produces a runtime exception, but the question was only about whether or not
 *    the code compile. Finally, options F and G are incorrect as var con not be used in a multiple variable assignment.
 */
public class Question_1 {

    public static void main(String[] args) {

        //var spring = null;  //A

        var fall = "leaves"; //B

        //var evening = 2; evening = null;  //C

        var night = new Object(); //D

        var day = 1/0; //E

        //var winter = 12, cold; //F

        //var fall = 2, autumn = 2; //G

        var morning = ""; morning = null; //H


    }



}
