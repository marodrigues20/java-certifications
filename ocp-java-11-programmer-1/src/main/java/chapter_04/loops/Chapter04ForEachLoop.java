package chapter_04.loops;

import java.util.ArrayList;
import java.util.List;

/**
 * OCP Programmer I — Cap. 4: Making Decisions
 * Bloco 5: The for-each loop
 */
public class Chapter04ForEachLoop {

    public static void main(String[] args) {
        //forEachWithArray();
        //forEachWithList();
        //forEachWithVar();
        //forEachDoesNotModifyOriginal();
        forEachVsFor();
    }

    // -------------------------------------------------------------------------
    // 1. for-each com array
    // -------------------------------------------------------------------------
    static void forEachWithArray() {
        System.out.println("=== 1. for-each com array ===");

        String[] names = {"Lisa", "Kevin", "Roger"};
        for (String name : names) {
            System.out.print(name + ", ");
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // 2. for-each com List
    // -------------------------------------------------------------------------
    static void forEachWithList() {
        System.out.println("\n=== 2. for-each com List ===");

        List<String> values = new ArrayList<>();
        values.add("Lisa");
        values.add("Kevin");
        values.add("Roger");

        for (String value : values) {
            System.out.print(value + ", ");
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // 3. for-each com var (Java 10+)
    // -------------------------------------------------------------------------
    static void forEachWithVar() {
        System.out.println("\n=== 3. for-each com var ===");

        List<String> values = new ArrayList<>();
        values.add("Lisa");
        values.add("Kevin");
        values.add("Roger");

        // var é inferido como String pelo compilador
        for (var value : values) {
            System.out.print(value + ", ");
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // 4. EXAM TRAP — modificar a variável do loop não altera o array original
    // -------------------------------------------------------------------------
    static void forEachDoesNotModifyOriginal() {
        System.out.println("\n=== 4. for-each não modifica o original ===");

        int[] numbers = {1, 2, 3, 4, 5};

        for (int n : numbers) {
            n = n * 2; // modifica a cópia local, NÃO o array original!
        }

        // array continua igual
        for (int n : numbers) {
            System.out.print(n + " "); // 1 2 3 4 5 — sem alteração
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // 5. for-each vs for — quando usar cada um
    // -------------------------------------------------------------------------
    static void forEachVsFor() {
        System.out.println("\n=== 5. for-each vs for ===");

        String[] names = {"Lisa", "Kevin", "Roger"};

        // for-each — mais legível, sem índice
        System.out.print("for-each: ");
        for (String name : names) {
            System.out.print(name + " ");
        }
        System.out.println();

        // for normal — necessário quando precisas do índice
        System.out.print("for com indice: ");
        for (int i = names.length - 1; i >= 0; i--) {
            System.out.print(names[i] + " "); // ao contrário — impossível com for-each
        }
        System.out.println();
    }
}
// =============================================================================
// Expected output:
// === 1. for-each com array ===
// Lisa, Kevin, Roger,
//
// === 2. for-each com List ===
// Lisa, Kevin, Roger,
//
// === 3. for-each com var ===
// Lisa, Kevin, Roger,
//
// === 4. for-each não modifica o original ===
// 1 2 3 4 5
//
// === 5. for-each vs for ===
// for-each: Lisa Kevin Roger
// for com indice: Roger Kevin Lisa
// =============================================================================
