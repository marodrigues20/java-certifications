package chapter_07.statics;

public class Counter {
    private static int count = 5;

    public static int getCount() {
        return count;
    }

    public static void main(String[] args) {
        Counter c = null;

        // Parece que vai lançar NullPointerException...
        // mas NÃO! O compilador sabe que getCount() é static
        // e compila como Counter.getCount() por baixo dos panos
        System.out.println(c.getCount());  // imprime 5 ✅

        // Equivalente a:
        System.out.println(Counter.getCount());  // imprime 5 ✅
    }
}
