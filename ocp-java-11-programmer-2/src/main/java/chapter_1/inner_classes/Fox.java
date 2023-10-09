package chapter_1.inner_classes;

public class Fox {

    private class Den{

    }

    /**
     * Compile because goHome() is an instance method, is an instance method, and therefore the call is associated
     * with this instance.
     */
    public void goHome(){
        new Den();
    }

    /**
     * It is called inside a static method.
     * You can still call the constructor, but you have to explicitly give it a reference to a Fox instance.
     */
    public static void visitFriend(){
        //new Den();  // DOES NOT COMPILE
    }
}

