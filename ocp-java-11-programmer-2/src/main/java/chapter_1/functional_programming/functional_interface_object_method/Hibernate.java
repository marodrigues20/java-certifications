package chapter_1.functional_programming.functional_interface_object_method;


/**
 * Despite looking a lot like our Dive interface, the Hibernate uses equals(Hibernate) instead of equals(Object).
 * Because this does not match the method signature of the equals(Object) method defined in the Object class, this
 * interface is counted as containing two abstract methods: equals(Hibernate) and rest().
 */
public interface Hibernate {

    String toString();
    public boolean equals(Hibernate o);
    public abstract int hashCode();
    public void rest();

}
