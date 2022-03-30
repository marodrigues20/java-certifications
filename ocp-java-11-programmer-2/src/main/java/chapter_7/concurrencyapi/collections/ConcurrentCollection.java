package chapter_7.concurrencyapi.collections;

import java.util.*;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * Concurrent Collection classes
 * <p>
 * Class name            | Java Collection Framework interfaces | Elements ordered? | Sorted? | Blocking? |
 * ConcurrentHashMap     | ConcurrentMap                        | No                | No      | No        |
 * ConcurrentLinkedQueue | Queue                                | Yes               | No      | No        |
 * ConcurrentSkipListMap | ConcurrentMap                        | Yes               | Yes     | No        |
 * SortedMap
 * NavigableMap
 * ConcurrentSkipListSet | SortedSet                            | Yes               | Yes      | No      |
 * NavigableSet
 * CopyOnWriteArrayList  | List                                 | Yes               | No       | No      |
 * CopyOnWriteArraySet   | Set                                  | No                | No       | No      |
 * LinkedBlockingQueue   | BlockingQueue                        | Yes               | No       | Yes     |
 *
 *
 * We use an interface reference for the variable type of the newly created object and use it the same way as
 * we would a nonconcurrent object. The difference is that these objects is that these objects are safe to
 * pass to multiple threads.
 */
public class ConcurrentCollection {

    public static void main(String[] args) {

        concurrentHashMap();
        concurrentLinkedQueue();
        concurrentSkipListSet();
        concurrentSkipListMap();
        copyOnWriteArrayList();
        copyOnWriteArraySet();
        linkedBlockingQueue();

    }

    /**
     * ConcurrentHashMap implements Map and ConcurrentMap. When declaring methods that take a concurrent collection,
     * it is up to you to determine the appropriate method parameter type. For example, a method signature may
     * require a ConcurrentMap reference to ensure that an object passed to it is properly supported in a
     * multithreaded environment.
     */
    private static void concurrentHashMap() {
        Map<String, Integer> map = new ConcurrentHashMap<>();
        map.put("zebra", 52);
        map.put("elephant", 10);
        System.out.println(map.get("elephant")); // 10
    }

    private static void concurrentLinkedQueue() {
        Queue<Integer> queue = new ConcurrentLinkedDeque<>();
        queue.offer(31);
        System.out.println(queue.peek());
        System.out.println(queue.poll());
    }

    /**
     * The SkipList classes, ConcurrentSkipListSet and ConcurrentSkipListMap, are concurrent versions of their
     * sorted counterparts, TreeSet and TreeMap, respectively.
     * When you see SkipList or SkipSet on the exam, just think "sorted" concurrent collection, and the rest
     * should follow naturally.
     */
    private static void concurrentSkipListSet() {
        Set<String> gardenAnimals = new ConcurrentSkipListSet<>();
        gardenAnimals.add("rabbit");
        gardenAnimals.add("gopher");
        System.out.println(gardenAnimals.stream()
                .collect(Collectors.joining(","))); // gopher, rabbit
    }

    private static void concurrentSkipListMap() {

        Map<String, String> rainForestAnimalDiet = new ConcurrentSkipListMap<>();
        rainForestAnimalDiet.put("koala", "bamboo");
        rainForestAnimalDiet.entrySet()
                .stream()
                .forEach((e) -> System.out.println(e.getKey() + "-" + e.getValue())); //koala-bamboo
    }

    /**
     * CopyOnWriteArrayList and CopyOnWriteArraySet. These classes copy all of their elements to a new underlying
     * structure anytime an element is added, modified, or removed from the collection. By a modified element,
     * we mean that the reference in the collection is changed. Modifying the actual contents of objects within a
     * collection will not cause a new structure to be allocated.
     * Although the data is copied to new underlying structure, our reference to the Collection object does not
     * change.
     *
     * The CopyOnWrite classes are similiar to the immutable object patterns.
     * The CopyOnWrite classes can use a lot of memory, since a new collection structure needs be allocated anytime
     * the collection is modified.
     */
    private static void copyOnWriteArrayList() {
        List<Integer> favNumbers = new CopyOnWriteArrayList<>(List.of(4, 3, 42));
        for (var n : favNumbers) {
            System.out.println(n + " ");
            favNumbers.add(9);
        }

        System.out.println();
        System.out.println("Size: " + favNumbers.size());

        //Alternatively we can use ArrayList class with an iterator
        List<Integer> birds2 = new ArrayList<>(List.of(4, 3, 42));
        var iterator = birds2.iterator();
        while(iterator.hasNext()){
            iterator.next();
            iterator.remove();
        }
    }

    private static void copyOnWriteArraySet() {
        Set<Character> favLetters = new CopyOnWriteArraySet<>(List.of('a', 't'));
        for (char c : favLetters) {
            System.out.println(c + " ");
            favLetters.add('s');
        }
        System.out.println();
        System.out.println("Size: " + favLetters.size());
    }

    /**
     * The BlockingQueue is just like a regular Queue, except that it includes methods that will wait a specific
     * amount of time to complete an operation.
     *
     * The implementation class LinkedBlockingQueue, as the name implies, maintains a linked list between elements.
     * The following sample is using a LinkedBlockingQueue to wait for the results of some of the operations.
     * The methods can each throw a checked InterruptedException, as they can be interrupted before they finish
     * waiting for a result; therefore, they must be properly caught.
     *
     * Method name | Description
     * offer(E e, long timeout, TimeUnit unit | Adds an item to the queue, waiting the specified time and returning
     *                                          false if the time elapses before space is available.
     *
     *
     * pool(long timeout, TimeUnit unit)  | Retrieves and removes an item from the queue, waiting the specified time
     *                                      and returning  null if the time elapses before the item is available.
     *
     */
    private static void linkedBlockingQueue() {
        try {
            var blockingQueue = new LinkedBlockingQueue<Integer>();
            blockingQueue.offer(39);
            blockingQueue.offer(3, 4, TimeUnit.SECONDS);
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll(10, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            // Handler interruption
        }

    }
}
