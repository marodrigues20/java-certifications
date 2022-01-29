package chapter_3.commoncollections;

import java.util.ArrayList;
import java.util.Collection;

public class ContainsCollection {

    public static void main(String[] args) {
        Collection<String> birds = new ArrayList<>();
        birds.add("hawk"); //[hawk]
        System.out.println(birds.contains("hawk")); //true
        System.out.println(birds.contains("robin")); //false

    }
}
