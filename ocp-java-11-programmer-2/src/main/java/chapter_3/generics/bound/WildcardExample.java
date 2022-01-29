package chapter_3.generics.bound;

import java.util.ArrayList;
import java.util.List;

public class WildcardExample {

    public static void main(String[] args) {
        List<String> keywords = new ArrayList<>();
        keywords.add("java");
        printList(keywords);
    }

    private static void printList(List<?> list){
        for (Object x: list ) {
            System.out.println(x);
        }
    }

    private static void difference(){
        //Both return type Object when calling the get() method.
        List<?> x1 = new ArrayList<>(); // x1 is of type List
        var x2 = new ArrayList<>(); // x2 is of type ArrayList
    }
}
