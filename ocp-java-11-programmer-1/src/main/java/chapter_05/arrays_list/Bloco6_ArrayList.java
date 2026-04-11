package chapter_05.arrays_list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Pontos-bomba para o exame:
 *
 * new ArrayList<>(10) → capacidade inicial, não é o size! size() continua 0
 * remove(1) remove pelo índice; remove(Integer.valueOf(1)) remove pelo valor — armadilha clássica!
 * Arrays.asList() → tamanho fixo — set() funciona mas add()/remove() dão exception!
 * ArrayList.equals() compara conteúdo ✅ — ao contrário de arrays normais!
 * Collections.sort() para ArrayList; Arrays.sort() para arrays — não confundir!
 */
public class Bloco6_ArrayList {

    // Formas de criar um ArrayList
    static void criacao() {
        ArrayList<String> list1 = new ArrayList<>();         // vazio
        ArrayList<String> list2 = new ArrayList<>(10);      // capacidade inicial (não size!)
        ArrayList<String> list3 = new ArrayList<>(list1);   // cópia de outra lista

        // Forma mais comum no mundo real — usar interface List
        List<String> list4 = new ArrayList<>();

        // Java antigo (antes de Java 5) — sem generics, evitar!
        ArrayList listAntiga = new ArrayList(); // ⚠️ raw type — compila mas evitar

        System.out.println(list1.size()); // 0
        System.out.println(list2.size()); // 0 ← capacidade ≠ size!
    }

    // add(), get(), set(), remove()
    static void addGetSetRemove() {
        List<String> birds = new ArrayList<>();
        birds.add("hawk");               // [hawk]
        birds.add(1, "robin");           // [hawk, robin]   — insere no índice 1... mas hawk é índice 0!
        birds.add(0, "blue jay");        // [blue jay, hawk, robin]

        System.out.println(birds);       // [blue jay, hawk, robin]
        System.out.println(birds.get(1)); // hawk

        birds.set(1, "eagle");           // [blue jay, eagle, robin]
        System.out.println(birds);

        // remove() por índice vs por valor — ARMADILHA!
        birds.remove(0);                 // remove "blue jay" (índice 0)
        System.out.println(birds);       // [eagle, robin]

        birds.remove("robin");           // remove por valor
        System.out.println(birds);       // [eagle]
    }

    // size(), isEmpty(), clear(), contains(), equals()
    static void outrosMetodos() {
        List<String> birds = new ArrayList<>();
        birds.add("hawk");
        birds.add("robin");

        System.out.println(birds.isEmpty());    // false
        System.out.println(birds.size());       // 2
        System.out.println(birds.contains("hawk")); // true
        System.out.println(birds.contains("eagle")); // false

        // equals() em ArrayList compara CONTEÚDO (ao contrário de arrays!)
        List<String> birds2 = new ArrayList<>();
        birds2.add("hawk");
        birds2.add("robin");
        System.out.println(birds.equals(birds2)); // true ✅

        birds.clear();
        System.out.println(birds.isEmpty());    // true
        System.out.println(birds.size());       // 0
    }

    // Autoboxing — ArrayList não aceita primitivos
    static void autoboxing() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);    // autoboxing: int 1 → Integer.valueOf(1)
        numbers.add(2);
        numbers.add(3);

        int first = numbers.get(0); // unboxing: Integer → int automático
        System.out.println(first);  // 1

        // ARMADILHA: remove(int index) vs remove(Integer value)
        numbers.remove(1);          // remove índice 1 → [1, 3]
        System.out.println(numbers);

        numbers.remove(Integer.valueOf(1)); // remove VALOR 1 → [3]
        System.out.println(numbers);
    }

    // Conversão ArrayList ↔ Array
    static void conversao() {
        // ArrayList → Array
        List<String> list = new ArrayList<>();
        list.add("hawk");
        list.add("robin");

        Object[] objectArray = list.toArray();          // Object[]
        String[] stringArray = list.toArray(new String[0]); // String[]
        System.out.println(Arrays.toString(stringArray)); // [hawk, robin]

        // Array → ArrayList (ARMADILHA: tamanho fixo!)
        String[] array = {"hawk", "robin"};
        List<String> fixedList = Arrays.asList(array);  // ⚠️ tamanho FIXO!
        // fixedList.add("eagle"); → UnsupportedOperationException!
        fixedList.set(0, "eagle"); // ✅ substituir é permitido
        System.out.println(fixedList); // [eagle, robin]

        // Java 9+ — List.of() → imutável (nem add nem set!)
        List<String> imutable = List.of("hawk", "robin");
        // imutable.add("eagle"); → UnsupportedOperationException!
        // imutable.set(0, "eagle"); → UnsupportedOperationException!
    }

    // Sorting
    static void sorting() {
        List<Integer> numbers = new ArrayList<>();
        numbers.add(99);
        numbers.add(5);
        numbers.add(81);

        Collections.sort(numbers);
        System.out.println(numbers); // [5, 81, 99]
    }

    public static void main(String[] args) {
        //System.out.println("=== Criação ===");
        //criacao();

        //System.out.println("\n=== add / get / set / remove ===");
        //addGetSetRemove();

        //System.out.println("\n=== Outros Métodos ===");
        //outrosMetodos();

        //System.out.println("\n=== Autoboxing ===");
        //autoboxing();

        //System.out.println("\n=== Conversão ArrayList <-> Array ===");
        //conversao();

        System.out.println("\n=== Sorting ===");
        sorting();
    }
}

