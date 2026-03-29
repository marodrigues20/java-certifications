package chapter_08.hidding_variable;


/**
 * Diferente do overriding de métodos de instância, variáveis nunca são polimórficas — sempre seguem o tipo da variável em compile time.
 */
public class MyTest {

    public static void main(String[] args) {

        Animal a = new Dog();
        System.out.println(a.name); // Animal — variable type decides!

        Dog d = new Dog();
        System.out.println(d.name); // Dog



    }


}
