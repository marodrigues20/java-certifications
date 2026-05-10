# Chapter 9 — Collections & Generics Review
## OCP Java 21 Exam Summary

---

## 1. List Implementations

| Class | Order | Duplicates | Null | Notes |
|---|---|---|---|---|
| `ArrayList` | insertion | ✅ | ✅ | fast random access O(1) |
| `LinkedList` | insertion | ✅ | ✅ | fast insert/remove O(1) |

### SequencedCollection (Java 21)
```java
list.getFirst();  list.getLast();
list.addFirst(e); list.addLast(e);
list.reversed();
```

---

## 2. Set Implementations

| Class | Order | Duplicates | Null |
|---|---|---|---|
| `HashSet` | none | ❌ | ✅ one null |
| `LinkedHashSet` | insertion | ❌ | ✅ one null |
| `TreeSet` | natural/Comparator | ❌ | ❌ |

### NavigableSet (TreeSet)
```java
set.headSet(e);      // < e
set.tailSet(e);      // >= e
set.subSet(a, b);    // >= a && < b
set.floor(e);        // <= e
set.ceiling(e);      // >= e
set.lower(e);        // < e
set.higher(e);       // > e
set.pollFirst();     // remove and return smallest
set.pollLast();      // remove and return largest
set.reversed();      // Java 21
```

---

## 3. Map Implementations

| Class | Order | Null keys | Null values |
|---|---|---|---|
| `HashMap` | none | ✅ one | ✅ |
| `LinkedHashMap` | insertion | ✅ one | ✅ |
| `TreeMap` | natural/Comparator | ❌ | ✅ |

### HashMap useful methods
```java
map.putIfAbsent(k, v);
map.replace(k, v);
map.computeIfAbsent(k, fn);
map.compute(k, (k, v) -> ...);
map.merge(k, v, (old, newV) -> ...);
```

### NavigableMap (TreeMap)
```java
map.firstKey();  map.lastKey();
map.floorKey(k); map.ceilingKey(k);
map.headMap(k);  map.tailMap(k);
map.subMap(a, b);
map.pollFirstEntry(); map.pollLastEntry();
```

---

## 4. Queue & Deque

### Queue methods
| Group | Insert | Peek | Remove |
|---|---|---|---|
| Returns null/false | `offer()` | `peek()` | `poll()` |
| Throws exception | `add()` | `element()` | `remove()` |

### PriorityQueue
- Min-heap — root is always the smallest
- `forEach()` → order NOT guaranteed
- `poll()` in loop → natural order (ascending)
- Accepts `Comparator` in constructor for custom order
- Grows dynamically — no capacity limit

```java
Queue<String> pq = new PriorityQueue<>(Comparator.comparingInt(String::length));
```

### ArrayDeque as Queue (FIFO)
- Insert at end → `offer()` / `add()`
- Remove from beginning → `poll()` / `remove()`

### ArrayDeque as Stack (LIFO)
- Insert at top → `push()`
- Remove from top → `pop()`
- Peek top → `peek()`
- Prefer `ArrayDeque` over `Stack` (Stack is legacy)

### ArrayDeque as Double-Ended Queue
```java
deque.addFirst(e);    deque.offerFirst(e);
deque.addLast(e);     deque.offerLast(e);
deque.getFirst();     deque.peekFirst();
deque.getLast();      deque.peekLast();
deque.removeFirst();  deque.pollFirst();
deque.removeLast();   deque.pollLast();
```

### ArrayDeque internals
- Circular array — initial capacity 16, doubles when full
- `addFirst()` → physically inserts at last available index (head moves back)
- `addLast()` → physically inserts at index 0 (tail moves forward)
- No shift — O(1) for both ends
- Reallocation (copy) only when full — amortized O(1)

---

## 5. Comparable vs Comparator

### Comparable
```java
public class Dog implements Comparable<Dog> {
    @Override
    public int compareTo(Dog dog) {
        return this.age.compareTo(dog.age); // this before other = ascending
    }
}
```
- Returns negative → `this` comes BEFORE
- Returns zero → equal
- Returns positive → `this` comes AFTER

### Comparator
```java
// Method reference
Comparator<Dog> byAge = Comparator.comparing(Dog::getAge);

// Lambda
Comparator<Dog> byAge = (d1, d2) -> d1.getAge().compareTo(d2.getAge());

// Java 5 — separate class
public class DogComparator implements Comparator<Dog> {
    public int compare(Dog d1, Dog d2) {
        return d1.getAge().compareTo(d2.getAge());
    }
}

// Chaining
Comparator<Dog> comp = Comparator.comparing(Dog::getAge)
                                 .reversed()
                                 .thenComparing(Dog::getName);
```

### Sorting
```java
Collections.sort(list);              // Java 5 — in-place
list.sort(null);                     // Java 8 — in-place, uses Comparable
list.sort(Comparator.naturalOrder()); // Java 8 — in-place
list.stream().sorted().forEach(...); // Java 8 — NOT in-place
```

---

## 6. Collections Utility Class

```java
Collections.sort(list);
Collections.binarySearch(list, key);       // list must be sorted — O(log₂ n)
Collections.binarySearch(list, key, comp); // with Comparator
Collections.min(list);                     // uses Comparable
Collections.max(list, comparator);         // with Comparator
Collections.reverse(list);                 // in-place
Collections.frequency(list, element);      // counts occurrences
Collections.nCopies(n, element);           // immutable list with n copies
Collections.unmodifiableList(list);        // immutable VIEW — reflects original changes
```

### binarySearch return values
- `>= 0` → element found, returns index
- `< 0` → not found, returns `-(insertion point) - 1`

### List search methods
| Method | Order required | Cost |
|---|---|---|
| `binarySearch()` | ✅ | O(log₂ n) |
| `contains()` | ❌ | O(n) |
| `indexOf()` | ❌ | O(n) |
| `lastIndexOf()` | ❌ | O(n) |

---

## 7. Method References — 4 Types

| Type | Syntax | Equivalent lambda |
|---|---|---|
| Static method | `Integer::parseInt` | `s -> Integer.parseInt(s)` |
| Instance on particular object | `System.out::println` | `e -> System.out.println(e)` |
| Instance on parameter | `String::length` | `s -> s.length()` |
| Constructor | `ArrayList::new` | `() -> new ArrayList<>()` |

---

## 8. Generics & Wildcards

### Generic class
```java
public class Box<T extends Animal, U> { }
```

### Generic method
```java
public <T extends Dog> void run(T velocity) { }
public <U extends Animal> U eat(U food) { }
// <T super Dog> is NOT valid in generic methods
```

### Wildcards — PECS
| Wildcard | Use | Operation |
|---|---|---|
| `<? extends T>` | Producer Extends | read only |
| `<? super T>` | Consumer Super | write allowed |
| `<?>` | unbounded | read only |

```java
// Read only — list could be List<Dog>, List<Cat> or List<Elephant>
public void print(List<? extends Animal> animals) {
    animals.forEach(System.out::println);
    // animals.add(...) — does NOT compile
}

// Write allowed — list accepts Animal or supertype
public void add(List<? super Animal> animals) {
    animals.add(new Dog(...)); // ok
}
```

---

## 9. Wrapper Classes & Autoboxing

```java
int x = 4;
Integer y = x;  // Autoboxing  — int -> Integer
x = y;          // Unboxing    — Integer -> int

Integer.MAX_VALUE;
Integer.MIN_VALUE;
Integer.parseInt("42");   // returns primitive int
Integer.valueOf("42");    // returns Integer object — uses cache
```

### Integer cache trap (OCP!)
```java
Integer a = Integer.valueOf(127);
Integer b = Integer.valueOf(127);
System.out.println(a == b);      // true  — same cached object

Integer c = Integer.valueOf(128);
Integer d = Integer.valueOf(128);
System.out.println(c == d);      // false — different objects
System.out.println(c.equals(d)); // true  — same value
```
Cache range: **-128 to 127**

---

## 10. Diamond Operator

```java
// Before Java 7
List<String> list = new ArrayList<String>();

// Java 7+ — compiler infers type from left side
List<String> list = new ArrayList<>();

// NOT valid — <> only on right side
// List<> list = new ArrayList<String>(); // does not compile

// Java 9+ — allowed with anonymous classes
Comparable<String> c = new Comparable<>() {
    public int compareTo(String o) { return 0; }
};
```

---

## 11. Factory Methods

| Method | Mutable? | Null allowed? | Notes |
|---|---|---|---|
| `Arrays.asList()` | partial (set only) | ✅ | view of original array |
| `List.of()` | ❌ | ❌ NullPointerException | |
| `List.copyOf()` | ❌ | ❌ | independent copy |
| `Collections.unmodifiableList()` | ❌ | ✅ | view — reflects original |
| `Map.of()` | ❌ | ❌ | up to 10 pairs |
| `Map.ofEntries()` | ❌ | ❌ | more than 10 pairs |

```java
// Arrays.asList — view of array
List<String> list = Arrays.asList(array);
list.set(0, "new");   // ok
list.add("new");      // UnsupportedOperationException

// List.of
List<String> list = List.of("a", "b", "c");

// List.copyOf — independent immutable copy
List<String> copy = List.copyOf(originalList);

// Map.of — up to 10 entries
Map<Integer, String> map = Map.of(1, "Alex", 2, "Bob");

// Map.ofEntries — more than 10 entries
Map<Integer, String> map = Map.ofEntries(
    Map.entry(1, "Alex"),
    Map.entry(2, "Bob")
);
```

---

## Quick Reference — Exceptions

| Scenario | Exception |
|---|---|
| `poll()` on empty queue | returns `null` |
| `remove()` on empty queue | `NoSuchElementException` |
| `peek()` on empty queue | returns `null` |
| `element()` on empty queue | `NoSuchElementException` |
| `add()` on full capacity queue | `IllegalStateException` |
| Modify immutable list | `UnsupportedOperationException` |
| `List.of()` with null | `NullPointerException` |
| `binarySearch()` on unsorted list | undefined result (no exception) |
| `TreeMap/TreeSet` with null key | `NullPointerException` |
