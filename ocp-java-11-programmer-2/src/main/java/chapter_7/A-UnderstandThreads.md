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

- Since its early days, Java has supported multithreading programming using Thread class. More recently, the Concurrency API
was introduced. It included numerous classes for performing complex thread-based tasks. The idea was simple: managing 
complex thread interactions is quite difficult for even the most skilled developers; therefore, a set of reusable 
features was created. The Concurrency API has grown over the years to include numerous classes and frameworks to assist
you in developing complex, multithreading applications.

- Threads and concurrency tend to be one of the more challenging topics for many programmers to grasp.

## Introducing Threads

- A thread is the smallest unit of execution that can be scheduled by the operating system. A process is a group of 
  associated threads that execute in the same, shared environment. It follows, then, that a single-threaded process is 
  one that contains exactly one thread, whereas a multithreading process is one that contains one or more threads.

- By shared process, we mean that the threads in the same process share the same memory space and can communicate directly
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
> A Java application terminates when the only threads that are running  are daemon threads.
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
when determining which threads should currently be executing. In Java, thread priorities are specified as integer values.

---
### Real World Scenario ###
### The importance of Thread Scheduling ###

- Even though multicore CPUs are quite common these days, single-core CPUs were the standard in personal computing for 
  many decades. During this time, operating systems developed complex thread-scheduling and context-switching algorithms
  that allowed users to execute dozens or even hundreds of threads on a single-core CPU system.
- These scheduling algorithms allowed users to experience the illusion that multiple tasks were being performed at the 
  same time within a single-CPU system. For example, a user could listen to music while writing a paper and received 
  notifications for new messages.
- Since the number of threads requested often far outweighs the numbers of processors available even in multicore system,
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
Runnable snake = () -> { int i=10; i++; };
Runnable beaver = () -> { return; };
Runnable coyote = () -> {};
```

- Notice that all of these lambda expressions start with a set of empty parentheses, ().
- Also, none of the lambda expression returns a value. 
- The following lambdas, while valid for other functional interfaces, are not compatible with *Runnable* because they 
  return a value.

```java
Runnable capybara = () -> "";                   // DOES NOT COMPILE
Runnable Hippopotamus = () -> 5;                // DOES NOT COMPILE
Runnable emu = () -> { return new Object(); };  // DOES NOT COMPILE
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
  public CalculateAverages (double[] scores){
      this.scores = scores;
  }
  public void run(){
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
public class PrintData implements Runnable{
    @Override 
    public void run(){
        for(int i=0; i<3; i++)
          System.out.println("Printing record: " +i);
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
    public void run(){
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

- Despite that the exam no long focuses on creating threads by extending the *Thread* class and implementing the Runnable
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
      for(int i = 0; i < 500; i++)
        CheckResults.counter++; }
    ).start();

    while(CheckResults.counter < 100){
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
    public static void main(String[] args){
        ExecutorService service = null;
        Runnable task1 = () -> 
                System.out.println("Printing zoo inventory");
        Runnable task2 = () -> 
        { for (int i = 0; i < 3; i++)
          System.out.println("Printing record: " +i);
        };
        try{
          service = Executors.newSingleThreadExecutor();
          System.out.println("begin");
          service.execute(task1);
          service.execute(task2);
          service.execute(task1);
          System.out.println("end");
        }finally {
            if(service != null) service.shutdown();
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

> Tip: As you learned in Chapter 5, resources such as thread executors should be properly closed to prevent memory leaks.
> Unfortunately, the ExecutorService interface does not extend the AutoCloseable interface, so you cannot use a 
  try-with-resources statement. You can still use a finally block. Not required but it is considered a good practice 
  to do so.

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

| Method name                                   | Description                                                                                                                                                                           |
|-----------------------------------------------|---------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| boolean isDone()                              | Returns *true* if the task was completed, threw an exception, or was cancelled                                                                                                        |
| boolean isCancelled()                         | Returns *true* if the task was cancelled before it completed normally                                                                                                                 |
| boolean cancel(boolean mayInterruptIfRunning) | Attempts to cancel execution of the task and returns *true* if it was successfully cancelled or *false* if it could not be cancelled or is complete                                   |
| V get()                                       | Retrieves the result of a task, waiting endlessly if it is not yet available                                                                                                          |
| V get(long timeout, TimeUnit unit)            | Retrieves the result of a task, waiting the specified amount of time. If the result is not ready by the time the timeout is reached, a checked *TimeoutException* will be thrown.     |
---

- The following is an updated verstion of our earlier polling example *CheckResults* class, which uses a *Future* instance
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

- The *java.util.concurrent.Callable* functional interface is similar to *Runnable* except exept that its *call()* method
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

> Note: If *awaitTermination()* is called before *shutdown()* within the same thread, then that thread will wait the full
> timeout value sent with *awaitTermination().

## Submitting Task Collections

- You should know for the exam the methods *invokeAll()* and *invokeAny()*.
- Both of these methods execute synchronously and take a Collection of tasks.
- The *invokeAll()* method executes all tasks ina provided collection and returns a *List* of ordered *Future* instances,
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
    }finally {
      if(service != null) service.shutdown();
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

> TIP: Bad things can happen with *scheduleAtFixedRate() if each task consistently takes longer to run than the execution
> interval. Imagine your boss came by your desk every minute and dropped off a piece of paper. Now imagine it took you
> five minutes to read each piece of paper. Before long, you would be drowning in piles of paper. This is how an 
> executor feels, Given enough time, the program would submit more tasks to the executor service than could fit in 
> memory, causing the program to crash.


- On the other hand, the *scheduleWithFixedDelay()* method creates a new task only after the previous task has finished.
- For example, if a task runs at 12:00 and takes five minutes to finish, with a period between executions of two minutes,
  then the next task will start at 12:07.

```
service.scheduleWithFixedDelay(command, 0, 2, TimeUnit.MINUTES);
```

- The *scheduleWithFixedDelay()* is useful for process that you want to happen repeatedly but whose specific time is 
  unimportant.

> Tip: If you are familiar with creating Cron jobs in Linux to schedule tasks, then you should know that *scheduleAtFixedRate() is the closest built-in Java equivalent.


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
- While a single-thread executor will wait for a thread to become available before running the next task, a pooled-thread
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
- Since threads run in a shared environemnt and memory space, how do we prevent two threads from interfering with each other?
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
            if(service != null) service.shutdown();
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



