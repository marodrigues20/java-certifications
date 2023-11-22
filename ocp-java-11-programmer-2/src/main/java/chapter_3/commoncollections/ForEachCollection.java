package chapter_3.commoncollections;

import java.util.Arrays;
import java.util.Collection;

public class ForEachCollection {

    public static void main(String[] args) {
        Collection<String> cats = Arrays.asList("Annie", "Ripley");
        cats.forEach(System.out::println);
        cats.forEach(c -> System.out.println(c));
    }
}
