package chapter_04.if_statement;

/**
 * OCP Programmer I — Cap. 4: Making Decisions
 * Bloco 1: Statements, Blocks & the if Statement
 */
public class Chapter04IfStatement {

    public static void main(String[] args) {
        statementVsBlock();
        basicIfElse();
        indentationTrap();
        unreachableBranch();
        booleanConditionTrap();
    }

    // -------------------------------------------------------------------------
    // 1. Statement vs Block — ambos são equivalentes
    // -------------------------------------------------------------------------
    static void statementVsBlock() {
        System.out.println("=== 1. Statement vs Block ===");

        int patrons = 0;

        // Single statement
        patrons++;

        // Exactly the same as a block
        {
            patrons++;
        }

        System.out.println("patrons = " + patrons); // 2
    }

    // -------------------------------------------------------------------------
    // 2. if / else if / else básico
    // -------------------------------------------------------------------------
    static void basicIfElse() {
        System.out.println("\n=== 2. if / else if / else ===");

        int hourOfDay = 14;

        if (hourOfDay < 11) {
            System.out.println("Good Morning");
        } else if (hourOfDay < 15) {
            System.out.println("Good Afternoon");   // este nunca executa
        } else {
            System.out.println("Good Evening");
        }
    }

    // -------------------------------------------------------------------------
    // 3. EXAM TRAP — indentação enganosa sem chavetas
    // -------------------------------------------------------------------------
    static void indentationTrap() {
        System.out.println("\n=== 3. Indentation Trap ===");

        int hourOfDay = 15;

        // Parece que as duas linhas estão dentro do if... mas só a primeira está!
        if (hourOfDay < 11)
            System.out.println("Good Morning");            // controlado pelo if
            System.out.println("This ALWAYS runs!");       // SEMPRE executa!
    }

    // -------------------------------------------------------------------------
    // 4. EXAM TRAP — ordem errada cria branch UNREACHABLE
    // -------------------------------------------------------------------------
    static void unreachableBranch() {
        System.out.println("\n=== 4. Unreachable Branch ===");

        int hourOfDay = 8;

        // Se hourOfDay < 11, então é também < 15 por definição.
        // O segundo else if NUNCA pode ser alcançado. Compila mas nunca imprime.
        if (hourOfDay < 15) {
            System.out.println("Good Afternoon (fires for ALL values < 15)");
        } else if (hourOfDay < 11) {
            System.out.println("Good Morning — UNREACHABLE, never prints!");
        } else {
            System.out.println("Good Evening");
        }
    }

    // -------------------------------------------------------------------------
    // 5. EXAM TRAP — condição do if TEM de ser boolean
    // -------------------------------------------------------------------------
    static void booleanConditionTrap() {
        System.out.println("\n=== 5. Boolean Condition Trap ===");

        int x = 5;

        // if (x) { }        // DOES NOT COMPILE — int não é boolean
        // if (x = 5) { }    // DOES NOT COMPILE — assignment não é boolean

        if (x == 5) {         // OK — == devolve boolean
            System.out.println("x == 5 compiles fine");
        }

        boolean raining = true;
        if (raining) {        // OK — variável boolean directa
            System.out.println("Bring an umbrella!");
        }
    }
}
// =============================================================================
// Expected output:
// === 1. Statement vs Block ===
// patrons = 2
//
// === 2. if / else if / else ===
// Good Afternoon
//
// === 3. Indentation Trap ===
// This ALWAYS runs!
//
// === 4. Unreachable Branch ===
// Good Afternoon (fires for ALL values < 15)
//
// === 5. Boolean Condition Trap ===
// x == 5 compiles fine
// Bring an umbrella!
// =============================================================================
