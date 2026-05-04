package chapter_9.deque;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;
import java.util.stream.IntStream;


/**
 * ArrayDeque implementa `Deque` que estende Queue — então tecnicamente os métodos de Queue estão disponíveis,
 * mas **semanticamente** para stack você usa push/pop/peek.
 */
public class ArrayDequeStackExample {

    public static void main(String[] args) {

        // Deque como Stack (LIFO) - Inseri e remove no início
        // push(), pop(), peek()

        System.out.println("************ Using ArrayDeque as a Stack (LIFO) ***************");
        List<String> stackList = List.of("method_3", "method_2", "method_1", "main_method");

        Deque<String> stack = new ArrayDeque<>(stackList);
        System.out.println("*********** Checking Current State of the Stack *****************");
        printStack(stack);

        System.out.println("*********** JVM empilha o método chamado *************");
        System.out.println("push() method at the top/begging of the stack/array: stack.push(method_4) and after stack.push(method_5)" );
        stack.push("method_4");
        stack.push("method_5");

        System.out.println("*********** Checking Current State of the Stack *****************");
        printStack(stack);


        System.out.println("*********** Checking Method Called but not removing it from Stack -> Last In (LI) *****************");
        System.out.println("peek() have a look at the Last in: " + stack.peek());


        System.out.println("************* Removing the last method_5 from Stack. Last In will be the First Out. **************");
        System.out.println("pop() method to remove the item \"method_5\" from Stack: " + stack.pop());

        System.out.println("*********** Checking Current State of the Stack *****************");
        printStack(stack);




    }

    private static void printStack(Deque<String> stack) {
        List<String> stackList = new ArrayList<>(stack);
        IntStream.range(0, stackList.size())
                .forEach(i -> System.out.println("Position [" + i + "] -> " + stackList.get(i)));
    }
}

// Deque como Array Circular