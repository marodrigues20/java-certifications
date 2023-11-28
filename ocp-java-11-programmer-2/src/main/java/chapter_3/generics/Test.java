package chapter_3.generics;

import chapter_3.generics.model.Elephant;

public class Test {

    public static void main(String[] args) {
        Elephant elephant = new Elephant();
        Integer numPounds = 15_000;
        SizeLimitedCrate<Elephant, Integer> c1
                = new SizeLimitedCrate<>(elephant, numPounds);


        System.out.println(c1);

    }
}
