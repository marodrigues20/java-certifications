package chapter_03.chars;

public class CharEspecial {
    public static void main(String[] args) {

        // Todos recebem o mesmo valor 65
        int    a = 65;
        long   b = 65L;
        short  c = 65;
        byte   d = 65;
        double e = 65.0;
        char   f = 65;          // decimal
        char   g = 0x41;        // hexadecimal base 16
        char   h = 0b1000001;   // binario base 2
        char   i = 0101;        // octal base 8
        char   j = '\u0041';    // notacao Java

        System.out.println("int    = " + a);  // 65
        System.out.println("long   = " + b);  // 65
        System.out.println("short  = " + c);  // 65
        System.out.println("byte   = " + d);  // 65
        System.out.println("double = " + e);  // 65.0
        System.out.println("char   = " + f);  // A  ← único que imprime o carácter!
        System.out.println("char   = " + g);  // A
        System.out.println("char   = " + h);  // A
        System.out.println("char   = " + i);  // A
        System.out.println("char   = " + j);  // A

        // Mas se fizeres cast para int, vês o número!
        System.out.println("char como int = " + (int) f); // 65
    }
}

