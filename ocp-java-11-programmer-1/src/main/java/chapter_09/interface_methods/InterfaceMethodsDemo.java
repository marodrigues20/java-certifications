package chapter_09.interface_methods;

public class InterfaceMethodsDemo {
    public static void main(String[] args) {

        // static method — chama via nome da interface
        Validator v = Validator.create();

        // default method — disponível via instância
        v.printResult("Hello");   // Valid: true
        v.printResult("");        // Valid: false
        v.printResult(null);      // checkNotNull protege — não imprime
    }
}
