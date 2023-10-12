package chapter_1.functional_programming.defining_functional_interface;

/**
 * It is not a valid functional interface. Neither snore() nor getZzz() meet the criteria of a single abstract method.
 * Even though default methods function like abstract methods, in that they can be overridden in a class implementing
 * the interface, they are insufficient for satisfying the single abstract method requirement.
 */
public interface Sleep {

    private void snore(){}

    default int getZzz() {
        return 1;
    }
}
