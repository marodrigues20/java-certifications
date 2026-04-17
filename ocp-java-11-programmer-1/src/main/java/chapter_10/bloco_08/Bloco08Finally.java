package chapter_10.bloco_08;

public class Bloco08Finally {

    // 1. Finally sempre executa — sem excecao
    static void exemploSemExcecao() {
        System.out.println("--- Sem excecao ---");
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("try ");
        } catch (Exception e) {
            sb.append("catch ");
        } finally {
            sb.append("finally ");
        }
        sb.append("after");
        System.out.println(sb); // try finally after
    }

    // 2. Finally sempre executa — com excecao
    static void exemploComExcecao() {
        System.out.println("--- Com excecao ---");
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("try ");
            throw new RuntimeException();
        } catch (Exception e) {
            sb.append("catch ");
        } finally {
            sb.append("finally ");
        }
        sb.append("after");
        System.out.println(sb); // try catch finally after
    }

    // 3. Finally sem catch — valido!
    static void exemploSemCatch() {
        System.out.println("--- Finally sem catch ---");
        try {
            System.out.println("try executou");
        } finally {
            System.out.println("finally executou sempre");
        }
    }

    // 4. Finally com return — finally GANHA sempre
    static int exemploReturn() {
        System.out.println("--- Finally com return ---");
        try {
            System.out.println("try: imprime 1");
            return -1; // seria o return... mas finally interrompe
        } catch (Exception e) {
            System.out.println("catch: imprime 2");
            return -2;
        } finally {
            System.out.println("finally: imprime 3");
            return -3; // este return e que vence!
        }
    }

    // 5. Excecao no finally — engole a excecao do catch!
    static void exemploExcecaoNoFinally() {
        System.out.println("--- Excecao no finally ---");
        try {
            throw new RuntimeException("excecao do try");
        } catch (RuntimeException e) {
            System.out.println("catch: " + e.getMessage());
            throw new RuntimeException("excecao do catch");
        } finally {
            System.out.println("finally executa");
            throw new RuntimeException("excecao do finally"); // esta vence!
            // excecao do catch e perdida para sempre
        }
    }

    // 6. System.exit() — finally NAO executa
    static void exemploSystemExit() {
        System.out.println("--- System.exit() ---");
        try {
            System.out.println("try executou");
            // System.exit(0); // se descomentar, finally NAO executa
        } finally {
            System.out.println("finally executou (system.exit comentado)");
        }
    }

    public static void main(String[] args) {
        exemploSemExcecao();
        System.out.println();

        exemploComExcecao();
        System.out.println();

        exemploSemCatch();
        System.out.println();

        int resultado = exemploReturn();
        System.out.println("return value: " + resultado);
        System.out.println();

        try {
            exemploExcecaoNoFinally();
        } catch (RuntimeException e) {
            System.out.println("main apanhou: " + e.getMessage());
        }
        System.out.println();

        exemploSystemExit();
    }
}
