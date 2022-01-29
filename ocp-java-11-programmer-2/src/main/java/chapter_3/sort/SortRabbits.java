package chapter_3.sort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Java knows that the Rabbits class is not Comparable. It knows sorting will fail.
 * We can fix this by passing a Comparator to sort();
 */
public class SortRabbits {

    static class Rabbit { int id; }

    public static void main(String[] args){
        List<Rabbit> rabbits = new ArrayList<>();
        rabbits.add(new Rabbit());

        Comparator<Rabbit> c = (r1,r2) -> r1.id = r2.id;
        Collections.sort(rabbits, c);
    }

}
