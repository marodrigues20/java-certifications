package chapter_5.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceBundle_v1 {

    private static void printWelcomeMessage(Locale locale) {
        var rb = ResourceBundle.getBundle("Zoo", locale);
        System.out.println(rb.getString("hello") + ", " + rb.getString("open"));

        rb.keySet().stream()
                .map(k -> k + ":" + rb.getString(k))
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
        var us = new Locale("en", "US");
        var france = new Locale("fr", "FR");
        printWelcomeMessage(us);
        printWelcomeMessage(france);
    }
}
