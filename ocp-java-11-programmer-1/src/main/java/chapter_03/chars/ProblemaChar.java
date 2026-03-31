package chapter_03.chars;

public class ProblemaChar {
    public static void main(String[] args) {

        // char aguenta Unicode básico (16 bits) ✅
        char euro = '\u20AC';
        System.out.println(euro); // €

        // Emojis e caracteres modernos precisam de mais de 16 bits!
        // Um emoji como 😀 tem o código U+1F600 — não cabe num char!
        String emoji = "\uD83D\uDE00"; // 😀 precisa de DOIS chars!
        System.out.println(emoji);     // 😀

        // Por isso para Unicode moderno usa-se String, não char!
        System.out.println("Tamanho do emoji em chars: " + emoji.length()); // 2 ← não é 1!
    }
}

