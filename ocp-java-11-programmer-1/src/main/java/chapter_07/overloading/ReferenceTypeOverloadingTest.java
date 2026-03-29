package chapter_07.overloading;


/**
 * ⚠️ Regra do exame: null é compatível com qualquer referência — o compilador escolhe o tipo mais específico na hierarquia.
 */
public class ReferenceTypeOverloadingTest {

    public static void print(String s) {
        System.out.println("String");
    }

    public static void print(Object o) {
        System.out.println("Object");
    }

    public static void main(String[] args) {
        print("hello");       // String — mais específico
        print(new Object());  // Object — match exato
        print(null);          // String — mais específico vence
    }
}
// Output:
// String
// Object
// String
