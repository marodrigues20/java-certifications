package chapter_3.review_questions;


/**
 * Question 6
 * Correct is B.
 * The line 26 compiles fine because toString() is defined on the Object class and is therefore always available to call.
 *
 * Line 35 creates the Hello class with the generic type String. It also passes an int to the println() method,
 * which gets autoboxed into an Integer. While the println() method takes a generic parameter of type T, it is not the
 * same <T> defined for the class on line 18. Instead, it is a different T defined as part of the method declaration on
 * line 30. Therefore, the String argument on line 35 applies only to the class. The method can actually take any object as
 * a parameter including autoboxed primitives.
 * Line 36 creates the Hello class with the generic type Object since no type is specified for that instance.
 * It passes a boolean to println(), which gets autoboxed into a Boolean.
 * The result is that hi-1hola-true is printed, making option B correct.
 */
public class Hello<T> {

   T t;

   public Hello(T t) {
       this.t = t;
   }

   public String toString(){
       return t.toString();
   }

   private <T> void println(T message){
       System.out.println(t + "-" + message);
   }

    public static void main(String[] args) {
        new Hello<String>("hi").println(1);
        new Hello("hola").println(true);
    }

}
