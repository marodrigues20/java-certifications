package chapter_5.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

public class YourOwnTime {

    public static void main(String[] args) {

        LocalTime time1 = LocalTime.of(6, 15); // hour and minute
        LocalTime time2 = LocalTime.of(6, 15, 30); // + seconds
        LocalTime time3 = LocalTime.of(6, 15, 30, 200); // + nanoseconds


        var dt1 = LocalDateTime.of(2020, Month.OCTOBER, 20, 6, 15, 30);

        LocalDate date = LocalDate.of(2020, Month.OCTOBER, 20);
        LocalTime time = LocalTime.of(6, 15);
        var dt2 = LocalDateTime.of(date, time);

    }
}
