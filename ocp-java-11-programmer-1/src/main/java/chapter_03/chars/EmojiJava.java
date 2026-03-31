package chapter_03.chars;


/**
 * Emojis modernos (Unicode 21 bits)
 *          ↓
 * Não cabem num char (16 bits)
 *          ↓
 * Java usa 2 chars juntos = "surrogate pair"
 *          ↓
 * \uD83D + \uDE00 = 😀
 *
 * ⚠️ É por isso que "😀".length() devolve 2 e não 1 — a String conta chars, não caracteres visuais!
 */
public class EmojiJava {
    public static void main(String[] args) {

        // Emoji 😀 = U+1F600 — não cabe num char (16 bits)!
        // Precisa de 2 chars — chamados "surrogate pair"
        String emoji = "\uD83D\uDE00";
        System.out.println(emoji);                    // 😀
        System.out.println(emoji.length());           // 2 ← dois chars!
        System.out.println(emoji.codePointCount(0,
                emoji.length()));           // 1 ← mas é 1 emoji!

        // Java 5+ — forma correcta de iterar com emojis
        emoji.codePoints().forEach(cp ->
                System.out.println(
                        "CodePoint: " + cp + " = " +
                                new String(Character.toChars(cp)))    // 128512 = 😀
        );
    }
}

