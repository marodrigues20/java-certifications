package chapter_1.final_modifier;

/**
 * The name variable is assigned a value when it is declared, while the bamboo variable is assigned a
 * value in a static initializer.
 * The height variable is not assigned a value anywhere in the class definition, so that line does not compile.
 */
public class Panda {

    final static String name = "Ronda";
    static final int bamboo;

    //static final double height;  // DOES NOT COMPILE

    static {
        bamboo = 5;
    }
}
