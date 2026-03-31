package chapter_03.operators;


/**
 * | Tipo      | Operandos | Exemplo     |
 * |-----------|-----------|-------------|
 * | Unário    | 1         | `++a`, `!b` |
 * | Binário   | 2         | `a + b`     |
 * | Ternário  | 3         | `a ? b : c` |
 */
public class Bloco1_TiposDeOperadores {


    public static void main(String[] args) {

        // Exemplo do livro: ordem de avaliação não é sempre esquerda-direita
        int cookies = 4;
        double reward = 3 + 2 * --cookies;
        // 1. --cookies → cookies = 3
        // 2. 2 * 3 = 6
        // 3. 3 + 6 = 9
        // 4. 9 (int) promovido para 9.0 (double)
        System.out.print("Zoo animal receives: " + reward + " reward points");
        // Output: Zoo animal receives: 9.0 reward points
    }


}
