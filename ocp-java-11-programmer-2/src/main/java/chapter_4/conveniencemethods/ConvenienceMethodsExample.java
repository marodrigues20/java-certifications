package chapter_4.conveniencemethods;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class ConvenienceMethodsExample {

    public static void main(String[] args) {

        //It works but not great. It's a bit long to read
        Predicate<String> egg = s -> s.contains("egg") && s.contains("brown");
        Predicate<String> otherString = s -> s.contains("egg") && !s.contains("brown");

        //Predicate<String> brownEggs2 = egg.and(brownEggs);
        //Predicate<String> otherEggs2 = egg.and(brown.negate());

        Consumer<String> c1 = x -> System.out.println("1: " + x);
        Consumer<String> c2 = x -> System.out.println(",2:" + x);

        //andThen run our code in sequence.
        Consumer<String> combined = c1.andThen(c2);
        combined.accept("Annie");

        Function<Integer, Integer> before = x -> x + 1;
        Function<Integer, Integer> after = x -> x * 2;

        //The result of before will feed the compose method to be used by after method reference.
        Function<Integer, Integer> combined2 = after.compose(before);
        System.out.println(combined2.apply(3));



    }
}
