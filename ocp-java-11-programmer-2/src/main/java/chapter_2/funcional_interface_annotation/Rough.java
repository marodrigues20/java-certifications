package chapter_2.funcional_interface_annotation;

/**
 * The Rough interface does not compile, because it contains two abstract methods, one of which
 * it inherits from Scaley
 */
//@FunctionalInterface
public interface Rough extends Scaley{
    void checkType();
}
