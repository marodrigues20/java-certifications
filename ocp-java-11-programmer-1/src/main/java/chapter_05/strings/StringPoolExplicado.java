package chapter_05.strings;

public class StringPoolExplicado {

    public static void main(String[] args) {

        // CASO 1: dois literais → mesmo objeto no pool
        String x = "Hello World";
        String y = "Hello World";
        System.out.println(x == y);  // true — compilador reutiliza a entrada

        // CASO 2: literal + método em runtime → heap novo
        String z = " Hello World".trim();
        System.out.println(x == z);      // false — z está no heap normal
        System.out.println(x.equals(z)); // true  — conteúdo igual

        // CASO 3: concatenação de literais → compile-time → pool
        String a = "Hello" + " World";   // compilador junta isto antes de correr
        System.out.println(x == a);  // true — mesmo objeto no pool!

        // CASO 4: concatenação com variável → runtime → heap
        String part = " World";
        String b = "Hello" + part;       // 'part' só é conhecida em runtime
        System.out.println(x == b);  // false

        // CASO 5: new String() → sempre fora do pool
        String c = new String("Hello World");
        System.out.println(x == c);  // false

        // CASO 6: intern() → força o pool
        String d = new String("Hello World").intern();
        System.out.println(x == d);  // true — intern() encontrou "Hello World" no pool
    }
}

