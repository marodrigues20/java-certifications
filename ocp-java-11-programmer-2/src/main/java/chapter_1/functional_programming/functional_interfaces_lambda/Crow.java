package chapter_1.functional_programming.functional_interfaces_lambda;

import java.util.function.Predicate;

public class Crow {

    private String color;

    public void caw(String name){
        String volume = "loudly";
        Predicate<String> p = s -> (name + volume + color).length() == 10;
    }
}
