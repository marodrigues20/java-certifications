package chapter_9.list;

import uk.co.collection.domain.Person;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public class MyArrayList {

    /**
     * - List
     * - ArrayList
     * - LinkedList
     */
    static void main() {

        //Initial capacity 4
        List<String> listArrayList = new ArrayList<>(List.of("Alex", "Bob", "David", "Xaquira"));
        listArrayList.add("Michael");
        listArrayList.add("Richard");

        System.out.println("First Element from SequencedCollection: " + listArrayList.getFirst());
        System.out.println("First Element from SequencedCollection: " + listArrayList.getLast());
        for (int i = 0; i < listArrayList.size(); i++) {
            listArrayList.get(i);
            listArrayList.remove(i);
        }

        System.out.println("Using addFirst from SequencedCollection");
        listArrayList.addFirst("Olivia");
        System.out.println("Using addLast from SequencedCollection");
        listArrayList.addLast("Rodrigues");

        Iterator<String> iterator = listArrayList.iterator();

        while (iterator.hasNext()) {
            System.out.println("I'm inside Interator to see the itens: " + iterator.next());
        }

        for (String name : listArrayList) {
            System.out.println(name);
        }

        Person person1 = new Person(32, "Paul");
        Person person2 = new Person(45, "Richard");
        Person person3 = new Person(20, "Alexandre");
        List<Person> listPerson = new ArrayList<>(List.of(person1, person2, person3));

        listPerson.sort(Comparator
                .comparingInt(Person::age)
                .thenComparing(Person::name).reversed());

        listPerson.removeIf(person -> person.name().equalsIgnoreCase("Paul"));

        listPerson.forEach(System.out::println);

    }


}
