package chapter_3.commoncollections;

import java.util.ArrayList;
import java.util.Collection;

/**
 * ConcurrentModificationException without Threads? This is the Java's way complaining that you are trying to
 * modify the list while looping through it. Chapter 7 shows how to fix it.
 */
public class DeletingWhileLoop {

    public static void main(String[] args) {

        Collection<String> birds = new ArrayList<>();
        birds.add("hawk");
        birds.add("hawk");
        birds.add("hawk");

        for (String bird: birds) {
            //ConcurrentModificationException
            birds.remove(bird);
        }

    }
}
