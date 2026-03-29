package chapter_08.super_method;


/**
 * super.method() pode ser chamado em qualquer linha do método — não precisa ser a primeira como o super() do construtor.
 */
public class MyTest {

    public static void main(String[] args) {

        Dog d = new Dog();
        d.makeSound();
        // Generic sound
        // Woof!


    }
}
