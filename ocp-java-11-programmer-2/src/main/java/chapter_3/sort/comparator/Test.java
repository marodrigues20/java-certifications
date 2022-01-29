package chapter_3.sort.comparator;


import java.util.Comparator;

/**
 * Show how to build a comparator using method reference instead of create the class MultiFieldComparator.
 */
public class Test {

    public static void main(String[] args) {

        //Comparing what's the grater using species after using weight.
        Comparator<Squirrel> c = Comparator.comparing(Squirrel::getSpecies)
                .thenComparing(Squirrel::getWeight);

        //Using method reference but reverse the order. (asc / desc)
        var c2 = Comparator.comparing(Squirrel::getSpecies).reversed();


    }
}
