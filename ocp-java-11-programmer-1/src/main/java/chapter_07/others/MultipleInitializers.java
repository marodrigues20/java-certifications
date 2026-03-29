package chapter_07.others;


/**
 * Quantos static initializer e instance initializer eu posso ter por classe?
 * Ótima pergunta! Pode ter quantos quiser — sem limite!
 *
 * ✅ Regra: todos rodam na ordem em que aparecem no código.
 */
public class MultipleInitializers {

    // static initializer 1
    static { System.out.println("static 1"); }

    // static initializer 2
    static { System.out.println("static 2"); }

    // instance initializer 1
    { System.out.println("instance 1"); }

    // instance initializer 2
    { System.out.println("instance 2"); }

    public MultipleInitializers() {
        System.out.println("constructor");
    }

    public static void main(String[] args) {
        new MultipleInitializers();
        new MultipleInitializers();
    }
}
// Output:
// static 1       ← roda uma vez, na ordem
// static 2       ← roda uma vez, na ordem
// instance 1     ← 1º new
// instance 2     ← 1º new
// constructor    ← 1º new
// instance 1     ← 2º new
// instance 2     ← 2º new
// constructor    ← 2º new
