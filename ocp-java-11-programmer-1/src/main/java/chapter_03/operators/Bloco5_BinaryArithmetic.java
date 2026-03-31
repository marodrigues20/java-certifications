package chapter_03.operators;

public class Bloco5_BinaryArithmetic {

    public static void main(String[] args) {

        // --- Precedência: * antes de + ---
        int price = 2 * 5 + 3 * 4 - 8;
        // Passo 1: 2*5=10, 3*4=12
        // Passo 2: 10 + 12 - 8 = 14
        System.out.println("price = " + price); // 14

        // --- Divisão inteira: floor (não arredonda!) ---
        System.out.println(9  / 3);  // 3
        System.out.println(10 / 3);  // 3  (não 3.33!)
        System.out.println(11 / 3);  // 3
        System.out.println(12 / 3);  // 4

        // --- Módulo: resto da divisão ---
        System.out.println(9  % 3);  // 0
        System.out.println(10 % 3);  // 1
        System.out.println(11 % 3);  // 2
        System.out.println(12 % 3);  // 0

        // --- Parênteses inválidos: não compila ---
        // long pigeon  = 1 + ((3 * 5) / 3;    // DOES NOT COMPILE - parênteses não balanceados
        // short robin  = 3 + [(4 * 2) + 4];   // DOES NOT COMPILE - [] não são permitidos em Java
    }

}

/**
 * ⚠️ Regras de parênteses para o exame
 * Têm de estar sempre balanceados — cada ( tem o seu )
 * [] não substituem () em expressões aritméticas em Java
 * Parênteses aninhados → avalia sempre o mais interior primeiro
 */
