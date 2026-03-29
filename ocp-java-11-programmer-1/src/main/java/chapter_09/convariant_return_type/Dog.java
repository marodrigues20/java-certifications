package chapter_09.convariant_return_type;


/**
 * Covariant return type
 * Significa que ao fazer override, podes retornar um tipo mais específico (subclasse) do que o declarado na classe pai:
 * Dog é covariant em relação a Animal — é mais específico, está "abaixo" na hierarquia.
 * Regra simples:
 * No override, o return type pode ser igual ou mais específico — nunca mais genérico.
 */
class Dog extends Animal {
    @Override
    Dog create() {
        return new Dog();
    }  // Dog é subclasse de Animal ✅


    //O oposto não compila:
    //@Override
    //Object create() { return new Dog(); }  // Object é mais genérico ❌
}
