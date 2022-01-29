package chapter_3.commoncollections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class removeIfCollection {

    public static void main(String[] args) {
        collectionArrayList();
        collectionSet();
    }

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
