package chapter_3.test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Platypus {



    String name;
    int beakLenght;

    public Platypus(String name, int beakLenght) {
        this.name = name;
        this.beakLenght = beakLenght;
    }

    @Override
    public String toString() {
        return "Platypus{" +
                "name='" + name + '\'' +
                ", beakLenght=" + beakLenght +
                '}';
    }

    public static void main(String[] args) {

        Comparator<Integer> c1 = (o1, o2) -> o2 - o1;
        Comparator<Integer> c2 = Comparator.naturalOrder();
        Comparator<Integer> c3 = Comparator.reverseOrder();

        var list = Arrays.asList(5, 4, 7, 2);
        Collections.sort(list, c1);

        System.out.println(Collections.binarySearch(list, 2));
    }

    public int getBeakLenght() {
        return beakLenght;
    }

    public static <T> T identity(T t){
            return null;
    }
}
