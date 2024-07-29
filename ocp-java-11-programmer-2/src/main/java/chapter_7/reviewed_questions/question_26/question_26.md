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
        ExecutorService s = null'
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

A. It outputs a number 10 times.
B. It outputs a Boolean value 10 times.
C. It outputs a null value 10 times.
D. It outputs Exception! 10 times.
E. It hangs indefinitely at runtime.
F. The code will not compile because of line o1.
G. The code will not compile because of line o2.

Correct Answer: