package chapter_04.loops;

/**
 * OCP Programmer I — Cap. 4: Making Decisions
 * Bloco 3: while e do/while loops
 */
public class Chapter04WhileLoops {

    public static void main(String[] args) {
        whileZeroExecutions();
        whileMultipleExecutions();
        doWhileAlwaysOnce();
        infiniteLoopExample();
    }

    // -------------------------------------------------------------------------
    // 1. while — executa ZERO vezes porque condição é false logo no início
    // -------------------------------------------------------------------------
    static void whileZeroExecutions() {
        System.out.println("=== 1. while — zero execuções ===");

        int full = 5;
        while (full < 5) {              // false logo à partida
            System.out.println("Not full!");
            full++;
        }
        System.out.println("Loop terminou. full = " + full); // 5
    }

    // -------------------------------------------------------------------------
    // 2. while — executa múltiplas vezes
    // -------------------------------------------------------------------------
    static void whileMultipleExecutions() {
        System.out.println("\n=== 2. while — múltiplas execuções ===");

        int roomInBelly = 5;
        int bitesOfCheese = 3;

        while (bitesOfCheese > 0 && roomInBelly > 0) {
            bitesOfCheese--;
            roomInBelly--;
        }
        System.out.println(bitesOfCheese + " pieces of cheese left"); // 0
        System.out.println(roomInBelly + " room left in belly");      // 2
    }

    // -------------------------------------------------------------------------
    // 3. do/while — executa SEMPRE pelo menos uma vez
    // -------------------------------------------------------------------------
    static void doWhileAlwaysOnce() {
        System.out.println("\n=== 3. do/while — sempre executa pelo menos uma vez ===");

        // Mesmo com condição false, o corpo executa uma vez
        int lizard = 0;
        do {
            lizard++;
        } while (false);            // condição false — mas já executou uma vez!

        System.out.println("lizard = " + lizard); // 1

        // Comparação directa: while vs do/while com condição false à partida
        int x = 0;
        //while (false) {
        //    x++;                    // nunca executa
        //}
        System.out.println("while com false: x = " + x); // 0

        int y = 0;
        do {
            y++;                    // executa uma vez
        } while (false);
        System.out.println("do/while com false: y = " + y); // 1
    }

    // -------------------------------------------------------------------------
    // 4. Infinite loop — cuidado! variável de controlo nunca muda
    // -------------------------------------------------------------------------
    static void infiniteLoopExample() {
        System.out.println("\n=== 4. Infinite Loop — exemplo seguro ===");

        int pen = 2;
        int pigs = 5;
        int count = 0;

        // Versão PERIGOSA (comentada para não bloquear):
        // while (pen < 10)
        //     pigs++;          // pen nunca muda → loop infinito!

        // Versão CORRIGIDA — a variável de controlo avança:
        while (pen < 10) {
            pigs++;
            pen++;              // pen avança → loop termina
            count++;
        }
        System.out.println("Loop executou " + count + " vezes"); // 8
        System.out.println("pigs = " + pigs);                    // 13
    }
}
// =============================================================================
// Expected output:
// === 1. while — zero execuções ===
// Loop terminou. full = 5
//
// === 2. while — múltiplas execuções ===
// 0 pieces of cheese left
// 2 room left in belly
//
// === 3. do/while — sempre executa pelo menos uma vez ===
// lizard = 1
// while com false: x = 0
// do/while com false: y = 1
//
// === 4. Infinite Loop — exemplo seguro ===
// Loop executou 8 vezes
// pigs = 13
// =============================================================================
