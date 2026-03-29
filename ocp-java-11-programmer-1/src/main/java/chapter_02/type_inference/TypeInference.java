package chapter_02.type_inference;


/**
 * 7 regras do var:
 *
 * Só funciona em variáveis locais
 * Deve ser inicializado na mesma linha
 * O tipo não pode mudar, o valor pode
 * Não pode ser inicializado com null
 * Não funciona em parâmetros de método
 * Não funciona em múltipla declaração
 * Não pode ser nome de classe ou interface
 */
public class TypeInference {

    private void test(){

        // Desde Java 10, o compilador pode inferir o tipo automaticamente:
        var name = "Hello";  // compilador infere String
        var size = 7;        // compilador infere int


        var x = 7;

        //x = "texto";          // ❌ tipo inferido como int, não muda

        //var n = null;         // ❌ compilador não sabe o tipo

        //var a = 2, b = 3;     // ❌ múltipla declaração

        //public int add(var a) // ❌ parâmetro de método

        //public class var { }  // ❌ nome de classe




    }
}
