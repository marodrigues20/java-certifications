package chapter_3.commoncollections;

import java.util.ArrayList;
import java.util.Collection;

public class CleanCollection {

    public static void main(String[] args) {

        Collection<String> birds = new ArrayList<>();
        birds.add("hawk"); // [hawk]
        birds.add("hawk"); //[hawk, hawk]
        System.out.println(birds.isEmpty()); // false
        System.out.println(birds.size()); // 2
        birds.clear();
        System.out.println(birds.isEmpty());
        System.out.println(birds.size()); // 0

    }
}
