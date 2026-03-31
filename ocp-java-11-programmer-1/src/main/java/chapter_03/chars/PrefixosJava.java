package chapter_03.chars;

public class PrefixosJava {
    public static void main(String[] args) {

        char c1 = 65;           // decimal       → 'A'
        char c2 = 0x41;         // hexadecimal   → 'A'
        char c3 = '\u0041';     // unicode hex   → 'A'
        char c4 = 0b1000001;    // binário       → 'A'

        System.out.println(c1); // A
        System.out.println(c2); // A
        System.out.println(c3); // A
        System.out.println(c4); // A

        // Prova que 41 decimal NÃO é 'A'!
        char errado = 41;
        System.out.println(errado); // ) ← não é 'A'!
    }
}

