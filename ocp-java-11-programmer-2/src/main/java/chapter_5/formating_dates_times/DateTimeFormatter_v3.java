package chapter_5.formating_dates_times;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatter_v3 {

    public static void main(String[] args) {


        var dateTime = LocalDateTime.of(2020, Month.OCTOBER, 20, 6, 15, 30);
        var formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");

        System.out.println(dateTime.format(formatter)); // 10/20/2020 06:15:30
        System.out.println(formatter.format(dateTime)); // 10/20/2020 06:15:30
    }
}
