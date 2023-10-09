package chapter_1.inner_classes;


/**
 * An inner class declaration looks just like a stand-alone class declaration except that it happens to be located inside
 * another class.
 * Since an inner class is not static, it has to be used with an instance of the outer class.
 * Both Inner and callInner() are member of Outer. Since they are peers, they just write the name.
 */
public class Outer {

    private String greeting = "Hi";

    protected class Inner {
        public int repeat = 3;

        public void go() {
            for (int i = 0; i < repeat; i++) {
                System.out.println(greeting);
            }
        }
    }

    public void callInner(){
        Inner inner = new Inner();
        inner.go();
    }

    /**
     * Regular way to call the Inner class.
     */
    public static void main(String[] args){
        Outer outer = new Outer();
        outer.callInner();
    }


    /**
     * There is another way to instantiate Inner that looks odd at first. Ok, well maybe not just at first. This syntax
     * isn't used often enough to get used to it:
     */
    /*public static void main(String[] args){
        Outer outer = new Outer();
        Inner inner = outer.new Inner(); // create the inner class
        inner.go();
    }*/
}
