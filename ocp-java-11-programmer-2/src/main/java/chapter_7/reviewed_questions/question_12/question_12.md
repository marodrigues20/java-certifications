## Assuming takeNap() is a method that takes five seconds to execute without throwing an exception,
## what is the expected result of executing the following code snippet?

```java
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

private void test() {

    ExecutorService service = null;
    try {
        service = Executors.newFixedThreadPool(4);
        service.execute(() -> takeNap());
        service.execute(() -> takeNap());
        service.execute(() -> takeNap());
    } finally {
        if (service != null) service.shutdown();
    }
    service.awaitTermination(2, TimeUnit.SECONDS);
    System.out.println("DONE!");
}
```


A. It will immediately print DONE!
B. It will pause for 2 seconds and then print DONE!.
C. It will pause for 5 seconds and then print DONE!.
D. It will pause for 15 seconds and then print DONE!.
E. It will throw an exception at runtime.
F. None of the above, as the code does not compile.


## Correct Answer: B

- The code snippet submits three tasks to an *ExecutorService*, shuts it down, and then waits for the results.
- The *awaitTermination()* method waits a specified amount of time for all tasks to complete, and the service to finish
  shutting down.
- Since each five-seconds tasks is still executing, the *awaitTermination()* method will return with a value of *false* 
  after two seconds but now throw an exception.
- For this reason, option B is correct.