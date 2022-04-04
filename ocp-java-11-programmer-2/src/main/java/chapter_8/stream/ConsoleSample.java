package chapter_8.stream;

import java.io.Console;
import java.util.Arrays;
import java.util.Locale;

/**
 * The Console object may not be available, depending on where the code is being called. If it is not available, then
 * System.console() return null. It is imperative that you check for a null value before attempting to use a Console
 * object
 */
public class ConsoleSample {

    public static void main(String[] args) {

        console();
        consoleFormat();
        consoleWithLocale();
        consolePasswordMethod();
    }



    private static void consoleFormat() {
        Console console = System.console();
        if(console == null){
            throw new RuntimeException("Console not available");
        }else {
            console.writer().println("Welcome to Our Zoo!");
            console.format("It has %d animals and employs %d people", 391, 25);
            console.writer().println();
            console.printf("The zoo spans %5.1f acres", 128.91);
        }
    }

    private static void console() {
        Console console = System.console();
        if(console != null){
            String userInput = console.readLine();
            console.writer().println("You entered: " + userInput);
        }else {
            System.err.println("Console not available");
        }
    }

    private static void consoleWithLocale() {
        Console console = System.console();
        console.writer().format(new Locale("fr", "CA"), "Hello World");
    }

    private static void consolePasswordMethod(){

        Console console = System.console();
        if(console == null){
            throw new RuntimeException("Console not available");
        }else {
            String name = console.readLine("Please enter your name: ");
            console.writer().format("Hi %s", name);
            console.writer().println();

            console.format("What is your address? ");
            String address = console.readLine();

            char[] password = console.readPassword("Enter a password " + "between %d and %d characters ", 5, 10);

            char[] verify = console.readPassword("Enter your password again: ");

            console.printf("Passwords " + ((Arrays.equals(password, verify)) ? "match" : "do not match"));
        }

    }
}
