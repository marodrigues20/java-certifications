package chapter_3.review_questions;


import java.util.HashMap;

/**
 * Question 25
 *
 * Correct is F.
 *
 * The first call to merge() calls the mapping function and adds the two numbers to get 13.
 * It then updates the map.
 * The second call to merge() sees that the map currenlty has a null value for that key.
 * It does not call the mapping function but instead replaces it with the new value of 3.
 * Therefore, option F is correct.
 */
public class Question_25 {

    public static void main(String[] args) {

        var map = new HashMap<Integer, Integer>();
        map.put(1, 10);
        map.put(2, 20);
        map.put(3, null);
        map.merge(1,3, (a, b) -> a + b);
        map.merge(3,3, (a, b) -> a + b);
        System.out.println(map); // {1=13, 2=20, 3=3}
    }

}
