package chapter_8.print;

public class FormatSample {

    public static void main(String[] args) {
        //format();
        //format2();
        //printException();
        format3();
    }


    /**
     * In the second format() operation, the parameters are inserted and formatted via symbols in the order that
     * they are provided in the vararg.
     */
    static void format(){
        String name = "Lindsey";
        int orderId = 5;

        // Both print: Hello Lindsey, order 5 is ready
        System.out.format("Hello " + name + ", order " + orderId + " is ready. ");
        System.out.format("Hello %s, order %d is ready.", name, orderId);
    }

    /**
     * The following example uses all four symbols.
     */
    static void format2(){
        String name = "James";
        double score = 90.25;
        int total = 100;

        System.out.format("%s:%nScore: %f out of %d", name, score, total);
    }

    /**
     * Mixing data type may cause exceptions at runtime. For example, the following throws an exception because a
     * floating-point number is used when an integer value is expected.
     */
    static void printException() {
        System.out.format("Food: %d tons", 2.0); // IllegalFormatConversionException
    }

    /**
     * The format() method supports a lot of other symbols and flags. You don't need to know any of them for the
     * exam beyond what we've discussed already.
     */
    static void format3(){

        var pi = 3.14159265359;
        System.out.format("[%f]", pi);  // [3.141593]
        System.out.format("[%12.8f]", pi);  // [  3.14159265]
        System.out.format("[%012f]", pi);  // [00003.141593]
        System.out.format("[%12.2f]", pi);  // [        3.14]
        System.out.format("[%.3f]", pi);  // [3.142]








    }
}
