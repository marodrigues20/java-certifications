package chapter_4.funcional.consumer;

import java.util.function.Consumer;

/**
 * We use a Consumer when we want to do something with a parameter but not return anything.
 */
public class ConsumerExamples {

    public static void main(String[] args) {

        Consumer<String> c1 = System.out::println;
        Consumer<String> c2 = x -> System.out.println(x);

        c1.accept("Annie");
        c2.accept("Annie");

    }
}
