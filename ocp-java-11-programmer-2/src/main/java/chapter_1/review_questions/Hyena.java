package chapter_1.review_questions;

import java.util.function.Predicate;

/**
 * Question 17.
 * Letter A and F are correct.
 * Option A is a valid lambda expression. While main() is a static method, it can access age since it is using reference
 * to an instance of Hyena, which is effectively final in this method. Remember from your 1Zo-815 studies that var is a
 * reserved type, not a reserved word, and may be used for variable names. Option F is also correct, with the lambda
 * variable being a reference to a Hyena object. The variable is processed using deferred execution in the testLaugh()
 * method.
 *
 * Option B and E are incorrect; since the local variable age is not effectively final, this would lead to a compilation
 * error. Option C would also cause a compilation error, since the expression uses the variable name p, which is already
 * declared within the method. Finally, option D is incorrect, as this is not even a lambda expression.
 */
public class Hyena {

    private int age = 1;

    public static void main(String[] args) {
        var p = new Hyena();
        double height = 10;
        int age = 1;
        testLaugh(p, var -> p.age <= 10);
        testLaugh(p, h -> h.age < 5);
        age = 2;
    }

    static void testLaugh(Hyena panda, Predicate<Hyena> joke){
        var r = joke.test(panda) ? "hahaha" : "silence";
        System.out.println(r);
    }
}
