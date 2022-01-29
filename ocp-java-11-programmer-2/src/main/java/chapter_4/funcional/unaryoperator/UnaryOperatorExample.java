package chapter_4.funcional.unaryoperator;


import java.util.function.UnaryOperator;

/**
 * A UnaryOperator transform its value into one of the same type. It extends Function interface.
 */
public class UnaryOperatorExample {

    public static void main(String[] args) {

        UnaryOperator<String> u1 = String::toUpperCase;
        UnaryOperator<String> u2 = x -> x.toUpperCase();

        System.out.println(u1.apply("chirp"));
        System.out.println(u2.apply("chirp"));
    }
}
