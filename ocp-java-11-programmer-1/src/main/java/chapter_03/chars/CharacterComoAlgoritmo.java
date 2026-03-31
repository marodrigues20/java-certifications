package chapter_03.chars;


/**
 * String (array de char)
 *     ↓
 *     ├── char normal  →  Character.isSurrogate() = false
 *     │                →  1 char = 1 codePoint
 *     │
 *     └── surrogate pair →  Character.isSurrogate() = true
 *                        →  2 chars = 1 codePoint
 *                        →  Character.toCodePoint(high, low)
 *
 *  A classe Character é literalmente o intérprete do Unicode em Java — é ela que sabe as regras de quais pares de chars formam um surrogate pair e como convertê-los para o codePoint correcto! 🔥
 */
public class CharacterComoAlgoritmo {
    public static void main(String[] args) {

        String emoji = "😀";
        char c1 = emoji.charAt(0); // \uD83D
        char c2 = emoji.charAt(1); // \uDE00

        // A classe Character reconhece os surrogate pairs
        System.out.println("c1 é surrogate? " +
                Character.isSurrogate(c1));              // true
        System.out.println("c2 é surrogate? " +
                Character.isSurrogate(c2));              // true

        System.out.println("c1 é high surrogate? " +
                Character.isHighSurrogate(c1));          // true  ← primeiro char
        System.out.println("c2 é low surrogate?  " +
                Character.isLowSurrogate(c2));           // true  ← segundo char

        // Character junta os dois e devolve o codePoint real!
        int codePoint = Character.toCodePoint(c1, c2);
        System.out.println("CodePoint = " + codePoint); // 128512

        // E converte de volta para o carácter visual
        System.out.println("Carácter  = " +
                new String(Character.toChars(codePoint)));   // 😀

        // Verifica se um char normal NÃO é surrogate
        char normal = 'A';
        System.out.println("\n'A' é surrogate? " +
                Character.isSurrogate(normal));          // false
    }
}
