package chapter_9.collections;

import chapter_9.compare.domain.BreedEnum;
import chapter_9.compare.domain.Dog;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class CollectionUtilities {

    public static void main(String[] args) {

        List<String> nameList = new ArrayList<>(List.of("Wagner", "Nick", "Alex", "Richard", "July", "Pamela"));

        System.out.println("****** Collections.sort() method - // [List must be classified to work]");
        Collections.sort(nameList);
        nameList.forEach(System.out::println);

        System.out.println("*********** binarySearch() method O(log₂ n) - // [List must be sorted before calling binarySearch, otherwise result is undefined]");
        System.out.println("binarySearch() -> If result is >= 0 Element has been found");
        System.out.println("binarySearch() -> If result is < 0 Element has not been found");
        System.out.println("binarySerach() is used when We need to know where the element is");
        Collections.sort(nameList);
        int name = Collections.binarySearch(nameList, "Paul", Comparator.naturalOrder());
        System.out.println(name);

        System.out.println();
        System.out.println("************ contains() method O(n) ***********************");
        System.out.println("Let's see that we can find \"Richard\" in the List: " + nameList.contains("Richard"));

        System.out.println();
        System.out.println("************ indexOf() method O(n) ***********************");
        System.out.println("Let's check  " + nameList.indexOf("Richard"));

        // min() / max() — com e sem Comparator
        System.out.println();
        System.out.println("min()/ max() method - [No Comparator - use natural order]");
        String min = Collections.min(nameList); // menor alfabeticamente
        String max = Collections.max(nameList); // maior alfabeticamente
        System.out.println("min() [menor alfabeticamente] -> " + min);
        System.out.println("max() [maior alfabeticamente]-> " + max);


        // menor por tamanho do nome
        System.out.println();
        System.out.println("min()/ max() method - [Using Comparator - use natural order]");
        String minByLength = Collections.min(nameList, Comparator.comparingInt(String::length));
        String maxByLength = Collections.max(nameList, Comparator.comparingInt(String::length));
        System.out.println("min() by name length -> " + minByLength);
        System.out.println("max() by name length -> " + maxByLength);


        System.out.println();
        System.out.println("min()/ max() method - [Using Comparator - Comparator by age]");
        Dog dog1 = new Dog("Toto", 1, BreedEnum.BORDER_COLIE);
        Dog dog2 = new Dog("Doug", 3, BreedEnum.FRENCH_BULLDOG);
        Dog dog3 = new Dog("Caramel", 5, BreedEnum.GOLDEN_RETRIEVER);
        Dog dog4 = new Dog("Preet", 4, BreedEnum.LABRADOR_RETRIEVER);
        List<Dog> dogs = new ArrayList<>(List.of(dog1, dog2, dog3, dog4));
        Dog youngest = Collections.min(dogs, Comparator.comparing(Dog::getAge));
        Dog oldest   = Collections.max(dogs, Comparator.comparing(Dog::getAge));
        System.out.println("youngest -> " + youngest);
        System.out.println("oldest   -> " + oldest);


        System.out.println("*********** Collections.unmodifiableList() method *****************");
        // unmodifiableList é uma view da lista original - não uma cópia. Se você modificar a lista original, a view reflete a mudança.
        // unmodifiableList() — lista imutável
        List<String> unmodifiableList = Collections.unmodifiableList(nameList);
        // Leitura funciona normalmente
        unmodifiableList.forEach(System.out::println);
        // Tentativa de modificar lança UnsupportedOperationException
        //unmodifiableList.add("Paul");    // UnsupportedOperationException
        //unmodifiableList.remove("Alex"); // UnsupportedOperationException

        List<String> immutable = List.copyOf(nameList); // cópia independente e imutável


        System.out.println();
        System.out.println("**************** Collections.frequency() method ******************");
        // frequency() — conta ocorrências de um elemento
        List<String> nameList2 = new ArrayList<>(List.of("Alex", "Paul", "Alex", "Richard", "Alex", "July"));
        int frequency = Collections.frequency(nameList2, "Alex");
        System.out.println("frequency() — number of times 'Alex' appears in the list -> " + frequency); // 3

        System.out.println("************ Collections.nCopies() method *********************");
        // A lista retorna por nCopies() é imutável - tentar modificar lança UnsupportedOperationException
        // nCopies() — cria lista com n cópias de um elemento
        List<String> copies = Collections.nCopies(5, "Alex");
        System.out.println("nCopies() — creates a list with 5 copies of 'Alex' -> " + copies);
        // [Alex, Alex, Alex, Alex, Alex]

        System.out.println();
        System.out.println("*********** new ArrayList<>(Collections.nCopies(5, \"Alex\")) *************");
        // Lista Mutável
        List<String> mutableCopies = new ArrayList<>(Collections.nCopies(5, "Alex"));
        mutableCopies.add("Paul"); // funciona


        System.out.println();
        System.out.println("**************** Collections.reverse() method ******************** ");
        // reverse() — inverte a ordem da lista
        List<String> nameList3 = new ArrayList<>(List.of("Wagner", "Nick", "Alex", "Richard", "July", "Pamela"));

        System.out.println();
        System.out.println("************ Collections.reverse() method *********************");
        System.out.println("Before reverse:");
        nameList3.forEach(System.out::println);

        System.out.println();
        System.out.println("Reversed order ");
        Collections.reverse(nameList3);

        System.out.println();
        System.out.println("After reverse:");
        nameList3.forEach(System.out::println);

    }
}
