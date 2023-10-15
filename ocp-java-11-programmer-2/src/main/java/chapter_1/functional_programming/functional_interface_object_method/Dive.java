package chapter_1.functional_programming.functional_interface_object_method;


/**
 * The dive() method is the single abstract method, while the others are not counted since they are public methods
 * defined in the Object class.
 */
public interface Dive {
    String toString();
    public boolean equals(Object o);
    public abstract int hashCode();
    public void dive();
}
