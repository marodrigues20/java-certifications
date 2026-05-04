package chapter_9.compare;

import chapter_9.compare.domain.BreedEnum;
import chapter_9.compare.domain.Dog;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ComparatorTest {

    public static void main(String[] args) {


        Dog dog1 = new Dog("Toto", 1, BreedEnum.BORDER_COLIE);
        Dog dog2 = new Dog("Doug", 3, BreedEnum.FRENCH_BULLDOG);
        Dog dog3 = new Dog("Caramel", 5, BreedEnum.GOLDEN_RETRIEVER);
        Dog dog4 = new Dog("Preet", 4, BreedEnum.LABRADOR_RETRIEVER);
        List<Dog> listDogs = new ArrayList<>(List.of(dog1, dog2, dog3, dog4));

        Comparator<Dog> comparatorMethodReference = Comparator.comparing(Dog::getAge);
        //Equivalente
        //Comparator<Dog> comparatorMethodReference2 = Comparator.comparing(dog -> dog.getAge());
        Comparator<Dog> comparatorTest = new CompareExample();
        Comparator<Dog> comparatorLambda = (dog_1, dog_2) -> dog_1.getAge().compareTo(dog_2.getAge());


        System.out.println("*********** Java 8 — modifica a lista in-place ***************");
        listDogs.sort(comparatorMethodReference);
        System.out.println("After List.sort():");
        listDogs.forEach(System.out::println);

        System.out.println();
        System.out.println("*********** Reset da lista para demonstrar o Stream independente usando Comparator with Lambda ***************");
        List<Dog> listDogs2 = new ArrayList<>(List.of(dog1, dog2, dog3, dog4));
        System.out.println("After Stream.sorted() — original list unchanged:");
        listDogs2.stream().sorted(comparatorLambda).forEach(System.out::println);

        System.out.println();
        System.out.println("*********** Reset da lista para demonstrar o Stream independente com classe avulsa implementado Comparator ***************");
        List<Dog> listDogs3 = new ArrayList<>(List.of(dog1, dog2, dog3, dog4));
        System.out.println("After Stream.sorted() — original list unchanged:");
        listDogs3.stream().sorted(comparatorTest).forEach(System.out::println);


        System.out.println();
        System.out.println("*********** Reset da lista para demonstrar o Stream independente com classe avulsa implementado Comparator with thenComparing ***************");
        Dog dog5 = new Dog("Alex", 3, BreedEnum.GERMAN_SHEPHERD); // mesma idade que Doug (3)
        List<Dog> listDogs4 = new ArrayList<>(List.of(dog1, dog2, dog3, dog4, dog5));
        System.out.println("After Stream.sorted() — original list unchanged:");
        listDogs4.stream().sorted(comparatorTest.reversed().thenComparing(Dog::getName)).forEach(System.out::println);


    }
}
