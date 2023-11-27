package chapter_3.sort.binarysearch;

import chapter_3.sort.comparable.Duck;

import java.util.Set;
import java.util.TreeSet;

public class UseTreeSet {
    static class Rabbit { int id; }
    public static void main(String[] args) {
        Set<Duck> ducks = new TreeSet<>();
        ducks.add(new Duck("Puddles"));

        Set<Rabbit> rabbits = new TreeSet<>();
        rabbits.add(new Rabbit()); // ClassCastException

    }
}
