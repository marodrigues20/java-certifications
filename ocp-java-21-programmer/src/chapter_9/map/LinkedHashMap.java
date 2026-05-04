package chapter_9.map;


import chapter_9.domain.Person;

import java.util.Map;
import java.util.Set;


/**
 * Mesmos métodos ✅
 * Mesma performance O(1) ✅
 * Única diferença → insertion order ✅
 * Custo extra → ponteiros before/next em cada Node (mais memória)
 */
public class LinkedHashMap {


    static void main() {

        // Not use this to keep the inserted Order in LinkedHashMap. Map does not orded.
        Map<String, Person> mapList = Map.of(
                "1955-03-14", new Person(71, "Vania"),
                "2001-07-22", new Person(25, "Richard"),
                "1996-11-05", new Person(30, "Alexandre"),
                "1986-09-18", new Person(40, "Nick"),
                "1976-04-30", new Person(50, "Philip"));

        Map<String, Person> originalMap = new java.util.LinkedHashMap<>();
        // In this way LinkedHashMap preserv the order
        originalMap.put("1955-03-14", new Person(71, "Vania"));
        originalMap.put("2001-07-22", new Person(25, "Richard"));
        originalMap.put("1996-11-05", new Person(30, "Alexandre"));
        originalMap.put("1986-09-18", new Person(40, "Nick"));
        originalMap.put("1976-04-30", new Person(50, "Philip"));

        Set<Map.Entry<String, Person>> set  = originalMap.entrySet();

        for(Map.Entry<String, Person> entry: set){
            System.out.println(entry.getKey());
        }

    }
}
