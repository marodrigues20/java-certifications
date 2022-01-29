package chapter_3.generics.bound;

import java.util.ArrayList;
import java.util.List;


/**
 * This attribution looks logical. Because String is a subclass of Object class.
 * Java is trying to protect us from ourselves with this one.
 */
public class WrongUseGenerics {

    public static void main(String[] args) {
        List<String> keywords = new ArrayList<>();
        keywords.add("java");
        //printlist(keywords);
    }

    private static void printlist(List<Object> list){
        for(Object x: list){
            System.out.println(x);
        }
    }

    private static void assignedProblems(){
        List<Integer> numbers = new ArrayList<>();
        numbers.add(new Integer(42));
        //List<Object> objects = numbers; //DOES NOT COMPILE. IF COMPILE ALLOW IT. WE COULD DO WRONG THING BELLOW LINE.
        //objects.add("forty two"); //HERE WE BREAK THE PROMISSE ADDING String inside Integer List.
        System.out.println(numbers.get(1));
    }
}
