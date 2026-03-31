package chapter_03.logical_operators;

public class Bloco12_ShortCircuit {
    public static void main(String[] args) {

        // --- Short-circuit OR: lado direito nunca avaliado ---
        int hour = 10;
        boolean zooOpen = true || (hour < 4);
        // lado esquerdo = true → resultado já é true → PARA!
        // (hour < 4) nunca é avaliado!
        System.out.println("zooOpen = " + zooOpen); // true

        System.out.println();

        // --- Unperformed side effect --- (armadilha do exame!)
        int rabbit = 6;
        boolean bunny = (rabbit >= 6) || (++rabbit <= 7);
        // lado esquerdo = true → PARA! → ++rabbit NUNCA executado!
        System.out.println("rabbit = " + rabbit); // 6 — NÃO foi incrementado!

        System.out.println();

        // --- & vs && (diferença real) ---
        int x = 5;
        boolean r1 = (x > 3) | (++x > 4);  // | avalia os dois lados
        System.out.println("x com |  = " + x); // 6 — x foi incrementado!

        x = 5; // reset
        boolean r2 = (x > 3) || (++x > 4); // || para no lado esquerdo!
        System.out.println("x com || = " + x); // 5 — x NÃO foi incrementado!

        System.out.println();

        // --- Uso clássico: evitar NullPointerException ---
        String duck = null;

        // ❌ Perigoso — & avalia os dois lados → NullPointerException!
        // if (duck != null & duck.getClass() != null) {}

        // ✅ Seguro — && para se duck for null → nunca chega ao duck.getClass()!
        if (duck != null && duck.getClass() != null) {
            System.out.println("duck não é null");
        } else {
            System.out.println("duck é null — sem NullPointerException!"); // ← este
        }
    }
}