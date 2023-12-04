package chapter_1.review_questions;


/**
 * Question 21.
 * Letters B, E are correct.
 * Like classes, interface allow instance methods to access static members, but not vice versa. Non-static private,
 * abstract, and default methods are considered instance methods in interfaces. Line 3 does not compile because the
 * static method hunt() cannot access an abstract instance method getName(). Line 6 does not compile because the
 * private static method sneak() cannot access the private instance method roar(). The rest of the lines compile
 * without issue.
 */
public interface BigCat {
    abstract String getName();
    //static int hunt(){ getName(); return 5; }
    default void climb() { rest(); }
    //private void roar() { getName(); climb(); hunt(); }
    //private static boolean snead() { roar(); return true;}
    private int rest() {
        return 2;
    }
}
