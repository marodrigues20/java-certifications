package chapter_02.init_order;

public class Chick {

    private String name = "Fluffy";      // 1º


    {
        System.out.println("init block " + name); // 2º
    }


    public Chick() {
        name = "Tiny";                       // 3º
        System.out.println("construtor");
    }

    public static void main(String[] args) {
        Chick chick = new Chick();
    }
}
