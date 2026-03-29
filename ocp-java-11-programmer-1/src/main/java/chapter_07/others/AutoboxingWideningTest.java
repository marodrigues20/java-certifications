package chapter_07.others;

public class AutoboxingWideningTest {

    public static void test(Long x) {
        System.out.println("Long");
    }

    public static void main(String[] args) {
        int i = 5;

         //test(i);  // ❌ descomente para ver o erro de compilação

        // Solução 1 — cast explícito
        test((long) i);  // ✅

        // Solução 2 — explícito com valueOf
        // Java promove o primitive para de int para long e depois faz o autoboxing
        test(Long.valueOf(i));  // ✅

        // Solução 3 — declarar como long direto
        long l = i;
        test(l);  // ✅
    }
}

