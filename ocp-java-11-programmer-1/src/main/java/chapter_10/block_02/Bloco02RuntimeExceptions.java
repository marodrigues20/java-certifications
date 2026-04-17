package chapter_10.block_02;


/**
 * Nota do exame: Divisão de double por zero não lança exceção — devolve Infinity. Só int / 0 lança ArithmeticException
 */
public class Bloco02RuntimeExceptions {

    // 1. ArithmeticException — divisão de int por zero
    static void exemploArithmetic() {
        try {
            int resultado = 11 / 0;
        } catch (ArithmeticException e) {
            System.out.println("ArithmeticException: " + e.getMessage());
            // Output: ArithmeticException: / by zero
        }
    }

    // 2. ArrayIndexOutOfBoundsException — índice inválido
    static void exemploArrayIndex() {
        try {
            int[] arr = new int[3];
            System.out.println(arr[-1]); // índice negativo
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("ArrayIndexOutOfBounds: " + e.getMessage());
            // Output: ArrayIndexOutOfBounds: Index -1 out of bounds for length 3
        }
    }

    // 3. ClassCastException — cast impossível em runtime
    static void exemploClassCast() {
        try {
            String tipo = "moose";
            Object obj = tipo;           // upcast OK
            Integer numero = (Integer) obj; // runtime: String não é Integer!
        } catch (ClassCastException e) {
            System.out.println("ClassCastException apanhada!");
            // Compilador não detecta — só falha em runtime
        }
    }

    // 4. NullPointerException — método em referência null
    static void exemploNullPointer() {
        try {
            String nome = null;
            System.out.println(nome.length()); // nome é null!
        } catch (NullPointerException e) {
            System.out.println("NullPointerException: referência nula");
        }
    }

    // 5. IllegalArgumentException — lançada pelo programador
    static void exemploIllegalArgument() {
        try {
            setNumeroOvos(-2);
        } catch (IllegalArgumentException e) {
            System.out.println("IllegalArgumentException: " + e.getMessage());
            // Output: IllegalArgumentException: # ovos nao pode ser negativo
        }
    }

    static void setNumeroOvos(int num) {
        if (num < 0) {
            throw new IllegalArgumentException("# ovos nao pode ser negativo");
        }
        System.out.println("Ovos definidos: " + num);
    }

    // 6. NumberFormatException — subclasse de IllegalArgumentException
    static void exemploNumberFormat() {
        try {
            int valor = Integer.parseInt("abc"); // "abc" não é número!
        } catch (NumberFormatException e) {
            System.out.println("NumberFormatException: " + e.getMessage());
            // Output: NumberFormatException: For input string: "abc"
        }
    }

    public static void main(String[] args) {
        exemploArithmetic();
        exemploArrayIndex();
        exemploClassCast();
        exemploNullPointer();
        exemploIllegalArgument();
        exemploNumberFormat();
    }
}
