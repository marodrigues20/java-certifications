## Assuming 100 milliseconds is enough time for the tasks submitted to the service executor to complete,
## what is the result of executing the following method? (Choose all that apply.)

```
    private AtomicInteger s1 = new AtomicInteger(0); // w1
    private int s2 = 0;

    private void countSheep() throws InterruptedExecution {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor(); // w2
            for(int i=0; i < 100; i++)
                service.execute(() -> {
                    s1.getAndIncrement(); s2++}); // w3
            Thread.sleep(100);
            System.out.println(s1 + " " + s2);
        } finally {
            if(service != null) service.shutdown();
        }
    }
```

A. The method consistently prints 100 99.           <br>
B. The method consistently prints 100 100.          <br>
C. The output cannot be determined ahead of time.   <br>
D. The code will not compile because of line w1.    <br>
E. The code will not compile because of line w2.    <br>
F. The code will not compile because of line w3.    <br>
G. It compiles but throws an exception at runtime.  <br>

Correct Answer: B

- The code compiles and runs without issue, so options D, E, F, and G are incorrect.
- The key aspect to notice in the code is that a single-thread executor is used, meaning that no task will be executed 
  concurrently.
- Therefore, the results are valid and predictable with 100 100 being the output, and option B is the correct answer.
- If a pooled thread executor was used with at least two threads,then the *sheepCount2++* operations could overwrite 
  each other, making the second value indeterminate at the end of the program.
- In this case, option C would be the correct answer.