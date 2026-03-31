package chapter_03.chars;

public class EncodingJava {
    public static void main(String[] args) throws Exception {

        String texto = "Hello 😀";

        // =====================================================
        // CONTAGEM DE CARACTERES
        // =====================================================

        // length() conta chars (posições no array) — não codePoints visuais!
        System.out.println("Chars no array (length) : " + texto.length()); // 8
        // H(1) e(2) l(3) l(4) o(5) ' '(6) \uD83D(7) \uDE00(8)
        //                                   ↑_____________↑
        //                                   emoji = 2 chars (surrogate pair)

        // codePointCount() conta caracteres visuais reais
        System.out.println("Caracteres visuais      : " +
                texto.codePointCount(0, texto.length())); // 7
        // H(1) e(2) l(3) l(4) o(5) ' '(6) 😀(7)

        System.out.println();

        // =====================================================
        // CONVERSÃO PARA BYTES EM DIFERENTES ENCODINGS
        // =====================================================

        byte[] emUtf8  = texto.getBytes("UTF-8");
        byte[] emUtf16 = texto.getBytes("UTF-16");
        byte[] emUtf32 = texto.getBytes("UTF-32");

        // UTF-8: tamanho variável
        // ASCII (H,e,l,l,o,' ') = 1 byte cada × 6 = 6 bytes
        // Emoji (😀)            = 4 bytes (precisa de 4 bytes em UTF-8)
        // Total                 = 6 + 4 = 10 bytes
        System.out.println("UTF-8  bytes: " + emUtf8.length);  // 10

        // UTF-16: tamanho variável + BOM
        // BOM (Byte Order Mark)  = 2 bytes extra no início (indica ordem dos bytes)
        // ASCII (H,e,l,l,o,' ') = 2 bytes cada × 6 = 12 bytes
        // Emoji (😀)            = surrogate pair = 2 chars × 2 bytes = 4 bytes
        // Total                 = 2 (BOM) + 12 + 4 = 18 bytes
        System.out.println("UTF-16 bytes: " + emUtf16.length); // 18

        // UTF-32: tamanho fixo (4 bytes por codePoint) — SEM BOM no getBytes()
        // H, e, l, l, o, ' '   = 6 codePoints × 4 bytes = 24 bytes
        // Emoji (😀)            = 1 codePoint  × 4 bytes = 4 bytes
        // Total                 = 7 codePoints × 4 bytes = 28 bytes
        System.out.println("UTF-32 bytes: " + emUtf32.length); // 28

        System.out.println();

        // =====================================================
        // PROVA QUE O TEXTO É SEMPRE O MESMO
        // independentemente do encoding usado para guardar!
        // =====================================================

        String deUtf8  = new String(emUtf8,  "UTF-8");
        String deUtf16 = new String(emUtf16, "UTF-16");
        String deUtf32 = new String(emUtf32, "UTF-32");

        System.out.println("UTF-8  descodificado : " + deUtf8);
        System.out.println("UTF-16 descodificado : " + deUtf16);
        System.out.println("UTF-32 descodificado : " + deUtf32);

        // O texto é sempre igual — o encoding só muda como é guardado em bytes
        System.out.println("UTF-8  == UTF-16? " + deUtf8.equals(deUtf16)); // true
        System.out.println("UTF-16 == UTF-32? " + deUtf16.equals(deUtf32)); // true

        System.out.println();

        // =====================================================
        // VISUALIZAÇÃO DO SURROGATE PAIR DO EMOJI
        // =====================================================

        char c1 = texto.charAt(6); // primeiro char do emoji
        char c2 = texto.charAt(7); // segundo char do emoji

        System.out.println("char[6] (hex) : " + Integer.toHexString(c1).toUpperCase()); // D83D
        System.out.println("char[7] (hex) : " + Integer.toHexString(c2).toUpperCase()); // DE00
        System.out.println("É surrogate pair? : " +
                (Character.isHighSurrogate(c1) && Character.isLowSurrogate(c2))); // true

        // Junta os 2 chars e recupera o codePoint original do emoji
        int codePoint = Character.toCodePoint(c1, c2);
        System.out.println("CodePoint do emoji    : " + codePoint); // 128512
        System.out.println("Emoji recuperado      : " +
                new String(Character.toChars(codePoint))); // 😀
    }
}


