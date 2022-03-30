package chapter_7.concurrencyapi.threadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Protecting Data with Atomic Classes
 *
 * One way to improve our sheep counting example is to use the java.util.concurrent.atomic package. As with many of the
 * classes in the Concurrency API, these classes exist to make your life easier.
 *
 * Atomic is the property of an operation to be carried out as a single unit of execution without any interference by
 * another thread. A thread-safe atomic version of the increment operator would be one that performed the read and
 * write of the variable as a single operation, not allowing any other threads to access the variable during the operation.
 * Any thread trying to access the sheepCount variable while an atomic operation is in process will have to wait until
 * the atomic operation on the variable is complete.
 *
 * Note: Thread synchronization using atomic operations
 *
 * Atomic Classes
 * Class Name    | Description
 * AtomicBoolean | A boolean value that may be updated atomically
 * AtomicInteger | An int value that may be updated atomically
 * AtomicLong    | A long value that may be updated atomically
 *
 * How do we use atomic class? Each class includes numerous methods that are equivalent to many of the primitive built-in
 * operators that we use one primitive, such as the assignment operator (=) and the increment operators (++)
 *
 *
 * Unlike our SheepManagerNoThreadSafe example output, the numbers 1 through 10 will always be printed, although the
 * order is still not guaranteed.
 *
 * Common atomic methods:
 *
 * Method Name        |
 * get()              | Retrieves the current value
 * set()              | Sets the given value, equivalent to the assignment = operator
 * getAndSet()        | Atomically sets the new value and returns the old value
 * incrementAndGet()  | equivalent ++value
 * getAndIncrement()  | equivalent value++
 * decrementAndGet()  | --value
 * getAndDecrement()  | value--
 *
 *
 */
public class SheepManagerAtomicClass {

    private AtomicInteger sheepCount = new AtomicInteger(0);

    private void incrementAndReport(){
        System.out.print((sheepCount.incrementAndGet()) + " ");
    }

    public static void main(String[] args) {
        ExecutorService service = null;

        try{
            service = Executors.newFixedThreadPool(20);
            SheepManagerAtomicClass manager = new SheepManagerAtomicClass();
            for(int i = 0; i < 10; i++){
                service.submit(() -> manager.incrementAndReport());
            }
        }finally {
            if(service != null) service.shutdown();
        }
    }


}
