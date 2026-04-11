package chapter_05.strings;

public class Bloco1_StringConcatImutabilidade {

    // Regras de concatenação
    static void regrasDeConcat() {
        System.out.println(1 + 2);           // 3    (regra 1)
        System.out.println("a" + "b");       // ab   (regra 2)
        System.out.println("a" + "b" + 3);   // ab3  (regras 2+3)
        System.out.println(1 + 2 + "c");     // 3c   (regras 1+2+3)
        System.out.println("c" + 1 + 2);     // c12  (regras 2+3)

        int three = 3;
        String four = "4";
        System.out.println(1 + 2 + three + four); // 64 — ATENÇÃO!
    }

    // += com Strings
    static void plusEquals() {
        String s = "1";
        s += "2";   // s = "12"
        s += 3;     // s = "123"  (3 é concatenado, não somado)
        System.out.println(s); // 123
    }

    // Imutabilidade — métodos devolvem novo objeto, original não muda
    static void imutabilidade() {
        String s2 = "1";
        String s3 = s2.concat("2");
        s3.concat("3");             // resultado ignorado! s3 continua "12"
        System.out.println(s3);     // 12

        // s2 nunca mudou
        System.out.println(s2);     // 1
    }

    public static void main(String[] args) {
        System.out.println("=== Regras de Concatenação ===");
        regrasDeConcat();

        System.out.println("\n=== += com Strings ===");
        plusEquals();

        System.out.println("\n=== Imutabilidade ===");
        imutabilidade();
    }
}
