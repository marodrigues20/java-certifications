package chapter_03.instance_of;


/**
 * O livro diz explicitamente:
 * "É boa prática usar instanceof antes de fazer cast para um tipo mais específico."
 */
public class Bloco10_RelationalOperators {
    public static void main(String[] args) {

        // --- Operadores numéricos ---
        int gibbonFeet  = 2;
        int wolfFeet    = 4;
        int ostrichFeet = 2;

        System.out.println(gibbonFeet < wolfFeet);    // true
        System.out.println(gibbonFeet <= wolfFeet);   // true
        System.out.println(gibbonFeet >= ostrichFeet);// true
        System.out.println(gibbonFeet > ostrichFeet); // false — igual NÃO é maior!

        System.out.println();

        // --- instanceof: verifica o tipo em runtime ---
        Integer zooTime = Integer.valueOf(9);
        Number  num     = zooTime;  // Integer é um Number
        Object  obj     = zooTime;  // Integer é um Object

        // Um objecto, três referências — instanceof é true para todas!
        System.out.println(zooTime instanceof Integer); // true
        System.out.println(num    instanceof Number);   // true
        System.out.println(obj    instanceof Object);   // true

        System.out.println();

        // --- instanceof com null → sempre false ---
        String texto = null;
        System.out.println(texto instanceof String); // false — nunca explode!

        System.out.println();

        // --- instanceof + cast — padrão clássico do exame ---
        Number n = Integer.valueOf(42);
        if (n instanceof Integer) {
            Integer i = (Integer) n; // cast seguro — já verificámos o tipo!
            System.out.println("É Integer: " + i); // 42
        }

        // --- DOES NOT COMPILE ---
        // Number  time = Integer.valueOf(9);
        // if (time instanceof String) {} // DOES NOT COMPILE — tipos incompatíveis!

        // if (null instanceof null) {}   // DOES NOT COMPILE — null no lado direito!
    }
}

