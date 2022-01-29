package chapter_4.funcional.function;


import java.util.function.Function;

/**
 * A Function is responsible for turning one parameter into a value of a different type and returning it.
 */
public class FunctionExample {

    public static void main(String[] args) {

        Function<String, Integer> f1 = String::length;
        Function<String, Integer> f2 = x -> x.length();

        System.out.println(f1.apply("cluck"));
        System.out.println(f2.apply("cluck"));

    }
}
