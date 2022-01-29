package chapter_3.sort.comparable;

import java.util.ArrayList;
import java.util.Collections;

public class Duck implements Comparable<Duck>{

    private String name;

    public Duck(String name) {
        this.name = name;
    }

    public String toString(){
        return name;
    }

    @Override
    public int compareTo(Duck duck) {

        return name.compareTo(duck.name); // sorts ascendingly by name

    }

    public static void main(String[] args) {
        var ducks = new ArrayList<Duck>();
        ducks.add(new Duck("Quack"));
        ducks.add(new Duck("Puddles"));
        Collections.sort(ducks); //sort by name
        System.out.println(ducks);
    }
}
