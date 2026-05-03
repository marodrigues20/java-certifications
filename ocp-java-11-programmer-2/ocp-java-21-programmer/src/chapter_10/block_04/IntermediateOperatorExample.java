package chapter_10.block_04;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class IntermediateOperatorExample {

    public static void main(String[] args) {

        System.out.println("************** map() method ****************************");
        Stream.of("Alex", "Richard", "Jerry", "Mathews")
                .map(String::length)
                .collect(Collectors.toList())
                .forEach(System.out::println);




        /**
         * Stream<Map<String,String>>
         *
         * [  Map1: { 1980-08-18=Alex, 1990-02-02=Mario, 2026-02-10=Vania }  ]
         * [  Map2: { 1954-04-40=Richard, 1960-07-30=Nick }                   ]
         *
         * Flatmap abre cada Map e coloca todas as entries no mesmo nível
         *
         * Stream<Map.Entry<String,String>>
         *
         * 1980-08-18=Alex
         * 1990-02-02=Mario
         * 2026-02-10=Vania
         * 1954-04-40=Richard
         * 1960-07-30=Nick
         */
        System.out.println("************* flatMap() Method *********************");
        List<Map<String,String>> mapList = List.of(
                Map.of("1980-08-18", "Alex",
                        "1990-02-02", "Mario",
                        "2026-02-10", "Vania"),
                Map.of("1954-04-40", "Richard",
                        "1960-07-30", "Nick")
        );

        List<Map.Entry<String,String>> listMap = mapList.stream()
                .flatMap(t -> t.entrySet().stream())
                .collect(Collectors.toList());

        System.out.println(listMap);


            Map<String, String> map = mapList.stream()
                .flatMap(t -> t.entrySet().stream())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue));

        map.forEach((k,v) -> System.out.println(k + " <-> "+ v));


        System.out.println("*************** sorted() method *******************");

        List<Person> listPerson = List.of(new Person(18,"Alex"),
                new Person(25, "Margaratti"),
                new Person(30, "Miguel"));

        listPerson.stream()
                .sorted(Comparator.comparing(Person::age).thenComparing(Person::name))
                .forEach(System.out::println);

        System.out.println("*************** peek() method *******************");
        listPerson.stream()
                .peek(System.out::println)
                .map(Person::name)
                .sorted(Comparator.naturalOrder())
                .forEach(System.out::println);


        System.out.println("*************** skip() method *******************");
        listPerson.stream()
                .skip(2)
                .forEach(System.out::println);


        System.out.println("*************** limit() method *******************");
        listPerson.stream()
                .limit(2)
                .forEach(System.out::println);


    }
}
