package chapter_03.pattern_matchin_21;

public class ComparacaoFinal {
    public static void main(String[] args) {

        Object obj = "Hello";

        // ❌ Java 11 — verbose
        String r1;
        if (obj instanceof Integer) {
            Integer i = (Integer) obj;
            r1 = "Integer: " + i;
        } else if (obj instanceof String) {
            String s = (String) obj;
            r1 = "String de tamanho: " + s.length();
        } else {
            r1 = "Outro";
        }
        System.out.println(r1); // String de tamanho: 5

        // ✅ Java 21 — limpo e directo
        String r2 = switch (obj) {
            case Integer i -> "Integer: " + i;
            case String  s -> "String de tamanho: " + s.length();
            default        -> "Outro";
        };
        System.out.println(r2); // String de tamanho: 5
    }
}

