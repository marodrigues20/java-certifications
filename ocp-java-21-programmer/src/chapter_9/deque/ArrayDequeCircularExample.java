package chapter_9.deque;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * ArrayDeque as a Double-Ended Queue (Deque)
 * Allows insertion and removal from both ends
 * ArrayDeque - Circular Array implementation
 * Insertions always happen at the last available physical index.
 * forEach iterates from head (last physical index) to tail (index 0).
 * Logically, head is the first element and tail is the last.
 * i.e:
 * Index[0]   = tail (logical end)
 * Index[n-1] = head (logical beginning)
 */
public class ArrayDequeCircularExample {

    public static void main(String[] args) {

        Deque<String> deque = new ArrayDeque<>();

        // ******** addFirst() / offerFirst() ***************
        System.out.println("************ Inserting at the BEGINNING (logically) ***************");
        System.out.println("addFirst() [Physically inserts at the last available index. Logically becomes the first element (head pointer moves)]");
        deque.addFirst("Richard");
        deque.addFirst("Alex");  // Alex becomes the first logically
        System.out.println("offerFirst() [Same as addFirst(). Returns true if successful] -> " + deque.offerFirst("July"));
        deque.forEach(System.out::println);

        // ******** addLast() / offerLast() ***************
        System.out.println("************ Inserting at the END ***************");
        System.out.println("addLast() [Physically inserts at index 0 (tail). Logically becomes the last element. Throws IllegalStateException if full — never happens with ArrayDeque]");
        deque.addLast("Rafael");
        System.out.println("offerLast() [Same as addLast(). Returns true if successful] -> " + deque.offerLast("Vania"));
        deque.forEach(System.out::println);

        // ******** peekFirst() / getFirst() ***************
        System.out.println("************ Checking the FIRST element ***************");
        System.out.println("peekFirst() [Returns first element without removing. Returns null if empty] -> " + deque.peekFirst());
        System.out.println("getFirst()  [Returns first element without removing. Throws NoSuchElementException if empty] -> " + deque.getFirst());

        // ******** peekLast() / getLast() ***************
        System.out.println("************ Checking the LAST element ***************");
        System.out.println("peekLast() [Returns last element without removing. Returns null if empty] -> " + deque.peekLast());
        System.out.println("getLast()  [Returns last element without removing. Throws NoSuchElementException if empty] -> " + deque.getLast());

        // ******** pollFirst() / removeFirst() ***************
        System.out.println("************ Removing from the BEGINNING ***************");
        System.out.println("pollFirst()  [Removes and returns first element. Returns null if empty] -> " + deque.pollFirst());
        System.out.println("removeFirst() [Removes and returns first element. Throws NoSuchElementException if empty] -> " + deque.removeFirst());
        deque.forEach(System.out::println);

        // ******** pollLast() / removeLast() ***************
        System.out.println("************ Removing from the END ***************");
        System.out.println("pollLast()  [Removes and returns last element. Returns null if empty] -> " + deque.pollLast());
        System.out.println("removeLast() [Removes and returns last element. Throws NoSuchElementException if empty] -> " + deque.removeLast());
        deque.forEach(System.out::println);
    }
}