package chapter_2.funcional_interface_annotation;


/**
 * The Reptile declaration does not compile, because the @FunctionalInterface annotation can be applied only to interfaces.
 */
//@FunctionalInterface
public abstract class Reptile {
    abstract String getName();
}
