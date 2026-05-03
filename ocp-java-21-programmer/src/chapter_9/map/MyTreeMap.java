package chapter_9.map;

import uk.co.collection.domain.Person;

import java.util.Map;
import java.util.NavigableMap;
import java.util.Set;
import java.util.TreeMap;

public class MyTreeMap {

    static void main() {

        Map<String, Person> mapList = Map.of(
                "1955-03-14", new Person(71, "Vania"),
                "2001-07-22", new Person(25, "Richard"),
                "1996-11-05", new Person(30, "Alexandre"),
                "1986-09-18", new Person(40, "Nick"),
                "1976-04-30", new Person(50, "Philip"));

        NavigableMap<String, Person> orinalTreeMap = new TreeMap<>(mapList);

        Set<Map.Entry<String, Person>> setMap = orinalTreeMap.entrySet();

        for (Map.Entry<String, Person> entry : setMap) {
            System.out.println(entry.getKey());

        }

        // smallest key in the TreeMap
        System.out.println("firstKey: " + orinalTreeMap.firstKey());
        // largest key in the TreeMap
        System.out.println("lastKey: " + orinalTreeMap.lastKey());

        // highest key still below or equal to 1980-01-01
        System.out.println("floorKey 1980-01-01: " + orinalTreeMap.floorKey("1980-01-01"));
        // lowest key still above or equal to 1980-01-01
        System.out.println("ceilingKey 1980-01-01: " + orinalTreeMap.ceilingKey("1980-01-01"));
        // highest key strictly below 1980-01-01 (exclusive)
        System.out.println("lowerKey 1980-01-01: " + orinalTreeMap.lowerKey("1980-01-01"));
        // lowest key strictly above 1980-01-01 (exclusive)
        System.out.println("higherKey 1980-01-01: " + orinalTreeMap.higherKey("1980-01-01"));

        // returns all entries with keys strictly below 1980-01-01
        System.out.println("headMap before 1980-01-01: " + orinalTreeMap.headMap("1980-01-01"));
        // returns all entries with keys greater than or equal to 1980-01-01
        System.out.println("tailMap from 1980-01-01: " + orinalTreeMap.tailMap("1980-01-01"));

        // removes and returns the first entry (smallest key)
        System.out.println("pollFirstEntry: " + orinalTreeMap.pollFirstEntry());
        // removes and returns the last entry (largest key)
        System.out.println("pollLastEntry: " + orinalTreeMap.pollLastEntry());


        // returns all entries with keys between 1976-04-30 (inclusive) and 1996-11-05 (exclusive)
        System.out.println("subMap from 1976-04-30 to 1996-11-05: " + orinalTreeMap.subMap("1976-04-30", "1996-11-05"));
    }
}
