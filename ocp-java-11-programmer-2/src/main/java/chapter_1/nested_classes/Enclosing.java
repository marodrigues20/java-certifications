package chapter_1.nested_classes;

public class Enclosing {
    static class Nested{
        private int price = 6;
    }


    /**
     * Since the class is static, you do not need an instance of Enclosing to use it. Your are allowed to access private
     * instances variables.
     * @param args
     */
    public static void main(String[] args){
        Nested nested = new Nested();
        System.out.println(nested.price);
    }
}
