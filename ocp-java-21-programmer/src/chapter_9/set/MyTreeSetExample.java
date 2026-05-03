package chapter_9.set;

import uk.co.collection.domain.Person;

import java.util.*;

public class MyTreeSetExample {

    static void main() {

        List<Person> personList = List.of(
                new Person(70, "Vania"),
                new Person(25, "Richard"),
                new Person(30, "Alexandre"),
                new Person(40, "Nick"),
                new Person(50, "Philip"));
        NavigableSet<Person> personTreeSet = new TreeSet<>(Comparator.comparing(Person::age));
        personTreeSet.addAll(personList);

        personTreeSet.forEach(System.out::println);

        System.out.println("****************** Checking methods *********************************");
        System.out.println("Let's get the first position in the TreeSet: " + personTreeSet.first());
        System.out.println("Let's get the last position in the TreeSet: " + personTreeSet.last());
        System.out.println("Let's see how headSet method that retrieves everything strictly below Nick(40) exclusive: " + personTreeSet.headSet(personList.get(3)));
        System.out.println("Let's see how tailSet method that retrieve everything above including 40 age: " + personTreeSet.tailSet(personList.get(3)));
        System.out.println("Let's see how subSet method retrieves elements from Richard(25) inclusive to Philip(50) exclusive: " + personTreeSet.subSet(personList.get(1), personList.get(4)));


        System.out.println("****************** Checking Pooling methods *********************************");
        NavigableSet<Person> personTreeSet2 = new TreeSet<>(Comparator.comparing(Person::age));
        personTreeSet2.addAll(personList);
        System.out.println("-------- Current personTreeSet2 state -----------------");
        personTreeSet2.forEach(System.out::println);
        System.out.println("*************** NavigableSet Methods *******************");
        System.out.println("Let's see pollFirst method get and removing  the first item in the treeSet: " + personTreeSet2.pollFirst());
        System.out.println("Let's see pollLast method get and removing  the last item in the treeSet: " + personTreeSet2.pollLast());
        System.out.println("-------- Final personTreeSet2 state -----------------");
        personTreeSet2.forEach(System.out::println);


        System.out.println("*************** More NavigableSet Methods *******************");
        personTreeSet.forEach(System.out::println);
        Person ref1 = new Person(35, "");
        System.out.println("Let's see floor method that returns the highest element still below or equal to 35: " + personTreeSet.floor(ref1));
        System.out.println("Let's see ceiling method that returns the lowest element still above or equal to 35: " + personTreeSet.ceiling(ref1));
        System.out.println("Let's see lower method that returns the highest element strictly below 35 (exclusive): " + personTreeSet.lower(ref1));
        System.out.println("Let's see higher method that returns the lowest element strictly above 35 (exclusive): " + personTreeSet.higher(ref1));



        //personTreeSet.addFirst(new Person(80, "Robson")); // UnsupportedOperationException
        //personTreeSet.addLast(new Person(80, "Robson")); // UnsupportedOperationException

        // Not possible get the node by index.
        //for(int i = 0; i < personTreeSet.size(); i ++){
            //System.out.println(personTreeSet.get(i));
        //}

        System.out.println("************* ITERATOR IN ACTION ****************");
        Iterator<Person> iter = personTreeSet.iterator();
        while(iter.hasNext()){
            System.out.println(iter.next());
        }

        System.out.println("************* New Sorting by name ****************");
        personTreeSet.stream().sorted(Comparator.comparing(Person::name)).forEach(System.out::println);

        System.out.println("************* For each  ****************");
        for(Person person: personTreeSet){
            System.out.println(person);
        }

    }

}
