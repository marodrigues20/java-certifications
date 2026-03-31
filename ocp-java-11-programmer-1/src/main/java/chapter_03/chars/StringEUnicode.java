package chapter_03.chars;


/**
 * Pergunta 1 — A String também usa a tabela Unicode?
 * Sim! A String é internamente um array de char — e cada char aponta para a tabela Unicode.
 */
public class StringEUnicode {
    public static void main(String[] args) {

        // Estas três Strings são IGUAIS!
        String s1 = "ABC";
        String s2 = "\u0041\u0042\u0043";   // notação Java
        String s3 = new String(new char[]{'A', 'B', 'C'});

        System.out.println(s1);              // ABC
        System.out.println(s2);              // ABC
        System.out.println(s3);              // ABC
        System.out.println(s1.equals(s2));   // true
        System.out.println(s2.equals(s3));   // true
    }
}

