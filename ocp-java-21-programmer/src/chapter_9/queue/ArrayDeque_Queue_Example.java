package chapter_9.queue;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class ArrayDeque_Queue_Example {

    public static void main(String[] args) {

        List<String> listForQueue = List.of("Alex", "Richard", "Rafael", "July");
        Deque<String> queue = new ArrayDeque<>(listForQueue);

        // Deque como Queue (FIFO) - Insere no final, remove do início
        // offer(), poll(), peek()
        System.out.println("************ Using ArrayDeque as a Queue (FIFO) ***************");
        System.out.println("offer() method to add a new person in the end of the queue -> " + queue.offer("Me"));

        System.out.println("************ Checking the current Queue State *****************");
        queue.forEach(t -> System.out.println("Let's see the current queue state in the post office: " + t));

        System.out.println("Checking who is the person that will be attended in the post office ***************");
        System.out.println("peek() method to give a look at the next person that will be attended to the cashier: " + queue.peek());

        System.out.println("***************** Calling to be attended in the Post Office **********");
        System.out.println("poll() method will call the person at the beginning of the queue and person will leave the queue to be attended: " + queue.poll());

        System.out.println("************ Checking the current Queue State *****************");
        queue.forEach(t -> System.out.println("Let's see the current queue state in the post office: " + t));

        // Methods that throw exception
        // add(), remove(), element()
        System.out.println("************ Methods that throw exception ***************");

        System.out.println("add() [Inserts at the end of the queue. Throws IllegalStateException if capacity is full] -> " + queue.add("Vania"));

        System.out.println("************ Checking the current Queue State *****************");
        queue.forEach(t -> System.out.println("Let's see the current queue state in the post office: " + t));

        System.out.println("element() [Same as peek() but throws NoSuchElementException if queue is empty] -> " + queue.element());

        System.out.println("remove() [Removes and returns the first element. Throws NoSuchElementException if queue is empty] -> " + queue.remove());

        System.out.println("************ Checking the current Queue State *****************");
        queue.forEach(t -> System.out.println("Let's see the current queue state in the post office: " + t));

    }
}