package chapter_2.funcional_interface_annotation;


/**
 * Smooth interface contains two abstract methods, although since one matches since matches the signature of a method
 * in java.lang.Object, it does compile
 */
@FunctionalInterface
public interface Smooth extends Scaley{
    boolean equals(Object unused);
}
