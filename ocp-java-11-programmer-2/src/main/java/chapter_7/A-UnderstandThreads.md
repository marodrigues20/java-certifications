## Chapter 7 - Concurrency

### Objectives covered in this chapter

- Concurrency
    - Create worker threads using Runnable, Callable and use an ExecutorService to concurrently execute tasks.
    - Use *java.util.concurrent* collections and classes including CyclicBarrier and CopyOnWriteArrayList
    - Write thread-safe code
    - Identity threading problems such as deadlocks and live-locks.
- Parallel Streams
    - Develop code that uses parallel streams
    - Implement decomposition and reduction with streams


- As you will learn in Chapter 8, "I/O", Chapter 9 "NIO.2", and Chapter 10, "JDBC", computers are capable of reading
  and writing data to external resources. Unfortunately, as compared to CPU operations, these disk/network operations
  tend to be extremely slow-so slow, in fact, that if your computer's operating system were to stop and wait for every
  disk or network operation to finish, your computer would appear to freeze or lock up constantly.

- Luckily, all moderns operating system support what is known as multithreading processing.
- The idea behind multithreading processing is to allow an application or group of applications to execute multiple
  tasks at the same time.

- Since its early days, Java has supported multithreading programming using Thread class. More recently, the Concurrency
  API
  was introduced. It included numerous classes for performing complex thread-based tasks. The idea was simple: managing
  complex thread interactions is quite difficult for even the most skilled developers; therefore, a set of reusable
  features was created. The Concurrency API has grown over the years to include numerous classes and frameworks to
  assist
  you in developing complex, multithreading applications.

- Threads and concurrency tend to be one of the more challenging topics for many programmers to grasp.

## Introducing Threads

- A thread is the smallest unit of execution that can be scheduled by the operating system. A process is a group of
  associated threads that execute in the same, shared environment. It follows, then, that a single-threaded process is
  one that contains exactly one thread, whereas a multithreading process is one that contains one or more threads.

- By shared process, we mean that the threads in the same process share the same memory space and can communicate
  directly
  with one another. Refer to Figure 7.1 for an overview of threads and their shared environment within a process.

![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_7/images/Figure_7_1.png?raw=true)

- Figure 7.1 shows a single process with three threads. It also shows how they are mapped to an arbitrary number of *n*
  CPUs available within the system. Keep this diagram in mind when we discuss task schedulers later in this section.

- A task is a single unit of work performed by a thread.
- A thread can complete multiple independent tasks but only one task at a time.
- By shared memory in Figure 7.1, we are generally referring to *static* variables, as well as instance and local
  variables passed to a thread.

## Distinguishing Thread Types

- A system thread is created by the JVM and runs in the background of the application.
- For example, the garbage collection is managed by a system thread that is created by the JVM and runs
  in the background.
- For the most part, the execution of system-defined threads is invisible to the application developer.
- When a system-defined thread encounters a problem and cannot recover, such as running out of memory,
  it generates a Java Error, as opposed to an Exception.

> Note: Even though it is possible to catch an Error, it is considered a poor practice to do so, since it is rare that
> an application can recover from a system-level failure.

- Alternatively, a ***user-defined*** thread is one created by the application developers to accomplish a specific task.
- With the exception of parallel streams presented briefly in Chapter 4, "Functional Programming," all applications
  that we have created up to this point have been multithreading, but they contained only one user-defined thread,
  which calls the main() thread. For simplicity, we commonly refer to threads that contain only a single user-defined
  thread as a single-threaded application, since we are often uninterested in the system threads.

> Note: Daemon thread is one that will not prevent the JVM from exiting when the program finishes.
> A Java application terminates when the only threads that are running are daemon threads.
> For example, if garbage collection is the only thread left running, the JVM will automatically shut down.
> Both system and user-defined thread can be marked as daemon threads.

## Understanding Thread Concurrency

- The property of executing multiple threads and processes at the same time is referred to as concurrency.
- Of course, with a single-core CPU system, only one task is actually executing. For example, a thread scheduler may
  employ a round-robin schedule in which available thread receives an equal number of CPU cycles with which to execute,
  with threads visited in a circular order. If there are 10 available threads, they might each get 100 milliseconds in
  which to execute, with the process returning to the first thread after the last thread has executed.

- A context switch is the process of storing a thread's current state and later restoring the state of the thread to
  continue execution.

- Finally, a thread can interrupt or supersede another thread if it has a higher thread priority than the order thread.
  A thread priority is a numeric value associated with a thread that is taken into consideration by the thread scheduler
  when determining which threads should currently be executing. In Java, thread priorities are specified as integer
  values.

---

### Real World Scenario ###

### The importance of Thread Scheduling ###

- Even though multicore CPUs are quite common these days, single-core CPUs were the standard in personal computing for
  many decades. During this time, operating systems developed complex thread-scheduling and context-switching algorithms
  that allowed users to execute dozens or even hundreds of threads on a single-core CPU system.
- These scheduling algorithms allowed users to experience the illusion that multiple tasks were being performed at the
  same time within a single-CPU system. For example, a user could listen to music while writing a paper and received
  notifications for new messages.
- Since the number of threads requested often far outweighs the numbers of processors available even in multicore
  system,
  these thread-scheduling algorithms are still employed in operating system today.

---

## Defining a Task with Runnable

- *java.lang.Runnable* is a functional interface that takes no arguments and returns no data.
- The following is the definition of the *Runnable* interface:

```java

@FunctionalInterface
public interface Runnable {
    void run();
}
```

- The Runnable interface is commonly used to define the task or work a thread will execute, separate from the main
  application thread. We will be relying on the Runnable interface throughout this chapter, especially when we discuss
  applying parallel operations to stream.

- The following lambda expressions each implement the *Runnable* interface:

```java
Runnable sloth = () -> System.out.println("Hello World");
Runnable snake = () -> {
    int i = 10;
    i++;
};
Runnable beaver = () -> {
    return;
};
Runnable coyote = () -> {
};
```

- Notice that all of these lambda expressions start with a set of empty parentheses, ().
- Also, none of the lambda expression returns a value.
- The following lambdas, while valid for other functional interfaces, are not compatible with *Runnable* because they
  return a value.

```java
Runnable capybara = () -> "";                   // DOES NOT COMPILE
Runnable Hippopotamus = () -> 5;                // DOES NOT COMPILE
Runnable emu = () -> {
    return new Object();
};  // DOES NOT COMPILE
```

## Creating Runnable Classes

- Even though *Runnable* is a functional interface, many classes implement it directly, as shown in the following code:

i.e: chapter_7.runnable.CalculateAverage.java

```java
public class CalculateAverage implements Runnable {
    public void run() {
        // Define work here
    }
}
```

- It is also useful if you need to pass information to your *Runnable* object to be used by the run() method, such as in
  the following constructor:

i.e: chapter_7.runnable.CalculateAverages.java

```java
public class CalculateAverages implements Runnable {
    private double[] scores;

    public CalculateAverages(double[] scores) {
        this.scores = scores;
    }

    public void run() {
        // Define work here that uses the scores object
    }
}
```

- In this chapter, we focus on creating lambda expressions that implicitly implement the *Runnable* interface.
- Just be aware that it is commonly used in class definitions.

## Creating a Thread

- The simplest way to execute a thread is by using the *java.lang.Thread" class.
- Executing a task with Thread is a two-step process.
- First, you define the *Thread* with the corresponding task to be done.
- Then, you start the task by using the *Thread.start()* method.

> Note: Remember that order of thread execution is not often guaranteed. <br>
> The exam commonly presents questions in which multiple tasks are started at the same time, and you must determine the
> result.

- Defining the task that a Thread instance will execute can be done two ways in Java:
    - Provide a *Runnable* object or lambda expression to the *Thread* constructor.
    - Create a class that extends *Thread* and overrides the *run()* method.

- The following are example of these techniques:

i.e: i.e: chapter_7.runnable.PrintData.java

```java
public class PrintData implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 3; i++)
            System.out.println("Printing record: " + i);
    }

    public static void main(String[] args) {
        (new Thread(new PrintData())).start();
    }
}
```

i.e: chapter_7.runnable.ReadInventoryThread.java

```java
public class ReadInventoryThread extends Thread {
    @Override
    public void run() {
        System.out.println("Printing zoo inventory");
    }

    public static void main(String[] args) {
        (new ReadInventoryThread()).start();
    }
}
```

- Let's try this.
- What is the output of the following code snippet using these two classes?

i.e: chapter_7.thread.ThreadAndRunnableTest.java

```
2:   public static void main(String[] args) {
3:       System.out.println("begin");
4:       (new ReadInventoryThread()).start();
5:       (new Thread(new PrintData())).start();
6:       (new ReadInventoryThread()).start();
7:       System.out.println("end");
8:    }
```

- The answer is that it is unknown until runtime.
- The following is just one possible output:

```
begin
Printing zoo inventory
end
Printing zoo inventory
Printing record: 0
Printing record: 1
Printing record: 2
```

- This sample uses a total of four threads-the main() user thread and three additional threads created on lines 4-6.
- Each thread created on these lines is executed as an asynchronous task.
- By asynchronous, we mean that the thread executing the main() method does not wait for the result of each newly
  created thread before continuing.
- For example, lines 5 and 6 may be executed before the thread created on line 4 finishes.
- The opposite of this behavior is a *synchronous* task in which the program waits (or blocks) on line 4 for the thread
  to finish executing before moving on to the next line.
- The vast majority of method calls used in this book have been synchronous up until now.
- While the order of thread execution once the thread have been started is indeterminate, the order within a single
  thread is still linear. In particular, the *for()* loop in *PrintData* is still ordered. Also, *begin* appears before
  *end* in the main() method.

---

### Calling run() Instead of start() ###

- Be careful with code that attempts to start a thread by calling run() instead of start().
- Calling *run()* on a *Thread* or a *Runnable* does not actually start a new thread.
- While the following code snippets will compile, none will actually execute a task on a separate thread:

i.e: chapter_7.thread.ThreadAndRunnableUsingRunMethod.java

```
    System.out.println("begin");
    (new ReadInventoryThread()).run();
    (new Thread(new PrintData())).run();
    (new ReadInventoryThread()).run();
    System.out.println("end");
```

- Unlike the previous example, each line of this code will wait until the *run()* method is complete before moving on to
  the next line.
- Also unlike the previous program, the output for this code sample will be the same each time it is executed.

---

- In general, you should extend the *Thread* class only under specific circumstances, such as when you are creating your
  own priority-based thread.
- In most situations, you should implement the *Runnable* interface rather than extend the Thread class.
- For the exam, previous version required understand difference between extending *Thread* and implement *Runnable*.
- The exam now strongly encourages developers to use the Concurrency API.
- For the exam, you also do not need to know about other thread-related methods, such as *Object.wait()*,
  *Object.notify()*, *Thread.join()*, etc. In fact, you should avoid them in general and use the Concurrency API as much
  as possible. It takes a large amount of skill (and some luck!) to use these methods correctly.

---

### Real World Scenario ###

### For interviews, Be Familiar with Thread-Creation Option ###

- Despite that the exam no long focuses on creating threads by extending the *Thread* class and implementing the
  Runnable
  interface.
- If asked this question, you should answer it accurately.
- You should also mention that you can now create and manage threads indirectly using an ExecutorService.

---

## Polling with Sleep

- Even though multi-threaded programming allows you to execute multiple tasks at the same time, one thread often needs
  to wait for the results of another thread to proceed.
- One solution is to use polling.
- Polling is the process of intermittently checking data at some fixed interval.

i.e: chapter_7.pooling.CheckResults.java

```java
public class CheckResults {
    private static int counter = 0;

    public static void main(String[] args) {
        new Thread(() -> {
            for (int i = 0; i < 500; i++)
                CheckResults.counter++;
        }
        ).start();

        while (CheckResults.counter < 100) {
            System.out.println("Not reached yet");
        }

        System.out.println("Reached");

    }
}
```

- How many times does this program print "Not reached yet"?
- The answer is, we don't know! It could output zero, ten, or a million times.
- If our thread scheduler is particularly poor, it could operate infinitely!
- Using a while() loop to check for data without some kind of delay is considered a bad coding practice as it ties up
  CPU resources for no reason.
- We can improve this result by using the *Thread.sleep()* method to implement polling.
- The *Thread.sleep()* method requests the current thread of execution rest for a specified number of milliseconds.
- When used inside the body of the *main()* method, the thread associated with the *main()* method will pause, while the
  separate thread will continue to run.
- Compare the previous implementation with the following one that uses *Thread.sleep();*

```java
  public static void main(String[] args) throws InterruptedException {
    new Thread(() -> {
        for (int i = 0; i < 500; i++)
            CheckResults_v2.counter++;
    }
    ).start();
    while (CheckResults_v2.counter < 100) {
        System.out.println("Not reached yet");
        Thread.sleep(1000); // 1 SECOND
    }
    System.out.println("Reached");
}
```

- In this example, we delay 1,000 milliseconds at the end of the loop, or 1 second.
- While this may seem like a small amount, we have now prevented a possibly infinite loop from executing and locking up
  our program.
- Notice that we also changed the signature of the *main()* method, since *Thread.sleep()* throws the checked
  *InterruptedException*.
- Alternatively, we could have wrapped each call to the *Thread.sleep()* method in a *try/catch* block.
  <br>
  <br>
- How many times does the *while()* loop execute in this revised class?
- Still unknown!
- While pooling does prevent the CPU from being overwhelmed with a potentially infinite loop, it does not guarantee when
  the loop will terminate. For example, the separate thread could be losing CPU time to a higher-priority process,
  resulting in multiple executions of the *while()* loop before it finishes.
  <br>
  <br>
- Another issue to be concerned about is the shared *counter* variable.
- What if one thread is reading the *counter* variable another thread is writing it?
- The thread reading the shared variable may end up with an invalid or incorrect value.
- We will discuss these issues in detail in the upcoming section on writing thread-safe code.

## Creating Threads with the Concurrency API

- Java includes the Concurrency API to handle the complicated work of managing threads for you.
- The Concurrency API includes the ExecutorService interface, which defines services that create and manage threads for
  for you.
- It is recommended that you use this framework anytime you need to create and execute a separate task, even if you need
  only a single thread.

## Introducing the Single-Thread Executor

- Since *ExecutorService* is an interface, how do you obtain an instance of it?
- The Concurrency API includes the *Executors* factory class that can be used to create instances of the
  *ExecutorService* object.
- Let's start with a simple example using the *newSingleThreadExecutor()* method to obtain an *ExecutorService* instance
  and the *execute()* method to perform asynchronous tasks.

i.e: chapter_7.concurrencyapi.singleThread.ZooInfo.java

```java
import java.util.concurrent.*;

public class ZooInfo {
    public static void main(String[] args) {
        ExecutorService service = null;
        Runnable task1 = () ->
                System.out.println("Printing zoo inventory");
        Runnable task2 = () ->
        {
            for (int i = 0; i < 3; i++)
                System.out.println("Printing record: " + i);
        };
        try {
            service = Executors.newSingleThreadExecutor();
            System.out.println("begin");
            service.execute(task1);
            service.execute(task2);
            service.execute(task1);
            System.out.println("end");
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
```

- As you may notice, this is just a rewrite of our earlier PrintData and ReadInventoryThread classes.
- In this example, we use the *Executors.newSingleThreadExecutor()* method to create the service.
- Unlike our earlier example, in which we had three extra theads for newly created tasks, this example uses only one,
  which means that the threads will order their results.
- For example, the following is a possible output for this code snippet:

```
begin
end
Printing zoo inventory
Printing record: 0
Printing record: 1
Printing record: 2
Printing zoo inventory
```

- With a single-thread executor, result are guaranteed to be executed sequentially.
- Notice that the *end* text is output while our thread executor tasks are still running.
- This is because the *main()* method is still an independent thread from the *ExecutorService*.

## Shutting Down a Thread Executor

- Once you have finished using a thread executor, it is important that you call the *shutdown()* method.
- A thread executor a non-daemon thread on the first task that is executed, so failing to call *shutdown()* will result
  in your application never terminating.

<br>
<br>

- The shutdown process for a thread executor involves first rejection any new tasks submitted to the thread executor
  while continuing to execute any previously submitted tasks.
- During this time, calling *isShutdown()* will return *true*, while *isTerminated()* will return *false*.
- If a new task is submitted to the thread executor while it is shutting down, a *RejectedExecutionException* will be
  thrown. Once all active tasks have been completed, *isShutdown()* and *isTerminated()* will both return *true*.
- Figure 7.2 shows the life cycle of an *ExecutorService* object.

![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_7/images/Figure_7_2.png?raw=true)

- The *ExecutorService* provides a method called *shutdownNow()*, which attempts to stop all running tasks and discard
  any that have not been started yet.
- It is possible to create a thread that will never terminate, so any attempt to interrupt it may be ignored.
- Lastly, *shutdownNow()* returns a *List<Runnable>* of tasks that were submitted to the thread executor but that were
  never started.

> Tip: As you learned in Chapter 5, resources such as thread executors should be properly closed to prevent memory
> leaks.
> Unfortunately, the ExecutorService interface does not extend the AutoCloseable interface, so you cannot use a
> try-with-resources statement. You can still use a finally block. Not required but it is considered a good practice
> to do so.

## Submitting Tasks

- The first method we presented, *execute()*, is inherited from the *Executor* interface, which the *ExecutorService*
  interface extends.
- Because the return type of the method is *void*, it does not tell us anything about the result of the task. It is
  considered a "fire-and-forget" method, as once it is submitted, the results are not directly available to the calling
  thread.
- Fortunately, the writers of Java added *submit()* methods to the *ExecutorService* interface, which, like *execute()*,
  can be used to complete tasks asynchronously.
- Unlike *execute()*, though, *submit()* returns a *Future* instance that can be used to determine whether the task is
  complete. It can also be used to return a generic result object after the task has been completed.

---

### TABLE 7.1 ExecutorService methods ###

| Method name                                                                                              | Description                                                                                                                                                  |
|----------------------------------------------------------------------------------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------|
| void execute(Runnable command)                                                                           | Executes a *Runnable* task at some point in the future                                                                                                       |
| Future<?> submit(Runnable task)                                                                          | Executes a *Runnable* task at some point in the future and returns a *Future* representing the task                                                          |
| <T> Future<T> submit(Callable<T> task)                                                                   | Executes a Callable task at some point in the future and returns a *Future* representing the pending results of the task                                     |
| <T> List<Future<T>> invokeAll(Collection<? extends Callable<T>> tasks) throws InterruptedException       | Executes the given tasks and waits for all tasks to complete. Returns a *List* of *Future* instances, in the same order they were in the original collection |
| <T> T invokeAny(Collection<? extends Callable<T>> tasks) thorws InterruptedException, ExecutionException | Executes the given tasks and waits for at least one to complete. Returns a *Future* instance for a complete task and cancels any unfinished tasks            |

---

> Submitting Tasks: execute() vs. submit()
> In your own code we recommend submit() over execute() whenever possible

## Waiting for Results

- How do we know when a task submitted to an *ExecutorService* is complete? As mentioned in the previous section,
  she *submit()* method returns a *java.util.concurrent.Future<V> instance that can be used to determine this result.

```java
Future<?> future = service.submit(() -> System.out.println("Hello"));
```

---

### TABLE 7.2 *Future* methods ###

| Method name                                   | Description                                                                                                                                                                       |
|-----------------------------------------------|-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| boolean isDone()                              | Returns *true* if the task was completed, threw an exception, or was cancelled                                                                                                    |
| boolean isCancelled()                         | Returns *true* if the task was cancelled before it completed normally                                                                                                             |
| boolean cancel(boolean mayInterruptIfRunning) | Attempts to cancel execution of the task and returns *true* if it was successfully cancelled or *false* if it could not be cancelled or is complete                               |
| V get()                                       | Retrieves the result of a task, waiting endlessly if it is not yet available                                                                                                      |
| V get(long timeout, TimeUnit unit)            | Retrieves the result of a task, waiting the specified amount of time. If the result is not ready by the time the timeout is reached, a checked *TimeoutException* will be thrown. |

---

- The following is an updated verstion of our earlier polling example *CheckResults* class, which uses a *Future*
  instance
  to wait for the results:

i.e: chapter_7.wait_results.CheckResults_v3.java

```java
import java.util.concurrent.*;

public class CheckResults_v3 {
    public static void main(String[] unused) {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            Future<?> result = service.submit(() -> {
                for (int i = 0; i < 500; i++) CheckResults_v3.counter++;
            });
            result.get(10, TimeUnit.SECONDS);
            System.out.println("Reached!");
        } catch (TimeoutException e) {
            System.out.println("Not reached in time");
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
```

- This example is similar to our earlier polling implementation, but it does not use the *Thread* class directly.
- In part, this is the essence of the Concurrency API: to do complex things threads without having to manage threads
  directly.
- As *Future<V>* is a generic interface, the type V is determined by the return type of the Runnable method.
- Since the return type of *Runnable.run()* is *void*, the *get()* method always returns *null* when working with
  *Runnable* expressions.
- The *Future.get()* method can take an optional value and enum type *java.util.concurrent.TimeUnit*.
- We present the full list of *TimeUnit* values in Table 7.3 in increasing order of duration.

---

### TABLE 7.3 TimeUnit values ###

| Enum name             | Description                                         |
|-----------------------|-----------------------------------------------------|
| TimeUnit.NANOSECONDS  | Time in one-billionth of a second (1/1,000,000,000) |
| TimeUnit.MICROSECONDS | Time in one-millionth of a second (1/1,000,000)     |
| TimeUnit.MILLISECONDS | Time in one-thousandth of a second (1/1,000)        |
| TimeUnit.SECONDS      | Time in seconds                                     |
| TimeUnit.MINUTES      | Time in minutes                                     |
| TimeUnit.HOURS        | Time in hours                                       |
| TimeUnit.DAYS         | Time in days                                        |

---

## Introducing Callable

- The *java.util.concurrent.Callable* functional interface is similar to *Runnable* except exept that its *call()*
  method
  returns a value and can throw a checked exception.

```java

@FunctionalInterface
public interface Callable<V> {
    V call() throws Exception;
}
```

- The *Callable* interface is often preferable over *Runnable*, since it allows more details to be retrieved easly from
  the task after it is completed.
- The *ExecutorService* includes an overloaded version of the *submit()* method that takes a *Callable* object and
  returns a generic *Future<T>* instance

- Let's take a look at an example using *Callable*.

i.e: chapter_7.concurrencyapi.callable.AddData.java

```java
import java.util.concurrent.*;

public class AddData {
    public static void main(String[] args) throws Exception {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            Future<Integer> result = service.submit(() -> 30 + 11);
            System.out.println(result.get()); // 41
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
```

## Waiting for All Tasks to Finish

- If we don't need the results of the tasks and are finished using our thread executor, there is a simplier approach.
- First, we shut down the threads executor using the *shutdown()* method.
- Next, we use the *awaitTerminiation()* method available for all thread executors. The method waits the specified time
  to complete all tasks, returning sooner if all tasks finish or an *InterruptedException* is detected.
- You can see an example of this in the following code snippet:

i.e: chapter_7.concurrencyapi.callable.AddData_v2.java

```java
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AddData_v2 {
    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newSingleThreadExecutor();
            // Add tasks to the thread executor
            // ...
        } finally {
            if (service != null) service.shutdown();
        }

        if (service != null) {
            service.awaitTermination(1, TimeUnit.MINUTES);

            // Check whether all tasks are finished
            if (service.isTerminated()) System.out.println("Finished");
            else System.out.println("At least one task is still running");
        }
    }
}
```

- In this example, we submit a number of tasks to the thread executor and then shut down the thread executor and wait up
  to one minute for the results.
- Notice that we can call *isTerminated()* after the *awaitTermination()* method finishes to confirm that all tasks
  are actually finished.

> Note: If *awaitTermination()* is called before *shutdown()* within the same thread, then that thread will wait the
> full
> timeout value sent with *awaitTermination().

## Submitting Task Collections

- You should know for the exam the methods *invokeAll()* and *invokeAny()*.
- Both of these methods execute synchronously and take a Collection of tasks.
- The *invokeAll()* method executes all tasks ina provided collection and returns a *List* of ordered *Future*
  instances,
  with one *Future* instance corresponding to each submitted task, in the order they were in the original collection.

i.e: chapter_7.concurrencyapi.future.InvokeAll.java

### invokeAll() method

```
20: ExecutorService service = ...
21: System.out.println("begin");
22: Callable<String> task = () -> "result";
23: List<Future<String>> list = service.invokeAll(
24:        List.of(task, task, task));
25: for(Future<String> future : list){
26:  System.out.println(future.get());
27: }
28: System.out.println("end");
```

- In this example, the JVM waits on line 23 for all tasks to finish before moving on line 25.
- Unlike our earlier examples, this means that *end* will always be printed last.
- Also, even though *future.isDone()* returns *true* for each element in the return *List*, a task could have completed
  normally or thrown an exception.

### invokeAny() method

- On the other hand, the *invokeAny()* method executes a colletion of tasks and returns the result of one of the tasks
  that successfully completes execution, cancelling all unfinished tasks.
- While the first task to finish is often returned, this behavior is not guaranted, as any completed task can be
  returned by this method.

i.e: chapter_7.concurrencyapi.future.InvokeAny.java

```
20: ExecutorService service = ...
21: System.out.println("begin");
22: Callable<String> task = () -> "result";
23: String data = service.invokeAny(List.of(task, task, task));
24: System.out.println(data);
25: System.out.println("end");
```

- As before, the JVM waits on line 23 for a completed task before moving on the next line.
- The other tasks that did not completed are cancelled.
- For the exam, remember that the *invokeAll()* method will wait indefinitely until all tasks are complete, while the
  *invokeAny()* method will wait indefinitely until at least one task completes.
- The ExecutorService interface also includes overloaded versions of *invokeAll()* and *invokeAny()* that take a
  *timeout* value and *TimeUnit* parameter.

## Scheduling Tasks

- Oftentimes in Java, we need to schedule a task to happen at some future time. We might even need to schedule the task
  to happen repeatedly, at some set interval.
- For example, imagine that we want to check the supply of food for zoo animals once an hour and fill it as needed.
- The *ScheduledExecutorService*, which is a subinterface of *ExecutorService*, can be used for just such a task.
- Like *ExecutorService*, we obtain an instance of *ScheduleExecutorService* using a factory method in the *Executors*
  class, as shown in the following snippet:

```java
ScheduledExecutorService service
        = Executors.newSingleThreadScheduleExecutor();
```

---

### TABLE 7.4 *ScheduledExecutorService* methods (Summary) ###

| Method Name                                                                            | Description                                                                                                                                                                        |
|----------------------------------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| schedule(Callable<V> callable, long delay, TimeUnit unit)                              | Creates and executes a *Callable* task after the given delay                                                                                                                       |
| schedule(Runnable command, long delay, TimeUnite unit)                                 | Creates and executes a Runnable task after the given delay                                                                                                                         |
| scheduleAtFixedRate(Runnable command, long initialDelay, long period, TimeUnit unit    | Creates and executes a *Runnable* task after the given initial delay, creating a new task every period value that passes                                                           |
| scheduleWithFixedDelay(Runnable command, long initialDelay, long delay, TimeUnit unit) | Creates and executes a *Runnable* a task after the given initial delay an dsubsequnelty with tiven delay between the termination of one execution and the commencement of the next |

---

- The first two *schedule()* methods in Table 7.4 take a *Callable* or *Runnable*, respectively; perform the task after
  some delay; and return a *ScheduledFuture* instance.
- The *ScheduledFuture* interface is identical to the *Future* interface, except that it includes a *getDelay()* method
  that returns the remaning delay.
- The following uses the *schedule()* method with *Callable* and *Runnable* tasks:

i.e: chapter_7.concurrencyapi.schedule.ScheduledExecutorServiceExample.java

```java
public class ScheduledExecutorServiceExample {

    public static void main(String[] args) {
        ScheduledExecutorService service = null;
        try {
            service = Executors.newSingleThreadScheduledExecutor();
            Runnable task1 = () -> System.out.println("Hello Zoo");
            Callable<String> task2 = () -> "Monkey";
            ScheduledFuture<?> r1 = service.schedule(task1, 10, TimeUnit.SECONDS);
            ScheduledFuture<?> r2 = service.schedule(task2, 8, TimeUnit.MINUTES);
            service.scheduleAtFixedRate(task1, 5, 1, TimeUnit.MINUTES); // Initial delay 5. Every 1 minute.
            service.scheduleWithFixedDelay(task1, 0, 2, TimeUnit.MINUTES); // Initial delay 0. Every 2 minutes
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
```

- The first task is scheduled 10 seconds in the future, whereas the second task is scheduled 8 minutes in the future.

> Note: While these tasks are scheduled in the future, the actual execution may be delayed.
> For example, there may be no threads available to perform the task, at which point they will just wait in the queue.
> Also, if the *ScheduledExecutorService* is shutdown by the time the scheduled task execution time is reached, then
> these tasks will be discarded.

- The *scheduleAtFixedRate()* method creates a new task and submits it to the executor every period, regardless of
  whether the previous task finished.
- The following example executes a *Runnable* task every minute, following an initial five-minute delay:

```
service.scheduleAtFixedRate(command, 5, 1,TimeUnit.MINUTES);
```

- The *scheduleAtFixedRate()* method is useful for tasks that need to be run at specific intervals, such as checking the
  health of the animals once a day. Even if it takes two hours to examine an animal on Monday, this doesn't mean that
  Tuesday's exam should start any later in the day.

> TIP: Bad things can happen with *scheduleAtFixedRate() if each task consistently takes longer to run than the
> execution
> interval. Imagine your boss came by your desk every minute and dropped off a piece of paper. Now imagine it took you
> five minutes to read each piece of paper. Before long, you would be drowning in piles of paper. This is how an
> executor feels, Given enough time, the program would submit more tasks to the executor service than could fit in
> memory, causing the program to crash.

- On the other hand, the *scheduleWithFixedDelay()* method creates a new task only after the previous task has finished.
- For example, if a task runs at 12:00 and takes five minutes to finish, with a period between executions of two
  minutes,
  then the next task will start at 12:07.

```
service.scheduleWithFixedDelay(command, 0, 2, TimeUnit.MINUTES);
```

- The *scheduleWithFixedDelay()* is useful for process that you want to happen repeatedly but whose specific time is
  unimportant.

> Tip: If you are familiar with creating Cron jobs in Linux to schedule tasks, then you should know that *
> scheduleAtFixedRate() is the closest built-in Java equivalent.

## Increasing Concurrency with Pools

- All of our examples up until now have been with sigle-thread executors.
- We now present three additional factory methods in the *Executors* class that act on a pool of threads, rather than on
  a single thread.
- A ***thread pool*** is a group of pre-instantiated reusable threads that are available to perform a set of arbitrary
  tasks.
- Table 7.5 includes our two previous single-thread executor methods, along with the new ones that you should know for
  exam.

---

### TABLE 7.5 *Executors* factory methods ###

| Method                                                      | Description                                                                                                                                                                        |
|-------------------------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| ExecutorService newSingleThreadExecutor()                   | Creates a single-threaded executor that uses a single worker thread operating off an unbounded queue. Results are processed sequentially in the order in which they are submitted. |
| ScheduledExecutorService newSinglethreadScheduledExecutor() | Creates a single-threaded executor that can schedule commands to run after a given delay or to execute periodically                                                                |
| ExecutorService newCachedThreadPool()                       | Creates a thread pool that creates new threads as needed but will reuse previously contructed threads when they are available                                                      |
| ExecutorService newFixedThreadPool(int)                     | Creates a thread pool that reuses a fixed number of threads operating off a shared unbounded queue                                                                                 |
| ScheduledExecutorService newScheduledThreadPool(int)        | Creates a thread pool that can schedule commands to run after a given delay or to execute periodically                                                                             |

---

- The difference between a single-thread and a pooled-thread executor is what happens when a task is already running.
- While a single-thread executor will wait for a thread to become available before running the next task, a
  pooled-thread
  executor can execute the next task concurrently.
- If the pool runs out of available threads, the task will be queued by the thread executor and wait to be completed.

<br>

- The *newFixedThreadPool()* takes a number of threads and allocates them all upon creation.
- As long as our number of tasks is less than our number of threads, all tasks will be executed concurrently.
- If at any point the number of tasks exceeds the number of threads in the pool, they will wait in a similiar manner as
  you saw with a single-thead executor.
- In fact, calling *newFixedThreadPool()* with a value of 1 is equivalent to calling *newSingleThreadExecutor()*.

<br>

- The *newCachedThreadPool()* method will create a thread pool of unbounded size, allocating a new thread anytime one is
  required or all existing threads are busy.
- This is commonly used for pools that require executing many short-lived asynchronous tasks.
- For long-lived process, usage of this executor is strongly discouraged, as it could grow to encompass a large number
  of threads over the application life cycle.

<br>

- The *newScheduledThreadPool()* is identical to the *newFixedThreadPool()* method, except that it returns an instance
  of *ScheduledExecutorService* and is therefore compatible with scheduling tasks.

---

### Real World Scenario - Choosing a Pool Size ###

- In practice, choosing an appropriate pool size requires some thought.
- In general, you want at least a handful more threads than you think you will ever possibly need.
- On the other hand, you don't want to choose so many threads that your application uses up too many resources or too
  much CPU processing power.
- Oftentimes, the number of CPUs avaialable is used to determine the thread pool size using this command.

```
Runtime.getRuntime().availableProcessors()
```

- It is a common practice to allocate threads based on the number of CPUs.

---

## Writing Thread-Safe Code

- *Thread-safety* is the property of an object that guarantees safe execution by multiple threads at the same time.
- Since threads run in a shared environemnt and memory space, how do we prevent two threads from interfering with each
  other?
- In this part of the chapter, we show how to use a variety of techiniques to protect data including: atomic classes,
  *synchronized blocks*, the *Lock framework*, and *cyclic barriers*.

## Understanding Thread-Safety

- Imagine that our zoo has a program to count sheep, preferably one that won't put the zoo workers to sleep!
- Each zoo worker runs out to a field, adds a new sheep to the flock, counts the total number of sheep, and runs back
  to us to report the results. We present the following code to represent this conceptually, choosing a thread pool size
  so that all tasks can be run concurrenlty:

i.e: chapter_7.concurrencyapi.thread_safety.SheepManager.java

```java
import java.util.concurrent.*;

public class SheepManager {
    private int sheepCount = 0;

    private void incrementAndReport() {
        System.out.print((++sheepCount) + " ");
    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(20);
            SheepManager manager = new SheepManager();
            for (int i = 0; i < 10; i++) {
                service.submit(() -> manager.incrementAndReport());
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
```

- What does this program output?
- You might think it will output numbers from 1 to 10, in order, but that is far from guaranteed.
- It may output in a different order.
- Worse yet, it may print some numbers twice and not print some numbers at all!
- The following are all possible outputs of this program:

```
1 2 3 4 5 6 7 8 9 10
1 9 8 7 3 6 6 2 4 5
1 8 7 3 2 6 5 4 2 9
```

- So, what went wrong?
- In this example, we use the pre-increment (++) operator to update the *sheepCount* variable.
- A problem occurs when two threads both execute the right side of the expression, reading the "old" value before either
  thread writes the "new" value of the variable.
- The two assignments become redundant; they both assign the same value, with one thread overwriting the result of the
  other.
- Figure 7.3 demonstrate this problem with two threads, assuming the *sheepCound* has a starting value of 1.

<br>

- You can see in Figure 7.3 that both threads read and write the same values, causing one of the two ++ is not
  thread-safe.
- As you will see later in this chapter, the unexpected result of two tasks executing at the same time is referred to
  as a *race condition*.

<br>

- Conceptually, the idea here is that some zoo workers may run faster on their way to the field but more slowly on their
  way back and report late.
- Other workers may get to the field last but somehow be the first ones back to report the results.

![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_7/images/Figure_7_3.png?raw=true)

## Protecting Data with Atomic Classes

- One way to improve our sheep counting example is to use the *java.util.concurrent.atomic* package.
- As with many of the classes in the Concurrency API, these classes exist to make your life easier.

- In our SheepManager sample output, we printed twice, with the highest counter being 9 instead of 10.
- As we demonstrated in the previous section, the increment operator ++ is not thread-safe.
- The reason that it is not thread-safe is that the operation is not atomic, carrying out two tasks, read and write,
  that can be interrupted by other threads.

---

- *Atomic* is the property of an operation to be carried out as a single unit of execution without any interference by
  another thread.
- A thread-safe atomic version of the increment operator would be one that performed the read and write of the variable
  as a single operation.
- Figure 7.4 shows result of making the *sheepCount* variable atomic.

---

- Figure 7.4 resembles our earlier Figure 7.3, except that reading and writing the data is atomic with regard to the
- *sheepCount* variable while an atomic operation is in process will have to wait until the atomic operation on the
  variable is complete.
- Since accessing primitives and references in Java is common in shared environments, the Concurrency API includes
  numerous useful classes that are conceptually the same as our primitive classes but that support atomic operations.

![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_7/images/Figure_7_4.png?raw=true)
Figure 7.4 Thread synchronization using atomic operations


---

### TABLE 7.6 Atomic classes ###

| Class Name    | Description                                      |
|---------------|--------------------------------------------------|
| AtomicBoolean | A *boolean* value that may be updated atomically |
| AtomicInteger | An *int* value that may be updated atomically    |
| AtomicLong    | A *long* value that may be updated atomically    |

---

- How do we use an atomic class?
- Each class includes numerous methods that are equivalent to many of the primitive built-in operators that we use on
  primitives such as the assignment operator (=) and the increment operators (++).
- In the following example, we update our SheepManager class with an *AtomicInteger*:

i.e: chapter_7.concurrencyapi.thread_safety.SheepManager_v2.java

```
private AtomicInteger sheepCount = new AtomicInteger(0);
private void incrementAndReport(){
    System.out.print(sheepCount.incrementAngGet()+ " ");
}
```

- When we run this modification, we get varying output, such as the following:

```
2 3 1 4 5 6 7 8 9 10
1 4 3 2 5 6 7 8 9 10
1 4 3 5 6 2 7 8 10 9
```

- Unlike our previous sample output, the number 1 through 10 will always be printed, although the order is still not
  guaranteed.
- Using the atomic classes ensures that the data is consistent between workers and that no values are lost due to
  concurrent modification.

---

### TABLE 7.7 Common atomic methods ###

| Method name       | Description                                                                |
|-------------------|----------------------------------------------------------------------------|
| get()             | Retrieves the current value                                                |
| set()             | Sets the given value, equivalent to the assignement *=* operator           |
| getAndSet()       | Atomically sets the new value and returns the old value                    |
| incrementAndGet() | For numeric classes, atomic pre-increment operation equivalent to ++value  |
| getAndIncrement() | For numeric classes, atomic post-increment operation equivalent to value++ |
| decrementAndGet() | For numeric classes, atomic pre-decrement operation equivalent --value     |
| getAndDecrement() | For numeric classes, atomic post-decrement operation equivalent to value-- |

---

## Improving Access with Synchronized Blocks

- While atomic classes are great at protecting single variables, they aren't particularly useful if you need to execute
  a series of commands or call a method.
- How do we improve the results so that each worker is able to increment and report the results in order?
- The most common technique is to use a monitor, also called a lock, to synchronize access.
- A *monitor* is a structure that supports  *mutual exclusion*, which is the property that at most one thread is
  executing
  a particular segment of code at a given time.
- In Java, any *Object* can be used as a monitor, along with the *synchronized* keyword, as shown in the following
  example:

```
SheepManager manager = new SheepManager();
synchronized(manager){
  // work to be completed by one thread at a time
}
```

- This example is reffered to as a *synchronized block*.
- Each thread that arrives will first check if any threads are in the block.
- In this manner, a thread "acquires the lock" for the monitor.

> Note: To synchronize access across multiple threads, each thread must have access to the same Object.
> For example, synchronizing on different objects would not actually order the results.

- Let's say that we replaced our *for()* loop with the following implementation:

```
for(int i = 0; i < 10; i++){
  synchronzied(manager){
    service.submit(() -> manager.incrementAndReport());
  }
}
```

- Does this solution fix the problem?
- We've synchronized the creation of the threads but not the execution of the threads.
- Diagnosing and resolving threading problems is often one of the most difficult tasks in any program language.
- We now present a correct version of the *SheepManager* class, which does order the workers.

i.e: chapter_7.concurrencyapi.thread_safety.SheepManager_v3.java

```java
public class SheepManager_v3 {
    private int sheepCount = 0;

    private void incrementAndReport() {
        synchronized (this) {
            System.out.print((++sheepCount) + " ");
        }
    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(20);
            SheepManager_v3 manager = new SheepManager_v3();
            for (int i = 0; i < 10; i++) {
                service.submit(() -> manager.incrementAndReport());
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
```

- When this code executes, it will consistently output the following:

```
1 2 3 4 5 6 7 8 9 10 
```

- Although all threads are still created and executed at the same time, they each wait at the *synchronized* block for
  worker to increment and report the result before entering.
- We could have synchronized on any object, so long as it was the same object.
- For example, the following code snippet would have also worked:

```
private final Object herd = new Object();
private void incrementAndReport(){
  synchronized(herd){
    System.out.println((++sheepCount)+" ");
  }
}
```

- Although we didn't need to make the *herd* variable final, doing so ensures that it is not reassigned after threads
  start using it.

> Note: We are not gaining any improvement by using an atomic variable if the only time that we access the variable is
> within a *synchronized* block.

## Synchronizing on Methods

- We can add the *synchronized* modifiers to any instance method to *synchronize* automatically on the object itself.
- For example, the following two method definitions are equivalent:

```java
// synchronized block
private void incrementAndReport() {
    synchronized (this) {
        System.out.println((++sheepCount) + " ");
    }
}
```

```java
// synchronized method modifier
private synchronized void incrementAndReport() {
    System.out.println((++sheepCount) + " ");
}
```

- We can also apply the *synchronized* modifier to *static* methods.
- What object is used as the monitor when we synchronize on a *static* method?
- The class object, of course!
- For example, the following two methods are equivalent for *static* synchronization inside our *SheepManager* class:

```java
// syncronized block
public static void printDaysWork() {
    synchronized (SheepManager.class) {
        System.out.println("Finished work");
    }
}
```

```java
// synchronized modifier
public static synchronized void printDaysWork() {
    System.out.println("Finished work");
}
```

- You can use *static* synchronization if you need to order thread access across all instances, rather than single
  instance.

---

### Avoid Synchronization Whenever Possible ###

- Correctly using the *synchronized* keyword can be quite challenging, especially if the data you are trying to protect
  is available to a dozen of methods.
- Even when the data is protected, though, the performance cost for using it can be high.
- In this chapter, we present many classes within the Concurrency API that are a lot easier to use and more performant
  than synchronization.
- Some you have seen already, like the atomic classes, and others we'll be covering shortly, including the *Lock*
  framework,
  *concurrent* collections, and *cyclic barriers*.
- You should be familiar with all the classes in the Concurrency API, you should study them carefully if you are writing
  a lot of multithreading applications.
- They contain a wealth of methods that manage complex process for you in a thread-safe and performant manner.

---

## Understanding the Lock Framework

- A *synchronized* block support only a limited set of functionality.
- For example, what if we want to check whether a lock is available and, if it is not, perform some other task?
- Futhermore, if the lock is never available and we synchornize on it, we might hang forever.
  <br>
- The Concurrency API includes the *Lock* interface that is conceptually similar to using the *synchronized* keywork,
  but
  with a lot more bells and whistels.
- Instead of synchronizing on any *Object*, though, we can "lock" only on an object that implements the *Lock*
  interface.

## Applying a ReentrantLock Interface

- Using the *Lock* interface is pretty easy.
- When you need to protect a piece of code from multithreaded precessing, create an instance of *Lock* that all threads
  have access to.
- Each thread then calls *lock()* before it enters the protected code and calls unlock() before it exists the protected
  code.
- For contrast, the following shows two implementations, one with a *synchronized* block and one with a *Lock* instance.
- As we'll see in the next section, the *Lock* solution has a number of features not available to the *synchronized*
  block.

```
// Implementation #1 with a synchronized block
Object object = new Object();
synchronized (object){
    // Protected code
}
```

```
Lock lock = new ReentrantLock();
try {
  lock.lock();
  //Protected code
} finally{
  lock.unlock();
}
```

> Tip: While certainly not required, it is a good practice to use a try/finally block with *Lock* instances.
> This ensures any acquired locks are properly released.

- These two implementations are conceptually equivalent.
- The ReentrantLock class is a simple monitor that implements the *Lock* interface and
  supports mutual exclusion.
- In other words, at most one thread is allowed to hold a lock at any given time.
- The *ReentrantLock* class ensures that once a thread has called *lock()* and obtained
  the lock, all other threads that call *lock()* will wait until the first thread calls
  *unlock().
- As far as which thread gets the lock next, that depends on the parameters used to create the *Lock* object.

> Note: The *ReentrantLock* class contains a constructor that can be used to send a *boolean* "fairness" parameter.
> If set to true, then the lock will usually be granted to each thread in the order it was requested.
> It is false by default when using the no-argument constructor. 
> In practice, you should enable fairness only when ordering is absolutely required, as it could lead to a siginificant
  slowdown.


- Besides always making sure to release a lock, you also need to make sure that you only release a lock that you actually
  have.
- If you attempt to release a lock that you do not have, you will get an exception at runtime.

```
Lock lock = new ReentrantLock();
lock.unlock(); // IllegalMonitorStateException
```

- The *Lock* interface includes four methods that you should know for the exam, as listed in Table 7.8.

---
### TABLE 7.8 Lock methods

| Method                          | Description                                                                                                                                        |
|---------------------------------|----------------------------------------------------------------------------------------------------------------------------------------------------|
| void lock()                     | Request a lock and blocks until lock is acquired                                                                                                   |
| void unlock()                   | Release a lock                                                                                                                                     | 
| boolean tryLock()               | Requests a lock and returns immediately. Returns a boolean indicating whether the lock was succefully acquired                                     |
| boolean tryLock(long, TimeUnit) | Requests a lock and block up to the specified time until lock is required. Returns a boolean indicating whether the lock was successfully acquired |
---


## Attempting to Acquire a Lock

- While the *ReentrantLock* class allows you to wait for a lock, it so far suffers from the same problem as a *synchronized* 
  block.
- A thread could end up waiting forever to obtain a lock.
- Luckily, Table 7.8 includes two additional methods that make the *Lock* interface a lot safer to use than a *synchronized* block.

- For convenience, we'll be using the following *printMessage()* method for the code in this section:

```java
public static void printMessage(Lock lock){
    try{
        lock.lock();
    } finally {
        lock.unlock();
    }
}
```

## tryLock()

- The *tryLock()* method will attempt to acquire a lock and immediately return a *boolean* result indicating whether the
  lock was obtained.
- Unlike the *lock() method, it does not wait if another already holds the lock.
- It returns immediately, regardless of weather or not a lock is available.
- The following is a sample implementation using the *tryLock() method:


i.e: chapter_7.concurrencyapi.thread_safety.SheepManagerTryLock.java
```java
Lock lock = new ReentrantLock();
new Thread(() -> printMessage(lock)).start();
if (lock.tryLock()) {
    try {
        System.out.println("Lock obtained, entering protected code");
    } finally {
        lock.unlock();
    }
} else {
    System.out.println("Enable to acquire lock, doing something else");
}
```


- When you run this code, it could reproduce either message, depending on the order of execution.
- A fun exercise is to insert some *Thread.sleep()* delays into this snippet to encourage a particular to be displayed.
- Like *lock()*, the *tryLock()* method should be used with a *try/finally* block.
- Fortunately, you need to release the lock only if it was successfully acquired.

> Tip: It is imperative that your program always checks the return value of the *tryLock() method. It tells your programm
> whether the lock needs to be released later.


## tryLock(long, TimeUnit)

- The *Lock* interface includes an overloaded version of *tryLock(long, TimeUnit) that acts like a hybrid of *lock()* and
  *tryLock()*. 
- Like the other two methods, if a lock is available, then it will immediately return with it.
- If a lock is unavailable, though, it will wait up to the specified time limit for the lock.

i.e: chapter_7.concurrencyapi.thread_safety.SheepManagerTryLockOverloaded.java
```java
Lock lock = new ReentrantLock();
new Thread(() -> printMessage(lock)).start();
if (lock.tryLock(10, TimeUnit.SECONDS)) {
try {
System.out.println("Lock obtained, entering protected code");
    } finally {
       lock.unlock();
    }
} else {
  System.out.println("Enable to acquire lock, doing something else");
}
```

- The code is the same as before, except this time one of the threads waits up to 10 seconds to acquire the lock.

## Duplicate Lock Requests

- The *ReetrantLock* class maintains a counter of the number of times a lock has been given to a thread.
- To release the lock for other threads to use, *unlock()* must be called the same number of times the lock was granted.
- The following code snippet contains an error.
- Can you spot it?

```java
Lock lock = new ReentrantLock();
if(lock.tryLock()){
    try{
        lock.lock();
        System.out.println("Lock obtained, entering protected code");
    } finally{
        lock.unlock();
    }
}
```

- The thread obtains the lock twice but releases it only once..
- You can verify this by spawining a new thread after this code runs that attemps to obtain a lock.
- The following prints *false*;

```java
new Thread(() -> System.out.print(lock.tryLock())).start();
```

- It is critical that you release a lock the same number of times it is acquired.
- For calls with *tryLock()*, you need to call *unlock()* only if the methods returned *true*.


## Reviewing the Lock Framework

- To review, the *ReentrantLock* class supports the same features as a *synchronized* block, while adding a number of 
  improvements.
  - Ability to request a lock without blocking
  - Ability to request a lock while blocking for a specified amount of time.
  - A lock can be created with a fairness property, in which the lock is granted to threads in the order it was requested.

- The Concurrency API includes other lock-based classes, although *ReentrantLock* is the only one you need to know for 
  the exam.

> Tip: While not on the exam, *ReentrantReadWriteLock* is a really useful class.
> It includes separate locks for reading and writing data and is useful on data structures where reads are far more common
> than writes.
> For example, if you have a thousand threads reading data but only one thread writing data, this class can help you 
> maximize concurrent access.


## Orchestrating Tasks with a CyclicBarrier

- We complete our discussion of thread-safety by discussing how to orchestrate complex tasks across many things.
- Our zoo workers are back, and this time they are cleaning pens.
- Imagine that there is a lion pen that needs to be emptied, cleaned, and then filled back up with the lions.
- To complete the task, we have assigned four zoo workers.
- Obviously, we don't want to start cleaning the cage while a lion is roaming in it, lest we end up losing a zoo worker!
- Furthermore, we don't want to let the lions back into the pen while it is still being cleaned.
<br>
- We could have all the work completed by a single worker, but this would be slow and ignore the fact that we have 
  three zoo workers standing by to help.
- A better solution would be to have all four zoo employees work concurrently, pausing between the end of one set of 
  tasks and the start of the next.
- Ot coordinate these tasks, we can use the *CyclicBarrier* class.
- For now, let's start with a code sample without a *CyclicBarrier*.

i.e: chapter_7.concurrencyapi.thread_safety.LionPenManager.java
```java
public class LionPenManager {
    private void removeLions() {
        System.out.println("Removing lions");
    }
    private void cleanPen() {
        System.out.println("Cleaning the pen");
    }
    private void addLions() {
        System.out.println("Adding Lions");
    }
    public void performTask() {
            removeLions();
            cleanPen();
            addLions();
    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            var manager = new LionPenManager();
            for (int i = 0; i < 4; i++) {
                service.submit(() -> manager.performTask());
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
```

- The following is sample output based on this implementation:

```
Removing lions
Cleaning the pen
Adding Lions
Removing lions
Removing lions
Cleaning the pen
Adding Lions
Removing lions
Cleaning the pen
Adding Lions
Cleaning the pen
Adding Lions
```


- Although within a single thread the results are ordered, among multiple workers the output is entirely random.
- We see that some lions are still being removed while the cage, and other lions are added before the cleaning process is 
  finished.
- In our conceptual example, this would be quite chaotic and would not lead to a very clean cage.
- We can improve these results by using the *CyclicBarrier* class.
- The *CyclicBarrier* takes in its constructors a limit value, indicating the number of threads to wait for.
- As each thread finishes, it calls *await()* method on the cyclic barrier.
- Once the specified number of threads have each called *await()*, the barrier is released, and all threads can continue.

- The following is a reimplementation of our *LionPenManager* class that uses *CyclicBarrier* objects to coordinate access.


i.e: chapter_7.concurrencyapi.thread_safety.LionPenManager_v2.java
```java
public class LionPenManager_v2 {
    private void removeLions() {
        System.out.println("Removing lions");
    }
    private void cleanPen() {
        System.out.println("Cleaning the pen");
    }
    private void addLions() {
        System.out.println("Adding Lions");
    }
    public void performTask(CyclicBarrier c1, CyclicBarrier c2) {

        try {
            removeLions();
            c1.await();
            cleanPen();
            c2.await();
            addLions();
        } catch (InterruptedException | BrokenBarrierException e) {
           // Handle checked exceptions here
        }

    }

    public static void main(String[] args) {
        ExecutorService service = null;
        try {
            service = Executors.newFixedThreadPool(4);
            var manager = new LionPenManager_v2();

            var c1 = new CyclicBarrier(4);
            var c2 = new CyclicBarrier(4, () -> System.out.println("*** Pen Cleaned!"));

            for (int i = 0; i < 4; i++) {
                service.submit(() -> manager.performTask(c1, c2));
            }
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
```

- In this example, we have updated *performTask()* to use *CyclicBarrier* objects.
- Like synchronizing on the same object, coordinating a task with a *CyclicBarrier* requires the object to be *static* or
  passed to the thread performing the task.
- We also add a try/ catch block in the *performingTask()* method, as the *await()* method throws multiple checked exceptions.

- The following is sample output based on this revised implementation of our *LionPenManager* class:

```
Removing lions
Removing lions
Removing lions
Removing lions
Cleaning the pen
Cleaning the pen
Cleaning the pen
Cleaning the pen
*** Pen Cleaned!
Adding Lions
Adding Lions
Adding Lions
Adding Lions
```

- As you can see, all the results are now organized.
- Removing the lions all happens in one step, as does cleaning the pen and adding the lions back in.
- In this example, we used two different constructors for our *CyclicBarrier* objects, the latter of which called 
  a *Runnable* method upon completion.

---
### Thread Pool Size and Cyclic Barrier Limit ###

- If you are using a thread pool, make sure that you set the number of available threads to be at least as large as your
  *CyclicBarrier* limit value.
- For example, what if we changed the code in the previous example to allocate only two threads, such as in the following
  snippet?

```java
ExecutorService service = Executors.newFixedThreadPool(2);
```

- In this case, the code will hang indefinitely.
- The barrier would never be reached as the only threads available in the pool are stuck waiting for the barrier to be 
  completed.
- This would result in a deadlock, which will be discussed shortly.
---

- The *CyclicBarrier* class allows us to perform complex, multithreaded tasks, while all threads stop and wait at logical
  barriers.
- This solution is superior to a single-threaded solution, as the individual tasks, such as removing the lions, can be 
  completed in parallel by all four zoo workers.

## Reusing CyclicBarrier

- After a *CyclicBarrier* is broken, all threads are released, and the number of threads waiting on the *CyclicBarrier*
  goes back to zero.
- At this point, the *CyclicBarrier* may be used again for a new set of waiting threads.
- For example, if our *CyclicBarrier* limit is 5 and we have 15 threads that call *await()*, then the *CyclicBarrier* will
  be activated a total of three times.


## Using Concurrent Collections

- Besides managing threads, the Concurrency API includes interfaces and classes that help you coordinate access to 
  collections shared by multiple tasks.
- By collections, we are of course referring to the Java Collections Framework that we introduced in Chapter 3, "Generics
  and Collections."
- In this section, we will demonstrate many of the concurrent classes available to you when using the Concurrency API.

## Understanding Memory Consistency Errors

- The purpose of the concurrent collection classes is to solve common memory consistency errors.
- A *memory consistency error* occurs when two threads have inconsistent views of what should be the same data.
- Conceptually, we want to write on one thread to be available to another thread if it accesses the concurrent collection
  after the write has occurred.
- When two threads try to modify the same non-concurrent collection, the JVM may throw a *ConcurrentModificationException* at runtime.
- In fact, it can happen with a single thread.
- Take a look at the following code snippet:

```java
var foodData = new HashMap<String, Integer>()>;
foodData.put("penguin", 1);
foodData.put("flamingo", 2);
for(String key: foodData.keySet())
    foodData.remove(key);
```

- This snippet will throw a *ConcurrentModificationException* during the second iteration of the loop, since the iterator
  on *keySet()* is not properly updated after the first element is removed.
- Changing the first line to use a *ConcurrentHashMap* will prevent the code from throwing an exception at runtime.

```java
var foodData = new ConcurrentHashMap<String, Integer>()>;
foodData.put("penguin", 1);
foodData.put("flamingo", 2);
for(String key: foodData.keySet())
    foodData.remove(key);
```

- Although we don't usually modify a loop variable, this example highlights the fact that the *ConcurrentHashMap()* is
  ordering read/write access such that all access to the class is consistent.
- In this code snippet, the iterator created by *keySet()* is updated as soon as an object is removed from the *Map*.
- The concurrent classes were created to help avoid common issues in which multiple threads are adding and removing
  objects from the same collections.
- At any given instance, all threads should have the same consistent view of the structure of the collection.


## Working with Concurrent Classes

- You should use a concurrent collection class anytime that you are going to have multiple threads to modify a collections
  object outside a *synchoronized* blocked or method, even if you don't expect a concurrency problem.
- On the other hand, immutable or read-only objects can be accessed by any number of threads without a concurrent collection.

> Tip: Immutable objects can be accessed by any number of threads and do not require synchronization.
> By definition, they do not change, so there is no chance of a memory consistency error.


- In the same way that we instantiate an *ArrayList* object but pass around a *List* reference, it is considered a good 
  practice to instantiate a concurrent collection but pass it around using a nonconcurrent interface whenever possible.
- In some cases, the callers may need to know that it is a concurrent collection so that a concurrent interface or class
  is appropriate, but for the majority of circumstances, that distinction is not necessary.

- Table 7.9 lists the common concurrent classes with which you should be familiar with the exam.


---
### TABLE 7.9 Concurrent collection classes

| Class name            | Java Collections Framework interfaces  | Elements ordered? | Sorted? | Blocking? |
|-----------------------|----------------------------------------|-------------------|---------|-----------|
| ConcurrentHashMap     | ConcurrentMap                          | No                | No      | No        |
| ConcurrentLinkedQueue | Queue                                  | Yes               | No      | No        |
| ConcurrentSkipListMap | ConcurrentMap; SortedMap; NavigableMap | Yes               | Yes     | No        |
| ConcurrentSkipListSet | SortedSet; NavigableSet                | Yes               | Yes     | No        |
| CopyOnWriteArrayList  | List                                   | Yes               | No      | No        |
| CopyOnWriteArraySet   | Set                                    | No                | No      | No        |
| LinkedBlockingQueue   | BlockingQueue                          | Yes               | No      | Yes       |
---

- Based on your knowledge of collections from Chapter 3, classes like *ConcurrentHashMap* and *ConcurrentLinkedQueue* 
  should be quite easy for you to learn.
- Take a look at the following code samples:

```java
Map<String,Integer> map = new ConcurrentHashMap<>();
map.put("zebra", 52);
map.put("elephant", 10);
System.out.println(map.get("elephant")); // 10
```

```java
Queue<Integer> queue = new ConcurrentLinkedQueue<>();
queue.offer(31);
System.out.println(queue.peek()); //31
System.out.println(queue.pool()); //31
```

- Like we often did in Chapter 3, we use an interface reference for the variable type of the newly created object
  and use it the same way as we would a non-concurrent object.
- The difference is that these objects are safe to pass to multiple threads.
- All of these classes implement multiple interfaces.
- For example, *ConcurrentHashMap* implements *Map* and *ConcurrentMap*
- When declaring methods that take a concurrent collection, it is up to you to determine the appropriate method parameter
  type. For example, a method signature may require a *ConcurrentMap* reference to ensure that an object passed to it is
  properly supported in a multithreaded environment.


## Understanding SkipList Collections

- The *SkipList* classes, *ConcurrentSkipListSet* and *ConcurrentSkipListMap*, are concurrent version of their sorted 
  counterparts, TreeSet and TreeMap, respectively. 
- They maintain their elements or keys in the natura ordering or their elements.
- In this manner, using them is the same as the cod that you worked with in Chapter 3.

i.e: chapter_7.concurrencyapi.collections.ConcurrentCollection.java
```java
private static void concurrentSkipListSet() {
  Set<String> gardenAnimals = new ConcurrentSkipListSet<>();
  gardenAnimals.add("rabbit");
  gardenAnimals.add("gopher");
  System.out.println(gardenAnimals.stream()
          .collect(Collectors.joining(","))); // gopher, rabbit
}
```

i.e: chapter_7.concurrencyapi.collections.ConcurrentCollection.java
```java
private static void concurrentSkipListMap() {

        Map<String, String> rainForestAnimalDiet = new ConcurrentSkipListMap<>();
        rainForestAnimalDiet.put("koala", "bamboo");
        rainForestAnimalDiet.entrySet()
                .stream()
                .forEach((e) -> System.out.println(e.getKey() + "-" + e.getValue())); //koala-bamboo
    }
```

- When you see SkipList or SkipSet on the exam, just think "sorted" concurrent collections, and the rest should follow 
  naturally.

## Understanding CopyOnWrite Collections

- Table 7.9 included two classes, *CopyOnWriteArrayList* and *CopyOnWriteArraySet*, that behave a little differently than 
  the other concurrent examples that you have seen.
- These classes copy all of their elements to a new underlying structure anytime an element is added, mofified, or removed
  from the collection.
- By a *modified* element, we mean that the reference in the collection is changed. Modifying the actual contents of 
  objects within the collection will not cause a new structure to be allocated.
- Although the data is copied to a new underlying structure, our reference to the *Collection* object does not change.
- This is particularly useful in multithreaded environments that need to iterate the collection.
- Any iterator established prior to a modification will not see the changes, but instead it will iterate over the orignal
  elements prior to the modification.
- Let's take a look at how this works with an example.
- Does the following program terminate?
- If so, how many times does the loop execute?

````java
private static void copyOnWriteArrayList() {
    List<Integer> favNumbers = new CopyOnWriteArrayList<>(List.of(4, 3, 42));
    for (var n : favNumbers) {
        System.out.println(n + " ");
        favNumbers.add(9);
    }

    System.out.println(); 
    System.out.println("Size: " + favNumbers.size()); 
}
````

- When executed as part of a program, this code snippet outputs the following:

```
4 3 42 
Size: 6
```

- Despite adding elements to the array while iterating over it, the *for* loop only iterate on the ones created when the 
  loop started.
- Alternatively, if we had used a regular *ArrayList* object, a *concurrentModificationException* would have been thrown
  at run time.

> Note: The *CopyOnWrite* classes are similar to the immutable object patterns that you saw in Chapter 1,
> "Java Fundamentals", as a new underlying structure is created every time the collection is modified.
> Unlike a true immutable object, though, the reference to the object stays the same even while the underlying data is 
> changed.

- The *CopyOnWriteArraySet* is used just like a *HashSet* and has similar properties as the *CopyOnWriteArrayList* class.

```java
  private static void copyOnWriteArraySet() {
      Set<Character> favLetters = new CopyOnWriteArraySet<>(List.of('a', 't'));
      for (char c : favLetters) {
          System.out.println(c + " ");
          favLetters.add('s');
      }
      System.out.println();
      System.out.println("Size: " + favLetters.size());
  }
```

- This code snippet prints:

```
a t 
Size: 3
```

- The *CopyOnWrite* classes can use a lot of memory, since a new collection structure needs to be allocated anytime the 
  collection is modified.
- They are commonly used in multithreading environment situations where reads are far more common than writes.

---
### Revisting Deleting While Looping ###

- In Chapter 3, we showed an example where deleting from an *ArrayList* while iterating over it triggered a 
  *ConcurrentModificationException*.
- Here we present a version that does work using *CopyOnWriteArrayList:

```java
List<String> birds = new CopyOnWriteArrayList<>();
birds.add("hawk");
birds.add("hawk");
birds.add("hawk");

for(String bird : birds)
    birds.remove(bird);
System.out.print(birds.size()); // 0
```

- As mentioned, though, *CopyOnWrite* classes can use a lot of memory.
- Another approach is to use the ArrayList class with an iterator, as shown here:

```java
var iterator = birds.iterator();
while(iterator.hasNext()){
    iterator.next();
    iterator.remove();
}
System.out.println(birds.size()); // 0
```
---

## Understanding Blocking Queues

- The *BlockingQueue* is just like a regular *Queue*, except that it includes methods that will wait a specific amount
  of time to complete an operation.
- Since *BlockingQueue* inherits all the methods from *Queue*, we skip the inherited methods you learned in Chapter 3 
  present the new methods in Table 7.10.

---
### TABLE 7.10 BlockingQueue waiting methods ###

| Method name                               | Description                                                                                                                                    |
|-------------------------------------------|------------------------------------------------------------------------------------------------------------------------------------------------|
| offer(E e, long timeout, TimeUnit unit)   | Adds an item to the queue, waiting the specified time and returning *false* if the time elapses before space is available                      |
| poll(long timeout, TimeUnit unit)         | Retrieves and removes an item from the queue, waiting the specified time and returning *null* if the time elapses before the item is available |
---

- The implementation class *LinkedBlockingQueue*, as the name implies, maintains a linked list between elements.
- The following sample is using a *LinkedBlockingQueue* to wait for the result of some of the operations.
- The methods in Table 7.10 can each throw a checked *InterruptedException*, as they can be interrupted before they finish
  waiting for a result; therefore, they must be propertly caught.

```java
private static void linkedBlockingQueue() {
        try {
            var blockingQueue = new LinkedBlockingQueue<Integer>();
            blockingQueue.offer(39);
            blockingQueue.offer(3, 4, TimeUnit.SECONDS);
            System.out.println(blockingQueue.poll());
            System.out.println(blockingQueue.poll(10, TimeUnit.MILLISECONDS));
        } catch (InterruptedException e) {
            // Handler interruption
        }

    }
```

- This code snippet prints the following:
```
39
3
```

- As shown in this example, since *LinkedBlockingQueue* implements both *Queue* and *BlockingQueue*, we can use methods 
  available to both, such as those that don't take any wait arguments.

## Obtaining Synchronized Collections

- Besides the concurrent collection classes that we have covered, the Concurrency API also includes methods for obtaining
  synchronized versions of existing non-concurrent collection objects.
- These synchronized methods are defined in the *Collections* class.
- They operate on the inputted collection and return a reference that is the same type as the underlying collection.
- We list these methods in Table 7.11.

---
### TABLE 7.11 Synchronized collections methods ###

| Methods                                       |
|-----------------------------------------------|
| synchronizedCollection(Collection<T> c)       |
| synchronizedList(List<T> list                 |
| synchronizedMap(Map<K,V> m)                   |
| synchronizedNavigableMap(NavigableMap<K,V> m) |
| synchronizedNavigableSet(NavigableSet<T> s    |
| synchronizedSortedMap(SortedMap<K,V> m)       |
| synchronizedSortedSet(SortedSet<T> s)         |
---

- When should you 7.9 use these methods?
- If you know at the time creation that your object requires synchronization, then you should use one of the concurrent 
  collection classes listed in Table 7.9.
- On the other hand, if you are given an existing collection that is not a concurrent class and need to access it among 
  multiple threads, you can wrap it using the methods in TABLE 7.11.
- Unlike the concurrent collection, the synchronized collections also throw an exception if they are modified within an 
  iterator by a single thread.
- For example, take a look at the following modification of our earlier example:


- i.e: chapter_7.concurrencyapi.collections.SyncCollectionMethods.java
```java
public static void main(String[] args) {
        var foodData = new HashMap<String,Object>();
        foodData.put("penguin", 1);
        foodData.put("flaming", 2);
        var synFoodData = Collections.synchronizedMap(foodData);
        for(String key: synFoodData.keySet()){
            synFoodData.remove(key);
        }
    }
```

- This loop throws a *ConcurrentModificationException*, whereas our example that used *ConcurrentHashMap*
  did not.
- Other than iterating over the collection, the object returned by methods in Table 7.11 are safe from memory consistency
  errors and can be used among multiple threads.


## identifying Threading Problems

- A threading problem can occur in multi-threaded applications when two or more threads interact in an unexpected and 
  undesirable way.
- For example, two threads may block each other from accessing a particular segment of code.
- Although the Concurrency API reduces the potential for threading issues, it does not eliminate it.
- In practice, finding and identifying threading issues within an application is often one of the most difficult tasks
  a developer can undertake.

## Understanding Liveliness

- As you have seen in this chapter, many thread operations can be performed independently, but some require coordination.
- For example, synchronizing on a method requires all threads that call the method to wait for other threads to finish
  before continuing.
- You also saw earlier in the chapter that threads in a *CyclicBarrier* will each wait for the barrier limit to be reached
  before continuing.


- Liveliness is the ability of an application to be able to execute in a timely manner.
- Liveliness problems, then, are those in which the application becomes unresponsive or in some kind of "stuck" state.
- For the exam, there are three types of lineliness issues with which you should be familiar: deadlock, starvation, and 
  livelock.


## Deadlock

- Deadlock occurs when two or more threads are blocked forever, each waiting on the other.

- The following application models this behavior:


i.e: chapter_7.concurrencyapi.collections.Fox.java
```java
class Food {
}

class Water {
}

public class Fox {

    public void eatAndDrink(Food food, Water water) {
        synchronized (food) {
            System.out.println("Got Food");
            moved();
            synchronized (water) {
                System.out.println("Got Water");
            }
        }
    }

    public void drinkAndEat(Food food, Water water) {
        synchronized (water) {
            System.out.println("Got Water!");
            moved();
            synchronized (food) {
                System.out.println("Got Food!");
            }
        }
    }

    private void moved() {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            // Handle Exception
        }
    }

    public static void main(String[] args) {
        // Create participants and resources
        Fox foxy = new Fox();
        Fox tails = new Fox();
        Food food = new Food();
        Water water = new Water();
        // Process Data
        ExecutorService service = null;
        try {
            service = Executors.newScheduledThreadPool(10);
            service.submit(() -> foxy.eatAndDrink(food, water));
            service.submit(() -> tails.drinkAndEat(food, water));
        } finally {
            if (service != null) service.shutdown();
        }
    }
}
```

- In this example, Foxy obtains the food and then moves to the other side of the environment to obtain the water.
- Unfortunately, Tails already drank the water and is waiting for the food to become available. The result is that our 
  program outputs the following, and it hangs indefinitely.

```
Got Food
Got Water!
```

- This example is considered a deadlock because both participants are permanently blocked,
  waiting on resources that will never become available.


## Preventing Deadlocks

- How do you fix a deadlock once it has occurred?
- The answer is that you can't in most situations.
- On the other hand, there are numerous strategies to help prevent deadlocks from ever happening in the first place.


## Starvation

- *Starvation* occurs when a single thread is perpetually denied access to a shared resource or lock.
- The thread is still active, but it is unable to complete its work as a result of other threads constantly taking the 
  resource that they are trying to access.
<br>
- In our example, imagine that we have a pack of very hungry, very competitive foxes in our environment.
- Every time Foxy stands up to get food, one of the other foxes sees her an rushes to eat before her.
- Foxy is free to roam around the enclosure, take a nap, and howl for a zookeeper but is never able to obtain access to 
  food.
- In this example, Foxy literally and figuratively experiences starvation.
- It's a good thing that this is just a theoretical example!

## Livelock

- Livelock occurs when two or more threads are conceptually blocked forever, although they are each still active and 
  trying to complete their task.
- Livelock is a special case of resource starvation in which two or more threads actively try to acquire a set of locks,
  are unable to do so, and restart part of the process.
- Livelock is often a result of two threads trying to resolve a deadlock
- In practice, livelock is often a difficult issue to detect.
- Threads in a livelock state appear active and able to respond to requests, even when they are in fact stuck in an 
  endless cycle.

## Managing Race Conditions

- A *race condition* is an undesirable result that occurs when two tasks, which should be completed sequentially, are
  completed at the same time.
- We encountered examples of race conditions ealier in the chapter when we introduced synchronization.
<br>
- While Figure 7.5 shows a classical thread-based example of a race condition, we now provide a more illustrative example.
- Image two zoo patrons, Olivia and Sophia, are signing up for an account on the zoo's new visitor website.
- Both of them want to use the same username, ZooFan, and they each send requests to create the account at the same time,
  as shown in Figure 7.5.
- What result does the web server return when both users attempt to create an account with the same username in Figure 7.5?

![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_7/images/Figure_7_5.png?raw=true)


- Possible Outcomes for This Race Condition
  - Both users are able to create accounts with the username ZooFan.
  - Both users are unable to create an account with username ZooFan, returning an error message to both users.
  - One user is able to create the account with the username ZooFan, while the other user receives an error message.

## Working with Parallel Stream

- One of the most powerful features of the Stream API is built-in concurrency support.
- Up until now, all of the streams with which you have worked have been serial streams.
- A serial stream is a stream in which the results are ordered, with only one entry being processed at a time.
- A *parallel stream* is a stream that is capable of processing results concurrently, using multiple threads.
- For example, you can use a *parallel stream* and the *map()* operation to operate concurrently on the elements in the 
  stream, vastly improving performance over processing a single element at a time.
- Using a parallel stream can change not only the performance of your application but also the expected results.
- As you shall see, some operations also require special handling to be able to be processed in a parallel manner.

> Tip: The number of threads available in a parallel stream is proportional to the number of available CPUs in your 
> environment.


## Creating Parallel Streams

- For the exam, you should be familiar with the two ways of creating a parallel stream.

## Calling *parallel()* on an Existing Stream

- The first way to create a parallel stream is from an existing stream.
- You just call *parallel()* on an existing stream to convert it to one that supports multithreaded processing, as 
  shown in the following code:

```java
Stream<Integer> s1 = List.of(1,2).stream();
Stream<Integer> s2 = s1.parallel();
```

- Be aware that *parallel()* is an intermediate operation that operates on the original stream.
- For example, applying a terminal operation to *s2* also makes *s1* unavailable for further use.

## Calling parallelStream() on a Collection Object

- The second way to create a parallel stream is from a Java *Collection* class.
- The *Collection* interface includes a method *parallelStream()* that can be called on any collection and returns a 
  parallel stream.
- The following creates the parallel stream directly from the *List* object:

```java
Stream<Integer> s3 = List.of(1,2).parallelStream();
```

> Note: The *Stream* interface includes a method *isParallel()* that can be used to test if the instance of a stream 
> supports parallel processing. Some operations on stream preserve the paralallel attribute, while others do not.
> For example, the *Stream.concat(Stream s1, Stream s2) is parallel if either s1 or s2 is parallel.
> On the other hand, *flatMap()* creates a new stream that is not parallel by default, regardless of whether the underlying
> elements were parallel.


## Performing a Parallel Decomposition

- A *parallel decomposition* is the process of taking a task, breaking it up into *smaller* pieces that can be performed
  concurrently, and then reassembling the results. The more concurrent a decomposition, the greater the performance
  improvement of using parallel stream.
- For starters, let's define a reusable function that "does work" just by waiting for five seconds.

```java
private static int doWork(int input){
    try{
        Thread.sleep(5000);
    } catch (InterruptedException e){
        return input;
    }
}
```

- We can pretend that in a real application this might be calling a database or reading a file.
- Now let's use this method with a serial stream.

```java
long start = System.currentTimeMillis();
List.of(1,2,3,4,5)
     .stream()
     .map(w -> doWork(w))
     .forEach(s -> System.out.println(s + " "));
```

- What do you think this code will output when executed as part of a *main()* method?
- Let's take a look.
```
1 2 3 4 5
Time: 25 seconds
```

- As you might expect, the results are ordered and predictable because we are using a serial stream.
- It also took around 25 seconds to process all five results, one at a time.
- What happens if we use a parallel stream, though?

```java
long start = System.currentTimeMillis();
List.of(1,2,3,4,5)
      .parallelStream()
      .map(w -> doWork(w))
      .forEach(s -> System.out.print(s + " "));

System.out.println();
var timeTaken = (System.currentTimeMillis()-start)/1000;
System.out.println("Time: " + timeTaken + " seconds");
```

- With a parallel stream, the *map()* and *forEach()* operations are applied concurrently.
- The following is sample output:

```
3 2 1 5 4
Time: 5 seconds
```

- As you can see, the results are no longer ordered or predictable.
- The *map()* and *forEach()* operations on a parallel stream are equivalent to submitting multiple *Runnable* lambda 
  expressions to a pooled thread executor and then waiting for the results.
- What about the time required?
- In this case, our system had enough CPUs for all of the tasks to be run concurrently.
- If you ran this same code on a computer with fewer processors, it might output 10 seconds, 15 seconds, or some other
  value.
- The key is that we've written our code to take advantage of parallel processing when available, so our job is done.

---
### Ordering forEach Results ###

- The Stream API includes an alternative version of the *forEach()* operation called *forEachOrdered()*, which forces a
  a parallel stream to process the results in order at the cost of performance.
- For example, take a look at the following code snipped:

```java
List.of(5,2,1,4,3)
     .parallelStream()
     .map(w -> doWork(w))
     .forEachOrdered(s -> System.out.println(s + " "));
```

- Like our starting example, this outputs the results in the order that they are defined in the stream:

```
5 2 1 4 3
Time: 5 seconds
```

- With this change, the *forEachOrdered()* operation forces our stream into a single-threaded process.
- While we've lost some of the performance gains of using a parallel stream, our *map()* operation is still able to take
  advantage of the parallel stream and perform a parallel decomposition in 5 seconds instead of 25 seconds.

---

## Processing Parallel Reductions

- Using parallel streams can impact how you write your application.
- Reduction operations on parallel streams are referred to as *parallel reductions*.
- The results for parallel reductions can be different from what you expect when working with serial streams.

## Performing Order-Based Tasks

- Since order is not guaranteed with parallel streams, methods such as *findAny()* on parallel streams may result in 
  unexpected behaviour.
- Let's take a look at the results of *findAny()* applied to a serial stream.

```java
System.out.print(List.of(1,2,3,4,5,6)
    .stream()
    .findAny().get());
```

- This code frequently outputs the first value in the serial stream, 1, although this is not guaranteed.
- The *findAny()* method is free to select any element on either serial or parallel streams.
- With a parallel stream, the JVM can create any number of threads to process the stream.
- When you call *findAny()* on a parallel stream, the JVM select the first thread to finish the task and retrieves its
  data.

```java
System.out.print(List.of(1,2,3,4,5,6)
  .parallelStream()
  .findAny().get());
```

- The result is that the output could be 4, 1, or really any value in the stream.
- You can see that with parallel stream, the results of *findAny()* are not as predictable.
- Any stream operation that is based on order, including *findFirst()*, *limit()*, or *skip()*, may actually perform
  more slowly in a parallel environment.
- This is a result of a parallel processing task being forced to coordinate all of its threads in a synchronized-like
  fashion.
- On the plus side, the result of ordered operations on a parallel stream will be consistent with a serial stream.
- For example, calling *skip(5).limit(2).findFirst()* will return the same result on ordered serial and parallel streams.


---
### Real World Scenario - Creating Unordered Stream ###

- All of the streams wich which you have been working are considered ordered by default.
- It is possible to create an unordered stream from an ordered stream, similar to how you create a parallel stream from
  a serial stream.

```java
List.of(1,2,3,4,5,6).stream().unordered();
```

- This method does not actually reorder the elements;
- It just tells the JVM that if an order-based stream operation is applied, the order can be ignored.
- For example, calling *skip(5)* on an unordered stream will skip any 5 elements, not the first 5 required on an ordered
  stream.
- For serial streams, using an unordered version has no effect, but on parallel streams, the result can greatly improve
  performance.

```java
List.of(1,2,3,4,5,6).stream().unordered().parallel();
```

- Even though unordered streams will not be on the exam, if you are developing application with parallel streams, you
  should know when to apply an unordered stream to improve performance.
---


## Combining Results with reduce()

- As you learned in Chapter 4, the stream operation *reduce()* combines a stream into a single object.
- Recall that the first parameter to the *reduce()* method is called the *identity*, the second parameter is called the 
  *accumulator*, and the third parameter is called the *combiner*.
- The following is the signature for the method:

```java
    <U> U reduce(U identity,
       BiFunction<U, ? super T, U> accumulator,
       BinaryOperator<U> combiner);
```

- We can concatenete a list of *char* values, using the *reduce()* method, as shown in the following example:

i.e: chapter_7.streams.reductions.CombiningResultsWithReduce.java
```java
 private static void parallelStreamReduceMethod(){
        String word = List.of('w','o','l','f')
                .parallelStream()
                .reduce("",(s1,c) -> s1 + c, (s2, s3) -> s2 + s3);
        System.out.println(word); // wolf
    }
```

> Note: The naming of the variables in this stream example is not accidental. 
> We used c for char, whereas s1, s2, and s3 are String values.


- On parallel Stream, the *reduce()* method works by applying the reduction to pairs of elements within the stream to
  create intermediate values and then combining those intermediate values to produce a final result.
- Put another way, in a serial stream, *wolf* is built one character at a time.
- In a parallel stream, the intermediate values *wo* and *lf* are created and then combined.








































