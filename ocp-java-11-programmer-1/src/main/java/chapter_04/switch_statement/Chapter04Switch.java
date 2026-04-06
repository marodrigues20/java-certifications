package chapter_04.switch_statement;

/**
 * OCP Programmer I — Cap. 4: Making Decisions
 * Bloco 2: The switch Statement
 */
public class Chapter04Switch {

    public static void main(String[] args) {
        //basicSwitch();
        //fallThrough();
        //defaultAnywhere();
        //acceptableCaseValues();
        numericPromotion();
    }

    // -------------------------------------------------------------------------
    // 1. Switch básico com break
    // -------------------------------------------------------------------------
    static void basicSwitch() {
        System.out.println("=== 1. Basic Switch ===");

        int dayOfWeek = 5;
        switch (dayOfWeek) {
            default:
                System.out.println("Weekday");
                break;
            case 0:
                System.out.println("Sunday");
                break;
            case 6:
                System.out.println("Saturday");
                break;
        }
        // default não precisa de estar no fim — é válido em qualquer posição
    }

    // -------------------------------------------------------------------------
    // 2. EXAM TRAP — fall-through sem break
    // -------------------------------------------------------------------------
    static void fallThrough() {
        System.out.println("\n=== 2. Fall-Through (no break) ===");

        var dayOfWeek = 5; // var resolve para int — válido em switch
        switch (dayOfWeek) {
            case 0:
                System.out.println("Sunday");
            default:
                System.out.println("Weekday");  // sem break: cai para o próximo
            case 6:
                System.out.println("Saturday"); // também executa
                break;
        }
        // Output: Weekday / Saturday
        // (case 0 não corresponde, vai ao default, depois cai para case 6)
        // O Java não volta atrás para verificar se dayOfWeek == 6. Depois de entrar no default, simplesmente executa tudo o
        // que está abaixo até encontrar um break ou acabar o switch.
        //É por isso que o exame adora switch sem break — o valor da variável deixa de importar assim que o fluxo entra num case.
    }

    // -------------------------------------------------------------------------
    // 3. EXAM TRAP — default no meio, com dayOfWeek = 6
    // -------------------------------------------------------------------------
    static void defaultAnywhere() {
        System.out.println("\n=== 3. Default Anywhere (day = 6) ===");

        int dayOfWeek = 6;
        switch (dayOfWeek) {
            case 0:
                System.out.println("Sunday");
            default:
                System.out.println("Weekday");  // SKIPPED — há case correspondente
            case 6:
                System.out.println("Saturday"); // executa e faz break
                break;
        }
        // Mesmo o default estando antes do case 6, só "Saturday" é impresso.
        // default só é visitado quando NENHUM case corresponde.
    }

    // -------------------------------------------------------------------------
    // 4. Valores válidos e inválidos em case
    // -------------------------------------------------------------------------
    static void acceptableCaseValues() {
        System.out.println("\n=== 4. Acceptable Case Values ===");

        final int bananas = 1;   // final + literal = compile-time constant ✅
        int apples = 2;          // não é final ❌
        int target = 3;

        switch (target) {
            case bananas:                  // OK — final com literal
                System.out.println("bananas");
                break;
            // case apples:               // DOES NOT COMPILE — não é final
            // case getCookies():         // DOES NOT COMPILE — método = runtime
            case 3 * 5:                   // OK — expressão compile-time (= 15)
                System.out.println("15");
                break;
            default:
                System.out.println("other: " + target); // imprime isto
        }
    }

    // -------------------------------------------------------------------------
    // 5. Numeric promotion — o valor tem de caber no tipo do switch
    // -------------------------------------------------------------------------
    static void numericPromotion() {
        System.out.println("\n=== 5. Numeric Promotion ===");

        short size = 4;
        final int small = 15;
        // final int big = 1_000_000;  // DOES NOT COMPILE — não cabe em short

        switch (size) {
            case small:           // OK — 15 cabe em short, cast feito em compile-time
                System.out.println("small (15)");
                break;
            case 1 + 2:           // OK — expressão resolve para 3, cabe em short
                System.out.println("1+2 = 3");
                break;
            default:
                System.out.println("size = " + size); // imprime isto (size=4)
        }

        //Então a regra completa é:
        //O valor do case não precisa de ser declarado como short — mas o seu valor tem de caber no tipo do switch
        // sem cast explícito, e tem de ser resolvível em compile-time.
        //É uma promoção ao contrário — em vez de promover short para int, o compilador reduz o valor do case para
        // short, mas só se couber.
        // E se cair no exame, a dica prática é:
        //
        //Vê o tipo da variável do switch
        //Verifica o range desse tipo
        //Qualquer case fora desse range → não compila
        //Simples assim.
    }

    static int getCookies() { return 4; } // método auxiliar — não é compile-time
}
// =============================================================================
// Expected output:
// === 1. Basic Switch ===
// Weekday
//
// === 2. Fall-Through (no break) ===
// Weekday
// Saturday
//
// === 3. Default Anywhere (day = 6) ===
// Saturday
//
// === 4. Acceptable Case Values ===
// other: 3
//
// === 5. Numeric Promotion ===
// size = 4
// =============================================================================
