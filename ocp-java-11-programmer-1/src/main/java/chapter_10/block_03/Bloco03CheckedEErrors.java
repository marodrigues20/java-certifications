package chapter_10.block_03;



public class Bloco03CheckedEErrors {

    // 1. Checked exception declarada com throws
    static void exemploIOExceptionDeclarada() throws java.io.IOException {
        // Declaramos com throws — delegamos ao chamador
        throw new java.io.IOException("erro ao ler ficheiro");
    }

    // 2. Checked exception tratada com try/catch
    static void exemploIOExceptionTratada() {
        try {
            throw new java.io.IOException("ficheiro corrompido");
        } catch (java.io.IOException e) {
            System.out.println("IOException tratada: " + e.getMessage());
        }
    }

    // 3. FileNotFoundException é subclasse de IOException
    static void exemploFileNotFoundException() {
        try {
            throw new java.io.FileNotFoundException("config.txt nao existe");
        } catch (java.io.IOException e) {
            // FileNotFoundException é IOException — este catch apanha-a!
            System.out.println("Apanhado como IOException: " + e.getMessage());
        }
    }

    // 4. ExceptionInInitializerError — static initializer com erro
    // (classe separada para demonstrar)
    static void exemploExceptionInInitializer() {
        System.out.println("ExceptionInInitializerError ocorre quando um");
        System.out.println("static initializer lanca uma excecao.");
        System.out.println("Exemplo:");
        System.out.println("  static {");
        System.out.println("    int[] arr = new int[3];");
        System.out.println("    int x = arr[-1]; // lanca ArrayIndexOutOfBounds");
        System.out.println("  }");
        System.out.println("  --> JVM lanca ExceptionInInitializerError");
    }

    // 5. StackOverflowError — recursão infinita
    static void exemploStackOverflow() {
        try {
            recursaoInfinita(0);
        } catch (StackOverflowError e) {
            // Tecnicamente podes apanhar, mas nunca deves na vida real
            System.out.println("StackOverflowError: stack esgotada apos recursao infinita");
        }
    }

    static void recursaoInfinita(int n) {
        recursaoInfinita(n + 1); // chama-se a si proprio sem parar
    }

    // 6. NoClassDefFoundError — simulamos a descrição
    static void exemploNoClassDefFound() {
        System.out.println("NoClassDefFoundError ocorre quando:");
        System.out.println("  - A classe existia em compile-time");
        System.out.println("  - Mas nao esta disponivel em runtime");
        System.out.println("  - Ex: biblioteca presente ao compilar, ausente ao executar");
    }

    public static void main(String[] args) {
        exemploIOExceptionTratada();
        exemploFileNotFoundException();
        exemploExceptionInInitializer();
        exemploStackOverflow();
        exemploNoClassDefFound();
    }
}
