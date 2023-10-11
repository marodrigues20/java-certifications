package chapter_1.functional_programming;


/**
 * In this example, the Sprint interface is a functional interface, because it contains exactly one abstract method,
 * and the Tiger class is a valid class that implements the interface.
 *
 * Note: @FunctionalInterface annotation is optional. Chapter 2 will cover the meaning of it.
 */
public class Tiger implements Sprint{
    @Override
    public void sprint(int speed) {
        System.out.println("Animal is sprinting fast! " + speed);
    }
}
