package chapter_1.questions.question16;

/**
 * Question 16
 * Letters A, D and E are correct.
 * A valid functional interface is one that contain a single abstract method, excluding any public methods that are
 * already defined in the java.lang.Object class. Transport and Boat are valid functional interfaces, as they each
 * contain a single abstract method: go() and hashCode(String), respectively. Since the other methods are part of
 * Object, they do not count as abstract methods. Train is also a functional interface since it extends Transport and
 * does not define any additional abstract methods.
 */
public interface Boat {
    int hashCode();
    int hashCode(String input);
}
