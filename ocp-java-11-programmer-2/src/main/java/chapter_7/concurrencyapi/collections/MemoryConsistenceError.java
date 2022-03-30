package chapter_7.concurrencyapi.collections;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Using Concurrent Collection
 *
 * Besides, managing threads the Concurrency API includes interfaces and classes that help you coordinate access
 * to collections shared by multiple tasks.
 *
 * The purpose of the concurrent collection classes is to solve common memory consistency errors. A memory consistency
 * error occurs when two threads have inconsistent views of what should be the same data.
 * Conceptually, we want writes on one thread to be available to another thread if it accesses the concurrent collection
 * after the write has occurred.
 * When two threads try to modify the same nonconcurrent collection, the JVM may throw a ConcurrentModificationException
 * at runtime. In fact, it can happen with a single thread.
 *
 * The concurrent classes were created to help avoid common issues in which multiple threads are adding and removing
 * objects from the same collections. At any give instance, all threads should have the same consistence view of the
 * structure of the collection.
 *
 * You should use a concurrent class anytime that you are going to have multiple threads modify a collections object
 * outside a synchronized block or method, even if you don't expect a concurrency problem.
 *
 * In the same way that we instantiate an ArrayList object but pass around a List reference, it is considered a good
 * practice to instantiate a concurrent collection but pass it around using a nonconcurrent interface whenever possible.
 */
public class MemoryConsistenceError {

    public static void main(String[] args) {

        MemoryConsistenceError memoryConsistenceError = new MemoryConsistenceError();
        memoryConsistenceError.concurrentModificationException();
        memoryConsistenceError.concurrentHashMap();

    }

    /**
     * This snippet will throw a ConcurrentModificationException during the second iteration of the loop, since the
     * iterator on keySet() is not properly updated after the first element is removed.
     */
    private void concurrentModificationException(){
        var foodData = new HashMap<String, Integer>();
        foodData.put("penguin", 1);
        foodData.put("flamingo", 2);
        for (String key: foodData.keySet()){
            foodData.remove(key);
        }
    }

    private void concurrentHashMap(){
         var foodData = new ConcurrentHashMap<String, Integer>();
         foodData.put("penguin", 1);
         foodData.put("flamingo", 2);
         for (String key: foodData.keySet()){
             foodData.remove(key);
         }
    }
}
