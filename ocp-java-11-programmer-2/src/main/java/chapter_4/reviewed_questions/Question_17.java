package chapter_4.reviewed_questions;


import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 * Which of the following is equivalent to this code? (Choose all that apply.)
 * Answer: E
 *
 * The question starts with a UnaryOperator<Integer>, which takes one parameter and returns a value of the same type.
 * Therefore, option E is correct, as UnaryOperator actually extends Function.
 * Notice that other options don't even compile because they have the wrong number of generic types for the functional
 * interface provided.
 * You should know that a BiFunction<T,U,R> takes three generic arguments, a BinaryOperator<T> takes one generic
 * argument, and a Function<T,R> takes two generic arguments.
 *
 */
public class Question_17 {

    public static void main(String[] args) {
        originalCode();
        equivalentCode();
    }

    private static void originalCode() {
        UnaryOperator<Integer> u = x -> x * x;
    }

    private static void equivalentCode(){
        Function<Integer, Integer> f = x -> x * x;
    }
}
