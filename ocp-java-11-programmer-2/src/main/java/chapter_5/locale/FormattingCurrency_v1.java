package chapter_5.locale;

import java.text.NumberFormat;

public class FormattingCurrency_v1 {

    public static void main(String[] args) {

        double price = 48;
        var myLocale = NumberFormat.getCurrencyInstance();
        System.out.println(myLocale.format(price));
    }
}
