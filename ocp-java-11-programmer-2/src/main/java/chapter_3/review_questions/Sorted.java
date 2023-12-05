package chapter_3.review_questions;

import java.util.Comparator;
import java.util.TreeSet;

/**
 * Question 14
 *
 * Correct is C.
 *
 * This question is difficult because it defines both Comparable and Comparator on the same object.
 * The t1 object doesn't specify a Comparator, so it uses the Comparable object's compareTo() method.
 * This sorts by the text instance variable. The t2 object did specify a Comparator when calling the
 * constructor, so it uses the compare() method, which sorts by the int.
 *
 */
public class Sorted implements Comparable<Sorted>, Comparator<Sorted> {

    private int num;
    private String text;

    public Sorted(int num, String text) {
        this.num = num;
        this.text = text;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return "" + num;
    }

    @Override
    public int compareTo(Sorted s) {
        return text.compareTo(s.text);
    }

    @Override
    public int compare(Sorted s1, Sorted s2) {
        return s1.num - s2.num;
    }

    public static  void main(String[] args){
        var s1 = new Sorted(88, "a");
        var s2 = new Sorted(55, "b");
        var t1 = new TreeSet<Sorted>();
        t1.add(s1); t1.add(s2);
        var t2 = new TreeSet<Sorted>(s1);
        t2.add(s1); t2.add(s2);
        System.out.println(t1 + "" + t2); // Printout [88, 55][55, 88]
    }
}
