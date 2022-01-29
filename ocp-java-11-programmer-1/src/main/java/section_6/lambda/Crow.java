package section_6.lambda;

import java.util.function.Consumer;

/*
Lambda bodies are allowed to reference some variables from the surrounding code.
 */
public class Crow {

    private String color;
    private static String xpto;

    public void caw(String name) {
        String volume = "loudly";
        Consumer<String> consumer = s ->
                System.out.println(name + " says " + volume + " that she is " + this.color +  ": " +  xpto);
    }
}
