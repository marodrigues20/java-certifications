package chapter_3.review_questions;


import java.util.Arrays;
import java.util.Comparator;

/**
 * Question 10
 *
 * Correct is A
 * The array is sorted using MyComparator, which sorts the element in reverse alphabetical order in a
 * case-insensitive fashion.
 * Normally, numbers sort before letters. This code reverses that by calling the compareTo() method on b instead a.
 *
 */
public class MyComparator implements Comparator<String> {


    @Override
    public int compare(String a, String b) {
        return b.toLowerCase().compareTo(a.toLowerCase());
    }

    public static void main(String[] args) {
        String[] values = {"123", "Abb", "aab"};
        Arrays.sort(values, new MyComparator());
        for (var s: values)
            System.out.println(s + " ");
    }
}
