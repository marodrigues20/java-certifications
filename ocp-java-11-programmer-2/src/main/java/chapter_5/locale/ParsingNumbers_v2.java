package chapter_5.locale;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ParsingNumbers_v2 {

    public static void main(String[] args) throws ParseException {

        String income = "$92,807.99";
        var cf = NumberFormat.getCurrencyInstance(Locale.US);
        double value = (Double) cf.parse(income);
        System.out.println(value);  //92807.99
    }
}
