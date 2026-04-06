package chapter_04.loops;

/**
 * OCP Programmer I — Cap. 4: Making Decisions
 * Bloco 6: Nested Loops
 */
public class Chapter04NestedLoops {

    public static void main(String[] args) {
        basicNestedLoop();
        nestedWithCounter();
        nestedWithArray();
    }

    // -------------------------------------------------------------------------
    // 1. Nested loop básico — tabela de multiplicação
    // -------------------------------------------------------------------------
    static void basicNestedLoop() {
        System.out.println("=== 1. Nested Loop Básico ===");

        for (int i = 1; i <= 3; i++) {
            for (int j = 1; j <= 3; j++) {
                System.out.print(i * j + "\t");
            }
            System.out.println();
        }
        // Para cada i (exterior), j corre de 1 a 3 completo (interior)
    }

    // -------------------------------------------------------------------------
    // 2. EXAM TRAP — contar quantas vezes o interior executa
    // -------------------------------------------------------------------------
    static void nestedWithCounter() {
        System.out.println("\n=== 2. Contar execuções do loop interior ===");

        int hungryHippopotamus = 5;
        int count = 0;

        while (hungryHippopotamus > 0) {
            hungryHippopotamus--;
            int i = 0;
            while (i < hungryHippopotamus) {
                i++;
                count++;
            }
            System.out.println("After outer iteration: hippo="
                    + hungryHippopotamus + " i=" + i);
        }
        System.out.println("Total inner executions: " + count);
        // exterior corre 5x (5,4,3,2,1,0)
        // interior corre 4+3+2+1+0 = 10x no total
    }

    // -------------------------------------------------------------------------
    // 3. Nested loop com array 2D — procurar um valor
    // -------------------------------------------------------------------------
    static void nestedWithArray() {
        System.out.println("\n=== 3. Nested Loop com Array 2D ===");

        int[][] matrix = {
                {1, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int searchValue = 5;
        int positionX = -1;
        int positionY = -1;

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == searchValue) {
                    positionX = i;
                    positionY = j;
                }
            }
        }

        if (positionX >= 0) {
            System.out.println("Valor " + searchValue
                    + " encontrado em [" + positionX + "][" + positionY + "]");
        }
    }
}
// =============================================================================
// Expected output:
// === 1. Nested Loop Básico ===
// 1    2    3
// 2    4    6
// 3    6    9
//
// === 2. Contar execuções do loop interior ===
// After outer iteration: hippo=4 i=4
// After outer iteration: hippo=3 i=3
// After outer iteration: hippo=2 i=2
// After outer iteration: hippo=1 i=1
// After outer iteration: hippo=0 i=0
// Total inner executions: 10
//
// === 3. Nested Loop com Array 2D ===
// Valor 5 encontrado em [1][1]
// =============================================================================

