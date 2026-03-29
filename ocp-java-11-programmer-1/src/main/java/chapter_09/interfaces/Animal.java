package chapter_09.interfaces;

public interface Animal {

    String XPTO = "DS";

    String getSound();          // abstract por padrão (public abstract implícito)

    default void breathe() {    // default method — tem implementação
        System.out.println("Breathing...");
    }

    static Animal create() {    // static method — pertence à interface
        return () -> "...";
    }

}

