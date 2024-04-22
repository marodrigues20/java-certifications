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


## Correct Answer: G