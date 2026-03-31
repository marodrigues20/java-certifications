package chapter_03.operators;


/**
 * | Operador | Descrição                                  |
 * |----------|--------------------------------------------|
 * | !        | Inverte um valor boolean                   |
 * | +        | Indica número positivo (implícito em Java) |
 * | -        | Nega uma expressão numérica                |
 * | ++       | Incrementa 1                               |
 * | --       | Decrementa 1                               |
 * | (type)   | Cast para outro tipo                       |
 */
public class Bloco3_UnaryNegationComplement {
    public static void main(String[] args) {

        // --- Logical complement: ! (só para boolean) ---
        boolean isAnimalAsleep = false;
        System.out.println(isAnimalAsleep);   // false
        isAnimalAsleep = !isAnimalAsleep;
        System.out.println(isAnimalAsleep);   // true

        // --- Negation: - (só para numéricos) ---
        double zooTemperature = 1.21;
        System.out.println(zooTemperature);           // 1.21
        zooTemperature = -zooTemperature;
        System.out.println(zooTemperature);           // -1.21
        zooTemperature = -(-zooTemperature);          // parênteses necessários!
        System.out.println(zooTemperature);           // -1.21

        // --- Estas linhas NÃO COMPILAM --- (descomenta para ver o erro)
         //int pelican  = !5;          // DOES NOT COMPILE - ! não se aplica a int
        // boolean penguin = -true;    // DOES NOT COMPILE - - não se aplica a boolean
        // boolean peacock = !0;       // DOES NOT COMPILE - ! não se aplica a int
    }
}

/**
 * Por que zooTemperature = -(-zooTemperature) dá -1.21?
 * Porque antes de entrar na expressão, zooTemperature já valia -1.21.
 * Aplicar -(-1.21) devolve 1.21... mas depois o - exterior aplica-se novamente → -1.21. ✅
 * (O livro usa este exemplo exactamente para mostrar que precisas de parênteses para negar duas vezes — sem eles -- seria o operador de decremento!)
 */

