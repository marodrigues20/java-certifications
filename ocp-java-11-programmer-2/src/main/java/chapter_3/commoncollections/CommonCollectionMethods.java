package chapter_3.commoncollections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

public class CommonCollectionMethods {

    public static void main(String[] args) {

        addCollection();
        removeCollection();
    }

    private static void removeCollection() {
        System.out.println("Starting removeCollection methods. ");

        Collection<String> birds = new ArrayList<>();
        birds.add("hawk");
        birds.add("hawk");
        System.out.println(birds.remove("cardinal")); //false
        System.out.println(birds.remove("hawk")); //true
        System.out.println(birds);



    }

    private static void addCollection(){
        System.out.println("Starting addCollection methods. ");

        Collection<String> list = new ArrayList<>();
        System.out.println(list.add("Sparrow")); //true
        System.out.println(list.add("Sparrow")); //true

        Collection<String> set = new HashSet<>();
        System.out.println(set.add("Sparrow")); //true
        System.out.println(set.add("Sparrow")); //false Not allowed duplicated items

    }
}
