package chapter_1.review_questions;

/**
 * Question 18.
 * Letter C, D, G are correct.
 * Option C is the correct way to create an instance of an inner class Cub using an instance of the outer class Lion.
 * The syntax looks weird, but it creates an object of the outer class and then an object of the inner class from it.
 * Options A, B and E use incorrect syntax for creating an instance of the Cub class. Options D and G are the correct
 * way to create an instance of the static nested Den class, as it does not require an instance of Lion, while option F
 * uses invalid syntax. Finally, option H is incorrect since it lacks an instance of Lion. If rest() where an instance
 * method instead of a static method, then option H would be correct.
 */
public class Lion {

    /**
     * Inner Class
     */
    class Cub{

    }

    /**
     * Nested Class
     */
    static class Den {

    }

    static void rest(){

        Lion.Cub c = new Lion().new Cub();

        var d = new Den();

        Lion.Den g = new Lion.Den();
    }
}
