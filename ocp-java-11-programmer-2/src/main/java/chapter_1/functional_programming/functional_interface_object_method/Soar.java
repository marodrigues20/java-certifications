package chapter_1.functional_programming.functional_interface_object_method;

/**
 * Is the Soar class a functional interface?
 * It is not. Since toString() is a public method implemented in Object, it does not count towards the single
 * abstract method test.
 */
public interface Soar {
    abstract String toString();
}
