package chapter_03.operators;

public class OverflowVsLong {
    public static void main(String[] args) {

        // ❌ Overflow — dois int, resultado int, já rebentou antes do println
        System.out.println(2147483647 + 1);       // -2147483648

        // ✅ Sem overflow — 1L força a promoção para long ANTES da soma
        System.out.println(2147483647 + 1L);      //  2147483648

        // ✅ Sem overflow — cast força promoção antes da soma
        System.out.println((long)2147483647 + 1); //  2147483648

        // ❌ Ainda overflow! — cast aplica-se ao resultado já rebentado
        System.out.println((long)(2147483647 + 1)); // -2147483648
    }
}

/**
 * Regra de ouro:
 * A promoção numérica só acontece quando os operandos são de tipos diferentes.
 * Se os dois operandos são int, o cálculo é feito em int — não importa o contexto à volta.
 */
