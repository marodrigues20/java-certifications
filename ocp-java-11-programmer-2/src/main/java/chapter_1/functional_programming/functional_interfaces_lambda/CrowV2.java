package chapter_1.functional_programming.functional_interfaces_lambda;

import java.util.function.Predicate;


/**
 * In this example, the values of name and volume are assigned new values on line 16 and 17. For this reason,
 * the lambda expression declared on lines 11 and 12 does not compile since it references local variables that are not
 * final or effectively final. If lines 16 and 17 were removed, then the class would compile.
 */
public class CrowV2 {

    private String color;

    public void caw(String name){
        String volume = "loudly";
        color = "allowed";
        //name = "not allowed";
        //volume = "not allowed";

        Predicate<String> p = s -> (name + volume + color).length() == 9; // DOES NOT COMPILE
    }
}
