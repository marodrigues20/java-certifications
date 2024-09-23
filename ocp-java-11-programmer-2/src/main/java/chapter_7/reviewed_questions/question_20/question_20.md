## What is the result of executing the following program? (Choose all that apply.)

```java

import java.util.concurrent.Executors;

public class PrintCounter {
    static int count = 0;
    public static void main(String[] args) throws 
            InterruptedException, ExecutionException {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            var r = new ArrayList<Future<?>>();
            IntStream.iterate(0, i -> i+1).limit(5).forEach(
                    i -> r.add(service.execute(() -> { count++; })) // n1
            );
            for(Future<?> result : r) {
                System.out.print(result.get() + " "); // n2
            }
        }finally {
            if (service != null) service.shutdown();
        } } }
```

A. It prints 0 1 2 3 4. <br>
B. It prints 1 2 3 4 5. <br>
C. It prints null null null null null. <br>
D. It hangs indefinitely at runtime. <br>
E. The output cannot be determined. <br>
F. The code will not compile because of line n1. <br>
G. The code will not compile because of line n2. <br>

Correct Answer: F

- The key to solving this question is to remember that the *execute()* method returns *void*, not a *Future* object.
- Therefore, line *n1* does not compile, and option F is the correct answer.
- If the *submit()* method had been used instead of *execute()*, then option C would have been the correct answer,
  as the output of the *submit(Runnable)* task is a *Future<?>* object that can only return *null* on its *get()* method.