package chapter_10.block_01;

public class Bloco01Hierarquia {

    // 1. Checked exception — obriga a declarar ou tratar
    static void exemploChecked() throws Exception {
        // IOException é checked — temos de declarar com throws
        throw new java.io.IOException("ficheiro não encontrado");
    }

    // 2. Unchecked (runtime) — não obriga a declarar
    static void exemploUnchecked() {
        // NullPointerException é runtime — compilador não se queixa
        throw new NullPointerException("referência nula");
    }

    // 3. Error — lançado pela JVM, não deves capturar
    static void exemploError() {
        // StackOverflowError é lançado pela JVM, não pelo programador
        // Simulamos com recursão infinita (não corras isto!)
        System.out.println("Errors são lançados pela JVM - ex: StackOverflowError");
    }

    // 4. Throwable é pai de tudo — mas nunca o uses em catch na vida real
    static void exemploThrowable() {
        System.out.println("Throwable -> Exception -> RuntimeException");
        System.out.println("Throwable -> Error");
        System.out.println("Checked: herda Exception mas NAO RuntimeException");
        System.out.println("Unchecked: herda RuntimeException ou Error");
    }

    public static void main(String[] args) {
        exemploUnchecked();   // corre sem problemas (até lançar)
        exemploError();
        exemploThrowable();
        // exemploChecked();  // não compilaria sem try/catch ou throws no main
    }
}