package chapter_5.formating_dates_times;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterExample {

    public static void main(String[] args) {

        LocalDate date = LocalDate.of(2020, Month.OCTOBER, 20);
        System.out.println(date.getDayOfWeek());  //TUESDAY
        System.out.println(date.getMonth());      //OCTOBER
        System.out.println(date.getYear());       //2020
        System.out.println(date.getDayOfYear());  //294

        LocalTime time = LocalTime.of(11, 12, 34);
        LocalDateTime dt = LocalDateTime.of(date, time);

        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(dt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

        var f = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm");
        System.out.println(dt.format(f)); // October 20, 2020 at 11:12

    }
}
