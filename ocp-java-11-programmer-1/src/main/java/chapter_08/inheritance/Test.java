package chapter_08.inheritance;

public class Test {

    public static void main(String[] args) {

        Dog c = new Dog();
        c.eat(); // herdado de Animal
        c.bark();  // próprio de Cachorro

    }
}
