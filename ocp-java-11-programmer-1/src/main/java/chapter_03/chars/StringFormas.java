package chapter_03.chars;


/**
 * 💡 A notação \\uXXXX é a única que funciona directamente dentro de uma String. As outras precisam de conversão explícita.
 */
public class StringFormas {
    public static void main(String[] args) {

        // ✅ Notação \\uXXXX — funciona directamente na String
        String s1 = "\u0041\u0042\u0043";
        System.out.println(s1); // ABC

        // ✅ Decimal — tens de converter via char
        String s2 = "" + (char)65 + (char)66 + (char)67;
        System.out.println(s2); // ABC

        // ✅ Hex — tens de converter via char
        String s3 = "" + (char)0x41 + (char)0x42 + (char)0x43;
        System.out.println(s3); // ABC

        // ✅ Usando StringBuilder
        StringBuilder sb = new StringBuilder();
        sb.appendCodePoint(65);    // decimal
        sb.appendCodePoint(0x42);  // hex
        sb.appendCodePoint(67);    // decimal
        System.out.println(sb.toString()); // ABC
    }
}

