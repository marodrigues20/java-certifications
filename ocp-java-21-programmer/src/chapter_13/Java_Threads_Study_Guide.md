# Java Threads & Concurrency — Study Guide
> OCP Java 21 Exam Review + Interview Prep  
> Mário Rodrigues | June 2026

---

## SECTION 1 — OCP Exam Review

### Platform Threads vs Virtual Threads

| | Platform Thread | Virtual Thread |
|--|--|--|
| Backed by | OS Thread (1:1) | Carrier Thread (M:N) |
| Cost | Heavy — ~1MB stack | Lightweight — ~few KB |
| Creation | Expensive | Cheap (millions possible) |
| Blocking | Blocks OS thread | Unmounts from carrier thread |
| Use case | CPU-bound tasks | I/O-bound, high concurrency |
| API | `new Thread()` | `Thread.ofVirtual()` |

- **Platform thread** = one OS thread. Blocking a platform thread blocks the OS thread.
- **Virtual thread** = mounted on a carrier (platform) thread. When blocked, it unmounts and frees the carrier for another virtual thread.
- Virtual threads are **not faster** per task — they allow **more concurrency** with fewer OS threads.

---

### Creating Threads — Two Ways

```java
// 1. Extend Thread
public class MyThread extends Thread {
    public void run() { System.out.println("Running"); }
}
new MyThread().start();

// 2. Implement Runnable (preferred)
Runnable task = () -> System.out.println("Running");
new Thread(task).start();
```

**Key rule:** Always call `.start()`, never `.run()` directly. `.run()` executes on the current thread — no new thread is created.

---

### Thread States (know all 6)

```
NEW → RUNNABLE → (BLOCKED | WAITING | TIMED_WAITING) → TERMINATED
```

| State | Description |
|--|--|
| NEW | Created but `start()` not called |
| RUNNABLE | Running or ready to run |
| BLOCKED | Waiting to acquire a synchronized lock |
| WAITING | Waiting indefinitely (`wait()`, `join()`) |
| TIMED_WAITING | Waiting for specified time (`sleep(ms)`, `join(ms)`) |
| TERMINATED | Finished execution |

---

### Key Thread Methods

| Method | Throws | Notes |
|--|--|--|
| `Thread.sleep(ms)` | `InterruptedException` | Static. Pauses current thread. Does NOT release locks. |
| `thread.join()` | `InterruptedException` | Current thread waits for `thread` to finish |
| `thread.join(ms)` | `InterruptedException` | Waits at most `ms` milliseconds |
| `thread.interrupt()` | — | Sets interrupt flag. Sleeping thread throws `InterruptedException` |
| `Thread.currentThread()` | — | Static. Returns reference to current thread |
| `thread.isAlive()` | — | Returns true if thread started and not terminated |
| `thread.setDaemon(true)` | — | Must be called BEFORE `start()` |

**Daemon threads:** JVM exits when only daemon threads remain. Used for background tasks (GC, monitoring).

---

### Concurrency API — `ExecutorService`

```java
// Create thread pools
ExecutorService es = Executors.newSingleThreadExecutor();  // 1 thread
ExecutorService es = Executors.newFixedThreadPool(4);      // 4 threads
ExecutorService es = Executors.newCachedThreadPool();      // grows as needed
ExecutorService es = Executors.newScheduledThreadPool(2);  // scheduled tasks

// Submit tasks
es.execute(runnable);           // fire and forget, no result
Future<?> f = es.submit(runnable);   // returns Future
Future<T> f = es.submit(callable);  // returns Future<T>

// Shutdown (always do this)
es.shutdown();       // graceful — waits for running tasks
es.shutdownNow();    // forceful — attempts to stop all tasks

// Await termination
boolean done = es.awaitTermination(10, TimeUnit.SECONDS);
```

**`shutdown()` vs `shutdownNow()`:**
- `shutdown()` → stops accepting new tasks, finishes existing ones
- `shutdownNow()` → tries to interrupt running tasks, returns list of waiting tasks

---

### Callable vs Runnable

| | Runnable | Callable\<T\> |
|--|--|--|
| Method | `run()` | `call()` |
| Returns | void | T |
| Throws checked exception | ❌ | ✅ |

---

### Future\<T\>

```java
Future<Integer> future = executor.submit(() -> 42);

future.get();              // blocks until result ready — throws InterruptedException, ExecutionException
future.get(2, SECONDS);   // blocks with timeout — also throws TimeoutException
future.isDone();           // true if completed (normally, exception, or cancelled)
future.isCancelled();      // true if cancelled before completion
future.cancel(true);       // attempts to cancel; true = interrupt if running
```

**`future.get()` exceptions:**
- `InterruptedException` — current thread was interrupted while waiting
- `ExecutionException` — the callable threw an exception
- `TimeoutException` — timeout expired before completion

---

### Scheduling Tasks — `ScheduledExecutorService`

```java
ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);

ses.schedule(task, 5, SECONDS);                    // run once after 5s delay
ses.scheduleAtFixedRate(task, 0, 1, SECONDS);      // run every 1s (delay between starts)
ses.scheduleWithFixedDelay(task, 0, 1, SECONDS);   // run 1s after previous finishes
```

**`scheduleAtFixedRate` vs `scheduleWithFixedDelay`:**
- `AtFixedRate` — gap measured from **start** to start. If task takes longer than period, runs immediately after.
- `WithFixedDelay` — gap measured from **end** of one run to start of next. Always respects the delay.

---

### Thread Safety & Atomic Classes

**Race condition:** two threads read-modify-write the same variable without synchronisation → unpredictable result.

**Atomic classes** (package `java.util.concurrent.atomic`) — thread-safe without locks:

```java
AtomicInteger count = new AtomicInteger(0);
count.incrementAndGet();    // thread-safe ++
count.decrementAndGet();    // thread-safe --
count.addAndGet(5);         // thread-safe += 5
count.getAndSet(10);        // get current, then set to 10
count.compareAndSet(5, 10); // if current == 5, set to 10; returns boolean
```

**Common atomic classes:** `AtomicInteger`, `AtomicLong`, `AtomicBoolean`, `AtomicReference<T>`

---

### Synchronisation

#### `synchronized` keyword

```java
// Synchronized method — locks on 'this'
public synchronized void increment() { count++; }

// Synchronized block — more granular
public void increment() {
    synchronized(this) { count++; }
}

// Static synchronized — locks on the Class object
public static synchronized void staticMethod() { }
```

**Key rules:**
- Only one thread can hold a lock at a time
- `sleep()` does NOT release the lock
- `wait()` DOES release the lock (must be called inside `synchronized`)
- `notify()` / `notifyAll()` wake waiting threads (must be inside `synchronized`)

#### `ReentrantLock`

```java
Lock lock = new ReentrantLock();
lock.lock();
try {
    // critical section
} finally {
    lock.unlock(); // always unlock in finally
}

lock.tryLock();           // returns true if acquired, false if not — non-blocking
lock.tryLock(2, SECONDS); // try for up to 2 seconds
```

**`ReentrantLock` vs `synchronized`:**
- `tryLock()` — can attempt without blocking
- Can be fair (`new ReentrantLock(true)`) — threads acquire in request order
- Must manually unlock — use `finally` block

---

### Concurrent Collections

| Class | Description |
|--|--|
| `ConcurrentHashMap` | Thread-safe map, bucket-level locking |
| `CopyOnWriteArrayList` | Thread-safe list — copies array on every write |
| `CopyOnWriteArraySet` | Thread-safe set backed by `CopyOnWriteArrayList` |
| `LinkedBlockingQueue` | Thread-safe FIFO queue with optional capacity |
| `ArrayBlockingQueue` | Bounded blocking queue |
| `SynchronousQueue` | Each insert waits for a remove (and vice versa) |

**`CopyOnWriteArrayList`:** Iterators are never invalidated because they see a snapshot of the array at creation time. Good for read-heavy, write-rare scenarios.

**`synchronized` collections** (legacy — prefer concurrent classes):
```java
List<String> list = Collections.synchronizedList(new ArrayList<>());
```
These lock the entire collection — less concurrent than `CopyOnWriteArrayList` or `ConcurrentHashMap`.

---

### Parallel Streams

```java
// Create parallel stream
list.parallelStream()
    .filter(n -> n > 0)
    .mapToLong(Long::valueOf)
    .sum();

// Convert existing stream to parallel
list.stream().parallel().forEach(...);
```

**Key exam rules:**
- Results of parallel streams are **unordered** unless `forEachOrdered()` is used
- `findAny()` is preferred over `findFirst()` in parallel streams — no coordination needed
- `reduce()` on parallel stream — the identity value must be valid for any partial result
- Stateful operations (`sorted()`, `distinct()`) require coordination — reduce parallel benefit
- **Avoid stateful lambdas** in parallel streams — shared state causes race conditions

```java
// WRONG — race condition in parallel stream
List<Integer> results = new ArrayList<>();
list.parallelStream().forEach(results::add); // NOT thread-safe!

// CORRECT
List<Integer> results = list.parallelStream().collect(Collectors.toList());
```

---

### Creating Virtual Threads (Java 21)

```java
// Option 1 — Thread.ofVirtual()
Thread vt = Thread.ofVirtual().start(() -> System.out.println("Virtual!"));

// Option 2 — Factory
Thread.Builder.OfVirtual factory = Thread.ofVirtual();
Thread vt = factory.unstarted(runnable);
vt.start();

// Option 3 — ExecutorService with virtual threads
ExecutorService es = Executors.newVirtualThreadPerTaskExecutor();
es.submit(() -> System.out.println("Virtual!"));
```

---

### Exam Essentials — Quick Fire Facts

- `sleep()` → static, affects **current** thread, does **not** release locks, throws `InterruptedException`
- `join()` → instance method, current thread **waits** for that thread to finish
- `start()` → creates new thread; `run()` → executes on current thread (no new thread)
- Daemon thread → must set **before** `start()`; JVM exits when only daemons remain
- `synchronized` → releases lock on `wait()`, NOT on `sleep()`
- `AtomicInteger.incrementAndGet()` → thread-safe; `count++` → NOT thread-safe
- `ConcurrentHashMap` → bucket-level locking; `Collections.synchronizedMap` → full map lock
- `Future.get()` throws `ExecutionException` if the task threw an exception
- Parallel stream `reduce()` → identity must be valid for partial sums, not just the final result
- `scheduleAtFixedRate` → delay between **starts**; `scheduleWithFixedDelay` → delay between **end and next start**

---

## SECTION 2 — Interview Prep

### "What is a thread and why do we need them?"

> *"A thread is the smallest unit of execution within a process. In Java, every application starts with one main thread. We create additional threads to do work concurrently — for example, handling multiple HTTP requests simultaneously or processing data in parallel while keeping the UI responsive."*

**Key point for Tesco:**
> *"In high-traffic systems like Tesco's e-commerce platform, threading is fundamental. At peak — think Christmas — you need to handle thousands of simultaneous requests without blocking. That's where Java's concurrency model becomes critical."*

---

### "What is the difference between Platform Threads and Virtual Threads?"

> *"Platform threads map 1:1 to OS threads. They're expensive — each takes about 1MB of stack memory and there's a hard limit on how many the OS can support. Virtual threads, introduced in Java 21, are lightweight and managed by the JVM. When a virtual thread blocks on I/O, it unmounts from its carrier thread, freeing it to run another virtual thread. This means you can have millions of virtual threads with a fraction of the OS threads."*

**When to use which:**
- **Platform threads** → CPU-bound work (heavy computation, no I/O waiting)
- **Virtual threads** → I/O-bound work (database calls, HTTP calls, Kafka consumers) — the vast majority of backend work

**Tesco context:**
> *"At Tesco's scale, a backend service handling product catalogue lookups or order processing does a lot of I/O — database queries, cache reads, API calls. Virtual threads are ideal here because they remove the thread-per-request bottleneck without the complexity of reactive programming."*

---

### "What is a race condition and how do you prevent it?"

> *"A race condition happens when two threads read and modify shared state concurrently without proper synchronisation, producing unpredictable results. For example, two threads both read `count = 5`, both increment it, and both write `6` — but the correct result should be `7`."*

**Prevention strategies:**
1. **Atomic classes** — `AtomicInteger`, `AtomicLong` for simple counters/flags
2. **`synchronized`** — for blocks of code that must be atomic
3. **`ReentrantLock`** — more flexible locking with `tryLock()`
4. **Immutability** — if state never changes, no race condition possible
5. **Concurrent collections** — `ConcurrentHashMap` instead of `HashMap`

> *"My preference is to use the highest-level abstraction available — atomic classes for simple counters, concurrent collections for shared data structures, and `synchronized` only when I need to coordinate multiple operations together."*

---

### "What is deadlock and how do you avoid it?"

> *"Deadlock is when two or more threads are each waiting for a lock held by the other — they block each other forever. For example, Thread 1 holds Lock A and waits for Lock B; Thread 2 holds Lock B and waits for Lock A."*

**Prevention:**
- Always acquire locks in the **same order** across all threads
- Use `tryLock()` with a timeout — if you can't acquire, back off and retry
- Minimise the scope of locks — hold them for as short a time as possible
- Prefer higher-level concurrency utilities (`ExecutorService`, `ConcurrentHashMap`) over manual locking

---

### "When would you use `ExecutorService` instead of creating threads manually?"

> *"Always. Creating threads manually is expensive and uncontrolled — you can accidentally create thousands of threads and exhaust OS resources. `ExecutorService` gives you a managed thread pool with bounded concurrency, proper lifecycle management (`shutdown()`), and result handling via `Future`. In production code, I'd never call `new Thread()` directly."*

**Real example from your CV:**
> *"At Lloyds Bank, our Kafka consumers ran in a managed thread pool. We used `ExecutorService` to control the number of concurrent consumers per partition, ensuring we didn't overwhelm downstream services."*

---

### "What is the difference between `synchronized` and `ReentrantLock`?"

> *"`synchronized` is simpler — built into the language, automatically releases the lock when the block exits. `ReentrantLock` gives you more control: you can try to acquire without blocking (`tryLock()`), acquire with a timeout, make it fair so threads get the lock in order, or use `Condition` objects for fine-grained thread coordination. The trade-off is that you must remember to `unlock()` in a `finally` block."*

---

### "What are virtual threads and why do they matter for high-traffic systems?"

This is the **most important Java 21 topic for Tesco.** Prepare this answer well.

> *"Before virtual threads, scaling a Java service meant either using reactive/async programming — which is complex and hard to debug — or accepting that you're limited by the number of OS threads. Virtual threads change this fundamentally. They're cheap enough that you can have one per request, like the simple thread-per-request model, but they scale like async code. The JVM automatically handles unmounting a blocked virtual thread and remounting it when the I/O completes."*

**The killer sentence:**
> *"Virtual threads let you write simple, readable, blocking code — no callbacks, no reactive chains — and get async performance. For a high-traffic system like Tesco's, this means handling 10x more concurrent requests with the same hardware."*

---

### "How would you handle millions of events per day?" (Tesco scenario)

> *"I'd use a combination of approaches: First, Kafka for the event pipeline — partitioned topics allow horizontal consumer scaling. Each consumer pod runs on Kubernetes and processes events concurrently using a thread pool or, in Java 21, virtual threads for I/O-heavy processing. I'd ensure idempotent consumers to handle at-least-once delivery. For CPU-bound aggregations, I'd use parallel streams or structured concurrency. For monitoring, I'd track consumer lag, DLQ depth, and processing latency."*

**This directly connects to your Lloyds Bank NPA experience** — use it.

---

### Talking Points by Topic

| Topic | What to say |
|--|--|
| Thread safety | "I prefer immutability first, then atomic classes, then concurrent collections, then explicit locking — in that order of preference." |
| Virtual threads | "Game changer for I/O-bound services. Simple blocking code that scales like async." |
| Deadlock | "Consistent lock ordering and `tryLock()` with timeout are my go-to prevention strategies." |
| Parallel streams | "Useful for CPU-bound data processing on collections already in memory. For streaming data, Kafka Streams is the right tool." |
| ExecutorService | "Always use a managed pool. I size it based on whether the work is CPU-bound (threads ≈ CPUs) or I/O-bound (threads can be higher, or use virtual threads)." |
