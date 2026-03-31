package chapter_03.operators;

public class ShortPlusShort {
    public static void main(String[] args) {

        short k = 2;
        short n = 5;

        // O println imprime 7 — mas o tipo do resultado é int!
        System.out.println(k + n); // 7

        // Prova que o resultado é int — isto NÃO COMPILA:
        // short resultado = k + n; // DOES NOT COMPILE ❌

        // Para guardar tens de fazer cast:
        short resultado = (short)(k + n); // ✅
        System.out.println(resultado); // 7

        // Ou usar var para veres o tipo inferido:
        var inferido = k + n;
        System.out.println(((Object)inferido).getClass().getSimpleName()); // Integer
    }
}

