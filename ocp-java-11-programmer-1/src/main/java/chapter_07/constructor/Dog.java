package chapter_07.constructor;

/**
 * Regras do exame:
 * <p>
 * this() deve ser a primeira linha do construtor
 *
 * Ordem de execução completa:
 *
 * Static initializers (uma vez por classe)
 * Instance initializers (a cada new)
 * Construtor
 */
public class Dog {

    private String name;
    private int age;


    static {
        System.out.println("classe carregada");
    }


    // Initializer Blocks
    // Executam antes do construtor, na ordem em que aparecem:
    {
        System.out.println("1. instance initializer");
    }

    public Dog() {
        //System.out.println("antes");  // ERRO — this() não é a primeira linha
        this("Unkown", 0); // // delega para o construtor abaixo
        System.out.println("test default constructor");
    }

    public Dog(String name, int age) {
        this("third day");
        this.name = name;
        this.age = age;
        System.out.println("Constructor overloaded");
    }

    public Dog(String t) {
        System.out.println("Constructor " + t);
    }
}
