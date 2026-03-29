package chapter_08.super_method;

public class Dog extends Animal {
    @Override
    public void makeSound() {
        super.makeSound(); // calls Animal's version first
        System.out.println("Woof!");
    }
}