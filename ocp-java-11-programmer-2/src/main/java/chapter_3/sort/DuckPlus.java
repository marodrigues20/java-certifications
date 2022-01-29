package chapter_3.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Comparable interface give us one way to sort one specific object choose the way that we sort it. We use compareTo
 * method. (We choose name to sort DuckPlus)
 * When we want to sort using a different field we have to use Comparator and its respective method compare.
 * (We choose weight to sort using a different field).
 * Both Comparator and Compare are funcional interface.
 */
public class DuckPlus implements Comparable<DuckPlus> {

    private String name;
    private int weight;

    public DuckPlus(String name, int weight) {
        this.name = name;
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public int compareTo(DuckPlus d) {
        return this.name.compareTo(d.name);
    }

    public static void main(String[] args) {

        /*Comparator<DuckPlus> byWeight = new Comparator<DuckPlus>() {
            @Override
            public int compare(DuckPlus d1, DuckPlus d2) {
                return d1.weight - d2.weight;
            }
        };*/

        Comparator<DuckPlus> byWeight = (d1, d2) -> d1.weight - d2.weight; // lambda
        Comparator<DuckPlus> byWeight2 = Comparator.comparing(DuckPlus::getWeight); // method reference

        var ducks = new ArrayList<DuckPlus>();
        ducks.add(new DuckPlus("Quack", 7));
        ducks.add(new DuckPlus("Puddles", 10));
        Collections.sort(ducks); //ascending order by name;
        System.out.println(ducks);

        Collections.sort(ducks, byWeight); //ascending order byWeight
        System.out.println(ducks);
    }

}
