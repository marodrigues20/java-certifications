package chapter_4.funcional.binaryoperator;


import java.util.function.BinaryOperator;

/**
 * A BinaryOperator merges two values into one of the same type. Adding two numbers is a binary operation.
 * It extends BiFunction Interface.
 */
public class BinaryOperatorExample {

    public static void main(String[] args) {

        BinaryOperator<String> b1 = String::concat;
        BinaryOperator<String> b2 = (string, toAdd) -> string.concat(toAdd);

        System.out.println(b1.apply("baby","chick"));
        System.out.println(b2.apply("baby","chick"));


    }
}
