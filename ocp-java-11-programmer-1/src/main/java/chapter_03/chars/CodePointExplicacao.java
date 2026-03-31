package chapter_03.chars;

public class CodePointExplicacao {
    public static void main(String[] args) {

        // codePoint de caracteres simples
        System.out.println("codePoint de 'A' = " + (int)'A');      // 65
        System.out.println("codePoint de 'a' = " + (int)'a');      // 97
        System.out.println("codePoint de '€' = " + (int)'\u20AC'); // 8364

        // codePoint de emoji — precisa de String, não cabe em char!
        String emoji = "😀";
        System.out.println("length()         = " + emoji.length());          // 2 (dois chars!)
        System.out.println("codePointCount() = " + emoji.codePointCount(0,
                emoji.length()));          // 1 (um codePoint!)
        System.out.println("codePoint do 😀  = " + emoji.codePointAt(0));   // 128512

        // Iterar correctamente por codePoints
        "Hello 😀".codePoints().forEach(cp -> {
            System.out.println(
                    "codePoint: " + cp +
                            " → carácter: " + new String(Character.toChars(cp))
            );
        });
    }
}

