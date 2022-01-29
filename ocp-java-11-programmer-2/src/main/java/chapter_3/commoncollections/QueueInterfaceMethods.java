package chapter_3.commoncollections;

import java.util.LinkedList;
import java.util.Queue;

public class QueueInterfaceMethods {

    public static void main(String[] args) {

        Queue<Integer> queue = new LinkedList<>();
        System.out.println(queue.offer(10)); //true - Add an element to the back of the queue.
        System.out.println(queue.offer(4)); //true - Add an element to the back of the queue.
        System.out.println(queue.peek()); //10 - Return next element or return null if empty queue.
        //10 - Remove and returns next element or return null if empty queue. (consume the queue)
        System.out.println(queue.poll());
        System.out.println(queue.poll()); //4 -
        System.out.println(queue.peek()); //null


    }
}
