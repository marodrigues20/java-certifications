package chapter_03.ternary;

public class Bloco13_TernaryOperator {
    public static void main(String[] args) {

        // --- Uso básico ---
        int owl = 5;
        int food = owl < 2 ? 3 : 4;
        System.out.println("food = " + food); // 4

        // --- Parênteses opcionais mas recomendados ---
        int food2 = (owl < 2) ? 3 : 4;
        System.out.println("food2 = " + food2); // 4

        // --- Tipos diferentes nas expressões ---
        int stripes = 7;

        // ✅ COMPILA — println aceita Object, converte tudo
        System.out.print((stripes > 5) ? 21 : "Zebra"); // 21
        System.out.println();

        // ❌ NÃO COMPILA — "Horse" não pode ser atribuído a int!
        // int animal = (stripes < 9) ? 3 : "Horse";

        // --- Unperformed side effect --- (armadilha do exame!)
        int sheep = 1;
        int zzz   = 1;

        // condição true → só sheep++ é avaliado!
        int sleep = zzz < 10 ? sheep++ : zzz++;
        System.out.println("sheep = " + sheep); // 2 — foi incrementado!
        System.out.println("zzz   = " + zzz);   // 1 — NÃO foi incrementado!

        System.out.println();

        // condição false → só zzz++ é avaliado!
        sheep = 1; // reset
        zzz   = 1; // reset
        int sleep2 = sheep >= 10 ? sheep++ : zzz++;
        System.out.println("sheep = " + sheep); // 1 — NÃO foi incrementado!
        System.out.println("zzz   = " + zzz);   // 2 — foi incrementado!
    }
}

