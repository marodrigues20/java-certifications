package chapter_03.pattern_matchin_21;

public class SwitchPatternMatching {



    /**
     * O switch clássico só compara valores — 1, 2, 3...
     * Compara valores primitivos e Strings — nada mais.
     * If Quando tens Object — não sabes o tipo!
     *
     */
    private void compareTypesBeforeJava21() {
        // Tens um Object — não sabes o tipo!
        Object obj = Integer.valueOf(42);

        // Só consegues fazer com if/else encadeado (feio e verbose)
        if (obj instanceof Integer) {
            Integer i = (Integer) obj;
            System.out.println("É Integer: " + i);
        } else if (obj instanceof String) {
            String s = (String) obj;
            System.out.println("É String: " + s);
        } else if (obj instanceof Double) {
            Double d = (Double) obj;
            System.out.println("É Double: " + d);
        }

    }


    /**
     * Java 21 — Pattern Matching no switch resolve isto!
     * case Integer i -> "É Integer: " + i;
     *      ↑↑↑↑↑↑↑ ↑    ↑______________↑
     *      |       |     o que fazer se entrar aqui
     *      |       |
     *      |       └─ variável 'i' criada automaticamente
     *      |          já com o cast feito! (não precisas de fazer (Integer) obj)
     *      |
     *      └─ verifica se obj é do tipo Integer
     * @param args
     */
    public static void main(String[] args) {

        Object obj = Integer.valueOf(42);

        // Em vez de if/else encadeado — usa switch!
        String resultado = switch (obj) {
            case Integer i -> "É Integer: " + i;   // verifica tipo + extrai variável
            case String s -> "É String: " + s;   // verifica tipo + extrai variável
            case Double d -> "É Double: " + d;   // verifica tipo + extrai variável
            case null -> "É null!";
            default -> "Outro tipo";
        };

        System.out.println(resultado); // É Integer: 42
    }



}

