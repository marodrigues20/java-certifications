## Which lines need to be changed to make the code compile? (Choose all that apply.)

```java
public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService service = //w1
                Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(() -> {
            System.out.println("Open Zoo");
            return null; // w2
        }, 0, 1, TimeUnit.MINUTES);
        var result = service.submit(() ->  // w3
                System.out.println("Wake Staff"));
        System.out.println(result.get()); // w4
    }
```

A. It compiles and runs without issue.
B. Line w1
C. Line w2
D. Line w3
E. Line w4
F. It compiles but throws an exception at runtime.
G. None of the above

## Correct Answer: B, C

- The code does not compile, so options A and F are incorrect.
- The first problem is that although a *ScheduledExecutorService* is created, it is assigned to an *ExecutorService*.
- The type of the variable on line w1 would have to be updated to *ScheduledExecutorService* for the code to compile,
  option B correct.
- The second problem is that scheduleWithFixedDelay() supports only *Runnable*, not *Callable*, and any attempt to 
  return a value is invalid in *Runnable* lambda expression; therefore, line w2 will also not compile, and option C is 
  correct.
- The rest of the lines compile without issue, so options D and E are incorrect.

