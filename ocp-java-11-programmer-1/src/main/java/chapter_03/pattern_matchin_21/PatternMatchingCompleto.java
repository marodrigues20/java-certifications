package chapter_03.pattern_matchin_21;

public class PatternMatchingCompleto {
    public static void main(String[] args) {

        // =====================================================
        // JAVA 11 — forma antiga (o que o livro ensina)
        // =====================================================

        System.out.println("=== Java 11 (forma antiga) ===");

        Object obj = Integer.valueOf(42);

        // Passo 1: verificar tipo
        // Passo 2: cast manual
        // Passo 3: usar a variável
        if (obj instanceof Integer) {
            Integer i = (Integer) obj;  // cast manual obrigatório!
            System.out.println("É Integer: " + i);
            System.out.println("Dobro: " + (i * 2));
        }

        // =====================================================
        // JAVA 16+ — Pattern Matching para instanceof
        // =====================================================

        System.out.println("\n=== Java 16+ (Pattern Matching) ===");

        // instanceof + cast + variável numa só linha!
        if (obj instanceof Integer i) {
            // 'i' já está disponível aqui — sem cast manual!
            System.out.println("É Integer: " + i);
            System.out.println("Dobro: " + (i * 2));
        }

        // =====================================================
        // PATTERN MATCHING — vários tipos
        // =====================================================

        System.out.println("\n=== Vários tipos ===");

        Object[] objectos = {
                Integer.valueOf(42),
                Double.valueOf(3.14),
                "Hello Java",
                Long.valueOf(100L),
                null
        };

        for (Object o : objectos) {

            // Java 11 — forma antiga (verbose!)
            if (o instanceof Integer) {
                Integer i = (Integer) o;
                System.out.println("Java 11 — Integer: " + i);
            } else if (o instanceof Double) {
                Double d = (Double) o;
                System.out.println("Java 11 — Double:  " + d);
            } else if (o instanceof String) {
                String s = (String) o;
                System.out.println("Java 11 — String:  " + s);
            }
        }

        System.out.println();

        for (Object o : objectos) {

            // Java 16+ — Pattern Matching (limpo!)
            if (o instanceof Integer i) {
                System.out.println("Java 16 — Integer: " + i);
            } else if (o instanceof Double d) {
                System.out.println("Java 16 — Double:  " + d);
            } else if (o instanceof String s) {
                System.out.println("Java 16 — String:  " + s);
            } else if (o == null) {
                System.out.println("Java 16 — null!");
            } else {
                System.out.println("Java 16 — Outro:   " + o);
            }
        }

        // =====================================================
        // JAVA 21 — Pattern Matching no switch
        // =====================================================

        System.out.println("\n=== Java 21 (switch Pattern Matching) ===");

        for (Object o : objectos) {
            String resultado = switch (o) {
                case Integer i -> "Integer: "  + i + " dobro=" + (i * 2);
                case Double  d -> "Double:  "  + d + " raiz=" + Math.sqrt(d);
                case String  s -> "String:  '" + s + "' tamanho=" + s.length();
                case Long    l -> "Long:    "  + l;
                case null      -> "É null!";
                default        -> "Tipo desconhecido: " + o.getClass().getSimpleName();
            };
            System.out.println(resultado);
        }

        // =====================================================
        // PATTERN MATCHING — com condição extra (Java 21 "guard")
        // =====================================================

        System.out.println("\n=== Java 21 (guard — condição extra) ===");

        Object numero = Integer.valueOf(42);

        String classificacao = switch (numero) {
            case Integer i when i < 0    -> "Integer NEGATIVO: "  + i;
            case Integer i when i == 0   -> "Integer ZERO";
            case Integer i when i < 100  -> "Integer PEQUENO: "   + i; // ← entra aqui!
            case Integer i               -> "Integer GRANDE: "    + i;
            default                      -> "Não é Integer";
        };

        System.out.println(classificacao); // Integer PEQUENO: 42
    }
}
