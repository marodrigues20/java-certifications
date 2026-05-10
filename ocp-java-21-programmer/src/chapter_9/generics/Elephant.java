package chapter_9.generics;

import chapter_9.compare.domain.Animal;
import chapter_9.compare.domain.BreedEnum;
import chapter_9.compare.domain.Dog;

import java.util.List;

public class Elephant<T extends Animal, U> extends Animal {

    private T animal;
    private U type;

    public Elephant(T animal, U type) {
        this.animal = animal;
        this.type = type;
    }

    public <T extends Dog> void run(T velocity) {
        System.out.println(velocity + " is running!");
    }

    // Does not compile — Java does not allow <T super Dog> in generic methods.
    // The 'super' keyword is only valid with wildcards (?) in collections.
    // To write elements, use: public void slowdown(List<? super Dog> animals) { }
    // public <T super Dog> void slowdown(T velocity) { }

    // Valid — wildcard with super in collection
    public void slowdown(List<? super Dog> animals) { }

    public <U extends Animal> U eat(U food) {
        return null;
    }

    // PECS - PE (Producer Extends)
    // Producer — read only. Adding elements is not allowed because the list could be
    // List<Dog>, List<Cat> or List<Elephant> — mixing subtypes would break type safety.
    public void addDistance(List<? extends Animal> animals) {
        animals.forEach(System.out::println);
        // animals.add(new Dog(...)); // Does not compile — read only
    }

    // PECS - CS (Consumer Super)
    // Write allowed. The list is List<Animal> or List<Object> — both are designed to accept
    // any Animal subtype. Mixing subtypes is the expected behavior, not a violation.
    // With List<? super Animal> the compiler knows the list accepts at least Animal —
    // so any Animal subtype can be inserted safely.
    public void addJumps(List<? super Animal> animals) {
        animals.add(new Dog("Toto", 2, BreedEnum.LABRADOR_RETRIEVER));
    }
}