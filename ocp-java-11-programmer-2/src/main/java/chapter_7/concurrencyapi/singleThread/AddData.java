package chapter_7.concurrencyapi.singleThread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Introducing Callable
 *
 * The java.util.concurrent.Callable functional interface is similar to Runnable except that its call() method returns
 * a value and can throw a checked exception.
 * The Callable interface is often preferable over Runnable, since it allows more details to be retrieved easily from
 * the tasks after it is completed.
 *
 * ExecutorService includes an overloaded version of the submit() method that takes a Callable object and returns a
 * generic Future<T> instance.
 *
 * Unlike Runnable, in which the get() method always return null, the get() methods on a Future instance return the
 * matching generic type (which could also be a null value)
 */
public class AddData {

    public static void main(String[] args) throws Exception {

        ExecutorService service = null;
        try{

            service = Executors.newSingleThreadExecutor();
            Future<Integer> result = service.submit(() -> 30 + 11);
            System.out.println(result.get());

        }finally {
            if(service != null) service.shutdown();
        }

    }
}
