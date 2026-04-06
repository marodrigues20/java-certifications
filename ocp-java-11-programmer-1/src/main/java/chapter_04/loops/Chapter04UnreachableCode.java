package chapter_04.loops;

/**
 * OCP Programmer I — Cap. 4: Making Decisions
 * Bloco 8: Unreachable Code
 */
public class Chapter04UnreachableCode {

    public static void main(String[] args) {
        //unreachableAfterBreak();
        //unreachableAfterContinue();
        unreachableAfterReturn();
        //reachableInDifferentBlock();
    }

    // -------------------------------------------------------------------------
    // 1. EXAM TRAP — código após break não compila
    // -------------------------------------------------------------------------
    static void unreachableAfterBreak() {
        System.out.println("=== 1. Unreachable after break ===");

        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                break;
                // System.out.println("after break"); // DOES NOT COMPILE — unreachable!
            }
            System.out.print(i + " "); // 0 1 2
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // 2. EXAM TRAP — código após continue não compila
    // -------------------------------------------------------------------------
    static void unreachableAfterContinue() {
        System.out.println("\n=== 2. Unreachable after continue ===");

        for (int i = 0; i < 5; i++) {
            if (i % 2 == 0) {
                continue;
                // System.out.println("after continue"); // DOES NOT COMPILE — unreachable!
            }
            System.out.print(i + " "); // 1 3
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // 3. EXAM TRAP — código após return não compila
    // -------------------------------------------------------------------------
    static void unreachableAfterReturn() {
        System.out.println("\n=== 3. Unreachable after return ===");

        int value = 10;
        if (value > 5) {
            System.out.println("value is greater than 5");
            return;
            // System.out.println("after return"); // DOES NOT COMPILE — unreachable!
        }
        System.out.println("this runs if value <= 5");
    }

    // -------------------------------------------------------------------------
    // 4. Código em bloco DIFERENTE — é alcançável, compila
    // -------------------------------------------------------------------------
    static void reachableInDifferentBlock() {
        System.out.println("\n=== 4. Reachable in different block ===");

        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                break;          // sai do loop
            }
            System.out.print(i + " ");
        }
        // Este código está FORA do loop — é alcançável, compila!
        System.out.println();
        System.out.println("This is reachable — it's outside the loop");
    }
}
// =============================================================================
// Expected output:
// === 1. Unreachable after break ===
// 0 1 2
//
// === 2. Unreachable after continue ===
// 1 3
//
// === 3. Unreachable after return ===
// value is greater than 5
//
// === 4. Reachable in different block ===
// 0 1 2
// This is reachable — it's outside the loop
// =============================================================================
