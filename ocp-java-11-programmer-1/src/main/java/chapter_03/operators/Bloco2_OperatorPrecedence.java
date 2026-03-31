package chapter_03.operators;

/**
 * | Precedência (maior → menor) | Operadores                                        |
 * |-----------------------------|---------------------------------------------------|
 * | Post-unary                  | `expression++`, `expression--`                    |
 * | Pre-unary                   | `++expression`, `--expression`                    |
 * | Other unary                 | `-`, `!`, `~`, `+`, `(type)`                      |
 * | Multiplicação               | `*`, `/`, `%`                                     |
 * | Adição                      | `+`, `-`                                          |
 * | Shift (não testado)         | `<<`, `>>`, `>>>`                                 |
 * | Relacional                  | `<`, `>`, `<=`, `>=`, `instanceof`                |
 * | Igualdade                   | `==`, `!=`                                        |
 * | Logical                     | `&`, `^`, `\|`                                    |
 * | Short-circuit               | `&&`, `\|\|`                                      |
 * | Ternário                    | `boolean ? expr1 : expr2`                         |
 * | Assignment                  | `=`, `+=`, `-=`, `*=`, `/=`, `%=`, etc.           |
 */
public class Bloco2_OperatorPrecedence {

    public static void main(String[] args) {

        // Exemplo 1: multiplicação antes de adição
        int price = 2 * 5 + 3 * 4 - 8;
        // Passo 1: 2*5=10, 3*4=12
        // Passo 2: 10 + 12 - 8 = 14  (esquerda → direita)
        System.out.println("price = " + price); // 14

        // Exemplo 2: parentheses overridem a precedência
        int price2 = 2 * ((5 + 3) * 4 - 8);
        // Passo 1: 5+3 = 8
        // Passo 2: 8*4 = 32
        // Passo 3: 32-8 = 24
        // Passo 4: 2*24 = 48
        System.out.println("price2 = " + price2); // 48

        // Exemplo 3: perimeter — do livro
        int height = 5, length = 10;
        var perimeter = 2 * height + 2 * length;
        // 2*5=10, 2*10=20, 10+20=30
        System.out.println("perimeter = " + perimeter); // 30
    }
}

