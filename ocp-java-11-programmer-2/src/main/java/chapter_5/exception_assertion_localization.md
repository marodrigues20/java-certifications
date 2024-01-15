# Chapter 5 - Exception, Assertions, and Localization

- Exception Handling and Assertions
  - Use the try-with-resources construct
  - Create and use custom exception classes
  - Test invariants by using assertions
- Localization
  - Use the Locale class
  - Use resource bundles
  - Format messages, dates, and numbers with Java


- This chapter is about creating applications that adapt to change.
- How do we build applications that can support multiple languages or geographic regions?
- In this chapter, we will discuss these problems and solutions to them using exceptions,
  assertions, and localization.
- One way to make sure your applications respond to change is to build in support early on.
- For example, ***supporting localization doesn't mean you actually need to support multiple
  languages right away.*** It just means your application can be more early adapted in the future.
- By the end of this chapter, we hope we've provided structure for designing applications that better adapt to change


# Reviewing Exceptions

- An exception is Java's way of saying, "I give up. I don't know what to do right now." You deal with it."
- When you write a method, you can either deal with the exception or make it the calling code's problem.


# Handling Exceptions

- A try statement is used to handle exceptions. It consists of a try clause, zero or more catch clauses to handle the 
  exceptions that are thrown, and an optional finally clause, which runs regardless of whether an exception is thrown.
- Figure 5.1 shows the syntax of a try statement.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_5/images/Figure_5_1.png?raw=true)


- A traditional try statement must have at least one of the following:
- a catch block or a finally block. It can have more than one catch block, including multi-catch blocks, but at most 
  one finally block.


## Tip: Swallowing an exception
  - Swallowing an exception is when you handle it with an empty catch block.
  - When presenting a topic, we often do this to keep things simple.
  - Please, never do this in practice! Oftentimes, it is added by developers who do not want to handle or declare an 
    exception properly and can lead to bugs in production code.


## Try-with-resources
- You can also create a try-resources statement to handle exceptions.
- A try-with-resources statement looks a lot like a try statement, except that it includes a list of resources inside a 
  set of parentheses, (). These resources are automatically closed in the reverse order that they are declared at the 
  conclusion of the try clause.
- The syntax of the try-with-resources statement is presented in Figure 5.2.



![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_5/images/Figure_5_2.png?raw=true)


- Did you notice we used var for the resource type?
- While var is not required, it is convenient when working with streams, database objects, and especially generics,
  whose declarations can be lengthy.


## Distinguishing between throw and throws

- By now, you should know the difference between ***throw*** and ***throws***.
- The ***throw*** keyword means an exception is actually being thrown, while the throws keyword indicates that the 
  method merely has the potential to throw that exception.

```
10: public String getDataFromDatabase() throws SQLException {
11:  throw new UnsupportedOperationException();
12: }
```


## Examining Exception Categories

- In Java, all exceptions inherit from Throwable, although in practice, the only ones you should be handling or declaring
  extend from the Exception class.
- Figure 5.3 reviews the hierarchy of the top-level exception classes.


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_5/images/Figure_5_3.png?raw=true)


- To begin with, a checked exception must be handled or declared by the application code where it is thrown.
- The handle or declare rule dictates that a checked exception must be either caught in aa catch block or thrown to the 
  caller by including it in the method declaration.
- In Java, all exceptions that inherit Exception but not RuntimeException are considered checked exceptions.
- On the other hand, an unchecked exception does not be handled or declared.
- Unchecked exception are often referred to as runtime exception, although in java unchecked exception include any class
  that inherits RuntimeException or Error. An ***Error*** is fatal, and it is considered a poor practice to catch it.

  
- You need to memorize whether the exceptions are checked or unchecked exceptions.

- Table 5.1 list the unchecked exceptions that inherit RuntimeException that you should be familiar with for the exam.

### Table 5.1 Unchecked exceptions

| ArithmeticExceptions     | ArrayIndexOutOfBoundException |
|--------------------------|-------------------------------|
| ArrayStoreException      | ClassCastException            |
| IllegalArgumentException | IllegalStateException         |
| MissingResourceException | NullPointerException          |
| NumberFormatException    | UnsupportedOperationException |

- Table 5.2 presents the checked exceptions you should also be familiar with.

### TABLE 5.2 Checked exceptions

| FileNotFoundException    | IOException    |
|--------------------------|----------------|
| NotSerializableException | ParseException | 
| SQLException             | Not Applicable |

## Inheriting Exception Classes

- When evaluating catch blocks, the inheritance of the exception types can be important.
- For the exam, you should know that NumberFormatException inherits from IllegalArgumentException.
- You should also know that FileNotFoundException and NotSerializableException both inherit from IOException.

- This comes up often in multi-catch expression. For example, why does the following not compile?

```
try{
  throw new IOException();
} catch (IOException | FileNotFoundException e) {} // DOES NOT COMPILE  
```

- Since FileNotFoundException is a subclass of IOException, listing both in a multi-catch expression is redundant, 
  resulting in a compilation error.

- Ordering of exceptions in consecutive catch blocks matters too. Do you understand why the following does not compile?

```
try{
  throw new IOException();
} catch (IOException e) {
} catch (FileNotFoundException e) {} // DOES NOT COMPILE
```

- For the exam, remember that trying to catch a more specific exception (after already catching a broader exception) 
  results in unreachable code and a compiler error.



## Creating Custom Exceptions

- Java provides many exception classes out of the box. Sometimes, you want to write a method with a more specialized
  type of exception. You can create own exception class to do this.

## Declaring Exceptions Classes

- When creating your own exception, you need to decide whether it should be a checked or unchecked exception.
- While you can extend any exception class, it is most common to extend Exception (for checked) or RuntimeException
  (for unchecked).


- Creating your own exception class is really easy. Can you figure out whether the exception are checked or unchecked
  in this example?

```
1: class CannotSwimException extends Exception {}
2: class DangerInTheWater extends RuntimeException {}
3: class SharkInTheWaterException extends DangerInTheWater {}
4: class Dolphin {
5:    public void swim() throws CannotSwimException {
6:        // logic here
7:    }
8: }
```

- The method implementation could be written to actually throw it or not. 
- The method implementation could also be written to throw a SharkInTheWaterException,
  and ArrayIndexOutOfBoundsExceptions, or any other runtime exception.


## Adding Custom Constructors

- Let's see how to pass more information in your exception.
- The following example shows the three most common constructors defined by the Exception class:

```
public class CannotSwimException extends Exception {
  public CannotSwimException() {
    super();
  }
  public CannotSwimException(Exception e){
    super(e)
  }
  public CannotSwimException(String message) {
    super(message);
  }
}
```

Note: Remember that the default no-argument constructor is provided automatically if you don't write any constructor of
      your own.


- In these examples, our constructors and parent constructors took the same parameters, but these is certainly not 
  required. For example, the following constructor takes an Exception and calls the parent constructor that takes a 
  String:


```
public CannotSwimException(Exception e) {
  super("Cannot swim because: " + e.toString());
}
```

- Using a different constructor allows you to provide more information about what went wrong.
- For example, let's say we have a main() method with the following line:

```
15: public static void main(String[] unused) throws Exception {
16:     throw new CannotSwimException();
17: }
```

The output for this method is as follow:

```
Exception in thread "main" CannotSwimException
  at CannotSwimException.main(CannotSwimException.java:16)
```

- The JVM give us just the exception and its location. Useful, but we could get more.
- Now, let's change the main() method to include some text, as shown here:

```
15: public static void(String[] unused) throws Exception {
16:   throw new CannotSwimException("broken fin");
17: }
```

- The output of this new main() method is as follow:

```
Exception in thread "main" CannotSwimException: broken fin
  at CannotSwimException.main(CannotSwimException.java:16)
```

- This time we see the message text in the result. You might want to provide more information about the exception
  depending on the problem.
- We can even pass another exception, if there is an underlying cause for the exception.
- Take a look at this version of our main() method:

```
15: public static void main(String[] unused) throws Exception {
16:   throw new CannotSwimException(
17:     new FileNotFoundException("Cannot find shark file"));
18: }
```

- This would yield the longest output so far:

```
Exception in thread "main" CannotSwimException:
    java.io.FileNotFoundException: Cannot find shark file
    at CannnotSwimException.main(CannotSwimException.java:16)
  Caused by: java.io.FileNotFoundException: Cannot find shark file
     ... 1 more
```

## Printing Stack Traces

- The errors messages that we've been showing are called stack traces.
- A stack trace shows the exceptions along with the method calls it took to get there.
- The JVM automatically prints a stack trace when an exception is thrown that is not handled by the program.

- You can also print the stack trace on your own.
- The advantage is that you can read or log information from the stack trace and then continue to handle or 
even rethrow it.


```
try {
  throw new CannotSwimException();
} catch (CannotSwimException e) {
  e.printStackTrace();
}
```

## Automating Resource Management













