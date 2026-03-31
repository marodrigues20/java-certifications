package chapter_03.equality_operators;

public class Bloco9_EqualityOperators {
    public static void main(String[] args) {

        // --- Primitivos: compara VALORES ---
        int a = 5;
        int b = 5;
        System.out.println(a == b);        // true — mesmo valor

        // Promoção numérica no ==
        int x = 5;
        double y = 5.0;
        System.out.println(x == y);        // true — x promovido para double

        // --- Objectos: compara REFERÊNCIAS (endereço em memória) ---
        String s1 = new String("Java");
        String s2 = new String("Java");
        String s3 = s1;

        System.out.println(s1 == s2);      // false — objectos diferentes em memória!
        System.out.println(s1 == s3);      // true  — apontam para o mesmo objecto!
        System.out.println(s1.equals(s2)); // true  — conteúdo igual!

        // --- null ---
        System.out.println(null == null);  // true

        // --- Armadilha do exame: = vs == ---
        boolean healthy = false;
        if (healthy = true)                // ASSIGNMENT não COMPARAÇÃO!
            System.out.println("Good!");   // imprime sempre!

        // --- Estas linhas NÃO COMPILAM ---
        // boolean monkey = true == 3;      // DOES NOT COMPILE — boolean vs int
        // boolean ape    = false != "Grape"; // DOES NOT COMPILE — boolean vs String
    }
}

