package chapter_5.formating_dates_times;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatter_v2 {

    public static void main(String[] args) {

        var dt = LocalDateTime.of(2020, Month.OCTOBER, 20, 6, 15, 30);

        var formatter1 = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");
        System.out.println(dt.format(formatter1));

        var formatter2 = DateTimeFormatter.ofPattern("MM_yyyy_-_dd");
        System.out.println(dt.format(formatter2));

        var formatter3 = DateTimeFormatter.ofPattern("h:mm z");
        System.out.println(dt.format(formatter3));
    }
}
