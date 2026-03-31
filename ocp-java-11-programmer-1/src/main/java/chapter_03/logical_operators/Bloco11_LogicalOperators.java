package chapter_03.logical_operators;

public class Bloco11_LogicalOperators {
    public static void main(String[] args) {

        boolean eyesClosed      = true;
        boolean breathingSlowly = true;

        // --- AND: só true se ambos true ---
        boolean asleep  = eyesClosed & breathingSlowly;
        System.out.println("asleep  = " + asleep);   // true

        // --- OR: true se pelo menos um for true ---
        boolean resting = eyesClosed | breathingSlowly;
        System.out.println("resting = " + resting);  // true

        // --- XOR: true só se forem DIFERENTES ---
        boolean awake   = eyesClosed ^ breathingSlowly;
        System.out.println("awake   = " + awake);    // false — são iguais!

        System.out.println();

        // --- Testa com valores diferentes ---
        boolean a = true;
        boolean b = false;

        System.out.println("a & b  = " + (a & b));   // false
        System.out.println("a | b  = " + (a | b));   // true
        System.out.println("a ^ b  = " + (a ^ b));   // true — são diferentes!

        System.out.println();

        // --- DIFERENÇA CRÍTICA para o exame ---
        // & e | avaliam SEMPRE os dois lados!
        int x = 5;
        boolean resultado = (x > 3) | (++x > 4);  // ++x É avaliado!
        System.out.println("x = " + x);            // 6 — x foi incrementado!
    }
}

