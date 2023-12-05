package chapter_3.review_questions;


import java.util.HashMap;
import java.util.Map;

/**
 * Question 9
 * Correct is E.
 * The map interface uses put() rather than add() to add elements to the map. If these examples used put(), the answer
 * would be options A and C.
 * Option B is no good because a long cannot be placed inside a Double without an explicit cast.
 * Option D is no good because a char is not the same thing as a String.
 *
 */
public class Question_9 {


    public static void main(String[] args) {

        Map<String, Double> map = new HashMap<>();

        //map.add("pi", 3.14159);

        //map.add("e", 2L);

        //map.add("log(1)", new Double(0.0));

        //map.add('x', new Double(123.4));
    }
}
