package chapter_1.final_modifier;


/**
 * The age variable is given a value when it is declared, while the fishEaten variable is assigned a
 * value in an instance initializer. The name variable is given a value in the no-argument constructor.
 * Notice that the second constructor does not assign a value to name, but since it calls the no-argument constructor
 * first, name is guaranteed to be assigned a value in the first line of this constructor.
 */
public class PolarBear {

    final int age = 10;
    final int fishEaten;
    final String name;

    {
        fishEaten = 10;
    }

    public PolarBear(){
        name = "Robert";
    }

    public PolarBear(int height){
        this();
    }
}
