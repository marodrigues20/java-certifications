# Chapter 3 - Generics and Collections

- Generics and Collections
  - Use wrapper classes, autoboxing and autounboxing
  - Create and use generic classes, methods with diamond notation and wildcard
  - Describe the Collections Framework and use key collection interface
  - Use Comparator and Comparable interfaces
  - Create and use convenience methods for collections
- Java Stream API
  - Use lambda expression and method references

We will review all the functional interfaces in Chapter 4, "Functional Programming" but since some will be used in this
chapter, we will provide Table 3.1 as a handy reference. The letters (R,T, and U) are generics that you can pass any
type to when using these functional interfaces.

Table 3.1 Functional interfaces used in this chapter

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


For this chapter, you can just use these functional interfaces as is. In the next chapter, though, we'll be presenting
and testing your knowledge of them.

## Using Method References

- Method references are another way to make the code easier to read, such as simply mentioning the name of the method.
- In this section we show you 4 types of method references. We will also mix in lambdas with method references.
- A method reference lets us remove that redundancy and instead write this:
  ```
  LearnToSpeak learner = System.out::println;
  ```
  
- The :: operator tells Java to call the println() method later.

Note: Remember that :: is like a lambda, and it is used for deferred execution with a functional interface.

- There are four formats for method references:
  - Static methods
  - Instance methods on a particular instance
  - Instance methods on a parameter to be determined at runtime
  - Constructor


## Calling Static Methods

- The Collections class has a static method that can be used for sorting. Per Table 3.1, the Consumer functional interface
takes one parameter and does not return anything.

i.e: chapter_3.method.refmethodstatic.Sort.java

```
14: Consumer<List<Integer>> methodRef = Collections::sort;
15: Consumer<List<Integer>> lambda = x -> Collections.sort(x);
```

- One line 14, we reference a method with one parameter, and Java knows that it's like a lambda with one parameter.
Additionally, Java knows to pass that parameter to the method.
- Collections has sort method overloaded. Java knows which one it should use because of the context. In this case, we
said that we were declaring a Consumer, which takes only one parameter. If for some reason Java can't find the correct
method ore there more than one method, then the compiler will report an error. The latter is sometimes called an 
ambiguous type error.

## Calling Instances Methods on a Particular Object

- The String class has a startsWith() method that takes one parameter and returns a boolean. Conveniently, a Predicate 
is a functional interface that takes one parameter and returns a boolean.

i.e: chapter_3.method.refmethodinstance.MethodReferences.java

```
18: var str = "abc";
19: Predicate<String> methodRef = str::startsWith;
20: Predicate<String> lambda = s -> str.startsWith(s);
```

- A method reference doesn't have to take any parameters. In this example, we use a Supplier, which takes zero parameters
and returns a value:

```
var random = new Random();
Supplier<Integer> methodRef = random::nextInt;
Supplier<Integer> lambda = () -> random.nextInt();
```

## Calling Instance Methods on a Parameter

i.e: chapter_3.method.refmethodparam.MethodParam.java

- Calling an instance method that doesn't take any parameters. The trick is that we will do so without knowing the
instance in advance.

```
23: Predicate<String> methodRef = String::isEmpty;
24: Predicate<String> lambda = s -> s.isEmpty();
```

- Line 23 says the method that we want to call is declared in String. It looks like a static method, but it isn't. 
Instead, Java knows that isEmpty() is an instance method that does not take any parameters. Java uses the parameter
supplied at runtime as the instance on which the method is called.

- We are going to use a functional interface called a BiPredicate, which takes two parameters and returns a boolean.

```
26: BiPredicate<String, String> methodRef = String::startsWith;
27: BiPredicate<String, String> lambda = (s, p) -> s.startsWith(p);
```

- Since the functional interface takes two parameters, Java has to figure out what they represent. The first one will
always be the instance of the object for instance methods. Any others are to be method parameters.

## Calling Constructors

i.e: chapter_3.method.refmethodconstructor.MethodRefConstructor.java

```
30: Supplier<List<String>> methodRef = ArrayList::new;
31: Supplier<List<String>> lambda = () -> new ArrayList();
```

- In the previous example, the lambda doesn't have any parameters.

- In our next example, we will use Function functional interface, which takes one parameter and returns a result. Notice
that line 32 in the following example has the same method reference as line 30 in the previous example:

```
32: Function<Integer, List<String>> methodRef = ArrayList::new;
33: Function<Integer, List<String>> lambda = x -> new ArrayList(x);
```

- Java sees that we are passing an Integer parameter and calls the constructor of ArrayList that takes a parameter.

## Reviewing Method References

- Table 3.2 Method references

| Type                                    | Before colon           | After colon | Example           |
|-----------------------------------------|------------------------|-------------|-------------------|
| Static methods                          | Class name             | Method name | Collections::sort |
| Instance methods on a particular object | Instance variable name | Method name | str::startsWith   |
| Instance methods on a parameter         | Class name             | Method name | String::isEmpty   |
| Constructor                             | Class name             | new         | ArrayList::new    |


## Number of Parameters in a Method Reference

- We mentioned that a method reference can look the same even when it will behave differently based on the surrounding
context. For example, given the following method:

```
public class Penguin {
  public static Integer countBabies(Penguin... cuties){
    return cuties.length;
  }
}
```

- We show three ways that Penguin::countBabies can be interpreted. This method allows you to pass zero or more values
and creates an array with those values.

```
10: Supplier<Integer> methodRef1 = Penguin::countBabies;
11: Supplier<Integer> lambda1 = () -> Penguin.countBabies(x);
12:
13: Function<Penguin, Integer> methodRef2 = Penguin::countBabies;
14: Function<Penguin, Integer> lambda2 = (x) -> Penguin.countBabies(x);
15:
16: BiFunction<Penguin, Penguin, Integer> methodRef3 = Penguin::countBabies;
17: BiFunction<Penguin, Penguin, Integer> lambda3 = (x, y) -> Penguin.countBabies(x, y);
```

- Lines 10 and 11 do not take any parameters because the functional interface is a Supplier.
- Line 13 and 14 take one parameter
- Line 16 and 17 take two parameters. 
- All six lines return an Integer from the method reference or lambda.
- There's nothing special about zero, one, and two parameters. If we had a functional interface with 100 parameters of
type Penguin and the final one of Integer, we could still implement it with Penguin::countBabies.


## Using Wrapper Classes

- From 1Zo-815 studies, you should remember that each Java primitive has a corresponding wrapper class, shown in
Table 3.3 - With autoboxing, the compiler automatically converts a primitive to the corresponding wrapper. Unsurprisingly,
unboxing is the process in which the compiler automatically converts a wrapper class back to a primitive.

| Primitive Type | Wrapper class | Example of initializing    |
|----------------|---------------|----------------------------|
| boolean        | Boolean       | Boolean.valueOf(true)      |
| byte           | Byte          | Byte.valueOf((byte) 1)     |
| short          | Short         | Short.valueOf((short) 1)   |
| int            | Integer       | Integer.valueOf(1)         |
| long           | Long          | Long.valueOf(1)            |
| float          | Float         | Float.valueOf((float) 1.0) |
| double         | Double        | Double.valueOf(1.0)        |
| char           | Character     | Character.valueOf('c')     |


- Can you spot the autoboxing and unboxing in this example?

```
12: Integer pounds = 120;
13: Character letter = "robot".charAt(0);
14: char r = letter;
```

- Line 12 is an example of autoboxing as the int primitive is autoboxed into an Integer object. Line 13 demonstrates 
that autoboxing can involve methods. The charAt() method returns a primitive char. It is then autoboxed into the 
wrapper object Character. Finally, line 14 shows an example of unboxing. The Character object is unboxed into a primitive
char.

- There are two tricks in the space of autoboxing and unboxing. The first has to do with null values. This innocuous-
looking code throws an exception:

```
15: var heights = new ArrayList<Integer>();
16: heights.add(null);
17: int h = heights.get(0); //NullPointerException
```

- Also, be careful when autoboxing into Integer. What do you think this code outputs?

i.e: chapter_3.wrapperclasses.AutoBoxing.java


## Using the Diamond Operator

- In the past, we would write code using generics like the following:

```
List<Integer> list = new ArrayList<Integer>();
Map<String, Integer> map = new HashMap<String, Integer>();
```

- You might even have generics that contain other generics, such as this:

```
Map<Long,List<Integer>> mapLists = new HashMap<Long, List<Integer>>();
```

- The diamond operator, <>, was added to the language. The diamond operator is a shorthand notation that allows you to 
omit the generic type from the right side of a statement when the type can be inferred. It is called the diamond operator
because <> looks like a diamond. Compare the previous declaration with these new, much shorter versions:

```
 List<Integer> list = new ArrayList();
 Map<String, Integer> map = new HashMap<>();
 Map<Long, List<Integer>> mapOfLists = new HashMap<>();
```

The first is the variable declaration and fully specifies the generic type. The second is an expression that infers the
type from the assignment operator, using the diamond operator. To the compiler, both these declarations and our previous 
ones are equivalent. To us, though, the latter is a lot shorter and easier to read.

The diamond operator cannot be used as the type in a variable declaration. It can be used only on the right side of an 
assignment operation.

```
List<> list = new ArrayList<Integer>();     // DOES NOT COMPILE
Map<> map = new HashMap<String, Integer>(); // DOES NOT COMPILE
class InvalidUse {
  void use(List<> data) {}                  // DOES NOT COMPILE
}
```

- Since var is new to Java, let's look at the impact of using var with the diamond operator. Do you think these two 
statements compile and are equivalent?

```
var list = new ArrayList<Integer>();  // CREATE AN INTEGER ARRAY
var list = new ArrayList<>();         // CREATE AN OBJECT ARRAY
```


## Using Lists, Sets, Maps, and Queues

- A collection is a group of objects contained in a single object. 
- The Java Collections Framework is a set of classes in java.util for storing collections.
- There are 4 main interfaces in the Java Collections Framework.
  - List: A list is an ordered collection of elements that allows duplicate entries. Elements
  in a list can be accessed by an int index.
  - Set: A set is a collection that does not allow duplicate entries.
  - Queue: A queue is a collection that orders its elements in a specific order for processing.
  A typical queue processes its elements in a first-in, first-out order, but other ordering are possible
  - Map: A map is a collection that maps keys to values, with no duplicate keys allowed. The elements in a map
  are key/value pairs.

### Figure 3.1 shows the Collection interface

![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_3/z_images/Figure_3_1.png?raw=true)






















