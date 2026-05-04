package chapter_9.compare;

import chapter_9.compare.domain.BreedEnum;
import chapter_9.compare.domain.Dog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MyComparableTest {

    public static void main(String[] args) {

        Dog dog1 = new Dog("Toto", 1, BreedEnum.BORDER_COLIE);
        Dog dog2 = new Dog("Doug", 3, BreedEnum.FRENCH_BULLDOG);
        Dog dog3 = new Dog("Caramel", 5, BreedEnum.GOLDEN_RETRIEVER);
        Dog dog4 = new Dog("Preet", 4, BreedEnum.LABRADOR_RETRIEVER);
        System.out.println("dog1 vem depois de dog2 pois o resultado é positivo: " + dog1.compareTo(dog2));

        List<Dog> listDogs = new ArrayList<>(List.of(dog1, dog2, dog3, dog4));

        // Java 5 — Collections utility class, uses Comparable natural order
        Collections.sort(listDogs);

        // Java 8 — List.sort() with null uses Comparable natural order
        listDogs.sort(null);

        // Java 8 — List.sort() with explicit Comparator.naturalOrder()
        listDogs.sort(Comparator.naturalOrder());

        // Java 8 — Stream sorted() uses Comparable natural order, does not modify the original list
        listDogs.stream()
                .sorted()
                .forEach(System.out::println);




    }
}
