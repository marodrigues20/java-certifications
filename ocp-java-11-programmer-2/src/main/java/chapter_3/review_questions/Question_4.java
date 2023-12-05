package chapter_3.review_questions;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Question 4
 * Correct is D.
 * This is a FIFO (first-in, first-out) queue.
 * On line 25, we remove the first element added, which is "hello".
 * On line 26, we look at the new first element ("hi") but don't remove it.
 * On line 27 and 28, we remove each element in turn until no elements are left, printing hi and
 * ola together.
 * Note that we don't use an Iterator to loop through the LinkedList to avoid concurrent modification issues.
 * The order to loop through the LinkedList to avoid concurrent modification issues.
 * The order in which the elements are stored internally is not part ot the API contract.
 */
public class Question_4 {

    public static void main(String[] args) {

        var greetings = new LinkedList<String>();
        greetings.offer("hello");
        greetings.offer("hi");
        greetings.offer("ola");
        greetings.pop();
        greetings.peek();
        while (greetings.peek() != null) {
            System.out.println(greetings.pop());
        }
    }
}
