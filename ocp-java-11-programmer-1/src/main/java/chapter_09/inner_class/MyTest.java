package chapter_09.inner_class;


/**
 * O outer.new Inner() é a sintaxe especial — e faz sentido porque Inner precisa de uma instância de Outer para existir.
 * Ela vive dentro de Outer.
 */
public class MyTest {

    public static void main(String[] args) {
        Outer outer = new Outer();
        Outer.Inner inner = outer.new Inner(); // usa a instância de Outer
        inner.greet();
    }
}
