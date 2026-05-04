package chapter_9.map;

import chapter_9.domain.Person;

import java.util.*;
import java.util.stream.Stream;

public class MyHashMap {

    static void main() {

        Map<String, Person> mapList = Map.of(
                "1955-03-14", new Person(71, "Vania"),
                "2001-07-22", new Person(25, "Richard"),
                "1996-11-05", new Person(30, "Alexandre"),
                "1986-09-18", new Person(40, "Nick"),
                "1976-04-30", new Person(50, "Philip"));

        Map<String, Person> initialMap = new HashMap<>(mapList);
        initialMap.put("1930-04-30", new Person(90, "Samanta"));

        // Remind: The Map is not sorted. What is being sorged here is the stream
        Stream<Person> personStream = initialMap.values()
                        .stream()
                                .sorted(Comparator.comparing(Person::name));

        personStream.forEach( t -> System.out.println("Sorted by name:" + t.name()));



        System.out.println("************* get Method *************************");
        // HashMap calcula via hashcode a posição onde o item é armazenado dentro do array (contiguous memory)
        System.out.println("Let's see Samanta age: " + initialMap.get("1930-04-30"));


        System.out.println("************ HashCode Method **************");
        // HashCode do map inteiro
        System.out.println(initialMap.hashCode());
        // ✅ mais útil — mostra o hashCode da chave
        System.out.println("HashCode of key 1930-04-30: " + "1930-04-30".hashCode());

        System.out.println("*************** containsKey  → check by hashCode + equals on KEY *******************");
        System.out.println("Let's see if contains key equal to 1930-04-30: " + initialMap.containsKey("1930-04-30"));

        System.out.println("*********** containsValue → check by equals on VALUE — O(n) **************");
        Person person = new Person(90, "Samanta");
        System.out.println("Let's try containsValue Method: " + initialMap.containsValue(person) );


        System.out.println("************ getOrDefault Method ********************");
        System.out.println("Let's check the method getOrDefault: " + initialMap.getOrDefault("1920-04-30", new Person( 0,"Not Found" )));
        System.out.println("Let's check the method getOrDefault: " + initialMap.getOrDefault("1930-04-30", new Person( 0,"Not Found" )));


        System.out.println("*********** putIfAbsent Method *******************");
        // Só adiciona se a chave NÃO existir
        initialMap.putIfAbsent("1930-04-30", new Person(90, "Roberto"));
        // Samanta continua — chave já existe

        System.out.println("*********** replace Method *******************");
        // Substitui o valor de uma chave existente
        initialMap.replace("1930-04-30", new Person(90, "Roberto"));

        System.out.println("************ remove Method ********************");
        // Remove só se a chave E o valor baterem
        initialMap.remove("1930-04-30", new Person(90, "Samanta")); // ❌ não remove — já foi substituída
        initialMap.remove("1930-04-30", new Person(90, "Roberto")); // ✅ remove — valor bate!


        System.out.println("************ merge Method ********************");
        // Se chave não existe — insere. Se existe — aplica a função
        initialMap.merge("1930-04-30", new Person(90, "Roberto"),
                (oldValue, newValue) -> newValue);


        // Só cria se a chave não existe
        initialMap.computeIfAbsent("1920-04-30",
                key -> new Person(100, "NewPerson"));
        // "1920-04-30" não existe → insere Person(100, "NewPerson")

        initialMap.computeIfAbsent("1930-04-30",
                key -> new Person(100, "NewPerson"));
        // "1930-04-30" já existe → não faz nada


        initialMap.compute("1930-04-30",
                (key, oldValue) -> new Person(oldValue.age() + 1, oldValue.name()));
        // oldValue = Person(90, "Roberto") → vira Person(91, "Roberto")

        initialMap.compute("1920-04-30",
                (key, oldValue) -> oldValue == null
                        ? new Person(0, "NewPerson")   // chave não existe
                        : new Person(oldValue.age() + 1, oldValue.name())); // chave existe


        System.out.println("************* Iterate over Keys *************");
        Set<String> keys = initialMap.keySet();
        keys.forEach(System.out::println);

        System.out.println("************* Iterate over values *************");
        Collection<Person> listValues = initialMap.values();
        listValues.forEach(System.out::println);

        System.out.println("*************** Iterate over Map *********************");
        Set<Map.Entry<String, Person>> initialSet = initialMap.entrySet();

        for (Map.Entry<String, Person> entry : initialSet){
            System.out.println(entry);
        }
    }
}
