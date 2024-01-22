package chapter_5.locale;

import java.util.Locale;

public class LocaleSelected_v3 {

    public static void main(String[] args) {

        Locale l1 = new Locale.Builder()
                .setLanguage("en")
                .setRegion("US")
                .build();

        Locale l2 = new Locale.Builder()
                .setRegion("US")
                .setLanguage("en")
                .build();
    }
}
