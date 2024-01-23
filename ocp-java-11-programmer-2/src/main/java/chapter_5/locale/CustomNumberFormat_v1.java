package chapter_5.locale;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CustomNumberFormat_v1 {

    public static void main(String[] args) {

        double d = 1234567.467;
        NumberFormat f1 = new DecimalFormat("###,###,###.0");
        System.out.println(f1.format(d));  // 1,234,567.5

        NumberFormat f2 = new DecimalFormat("000,000,000.00000");
        System.out.println(f2.format(d));  // 001,234,567.46700

        NumberFormat f3 = new DecimalFormat("$#,###,###.##");
        System.out.println(f3.format(d));  //$1,234,567.47
    }
}
