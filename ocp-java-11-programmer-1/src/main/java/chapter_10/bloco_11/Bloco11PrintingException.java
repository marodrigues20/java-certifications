package chapter_10.bloco_11;


public class Bloco11PrintingException {

    // metodo que lanca a excecao
    static void hop() {
        throw new RuntimeException("cannot hop");
    }

    // 1. System.out.println(e) — tipo + mensagem
    static void exemploToString() {
        System.out.println("--- println(e) ---");
        try {
            hop();
        } catch (RuntimeException e) {
            System.out.println(e);
            // java.lang.RuntimeException: cannot hop
        }
    }

    // 2. getMessage() — so a mensagem
    static void exemploGetMessage() {
        System.out.println("--- getMessage() ---");
        try {
            hop();
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
            // cannot hop
        }
    }

    // 3. printStackTrace() — stack trace completa
    static void exemploPrintStackTrace() {
        System.out.println("--- printStackTrace() ---");
        try {
            hop();
        } catch (RuntimeException e) {
            e.printStackTrace();
            // java.lang.RuntimeException: cannot hop
            //     at cap10.bloco11.Bloco11PrintingException.hop(...)
            //     at cap10.bloco11.Bloco11PrintingException.exemploPrintStackTrace(...)
            //     at cap10.bloco11.Bloco11PrintingException.main(...)
        }
    }

    // 4. Excecao sem mensagem — getMessage() devolve null
    static void exemploSemMensagem() {
        System.out.println("--- Sem mensagem ---");
        try {
            throw new RuntimeException(); // sem mensagem
        } catch (RuntimeException e) {
            System.out.println("println:    " + e);
            System.out.println("getMessage: " + e.getMessage()); // null
        }
    }

    // 5. Stack trace com multiplos niveis
    static void metodoA() { metodoB(); }
    static void metodoB() { metodoC(); }
    static void metodoC() {
        throw new IllegalArgumentException("erro no fundo da stack");
    }

    static void exemploStackProfunda() {
        System.out.println("--- Stack profunda ---");
        try {
            metodoA();
        } catch (IllegalArgumentException e) {
            System.out.println("getMessage: " + e.getMessage());
            System.out.println("printStackTrace:");
            e.printStackTrace();
            // mostra: metodoC -> metodoB -> metodoA -> exemploStackProfunda -> main
        }
    }

    public static void main(String[] args) {
        exemploToString();
        System.out.println();

        exemploGetMessage();
        System.out.println();

        exemploPrintStackTrace();
        System.out.println();

        exemploSemMensagem();
        System.out.println();

        exemploStackProfunda();
    }
}
