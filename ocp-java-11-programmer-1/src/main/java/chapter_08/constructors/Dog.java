package chapter_08.constructors;


/**
 *  Regra de ouro:
 *  super() sempre tem que ser a primeira linha do construtor. Sem exceção.
 *
 *  Se o pai não tem construtor default, o filho é obrigado a chamar super(...) explicitamente — o Java não consegue fazer isso por você.
 */
public class Dog extends Animal {

    public Dog() {
        //super("BlackJack"); // explicit call — must be FIRST line
        System.out.println("Dog constructor");
    }

}
