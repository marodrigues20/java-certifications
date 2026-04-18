# Chapter 7 — Concurrency: Cheat Sheet (Exam Quick Read)

---

## Índice
- [Threads — Factos Rápidos](#1-threads--factos-rápidos)
- [Runnable vs Callable](#2-runnable-vs-callable)
- [ExecutorService — Métodos](#3-executorservice--métodos)
- [Future — Métodos](#4-future--métodos)
- [TimeUnit](#5-timeunit)
- [ScheduledExecutorService — Métodos](#6-scheduledexecutorservice--métodos)
- [Executors — Factory Methods](#7-executors--factory-methods)
- [Atomic Classes](#8-atomic-classes)
- [synchronized — Regras](#9-synchronized--regras)
- [Lock / ReentrantLock — Métodos](#10-lock--reentrantlock--métodos)
- [CyclicBarrier — Regras](#11-cyclicbarrier--regras)
- [Concurrent Collections](#12-concurrent-collections)
- [BlockingQueue — Métodos](#13-blockingqueue--métodos)
- [Collections.synchronized — Métodos](#14-collectionssynchronized--métodos)
- [Threading Problems](#15-threading-problems)
- [Parallel Streams — Regras](#16-parallel-streams--regras)
- [Parallel Reductions — Regras](#17-parallel-reductions--regras)
- [volatile — Regras](#18-volatile--regras)
- [🆕 Virtual Threads — Java 21](#19--virtual-threads--java-21)
- [Armadilhas do Exame](#20-armadilhas-do-exame)

---

## 1. Threads — Factos Rápidos

| Conceito | Facto |
|---|---|
| Thread | Menor unidade de execução agendada pelo OS |
| Process | Grupo de threads que partilham memória |
| System thread | Criada pela JVM (ex: GC). Erro → `Error`, não `Exception` |
| User thread | Criada pelo developer |
| Daemon thread | Não impede a JVM de terminar. `setDaemon(true)` antes de `start()` |
| Context switch | Guardar estado de uma thread para executar outra |
| `run()` vs `start()` | `run()` executa no thread actual (não cria nova thread!). Sempre `start()` |

---

## 2. Runnable vs Callable

| | `Runnable` | `Callable<V>` |
|---|---|---|
| Método | `void run()` | `V call() throws Exception` |
| Retorna valor? | ❌ Não | ✅ Sim |
| Lança checked exception? | ❌ Não | ✅ Sim |
| Usado com | `Thread`, `execute()`, `submit()` | `submit()` |
| `Future.get()` retorna | `null` | `V` |

---

## 3. ExecutorService — Métodos

| Método | Descrição |
|---|---|
| `execute(Runnable)` | Fire-and-forget. Retorna `void` |
| `submit(Runnable)` | Retorna `Future<?>` |
| `submit(Callable<T>)` | Retorna `Future<T>` |
| `invokeAll(Collection<Callable<T>>)` | Espera **todas** terminarem. Retorna `List<Future<T>>` |
| `invokeAny(Collection<Callable<T>>)` | Espera **uma** terminar. Cancela as restantes. Retorna `T` |
| `shutdown()` | Rejeita novas tarefas, termina as pendentes |
| `shutdownNow()` | Tenta parar tudo. Retorna `List<Runnable>` não iniciadas |
| `isShutdown()` | `true` após `shutdown()` |
| `isTerminated()` | `true` só quando **tudo** terminou |
| `awaitTermination(long, TimeUnit)` | Bloqueia até tudo terminar ou timeout |

> **Regra:** `submit()` é preferível a `execute()` — retorna `Future`.
> **Regra:** `ExecutorService` não é `AutoCloseable` no Java 11 → usar `finally`.

---

## 4. Future — Métodos

| Método | Descrição |
|---|---|
| `isDone()` | `true` se completa, cancelada ou lançou excepção |
| `isCancelled()` | `true` se cancelada antes de completar |
| `cancel(boolean)` | Tenta cancelar. `true` se sucesso |
| `get()` | Bloqueia indefinidamente até ter resultado |
| `get(long, TimeUnit)` | Bloqueia até timeout → lança `TimeoutException` |

---

## 5. TimeUnit

| Enum | Duração |
|---|---|
| `NANOSECONDS` | 1/1,000,000,000 s |
| `MICROSECONDS` | 1/1,000,000 s |
| `MILLISECONDS` | 1/1,000 s |
| `SECONDS` | 1 s |
| `MINUTES` | 60 s |
| `HOURS` | 3600 s |
| `DAYS` | 86400 s |

---

## 6. ScheduledExecutorService — Métodos

| Método | Descrição |
|---|---|
| `schedule(Callable, delay, unit)` | Executa uma vez após delay |
| `schedule(Runnable, delay, unit)` | Executa uma vez após delay |
| `scheduleAtFixedRate(Runnable, initialDelay, period, unit)` | Executa de `period` em `period` **independentemente** do tempo de execução |
| `scheduleWithFixedDelay(Runnable, initialDelay, delay, unit)` | Executa `delay` após a **anterior terminar** |

> **Diferença chave:** `AtFixedRate` pode acumular tarefas se cada execução demorar mais que o `period`. `WithFixedDelay` nunca acumula.

---

## 7. Executors — Factory Methods

| Método | Descrição |
|---|---|
| `newSingleThreadExecutor()` | 1 thread. Tarefas ordenadas sequencialmente |
| `newSingleThreadScheduledExecutor()` | 1 thread com scheduling |
| `newFixedThreadPool(int n)` | Pool fixo de `n` threads |
| `newCachedThreadPool()` | Pool ilimitado. Reutiliza threads livres. Cuidado com processos longos |
| `newScheduledThreadPool(int n)` | Pool fixo com scheduling |

> `newFixedThreadPool(1)` ≡ `newSingleThreadExecutor()`

---

## 8. Atomic Classes

| Classe | Tipo |
|---|---|
| `AtomicBoolean` | `boolean` |
| `AtomicInteger` | `int` |
| `AtomicLong` | `long` |

| Método | Equivalente |
|---|---|
| `get()` | leitura |
| `set(v)` | `= v` |
| `getAndSet(v)` | lê o antigo, escreve o novo |
| `incrementAndGet()` | `++value` |
| `getAndIncrement()` | `value++` |
| `decrementAndGet()` | `--value` |
| `getAndDecrement()` | `value--` |

> Atomic garante operações **read+write atómicas**. Não garante atomicidade de múltiplas operações encadeadas.

---

## 9. synchronized — Regras

```java
// Bloco — qualquer Object como monitor
synchronized (obj) { }

// Método de instância — monitor = this
public synchronized void method() { }

// Método estático — monitor = NomeClasse.class
public static synchronized void method() { }
```

> Todas as threads têm de sincronizar no **mesmo objecto** para haver exclusão mútua.
> `synchronized` dentro de um `synchronized` no mesmo objecto não causa deadlock (é reentrant).

---

## 10. Lock / ReentrantLock — Métodos

| Método | Descrição |
|---|---|
| `lock()` | Aguarda indefinidamente até obter o lock |
| `unlock()` | Liberta o lock. Lança `IllegalMonitorStateException` se não o tiver |
| `tryLock()` | Tenta obter sem bloquear. Retorna `boolean` imediatamente |
| `tryLock(long, TimeUnit)` | Tenta durante o tempo indicado. Retorna `boolean` |

```java
Lock lock = new ReentrantLock();
if (lock.tryLock()) {
    try { /* código */ }
    finally { lock.unlock(); } // só faz unlock se tryLock() retornou true
}
```

> `ReentrantLock` mantém **contador** de quantas vezes foi adquirido — deve ser libertado o mesmo número de vezes.
> Construtor `new ReentrantLock(true)` → fairness activado (ordem FIFO, mas mais lento).

---

## 11. CyclicBarrier — Regras

```java
CyclicBarrier barrier = new CyclicBarrier(4);                           // 4 threads esperam
CyclicBarrier barrier = new CyclicBarrier(4, () -> System.out.println("Fase concluída")); // com Runnable
```

| Regra | Detalhe |
|---|---|
| `await()` | Cada thread chama quando termina a sua fase |
| Barreira atingida | Quando `n` threads chamaram `await()` — todas são libertadas |
| Reutilizável | Após libertação, contador volta a zero automaticamente |
| `await()` lança | `InterruptedException` e `BrokenBarrierException` |
| Pool < limit | Deadlock garantido — threads ficam à espera para sempre |

> Pool de threads deve ter pelo menos tantas threads quanto o limite do `CyclicBarrier`.

---

## 12. Concurrent Collections

| Classe | Interface(s) | Ordenado? | Sorted? | Blocking? |
|---|---|---|---|---|
| `ConcurrentHashMap` | `ConcurrentMap` | ❌ | ❌ | ❌ |
| `ConcurrentLinkedQueue` | `Queue` | ✅ | ❌ | ❌ |
| `ConcurrentSkipListMap` | `ConcurrentMap`, `SortedMap`, `NavigableMap` | ✅ | ✅ | ❌ |
| `ConcurrentSkipListSet` | `SortedSet`, `NavigableSet` | ✅ | ✅ | ❌ |
| `CopyOnWriteArrayList` | `List` | ✅ | ❌ | ❌ |
| `CopyOnWriteArraySet` | `Set` | ❌ | ❌ | ❌ |
| `LinkedBlockingQueue` | `BlockingQueue` | ✅ | ❌ | ✅ |

> **SkipList** = versão concurrent de `TreeMap`/`TreeSet` (sorted).
> **CopyOnWrite** = cria nova estrutura a cada modificação. Iterator não vê modificações posteriores. Pesado em memória.
> **ConcurrentHashMap** não lança `ConcurrentModificationException` ao modificar durante iteração.

---

## 13. BlockingQueue — Métodos

| Método | Descrição |
|---|---|
| `offer(E, long, TimeUnit)` | Adiciona. Espera até timeout. Retorna `false` se timeout |
| `poll(long, TimeUnit)` | Remove e retorna. Espera até timeout. Retorna `null` se timeout |

> Ambos lançam `InterruptedException`.

---

## 14. Collections.synchronized — Métodos

```java
Collections.synchronizedList(list)
Collections.synchronizedMap(map)
Collections.synchronizedSet(set)
Collections.synchronizedSortedMap(map)
Collections.synchronizedSortedSet(set)
Collections.synchronizedNavigableMap(map)
Collections.synchronizedNavigableSet(set)
```

> **Diferença crítica:** `Collections.synchronized*` **lança** `ConcurrentModificationException` ao modificar durante iteração (mesmo numa só thread). `Concurrent*` classes não lançam.

---

## 15. Threading Problems

| Problema | Definição | Exemplo |
|---|---|---|
| **Deadlock** | 2+ threads bloqueadas para sempre, cada uma à espera da outra | Fox A tem comida, quer água. Fox B tem água, quer comida |
| **Starvation** | Thread activa mas nunca obtém acesso ao recurso | Thread com baixa prioridade nunca é escalonada |
| **Livelock** | 2+ threads activas mas presas num ciclo sem progresso | Cada thread cede ao outro, nenhuma avança |
| **Race Condition** | Resultado incorreto por execução simultânea não ordenada | `count++` por 2 threads em simultâneo |

---

## 16. Parallel Streams — Regras

```java
// Criar parallel stream
stream.parallel()                        // a partir de stream existente
collection.parallelStream()              // directamente da collection
```

| Regra | Detalhe |
|---|---|
| Ordem não garantida | `forEach()` em parallel stream → ordem aleatória |
| `forEachOrdered()` | Força ordem mas reduz performance |
| `findAny()` | Resultado imprevisível em parallel |
| `findFirst()`, `limit()`, `skip()` | Funcionam mas são mais lentos em parallel |
| `isParallel()` | Verifica se stream é parallel |
| `flatMap()` | Cria stream **não-parallel** por defeito, independentemente da origem |
| Número de threads | Proporcional ao número de CPUs disponíveis |

---

## 17. Parallel Reductions — Regras

### reduce()

```java
// 3 argumentos — recomendado para parallel
stream.reduce(identity, accumulator, combiner)
```

| Regra | Detalhe |
|---|---|
| Accumulator/combiner devem ser **associativos** | Ordem de execução não pode afectar resultado |
| Identity errada em parallel | `reduce("X", String::concat)` → `XwXoXlXf` (não `Xwolf`) |
| Subtracção é problemática | `reduce(0, (a,b) -> a - b)` dá resultados diferentes em serial vs parallel |

### collect() para parallel reduction

Requisitos para parallel reduction com `collect()`:
1. Stream é parallel
2. Collector tem `Characteristics.CONCURRENT`
3. Stream é unordered **ou** Collector tem `Characteristics.UNORDERED`

```java
// Collectors que suportam parallel reduction:
Collectors.toConcurrentMap(...)
Collectors.groupingByConcurrent(...)

// NÃO suporta parallel reduction (sem CONCURRENT):
Collectors.toSet()     // tem UNORDERED mas não CONCURRENT
Collectors.toList()    // nenhum dos dois
```

---

## 18. volatile — Regras

| | `volatile` | `synchronized` | Atomic |
|---|---|---|---|
| Visibilidade entre threads | ✅ | ✅ | ✅ |
| Atomicidade composta (`count++`) | ❌ | ✅ | ✅ |
| Uso típico | Flag `boolean` simples | Bloco de operações | Contador numérico |

```java
private volatile boolean running = true; // Thread B vê sempre o valor actual
```

> `volatile` previne caching de valores em CPU. Não substitui `synchronized` para operações compostas.

---

## 19. 🆕 Virtual Threads — Java 21

### Criação

```java
Thread.ofVirtual().start(runnable)                       // básico
Thread.ofVirtual().name("worker").start(runnable)        // com nome
Thread.startVirtualThread(runnable)                      // convenience
Thread.ofVirtual().name("task-", 0).start(runnable)     // task-0, task-1...
Executors.newVirtualThreadPerTaskExecutor()              // 1 VT por tarefa (recomendado)
```

### Platform vs Virtual

| | Platform | Virtual |
|---|---|---|
| Gerida por | OS | JVM |
| Stack | ~1MB fixo | Dinâmica, pequena |
| Escalabilidade | Milhares | Milhões |
| `isVirtual()` | `false` | `true` |
| Daemon por defeito | ❌ | ✅ **sempre** |
| `setDaemon(false)` | OK | ❌ `IllegalArgumentException` |
| `setPriority()` | OK | Ignorado (sempre `NORM_PRIORITY`) |
| Blocking I/O | Bloqueia OS thread | JVM liberta a carrier |
| `Thread.sleep()` | Bloqueia OS thread | JVM liberta a carrier |

### Pinning

| Situação | Pinning? | Carrier bloqueada? |
|---|---|---|
| `synchronized` block + blocking op | ✅ Sim | ✅ Sim — degradação |
| `ReentrantLock` + blocking op | ❌ Não | ❌ Não — carrier livre |
| `Thread.sleep()` sem lock | ❌ Não | ❌ Não |

> Prefere `ReentrantLock` em vez de `synchronized` com virtual threads.

### Quando usar

| Cenário | Virtual Threads? |
|---|---|
| I/O-bound (HTTP, DB, ficheiros) | ✅ Ideal |
| CPU-bound (cálculos) | ❌ Não ajuda — prefere parallel streams |
| Muito `synchronized` | ⚠️ Funciona mas perde benefício |

---

## 20. Armadilhas do Exame

| Armadilha | Detalhe |
|---|---|
| `thread.run()` em vez de `thread.start()` | Executa no thread actual, não cria nova thread |
| `shutdown()` sem `finally` | Executor nunca termina — aplicação não fecha |
| `unlock()` sem ter o lock | `IllegalMonitorStateException` |
| `tryLock()` sem verificar retorno | Pode chamar `unlock()` sem ter o lock |
| `ReentrantLock` adquirido 2x, unlock 1x | Lock nunca é totalmente libertado |
| Pool threads < CyclicBarrier limit | Deadlock garantido |
| `ConcurrentHashMap` vs `Collections.synchronizedMap` | O segundo lança `ConcurrentModificationException` ao iterar+modificar |
| `CopyOnWrite` iterator | Não vê elementos adicionados **depois** do iterator ser criado |
| `reduce()` identity errada em parallel | `"X"` aplicado a cada elemento → `XwXoXlXf` |
| `Collectors.toSet()` não faz parallel reduction | Falta `Characteristics.CONCURRENT` |
| `flatMap()` em parallel stream | Cria sub-stream **não-parallel** por defeito |
| Virtual thread `setDaemon(false)` | `IllegalArgumentException` — não é ignorado! |
| Virtual threads são daemon | JVM pode terminar antes da virtual thread concluir |
| `invokeAll()` vs `invokeAny()` | `invokeAll` espera todas; `invokeAny` espera uma e cancela o resto |
| `awaitTermination()` antes de `shutdown()` | Espera o timeout completo sem utilidade |
