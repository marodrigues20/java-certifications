package chapter_1.interface_members.private_static_methods;


/**
 * The breathe() method is able to be called in the static butterfly() and freestyle() methods, as well as the default
 * backstroke() and private breaststroke() methods. Also, notice that butterfly() is assumed to be public static
 * without any access modifier. The rules for private static interface methods are nearly the same as the rules for
 * private interface methods.
 */
public interface Swim {

    private static void breathe(String type){
        System.out.println("Inhale");
        System.out.println("Performing stroke: " + type);
        System.out.println("Exhale");
    }

    static void butterfly() { breathe("butterfly"); }
    public static void freestyle() { breathe("freestyle"); }
    default void backstroke() { breathe("backstroke"); }
    private void breaststroke() { breathe("breaststroke");}


}
