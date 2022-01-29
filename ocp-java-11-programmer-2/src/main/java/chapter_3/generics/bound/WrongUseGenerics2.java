package chapter_3.generics.bound;

import java.util.ArrayList;
import java.util.List;

public class WrongUseGenerics2 {

    static class Bird {}
    static class Sparrow extends Bird { }


    public static void main(String[] args) {

        //ArrayList<Number> list = new ArrayList<Integer>(); // DOES NOT COMPILE

        List<? extends Number> list2 = new ArrayList<Integer>();

        List<? extends Bird> birds = new ArrayList<Bird>();
        //birds.add(new Sparrow()); // DOES NOT COMPILE
        //birds.add(new Bird()); // DOES NOT COMPILE
    }

    private static long total(List<? extends Number> list) {

        long count = 0;
        for (Number number : list)
            count += number.longValue();
        return count;
    }




}
