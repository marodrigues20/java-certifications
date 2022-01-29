package chapter_3.generics;

/**
 * More examples of generics methods
 */
public class More {

    // Shows the formal parameter type immediately before the return type of void.
    public static <T> void sink(T t){

    }

    // Shows the return type being the formal parameter type.
    public static <T> T identity(T t) {
        return t;
    }

    // Omits the formal parameter type, and therefore it does not compile
    //public static T noGood(T t){ return t; } // DOES NOT COMPILE

    // Optional Syntax for invoking a Generic method.
    //Box.<String>ship("package");
    //Box.<String[]>ship(args);
}
