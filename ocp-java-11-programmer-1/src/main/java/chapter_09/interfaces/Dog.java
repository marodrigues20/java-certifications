package chapter_09.interfaces;

/**
 * 2. Implementando uma Interface
 * Classe concreta deve implementar todos os métodos abstratos.
 * Classe abstrata pode deixar métodos abstratos para subclasses.
 * Uma classe pode implementar múltiplas interfaces: class Bat implements Animal, Flyable
 */
public class Dog implements Animal {
    @Override
    public String getSound() { return "Woof"; }

    // breathe() já tem default — não precisa sobrescrever

    String fetch(){
        return "";
    }
}

