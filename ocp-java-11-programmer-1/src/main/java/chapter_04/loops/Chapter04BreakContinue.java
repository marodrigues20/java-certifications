package chapter_04.loops;

/**
 * OCP Programmer I — Cap. 4: Making Decisions
 * Bloco 7: break, continue e Labels
 */
public class Chapter04BreakContinue {

    public static void main(String[] args) {
        //basicBreak();
        //basicContinue();
        //breakWithLabel();
        continueWithLabel();
    }

    // -------------------------------------------------------------------------
    // 1. break — sai do loop imediatamente
    // -------------------------------------------------------------------------
    static void basicBreak() {
        System.out.println("=== 1. Basic break ===");

        for (int i = 0; i < 10; i++) {
            if (i == 5) break;          // sai quando i chega a 5
            System.out.print(i + " ");  // imprime 0 1 2 3 4
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // 2. continue — salta para a próxima iteração
    // -------------------------------------------------------------------------
    static void basicContinue() {
        System.out.println("\n=== 2. Basic continue ===");

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) continue;   // salta os pares
            System.out.print(i + " ");  // imprime só ímpares: 1 3 5 7 9
        }
        System.out.println();
    }

    // -------------------------------------------------------------------------
    // 3. break com label — sai do loop EXTERIOR
    // -------------------------------------------------------------------------
    static void breakWithLabel() {
        System.out.println("\n=== 3. break com label ===");

        int[][] list = {{1, 13}, {5, 2}, {2, 2}};
        int searchValue = 2;
        int positionX = -1;
        int positionY = -1;

        PARENT_LOOP:                                    // label no loop exterior
        for (int i = 0; i < list.length; i++) { // list.length → quantidade de elementos do array exterior → 3 linhas
            for (int j = 0; j < list[i].length; j++) { //list[i].length → quantidade de elementos do array interior da linha i → colunas dessa linha
                if (list[i][j] == searchValue) {
                    positionX = i;
                    positionY = j;
                    break PARENT_LOOP;                  // sai dos DOIS loops!
                }
            }
        }
        System.out.println("Encontrado em [" + positionX + "][" + positionY + "]");
        // Sem label, break só sairia do loop interior
        // e continuaria a procurar — encontraria [2][1] em vez de [1][1]
    }

    // -------------------------------------------------------------------------
    // 4. continue com label — salta para a próxima iteração do loop EXTERIOR
    // -------------------------------------------------------------------------
    static void continueWithLabel() {
        System.out.println("\n=== 4. continue com label ===");

        OUTER_LOOP:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 1) continue OUTER_LOOP;   // salta para próximo i
                System.out.println("i=" + i + " j=" + j);
            }
        }
        // Para cada i, assim que j==1, salta para o próximo i
        // j==2 nunca é impresso
    }
}
// =============================================================================
// Expected output:
// === 1. Basic break ===
// 0 1 2 3 4
//
// === 2. Basic continue ===
// 1 3 5 7 9
//
// === 3. break com label ===
// Encontrado em [1][1]
//
// === 4. continue com label ===
// i=0 j=0
// i=1 j=0
// i=2 j=0
// =============================================================================

