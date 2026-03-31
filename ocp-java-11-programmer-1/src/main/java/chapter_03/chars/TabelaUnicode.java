package chapter_03.chars;

public class TabelaUnicode {
    public static void main(String[] args) {

        System.out.println("Binario (0b...) Base 2 | Octal (0...) Base 8 | Decimal Base 10   | Hexadecimal (0x...) Base 16 | Notacao Java (\\uXXXX) | Caracter");
        System.out.println("-----------------------|---------------------|-------------------|-----------------------------|------------------------|---------");
        for (int i = 32; i < 128; i++) {
            System.out.printf("0b%-20s | 0%-18s | %-17d | 0x%-26s | \\u%04X                 | %c%n",
                    String.format("%8s", Integer.toBinaryString(i)).replace(' ', '0'),
                    Integer.toOctalString(i),
                    i,
                    Integer.toHexString(i).toUpperCase(),
                    i,
                    (char) i);
        }

        System.out.println("\n--- Símbolos especiais ---");
        System.out.printf("Euro:    \\u20AC = %c%n", '\u20AC');  // €
        System.out.printf("Libra:   \\u00A3 = %c%n", '\u00A3'); // £
        System.out.printf("Yen:     \\u00A5 = %c%n", '\u00A5'); // ¥
        System.out.printf("Copyrt:  \\u00A9 = %c%n", '\u00A9'); // ©
        System.out.printf("Emoji:   \\u263A = %c%n", '\u263A'); // ☺
    }
}








