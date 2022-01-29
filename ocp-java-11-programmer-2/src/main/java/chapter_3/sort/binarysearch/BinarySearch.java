package chapter_3.sort.binarysearch;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class BinarySearch {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(6,9,1,8);
        Collections.sort(list);

        System.out.println(list);

        System.out.println(Collections.binarySearch(list, 6)); //Print the position of item 6.
        // -2 because the number 3 couldn't be found in position 1
        System.out.println(Collections.binarySearch(list, 3));

    }
}
