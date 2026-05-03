package chapter_9.set;

import java.util.*;

public class MyHashSetExample {

    static void main() {
        exampleOfHashSet();

        //exampleOfTreeSet();
    }

    private static void exampleOfHashSet() {



        Set<String> hashSet = new HashSet<>(List.of("Alex", "Bob", "David","Xaquira", "Alex"));
        hashSet.add("Trump");
        Set<String> hashSet2 = new HashSet<>(List.of("Alex", "Bob", "Richard", "Milania"));
        // I merged hashSet and hashSet2
        hashSet2.addAll(hashSet);


        System.out.println("---------- loop over hashSet2 ---------------");
        // Remove Trump name if exists
        hashSet2.removeIf(name -> name.equalsIgnoreCase("Trump"));
        // Not keep inserted order
        hashSet2.forEach(System.out::println);


        System.out.println("----------------- loop over hashSet ----------------");
        hashSet.forEach(System.out::println);

        System.out.println("Let's see if contains Alex: " + hashSet.contains("Alex"));


        System.out.println("---------- loop over hashSet ---------------------");
        hashSet2.forEach(System.out::println);

        System.out.println("--------------------- removeAll method -----------");
        // Remove a interseção — o que os dois têm em comum.
        hashSet.removeAll(hashSet2);
        hashSet.forEach(System.out::println);


        System.out.println("------------- retainAll Method ---------- ");

        // interseção dos sets
        hashSet.retainAll(hashSet2);
        hashSet.forEach(System.out::println);

        System.out.println("------------ Loop Examples ------------");

        // It is not possible get by index or position
        //for (int i = 0; i < hashSet2.size(); i ++){
        //    hashSet.get(i);
        //}

        Iterator<String> iterSet = hashSet.iterator();
        while (iterSet.hasNext()){
            System.out.println(iterSet.next());
        }

        for(String name :  hashSet2){
            System.out.println("Let's see the name in for each: " + name);
        }

        System.out.println("---------------- ORDERING -------------");
        Set<String> hashSetOrdering = new HashSet<>(List.of("Alex", "Bob", "David","Xaquira", "Alex"));
        hashSetOrdering.stream().sorted(Comparator.naturalOrder()).forEach(System.out::println);

        System.out.println("------------- More methods ---------------");

        System.out.println("Size: " + hashSet2.size());
        System.out.println("isEmpty " + hashSet.isEmpty());
        System.out.println("containsAll " + hashSet.containsAll(hashSet2));
        System.out.println("toArray " + Arrays.toString(hashSet.toArray(new String[0])));

        String[] x = {"Philip", "John", "Paul"};
        System.out.println(Arrays.toString(x));


    }
}
