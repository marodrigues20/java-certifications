package chapter_08.abstracts.example_02;

public class Car extends Vehicle {
    @Override
    public void accelerate() {
        System.out.println("Car accelerating");
    }

    @Override
    public void brake() {
        System.out.println("Car braking");
    }

    //public abstract void brake(); Para que eu possa não implementar o método eu preciso fazer a class Car seja abstract também.
}


