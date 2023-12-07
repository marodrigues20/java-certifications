package chapter_3.review_questions;

/**
 * Question 17
 *
 * Correct B and D.
 *
 * Line 1 is a generic class that requires specifying a name for the type. Options A and C are incorrect because no type
 * is specified.
 * While you can use the diamond operator <> and the wildcard ? on variables and parameters, you cannot use them in a
 * class declaration. This means option B is the only correct answer for line 1.
 * Knowing the allows you to fill in line 3.
 * Option E is incorrect because T is not a class and certainly not one compatible with String.
 * Option F is incorrect because a wildcard cannot be specified on the right side when instantiating an object.
 * We're left with the diamond operator, making option D correct.
 */
//public class Generic {
public class Generic<T> { //Option B and

    public static void main(String[] args) {
        //Generic<String> g = new Generic<>(); Option A. Wrong
        //Generic<String> g = new Generic(); // Option B. We have to add <T>
        //Generic<String> g = new Generic<?>(); //Option C. Wrong
        Generic<String> g = new Generic<>(); // Option D
        //Generic<String> g = new Generic<T>(); //Option E. Wrong
        //Generic<String> g = new Generic<?>(); //Option F. Wrong
        Generic<Object> g2 = new Generic();
    }
}
