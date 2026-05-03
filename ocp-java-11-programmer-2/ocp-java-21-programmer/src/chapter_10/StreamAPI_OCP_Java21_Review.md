# Stream API — OCP Java 21 Review

---

## 1. Functional Interfaces (java.util.function)

| Interface | Return | Method | Parameters |
|---|---|---|---|
| `Supplier<T>` | T | `get()` | 0 |
| `Consumer<T>` | void | `accept(T)` | 1 |
| `BiConsumer<T,U>` | void | `accept(T,U)` | 2 |
| `Predicate<T>` | boolean | `test(T)` | 1 |
| `BiPredicate<T,U>` | boolean | `test(T,U)` | 2 |
| `Function<T,R>` | R | `apply(T)` | 1 |
| `BiFunction<T,U,R>` | R | `apply(T,U)` | 2 |
| `UnaryOperator<T>` | T | `apply(T)` | 1 |
| `BinaryOperator<T>` | T | `apply(T,T)` | 2 |

### Convenience Methods
```java
// Predicate
egg.and(brown)          // &&
egg.or(brown)           // ||
egg.negate()            // !

// Function
after.compose(before)   // before runs first, then after
after.andThen(before)   // after runs first, then before

// Consumer
c1.andThen(c2)          // c1 runs first, then c2
```

---

## 2. Optional

```java
Optional<T> opt = Optional.of(value);         // throws NullPointerException if null
Optional<T> opt = Optional.ofNullable(value); // safe — returns empty if null
Optional<T> opt = Optional.empty();
```

| Method | Empty Optional | Present Optional |
|---|---|---|
| `get()` | throws NoSuchElementException | returns value |
| `isPresent()` | false | true |
| `ifPresent(Consumer)` | does nothing | calls Consumer |
| `orElse(T)` | returns T | returns value |
| `orElseGet(Supplier)` | calls Supplier (lazy) | returns value |
| `orElseThrow()` | throws NoSuchElementException | returns value |
| `orElseThrow(Supplier)` | throws custom exception | returns value |

### ⚠️ orElse vs orElseGet
```java
opt.orElse(expensiveCall())          // EAGER — always evaluated
opt.orElseGet(() -> expensiveCall()) // LAZY — only if empty
```

### Java 9+ Optional Methods
```java
opt.ifPresentOrElse(v -> use(v), () -> fallback()); // if/else in one line
opt.or(() -> Optional.of("default"))                // fallback to another Optional
opt.stream()                                        // converts to Stream (0 or 1 element)
```

---

## 3. Stream Pipeline

```
Source → Intermediate Operations → Terminal Operation
```

| | Intermediate | Terminal |
|---|---|---|
| Required? | No | Yes |
| Multiple per pipeline? | Yes | No |
| Returns Stream? | Yes | No |
| Triggers pipeline? | No | Yes |
| Stream valid after? | Yes | No |

---

## 4. Stream Sources

| Method | Finite/Infinite | Notes |
|---|---|---|
| `Stream.empty()` | Finite | zero elements |
| `Stream.of(varargs)` | Finite | fixed elements |
| `collection.stream()` | Finite | from Collection |
| `collection.parallelStream()` | Finite | concurrent processing |
| `Stream.generate(Supplier)` | Infinite | no control |
| `Stream.iterate(seed, UnaryOperator)` | Infinite | with seed |
| `Stream.iterate(seed, Predicate, UnaryOperator)` | Finite | Java 9+ — like a for loop |

---

## 5. Terminal Operations

| Method | Returns | Reduction | Terminates Infinite? |
|---|---|---|---|
| `count()` | `long` | Yes | No |
| `min(Comparator)` | `Optional<T>` | Yes | No |
| `max(Comparator)` | `Optional<T>` | Yes | No |
| `findFirst()` | `Optional<T>` | No | Yes |
| `findAny()` | `Optional<T>` | No | Yes |
| `anyMatch(Predicate)` | `boolean` | No | Sometimes |
| `allMatch(Predicate)` | `boolean` | No | Sometimes |
| `noneMatch(Predicate)` | `boolean` | No | Sometimes |
| `forEach(Consumer)` | `void` | No | No |
| `reduce(BinaryOperator)` | `Optional<T>` | Yes | No |
| `reduce(identity, BinaryOperator)` | `T` | Yes | No |
| `collect(Collector)` | `R` | Yes | No |
| `toList()` ☕16+ | `List<T>` immutable | Yes | No |

### ⚠️ reduce — with vs without identity
```java
// WITH identity — always returns T (identity if empty)
T result = stream.reduce(identity, (a, b) -> a + b);

// WITHOUT identity — stream may be empty → Optional
Optional<T> result = stream.reduce((a, b) -> a + b);
```

### findFirst vs findAny
```java
findFirst() // always returns first element — sequential streams
findAny()   // returns any element — use with parallel streams for performance
```

---

## 6. Intermediate Operations

```java
filter(Predicate)          // keeps elements that match
map(Function)              // transforms each element — one to one
flatMap(Function)          // flattens nested structures — one to many
sorted()                   // natural order
sorted(Comparator)         // custom order
peek(Consumer)             // observe without changing — DEBUG only
limit(long)                // max elements
skip(long)                 // skip first n elements
```

### Java 9+ Intermediate Operations
```java
takeWhile(Predicate)  // keeps elements WHILE true — stops at first false
dropWhile(Predicate)  // drops elements WHILE true — keeps the rest
```

### Java 16+
```java
mapMulti(BiConsumer)  // alternative to flatMap — push-based
```

### ⚠️ peek warning
```java
// peek is for observation only — never modify state inside peek
stream.peek(x -> x.remove(0)) // BAD — modifies data structure
```

---

## 7. Primitive Streams

| | `IntStream` | `LongStream` | `DoubleStream` |
|---|---|---|---|
| Optional | `OptionalInt` | `OptionalLong` | `OptionalDouble` |
| `getAsX()` | `getAsInt()` | `getAsLong()` | `getAsDouble()` |
| SummaryStatistics | `IntSummaryStatistics` | `LongSummaryStatistics` | `DoubleSummaryStatistics` |
| `sum()` returns | `int` | `long` | `double` |
| `range()` / `rangeClosed()` | ✓ | ✓ | ✗ |

### Creating Primitive Streams
```java
IntStream.of(1, 2, 3)
IntStream.range(0, 10)       // [0, 9] exclusive
IntStream.rangeClosed(0, 10) // [0, 10] inclusive
IntStream.generate(() -> (int)(Math.random() * 10))
IntStream.iterate(0, i -> i + 1)
```

### Converting Between Stream Types
```java
// Stream<T> → IntStream
stream.mapToInt(String::length)

// IntStream → Stream<Integer>
intStream.boxed()
intStream.mapToObj(i -> i)

// IntStream → LongStream
intStream.mapToLong(i -> i)

// IntStream → DoubleStream
intStream.mapToDouble(i -> i)
```

### SummaryStatistics
```java
IntSummaryStatistics stats = IntStream.of(1, 2, 3, 4, 5).summaryStatistics();
stats.getMin();
stats.getMax();
stats.getSum();
stats.getCount();
stats.getAverage();
```

---

## 8. Collectors

> `collect()` is the terminal operator.
> `Collectors` are just recipes passed to `collect()`.

### Collecting into Data Structures
```java
Collectors.toList()                                          // mutable List
Collectors.toSet()                                           // no duplicates, no order
Collectors.toCollection(TreeSet::new)                        // specific Collection type
Collectors.toMap(keyFn, valueFn)                             // throws IllegalStateException on duplicate key
Collectors.toMap(keyFn, valueFn, mergeFn)                    // handles duplicate keys
Collectors.toMap(keyFn, valueFn, mergeFn, TreeMap::new)      // specific Map type
```

### Aggregating
```java
Collectors.joining()                   // concatenate
Collectors.joining(", ")               // with delimiter
Collectors.joining(", ", "[", "]")    // with delimiter, prefix, suffix
Collectors.counting()                  // count elements → Long
Collectors.averagingInt(fn)            // average → Double
Collectors.summarizingInt(fn)          // all stats → IntSummaryStatistics
```

### Grouping
```java
// groupingBy — creates key per value, only existing keys appear
Collectors.groupingBy(fn)                                    // Map<K, List<T>>
Collectors.groupingBy(fn, Collectors.toSet())                // Map<K, Set<T>>
Collectors.groupingBy(fn, TreeMap::new, Collectors.toSet())  // specific Map type

// partitioningBy — always two keys: true and false
Collectors.partitioningBy(predicate)                         // Map<Boolean, List<T>>
Collectors.partitioningBy(predicate, Collectors.toSet())     // Map<Boolean, Set<T>>

// mapping — transform before collecting
Collectors.groupingBy(fn,
    Collectors.mapping(
        s -> s.charAt(0),    // transform
        Collectors.toList()  // then collect
    ))
```

### Java 12+
```java
// teeing — applies TWO collectors and combines results
stream.collect(Collectors.teeing(
    Collectors.summingInt(Integer::intValue), // collector 1
    Collectors.counting(),                     // collector 2
    (sum, count) -> "Sum: " + sum + ", Count: " + count
));
```

---

## 9. Advanced Concepts

### Lazy Evaluation
```java
var list = new ArrayList<String>();
list.add("Annie");
list.add("Ripley");
var stream = list.stream(); // stream not created yet
list.add("KC");
stream.count(); // 3 — sees all three elements
```

### Chaining Optional
```java
optional.map(n -> "" + n)
        .filter(s -> s.length() == 3)
        .ifPresent(System.out::println);
```

### flatMap on Optional
```java
// use flatMap when helper returns Optional — avoids Optional<Optional<T>>
optional.flatMap(ChainingOptionals::calculator);
```

### Checked Exceptions in Lambdas
```java
// functional interfaces don't declare checked exceptions
// wrap with try/catch or create a safe wrapper method
Supplier<List<String>> s = () -> {
    try {
        return create();
    } catch (IOException e) {
        throw new RuntimeException(e);
    }
};
```

---

## 10. Key Exam Traps

| Trap | Rule |
|---|---|
| `orElse` vs `orElseGet` | `orElse` is eager — always evaluated |
| `findFirst` vs `findAny` | `findAny` only useful in parallel streams |
| `reduce` with vs without identity | with = `T`, without = `Optional<T>` |
| `Stream.toList()` vs `Collectors.toList()` | `toList()` is immutable (Java 16+) |
| `range` vs `rangeClosed` | `range` exclusive, `rangeClosed` inclusive |
| `DoubleStream` has no `range()` | no natural increment for doubles |
| `groupingBy` vs `partitioningBy` | `partitioningBy` always has true/false keys |
| `Collectors.toMap` duplicate key | throws `IllegalStateException` |
| `peek` modifying state | never modify data inside `peek` |
| `sorted()` on infinite stream | never terminates |
| `Stream` is a pipeline, not a source | `of`, `generate`, `iterate` create the source |
