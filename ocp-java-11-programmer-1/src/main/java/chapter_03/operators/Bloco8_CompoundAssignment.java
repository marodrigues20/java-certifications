package chapter_03.operators;

public class Bloco8_CompoundAssignment {
    public static void main(String[] args) {

        // --- Uso básico ---
        int camel = 2, giraffe = 3;
        camel = camel * giraffe;  // assignment simples → 6
        System.out.println("camel = " + camel); // 6

        camel = 2; // reset
        camel *= giraffe;         // compound → mesmo resultado
        System.out.println("camel = " + camel); // 6

        // --- O super poder: cast automático! ---
        long goat = 10;
        int sheep = 5;

        // ❌ NÃO COMPILA — long não cabe em int sem cast explícito
        // sheep = sheep * goat;

        // ✅ COMPILA — compound faz o cast para int automaticamente
        sheep *= goat;
        System.out.println("sheep = " + sheep); // 50

        // --- Assignment return value ---
        // O resultado de um assignment É o valor atribuído!
        long wolf   = 5;
        long coyote = (wolf = 3); // wolf recebe 3, coyote recebe o resultado = 3
        System.out.println("wolf   = " + wolf);   // 3
        System.out.println("coyote = " + coyote); // 3

        // --- Armadilha clássica do exame! ---
        // Parece um teste de igualdade mas é um assignment!
        boolean healthy = false;
        if (healthy = true)               // atribui true a healthy, não compara!
            System.out.println("Good!");  // imprime "Good!" sempre!
    }
}

/**
 * ⚠️ Armadilha do exame — = vs ==
 * if (healthy = true)   // ASSIGNMENT — atribui true, resultado é true  ✅ compila
 * if (healthy == true)  // COMPARISON — compara o valor de healthy       ✅ compila
 *
 * O exame adora meter = onde esperias == dentro de um if.
 * O código compila — mas o comportamento é completamente diferente!
 */
