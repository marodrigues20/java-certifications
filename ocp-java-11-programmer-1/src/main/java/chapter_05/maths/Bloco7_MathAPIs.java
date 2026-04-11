package chapter_05.maths;

public class Bloco7_MathAPIs {

    static void minMax() {
        // Funciona com int, long, float, double
        System.out.println(Math.min(7, 3));       // 3
        System.out.println(Math.max(7, 3));       // 7

        // ARMADILHA: negativos!
        System.out.println(Math.min(-5, -10));    // -10 ← -10 é menor que -5!
        System.out.println(Math.max(-5, -10));    // -5  ← -5 é maior que -10!
    }

    static void round() {
        // Arredonda .5 sempre para CIMA
        System.out.println(Math.round(3.5));   // 4
        System.out.println(Math.round(3.4));   // 3

        // ARMADILHA: negativos!
        System.out.println(Math.round(-3.5));  // -3 ← para CIMA em negativos!
        System.out.println(Math.round(-3.6));  // -4

        // ARMADILHA: devolve long, não double!
        long result = Math.round(9.99);
        System.out.println(result); // 10
        // double d = Math.round(9.99); // ❌ NÃO COMPILA sem cast!
    }

    static void pow() {
        // Devolve sempre double
        System.out.println(Math.pow(2, 3));    // 8.0  → 2³
        System.out.println(Math.pow(3, 2));    // 9.0  → 3²
        System.out.println(Math.pow(4, 0.5));  // 2.0  → raiz quadrada de 4

        // ARMADILHA: devolve double, não int!
        double result = Math.pow(2, 3);        // 8.0 não 8
        System.out.println(result);            // 8.0
    }

    static void random() {
        // 0.0 incluído — 1.0 excluído
        double r = Math.random();
        System.out.println(r >= 0.0 && r < 1.0); // sempre true!

        // Padrão para gerar número entre 0 e 9
        int zeroANove = (int)(Math.random() * 10);
        System.out.println(zeroANove); // 0, 1, 2 ... 9

        // Padrão para gerar número entre 1 e 10
        int umADez = (int)(Math.random() * 10) + 1;
        System.out.println(umADez); // 1, 2, 3 ... 10
    }

    public static void main(String[] args) {
        System.out.println("=== min() e max() ===");
        minMax();

        System.out.println("\n=== round() ===");
        round();

        System.out.println("\n=== pow() ===");
        pow();

        System.out.println("\n=== random() ===");
        random();
    }
}

