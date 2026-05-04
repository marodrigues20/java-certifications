package chapter_9.compare;

import chapter_9.compare.domain.Dog;

import java.util.Comparator;

public class CompareExample implements Comparator<Dog> {


    @Override
    public int compare(Dog o1, Dog o2) {

        return o1.getAge().compareTo(o2.getAge());

    }
}
