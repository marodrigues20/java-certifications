package chapter_02.gc;


/**
 * Dois pontos importantes:
 *
 * System.gc() existe mas não garante que o GC vai rodar
 * finalize() está depreciado desde Java 9 — não conta com ele
 */
public class GC {

    public void metodo() {
        String one = new String("a");  // objeto "a" na heap
        String two = new String("b");  // objeto "b" na heap

        one = two;   // "one" agora aponta para "b"
        // objeto "a" elegível para GC! ♻️

        one = null;  // "one" não aponta mais para nada
    }
    // fim do método → "two" sai de escopo
    // objeto "b" elegível para GC! ♻️

}
