package chapter_7.concurrencyapi.collections;

import java.util.Collections;
import java.util.HashMap;

/**
 * Obtain Synchronized Collection
 *
 * Besides the concurrent collection classes that we covered, the Concurrency API also includes methods for obtaining
 * synchronized versions of existing nonconcurrent collection object. These synchronized methods are defined in the
 * Collections class.
 *
 * Methods:
 * synchronizedCollection(Collection<T> c)
 * synchronizedList(List<T> list)
 * synchronizedMap(Map<K,V> m)
 * synchronizedNavigableMap(NavigableMap<K,V> m)
 * synchronizedNavigableSet(NavigableSet<T> s)
 * synchronizedSet(Set<T> s)
 * synchronizedSortedMap(SortedMap<K,V> m)
 * synchronizedSortedSet(SortedSet<T> s)
 *
 * When should you use these methods?
 * If you know at the time of creating that your objects require synchronization, then you should use one of the concurrent
 * collection classes listed above. On the other hand, if you are given an existing collection that is not a concurrent
 * class and need to access it among multiple threads, you can wrap it using the methods.
 * Unlike the concurrent collections, the synchronized collection also throw an exception if they are modified within
 * an iterator by a single thread.
 *
 * This loops throws a ConcurrentModificationException, whereas our example that used ConcurrentHashMap did not.
 */
public class SyncCollectionMethods {

    public static void main(String[] args) {
        var foodData = new HashMap<String,Object>();
        foodData.put("penguin", 1);
        foodData.put("flaming", 2);
        var synFoodData = Collections.synchronizedMap(foodData);
        for(String key: synFoodData.keySet()){
            synFoodData.remove(key);
        }
    }
}
