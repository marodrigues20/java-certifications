package chapter_3.commoncollections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class ListInterfaceMethods {

    public static void main(String[] args) {

        // method add - add one item
        // method set - replace one item
        List<String> list = new ArrayList<>();
        list.add("SD");                   //[SD]
        list.add(0, "NY");          //[NY,SD]
        list.set(1, "FL");               // [NY,FL]
        System.out.println(list.get(0)); // NY
        list.remove("NY");            // [FL]
        list.remove(0);               // []
        //list.set(0, "?");                //
        //IndexOutOfBoundsException

        List<Integer> numbers = Arrays.asList(1,2,3);
        numbers.replaceAll(x -> x*2);
        System.out.println(numbers);

        for (Integer item: numbers) {
            System.out.println(item);
        }

        Iterator<Integer> iter = numbers.iterator();
        while (iter.hasNext()){
            Integer string = iter.next();
            System.out.println(string);

        }

    }
}
