package chapter_4.funcional.biconsumer;

import java.util.HashMap;
import java.util.function.BiConsumer;

/**
 *  We use a BiConsumer when we want to do something with two parameter but not return anything.
 */
public class BiConsumerExample {

    public static void main(String[] args) {
        var map = new HashMap<String, Integer>();
        BiConsumer<String, Integer> b1 = map::put;
        BiConsumer<String, Integer> b2 = (k, v) -> map.put(k,v);

        b1.accept("chicken", 1);
        b2.accept("chick", 1);

        System.out.println(map);

        var map2 = new HashMap<String, String>();
        BiConsumer<String, String> b3 = map2::put;
        BiConsumer<String, String> b4 = (k, v) -> map2.put(k,v);

        b3.accept("chicken", "Cluch");
        b4.accept("chick", "Tweep");

        System.out.println(map2);
    }


}
