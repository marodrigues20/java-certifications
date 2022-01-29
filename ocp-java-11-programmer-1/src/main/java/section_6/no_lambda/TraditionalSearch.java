package section_6.no_lambda;

import section_6.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class TraditionalSearch {

    public static void main(String[] args) {
        // list of animals
        List<Animal> animals = new ArrayList<Animal>();
        animals.add(new Animal("fish", false, true));
        animals.add(new Animal("kangaroo", true, false));
        animals.add(new Animal("rabbit", true, false));
        animals.add(new Animal("turtle", false, true));
        // pass class that does check
        print(animals, new CheckIfHopper());
        //print(animals, a -> a.canHop());
        //print(animals, a -> a.canSwim());
        //print(animals, a -> !a.canSwim());
        //print(animals, (Animal a) -> !a.canSwim());
        print(animals, (Animal a) -> { return a.canSwim(); });
        print(animals, a -> { return true; } );


    }

    private static void print(List<Animal> animals, CheckTrait checker) {
        for (Animal animal : animals) {
            // the general check
            if (checker.test(animal))
                System.out.print(animal + " ");
        }
        System.out.println();
    }
}
