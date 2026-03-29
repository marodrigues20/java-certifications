package chapter_02.init_order;

public class Egg {
    public Egg() { number = 5; }    // construtor → seta 5
    private int number = 3;         // field → seta 3
    { number = 4; }                 // initializer → seta 4


    public static void main(String[] args) {
        Egg egg = new Egg();
    }
}
// Ordem real: 3 → 4 → 5
// Resultado: number = 5
