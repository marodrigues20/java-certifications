package chapter_05.arrays;

import java.util.Arrays;

public class ArraysCompareAlgoritmo {
    public static void main(String[] args) {

        // CASO 1: diferença nos VALORES → comprimento ignorado
        System.out.println(Arrays.compare(
                new int[]{1},
                new int[]{2}));       // negativo — 1 < 2 (valores)

        System.out.println(Arrays.compare(
                new int[]{5, 1},
                new int[]{3, 9}));    // positivo — 5 > 3 (para no índice 0!)

        // CASO 2: valores iguais → COMPRIMENTO decide
        System.out.println(Arrays.compare(
                new int[]{1, 2},
                new int[]{1, 2}));    // 0       — valores iguais, mesmo comprimento

        System.out.println(Arrays.compare(
                new int[]{1, 2},
                new int[]{1}));       // positivo — valores iguais, 1º array maior

        System.out.println(Arrays.compare(
                new int[]{1},
                new int[]{1, 2}));    // negativo — valores iguais, 1º array menor

        // CASO 3: mistura — valor decide antes do comprimento
        System.out.println(Arrays.compare(
                new int[]{1, 9, 9},
                new int[]{1, 9, 8})); // positivo — índice 2: 9 > 8 (comprimento ignorado!)
    }
}

