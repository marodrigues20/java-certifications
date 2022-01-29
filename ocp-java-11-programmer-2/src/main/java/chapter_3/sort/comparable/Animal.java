package chapter_3.sort.comparable;

/**
 * We could use Integer.compareTo but using int to learn how to do that.
 *
 * If we don't specify Animal in generic. Java assumes that we want an Object. We have to do a cast.
 */
public class Animal implements Comparable<Animal>{

    private int id;

    @Override
    public int compareTo(Animal a) {
        return this.id - a.id; // sorts ascending by
        //return this.id - a.id; // sorts descending order.
    }

    public static void main(String[] args) {

        var a1 = new Animal();
        var a2 = new Animal();
        a1.id = 5;
        a2.id = 7;
        System.out.println(a1.compareTo(a2)); //-2
        System.out.println(a1.compareTo(a1)); // 0
        System.out.println(a2.compareTo(a1)); // 2
    }


}
