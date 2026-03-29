package chapter_09.interfaces;

/**
 * 4. Polimorfismo
 * Objeto é referenciado pelo tipo da interface ou superclasse — comportamento real é do tipo em runtime.
 */
public class MyTest {

    public static void main(String[] args) {
        Animal animal = new Dog();
        animal.breathe();
        animal.getSound();
        // a.fetch();               // ERRO — fetch() não existe em Animal ❌
        String xpto = Animal.XPTO;
        Animal.create();

        Dog d = (Dog) animal;            // cast explícito — OK em runtime se for Dog
        d.fetch();                  // agora acessa métodos de Dog ✅

        Animal a2 = new Cat();
        Dog d2 = (Dog) a2;          // compila, mas lança ClassCastException em runtime!

        if(animal instanceof Dog){
            Dog dog = (Dog) animal;
            dog.fetch();
        }

        // Java 16+ pattern matching
        if (animal instanceof Dog dog2){
                dog2.fetch();
        }

    }
}
