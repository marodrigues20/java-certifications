package chapter_1.anonymous_classes;


/**
 * You can even define anonymous classes outside a method body. The following may look like we are instantiating an interface
 * as an instance variable, but the {} after the interface name indicates that this is an anonymous inner class implementing the
 * interface.
 */
public class Gorilla {
    interface Climb{}
    Climb climbing = new Climb() {};

}
