package chapter_03.chars;

public class CharVsShort {
    public static void main(String[] args) {

        char letra = 'A';
        System.out.println((int) letra);  // 65 — código Unicode do 'A'

        char max = '\uFFFF';
        System.out.println((int) max);    // 65535 — máximo do char

        // char não pode ser negativo:
        // char c = -1; // DOES NOT COMPILE ❌

        // mas short pode:
        short s = -1; // ✅
        System.out.println(s); // -1
    }
}

