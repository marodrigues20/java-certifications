package chapter_03.operators;

public class Bloco6_NumericPromotion {
    public static void main(String[] args) {

        // --- Regra 1: int + long → resultado é long ---
        int x = 1;
        long y = 33;
        var z1 = x * y;
        System.out.println(((Object)z1).getClass().getSimpleName()); // Long

        // --- Regra 2: float + double → resultado é double ---
        // ATENÇÃO: 2.1 sem 'f' é double! Esta linha NÃO COMPILA:
        // float y2 = 2.1; // DOES NOT COMPILE
        double x2 = 39.21;
        float y2  = 2.1f;   // precisa do 'f'!
        var z2 = x2 + y2;
        System.out.println(((Object)z2).getClass().getSimpleName()); // Double

        // --- Regra 3: short + short → resultado é int (NÃO short!) ---
        short a = 10;
        short b = 3;
        var z3 = a * b;
        System.out.println(((Object)z3).getClass().getSimpleName()); // Integer

        // Por isso isto NÃO COMPILA:
        // short capybara = a * b; // DOES NOT COMPILE — resultado é int!

        // --- Regra 4: short + float + double → resultado é double ---
        short w  = 14;
        float fx = 13;
        double d = 30;
        var z4 = w * fx / d;
        // Passo 1: w (short) → int → float (por causa do fx)
        // Passo 2: w*fx é float → double (por causa do d)
        System.out.println(((Object)z4).getClass().getSimpleName()); // Double
    }
}