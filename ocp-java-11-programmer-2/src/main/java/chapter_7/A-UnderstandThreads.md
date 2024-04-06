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

- Theses scheduling algorithms allowed users to experience the illusion that multiple tasks were being performed at the 
  same time within a single-CPU system. For example, a user could listen to music while writing a paper and receive 
  notification for new messages. 
- Since the number of threads requested often far outweighs the numbers of processors available even in multicore system,
  these thread-scheduling algorithms are still employed in operating system today.
---


