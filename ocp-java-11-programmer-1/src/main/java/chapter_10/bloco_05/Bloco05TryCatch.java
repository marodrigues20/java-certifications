package chapter_10.bloco_05;

public class Bloco05TryCatch {

    // 1. Fluxo normal — sem excecao
    static void exemploSemExcecao() {
        System.out.println("--- Sem excecao ---");
        try {
            System.out.println("dentro do try");
            // nao ha excecao
            System.out.println("fim do try");
        } catch (RuntimeException e) {
            System.out.println("catch — nunca executa aqui");
        }
        System.out.println("apos try/catch");
    }

    // 2. Fluxo com excecao — catch executa, resto do try e ignorado
    static void exemploComExcecao() {
        System.out.println("--- Com excecao ---");
        try {
            System.out.println("linha 1 do try");
            throw new RuntimeException("boom");
            // System.out.println("linha 2 — nunca chega aqui");
        } catch (RuntimeException e) {
            System.out.println("catch executou: " + e.getMessage());
        }
        System.out.println("apos try/catch — continua normalmente");
    }

    // 3. Excecao nao apanhada — propaga para cima
    static void exemploNaoApanhada() {
        System.out.println("--- Nao apanhada ---");
        try {
            throw new RuntimeException("nao sou apanhada aqui");
        } catch (NullPointerException e) {
            // RuntimeException nao e NullPointerException — nao apanha!
            System.out.println("este catch nao executa");
        }
        // excecao propaga — o que vier a seguir nao executa
    }

    // 4. Catch com superclasse apanha subclasses
    static void exemploSuperclasseApanha() {
        System.out.println("--- Superclasse apanha subclasse ---");
        try {
            Integer.parseInt("xyz"); // lanca NumberFormatException
        } catch (IllegalArgumentException e) {
            // NumberFormatException e subclasse de IllegalArgumentException
            System.out.println("Apanhado como IllegalArgumentException: "
                    + e.getClass().getSimpleName());
        }
    }

    // 5. Excecao e um objeto — podes guardar em variavel
    static void exemploExcecaoComoObjeto() {
        System.out.println("--- Excecao como objeto ---");
        try {
            int[] arr = new int[2];
            arr[5] = 10; // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Tipo: " + e.getClass().getName());
            System.out.println("Mensagem: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        exemploSemExcecao();
        System.out.println();
        exemploComExcecao();
        System.out.println();

        try {
            exemploNaoApanhada(); // vai propagar
        } catch (RuntimeException e) {
            System.out.println("main apanhou: " + e.getMessage());
        }

        System.out.println();
        exemploSuperclasseApanha();
        System.out.println();
        exemploExcecaoComoObjeto();
    }
}
