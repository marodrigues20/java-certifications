package chapter_9.set;

import java.util.*;

public class MyLinkedHashSetExample {

    static void main() {

        SequencedSet<String> linkedHashSet = new LinkedHashSet<>(List.of("Alex", "Bob", "David", "Xaquira", "Alex"));
        linkedHashSet.add("Rodrigues");
        linkedHashSet.removeIf(names -> names.equalsIgnoreCase("Rodrigues"));

        // ✅ mais claro
        System.out.println("----- removeAll — sobra o que é exclusivo do copyForRemoveAll -----");
        Set<String> copyForRemoveAll = new LinkedHashSet<>(List.of("Alex", "Bob", "David", "Michael", "Naomi"));
        copyForRemoveAll.removeAll(linkedHashSet);
        copyForRemoveAll.forEach(System.out::println);

        System.out.println("----- retainAll — interseção dos sets -----");
        Set<String> copyForRetainAll = new LinkedHashSet<>(List.of("Alex", "Bob", "David", "Michael", "Naomi"));
        copyForRetainAll.retainAll(linkedHashSet);
        copyForRetainAll.forEach(System.out::println);

        System.out.println("----- addAll — merge dos sets Union -----");
        Set<String> copyAddAll = new LinkedHashSet<>(List.of("Alex", "Bob", "David", "Michael", "Naomi"));
        copyAddAll.addAll(linkedHashSet);
        copyAddAll.forEach(System.out::println);

        System.out.println("----------- Ordering -------------------");
        copyAddAll.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);


        System.out.println("-------- Looping LinkedHashSet -----------");

        // Nao da para recuperar elementos pelo get. Nem existe essa operacao na interface Set e nem na SequencedSet.
        //for(int i = 0; i < copyAddAll.size(); i++){
            //copyAddAll.get(i);
        //}

        // LinkedHasSet implement the SequencedSet. For this reason, keep the inserted Order but it sill use hashCode
        Iterator<String> iter = linkedHashSet.iterator();
        while(iter.hasNext()){
            System.out.println("Let's see the inserted order: " + iter.next());
        }

        System.out.println(" --------------- Loop using For each (Keep inserted Ordered ---------------------------");
        linkedHashSet.addFirst("Vania");
        linkedHashSet.addLast("Mario");
        System.out.println("Test getFirst do SequencedCollection: " + linkedHashSet.getFirst());
        System.out.println("Test getFirst do SequencedCollection: " + linkedHashSet.getLast());
        SequencedSet<String> sequencedSet = linkedHashSet.reversed();
        for(String name: sequencedSet){
            System.out.println("Let's see the for each inherited thought the linkedHashSet: " + name);
        }
    }
}