package chapter_3.review_questions;

import java.util.HashMap;

/**
 * Question 11
 *
 * Correct is A.
 * Line 16 uses local variable type interface to create the map.
 * Line 18 and 19 use autoboxing to covert between the int primitive and the Integer wrapper class.
 * The keys map to their squared value. 1 maps to 1,2 maps to 4, 3 maps to 9, 4 maps to 16, and so on.
 */
public class Question_11 {
    public static void main(String[] args) {

        var map = new HashMap<Integer, Integer>(10);
        for (int i = 1; i <= 10; i++){
            map.put(i, i * i);
        }
        System.out.println(map.get(4));
    }
}
