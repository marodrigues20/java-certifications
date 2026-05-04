package chapter_9.queue;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;


/**
 * PriorityQueue não é uma lista ordenada - é um heap binário (min-heap)
 * A única garantia é o poll() sempre retorna o menor elemento. O resto fica organizado internamente para satisfazer
 * essa regra, não para ficar em ordem visual.
 *
 * Quando você chama add("Margarethe"), ela é inserida e o heap rebalanceia para manter a propriedade de heap - não
 * para ordenar tudo alfabeticamente. O forEach percorre o array interno do heap, que não é ordenado.
 *
 * Methods that throw exception vs methods that return null/false:
 * add()     x offer()
 * remove()  x poll()
 * element() x peek()
 */
public class PriorityQueueExample {

    public static void main(String[] args) {


        List<String> list = List.of("Alex", "Richard", "Rafael", "July");
        // Ordem alfabética (lexicográfia)
        Queue<String> queue = new PriorityQueue<>(list);

        System.out.println("******** peek() method ***************");
        System.out.println("peek() [Just give a quick look at the root node (smallest element). If queue is empty returns null] -> " + queue.peek());
        queue.forEach(System.out::println);

        System.out.println("******** poll() method ***************");
        System.out.println("poll() [Removes and returns the root node (smallest element). If queue is empty returns null] -> " + queue.poll());
        queue.forEach(System.out::println);

        System.out.println("******** offer() method ***************");
        System.out.println("offer() [Inserts element and rebalances the heap. Returns true if successful, false if capacity is full] -> " + queue.offer("Vania"));
        queue.forEach(System.out::println);

        System.out.println("******** add() method ***************");
        System.out.println("add() [Inserts element and rebalances the heap. Throws IllegalStateException if capacity is full. Not the case for PriorityQueue] -> " + queue.add("Margarethe"));
        queue.forEach(System.out::println);

        System.out.println("******** remove() method ***************");
        System.out.println("remove() [Removes and returns the root node (smallest element). Throws NoSuchElementException if queue is empty] -> " + queue.remove());
        queue.forEach(System.out::println);

        System.out.println("******** element() method ***************");
        System.out.println("element() [Same as peek() but throws NoSuchElementException if queue is empty] -> " + queue.element());
        queue.forEach(System.out::println);

        System.out.println("******** poll() in loop — natural order (smallest to largest) ***************");
        while (!queue.isEmpty()) {
            System.out.println(queue.poll());
        }

        System.out.println("******** PriorityQueue with custom Comparator ***************");
        Queue<String> byLength = new PriorityQueue<>(Comparator.comparingInt(String::length));
        byLength.add("Richard");
        byLength.add("Alex");
        byLength.add("July");
        byLength.add("Vania");
        System.out.println("poll() in loop — ordered by name length (shortest to longest):");
        while (!byLength.isEmpty()) {
            System.out.println(byLength.poll());
        }
    }
}