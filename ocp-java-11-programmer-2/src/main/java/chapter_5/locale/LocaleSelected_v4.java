package chapter_5.locale;

import java.util.Locale;

public class LocaleSelected_v4 {

    public static void main(String[] args) {
        System.out.println(Locale.getDefault());   //en_GB
        Locale locale = new Locale("fr");
        Locale.setDefault(locale);               // change the default
        System.out.println(Locale.getDefault()); // fr
    }
}
