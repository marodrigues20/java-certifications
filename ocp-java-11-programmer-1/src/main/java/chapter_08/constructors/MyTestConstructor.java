package chapter_08.constructors;


/**
 * O super() implícito
 * Se você não chamar super() explicitamente, o Java insere automaticamente super() na
 * primeira linha do construtor filho — chamando o construtor sem argumentos do pai.
 *
 */
public class MyTestConstructor {

    public static void main(String[] args) {
        Dog d = new Dog();
        // Output:
        // Animal constructor
        // Dog constructor

    }
}
