package chapter_3.commoncollections;

import java.util.*;
import java.util.function.BiFunction;

/**
 * HashMap is one of the implementation of Map. It stores the keys in a hash table.
 * It add elements and retrieve the element by key both have constant time.
 * The trade-off is that you lose the order in which you inserted the elements.
 * For keeping the order you could use LinkedHashMap out of exam.
 * The TreeMap stores the keys in a sorted tree structure. The key benefit is that the keys are always in sorted order.
 * The trade-off is that adding and checking whether a key is present takes longer as tree grows larger.
 */
public class MapInterfaceMethods {

    public static void main(String[] args) {
        //helperMethods();
        //hashMapExample();
        //treeMapExample();
        //forEachExample();
        //getOrDefaultExample();
        //replaceExamples();
        //pullIfAbsentExamples();
        //mergeExamples();
        //merge2Examples();
        merge3Example();
    }

    private static void merge3Example() {
        BiFunction<String, String, String> mapper = (v1, v2) -> v1.length() > v2.length() ? v1: v2;
        Map<String, String> favorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", "Bus Tour");
        favorites.merge("Jenny", "Skyride", mapper);
        favorites.merge("Sam", "Skyride", mapper); // It was added in the list because it wasn't in original list
        System.out.println(favorites);

    }

    private static void merge2Examples() {
        BiFunction<String, String, String> mapper = (v1, v2) -> v1.length() > v2.length() ? v1: v2;
        Map<String, String> favorites = new HashMap<>();
        favorites.put("Sam",null);
        favorites.merge("Tom", "Skyride", mapper);
        favorites.merge("Sam", "Skyride", mapper); // Not called otherwise we'll receive NullPointerException
        System.out.println(favorites);
    }

    private static void mergeExamples() {
        // The merge method add logic of what to choose.

        BiFunction<String, String, String> mapper = (v1, v2) -> v1.length() > v2.length() ? v1: v2;
        Map<String, String> favorites = new HashMap<>();
        favorites.put("Jenny","Bus Tour");
        favorites.put("Tom", "Tram");

        String jenny = favorites.merge("Jenny", "Skyride", mapper);
        String tom = favorites.merge("Tom", "Skyride", mapper);

        System.out.println(favorites);
        System.out.println(jenny);
        System.out.println(tom);

    }

    private static void pullIfAbsentExamples() {
        Map<String, String> favorites = new HashMap<>();
        favorites.put("Jenny", "Bus Tour");
        favorites.put("Tom", null);
        favorites.putIfAbsent("Jenny", "Tram"); // Not add because the Jenny has already existed.
        favorites.putIfAbsent("Sam", "Tram"); // Added
        favorites.putIfAbsent("Tom", "Tram"); //Added because the value was null
        System.out.println(favorites);



    }

    private static void replaceExamples() {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(1, 2);
        map.put(2, 4);
        Integer original = map.replace(2, 10); // Replace 2 by 10. Return the value replaced
        System.out.println(map);
        map.replaceAll((k,v) -> k + v); // Sum key and value and replace the original value for each key.
        System.out.println(map);
    }

    private static void getOrDefaultExample() {

        Map<Character, String> map = new HashMap<>();
        map.put('x', "spot");
        System.out.println("X marks the " + map.get('x'));
        System.out.println("X marks the " + map.getOrDefault('x',""));
        System.out.println("Y marks the " + map.get('y'));
        System.out.println("Y marks the " + map.getOrDefault('y',""));

    }

    private static void forEachExample() {
        Map<Integer, Character> map = new HashMap<>();
        map.put(1,'a');
        map.put(2,'b');
        map.put(3,'c');
        map.forEach((k,v) -> System.out.println(v));
        map.values().forEach(System.out::println);
        map.entrySet().forEach( e -> System.out.println(e.getKey() + e.getValue()));
    }

    private static void treeMapExample() {
        //TreeMap order by Key not Value
        Map<String, String> map = new TreeMap<>();
        map.put("koala", "bamboo");
        map.put("lion", "meat");
        map.put("giraffe", "leaf");
        String food = map.get("koala");
        for(String key: map.keySet()){
            System.out.println(key + " "); // giraffe koala lion
        }
    }

    private static void hashMapExample() {

        Map<String, String> map = new HashMap<>();
        map.put("koala", "bamboo");
        map.put("lion", "meat");
        map.put("giraffe", "leaf");
        String food = map.get("koala"); // bamboo
        for(String key: map.keySet()){
            System.out.println(key + " "); // koala, giraffe, lion
        }

        //System.out.println(map.contains("lion")); //NOT COMPILE. Method contains is just in Collection interface.
        System.out.println(map.containsKey("lion")); //true
        System.out.println(map.containsValue("lion")); //false
        System.out.println(map.size()); //3
        map.clear();
        System.out.println(map.size()); //0
        System.out.println(map.isEmpty()); //0

    }

    private static void helperMethods() {

        Map<String, String> myMap = Map.of("key1", "value1", "key2", "value2"); //Immutable object
        Map<String, String> myMap2 = Map.ofEntries(
                Map.entry("key1", "value1"),
                Map.entry("key2", "value2")); //Better way to build a new map. Less error prone.

        Map<String, String> myMap3 = Map.copyOf(myMap);
    }
}
