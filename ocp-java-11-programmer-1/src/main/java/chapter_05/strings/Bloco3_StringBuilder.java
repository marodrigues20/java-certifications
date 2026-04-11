package chapter_05.strings;

/**
 * Pontos-bomba para o exame:
 *
 * new StringBuilder() chamado uma vez → há sempre um único objeto, independentemente de quantas variáveis apontam para ele
 * delete(1, 100) com end fora do range → não dá exception, trunca ao fim
 * deleteCharAt(index fora do range) → dá exception
 * substring() no StringBuilder devolve uma String — não modifica o StringBuilder
 * StringBuilder não implementa equals() → usa referência como ==
 */
public class Bloco3_StringBuilder {

    // Mutabilidade e chaining — sb e same apontam para o MESMO objeto
    static void mutabilidadeEChaining() {
        StringBuilder sb = new StringBuilder("start");
        sb.append("+middle");                     // sb = "start+middle"
        StringBuilder same = sb.append("+end");   // sb = same = "start+middle+end"

        System.out.println(sb == same);           // true — mesmo objeto!
        System.out.println(sb);                   // start+middle+end

        // ARMADILHA CLÁSSICA: quantos objetos StringBuilder existem aqui?
        StringBuilder a = new StringBuilder("abc");
        StringBuilder b = a.append("de");         // a e b apontam para o mesmo!
        b = b.append("f").append("g");
        System.out.println("a=" + a);             // a=abcdefg
        System.out.println("b=" + b);             // b=abcdefg  (mesmo objeto!)
    }

    // Três formas de construir
    static void construtores() {
        StringBuilder sb1 = new StringBuilder();        // vazio
        StringBuilder sb2 = new StringBuilder("animal");// com valor
        StringBuilder sb3 = new StringBuilder(10);      // capacidade inicial (não length!)
        System.out.println(sb1.length()); // 0
        System.out.println(sb2.length()); // 6
        System.out.println(sb3.length()); // 0  ← capacidade ≠ length!
    }

    // append — aceita String, int, char, boolean, etc.
    static void append() {
        StringBuilder sb = new StringBuilder()
                .append(1)
                .append('c')
                .append("-")
                .append(true);
        System.out.println(sb); // 1c-true
    }

    // insert — insere no índice indicado
    static void insert() {
        StringBuilder sb = new StringBuilder("animals");
        sb.insert(7, "-");   // "animals-"
        sb.insert(0, "-");   // "-animals-"
        sb.insert(4, "-");   // "-ani-mals-"
        System.out.println(sb); // -ani-mals-
    }

    // delete e deleteCharAt
    static void delete() {
        StringBuilder sb = new StringBuilder("abcdef");
        sb.delete(1, 3);     // remove índice 1 e 2 → "adef"
        System.out.println(sb); // adef

        // delete com end > length é PERMITIDO (trunca até ao fim)
        StringBuilder sb2 = new StringBuilder("abcdef");
        sb2.delete(1, 100);  // remove tudo a partir do índice 1 → "a"
        System.out.println(sb2); // a

        // deleteCharAt fora do range → StringIndexOutOfBoundsException
        // new StringBuilder("adef").deleteCharAt(5) → EXCEPTION
    }

    // replace — apaga [start,end[ e insere nova string
    static void replace() {
        StringBuilder sb = new StringBuilder("pigeon dirty");
        sb.replace(3, 6, "sty");   // apaga "eon" → insere "sty"
        System.out.println(sb);    // pigsty dirty

        // replace com end > length também é permitido
        StringBuilder sb2 = new StringBuilder("pigeon dirty");
        sb2.replace(3, 100, "");
        System.out.println(sb2);   // pig
    }

    // reverse e toString
    static void reverseEToString() {
        StringBuilder sb = new StringBuilder("ABC");
        sb.reverse();
        System.out.println(sb);                   // CBA

        String s = sb.toString();
        System.out.println(s.equals("CBA"));      // true
        System.out.println(s.getClass().getSimpleName()); // String
    }

    // charAt, indexOf, length, substring — iguais à String
    static void metodosPartilhados() {
        StringBuilder sb = new StringBuilder("animals");
        String sub = sb.substring(sb.indexOf("a"), sb.indexOf("al")); // "anim"
        int len = sb.length();      // 7
        char ch = sb.charAt(6);     // 's'
        System.out.println(sub + " " + len + " " + ch); // anim 7 s

        // NOTA: substring() devolve String, não StringBuilder
        // sb não é modificado!
    }

    public static void main(String[] args) {
        //System.out.println("=== Mutabilidade e Chaining ===");
        //mutabilidadeEChaining();

        //System.out.println("\n=== Construtores ===");
        //construtores();

        //System.out.println("\n=== append() ===");
        //append();

        //System.out.println("\n=== insert() ===");
        //insert();

        //System.out.println("\n=== delete() ===");
        //delete();

        //System.out.println("\n=== replace() ===");
        //replace();

        //System.out.println("\n=== reverse() e toString() ===");
        //reverseEToString();

        //System.out.println("\n=== Métodos partilhados com String ===");
        //metodosPartilhados();
    }
}

//
