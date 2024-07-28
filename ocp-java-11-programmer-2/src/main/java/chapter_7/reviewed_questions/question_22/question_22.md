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

A. The method consistently prints 100 99.
B. The method consistently prints 100 100.
C. The output cannot be determined ahead of time.
D. The code will not compile because of line w1.
E. The code will not compile because of line w2.
F. The code will not compile because of line w3.
G. It compiles but throws an exception at runtime.

Correct Answer: C