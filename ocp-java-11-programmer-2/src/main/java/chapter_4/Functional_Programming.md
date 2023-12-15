# Chapter 4 - Functional Programming

- Java Stream API
  - Describe the Stream interface and pipelines
  - Use lambda expressions and method references
- Built-in Functional interfaces
  - Use interfaces from the java.util.function package
  - Use core functional interfaces including Predicate, Consumer, Function and Supplier
  - Use primitive and binary variations of base interfaces of java.util.function package.
- Lambda Operations on Streams
  - Extract stream data using map, peek and flatMap methods
  - Search stream data using search findFirst, findAny, anyMatch, allMatch and noneMatch methods
  - Use the Optional class
  - Perform calculations using count, max, min, average and sum stream operations
  - Sort a collection using lambda expression
  - Use Collectors with stream, including the groupingBy and partitioningBy operations

Note that the Stream API in this chapter is used for functional programming. By contrast, there are also java.io streams,
which we will talk about in Chapter8, "I/O". Despite both using the word stream, they are nothing alike.

## Working with Built-in Functional Interfaces


- All of the functional interfaces in Table 4.1 are provided in the java.util.function package.
- The convention here is to use the generic type T for the type parameter. If a second type parameter is needed, the 
next letter, U, is used. If a distinct return type is needed, R for return is used for the generic type.

- TABLE 4.1 


| Functional Interfaces | Return type | Method name  | # parameters |
|-----------------------|-------------|--------------|--------------|
| Supplier< T >         | T           | get()        | 0            |
| Consumer< T >         | void        | accept(T)    | 1(T)         |
| BiConsumer<T, U>      | void        | accept(T, U) | 2(T,U)       |
| Predicate< T >        | boolean     | test(T)      | 1(T)         |
| BiPredicate<T, U>     | boolean     | test(T, U)   | 2(T,U)       |
| Function<T, R>        | R           | apply(T)     | 1(T)         |
| BiFunctinal<T, U, R>  | R           | apply(T, U)  | 2(T,U)       |
| UnaryOperator<T>      | T           | apply(T)     | 1(T)         |
| BinaryOperator<T>     | T           | apply(T,T)   | 2 (T, T)     |


- Note 1: Table 4.1 is a subset of what you need to know. Many functional interfaces are defined in the java.util.function
package. There are even functional interfaces for handling primitives, which you'll see later in the chapter.
- Note 2: Table 4.1 is significant because these interfaces are often used in streams and other classes that come with 
Java, which is why you need to memorize them for the exam.
- Note 3: Chapter 7, there are 2 more functional interfaces called Runnable and Callable.

## Implementing Supplier

- A Supplier is used when you want to generate or supply values without talking any input. The Supplier interface is 
defined as follows:

```
@FunctionalInterface
public interface Supplier<T>{
  T get();
}
```

- You can create a LocalDate object using the factory method now(). This example shows how to use a Supplier to call 
this factory:

i.e: chapter_4.funcional.supplier.SupplierExamples.java

```
  Supplier<LocalDate> s1 = LocalDate::now;
  Supplier<LocalDate> s2 = () -> LocalDate.now();
  
  LocalDate d1 = s1.get();
  LocalDate d2 = s2.get();
  
  System.out.println(d1);  // Printout 2020-02-20
  System.out.println(d1);  // Printout 2020-02-20
```


- It's also a good opportunity to review static method references. The LocalDate::now method reference is used to create
a Supplier to assign to an intermediate variable s1.
- A Supplier is often used when constructing new objects.

i.e: i.e: chapter_4.funcional.supplier.SupplierExamples.java


## Implementing Consumer and BiConsumer

- You use a Consumer when you want to do something  with a parameter but not return anything. BiConsumer does the same
thing except that it takes two parameters. The interfaces are defined as follows:

```
@FunctionalInterface
public interface Consumer<T>{
  void accept(T t);
  // ommited defautl method
}

@FunctionalInterface
public interface BiConsumer<T, U>{
  void accept(T t, U u>);
  // omitted default method
}
```

- It should be old news by now that you can use a Predicate to test a condition.

```
  Predicate<String> p1 = String::isEmpty;
  Predicate<String> p2 = x -> x.isEmpty();
  
  System.out.println(p1.test(""));  //true
  System.out.println(p2.test("")); //true
```

i.e: chapter_4.funcional.predicate.SupplierExamples.java

- More interesting is a BiPredicate. This example also prints true twice.

```
  BiPredicate<String, String> b1 = String::startsWith;
  BiPredicate<String, String> b2 = (string, prefix) -> string.startsWith(prefix);
  
  System.out.println(b1.test("chicken", "chick")); //true
  System.out.println(b2.test("chicken", "chick"));
```

i.e: chapter_4.funcional.bipredicate.BiPredicateExample.java

## Implementing Function and BiFunction

- In Chapter 3, we used Function with merge() method. A Function is responsible for turning one parameter into a value 
  of a potentially different type and returning it. Similarly, a BiFunction is responsible for turning two parameters
  into a value and returning it. The interfaces are defined as follows:

```java
@FunctionalInterface
public interface Function<T, R>{
    R apply(T t);
    // omitted default and static methods
}

@FunctionalInterface
public interface BiFunction<T, U, R>{
    R apply(T t, U u);
}
```

- For example, this function converts a String to the length of the String

i.e: chapter_4.funcional.function.FunctionExample.java

```
    Function<String, Integer> f1 = String::length;
    Function<String, Integer> f2 = x -> x.length;
    
    System.out.println(f1.apply("cluck")); //5
    System.out.println(f2.apply("click")); //5
```

- This function turns a String into a Integer. Well, technically it turns the String into an int, which is autoboxed 
into an Integer. The types don't have to be different combines two String objects and produce another String:


i.e: chapter_4.funcional.bifunction.BiFunctionExample.java
```

  BiFunction<String, String, String> b1 = String::concat;
  BiFunction<String, String, String> b2 = (string, toAdd) -> string.concat(toAdd);

  System.out.println(b1.apply("baby ", "chick")); // baby chick
  System.out.println(b2.apply("baby ", "chick")); // baby chick

```

- The first two types in the BiFunction are the input types. The third is the result type. For the method reference,
  the first parameter is the instance the concat() is called on, and the second is passed to concat().

## Creating Your Own Functional Interfaces

- Java provides a built-in interface for functions with one or two parameters. What if you need more?
- Suppose that you want to create a functional interface for the wheel speed of each wheel on a tricycle. 
- You could create a functional interface such as this:

```java
@FunctionalInterface
interface TriFunction<T,U,V,R>{
    R apply(T t, U r, V v);
}
```

- There are four type parameters. The first three supply the types of the three wheel speeds. The fourth is the return 
  type. Now suppose that you want to create a function to determine how fast your quad-copter is going given the power 
  of the four motors. You could create a functional interface such as the following:

```java
@FunctionalInterface
interface QuadFunction<T,U,V,W,R>{
    R apply(T t, U u,V v,W w);
}
```

- Remember that you can add any functional interfaces you'd like, and Java matches them when you use lambdas or method 
  references.


## Implementing UnaryOperator and BinaryOperator

- UnaryOperator and BinaryOperator are a special case of a Function. They require all type parameters to be the same type.
- A UnaryOperator transforms its value into one of the same type.
- For example, incrementing by one is a unary operation. In fact, Unary Operator extends Function.
- A BinaryOperator merges two values into one of the same type. Adding two numbers is a binary operation. Similarly, 
  BinaryOperator extends BiFunction. The interfaces are defined as follows:

```java
import java.util.function.Function;

@FunctionalInterface
public interface UnaryOperator<T> extends Function<T, T>{ }
```

```java
import java.util.function.BiFunction;

@FunctionalInterface
public interface BinaryOperator<T> extends BiFunction<T, T, T>{
    // omitted static methods
}
```

- This means that method signature look like this:

```
  T apply(T t);         // UnaryOperator
  T apply(T t1, T t2);  // BinaryOperator
```

- In the Javadoc, you'll notice that these methods are actually inherited from the Function/BiFunction superclass.
- The generic declarations on the subclass are what force the type to be the same. For the unary example, notice how the 
  return type is the same type as the parameter.

```
UnaryOperator<String> u1 = String::toUpperCase();
UnaryOperator<String> u2 = x -> x.toUpperCase();

System.out.println(u1.apply("chirp")); // CHIRP
System.out.println(u2.apply("chirp")); // CHIRP
```

- This prints CHIRP twice. We don't need to specify the return type in the generics because UnaryOperator requires it
  to be the same as the parameter. And now here's the binary example:

```
BinaryOperator<String> b1 = String::concat;
BinaryOperator<String> b2 = (string, toAdd) -> string.concat(toAdd);

System.out.println(b1.apply("baby","chick")); // baby chick
System.out.println(b2.apply("baby","chick")); // baby chick
```

- Notice that this does the same thing as the BiFunction example. The code is more succint, which shows the importance 
of using the correct functional interface. It's nice to have one generic type specified instead of three.


## Convenience Methods on Functional Interfaces

- By definition, all functional interface have a single abstract method. This doesn't mean they can have only one method,
  though. Several of the common functional interfaces provide a number or helpful default methods.

- Table 4.2 shows the convenience methods on the built-in functional interfaces that you need to know for the exam.
- Table 4.2 shows only the main interfaces. The BiConsumer, BiFunction, and BiPredicate interfaces have similar methods 
  available.

- Let's start with these two Predicate variables.

```
Predicate<String> egg = s -> s.contains("egg");
Predicate<String> brown = s -> s.contains("brown");
```

- TABLE 4.2 Convenience methods

| Interface instance | Method return type | Method name | Method parameters |
|--------------------|--------------------|-------------|-------------------|
| Consumer           | Consumer           | andThen()   | Consumer          |
| Function           | Function           | andThen()   | Function          |
| Function           | Function           | compose()   | Function          |
| Predicate          | Predicate          | and()       | Preciate          |
| Predicate          | Predicate          | negate()    | -----             |
| Predicate          | Predicate          | or()        | Predicate         |


- Now we want a Predicate for brown eggs and another for all other colors of eggs. We could write this by hand, as shown 
  here:

```
  Predicate<String> brownEggs = 
      s -> s.contains("egg") && s.contains("brown");
  Predicate<String> otherEggs =
      s -> s.contains("egg") && ! s.contains("brown");
```

- This works, but it's not great. It's a bit long to read, and it contains duplication.
- What if we decide the letter e should be capitalized in egg? We'd have to change it in
  three variables: egg, brownEggs, and otherEggs. A better way to deal with this situation is to
  use two of the default methods on Predicate.

```
  Preicate<String> brownEggs = egg.and(brown);
  Predicate<String> otherEggs = egg.and(brown.negate());
```

- Neat! Now we are reusing the logic in the original Predicate variables to build two new ones.
- It's shorter and clearer waht the relationship is between variables. We can change the spelling of egg in one place,
  and the other two objects will have new logic because they reference it.


- Moving to consumer, let's take a look at the andThen() method, which run two functional interfaces in sequence.

```
  Consumer<String> c1 = x -> System.out.println("1: " + x);
  Consumer<String> c2 = x -> System.out.println(",2 " + x);
  
  Consumer<String> combined = c1.andThen(c);
  combined.accept("Annie"); // 1: Annie,2: Annie
```

- This shows that the Consumer instance are run in sequence and are independent of each other.
- By contrast, the compose() method on Function chains functional interfaces. However, it passes along the output of one
  to the input of another.


```
  Function<Integer, Integer> before = x -> x + 1;
  Function<Integer, Integer> after = x -> x * 2;
  
  Function<Integer, Integer> combined = after.compose(before);
  System.out.println(combined.apply(3));  // 8

```

- This time the before runs first, truning the 3 into a 4. Then the after runs, doubling the 4 to 8. All of the methods
  in this section are helpful in simplifying your code as you work with functional interfaces.


## Returning an Optional

- How do we express this "we don't know" or "not applicable" answer in Java? We use the Optional type.
- An Optional is created using a factory.
- You can either request an empty Optional or pass a value for the Optional to wrap.
- Think of an Optional as a box that might have something in it or might instead be empty.

## Creating an Optional


i.e: chapter_4.optional.OptionalExample.java

```
10:  public static Optional<Double> average(int... scores){
11:    if (scores.length == 0) return Optional.empty();
12:    int sum = 0;
13:    for (int score: scores) sum += score;
14:    return Optional.of((double) sum / scores.length)
15:  }
```

- Calling the method show what is in our two boxes.

```
  System.out.println(average(90, 100)); // Optional[95.0]
  System.out.println(average()); // Optional.empty
```

- You can see that one Optional contains a value and the other is empty. Normally, we want to check whether a value is
  there and/or get it out of the box. Here's one way to do that:

```
20: Optional<Double> opt = average(90, 100);
21: if (opt.isPresent())
22:    System.out.println(opt.get()); // 95.0
```

- Line 21 checks whether the Optional actually contains a value. Line 22 prints it out. What if we didn't do the check
  and the Optional was empty?

```
26: Optional<Double> opt = everage();
27: System.out.println(opt.get()); //95.0
```

- We'd get an exception since there is no value inside the Optional.

```
java.util.NoSuchElementException: No value present
```

- When creating an Optional, it is commomn to want to use empty() when the value is null. You can do this with an if 
  statement or ternary operator. We use the ternary operator(? :) to simplify the code.

```
Optional o = (value == null) ? Optional.empty() : Optional.of(value);
```

- If value is null, o is assigned the empty Optional. Otherwise, we wrap the value. Since this is such a common pattern,
  Java provides a factory method to do the same thing.

```
Optional o = Optional.ofNullable(value);
```

Table 4.3 Optional instance methods

| Method                  | When Optional is empty                      | When Optional contains value |
|-------------------------|---------------------------------------------|------------------------------|
| get()                   | Throws an exception                         | Returns value                |
| ifPresent (Consumer c)  | Does nothing                                | Calls Consumer with value    |
| isPresent()             | Returns false                               | Returns true                 |
| orElse(T other)         | Returns other parameter                     | Returns value                |
| orElseGet(Supplier s)   | Returns result of calling Supplier          | Returns value                |
| orElseThrow()           | Throws NoSuchElementException               | Returns value                |
| orElseThrow(Supplier s) | Throws exception create by calling Supplier | Returns value                |


- You've already seen get() and isPresent(). The other methods allow you to write code that uses an Optional in one line
  without having to use the ternary operator. This makes the code easier to read.


## Dealing with an Empty Optional

- The remaining methods allow you to specify what to do if a value isn't present. There are a few choices. The first two
  allow to specify a return value either directly or using a Supplier.


i.e: java.util.Optional.OptionalExample.java
```
30: Optional<Double> opt = average();
31: System.out.println(opt.orElse(Double.NaN));
32: System.out.println(opt.orElseGet(() -> Math.random());
```

- This prints something like the following:

```
NAN
0.49775932295380165
```

- We can have the code throw an exception if the Optional is empty.

```
30: Optional<Double> opt = evarage();
31: System.out.println(opt.orElseThrow()); 
```

- This prints something like the following:

```
Exception in thread "main" java.util.NoSuchElementException: No value present
	at java.base/java.util.Optional.orElseThrow(Optional.java:377)
	at chapter_4.optional.OptionalExample.main(OptionalExample.java:41)
```

- Without specifying a Supplier for the exception. Java will throw a NoSuchElementException. This method was added in 
Java 10. Remember that the stack trace looks weird because the lambdas are generated rather than names classes. 
- Alternatively, we can have the code throw a custom exception if the Optional is empty.

```
30: Optional<Double> opt = everage();
31: System.out.println(opt.orElseThrow(
32:      () -> new IllegalStateException())); 
```

- This prints something like the following:

```
Exception in thread "main" java.lang.IllegalStateException    
    at optionals.Methods.lambda$orElse$1(Methods.java:30)    
    at java.base/java.util.Optional.orElseThrow(Optional.java:408)
```

- Line 32 shows using a Supplier to create an exception that should be thrown. Notice that we do not write throw new
  IllegalStateException(). The orElseThrow() method takes care of actually throwing the exception when we run it.

- The two methods that takes a Supplier have different names. Do you see why this code does not compile?

```
  System.out.println(opt.orElseGet( 
      () -> new IllegalStateException())); // DOES NOT COMPILE
```

- The opt variable is an Optional<Double>. This means the Supplier must return a Double. Since this supplier returns an
exception, the type does not match.



## Is Optional the Same as null?

- There were a few shorcommings with this approach. 
- One was that there wasn't a clear way to express that null might be a special value. By contrast, returning an Optional
is a clear statement in the API that there might not be a value in there.
- Another advantage of Optional is that you can use a functional programming style with ifPresent() and the other methods
rather than needing an if statement. Finally, you'll see towards the end of the chapter that you can chain Optional calls.


## Using Streams

- A stream in Java is a sequence of data. A stream pipeline consist of the operations that run on a stream to produce a 
  result.

## Understanding the Pipeline Flow

- Think of a stream pipeline as an assembly line in a factory.
- Another important feature of an assembly line is that each person touches each element to do their operation and then
  that piece of data is gone. It doesn't come back.
- The streams is different than the list and queues.
- With a list, you can access any element at any time.
- With a queue, you are limited in which elements you can access, but all of the element are there.
- With streams, the data isn't generated up front - it is created when needed. This is an example of lazy evaluation, 
  which delays execution until necessary.
- In functional programming, these are called stream operations.
- There are three parts to a stream pipeline, as shown in Figure 4.2.
  - Source: Where the stream comes from
  - Intermediate operations: Transforms the stream into another one. There can be as few or as many intermediate
    operations as you'd like. Since streams use lazy evaluation, the intermediate operations do not run until the 
    terminal operation runs.
  - Terminal operation: Actually produces a result. Since streams can be used only once, the stream is no longer 
    valid after a terminal operation completes.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_4/images/figure_4_2.png?raw=true)


- You will need to know the difference between intermediate and terminal operations well. Make sure you can fill in 
  Table 4.4.

| Scenario                                | Intermediate operation | Terminal operation |
|-----------------------------------------|------------------------|--------------------|
| Required part of a useful pipeline?     | No                     | Yes                |
| Can exist multiple times in a pipeline? | Yes                    | No                 |
| Return type is a stream type            | Yes                    | No                 |
| Executed upon method call?              | No                     | Yes                |
| Stream valid after call?                | Yes                    | No                 |


## Creating Stream Sources

- In Java, the streams we have been talking about are represented by the Stream<T> interface, defined in the 
  java.util.stream package

## Creating Finite Streams

- For simplicity, we'll start with finite streams. There are a few ways to create them.

- i.e: chapter_4.streams.CreateStreamExample.java
```
11: Stream<String> empty = Stream.empty();          // cout = 0
12: Stream<Integer> singleElement = Stream.of(1);   // count = 1
13: Stream<Integer> fromArray = Stream.of(1, 2, 3); // count = 3
```
- Note: Line 13 shows how to create a stream from a varargs. You've undoubtedly noticed that there isn't any array on 
  line 13. The method signature uses varargs, which let you specify an array or individual elements.

- Java also provides a convenient way of converting a Collection to a stream.

```
14: var list = List.of("a", "b", "c");
15: Stream<String> fromList = list.stream();
```

- Line 15 shows that it is a simple method call to create a stream from a list.
- This is helpful since such conversions are common.


## Creating a Parallel Stream

- It is just as easy to create a parallel stream from a list.

```
24: var list = List.of("a", "b", "c");
25: Stream<String> fromListParallel = list.parallelStream();
```

- This is a great feature because you can write code that uses concurrency before learning what a thread is.


## Creating Infinite Streams

- So far, this isn't particularly impressive. We could do all this with lists. We can't create an infinite list, though,
  which makes streams more powerful.

```
17: Stream<Double> randoms = Stream.generate(Math::random);
18: Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
```

- Line 17 generates a stream of random numbers. How many random numbers? However many you need. If you call 
  randoms.forEach(System.out::println), the program will print random numbers until you kill it.
- Line 18 gives you more control. The interate() method takes a seed or starting value as the first parameter. 
  This is the first element that will be part of the stream. The other parameter is a lambda expression that gets
  passed the previous value and generates the next value.

- What if you wanted just odd numbers less than 100?

```
19: Stream<Integer> oddNumberUnder100 = Stream.iterate(
20: 1,              //seed 
21: n -> n < 100,   //Predicate to specify when done
22: n -> n + 2);    // UnaryOperator to get next value
```


## Reviewing Stream Creating Methods

- To review, make sure you know all the methods in Table 4.5. These are the ways of creating a source for streams,
  given a Collection instance coll.


| Method                                         | Finite or infinite? | Notes                                                                                                                                                                    |
|------------------------------------------------|---------------------|--------------------------------------------------------------------------------------------------------------------------------------------------------------------------|
| Stream.empty()                                 | Finite              | Creates Stream with zero elements                                                                                                                                        |
| Stream.of(varargs)                             | Finite              | Creates Stream with elements listed                                                                                                                                      |
| coll.stream()                                  | Finite              | Creates Stream from a Collection                                                                                                                                         |
| coll.parallelStream()                          | Finite              | Creates Stream from a Collection where the stream can run in parallel                                                                                                    |
| Stream.generate(supplier                       | Infinite            | Creates Stream by calling the Supplier for each element upon request                                                                                                     |
| Stream.iterate(seed, unaryOperator)            | Infinite            | Creates Stream by using the seed for the first element and then calling the UnaryOperator for each subsequent element upon request                                       |
| Stream.iterate(seed, predicate, unaryOperator) | Finite or infinite  | Creates Stream by using the seed for the first element and then calling the UnaryOperator for each subsequent element upon request. Stops if the Predicate returns false |



## Using Common Terminal Operations

- You can perform a terminal operation without any intermediate operations but not the other way around.
- This is why we will talk about terminal operations first.
- Reductions are a special type of terminal operation where all of the contents of the stream are combined into a single
  primitive or Object.


- Table 4.6 summarizes this section. Feel free to use it as a guide to remember the most important points as we go 
through each one individually. We explain them from simplest to most complex rather than alphabetically.

| Method                              | What happens for infinite streams | Return value | Reduction |
|-------------------------------------|-----------------------------------|--------------|-----------|
| count()                             | Does not terminate                | long         | Yes       |
| min(); max()                        | Does not terminate                | Optional<T>  | Yes       |
| findAny(); findFirst()              | Terminates                        | Optional<T>  | No        |
| allMatch(); anyMatch(); noneMatch() | Sometimes terminate               | boolean      | No        |
| forEach()                           | Does not terminate                | void         | No        |
| reduce()                            | Does not terminate                | Varies       | Yes       |
| collect()                           | Does not terminate                | Varies       | Yes       |


### count()

- The count() method determines the number of elements in a finite stream.
- For an infinite stream, it never terminates. Why? Count from 1 to infinity and let us know when you are finished.

```
long count()
```

- This example shows calling count() on a finite stream:

i.e: chapter_4.streams.TerminalOperationsExamples.java#methodCount()
```
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
System.out.println(s.count()); //3
```

## min() and max()

- The min() and max() methods allow you to pass a custom comparator and find the smallest or largest value in a finite 
  stream according to that sort order.
- Like the count() method, min() and max() hang on an infinite stream because they cannot be sure that a smaller or 
  larger value isn't coming later in the stream.
- Both mehtods are reductions because they return a single value after looking at the entire stream.


- The method signatures are as follows:

```
Optional<T> min(Comparator<? super T> comparator)
Optional<T> max(Comparator<? super T> comparator)
```

- This example finds animal with the fewest letters in its name:


i.e: chapter_4.streams.TerminalOperationsExamples.java#methodMin()
```
Stream<String> s = Stream.of("monkey", "ape", "bonobo");
Optional<String> min = s.min((s1, s2) -> s1.length - s2.length);
min.ifPresent(System.out::println); // ape
```

- As an example of where there isn't a minimum, let's look at an empty stream.

```
Optional<?> minEmpty = Stream.empty().min((s1, s2) -> 0);
System.out.println(minEmpty.isPresent());  //false
```

- Since the stream is empty, the comparator is never called, and no value is present in the Optional.


Note: You can't not have both (min and max), at least not using these methods. Remember, a stream can have only one 
      terminal operation.


### findAny() and findFirst()

- The findAny() and findFirst() methods return an element of the stream unless the stream is empty.
- If the stream is empty, they return an empty Optional.
- This is the first method you've seen that can terminate with an infinite stream.
- These methods are terminal operations but not reductions.
- The reason is that they sometimes return without processing all of the elements. This means that they return a value
  based on the stream but do not reduce the entire stream into one value.


- The method signature are as follows:

```
Optional<T> findAny()
Optional<T> findFirst()
```

- This example finds an animal:

i.e: chapter_4.streams.TerminalOperationsExamples.java#methodFindAny()
```
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
Stream<String> infinite = Stream.generate(() -> "chimp");

s.findAny().ifPresent(System.out::println); //monkey (usually)
infinite.findAny().ifPresent(System.out::println); //chimp
```


### allMatch(), anyMatch(), and noneMatch()

- Those three methods search a stream and return information about how the stream pertains to the predicate.
- These may or may not terminate for infinite streams. It depends on the data. Like the find methods, they are not 
  reductions because they do not necessarily look at all of the elements.
  

- The method signatures are as follows:

```
boolean anyMatch(Predicate <? super T> predicate)
boolean allMatch(Prediate <? super T) predicate)
boolean noneMatch(Predicate <? super T> predicate)
```

- This example checks whether animal names begin with letters:

i.e: chapter_4.streams.TerminalOperationsExamples.java#methodMatches()
```
var list = List.of("monkey", "2", "chimp");
Stream<String> infinite = Stream.generate(() -> "chimp");
Predicate<String> pred = x -> Character.isLetter(x.charAt(0));

System.out.println(list.stream().anyMatch(pred));     // true
System.out.println(list.stream().allMatch(pred));     // false
System.out.println(list.stream().noneMatch(pred));    // false
System.out.println(infinite.anyMatch(pred));          // true  
```


- This shows that we can reuse the same predicate, but we need a different stream each time.
- The anyMatch() method returns true because two of the three elements match.
- The allMatch() method returns false because one doesn't match.
- The noneMatch() method also returns false because one matches.
- On the infinite stream, one match is found, so the call terminates.
- If we called allMatch(), it would run until we killed the program.


Note: Remember that allMatch(), anyMatch(), and noneMatch() return a boolean.
      By contrast, the find method return an Optional because they return an element of the stream.


### forEach()

- Like in the Java Collections Framework, it is common to iterate over the elements of a stream. As expected, calling
  forEach() on a infinite does not terminate. Since there is no return value.

- Te method signature is as follow:


```
void forEach(Consumer<? super T> action)
```

- Notice that this is the only terminal operation with a return type of void.

i.e: chapter_4.streams.TerminalOperationsExamples.java#methodForEach()
```
Stream<String> s = Stream.of("Monkey", "Gorilla", "Bonobo");
s.forEach(System.out::print); // MonkeyGorillaBonobo
```

Note 1: Remember that you can call forEach() directly on a Collection or on a Stream.
Note 2: Don't get confused on the exam when you see both approaches.


- Notice that you can't use a traditional for loop an a stream.

```
Stream<Integer> s = Stream.of(1);
for (Integer i : s) {} // DOES NOT COMPILE
```

## Reduce

- The reduce() method combines a stream into a single object. It is a reduction, which means it process all elements.

```
T reduce(T identity, BinaryOperator<T> accumulator)

Optional<T> reduce(BinaryOperator<T> accumulator)

<U> U reduce(U identity, BiFunction<U, ? super T,U> accumulator, BinaryOperator<U> combiner)

```

- The most common way of doing a reduction is to start with an initial value and keep merging it with the enxt value.
- Think about how you would concatenate an array of String object into a single String without functional programming.
- It might look something like this:

```
var array = new String[] {"w","o","l","f"}
var result = "";
for (var s: array) result = result + s;
System.out.println(result); // wolf
```

- Notice how we still have the empty String as the identity. We also still concatenate the String object to get the 
  next value. We can even rewrite this with a method reference.

```
Stream<String> stream = Stream.of("w","o","l","f");
String word = stream.reduce("", String::concat);
System.out.println(word); // wolf
```

- Let's try another one. Can you write a reduction to multiply all of the Integer objects in a stream? Try it. Our 
  solution is shown here:

```
Stream<Integer> stream = Stream.of(3, 5, 6);
System.out.println(stream.reduce(1, (a, b) -> a*b));  //90
```


- We set the identity to 1 and the accumulator to multiplication. In many cases, the identity isn't really necessary, so
  Java lets us omit it. When you don't specify an identity, an Optional is returned because there might not be any data.
  There are three choices for what is in the Optional.
  - If the stream is empty, an empty Optional is returned.
  - If the stream has one element, it is returned.
  - If the stream has multiple elements, the accumulator is applied to combine them.
- The following illustrate each of these scenarios:


```
BinaryOperator<Integer> op = (a, b) -> a * b;

Stream<Integer> empty = Stream.empty();
Stream<Integer> oneElement = Stream.of(3);
Stream<Integer> threeElements = Stream.of(3, 5, 6);

empty.reduce(op).ifPresent(System.out::println);           // no output
oneElement.reduce(op).ifPresent(System.out::println);      // 3
threeElements.reduce(op).ifPresent(System.out::println);   // 90
```



- The third method signature is used when we are dealing with different types. It allows Java to create intermediate
  reduction and then combine them at the end. Let's take a look at an example that counts the number of characters in 
  each String.

i.e: chapter_4.streams.TerminalOperationsExamples.java#methodReduce3()

```
 Stream<String> stream = Stream.of("w", "o", "l", "f!");
 int length = stream.reduce(0, (i, s) ->  i+s.length(), (a, b) -> a + b);
 System.out.println(length);
```

- The first parameter (0) is teh value for the initializer.
- If we had an empty stream, this would be the answer.
- The second parameter is the accumulator. Unlike the accumulators you saw previously, this one handles mixed data types.
- In this example, the first argument, i, is an Integer, while the second argument, s, is a String. It adds the length
  of the current String to our running total.
- The third parameter is called the combiner, which combines any intermediate totals. In this case, a and b are both 
  Integer values.

- The three-argument reduce() operation is useful when working with parallel streams because it allows the stream to be
  decomposed and reassembled by separate threads. For example, if we needed to count the length of four 100-character
  strings, the first two values and the last two values could be computed independently. The intermediate result
  (200 + 200) would then be combined into the final value.



### Collect()

- The collect() method is a special type of reduction called a mutable reduction.
- Common mutable objects include StringBuilder and ArrayList.
- This is a really useful method, because it lets us get data out of streams and into another form.

```

<R> R collect(Supplier<R> supplier, 
    BiConsumer<R, ? super T> accumulator,
    BiConsumer<R, R> combiner)

<R,A> R collect(Collector<? super T, A,R> collector)
```

- Let's start with the first signature, which is used when we want to code specifically how collecting should work.
- Our wolf example from reduce can be converted to use collect().

```
Stream<String> stream = Stream.of("w", "o", "l", "f");
  StringBuilder word = stream.collect(StringBuilder::new,
  StringBuilder::append,
  StringBuilder::append);

System.out.println(word); // wolf
```

- The first parameter is the supplier, which creates the object that will store the result as we collect data.
- The second parameter is the accumulator, which is a BiConsumer that takes two parameter and doesn't return anything.
  In this example, it appends the next String to the StringBuilder.
- The final parameter is the combiner, which is another BiConsumer.It is responsible for taking two data collection and 
  merging them. This is useful when we are processing in parallel. Two smaller collections are formed and then merged 
  into one. This would work with StringBuilder only if we didn't care about the order of the letters. In this case, the
  accumulator and combiner have similar logic.

- Now let's look at an example where the logic is different in the accumulator and combiner.


i.e: i.e: chapter_4.streams.TerminalOperationsExamples.java#methodCollect2()
```
Stream<String> stream = Stream.of("w", "o", "l", "f");

TreeSet<String> set = stream.collect(
  TreeSet::new, 
  TreeSet::add, 
  TreeSet::addAll);
  
System.out.println(set); //[f, l, o, w]

```

- The collector has three parts as before.
- The supplier creates an empty TreeSet.
- The accumulator adds a single String from the Stream to the TreeSet.
- The combiner adds all of the elements of one TreeSet to another in case the operations were done in parallel and need 
  to be merged.

- In practice, there are many common collectors that come up over and over. 
- Rather than making developers keep reimplementing the same ones, Java provides a class with common collectors cleverly 
  named Collectors. This approach also makes the code easier to read because it is more expressive.

i.e: i.e: i.e: chapter_4.streams.TerminalOperationsExamples.java#methodCollect3()

```
Stream<String> stream = Stream.of("w", "o", "l", "f");
TreeSet<String> set = stream.collect(Collectors.toCollection(TreeSet::new));
System.out.println(set); // [f, l, o, w]
```

- If we didn't need the set to be sorted, we could make the code even shorter:

```
Stream<String> stream2 = Stream.of("w", "o", "l", "f");
Set<String> set2 = stream2.collect(Collectors.toSet());
System.out.println(set2); // [f, w, l, o]
```

- You might get different output for this last one since toSet() makes no guarantees as to which implementation of Set
  you'll get. It is likely to be a HashSet, but you shouldn't expect or rely on that.


## Using Common Intermediate Operations









