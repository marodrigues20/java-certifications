package chapter_08.overriding;

public class MyTestOverriding {

    public static void main(String[] args) {
        Animal a = new Dog();
        a.makeSound(); // Woof! — runs Dog's version

    }
}
