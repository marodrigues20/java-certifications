package chapter_03.operators;

public class Bloco4_PrePostIncrement {
    public static void main(String[] args) {

        // --- Exemplo base do livro ---
        int parkAttendance = 0;
        System.out.println(parkAttendance);    // 0
        System.out.println(++parkAttendance);  // 1 → pre:  incrementa ANTES de imprimir
        System.out.println(parkAttendance);    // 1
        System.out.println(parkAttendance--);  // 1 → post: imprime ANTES de decrementar
        System.out.println(parkAttendance);    // 0

        System.out.println("---");

        // --- Exemplo avançado do livro (exame!) ---
        int lion = 3;
        int tiger = ++lion * 5 / lion--;
        // Passo 1: ++lion  → lion=4, usa 4   →  4 * 5 / lion--
        // Passo 2: lion--  → usa 4, lion=3   →  4 * 5 / 4
        // Passo 3: 4*5=20, 20/4=5
        System.out.println("lion is "  + lion);   // 3
        System.out.println("tiger is " + tiger);  // 5
    }
}

/**
 * 🧠 Como resolver expressões com múltiplos ++/-- no exame?
 * Segue sempre estes passos:
 *
 * Lê da esquerda para a direita
 * Quando encontras ++x ou --x → aplica imediatamente, substitui pelo novo valor
 * Quando encontras x++ ou x-- → usa o valor actual, marca para alterar depois
 * Aplica as alterações pendentes antes de passar ao próximo operador
 * ⚠️ Atenção no exame: Esta distinção pre/post aparece em múltiplas questões. Confundir qual valor é retornado pode custar muitos pontos.
 */
