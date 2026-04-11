package chapter_05.arrays;

import java.util.Arrays;

public class Bloco5_Arrays {

    // Criação de arrays
    static void criacao() {
        int[] nums1 = new int[3];          // {0, 0, 0} — default int é 0
        int[] nums2 = new int[]{42,55,99}; // valores explícitos
        int[] nums3 = {42, 55, 99};        // anonymous array — mais curto

        // ARMADILHA: brackets antes ou depois do nome
        int[] ids, types;      // ids=int[], types=int[]  — DOIS arrays
        int ids2[], types2;    // ids2=int[], types2=int  — UM array, UM int!

        System.out.println(nums1[0]); // 0
        System.out.println(nums2[1]); // 55
        System.out.println(nums1.length); // 3 — propriedade, não método! sem ()
    }

    // Arrays de Strings — referências, não valores
    static void arraysDeStrings() {
        String[] bugs = {"cricket", "beetle", "ladybug"};
        String[] alias = bugs;              // alias aponta para o MESMO array

        System.out.println(bugs.equals(alias));  // true  — mesma referência
        System.out.println(bugs == alias);       // true  — mesmo objeto
        System.out.println(Arrays.toString(bugs)); // [cricket, beetle, ladybug]

        // Array de Strings não inicializado → null, não ""
        String[] names = new String[2];
        System.out.println(names[0]); // null
    }

    // Uso: length, loop, ArrayIndexOutOfBoundsException
    static void uso() {
        String[] mammals = {"monkey", "chimp", "donkey"};
        System.out.println(mammals.length); // 3

        // Loop correto: < length
        for (int i = 0; i < mammals.length; i++) {
            System.out.println(mammals[i]);
        }

        // ARMADILHAS comuns → ArrayIndexOutOfBoundsException:
        // mammals[3]              → índice 3 não existe (0-2)
        // mammals[mammals.length] → length=3, índice 3 não existe
        // for(i=0; i<=length; i++) → usa <= em vez de <
    }

    // Sorting
    static void sorting() {
        int[] numbers = {6, 9, 1};
        Arrays.sort(numbers);
        System.out.println(Arrays.toString(numbers)); // [1, 6, 9]

        // ARMADILHA: Strings ordenam alfabeticamente!
        String[] strings = {"10", "9", "100"};
        Arrays.sort(strings);
        System.out.println(Arrays.toString(strings)); // [10, 100, 9]
        // "1" vem antes de "9" alfabeticamente → ordem surpreendente!
    }

    // Binary Search — array TEM de estar ordenado!
    static void binarySearch() {
        int[] numbers = {2, 4, 6, 8};
        System.out.println(Arrays.binarySearch(numbers, 2)); //  0 — encontrou no índice 0
        System.out.println(Arrays.binarySearch(numbers, 4)); //  1 — encontrou no índice 1
        System.out.println(Arrays.binarySearch(numbers, 1)); // -1 — inserir no índice 0 → -(0)-1 = -1
        System.out.println(Arrays.binarySearch(numbers, 3)); // -2 — inserir no índice 1 → -(1)-1 = -2
        System.out.println(Arrays.binarySearch(numbers, 9)); // -5 — inserir no índice 4 → -(4)-1 = -5

        // Array NÃO ordenado → resultado imprevisível!
        int[] unsorted = {3, 2, 1};
        System.out.println(Arrays.binarySearch(unsorted, 2)); // ⚠️ imprevisível
    }

    // compare() e mismatch()
    static void compareEMismatch() {
        // compare(): negativo = 1º menor; 0 = iguais; positivo = 1º maior
        // Compara valores decimais primeiro. O comprimento só entra em jogo se todos os elementos comparados forem iguais. Nunca os dois ao mesmo tempo!
        System.out.println(Arrays.compare(new int[]{1}, new int[]{2}));      // negativo
        System.out.println(Arrays.compare(new int[]{1,2}, new int[]{1,2}));  // 0
        System.out.println(Arrays.compare(new int[]{1,2}, new int[]{1}));    // positivo (1º array maior)

        // Regras de "menor": null < números < letras maiúsculas < minúsculas
        //
        System.out.println(Arrays.compare(new String[]{"a"}, new String[]{"aa"})); // negativo ("a" é prefixo)
        System.out.println(Arrays.compare(new String[]{"a"}, new String[]{"A"}));  // positivo (uppercase < lowercase)

        // mismatch(): -1 se iguais; índice do 1º elemento diferente se diferentes
        System.out.println(Arrays.mismatch(new int[]{1}, new int[]{1}));         // -1 (iguais)
        System.out.println(Arrays.mismatch(new String[]{"a"}, new String[]{"A"})); // 0 (diferem no índice 0)
        System.out.println(Arrays.mismatch(new int[]{1,2}, new int[]{1}));       // 1 (diferem no índice 1)

        // DOES NOT COMPILE — tipos diferentes!
        // Arrays.compare(new int[]{1}, new String[]{"a"});
    }

    // Arrays multidimensionais
    static void multiDimensional() {
        int[][] vars1;          // 2D
        int[] vars3[];          // 2D — sintaxe estranha mas válida
        // int[] vars4[], space[][]; // vars4=2D, space=3D — armadilha!

        String[][] rectangle = new String[3][2]; // 3 linhas, 2 colunas
        rectangle[0][1] = "set";
        System.out.println(rectangle[0][1]); // set
        System.out.println(rectangle[1][0]); // null

        // Arrays assimétricos — cada linha pode ter tamanho diferente!
        int[][] diff = {{1,4}, {3}, {9,8,7}};
        System.out.println(diff[0].length); // 2
        System.out.println(diff[1].length); // 1
        System.out.println(diff[2].length); // 3
    }

    public static void main(String[] args) {
        //System.out.println("=== Criação ===");
        //criacao();

        //System.out.println("\n=== Arrays de Strings ===");
        //arraysDeStrings();

        //System.out.println("\n=== Uso ===");
        //uso();

        //System.out.println("\n=== Sorting ===");
        //sorting();

        //System.out.println("\n=== Binary Search ===");
        //binarySearch();

        System.out.println("\n=== compare() e mismatch() ===");
        compareEMismatch();

        //System.out.println("\n=== Multidimensional ===");
        //multiDimensional();
    }
}

// Pontos-bomba para o exame:
//
//length é propriedade (sem ()) — diferente de String.length()
//int[] ids, types → dois int[]; int ids[], types → um int[] e um int
//Arrays.sort() em Strings ordena alfabeticamente — "9" vem depois de "100"!
//binarySearch() em array não ordenado → resultado imprevisível — é escolha de exame!
//compare() retorna 0 se iguais; mismatch() retorna -1 se iguais — não confundir!
//Arrays.compare() com tipos diferentes → não compila

