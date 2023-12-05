package chapter_3.review_questions;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Question 8
 *
 * Correct B and F
 * We're looking for a Comparator definition that sorts in descending order by beakLength.
 * Option A is incorrect because it sorts in ascending order by beakLength.
 * Option C is incorrect because it sorts the beakLength in ascending order within those matches
 * that have the same name.
 * Option E is incorrect because there is no thenComparingNumber() method.
 */
public class Platypus {

    String name;
    int beakLength;

    public Platypus(String name, int beakLength) {
        this.name = name;
        this.beakLength = beakLength;
    }

    @Override
    public String toString() {
        return "" + beakLength;
    }

    public static void main(String[] args) {
        Platypus p1 = new Platypus("Paula", 3);
        Platypus p2 = new Platypus("Peter", 5);
        Platypus p3 = new Platypus("Peter", 7);

        List<Platypus> list = Arrays.asList(p1, p2, p3);

        Collections.sort(list, Comparator.comparing(Platypus::getBeakLength));

        /*Collections.sort(list, Comparator.comparing(Platypus::getName)
                .thenComparingInt(Platypus::getBeakLength)
                .reversed());*/

        System.out.println(list);


    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getBeakLength() {
        return beakLength;
    }

    public void setBeakLength(int beakLength) {
        this.beakLength = beakLength;
    }
}
