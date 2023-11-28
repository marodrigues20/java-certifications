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

- Notice that Map doesn't implement the Collection interface. It is considered part of the Java Collections Framework, 
even though it isn't technically a Collection. It's because the maps need different methods due to being key/value pairs.


## Common Collection Methods

- In this section, we use ArrayList and HashSet as our implementation classes, but they can apply to any class that 
inherits the Collection interface. We'll cover the specific properties of each Collection class in the next section.

### add() 

- The add() method inserts a new element into the Collection and returns whether it was successful. The method signature
is as follows:

  - boolean add(E element)

- The Collections Framework uses generics. You will see E appear frequently. It means the generic type that was used to
create the collection.

- i.e: chapter_3.commoncollections.CommonCollectionMethods.java


### remove()

- The remove() method removes a single matching value in the Collection and returns whether it was successful. The 
method signature is a follows:

- boolean remove(Object object)

- This time, the boolean return value tell us whether a match was removed

- i.e: chapter_3.commoncollections.CommonCollectionMethods.java

- If we call remove() on a List with an int uses the index, an index that doesn't exist will throw an exception. 
For example, birds.remove(100); throws an IndexOutOfBoundsException. Remember that there are overloaded remove() methods.
One takes the element to remove. The other takes the index of the element to remove.

- Deleting while Looping

```
Collection<String> birds = new ArrayList<>();
birds.add("hawk");
birds.add("hawk");
birds.add("hawk");

for(String bird: birds) // ConcurrentModificationException
  birds.remove(bird);
```

- Wait a minute. Concurrent modification? We don't get to concurrency until Chapter 7. That's right. It is possible to 
get a ConcurrentModificationException without threads. This is Java's way of complaining that you are trying to modify 
the list while looping through it. In Chapter 7, we'll return to this example and show how to fix it with the 
CopyOnWriteArrayList class.


### isEmpty() and size()

- The isEmpty() and size() methods look at how many elements are in the Collection. The method signatures are as follow:


```
boolean isEmpty()
int size()
```

- The following show how to use these methods:

```
chapter_3.commoncollections.EmptyAndSize.java
```


### clear()

- The clear() method provides an easy way to discard all elements of the Collection. The method signature is as follows:

```
void clear()
```

- The following shows how to use this method:

```
chapter_3.commoncollections.CleanCollection.java
```

### contains()

- The contains() method checks whether a certain value is in the Collection. The method signature is as follows:

```
boolean contains(Object object)
```

- The following shows how to use this method:

```
chapter_3.commoncollections.ContainsCollection.java
```

- The contains() method calls equals() on elements of the ArrayList to see whether there are any matches.

## removeIf()

- The removeIf() method all elements that match a condition. We can specify what should be deleted using a block of code
or even a method reference.
- The method signature looks like the following. (We will explain wht the ? super means in the "Working with Generics" 
section later in this chapter.)

```
boolean removeIf(Predicate<? super E> filter)
```

- It uses a Predicate, which takes one parameter and returns a boolean. Let's take a look at an example:

```
chapter_3.commoncollections.RemoveIfCollection.java (collectionArrayList())
```

- Let's try one more example that does use a method reference.


```
chapter_3.commoncollections.RemoveIfCollection.java (collectionSet())
```

### forEach()

- Looping through a Collection is common. There's also a forEach() method that you can call on a Collection. It uses a 
Consumer that takes a single parameter and doesn't return anything. The method signature is a follows:

```
 void forEach(Consumer<? super T> action)
```

- Cats like to explore, so let's print out two of them using both method references and streams.

```
chapter_3.commoncollections.ForEachCollection.java
```

## Using the List Interface

- You use a list when you want an ordered collection that can contain duplicate entries. Items can be retrieved and 
inserted at specific positions in the list based on an int index much like an array. Unlike an array, though, many List
implementation can change in size after they are declared.

- Figure 3.2 shows how you can envision a List. Each element of the list has an index, and the indexes begin with zero.

![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_3/z_images/List_Interface.png?raw=true)

- The main thing that all List implementations have in common is that they are ordered and allow duplicates.

### Comparing List Implementations

#### ArrayList
- The main benefit of an ArrayList is that you can look up any element in constant time.
- Adding or removing an element is slower than accessing an element.
- ArrayList a good choice when you are reading more often than(or the same amount as) writing to the ArrayList.


#### LinkedList

- A LinkedList is special because it implements both List and Queue.
- It has all the methods of a List.
- It also has additional methods to facilitate adding or removing from the beginning and/or end of the list.
- The main benefits of a LinkedList are that you can access, add, and remove from the beginning and end of the list in
  constant time.
- The trade-off is that dealing with an arbitrary index linear time.
- LinkedList a good choice when you'll be using it as Queue.
- As you saw in Figure 3.1, a LinkedList implements both the List and Queue interface.


### Creating a List with a Factory

- When you create a List of type ArrayList or LinkedList, you know the type. They are a few special methods where you
get a List back but don't know the type. These methods let you create a List including data in one line using a factory
method. This is convenient, especially when testing.

- TABLE 3.4 Factory methods to create a List

| Method                   | Description                                                       | Can add elements? | Can replace element? | Can delete elements? |
|--------------------------|-------------------------------------------------------------------|-------------------|----------------------|----------------------|
| Arrays.asList(varargs)   | Returns fixed size list backed by an array                        | No                | Yes                  | No                   |
| List.of(varargs)         | Returns immutable list                                            | No                | No                   | No                   |
| List.copyOf(collection)  | Returns immutable list with copy of original collection's values  | No                | No                   | No                   |


- Let's take a look at an example of these three methods.

```
i.e: chapter_3.commoncollections.factorymethods.FactoryMethods.java
```

### Working with List Methods

- The methods in the List interface are for working with indexes. In addition to the inherited Collection methods, the
method signatures that you need to know are in Table 3.5

TABLE 3.5 List methods

| Method                               | Description                                                                                                                        |
|--------------------------------------|------------------------------------------------------------------------------------------------------------------------------------|
| boolean add(E element)               | Adds element to end (available on all Collection APIs)                                                                             |
| void add(int index, E element        | Adds element at index  and moves the rest toward the end                                                                           |
| E get(int index                      | Returns element at index                                                                                                           |
| E remove(int index)                  | Removes elements at index and moves the rest towards the front                                                                     |
| void replaceAll(UnaryOperator<E> op> | Replaces each element in the list with the result of the operator                                                                  |
| E set(int index, E e)                | Replaces element at index and returns original. Throws IndexOutOfBoundsException if the index is larger than the maximum one set   |

- The following statement demonstrate most of these methods for working with a List:

```
i.e: chapter_3.commoncollections.ListInterfaceMethods
```

- The output would be the same if you tried these examples with LinkedList. Although the code would be less efficient,
it wouldn't be noticeable until you have very large lists.

- Let's look at using the replaceAll() method. It takes a UnaryOperator that takes one parameter and returns a value of
the same type.

```
i.e: chapter_3.commoncollections.ListInterfaceMethods

List<Integer> numbers = Arrays.asList(1,2,3);
numbers.replaceAll(x -> x*2);
System.out.println(numbers); // [2, 4, 6]
```

- This lambda doubles the value of each element in the list. The replaceAll() method calls the lambda on each element
of the list and replaces the value at that index.


## Using the Set Interface

- You use a set when you don't want to allow duplicate entries.
- You aren't concerned with the order in which you see these animals.

### Compering Set implementation


#### HashSet

- HashSet stores its elements in a hash table, which means the keys are a hash and the values are an Object. This means
that it uses the hashCode() method of the objects to retrieve them more efficiently.
- Adding Element and Check an element have a constant time.
- There is no order.

#### TreeSet

- A TreeSet stores its elements in a sorted tree structure.
- The main benefit is that the set is always in ordered order.
- The trade-off is that adding and checking whether an element exists take longer than with a HashSet, especially as 
 tree grows larger.

- Figures 3.4 shows how you can envision HashSet and TreeSet being stored.


#### Working with Set Methods

- Like list, you can create an immutable Set in one line or make a copy of an existing one.

```
Set<Character> letters = Set.of('z','o','o');
Set<Character> copy = Set.copyOf(letters);
```


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_3/z_images/Figure_3_4.png?raw=true)

- Those are the only methods you need to know for the Set interface for the exam! You have to know how sets behave with
respect to the traditional Collection methods. You also have to know the differences between the types of sets.

```
3: Set<Integer> set = new HashSet<>();
4: boolean b1 = set.add(66); //true
5: boolean b2 = set.add(10); //true
6: boolean b3 = set.add(66); //false
7: boolean b4 = set.add(8);  //true
8: set.forEach(System.out::println);
```
This code prints three lines:
66
8
10

- Now let's look at the same example with TreeSet.

```
3: Set<Integer> set = new TreeSet();
4: boolean b1 = set.add(66); //true
5: boolean b2 = set.add(10); //true
6: boolean b3 = set.add(66); //false
7: boolean b4 = set.add(8);  //true
8: set.forEach(System.out::println);
```

- This time, the code prints the following:

8
10
66

- The elements are printed out in their natural sorted order. Numbers implement the Comparator in Java, which is used for
sorting. Later in the chapter, you will learn how to create your own Comparable objects.

### Using the Queue Interface

- You use a queue when elements are added and removed in a specific order.
- A queue is assumed to be FIFO (first-in, first-out). (Some queue implementation change this to use a different order)
- The other format is LIFO (last-in, first-out), which is commonly referred to as a stack.

### Comparing Queue Implementation

- A double-ended queue is different from a regular queue in that you can insert and remove elements from both the front
and back of the queue.
- The main benefit of a LinkedList is that it implements both the List and Queue interfaces.
- The trade-off is that it isn't as efficient as a "pure" queue.
- You can use the ArrayDeque class (short for double-ended queue) if you need a more efficient queue. However, ArrayDeque
is not in scope for the exam.

### Working with Queue Methods

- The Queue interface contains many methods. Luckily, there are only six methods that you need to focus on.
These methods are shown in Table 3.6.

| Method             | Description                                                                      | Throws exception on failure |
|--------------------|----------------------------------------------------------------------------------|-----------------------------|
| boolean add(E e)   | Adds an element to the back of the queue and returns true or throws an exception | Yes                         |
| E element()        | Returns next element or throws an exception if empty queue                       | Yes                         |
| boolean offer(E e) | Adds an element to the back of the queue and returns whether successful          | No                          |
| E remove()         | Removes and returns next element or throws an exception if empty queue           | Yes                         |
| E poll()           | Removes and returns next element or returns null if empty queue                  | No                          |
| E peek()           | Returns next element or returns null if empty queue                              | No                          |

- As you can see, there are basically two sets of methods:
  - One set throws an exception when something goes wrong.
  - The other uses a different return value when something goes wrong.
  - The offer()/pool()/peek() methods are more common.

```
i.e: chapter_3.commoncollections.QueueInterfaceMethods.java
```


## Using the Map Interfaces

- You use a map when you want to identity values by a key.
- You don't need to know the names of the specific interfaces that the different maps implement, but you do need to 
  know that TreeMap is sorted.
- The main thing that all Map classes have in common is that they all have keys and values.


## Map.of() and Map.copyOf()

- Just like List and Set, there is a helper method to create a Map. You pass any number of pairs of keys and values.

```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (helperMethods)
Map.of("key1", "value1", "key2", "value2");
```

- Unlike, List and Set, this is less than ideal. Suppose you miscount and leave out a value.

```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (helperMethods)
Map.of("key1", "value1", "key2"); // INCORRECT
```

- This code compiles but throws an error at runtime. Passing keys and values is also harder to read because you have to 
keep track of which parameter is which. Luckily, there is a better way. Map also provides a method that lets you supply
key/value pairs.

```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (helperMethods)
Map<String, String> myMap2 = Map.ofEntries(
        Map.entry("key1", "value1"),
        Map.entry("key2", "value2")); //Better way to build a new map. Less error prone.
```

- If we leave out a parameter, the entry() method won't compile. Conveniently, Map.copyOf(map) works just like the List
and Set interface copyOf() methods.

## Comparing Map Implementations

### HashMap

- A HashMap stores the keys in a hash table. This means that it uses the hashCode() method of the keys to retrieve their
values more efficiently.
- The main benefit it that adding elements and retrieving the element by key both have constant time.
- The trade-off is that you lose the order in which you inserted the elements.
- If you concern with the order you should use LinkedHashMap, but that's not in scope for the exame.

### ThreeMap

- A TreeMap stores the keys in a sorted tree structure. The main benefit is that the keys are always in sorted order.
- Like a TreeSet, the trade-off is that adding and checking whether a key is present takes longer as the tree grows larger.

## Working with Map Methods

- Given that Map doesn't extend Collection, there are more methods specified on the Map interface.

### TABLE 3.7 Map methods

| Method                                            | Description                                                                                                  |
|---------------------------------------------------|--------------------------------------------------------------------------------------------------------------|
| void clear()                                      | Removes all keys and values from the map                                                                     |
| boolean containsKey(object key)                   | Returns whether key is in map                                                                                |
| boolean containsValue(Object value)               | Returns whether value is in map                                                                              |
| Set<Map.Entry<K,V>> entrySet()                    | Returns a Set of key/value pairs.                                                                            |
| void forEach(BiConsumer(K key, V value))          | Loop through each key/value pair.                                                                            |
| V get(Object key)                                 | Returns the value mapped by key or null if none is mapped.                                                   |
| V getOrDefault(Object key, V defaultValue)        | Returns the value mapped by the key or the default value if non is mapped.                                   |
| boolean isEmpty()                                 | Returns whether the map is empty.                                                                            |
| Set<K> keySet()                                   | Returns set of all keys.                                                                                     |
| V merge(K key, V value, Function(<V, V, V> func)) | Sets value if key not set. Runs the function if they key is set to determine the new value. Removes if null. |
| V put(K key, V value)                             | Adds or replaces key/value pair. Returns previous value or null.                                             |
| V putIfAbsent(K key, V value)                     | Adds value if key not present and returns null. Otherwise, returns existing value.                           |
| V remove(Object key)                              | Removes and returns value mapped to key. Returns null if none.                                               |
| V replace(K key, V value)                         | Replaces the value for a given key if the key is set. Returns the original value or null if none.            |
| void replaceAll(BiFunction<K, V, V> func          | Replaces each value with the results of the function.                                                        |
| int size()                                        | Returns the number of entries (key/value pairs) in the map.                                                  |
| Collection<V> values()                            | Returns Collection of all values.                                                                            |


## Basic Methods

- Let's start out by comparing the same code with two Map types. First up is HashMap.

### HashMap

```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (hashMapExample)

Map<String, String> map = new HashMap<>();
map.put("koala", "bamboo");
map.put("lion", "meat");
map.put("giraffe", "leaf");
String food = map.get("koala"); // bamboo
for(String key: map.keySet()){
    System.out.println(key + " "); // koala, giraffe, lion
}
```

- Java uses the hashCode() of the key to determine the order. The order here happens to not be
sorted order, or the order in which we typed the value.


### TreeMap

```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (treeMapExample)

//TreeMap order by Key not Value
Map<String, String> map = new TreeMap<>();
map.put("koala", "bamboo");
map.put("lion", "meat");
map.put("giraffe", "leaf");
String food = map.get("koala");
for(String key: map.keySet()){
    System.out.println(key + " "); // giraffe koala lion
```

- TreeMap sorts the keys as we would expect. If we were to have called values() instead of
  keySet(), the order of the values would correspond to the order of the keys.

- With our same map, we can try some boolean checks.

```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (treeMapExample)

//System.out.println(map.contains("lion")); //NOT COMPILE. Method contains is just in Collection interface.
System.out.println(map.containsKey("lion")); //true
System.out.println(map.containsValue("lion")); //false
System.out.println(map.size()); //3
map.clear();
System.out.println(map.size()); //0
System.out.println(map.isEmpty()); //0
```

### forEach() and entrySet()

- You saw the forEach() method earlier in the chapter. Note that it works a little different on a Map. This time, the 
lambda used by the forEach() method has two parameter; the key and the value. Let's look at an example, shown here:

```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (mapForEachExample)

Map<Integer, Character> map = new HashMap<>();
map.put(1,'a');
map.put(2,'b');
map.put(3,'c');
map.forEach((k,v) -> System.out.println(v));
```

- The lambda has both the key and values as the parameters. Interestingly, if you don't care about the key, this 
  particular code could have written with the values() method and a method reference instead.

```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (mapForEachExample)

map.values().forEach(System.out::println);
```

- Another way of going through all the data in a map is to get the key/value pairs in a Set. Java has a static interface 
  inside Map called Entry. It provides method to get the key and value of each pair.

```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (mapForEachExample)

map.entrySet().forEach( e -> System.out.println(e.getKey() + e.getValue()));
```

### getOrDefault()

- The get() method returns null if the requested key is not in map. Sometimes you prefer to have a different value
returned. Luckily, the getOrDefault() method makes this easy. 

```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (getOrDefaultExample)

3: Map<Character, String> map = new HashMap<>();
4: map.put('x', "spot");
5: System.out.println("X marks the " + map.get('x'));
6: System.out.println("X marks the " + map.getOrDefault('x',""));
7: System.out.println("Y marks the " + map.get('y'));
8: System.out.println("Y marks the " + map.getOrDefault('y',""));
```

This code prints the following:

```
X marks the spot
X marks the spot
Y marks the null
Y marks the
```

### replace() and replaceAll()

- These methods are similar to the Collection version except a key is involved.

```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (replaceExamples)

  Map<Integer, Integer> map = new HashMap<>();
  map.put(1, 2);
  map.put(2, 4);
  Integer original = map.replace(2, 10); // Replace 2 by 10. Return the value replaced
  System.out.println(map);
  map.replaceAll((k,v) -> k + v); // Sum key and value and replace the original value for each key.
  System.out.println(map); // {1=3, 2=12}
```

### putIfAbsent()

- The putIfAbsent method sets a value in the map but skips it if the value is already set to a non-null value.


```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (pullIfAbsentExamples)

Map<String, String> favorites = new HashMap<>();
favorites.put("Jenny", "Bus Tour");
favorites.put("Tom", null);
favorites.putIfAbsent("Jenny", "Tram"); // Not add because the Jenny has already existed.
favorites.putIfAbsent("Sam", "Tram"); // Added
favorites.putIfAbsent("Tom", "Tram"); //Added because the value was null
System.out.println(favorites); // {Tom=Tram, Jenny=Bus Tour, Sam=Tram}
```


### merge()

- The merge() method adds logic of what to choose. Suppose we want to choose the ride with the longest name. We can write 
code express this by passing a mapping function to the merge() method.

```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (mergeExamples)

11: BiFunction<String, String, String> mapper = (v1, v2) 
12:     -> v1.length() > v2.length() ? v1: v2;
13: 
14: Map<String, String> favorites = new HashMap<>();
15: favorites.put("Jenny","Bus Tour");
16: favorites.put("Tom", "Tram");
17:
18: String jenny = favorites.merge("Jenny", "Skyride", mapper);
19: String tom = favorites.merge("Tom", "Skyride", mapper);
20:
21: System.out.println(favorites); // {Tom=Skyride, Jenny=Bus Tour}
22: System.out.println(jenny);     // Bus Tour
23: System.out.println(tom);       // Skyride
```

- The code on line 11 and 12 take two parameters and returns a value. Our implementation returns the one with the longest
  name.
- The merge() method also has logic for what happens if null values or missing keys are involved. In this case, it doesn't 
  call the BiFunction at all, an it simply uses the new value.

```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (merge2Examples)

BiFunction<String, String, String> mapper = 
   (v1, v2) -> v1.length() > v2.length() ? v1: v2;
Map<String, String> favorites = new HashMap<>();
favorites.put("Sam",null);
favorites.merge("Tom", "Skyride", mapper);
favorites.merge("Sam", "Skyride", mapper); // Not called otherwise we'll receive NullPointerException
System.out.println(favorites); // {Tom=Skyride, Sam=Skyride}
```

- Notice that the mapping function isn't called. If it were, we'd have a NullPointerException. The mapping function is 
  used only when there are two actual values to decide between.

- The final thing to know about merge() is what happens when the mapping function is called and returns null. The key is
removed from the map when this happens:

```
i.e: chapter_3.commoncollections.MapInterfaceMethods.java (merge4NullExample)

BiFunction<String, String, String> mapper = (v1, v2) -> null;
Map<String, String> favorites = new HashMap<>();
favorites.put("Jenny", "Bus Tour");
favorites.put("Tom", "Bus Tour");
favorites.merge("Jenny", "Skyride", mapper);
favorites.merge("Sam", "Skyride", mapper); 
System.out.println(favorites); // {Tom=Bus Tour, Sam=Skyride}
```

- Tom was left alone since there was no merge() call for that key. Sam was added since that key was not in the original
list. Jenny was removed because the mapping function returned null.


### TABLE 3.8 Behavior of the merge() method

| If the requested key        | And mapping function returns       | Then:                                                                           |
|-----------------------------|------------------------------------|---------------------------------------------------------------------------------|
| Has a null value in map     | N/A (mapping function not called   | Update key's value in map with value parameter.                                 |
| Has a non-null value in map | null                               | Remove key from map                                                             |
| Has a non-null value in map | A non-null value                   | Set key to mapping function result.                                             |
| Is not in map               | N/A (mapping function not called)  | Add key with value parameter to map directly withou calling mapping function.   |



## Comparing Collection Types

### TABLE 3.9 Java Collections Framework types

| Type  | Can contain duplicate elements? | Elements always ordered?        | Has keys and values? | Must add/remove in specific order? |
|-------|---------------------------------|---------------------------------|----------------------|------------------------------------|
| List  | Yes                             | Yes (by index)                  | No                   | No                                 |
| Map   | Yes (for values)                | No                              | Yes                  | No                                 |
| Queue | Yes                             | Yes (retrieved in defined order | No                   | Yes                                |
| Set   | No                              |  No                             | No                   | No                                 |


- Additionally, make sure you can fill in Table 3.10 to describe the types on the exam.

### TABLE 3.10 Collection attributes

| Type        | Java Collections Framework interface | Sorted? | Calls hashCode? | Calls compareTo? |
|-------------|--------------------------------------|---------|-----------------|------------------|
| ArrayList   | List                                 | No      | No              | No               |
| HashMap     | Map                                  | No      | Yes             | No               |
| HashSet     | Set                                  | No      | Yes             | No               |
| LinkedList  | List, Queue                          | No      | No              | No               |
| TreeMap     | Map                                  | Yes     | No              | Yes              |
| TreeSet     | Set                                  | Yes     | No              | Yes              |


- The exam expects you to know which data structures allow null values. The data structure that involve sorting do not 
null values.

### Note - Older Collection

- There are a few collections that are no longer on the exam that you might come across in older code. All three were 
early Java data structures you could use with threads. In Chapter 7, you'll learn about modern alternatives if you need a 
concurrent collection.

  - Vector: Implements List. If you don't need concurrency, use ArrayList instead.
  - Hashtable: Implements Map. If you don't need concurrency, use HashMap instead.
  - Stack: Implements Queue. If you don't need concurrency, use a LinkedList instead.

## Sorting Data

- We discussed "order" for the TreeSet and TreeMap classes.
- For numbers, order is numerical order
- For String objects, order is defined according to the Unicode characters mapping.
- Numbers sort before letters
- Uppercase letters sort before lowercase letters

- You can also sort objects that you create yourself. Java provides an interface called Comparable.
- There is also a class called Comparator, which is used to specify that you want to use a different order than the 
object itself provides.

## Creating a Comparable Class

- The Comparable interfaces has only one method. In fact, this is the entire interface:

```
public interface Comparable<T>{
  int compareTo(T o);
}
```

```
i.e: chapter_3.sort.comparable.Duck.java

import java.util.*;
public class Duck implements Comparable<Duck>{
  private String name;
  
 ...
 
 public int comparateTo(Duck d) {
    return name.compareTo(d.name); // sorts ascendingly by name
 }
 
}
```

- Without implementing that interface, all we have is a method named compareTo(), but it wouldn't be a Comparable object.
We could also implement Comparable<Object> or some other class for T, but his wouldn't be as useful for sorting a group
of Duck objects with each other.


- Finally, the Duck class implements compareTo(). Since Duck is comparing objects of type String and the String class
already has a compareTo() method, it can just delegate.
- We still need to know what the compareTo() method returns so that we can write our own. There are three rule to know.
  - The number 0 is returned when the current object is equivalent to the argument to compareTo().
  - A negative number (less than 0) is returned when the current object is smaller than the argument to compareTo().
  - A positive number (greater than 0) is returned when the current object is larger than the argument to compareTo().



- Let's look at an implementation of compareTo() that compares numbers instead of String objects.

```
i.e: chapter_3.sort.comparable.Animal.java
```

## Casting the compareTo() Argument

- When dealing with legacy code or code that does not use generics, the compareTo() method requires a cast since it is 
passed an Object.

```
public class LegacyDuck implements Comparable{
  private String name;
  
  public int compareTo(Object obj){
    LegacyDuck d = (LegacyDuck) obj; // cast because no generics
    return name.compareTo(d.name);
  }
}
```

- Since we don't specify a generic type for Comparable, Java assumes that we want an Object, which means that we have to
cast to LegacyDuck before accessing instance variables on it.

## Checking for null

- When writing your own compare methods, you should check the data before comparing it if is not validated ahead of time.

```
i.e: chapter_3.sort.comparable.MissingDuck.java
```

## Keeping compareTo() and equals() Consistent

- The compareTo() method returns 0 if two objects are equal, while your equals() method returns true if two objects are 
  equal. 
- A natural ordering that uses compareTo() is said to be consistent with equals if, x.equal(y) is true whenever 
  x.compareTo(y) equals 0;
- Similarly, x.equals(y) must be false whenever x.compareTo(y) is not 0. You are strongly encouraged to make your 
  Comparable classes are consistent with equals because not all collection classes behave predictably if the compareTo() and
  equals() methods are not consistent.

- For example, the following Product class defines a compareTo() method that is not consistent with equals:

```
chapter_3.sort.comparable.Product.java

public class Product implements Comparable<Product>{

    private int id;
    private String name;

    public int hashCode() {
        return id;
    }

    public boolean equals(Object obj){
        if(!(obj instanceof Product)) return false;
        var other = (Product) obj;
        return this.id == other.id;
    }
    @Override
    public int compareTo(Product o) {
        return this.name.compareTo(o.name);
    }
}
```

- You might be sorting Product objects by name, but names are not unique. Therefore, the return value of compareTo() 
  might not be 0 when comparing two equal Product objects, so this compareTo() method is not consistent with equals. 
  One way to fix that is to use a Comparator to define the sort elsewhere.


## Comparing Data with a Comparator

- Sometimes you want to sort an object that did not implement Comparable, or you want to sort objects in different ways 
at difference times. Suppose that we add weight to our Duck class. We no have the following:

```
i.e: chapter_3.sort.DuckPlus.java
```

- Comparator is a functional interface since there is only one abstract method to implement. This means that we can 
rewrite the comparator on lines 18-22 using lambda expression, as shown here:

```
Comprator<DuckPlus> byWeight = (d1, d2) -> d.getWeight() - d2.getWeight();
```

- Alternatively, we can use a method reference and a helper method to specify we want to sort by weight.

```
Comparator<DuckPlus> byWeight = Comparator.comparing(Duck::getWeight);
```

- In this example, Comparator.comparing() is a static interface method that creates a Comparator given a lambda 
expression or method reference. Convenient, isn't?

## Is Comparable a Functional Interface?

- We said that Comparator is a functional interface because it has a single abstract method. Comparable is also a 
functional interface since it also has a single abstract method. However, using a lambda for Comparable would be silly. 
The point of Comparable is to implement it inside the object being compared.

## Comparing Comparable and Comparator

- TABLE 3.11 Comparison of Comparable and Comparator

| Difference                                        | Comprable   | Comparator  |
|---------------------------------------------------|-------------|-------------|
| Package name                                      | java.lang   | java.util   |
| Interface must be implemented by class comparing? | Yes         | No          |
| Method name in interface                          | compareTo() | compare()   |
| Number of parameters                              | 1           | 2           |
| Common to declare using a lambda                  | No          | Yes         |


## Comparing Multiple Fields

- When writing a Comparator that compares multiple instance variables, the code gets a little messy. Suppose that we 
have a Squirrel class, as shown here:

```
i.e: chapter_3.sort.comparator.Squirrel.java

public class Squirrel {
    private int weight;
    private String species;
}
```

- We want to write a Comparator to sort by species name. If two squirrels are from the same species, we ant to sort the 
one that weights the last first. We could do this with code that looks like this:

```
i.e: java.util.Comparator.MultiFieldComparator.java

public class MultiFieldComparator implements Comparator<Squirrel> {
    @Override
    public int compare(Squirrel s1, Squirrel s2) {

        int result = s1.getSpecies().compareTo(s2.getSpecies());
        if(result != 0){
            return result;
        }else {
            return s1.getWeight() - s2.getWeight();
        }
    }
}
```

- Alternatively, we can use method references and build the comparator. This code represents logic for the same 
comparison.

```
i.e: chapter_3.sort.comparator.Test.java

//Comparing what's the grater using species after using weight.
        Comparator<Squirrel> c = Comparator.comparing(Squirrel::getSpecies)
                .thenComparingInt(Squirrel::getWeight);

//Using method reference but reverse the order. (asc / desc)
var c2 = Comparator.comparing(Squirrel::getSpecies).reversed();
```


- Table 3.2 shows the helper methods you should know for building Comparator. We've omitted the parameter types to keep
you focused on the methods. They are many of the functional interfaces you'll be learning about in the next chapter.

| Method                     | Description                                                                                            |
|----------------------------|--------------------------------------------------------------------------------------------------------|
| comparing(function)        | Compare by the result of a function that returns any Object (or object autoboxed into an Object).      |
| comparingDouble(function)  | Compare by the results of a function that returns a double.                                            |
| comparingInt(function)     | Compare by the result of a funtion that returns an int.                                                |
| comparingLong ( function ) | Compare by the result of a function that returns a long.                                               |
| naturalOrder()             | Sort using the order specified by the Comparable implementation on the object itself.                  |
| reverseOrder()             | Sort using the reverse of the order specified by the Comparable implementation on the object itself.   |


- Table 3.13 shows the methods that you can chain to a Comparator to further specify its behavior.

- TABLE 3.13 Helper default methods for building a Comparator.

| Method                       | Description                                                                                                                                |
|------------------------------|--------------------------------------------------------------------------------------------------------------------------------------------|
| reversed()                   | Reverse the order the chained Comparator.                                                                                                  |
| thenComparing(function)      | If the previous Comparator returns 0, use this comparator that returns an Object or can be autoboxed into one.                             |
| thenComparingDouble(funtion) | If the previous Comparator returns 0, use this comparator that returns a double. Otherwise, return the value from the previous Comparator. |
| thenComparatingInt           | If the previous Comparator returns 0, use this comparator that returns an int. Otherwise, return the value from the previous Comparator.   |
| thenComparingLong(function)  | If the previous Comparator returns 0, use this comparator that returns a long. Otherwise, return the value from the previous Comparator.   |


## Sorting and Searching

- The Collections.sort() method uses the compareTo() method to sort. It expects the object to be sorted to be Comparable.

```
public class SortRabbits {
    static class Rabbit { int id; }
    public static void main(String[] args){
        List<Rabbit> rabbits = new ArrayList<>();
        rabbits.add(new Rabbit());
        Collections.sort(rabbits); // DOES NOT COMPILE
    }
}
```

- Java knows that the Rabbit class is not Comparable. It knows sorting will fail, so it doesn't even let the code
compile. You can fix this by passing a Comparator to sort().
- Remember that a Comparator is useful when you want to specify sort order without using a compareTo() method.

```
i.e: chapter_3.sort.SortRabbits.java

public class SortRabbits {
    static class Rabbit { int id; }
    public static void main(String[] args){
        List<Rabbit> rabbits = new ArrayList<>();
        rabbits.add(new Rabbit());
        Comparator<Rabbit> c = (r1,r2) -> r1.id = r2.id;
        Collections.sort(rabbits, c);
    }
}
```

- The sort() and binarySearch() methods allow you to pass in a Comparator object when you don't want to use the natural
  order.


## Reviewing binarySearch()

- The binarySearch() method requires a sorted List.

```
11: List<Integer> list = Arrays.asList(6,9,1,8);
12: Collections.sort(list); // [1, 6, 8, 9]
13: System.out.println(Collections.binarySearch(list, 6)); // 1
14: System.out.println(Collections.binarySearch(list, 3)); // -2 
```

- Line 13 prints the index at which a match is found.
- Line 14 prints one less than the negated index of where the requested value would need to be inserted.
- The number 3 would need to be inserted at index 1 (after the number 1 but before the number 6). 
- Negating that give us -1, and subtracting 1 gives us -2.

- There is a trick in working with binarySearch(). What do you think the following outputs?

```
3: var names = Arrays.asList("Fluffy", "Hoppy");
4: Comparator<String> c = Comparator.reverseOrder();
5: var index = Collections.binarySearch(names, "Hoppy", c);
6: System.out.println(index);
```

- You do need to know that the answer is not defined.
- Line 3 creates a list, ["Fluffy", "Hoppy"]. This list happens to be sorted in ascending order.
- Line 4 creates a Comparator that reverses the natural order.
- Line 5 requests a binary search in descending order.
- Since the list is in ascending order, we don't meed the precondition for doing a search.



- Going back to our Rabbit that does not implement Comparable, we try to add it to a TreeSet.

```
i.e: chapter_3.sort.binarysearch.UseTreeSet.java

2: public class UseTreeSet {
3:    static class Rabbit { int id; }
4:    public static void main(String[] args) {
5:        Set<Duck> ducks = new TreeSet<>();
6:        ducks.add(new Duck("Puddles"));
7:
8:        Set<Rabbit> rabbits = new TreeSet<>();
9:        rabbits.add(new Rabbit()); // ClassCastException
10: } }
```

- Line 6 is fine. Duck does implement Comparable. TreeSet is able to sort it into the proper position in the set.
- Line 9 is a problem.
- When TreeSet tries to sort it, Java discovers the fact that Rabbit does not implement Comparable.
- Java throws an exception that looks like this.

```
Exception in thread "main" java.lang.ClassCastException: 
  class chapter_3.sort.binarysearch.UseTreeSet$Rabbit cannot be cast to class java.lang.Comparable
```

- It may seen weird for this exception to be thrown when the first object is added to the set. After all, there is
  nothing to compare yet. Java works this way for consistency.

- Just like searching and sorting, you can tell collections that require sorting that you want to use a specific
  Comparator, for example:

```
8: Set<Rabbit> rabbits = new TreeSet<>((r1, r2) -> r1.id - r2.id);
9: rabbits.add(new Rabbit());
```

- Now Java knows that you want to sort by id and all is well.

## Working with Generics

- Generics is useful to avoid if we have a list doesn't add anything inside it. 

```
14: static void printNames(List list) {
15:   for (int i = 0; i < list.size(); i++) {
16:    String name = (String) list.get(i); //ClassCastException
17:    System.out.println(name);
18:   }
19: }
20: public static void main(String[] args){
21:  List names = new ArrayList();
22:  name.add(new StringBuilder("Webby"))
23:  printNames(names);
24: }
```

- This code throws a ClassCastException. Line 22 adds a StringBuilder to list. This is legal because a nongeneric list
can contain anything. However, line 16 is written to expect a specific  class to be in there. It casts to a String,
reflecting this assumption.
- Generics fix this by allowing you to write and use parameterized types. You specify that you want an ArrayList of 
String  objects.


## Generic Classes

- You can introduce generics into your own classes. The sintax for introducing a generic is to declare a formal type
parameter in angle brackets.

```
public class Crate<T>{
  private T contents;
  public T emptyCrate() {
    return contents;
  }
  public void packCrate(T contents){
      this.contents = contents;
  }
}
```

## Naming Convention for Generics

- A type parameter can be named anything you want.
- The convention is to use single uppercase letters to make it obvious that they aren't real class names.
- The following are common letters to use.
  - E for an element
  - K for a map key
  - V for a map value
  - N for a number
  - T for a generic data type
  - S, U, V, and so forth for multiple generic types

- Generic classes aren't limited to having a single type parameter. This class shows two generic parameters.

```
i.e: chapter_3.generics.SizeLimitedCrate.java


public class SizeLimitedCrate<T, U>{
  private T contents;
  private U sizeLimit;
  public SizeLimitedCrate(T contents, U sizeLimit){
      this.contents = contents;
      this.sizeLimit = sizeLimit;
  }
}
```

- To use this generic class, we can write the following:

```
Elephant elephant = new Elephant();
Integer numPounds = 15_000;
SizeLimitedCrate<Elephant, Integer> c1
    = new SizeLimiteCrate<>(elephant, numPounds);
```

- Here we specify that the type is Elephant, and the unit is Integer.
- We also throw in a reminder that numeric literals can contain underscore.


## What Is Type Erasure?

- Specifying a generic type allows the compiler to enforce proper use of the generic type.
- Behind the scenes, the compiler replaces all references to T in Crate with Object.
- In other words, after the code compiles, your generics are actually just Object types.
- The Crate class looks like the following at runtime:

```
  public class Crate {
    private Object contents;
    public Object emptyCreate(){
        return contents;
    }
    public void packCrate(Object contents){
        this.contents = contents;
    }
  }
```

- This means there is only one class file. There aren't different copies for different parameterized types.
  (Some other languages work that way.)
- This process of removing the generics syntax from your code is referred to as type erasure.
- Type erasure allows your code to be compatible with older versions of Java that do not contain generics.

- The compiler adds the relevant casts for your code to work with this type of erased class.
- For example, you type the following:

```
  Robot r = crate.emptyCrate();
```

- The compiler turns it into the following:

```
  Robot r = (Robot) crate.emptyCrate();
```


## Generic Interfaces

- Just like a class, an interface can declare a formal type parameter. For example, the following Shippable interface 
  uses a generic type as the argument to its ship() method:

```
i.e: chapter_3.generics.interfaces.Shippable.java

public interface Shippable<T> {
    void ship(T t);
}
```

- There are three ways a class can approach implementing this interface. 
- The first is to specify the generic type in the class.
- The following concrete class says that it deals only with robots.
- This lets it declare the ship() method with a Robot parameter.

```
  class ShippableRobotCrate implements Shippable<Robot>{
    public void ship(Robot t) {}
  }
```

- The next way is to create a generic class. The following concrete class allows the caller to specify the type of the 
  generic:

```
  class ShippableAbstractCrate<U> implements Shippable<U>{
    public void ship(U t) { }
  }
```

- In this example the type parameter could have been named anything, including T.
- We used U in the example so that it isn't confusing as to what T refers to.
- The exam won't mind trying to confuse you by using the same type parameter name.

## Raw Types

- The final way is to now use generics at all.
- This is the old way of writing code. It generates a compiler warning about Shippable being a raw type, but it does 
  compile.
- Here the ship() method has an Object parameter since the generic type is not defined:

```
  class ShippableCrate implements Shippable{
    public void ship(Object t) { }
  }
```

## What You Can't Do with Generic Types

- There are some limitations on what you can do with a generic type. These aren't on the exam, but it will be helpful to
refer to this scenario when you are writing practice programs and run into one of these situations.
- Most of the limitation are due to type erasure. Oracle refers to types whose information is fully available at runtime
as reifiable. Reifiable types can do anything that Java allows. Nonreifiable types have some limiations.
- Here are the things that you can't do with generics (and by "can't," we mean without resorting to contortions like
passing  in a class object):


- Calling a constructor: Writing new T() is not allowed because at runtime it would be new Object().
- Creating an array of that generic type: This one is the most annoying, but it makes sense because you'd be creating
  an array of Object values.
- Calling instanceof: This is not allowed because at runtime List<Integer> and List<String> look the same to Java
  thanks to type erasure.
- Using a primitive type as a generic type parameter: This isn't a big deal because you can use the wrapper class 
  instead. If you want a type of int, just use Integer.
- Creating a static variable as a generic type parameter: This is not allowed because the type is linked to the 
  instance of the class.


## Generic Methods

- It is also possible to declare them on the method level. This is often useful for static methods since they aren't 
  part of an instance than can declare the type. However, it is also allowed on non-static methods.

```
i.e: chapter_3.generics.Handler.java

public class Handler {

    public static <T> void prepare(T t){
        System.out.println("Preparing " + t);
    }

    public static <T> Crate<T> ship(T t){
        System.out.println("Shipping " + t);
        return new Crate<T>();
    }
}
```
- The method parameter is the generic type T. Before the return type, we declare the formal type parameter of <T>. 
In the ship() method, we show how you can use the generic parameter in the return type, Crate<T>, for the method.
- Unless a method is obtaining the generic formal type parameter from the class/interface, it is specified immediately
before the return type of the method. This can lead to some interesting-looking code!

```
i.e: chapter_3.generics.More.java

2: public class More {
3:    public static <T> void sink(T t){ }
4:    public static <T> T identity(T t) { return t; }
5:    public static T noGood(T t){ return t; } // DOES NOT COMPILE
6: }
```

- Line 3 shows the formal parameter type immediately before the return type of void.
- Line 4 shows the return type being the formal parameter type. It looks weird, but it is correct.
- Line 5 omits the formal parameter type, and therefore it does not compile.

## Optional Syntax for Invoking a Generic Method

