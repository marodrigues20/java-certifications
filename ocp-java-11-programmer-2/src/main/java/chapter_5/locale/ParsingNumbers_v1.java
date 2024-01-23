package chapter_5.locale;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ParsingNumbers_v1 {

    public static void main(String[] args) throws ParseException {

        String s = "40.45";

        var en = NumberFormat.getInstance(Locale.US);

        System.out.println(en.parse(s)); // 40.45

        var fr = NumberFormat.getInstance(Locale.FRANCE);
        System.out.println(fr.parse(s));  // 40
    }
}
