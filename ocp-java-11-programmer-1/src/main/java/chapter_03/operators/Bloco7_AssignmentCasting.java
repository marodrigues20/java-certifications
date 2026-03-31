package chapter_03.operators;

public class Bloco7_AssignmentCasting {
    public static void main(String[] args) {

        // --- Casting básico ---
        int trainer      = (int) 1.0;       // 1  — corta o .0
        short ticketTaker = (short) 1921222; // OVERFLOW! → 20678
        int usher        = (int) 9f;         // 9  — remove o f
        long manager     = 192301398193810323L; // L obrigatório!

        System.out.println("trainer = "      + trainer);       // 1
        System.out.println("ticketTaker = "  + ticketTaker);   // 20678 (overflow!)
        System.out.println("usher = "        + usher);         // 9
        System.out.println("manager = "      + manager);

        // --- Overflow clássico ---
        System.out.println(2147483647 + 1);  // -2147483648 (MAX_INT + 1 = MIN_INT!)

        // --- short * short → resultado é int (Regra 3 de promoção!) ---
        short mouse   = 10;
        short hamster = 3;
        // short capybara = mouse * hamster;         // DOES NOT COMPILE — resultado é int!
        short capybara = (short)(mouse * hamster);   // cast obrigatório → 30
        System.out.println("capybara = " + capybara); // 30

        // --- Casting apenas no mouse NÃO chega! ---
        // short errado = (short)mouse * hamster;    // DOES NOT COMPILE
        // Porquê? Cast é unário → aplica-se só ao mouse,
        // depois mouse*hamster volta a ser int pelo operador binário!

        // --- Estas linhas NÃO COMPILAM ---
        // float egg    = 2.0 / 9;       // DOES NOT COMPILE — 2.0 é double!
        // int tadpole  = (int)5 * 2L;   // DOES NOT COMPILE — resultado é long!
        // short frog   = 3 - 2.0;       // DOES NOT COMPILE — resultado é double!
    }
}

