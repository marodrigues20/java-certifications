package chapter_05.strings;


/**
 * | Método                      | O que compara                       | Usar com                    |
 * |-----------------------------|-------------------------------------|-----------------------------|
 * | ==                          | Referência (mesmo objeto?)          | Primitivos; evitar em String|
 * | .equals()                   | Conteúdo (mesmos caracteres?)       | String ✅                   |
 * | .equals() em StringBuilder  | Referência (não implementado!)      | ⚠️ Armadilha                |
 *
 *
 * | Expressão                         | Vai para o pool?                    |
 * |-----------------------------------|-------------------------------------|
 * | "Hello" (literal)                 | Sim                                 |
 * | "Hel" + "lo" (compile-time)       | Sim                                 |
 * | new String("Hello")               | Não — heap separado                 |
 * | str.trim() / método em runtime    | Não                                 |
 * | new String("Hello").intern()      | Sim — força entrada no pool         |
 */
public class Bloco4_StringPoolEquality {

    // == em StringBuilder: compara referências
    static void equalidadeStringBuilder() {
        StringBuilder one = new StringBuilder();
        StringBuilder two = new StringBuilder();
        StringBuilder three = one.append("a"); // three aponta para ONE

        System.out.println(one == two);    // false — objetos diferentes
        System.out.println(one == three);  // true  — mesmo objeto!

        // StringBuilder NÃO implementa equals() → usa referência
        System.out.println(one.equals(three)); // true  (mesmo objeto)
        System.out.println(one.equals(two));   // false (objetos diferentes)

        // Para comparar CONTEÚDO de StringBuilders, converte para String:
        StringBuilder sb1 = new StringBuilder("abc");
        StringBuilder sb2 = new StringBuilder("abc");
        System.out.println(sb1.toString().equals(sb2.toString())); // true ✅
    }

    // String pool: literais partilham a mesma referência
    static void stringPool() {
        String x = "Hello World";
        String y = "Hello World";
        System.out.println(x == y);    // true  — ambos no pool, mesmo objeto

        // Runtime → novo objeto, fora do pool
        String z = " Hello World".trim();
        System.out.println(x == z);    // false — z foi criado em runtime
        System.out.println(x.equals(z)); // true  — conteúdo igual ✅
    }

    // new String() força criação fora do pool
    static void newStringForaDoPool() {
        String x = "Hello World";
        String y = new String("Hello World"); // forças novo objeto no heap
        System.out.println(x == y);       // false
        System.out.println(x.equals(y));  // true
    }

    // intern() força entrada no pool
    static void intern() {
        String name  = "Hello World";
        String name2 = new String("Hello World").intern(); // usa o pool!
        System.out.println(name == name2); // true — ambos apontam para o pool
    }

    // ARMADILHA CLÁSSICA do exame: compile-time vs runtime
    static void compilTimeVsRuntime() {
        String first  = "rat" + 1;               // compile-time → pool → "rat1"
        String second = "r" + "a" + "t" + "1";  // compile-time → pool → "rat1"
        String third  = "r" + "a" + "t" + new String("1"); // runtime → heap

        System.out.println(first == second);         // true  (ambos no pool)
        System.out.println(first == second.intern()); // true
        System.out.println(first == third);           // false (third no heap)
        System.out.println(first == third.intern());  // true  (intern busca o pool)
    }

    // NÃO COMPILA: == entre tipos incompatíveis
    static void naoCompila() {
        String string = "a";
        StringBuilder builder = new StringBuilder("a");
        //System.out.println(string == builder); // DOES NOT COMPILE!
        //O compilador sabe que nunca podem ser o mesmo objeto
        System.out.println("String == StringBuilder não compila!");
    }

    public static void main(String[] args) {
        //System.out.println("=== == em StringBuilder ===");
        //equalidadeStringBuilder();

        System.out.println("\n=== String Pool ===");
        stringPool();

        //System.out.println("\n=== new String() fora do pool ===");
        //newStringForaDoPool();

        //System.out.println("\n=== intern() ===");
        //intern();

        //System.out.println("\n=== Compile-time vs Runtime ===");
        //compilTimeVsRuntime();

        //System.out.println("\n=== Não Compila ===");
        //naoCompila();
    }
}


// Pontos-bomba para o exame:
//
//Dois literais iguais → sempre o mesmo objeto no pool → == dá true
//new String("x") → sempre cria novo objeto fora do pool → == dá false
//"a" + "b" em compile-time → vai para o pool; str1 + str2 em runtime → não vai
//StringBuilder.equals() não compara conteúdo — usa referência como ==
//Nunca uses == ou intern() em código real — só para o exame!

