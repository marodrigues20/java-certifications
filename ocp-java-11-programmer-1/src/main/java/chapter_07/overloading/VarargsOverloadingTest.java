package chapter_07.overloading;

public class VarargsOverloadingTest {

    public static void test(int x) {
        System.out.println("int");
    }

    public static void test(int... x) {
        System.out.println("varargs");
    }

    public static void main(String[] args) {
        test(5);      // int — match exato vence varargs
        test(5, 6);   // varargs — única opção
        test();       // varargs — array vazio
    }
}
// Output:
// int
// varargs
// varargs
