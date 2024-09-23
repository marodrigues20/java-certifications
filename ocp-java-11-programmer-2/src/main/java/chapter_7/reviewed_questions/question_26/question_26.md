## Assuming an implemenation of the *performCount()* method is provided prior to runtime,
## which of the following are possible results of executing the following application? (Choose all that apply.)

```
import java.util.*;
import java.util.concurrent.*;

public class CountZooAnimals {

    public static void performCount(int animal) {
        // IMPLEMENTATION OMITTED
    }
    
    public static void printResults(Future<?> f) {
       try{
           System.out.println(f.get(1, TimeUnit.DAYS)); // o1
         } catch (Exception e) {
            System.out.println("Exception!");
         }
    }
    
    public static void main(String[] args) throws Exception {
        ExecutorService s = null;
        final var r = new ArrayList<Future<?>>();
        try {
            s = Executors.newSingleThreadExecutor();
            for(int i=0; i<10; i++) {
                final int animal = i;
                r.add(s.submit(() -> performCount(i))); // o2
            }
            r.forEach(f -> printResults(f));
        } finally {
            if(s != null) s.shutdown();
        }        
    }
}
```

A. It outputs a number 10 times. <br>
B. It outputs a Boolean value 10 times. <br>
C. It outputs a null value 10 times. <br>
D. It outputs Exception! 10 times. <br>
E. It hangs indefinitely at runtime. <br>
F. The code will not compile because of line o1. <br>
G. The code will not compile because of line o2. <br>

Correct Answer: C, D.

- The code compiles and runs without issue, so options F and G are incorrect.
- The return type of *performCount()* is *void*, so *submit()* is interpreted as being applied to a *Runnable* expression.
- While *submit(Runnable)* does return a *Future<?>*, calling *get()* on it always returns *null*.
- For this reason, option A and B are incorrect, and option C is correct.
- The *performCount()* method can also throw a runtime exception, which will then be thrown by the *get()* call as an
  *ExecutionException;* therefore, option D is also a correct answer.
- Finally, it is also possible for our *performCount()* to hang indefinitely, such as with a deadlock or infinite loop.
- Luckily, the call to *get()* can wait up to a day for a result, it will eventually finish, so option E is incorrect.


