package chapter_10.bloco_06;

public class Bloco06ChainingCatch {

    // 1. Ordem correta — subclasse antes de superclasse
    static void exemploOrdemCorreta() {
        System.out.println("--- Ordem correta ---");
        try {
            Integer.parseInt("abc"); // lanca NumberFormatException
        } catch (NumberFormatException e) {
            System.out.println("1o catch: NumberFormatException - " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.out.println("2o catch: IllegalArgumentException");
            // so executa se NAO for NumberFormatException
        }
    }

    // 2. Sem relacao hierarquica — qualquer ordem e valida
    static void exemploSemRelacao() {
        System.out.println("--- Sem relacao hierarquica ---");
        try {
            String s = null;
            s.length(); // lanca NullPointerException
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException — nao executa");
        } catch (NullPointerException e) {
            System.out.println("NullPointerException apanhada!");
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndex — nao executa");
        }
    }

    // 3. Apenas UM catch executa — nunca dois
    static void exemploUmSoCatch() {
        System.out.println("--- Apenas um catch executa ---");
        try {
            throw new NumberFormatException("teste");
        } catch (NumberFormatException e) {
            System.out.println("1o catch executou");
        } catch (IllegalArgumentException e) {
            System.out.println("2o catch — NUNCA executa");
        } catch (RuntimeException e) {
            System.out.println("3o catch — NUNCA executa");
        }
    }

    // 4. Scope do catch — variavel e so existe dentro do seu bloco
    static void exemploScope() {
        System.out.println("--- Scope da variavel e ---");
        try {
            throw new NullPointerException("nula");
        } catch (NullPointerException e1) {
            System.out.println("e1 existe aqui: " + e1.getMessage());
        } catch (RuntimeException e2) {
            System.out.println("e2 existe aqui: " + e2.getMessage());
            // e1 nao existe aqui — fora do scope
        }
    }

    // 5. FileNotFoundException e subclasse de IOException
    static void exemploFileNotFound() {
        System.out.println("--- FileNotFoundException -> IOException ---");
        try {
            throw new java.io.FileNotFoundException("sem ficheiro");
        } catch (java.io.FileNotFoundException e) {
            System.out.println("FileNotFoundException apanhada: " + e.getMessage());
        } catch (java.io.IOException e) {
            System.out.println("IOException — so apanha se nao for FileNotFoundException");
        }
    }

    /* 6. DOES NOT COMPILE — superclasse antes de subclasse
    static void exemploInvalido() {
        try {
            Integer.parseInt("abc");
        } catch (IllegalArgumentException e) {    // superclasse primeiro
            System.out.println("IllegalArgument");
        } catch (NumberFormatException e) {       // DOES NOT COMPILE
            System.out.println("NumberFormat");   // nunca seria alcancado
        }
    }
    */

    public static void main(String[] args) {
        exemploOrdemCorreta();
        System.out.println();
        exemploSemRelacao();
        System.out.println();
        exemploUmSoCatch();
        System.out.println();
        exemploScope();
        System.out.println();
        exemploFileNotFound();
    }
}
