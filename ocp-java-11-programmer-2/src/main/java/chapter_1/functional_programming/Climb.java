package chapter_1.functional_programming;

/**
 * It is a functional interface. Despite defining a slew of methods, it contains only one abstract method: reach().
 */
public interface Climb {

    void reach();

    default void fall(){}

    static int getBackUP(){
        return 100;
    }

    private static boolean checkHeight() {
        return true;
    }
}
