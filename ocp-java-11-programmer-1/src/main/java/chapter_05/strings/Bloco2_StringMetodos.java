package chapter_05.strings;


/**
 * | Método                       | Assinatura                                      | O que faz                                         |
 * |------------------------------|-------------------------------------------------|---------------------------------------------------|
 * | length()                     | int length()                                    | Nº de caracteres (conta de 1)                     |
 * | charAt()                     | char charAt(int index)                          | Char no índice (conta de 0)                       |
 * | indexOf()                    | int indexOf(int ch / String str, [int from])    | 1º índice do match; -1 se não encontrar           |
 * | substring()                  | String substring(int begin, [int end])          | Substring — inclui begin, exclui end              |
 * | toLowerCase/toUpperCase()    | String toLowerCase()                            | Muda capitalização                                |
 * | equals/equalsIgnoreCase()    | boolean equals(Object o)                        | Compara conteúdo                                  |
 * | startsWith/endsWith()        | boolean startsWith(String s)                    | Verifica prefixo/sufixo                           |
 * | replace()                    | String replace(char old, char new)              | Substitui caracteres/substrings                   |
 * | contains()                   | boolean contains(CharSequence s)                | Verifica se contém                                |
 * | strip/trim()                 | String strip()                                  | Remove whitespace. strip() = Java 11 + Unicode    |
 * | stripLeading/Trailing()      | String stripLeading()                           | Remove só início / só fim — Java 11               |
 */
public class Bloco2_StringMetodos {

    // "animals" → índices: a=0, n=1, i=2, m=3, a=4, l=5, s=6
    static void lengthECharAt() {
        String s = "animals";
        System.out.println(s.length());     // 7  (conta de 1)
        System.out.println(s.charAt(0));    // a  (índice 0)
        System.out.println(s.charAt(6));    // s  (último)
        // s.charAt(7) → StringIndexOutOfBoundsException!
    }

    static void indexOf() {
        String s = "animals";
        System.out.println(s.indexOf('a'));        // 0  (1ª ocorrência)
        System.out.println(s.indexOf("al"));       // 4
        System.out.println(s.indexOf('a', 4));     // 4  (começa a procurar no 4)
        System.out.println(s.indexOf("al", 5));    // -1 (não encontrou)
    }

    static void substring() {
        String s = "animals";
        System.out.println(s.substring(3));        // mals  (do 3 até ao fim)
        System.out.println(s.substring(3, 4));     // m     (índice 3, exclui 4)
        System.out.println(s.substring(3, 7));     // mals  (do 3 até ao fim)
        System.out.println(s.substring(3, 3));     // ""    (string vazia!)
        // s.substring(3, 2) → exception (índices ao contrário)
        // s.substring(3, 8) → exception (8 > length)
    }

    static void caseEEquals() {
        System.out.println("animals".toUpperCase());           // ANIMALS
        System.out.println("Abc123".toLowerCase());            // abc123

        System.out.println("abc".equals("ABC"));               // false
        System.out.println("ABC".equals("ABC"));               // true
        System.out.println("abc".equalsIgnoreCase("ABC"));     // true
    }

    static void startsEndsContainsReplace() {
        System.out.println("abc".startsWith("a"));   // true
        System.out.println("abc".startsWith("b"));   // false
        System.out.println("abc".startsWith("A"));   // false (case-sensitive!)
        System.out.println("abc".endsWith("c"));     // true
        System.out.println("abc".contains("b"));     // true
        System.out.println("abc".contains("B"));     // false

        System.out.println("abcabc".replace('a', 'A'));    // AbcAbc
        System.out.println("abcabc".replace("a", "A"));   // AbcAbc
    }

    static void stripETrim() {
        String text = " abc\t ";
        System.out.println(text.trim().length());          // 3  → "abc"
        System.out.println(text.strip().length());         // 3  → "abc"
        System.out.println(text.stripLeading().length());  // 5  → "abc\t "
        System.out.println(text.stripLeading());
        System.out.println(text.stripTrailing().length()); // 4  → " abc"
        System.out.println(text.stripTrailing());
    }

    // Method chaining — String é imutável, cada passo devolve um NOVO objeto
    static void methodChaining() {
        String result = "AniMaL   ".trim().toLowerCase().replace('a', 'A');
        System.out.println(result); // AnimAl

        // ARMADILHA: b nunca muda o original 'a'
        String a = "abc";
        String b = a.toUpperCase();
        b = b.replace("B", "2").replace('C', '3');
        System.out.println("a=" + a); // a=abc  (imutável!)
        System.out.println("b=" + b); // b=A23
    }

    public static void main(String[] args) {
        System.out.println("=== length() e charAt() ===");
        lengthECharAt();

        //System.out.println("\n=== indexOf() ===");
        //indexOf();

        //System.out.println("\n=== substring() ===");
        //substring();

        //System.out.println("\n=== case, equals ===");
        //caseEEquals();

        //System.out.println("\n=== startsWith / endsWith / contains / replace ===");
        //startsEndsContainsReplace();

        //System.out.println("\n=== strip() vs trim() ===");
        //stripETrim();

        //System.out.println("\n=== Method Chaining ===");
        //methodChaining();
    }

    //Pontos-bomba para o exame:
    //
    //length() conta de 1, charAt()/indexOf() contam de 0
    //substring(3,3) → string vazia "" (não é exception!)
    //substring(3,2) → exception (begin > end)
    //strip() é Java 11 e suporta Unicode; trim() não
    //contains("b") é equivalente a indexOf("b") != -1
}

