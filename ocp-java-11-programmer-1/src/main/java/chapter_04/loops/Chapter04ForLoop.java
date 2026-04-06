package chapter_04.loops;

/**
 * OCP Programmer I — Cap. 4: Making Decisions
 * Bloco 4: The for loop
 */
public class Chapter04ForLoop {

    public static void main(String[] args) {
        basicForLoop();
        //variableScopeInFor();
        //multipleVariables();
        //infiniteForLoop();
        //forLoopVariations();
    }

    // -------------------------------------------------------------------------
    // 1. for loop básico
    // -------------------------------------------------------------------------
    static void basicForLoop() {
        System.out.println("=== 1. Basic for loop ===");

        for (int i = 0; i < 5; i++) {
            System.out.print(i + " "); // 0 1 2 3 4
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // 2. Scope da variável — só existe dentro do loop
    // -------------------------------------------------------------------------
    static void variableScopeInFor() {
        System.out.println("\n=== 2. Variable Scope ===");

        for (int i = 0; i < 3; i++) {
            System.out.print(i + " "); // i só existe aqui dentro
        }
        // System.out.println(i); // DOES NOT COMPILE — i fora de scope
        System.out.println();
        System.out.println("i não existe aqui fora");
    }

    // -------------------------------------------------------------------------
    // 3. Múltiplas variáveis na inicialização — TÊM de ser do mesmo tipo
    // -------------------------------------------------------------------------
    static void multipleVariables() {
        System.out.println("\n=== 3. Multiple Variables ===");

        // OK — ambas int
        for (int i = 0, j = 10; i < 3; i++, j--) {
            System.out.println("i=" + i + " j=" + j);
        }

        // DOES NOT COMPILE — tipos diferentes na inicialização:
        // for (int i = 0, long j = 10; i < 3; i++) { }
    }

    // -------------------------------------------------------------------------
    // 4. for infinito — as 3 secções são opcionais
    // -------------------------------------------------------------------------
    static void infiniteForLoop() {
        System.out.println("\n=== 4. Infinite for loop (controlado) ===");

        int count = 0;
        for ( ; ; ) {               // compila! equivalente a while(true)
            if (count >= 3) break;  // saída manual
            System.out.println("count = " + count);
            count++;
        }
    }

    // -------------------------------------------------------------------------
    // 5. Variações válidas do for loop
    // -------------------------------------------------------------------------
    static void forLoopVariations() {
        System.out.println("\n=== 5. For loop variations ===");

        // Variável declarada fora — continua acessível depois do loop
        int i = 0;
        for (i = 0; i < 3; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        System.out.println("i depois do loop = " + i); // 3 — ainda acessível!

        // Redeclarar i dentro do for com mesmo nome — DOES NOT COMPILE:
        // for (int i = 0; i < 3; i++) { } // i já foi declarado neste scope
    }
}
// =============================================================================
// Expected output:
// === 1. Basic for loop ===
// 0 1 2 3 4
//
// === 2. Variable Scope ===
// 0 1 2
// i não existe aqui fora
//
// === 3. Multiple Variables ===
// i=0 j=10
// i=1 j=9
// i=2 j=8
//
// === 4. Infinite for loop (controlado) ===
// count = 0
// count = 1
// count = 2
//
// === 5. For loop variations ===
// 0 1 2
// i depois do loop = 3
// =============================================================================
