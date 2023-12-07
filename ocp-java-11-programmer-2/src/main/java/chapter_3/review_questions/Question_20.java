package chapter_3.review_questions;

import java.util.HashMap;
import java.util.Map;


/**
 * Question 20
 *
 * Correct is E.
 *
 * This question looks like it is about generics, but it's not. It is trying to see whether you notice
 * that Map does not have a contains() method. It has containsKey() and containsValue() instead. If containsKey()
 * was called, the answer would be false because 123 is an Integer key in the Map, rather than a String.
 */
public class Question_20 {

    public static void main(String[] args) {

        Map m = new HashMap();
        m.put(123,"456");
        m.put("abc", "def");
        //System.out.println(m.contains("123"));
    }
}
