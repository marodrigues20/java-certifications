package chapter_1.interface_members.static_methods;

public class Bunny implements Hop {

    /**
     * If we call getJumpHeight() without Hop the code will not compile, even though Bunny implements Hop.
     */
    public void printDetails(){
        System.out.println(Hop.getJumpHeight());
    }
}
