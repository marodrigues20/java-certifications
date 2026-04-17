package chapter_10.block_04;


/**
 * Regra de ouro: Se vires throw ou throws no exame, verifica imediatamente se o keyword correto está a ser usado. Um erro aqui passa despercebido quando lês depressa.
 */
public class Bloco04ThrowVsThrows {

    // 1. throw — lança a exceção imediatamente
    static void exemploThrow() {
        System.out.println("Antes do throw");
        throw new RuntimeException("algo correu mal");
        // System.out.println("Nunca chega aqui"); // DOES NOT COMPILE
    }

    // 2. throws — declara que o metodo PODE lancar (nao obriga)
    static void exemploThrows() throws java.io.IOException {
        System.out.println("Este metodo pode lancar IOException...");
        System.out.println("...mas nao tem de a lancar sempre");
        // Nao lanca nada aqui — e completamente valido
    }

    // 3. Excecao armazenada em variavel antes de ser lancada
    static void exemploVariavel() {
        RuntimeException e = new RuntimeException("criada antes");
        System.out.println("Excecao criada, ainda nao lancada");
        throw e; // lanca a excecao criada anteriormente
    }

    // 4. throw com checked exception obriga o metodo a declarar throws
    static void exemploCheckedComThrow() throws java.io.IOException {
        boolean condicao = true;
        if (condicao) {
            throw new java.io.IOException("ficheiro em falta");
        }
    }

    // 5. Chamador trata a excecao declarada com throws
    static void chamadorTrata() {
        try {
            exemploCheckedComThrow();
        } catch (java.io.IOException e) {
            System.out.println("Chamador tratou: " + e.getMessage());
        }
    }

    // 6. Chamador propaga a excecao com throws
    static void chamadorPropaga() throws java.io.IOException {
        exemploCheckedComThrow(); // propaga ao seu proprio chamador
    }

    public static void main(String[] args) {
        // Exemplo 2 — throws sem lancar
        try {
            exemploThrows();
        } catch (java.io.IOException e) {
            System.out.println("Nunca chega aqui neste caso");
        }

        // Exemplo 5 — chamador trata
        chamadorTrata();

        // Exemplo 3 — variavel (vai lancar excecao)
        try {
            exemploVariavel();
        } catch (RuntimeException e) {
            System.out.println("Apanhado: " + e.getMessage());
        }

        // Exemplo 1 — throw direto
        try {
            exemploThrow();
        } catch (RuntimeException e) {
            System.out.println("Apanhado: " + e.getMessage());
        }
    }
}
