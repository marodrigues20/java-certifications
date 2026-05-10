package chapter_9.autoboxing;

public class MyExamples {

    public static void main(String[] args) {

        IntegerExample();
    }

    private static void IntegerExample() {

        System.out.println("************ MAX_VALUE / MIN_VALUE ***************");
        Integer maxValue = Integer.MAX_VALUE;
        System.out.println("Integer.MAX_VALUE -> " + Integer.MAX_VALUE);

        Integer minValue = Integer.MIN_VALUE;
        System.out.println("Integer.MIN_VALUE -> " + Integer.MIN_VALUE);

        System.out.println("************ Autoboxing / Unboxing ***************");
        int x = 4;
        Integer y = x; // Autoboxing — int -> Integer
        System.out.println("Autoboxing -> " + y);

        x = y; // Unboxing — Integer -> int
        System.out.println("Unboxing -> " + x);

        System.out.println("************ parseInt vs valueOf ***************");
        String stringNumber = "42";
        int parseInt = Integer.parseInt(stringNumber); // returns primitive int — no autoboxing
        System.out.println("Integer.parseInt(stringNumber) -> " + parseInt);

        // valueOf is more efficient for numbers between -128 and 127 — reuses cached objects
        Integer valueOf = Integer.valueOf(stringNumber); // returns Integer object — uses cache if <= 127
        System.out.println("Integer.valueOf(stringNumber) -> " + valueOf);

        System.out.println("************ Conversion methods ***************");
        System.out.println("y.byteValue()   -> " + y.byteValue());
        System.out.println("y.floatValue()  -> " + y.floatValue());
        System.out.println("y.doubleValue() -> " + y.doubleValue());

        System.out.println("************ Integer Cache (-128 to 127) — OCP trap ***************");
        Integer a = Integer.valueOf(127);
        Integer b = Integer.valueOf(127);
        System.out.println("Cache hit  (127 == 127): " + (a == b));   // true — same object from cache

        Integer c = Integer.valueOf(128);
        Integer d = Integer.valueOf(128);
        System.out.println("Cache miss (128 == 128): " + (c == d));          // false — different objects
        System.out.println("Cache miss (128.equals(128)): " + c.equals(d));  // true — same value
    }
}