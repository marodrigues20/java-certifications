package chapter_3.commoncollections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class RemoveIfCollection {

    public static void main(String[] args) {
        collectionArrayList();
        collectionSet();
    }

    /**
     * Line 23 shows how to remove all of the String values that begin with the letter A.
     * How would you replace line 23 with a method reference? Trick question - you can't.
     * The removeIf() method takes a Predicate. We can pass only one value with this method reference.
     * Since startsWith takes a literal, it needs to be specified "the long way.".
     */
    private static void collectionArrayList(){
        System.out.println("Starting collectionArrayList method.");
        Collection<String> list = new ArrayList<>();
        list.add("Magician");
        list.add("Assistant");
        System.out.println(list); // [Magician, Assistant]
        list.removeIf(s -> s.startsWith("A"));
        System.out.println(list); // [Magician]
    }

    private static void collectionSet(){
        System.out.println("Starting collectionSet method.");

        Collection<String> set = new HashSet<>();
        set.add("Wand");
        set.add("");
        set.removeIf(String::isEmpty); // s -> s.isEmpty()
        System.out.println(set); // [Wand]
    }
}
