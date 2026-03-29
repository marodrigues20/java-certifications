package chapter_07.overloading;


/**
 * ✅ O compilador sempre escolhe a promoção mais próxima — int vai para long antes de considerar double.
 *
 * Regra simples para lembrar:
 *
 * Quando não há match exato, o compilador sobe a escada de promoção uma degrau de cada vez até encontrar um método compatível.
 *
 * byte → short → int → long → float → double
 *
 * Primitive promoting — int → long → double... — tudo ainda primitivo, só muda o tamanho
 * Autoboxing — int → Integer — converte primitivo para seu wrapper object
 */
public class PromotionTest {

    public static void calc(long x) {
        System.out.println("long");
    }

    public static void calc(double x) {
        System.out.println("double");
    }

    public static void calc(Integer x) { System.out.println("Integer"); }





    public static void main(String[] args) {
        byte b = 10;
        short s = 20;
        int i = 30;
        long l = 40L;
        float f = 5.0f;
        double d = 6.0;

        calc(b);   // long — byte → short → int → long ✅
        calc(s);   // long — short → int → long ✅
        calc(i);   // long — int → long ✅
        calc(l);   // long — match exato ✅
        calc(f);   // double — float → double ✅
        calc(d);   // double — match exato ✅


        calc(5);  // long — promoção tem prioridade sobre autoboxing!
    }
}
// Output:
// long
// long
// long
// long
// double
// double
