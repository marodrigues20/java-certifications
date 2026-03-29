package chapter_07.others;

public class AutoboxingVsPromotionTest {

    public static void test(Integer x) {
        System.out.println("Integer — autoboxing");
    }

    public static void test(long x) {
        System.out.println("long — promoção");
    }

    public static void main(String[] args) {
        int i = 5;
        test(i);  // long — promoção vence autoboxing!
    }
}

