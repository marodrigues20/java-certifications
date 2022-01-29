package chapter_3.wrapperclasses;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AutoBoxing {

    public static void main(String[] args) {

        var heights = new ArrayList<Integer>();
        heights.add(null);
        //int h = heights.get(0); //NullPointerException

        List<Integer> numbers = new ArrayList<Integer>();
        numbers.add(1); // Autoboxing
        numbers.add(Integer.valueOf(3)); //Wrapper the number 3
        numbers.add(Integer.valueOf(5)); //Wrapper the number 5
        numbers.remove(1); //remove method is overloaded. In this method we remove index 1.
        numbers.remove(Integer.valueOf(5)); ////remove method is overloaded. In this method we remove the object 5.
        System.out.println(numbers);


        //<> operator was added in java 11 and we don't need to repeat the generic in the right side.
        List<Integer> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        Map<Long, List<Integer>> mapOfLists = new HashMap<>();


        var list1 = new ArrayList<Integer>(); // var infer is a list1 of ArrayList of Integer.
        var list2 = new ArrayList<>(); // var infer is a list2 of ArrayList of Objects.





    }
}
