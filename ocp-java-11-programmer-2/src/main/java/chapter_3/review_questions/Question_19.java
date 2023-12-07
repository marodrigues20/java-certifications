package chapter_3.review_questions;


import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Question 19
 *
 * Correct A, D.
 *
 * A LinkedList implements both List and Queue. The List interface has a method to remove by index.
 * Since this method exists, Java does not autobox to call the other method. Queue has only the remove by
 * object method, so Java does autobox there. Since the number 1 is not in the list, Java does not remove.
 *
 */
public class Question_19 {



    public static void main(String[] args) {

        //List<Integer> q = new LinkedList<>(); //Option A  - output [10]
        Queue<Integer> q = new LinkedList<>();  //Option D - output [10, 12]
        q.add(10);
        q.add(12);
        q.remove(1);
        System.out.println(q);


    }
}
