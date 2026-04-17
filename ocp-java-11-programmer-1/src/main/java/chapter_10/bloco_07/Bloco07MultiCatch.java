package chapter_10.bloco_07;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Bloco07MultiCatch {

    // 1. Multi-catch basico — elimina codigo duplicado
    static void exemploBasico(String[] args) {
        System.out.println("--- Multi-catch basico ---");
        try {
            System.out.println(Integer.parseInt(args[1]));
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
            System.out.println("Missing or invalid input: " + e.getClass().getSimpleName());
        }
    }

    // 2. Tres excecoes sem relacao hierarquica
    static void exemploTresExcecoes() {
        System.out.println("--- Tres excecoes ---");
        try {
            String s = null;
            s.length(); // NullPointerException
        } catch (ArithmeticException | ArrayIndexOutOfBoundsException | NullPointerException e) {
            System.out.println("Apanhado: " + e.getClass().getSimpleName());
        }
    }

    // 3. Multi-catch com checked exceptions
    static void exemploChecked() {
        System.out.println("--- Multi-catch com checked ---");
        try {
            throw new FileNotFoundException("sem ficheiro");
        } catch (FileNotFoundException | IllegalArgumentException e) {
            // FileNotFoundException e checked, IllegalArgumentException e unchecked
            // sem relacao hierarquica — valido!
            System.out.println("Apanhado: " + e.getClass().getSimpleName());
        }
    }

    // 4. Ainda podes ter outro catch depois do multi-catch
    static void exemploComCatchAdicional() {
        System.out.println("--- Multi-catch + catch adicional ---");
        try {
            throw new IOException("io error");
        } catch (FileNotFoundException | IllegalArgumentException e) {
            System.out.println("multi-catch: " + e.getClass().getSimpleName());
        } catch (IOException e) {
            // IOException nao e FileNotFoundException nem IllegalArgumentException
            // este catch apanha o resto
            System.out.println("catch adicional: " + e.getClass().getSimpleName());
        }
    }

    /* 5. DOES NOT COMPILE — subclasse + superclasse juntas (redundante)
    static void exemploInvalido() {
        try {
            throw new IOException();
        } catch (FileNotFoundException | IOException e) { } // DOES NOT COMPILE
        //        ^subclasse             ^superclasse
        // FileNotFoundException ja e apanhada por IOException — redundante
    }
    */

    /* 6. DOES NOT COMPILE — variavel repetida
    static void exemploVariavelRepetida() {
        try {
        } catch (NullPointerException e | ArithmeticException e) { } // DOES NOT COMPILE
    }
    */

    public static void main(String[] args) {
        // Exemplo 1 — sem argumentos, lanca ArrayIndexOutOfBoundsException
        exemploBasico(new String[]{});
        System.out.println();

        // Exemplo 1 — com argumento invalido, lanca NumberFormatException
        exemploBasico(new String[]{"ignored", "abc"});
        System.out.println();

        exemploTresExcecoes();
        System.out.println();
        exemploChecked();
        System.out.println();
        exemploComCatchAdicional();
    }
}
