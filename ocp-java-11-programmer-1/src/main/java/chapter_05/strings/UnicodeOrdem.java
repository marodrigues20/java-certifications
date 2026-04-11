package chapter_05.strings;

public class UnicodeOrdem {
    public static void main(String[] args) {

        // Podes ver o valor Unicode de qualquer char fazendo cast para int
        System.out.println((int) '0');  // 48
        System.out.println((int) '9');  // 57
        System.out.println((int) 'A');  // 65
        System.out.println((int) 'Z');  // 90
        System.out.println((int) 'a');  // 97
        System.out.println((int) 'z');  // 122

        // Comparações que provam a ordem
        System.out.println('0' < 'A');  // true  — 48 < 65
        System.out.println('A' < 'a');  // true  — 65 < 97
        System.out.println('9' < 'A');  // true  — 57 < 65

        // Impacto em Arrays.compare()
        // "9" vs "A" → '9'(57) < 'A'(65) → negativo
        // "Z" vs "a" → 'Z'(90) < 'a'(97) → negativo
        // "5" vs "b" → '5'(53) < 'b'(98) → negativo
    }
}

