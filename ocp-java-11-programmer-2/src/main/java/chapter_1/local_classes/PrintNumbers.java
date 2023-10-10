package chapter_1.local_classes;


/**
 * Here's a complicated way to multiply two numbers:
 * References are allowed if local variable are final or effectively final.
 * If the local variables is final, Java can handle it by passing it to the constructor of the local class or by storing
 * it in the .class file. If it weren't effectively final, these tricks wouldn't work because the value could change
 * after the copy was made.
 */
public class PrintNumbers {


    private int length = 5;

    public void calculate(){
        final int width = 20;
        class MyLocalClass{
            public void multiply(){
                System.out.println(length * width);
            }
        }

        MyLocalClass local = new MyLocalClass();
        local.multiply();
    }

    public static void main(String[] args){
        PrintNumbers outer = new PrintNumbers();
        outer.calculate();
    }


}
