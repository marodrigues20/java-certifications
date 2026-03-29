package chapter_08.hiding;


/**
 * Method Hiding — métodos estáticos
 * Com métodos static a coisa muda. Você não faz override, você esconde o método do pai.
 */
public class MyTest {

    public static void main(String[] args) {
        Animal a = new Dog();
        a.identify(); // I am an Animal — runs Animal's version!

    }
}
