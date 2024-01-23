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

> Note: Remember that the default no-argument constructor is provided automatically if you don't write any constructor of
>       your own.


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

- As previously described, a try-with-resources statement ensures that any resources declared in the try clause are 
  automatically closed at the conclusion of the try block.
- This feature is also known as *** automatic resources management***, because Java automatically takes care of closing 
  the resources for you.
- For the exam, a resource is typically a file a database that requires some kind of stream or connection to read or 
  write data.

### Resources Managament vs. Garbage Collection

- If an object connected to a resource is not closed, then the connection could remain open. In fact, it may interfere 
  with Java's ability to garbage collect the object.


## Constructing Try-With-Resources Statements

- What types of resources can be used with a try-with-resources statements?
  - try-with-resources statements require resources that implement the AutoCloseable interface.

```
try (String reptile = "lizard") {
}
```

Inheriting AutoCloseable requires implementing a compatible close() method.

```
interface AutoCloseable{
  public void close() throws Exception;
}
```

- The close() method can choose to throw Exception or a subclass, or not throw any exception at all.



>Note:
>  - In Chapter 8 and Chapter 9, you will encounter resources that implement Closeable, rather than AutoCloseable.
>  - Since Closeable extends AutoCloseable, they are both supported in try-with-resources statements.
>  - The only difference between the two is that Closeable's close() method declares IOException, while AutoCloseable's 
>    close() method declares Exception.


- Let's define our own custom resources class for use in a try-with-resources statement.

i.e: chapter_5.trywithresources.MyFileReader.java


```java
public class MyFileReader implements AutoCloseable{
    
    private String tag;
    
    public MyFileReader(String tag){
        this.tag = tag;
    }
    
    @Override
    public void close() throws Exception {
        System.out.println("Closed:" + tag);
    }
}
```

- The following code snipped makes use of our custom reader class:


i.e: chapter_5.trywithresources.MyReaderFileUse.java

```java
public class MyReaderFileUse {


    public static void main(String[] args) throws Exception {

        try (var bookReader = new MyFileReader("monkey")){
            System.out.println("Try Block");
        } finally {
            System.out.println("Finally Block");
        }
    }
}
```

- The code prints the following at runtime:

```
Try Block
Closed:monkey
Finally Block
```

- As you can see, the resources are closed at the end of the try statement, before any catch or finally block are
  executed.
- Behind the scenes, the JVM calls the close() method inside a hidden finally block, which we can refer to as the 
  implicit finally block. The finally block that the programmer declared can be referred to as the explicit finally block.

- Tip:
  - Remember that the resource will be closed at the completion of the try block, before any declared catch or finally 
    blocks execute.

- The second rule you should be familiar with is: a try-with-resources statement can include multiple resources, which 
  closed in the reverse order in which they are declared.
- Resources are terminated by a semicolon (;), with the last one being optional.

- Consider the following code snipped:

```java
package chapter_5.trywithresources;

public class MultipleResourcesUse {

    public static void main(String[] args) throws Exception {

        try ( var bookReader = new MyFileReader("1");
              var movieReader = new MyFileReader("2");
              var tvReader = new MyFileReader("3");){
            System.out.println("Try Block");
        } finally {
            System.out.println("Finally Block");
        }

    }
}
```
- When executed, this code prints the following:

```
Try Block
Closed:3
Closed:2
Closed:1
Finally Block
```

- The final rule you should is: resources declared within a try-with-resources statement are in scope only within the 
  try block.
- This is another way to remember that the resources are closed before any catch or finally blocks are executed, as the 
  resources are no longer available. Do you see why lines 6 and 8 don't compile in this example?


```
3: try (Scanner s = new Scanner(System.in)) {
4:  s.nextLine();
5: } catch(Exception e) {
6:    s.nextInt();  // DOES NOT COMPILE
7: } finally {
8:    s.nextInt();  // DOES NOT COMPILE
9: }
```

- The problem is that Scanner has gone out of scope at the end of the try clause.
- Lines 6 and 8 do not have access to it. 
- This is actually a nice feature. You can't accidentally use an object that has been closed.
- Resources do not need to be declared inside a try-with-resources statement, though, as we will see in the next section.


## Learning the New Effectively Final Feature

- Starting with Java 9, it is possible to use resources declared prior to the try-with-resources statement, provided 
  they are marked final or effectively final.

```java
package chapter_5.trywithresources;

public class MyFileReaderFinalUse {

    public void relax() throws Exception {
        final var bookReader = new MyFileReader("4");
        MyFileReader movieReader = new MyFileReader("5");
        try (bookReader;
            var tvReader = new MyFileReader("6");
            movieReader){
            System.out.println("Try Block");
        }finally {
            System.out.println("Finally Block");
        }
    }
}
```

- On execution, the code prints the following:

```
Try Block
Closed:5
Closed:6
Closed:4
Finally Block
```

- If you come across a question on the exam that uses a try-with-resources statement with a variable not declared
  in the try clause, make sure it is effectively final. For example, the following does not compile:

```
31: var writer = Files.newBufferedWriter(path);
32: try(writer) { // DOES NOT COMPILE
33:     writer.append("Welcome to the zoo!");
34: }
35: writer = null;
```

- The writer variable is reassigned on line 35, resulting in the compiler not considering it effectively final.
- Since it is not an effectively final variable, it cannot be used in a try-with-resources statement on line 32.

- The other place the exam might try to trick you is accessing a resource after it has been closed.
- Consider the following:

```
41: var writer = Files.newBufferedWriter(path);
42: writer.append("This write is permitted but a really bad idea!");
43: try(writer) {
44:   writer.append("Welcome to the zoo!");
45: }
46: writer.append("This write will fail!");   // IOException
```

- This code compiles but throws an exception on line 46 with the message Stream closed.
- While it was possible to write to the resource before the try-with-resources statement,
  it is not afterward.

## Take Care When Using Resources Declared before try-with-resources Statements

- On line 42 of the previous code example, we used writer before the try-with-resources statement.
- While this is allowed, it's a really bad idea. What happens if line 42 throws an exception?
- In this case, the resource declared on line 41 never be closed!
- What about the following snippet?

```
51: var reader = Files.newBufferedReader(path1);
52: var writer = Files.newBufferedWriter(path2); // Don't do this!
53: try (reader; writer) {}
```

- It has the same problem.
- If line 52 throws an exception, such as the file cannot be found, then the resource declared on line 51 will never
  be closed. We recommended you use this new syntax sapringly or with only one resource at a time.
- For example, if line 52 was removed, then the resource created on line 52 was removed, then the resource created on 
  line 51 wouldn't have an opportunity to throw an exception before entering the automatic resource management block.


## Understanding Suppressed Exceptions

- What happens if the close() method throws an exception?
- Let's try an illustrative example:

```java

package chapter_5.trywithresources;

public class TurkeyCage implements AutoCloseable {
  @Override
  public void close() throws Exception {
    System.out.println("Close gate");
  }

  public static void main(String[] args) throws Exception {
    try (var t = new TurkeyCage()) {
      System.out.println("Put turkeys in");
    }
  }
}

```

- If the TurkeyCage doesn't close, the turkeys could all escape.
- Clearly, we need to handle such a condition.
- We already know that the resources are closed before any programmer-coded catch blocks are run.
- This means we can catch the exception thrown by close() if we want.
- Alternatively, we can allow the caller to deal with it.

- Let's expand our example with a new JammedTurkeyCage implementation, show here:

```java
package chapter_5.trywithresources.supress_exception;

public class JammedTurkeyCage implements AutoCloseable{
    @Override
    public void close() throws IllegalArgumentException {
        throw new IllegalArgumentException("Cage door does not close");
    }

    public static void main(String[] args) {
        try(JammedTurkeyCage t = new JammedTurkeyCage()) {
            System.out.println("Put turkeys in");
        } catch (IllegalArgumentException e){
            System.out.println("Caught: " + e.getMessage());
        }
    }
}
```

- The closed() method is automatically called by try-with-resources. 
- It throws an exception, which is caught by our catch block and prints the following:

```
Put turkeys in
Caught: Cage door does not close
```

- This seems reasonable enough.
- What happens if the try block also throws an exception?
- When multiple exceptions are thrown, all but the first are called suppressed exceptions.
- The idea is that Java treats the first exception as the primary one and tacks on any that come up while automatically
  closing.

- What do you think the following implementation of our main() method outputs?

```java
package chapter_5.trywithresources.supress_exception;

public class JammedTurkeyCage_v2 implements AutoCloseable{

    @Override
    public void close() throws IllegalArgumentException {
        throw new IllegalArgumentException("Cage door does not close");
    }

    public static void main(String[] args) {
        try(JammedTurkeyCage_v2 t = new JammedTurkeyCage_v2()){
            throw new IllegalStateException("Turkeys ran off");
        }catch (IllegalArgumentException e){
            System.out.println("Caught: " + e.getMessage());
            for (Throwable t: e.getSuppressed())
                System.out.println("Suppressed: " + t.getMessage());
        }
    }
}
```

- Line 612 throws the primary exception.
- At this point, the try clause ends, and Java automatically calls the close() method.
- Line 607 of JammedTurkeyCage_v2 throws an IllegalStateException, which is added as a suppressed exception.
- Then line 613 the catches the primary exception.
- Line 614 prints the message for the primary exception.
- Lines 617 - 618 iterate through any suppressed exceptions and print them.
- The program prints the following:

```
Caught: Turkeys ran off
Suppressed: java.lang.IllegalArgumentException: Cage door does not close
```

- Keep in mind that the catch looks for matches on the primary exception.
- What do you think this code prints?

```java
package chapter_5.trywithresources.supress_exception;

public class JammedTurkeyCage_v3 implements AutoCloseable{

  @Override
  public void close() throws IllegalArgumentException {
    throw new IllegalArgumentException("Cage door does not close");
  }

  public static void main(String[] args) {
    try(JammedTurkeyCage_v3 t = new JammedTurkeyCage_v3()){
      throw new RuntimeException("Turkeys ran off");
    }catch (IllegalArgumentException e){
      System.out.println("Caught: " + e.getMessage());
    }
  }
}
```

- Line 650 again throws the primary exception.
- Java calls the close() method and adds a suppressed exception.
- Line 651 would catch the IllegalStateException.
- However, we don't have one of those.
- The primary exception is a RuntimeException.
- Since this does not match the catch clause, the exception is thrown to the caller.
- Eventually the main() method would output something like the following:

```
Exception in thread "main" java.lang.RuntimeException: Turkeys ran off
	at chapter_5.trywithresources.supress_exception.JammedTurkeyCage_v3.main(JammedTurkeyCage_v3.java:12)
	Suppressed: java.lang.IllegalArgumentException: Cage door does not close
		at chapter_5.trywithresources.supress_exception.JammedTurkeyCage_v3.close(JammedTurkeyCage_v3.java:7)
		at chapter_5.trywithresources.supress_exception.JammedTurkeyCage_v3.main(JammedTurkeyCage_v3.java:11)
```


- Java remembers the suppressed exceptions that go with a primary exception even if we don't handle them in the code.

>NOTE
>  - if more than two resources throw an exception, the first one to be thrown becomes the primary exception, with the 
>    rest being grouped as suppressed exceptions.
>  - And since resources are closed in reverse order in which they are declared, the primary exception would be on the 
>    last declared resource that throws an exception.


- Keep in mind that suppressed exceptions apply only to exceptions thrown in the try clause.
- The following example does not throw a suppressed exception:


```java
package chapter_5.trywithresources.supress_exception;

import chapter_1.interface_members.inheritance_duplicate.Run;

public class JammedTurkeyCage_v4 implements AutoCloseable{
    @Override
    public void close() throws Exception {

    }

    public static void main(String[] args) {
        try(JammedTurkeyCage_v4 t = new JammedTurkeyCage_v4()){
            throw new IllegalStateException("Turkeys run off");
        }finally {
            throw new RuntimeException("and we couldn't find them");
        }
    }
}
```

- Line 701 throws an exception.
- Then Java tries to close the resource and adds a suppressed to it.
- Now we have a problem.
- The finally block runs after all this.
- Since line 9 also throws an exception, the previous exception from line 7 is lost, with the code printing the following:


```
Exception in thread "main" java.lang.RuntimeException: and we couldn't find them
	at chapter_5.trywithresources.supress_exception.JammedTurkeyCage_v4.main(JammedTurkeyCage_v4.java:15)
```

- This has always been and continues to be bad programming practice.
- We don't want to lose exceptions!
- Although out of scope for the exam, the reason for this has to do with backward compatibility.
- Automatic resources management was added in Java 7, and this behaviour existed before this feature was added.


## Declaring Assertions

- An assertion is a boolean expression that you place at a point in your code where you expect something to be true.
- An assert statement contains this statement along with an optional message.
- An assertion allows for detecting defects in the code.
- You can turn on assertions for testing and debugging while leaving them off when your program is in production.
- It is true when everything is working properly.
- If the program has a defect, it might not actually be true.

## Real World Scenario - Assertions vs. Unit Tests

- Most developers are more familiar with unit test framework, such as JUnit, than with assertions.
- While there are some similarities, assertions are commonly used to verify the internal state of a program, while unit 
  tests are most frequently used to verify behaviour.


## Validating Data with assert Statement

- The syntax for an assert statement has two forms, shown in Figure 5.4

![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_5/images/Figure_5_4.png?raw=true)

- When assertions are enabled and the boolean expression evaluates to false, then an AssertionError will be thrown at 
  runtime.
- Since programs arent' supposed to catch an Error, this means that assertions failure are fatal and end program!
- Assertions may include optional parentheses and a message.
- For example, each of the following is valid:

```java
package chapter_5.assertions;

public class Syntax {

    public static void main(String[] args) {

        int age = 1;
        int height = 2;
        double lenght = 100.00;
        String name = "Cecelia";

        assert 1 == age;

        assert(2 == height);

        assert 100.0 == lenght : "Problem with length";

        assert ("Cecelia".equals(name)) : "Failed to verify user data";
        
    }
}
```

- When provided, the error message will be sent to the AssertionError constructor.
- It is commonly a String, although it can be any value.


- The three possible outcomes of an assert statement are as follows:
  - If assertions are disabled, Java skips the assertion and goes on in the code.
  - If assertions are enabled and the boolean expression is true, then our assertion has been validated and nothing
    happens. The program continues to execute in its normal manner.
  - If assertions are enabled and the boolean expression is false, then our assertion is invalid and an AssertionError 
    is thrown.


- Presuming assertions are enabled, an assertions is a shorter way of writing the following:
```
  if (! boolean_expression) throw new AssertionError(error_message);
```

- Let's try an example. Consider the following:

```java
package chapter_5.assertions;

public class Party {

    public static void main(String[] args) {
        int numGuests = -5;
        assert numGuests > 0;
        System.out.println(numGuests);
    }
}
```

- How to enable assertions by executing it using the single-file source code command, as shown here:

```
java -ea Party.java
```


- Uh-oh, we made a type in our Party class.
- We intended for there to be five guests and not negative five guests.
- The assertion on line 805 detects this problem.
- Java throws the AssertionError at this point.
- Line 806 never runs since an error was thrown.

```
Exception in thread "main" java.lang.AssertionError
	at chapter_5.assertions.Party.main(Party.java:7)
```

## Enabling Assertions

- By default, assert statement are ignored by the JVM at runtime.
- To enable assertions, use the -enableassertions flag on the command line.

```
java -enableassertions Rectangle
```

- You can also use the shortcut -ea flag.

```
java -ea Rectangle
```

- Using the -enableassertions or -ea flag without any arguments enables assertions in all classes (except system classes).
- You can also enable assertions for a specific class or package.
- For example, the following command enables assertions only for classes in the com.demos package and any subpackages:

```
  java -ea:com.demos... my.programs.Main
```

- The ellipsis (...) means any class in the specified package or subpackages.
- You can also enable assertions for a specific class.

```
  java -ea:com.demos.TestColors my.programs.Main
```

> TIP 
> Keep an eye out for questions that contain an assert statement where assertions are not enabled.


## Disabling Assertions

- Sometimes you want to enable assertions for the entire application but disable it for select packages or classes.
- Java offers the -disableassertions or -da flag for just such an occasion.
- The following command enables assertions for the com.demos package but disables assertions for the TestColors class:

```
java -ea:com.demos... -da:com.demos.TestColors my.programs.Main
```

For the exam, make sure you understand how to use the -ea and -da flags in conjunction with each other.

## Applying Assertions

- Table 5.3 list some of the common uses of assertions.
- You won't be asked to identify the type of assertion on the exam.
- This is just to give you some ideas of how they can be used.

---
**TABLE 5.3 Assertions applications**

| Usage                   | Description                                                                                                                      |
|-------------------------|----------------------------------------------------------------------------------------------------------------------------------|
| Internal invariants     | Assert that a valude is within a certain constraint, such as assert x < 0.                                                       |
| Class invariants        | Assert the validity of an object's state. Class invariants are typically private methods within the class that return a boolean. |
| Control flow invariants | Assert that a line of code you assume is unreachable is never reached.                                                           |
| Pre-conditions          | Assert that certain conditions are met before a method is invoked.                                                               |
| Post-conditions         | Assert that certain conditions are met after a method executes successfully                                                      |

---

## Writing Assertions Correctly

- One of the most important rules you should remember from this section is:
- assertions should never alter outcomes.
- This is especially true because asssertions can, should, and probably will be turned off in a production environment.


- For example, the following assertion is not a good design because it alters the value of a variable:

```
int x = 10;
assert ++x > 10; // Not a good design!
```

- Assertions are used for debugging purposes, allowing you to verify that something that you think is true during the 
  coding phase is actually true at runtime.


## Working with Dates and Times

- The older Java 8 certification exams required you to know a lot about the Date and Time API.
- This included knowing many of the various date/time classes and their various methods. How to specify amounts of time
  with the Period and Duration classes, and even how to resolve values across zones with daylight saving.
- For the Java 11 exam, none of those topics is in scope.
- For the Java 11 exam, you still need to know how to format dates.


## Understanding Date and Time Types

- Java includes numerous classes to model the examples in the previous paragraph.
- These types are listed in Table 5.4

---

**TABLE 5.4 Date and time types**

| Class                     | Description                               | Example                   |
|---------------------------|-------------------------------------------|---------------------------|
| java.time.LocalDate       | Date with day, month, year                | Birth date                |
| java.time.LocalTime       | Time of day                               | Midnight                  |
| java.time.LocalDateTime   | Day and time with no time zone            | 10 a.m next Monday        |
| java.time.ZonedDateTime   | Date and time with a specific time zone   | 9 a.m. EST on 2/20/2021   |
---

- Each of these types contain a static method called now() that allows you to get the current value.

```java
package chapter_5.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;

public class Table_5_4_Example {

    public static void main(String[] args) {
        System.out.println(LocalDate.now());
        System.out.println(LocalTime.now());
        System.out.println(LocalDateTime.now());
        System.out.println(ZonedDateTime.now());
    }
}
```

- Your output is going to depend on the date/time when you run it and where you live, although it should resemble the 
  the following:

```
2024-01-18
12:27:35.532435
2024-01-18T12:27:35.532478
2024-01-18T12:27:35.532840Z[Europe/London]
```

- The first line contains only a date
- The second line contains only a time 
- The third line contains both a date and a time. Java uses T to separate the date and time when converting LocalDateTime
  to a String
- The last line adds the time zone offset and time zone.


## Using the of() Method

- We can create some date and time values using the of() methods in each class.

```
LocalDate date1 = LocalDate.of(2020, Month.OCTOBER, 20);
LocalDate date2 = LocalDate.of(2020, 10, 20);
```

- Both pass in the year, month, and date.
- Although it is a good to use the Month constants (to make the code easier to read),
  you can pass the int number o the month directly.


> TIP
> Working with dates is one of the few times where it is expected for you to count from 1, just like in the real world


- When creating a time, you can choose how detailed you want to be. 
- You can specify just the hour and minute, or you can include the number of seconds.
- You can even include nanoseconds if you want to be very precise (a nanosecond is a billionth of a second).


```java
package chapter_5.datetime;

import java.time.LocalTime;

public class YourOwnTime {

    public static void main(String[] args) {

        LocalTime time1 = LocalTime.of(6, 15);          // hour and minute
        LocalTime time2 = LocalTime.of(6, 15, 30);      // + seconds
        LocalTime time3 = LocalTime.of(6, 15, 30, 200); // + nanoseconds
        
    }
}
```

- These three times are all different but within a minute of each other.
- You can combine dates and times in multiple ways.

```
var dt1 = LocalDateTime.of(2020, Month.OCTOBER, 20, 6, 15, 30);
    
LocalDate date = LocalDate.of(2020, Month.OCTOBER, 20);
LocalTime time = LocalTime.of(6, 15);
var dt2 = LocalDateTime.of(date, time);
```

## Formatting Dates and Times

- The date and time classes support many methods to get data out of them.

```java
package chapter_5.formating_dates_times;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterExample {

    public static void main(String[] args) {

        LocalDate date = LocalDate.of(2020, Month.OCTOBER, 20);
        System.out.println(date.getDayOfWeek());  //TUESDAY
        System.out.println(date.getMonth());      //OCTOBER
        System.out.println(date.getYear());       //2020
        System.out.println(date.getDayOfYear());  //294
    }
}
```


- Java provides a class called DateTimeFormatter to display standards formats.

```java
package chapter_5.formating_dates_times;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatterExample {

    public static void main(String[] args) {

        LocalDate date = LocalDate.of(2020, Month.OCTOBER, 20);
        LocalTime time = LocalTime.of(11, 12, 34);
        LocalDateTime dt = LocalDateTime.of(date, time);

        System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_DATE));
        System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_TIME));
        System.out.println(dt.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));

    }
}
```

- The code snippet prints the following:

```
2020-10-20
11:12:34
2020-10-20T11:12:34
```

- The DateTimeFormatter will throw an exception if it encounters an incompatible type.
- For example, each of the following will produce an exception at runtime since it attempts to format a date with a time
  value, and vice versa:

```
System.out.println(date.format(DateTimeFormatter.ISO_LOCAL_TIME));
System.out.println(time.format(DateTimeFormatter.ISO_LOCAL_DATE));
```

```
Exception in thread "main" java.time.temporal.UnsupportedTemporalTypeException: Unsupported field: Year
	at java.base/java.time.LocalTime.get0(LocalTime.java:710)
	at java.base/java.time.LocalTime.getLong(LocalTime.java:688)
	...
```


- if you don't want to use of the predefined formats, DateTimeFormatter supports a custom format using a date format
  String.

```
var f = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm");
System.out.println(dt.format(f)); // October 20, 2020 at 11:12
```

- M is used for Month, while y is used for year.
- Using m instead of M means it will return the minute of the hour, not the month of the year.
- Using M by itself outputs the minimum number of characters for a month, such as 1 for January, while using MM always 
  outputs two digits, such as 01.
- Furthermore, using MMM prints the three-letter abbreviation, such as Jul for July.
- MMMM prints full month name.


---
*** The Date and SimpleDateFormat Classes ***

The exam may include questions with the older date/time classes.
For example, the previous code snippet could be written using the java.util.Date and java.text.SimpleDateFormat classes.

```
  DateFormat s = new SimpleDateFormat("MMMM dd, yyyy 'at' hh:mm");
  System.out.println(s.format(new Date()));  // October 20, 2020 at 06:15
```

- For the exam, the rules for defining a custom DateTimeFormatter and SimpleDateFormat symbols are the same.
---


---
TABLE 5.5 Common date/time symbols

| Symbol | Meaning           | Examples                   |
|--------|-------------------|----------------------------|
| y      | Year              | 20, 2020                   |
| M      | Month             | 1, 01, Jan, January        |
| d      | Day               | 5, 05                      |
| h      | Hour              | 9, 09                      |
| m      | Minute            | 45                         |
| s      | Second            | 52                         |
| a      | a.m./p.m.         | AM, PM                     |
| z      | Time Zone Name    | Eastern Standard Time, EST |
| Z      | Time Zone Offset  | -0400                      |
---

- Let's try some examples. What do you think the following prints?

```java
package chapter_5.formating_dates_times;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatter_v2 {

    public static void main(String[] args) {

        var dt = LocalDateTime.of(2020, Month.OCTOBER, 20, 6, 15, 30);

        var formatter1 = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");
        System.out.println(dt.format(formatter1));

        var formatter2 = DateTimeFormatter.ofPattern("MM_yyyy_-_dd");
        System.out.println(dt.format(formatter2));

        var formatter3 = DateTimeFormatter.ofPattern("h:mm z");
        System.out.println(dt.format(formatter3));
    }
}
```

```
10/20/2020 06:15:30
10_2020_-_20
Exception in thread "main" java.time.DateTimeException: Unable to extract ZoneId from temporal 2020-10-20T06:15:30
```

- The third example throws an exception at runtime because the underlying LocalDateTime does not have a timezone 
  specified. If ZonedDateTime was used instead, then the code would have completed successfully and printed something
  like 06:15 EDT.

- As you saw in the previous example, you need to make sure the format String  is compatible with the underlying
  date/time type.

---
TABLE 5.6 shows which symbols you can use with each of the date/time objects.

| Symbol | LocalDate | LocalTime | LocalDateTime | ZonedDateTime |
|--------|-----------|-----------|---------------|---------------|
| y      | X         | -         | X             | X             |
| M      | X         | -         | X             | X             |
| d      | X         | -         | X             | X             |
| h      | -         | X         | X             | X             |
| m      | -         | X         | X             | X             |
| s      | -         | X         | X             | X             |
| a      | -         | X         | X             | X             |
| z      | -         | -         | -             | X             |
| Z      | -         | -         | -             | X             |

---

- Make sure you know which symbols are compatible with which date/time types.
- For example, trying to format a month for a LocalTime or an hour for a LocalDate will result in a runtime exception.


## Selecting a format() Method

- The date/time classes contain a format() method that will take a formatter, while the formatter classes contain a 
  format() method that will take a date/time value. The result is that either of the following is acceptable:

```java
package chapter_5.formating_dates_times;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class DateTimeFormatter_v3 {

    public static void main(String[] args) {


        var dateTime = LocalDateTime.of(2020, Month.OCTOBER, 20, 6, 15, 30);
        var formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss");

        System.out.println(dateTime.format(formatter)); // 10/20/2020 06:15:30
        System.out.println(formatter.format(dateTime)); // 10/20/2020 06:15:30
    }
}
```

- These statements print the same value at runtime. Which syntax you use is up to you.


## Adding Custom Text Value

- What if you want your format to include custom text values?
- If you just type it as a part of the format String, the formatter will interpret each character as a date/time symbol.
- In the best case, it will display weird data based on extra symbols you enter.
- In the worst case, it will throw an exception because the characters contain invalid symbols. 
- Neither is desirable!

### How to solve it in the best way

- You can escape the text by surrounding it with a pair of single quotes (').
- Escaping text instructs the formatter to ignore the values inside the single quotes and just insert them as part of 
  the final value.
- We saw this earlier with the 'at' inserted into the formatter.

```java
package chapter_5.formating_dates_times;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class CustomTexting {

    public static void main(String[] args) {

        var dt = LocalDateTime.of(2020, Month.OCTOBER, 20, 6, 15, 30);
        var f = DateTimeFormatter.ofPattern("MMMM dd, yyyy 'at' hh:mm");
        System.out.println(dt.format(f)); // October 20, 2020 at 06:15
    }
}
```

- But what if you need to display a single quote in the output too?
- Welcome to the fun of escaping characters!
- Java supports this by putting two single quotes next to each other.

```java
package chapter_5.formating_dates_times;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class CustomTexting {

    public static void main(String[] args) {

        var dt = LocalDateTime.of(2020, Month.OCTOBER, 20, 6, 15, 30);
        
        var g1 = DateTimeFormatter.ofPattern("MMMM dd', Party''s at' hh:mm");
        System.out.println(dt.format(g1)); // October 20, Party's at 06:15

        var g2 = DateTimeFormatter.ofPattern("'System format, hh:mm: 'hh:mm");
        System.out.println(dt.format(g2)); // System format, hh:mm: 06:15

        var g3 = DateTimeFormatter.ofPattern("'NEW! 'yyyy', yay!'");
        System.out.println(dt.format(g3)); // NEW! 2020, yay!
    }
}

```

- Without escaping the text values with single quotes, an exception will be thrown at runtime if the text cannot be 
  interpreted as a date/time symbol.

```
DateTimeFormatter.ofPattern("The time is hh:mm"); // Exception thrown
```

- This line throws an exception since T is an unknown symbol. 
- The exam might also present you with an incomplete escape sequence.

```
DateTimeFormatter.ofPattern("'Time is: hh:mm: ");  // Exception thrown
```


## Supporting Internationalization and Location


- Many applications need to work in different countries and with different languages.
- For example, consider the sentence "The zoo is holding a special event 4/1/15 to look at animal behaviours.
- "When is th event? In the United States, it is on April 1. However, a British reader might also wonder why we didn't 
  write "behaviours."
- If we are making a website or program that will be used in multiples countries, we want to use the correct language 
  and formatting.

- ***Internalionalization*** is the process of design your program so it can be adapted.
- This involves placing strings in a properties file and ensuring the proper data formatters are used.
- ***Localization*** means actually supporting multiple locales or geographic regions.
- You can think of a locale as being like a language and country pairing.
- Localization includes translating strings to different languages.
- It also includes outputting date and numbers in the correct format for that locale


> Note
> Initially, your program does not need to support multiple locales.
> The key is to future-proof your application by using these techniques.
> This way, when your product becomes successful, you can add support for new languages or regions without rewriting 
> everything.


- In this section, we will look at how to define a locale and use it to format dates, numbers, and strings.

## Picking a Locale

- The Locale class is in the java.util package.
- The first useful Locale to find is the user's current locale.
- Try running the following code on your computer:

```java
package chapter_5.locale;

import java.util.Locale;

public class Locale_v1 {

    public static void main(String[] args) {

        Locale locale = Locale.getDefault();
        System.out.println(locale); //en_GB
    }
}
```

- When we run it, it prints en_US.
- It might be different for you.
- This default output tells us that our computers are using English and are sitting in the United States.


- Notice the format.
- First comes the lowercase language code.
- The language is always required.
- Then comes an underscore followed by the uppercase country code.
- The country is optional. 
- Figure 5.5 shows the two formats for Locale objects that you are expected to remember


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_5/images/Figure_5_5.png?raw=true)


- As practice, make sure that you understand why each of these Locale identifiers is invalid:

```
US     // Cannot have country without language
enUS   // Missing underscore
US_en  // The country and language are reversed
EN     // Language must be lowercase
```

- The corrected version are en and en_US.


> Note
> You do not need to memorize language or country codes.
> The exam will let you know about any that are being used.
> You do need to recognize valid and invalid formats.
> Pay attention to uppercase/lowercase and the underscore.
> For example, if you see a locale expressed as es_CO, then you should know that the language is es and the country is CO,
> even if you didn't know they represent Spanish and Colombia, respectively.


- As a developer, you often need to write code that selects a locale other than the default one.
- There are three common ways of doing this. 
- The first is to use the built-in constants in the Locale class, available for some common locales.


```java
package chapter_5.locale;

import java.util.Locale;

public class LocaleSelected_v1 {

    public static void main(String[] args) {
        System.out.println(Locale.GERMAN); // de
        System.out.println(Locale.GERMAN); // de_DE
    }
}
```

- The first example selects the German language, which is spoken in many countries, including Austria(de_AT) 
  and Liechtenstein (de_LI).
- The second example selects both German the language and Germany the country.
- While these examples may look similar, they are not the same.
- Only one includes a country code.

- The second way of selecting a Locale is to use the constructors to create a new object.
- You can pass just a language, or both a language and country:

```java
package chapter_5.locale;

import java.util.Locale;

public class LocaleSelected_v2 {

    public static void main(String[] args) {
        System.out.println(new Locale("fr"));               // fr
        System.out.println(new Locale("hi", "IN")); // hi_IN
    }
}
```

- The first is the language French, and the second is Hindi in India.

- Java will let you create a Locale with an invalid language or country, such as xx_XX. 
- However, it will not match the Locale that you want to use, and your program will not behave as expected.


- There's a third way to create a Locale that is more flexible.
- The builder design pattern lets you set all of the properties that you care about and then build it at the end.
- This means that you can specify the properties in any order.
- The following two Locale values both represent en_US:

```java
package chapter_5.locale;

import java.util.Locale;

public class LocaleSelected_v3 {

    public static void main(String[] args) {
        
        Locale l1 = new Locale.Builder()
                .setLanguage("en")
                .setRegion("US")
                .build();

        Locale l2 = new Locale.Builder()
                .setRegion("US")
                .setLanguage("en")
                .build();
    }
}
```

- When testing a program, you might need to use a Locale other than the default of your computer.


````java
package chapter_5.locale;

import java.util.Locale;

public class LocaleSelected_v4 {

    public static void main(String[] args) {
        System.out.println(Locale.getDefault());   //en_GB
        Locale locale = new Locale("fr");
        Locale.setDefault(locale);               // change the default   
        System.out.println(Locale.getDefault()); // fr
    }
}
````

- The Locale changes for only that one Java Program.
- It does not change any settings on your computer.
- It does not even change future executions of the same program.


> Note
> The exam may use setDefault() because it can't make assumptions about where you are located.
> In practice, we rarely write code to change a user's default locale.


## Localizing Numbers

- It might surprise you that formatting or parsing currency and number values can change depending on your locale.
- For example, in the United States, the dollar sign is prepended before the value along with a decimal point vor values
  less than one dollar, such as $2.15. In Germany, though, the euro symbol is appended to the value along with a comma 
  for values less than one euro, such as 2,15 €.


- Luckily, the java.text package includes classes to save the day.
- The following sections cover how to format numbers, currency, and dates based on the locale.

- The first step to formatting or parsing data is the same: obtain an instance of a NumberFormat.
- Table 5.7 shows the available factory methods.


---
### TABLE 5.7 Factory methods to get a NumberFormat ###

| Description                              | Using default Locale and a specified Locale                                  |
|------------------------------------------|------------------------------------------------------------------------------|
| A general-purpose formatter              | NumberFormat.getInstance(); NumberFormat.getInstance(locale)                 |
| Same as getInstance                      | NumberFormat.getNumberInstance(); NumberFormat.getNumberInstance(locale)     |
| For formatting monetary amounts          | NumberFormat.getCurrencyInstance(); NumberFormat.getCurrencyInstance(locale) |
| For formatting percentages               | NumberFormat.getPercentInstance();  NumberFormat.getPercentInstance(locale)  |
| Rounds decimal values before displaying  | NumberFormat.getIntegerInstance(); NumberFormat.getIntegerInstance(locale)   |


- Once you have the NumberFormat instance, you can call format() to turn a number into a String, or you can use parse()
  to turn a String into a number.
---



> Tip
> The format classes are not thread-safe.
> Do not store them in instance variables or static variables.
> You'll learn more about thread safety in Chapter 7, "Concurrency"


## Formatting Numbers

- When we format data, we convert it from a structured object or primitive value into a String.
- The NumberFormat.format() method formats the given number based on the locale with the NumberFormat objects.

- Let's go back to our zoo for a minute
- For marketing literature, we want to share the average monthly number of visitors to the San Diego Zoo.
- The following shows priting out the same number in three different locales:

```java
package chapter_5.locale;

import java.text.NumberFormat;
import java.util.Locale;

public class FormattingNumbers_v1 {

    public static void main(String[] args) {

        int attendeesPerYear = 3_200_000;
        int attendeesPerMonth = attendeesPerYear / 12;

        var us = NumberFormat.getInstance(Locale.US);
        System.out.println(us.format(attendeesPerMonth)); // 266,666

        var gr = NumberFormat.getInstance(Locale.GERMANY);
        System.out.println(gr.format(attendeesPerMonth)); // 266.666

        var ca = NumberFormat.getInstance(Locale.CANADA_FRENCH);
        System.out.println(ca.format(attendeesPerMonth));  // 266 666
    }
}

```

- The output looks like this:

```
266,666
266.666
266 666
```

- This shows how our U.S, German, and French Canadian guests can all see the same information in the number format they
  accustomed to using.
- In practice, we would just call NumberFormat.getInstance() and rely on the user's default locale to format the output.
- Formatting currency works the same way.

```java
package chapter_5.locale;

import java.text.NumberFormat;

public class FormattingCurrency_v1 {

    public static void main(String[] args) {

        double price = 48;
        var myLocale = NumberFormat.getCurrencyInstance();
        System.out.println(myLocale.format(price));
    }
}
```

- When run with the default locale of en_US for the United States, it outputs $48.00.
- On the other hand, when run with the default locale of en_GB for Great Britain, it outputs £48.00.

> Note
> In the real world, use int or BigDecimal for money and not double.
> Doing math on amounts with double is dangerous because the values are stored as floating-point numbers.
> Your boss won't appreciate it if you lose pennies or fractions of pennies during transactions!


## Parsing Numbers

- When we parse data, we convert it from a String to a structured object or primitive value.
- The NumberFormat.parse() method accomplishes this and takes the locale into consideration.

- For example, if the locale is the English/United States (en_US) and the number contains commas, the commas are treated
  as formatting symbols.
- If the locale relates to a country or language that uses commas as a decimal separator, the comma is treated as a 
  decimal point.


> Note
> The parse() method, found in various types, declares a checked exception ParseException that must be handled or 
> declares a checked exception ParseException that must be handled or declared in the method in which they are called.


- Let's look at an example.
- The following code parses a discounted ticket prince with different locales. The parse() method throws a checked 
  ParseException, so make sure to handle or declare it in your own code.

```java
package chapter_5.locale;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ParsingNumbers_v1 {

    public static void main(String[] args) throws ParseException {

        String s = "40.45";

        var en = NumberFormat.getInstance(Locale.US);

        System.out.println(en.parse(s)); // 40.45

        var fr = NumberFormat.getInstance(Locale.FRANCE);
        System.out.println(fr.parse(s));  // 40
    }
}
```

- In the United States, a dot (.) is part of a number, and the number is parsed how you might expect.
- France does not use a decimal point to separate numbers.
- Java parses it as a formatting character, and it stops looking at the rest of the number.
- The lesson is to make sure that you parse using the right Locale!

- The parse() method is also used for parsing currency.
- For example, we can read in the zoo's monthly income from ticket sales.


```java
package chapter_5.locale;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public class ParsingNumbers_v2 {

    public static void main(String[] args) throws ParseException {

        String income = "$92,807.99";
        var cf = NumberFormat.getCurrencyInstance(Locale.US);
        double value = (Double) cf.parse(income);
        System.out.println(value);  //92807.99
    }
}
```

- The currency string "$92,807.99" contains a dollar sign and a comma.
- The parse method strips out the characters and converts the value to a number.
- The return value of parse is a Number object.
- Number is the parent class of all the java.lang wrapper classes, so the return value can be cast to its appropriate 
  data type.
- The Number is cast to a Double and then automatically unboxed into a double.


## Writing a Custom Number Formatter

- Like you saw earlier when working with dates, you can also create your own number format strings using the 
  DecimalFormat class, which extends NumberFormat.
- When creating a DecimalFormat object, you use a constructor rather than a factory method.
- You pass the pattern that you would like to use.
- The patterns can get complex, but you need to know only about two formatting characters, shown in Table 5.8.


---
### TABLE 5.8 DecimalFormat symbols ###

| Symbol | Meaning                                             | Examples |
|--------|-----------------------------------------------------|----------|
| #      | Omit the position if no digit exists for it.        | $2.2     |
| 0      | Put a 0 in the position if no digit exists for it.  | $00.20   |
---

- These examples should help illuminate how these symbols work:


```java
package chapter_5.locale;

import java.text.DecimalFormat;
import java.text.NumberFormat;

public class CustomNumberFormat_v1 {

    public static void main(String[] args) {

        double d = 1234567.467;
        NumberFormat f1 = new DecimalFormat("###,###,###.0");
        System.out.println(f1.format(d));  // 1,234,567.5

        NumberFormat f2 = new DecimalFormat("000,000,000.00000");
        System.out.println(f2.format(d));  // 001,234,567.46700

        NumberFormat f3 = new DecimalFormat("$#,###,###.##");
        System.out.println(f3.format(d));  //$1,234,567.47
    }
}
```

- Line 1734 displays the digits in the number, rounding to the nearest 10th after decimal.
- The extra positions to the left are left off because we used #.
- Line 1737 adds leading and trailing zeros to make the output the desired length.
- Line 20 shows prefixing a nonformatting characters ($ sing) along with rounding because fewer digits are printed than 
  available.

## Localizing Dates


- Like numbers, date formats can vary by locale.

---
### TABLE 5.9 Factory methods to get a DateTimeFormatter ###d

| Description                    | Using default Locale                                                                                              |
|--------------------------------|-------------------------------------------------------------------------------------------------------------------|
| For formatting dates           | DateTimeFormatter.ofLocalizedDate(dateStyle)                                                                      |
| For formatting times           | DateTimeFormatter.ofLocalizedTime(timeStyle)                                                                      |
| For formatting dates and times | DateTimeFormatter.ofLocalizedDateTime(dateStyle, timeStyle); DateTimeFormatter.ofLocalizedDateTime(dateTimeStyle) |
---


- Each method in the table takes a FormatStyle parameter, with possible values SHORT, MEDIUM, LONG, and FULL.
- For the exam, you are not required to know the format of each of these styles.

- What if you need a formatter for a specific locale?
- Easy enough - just append withLocale(locale) to the method call.

- Let's put it all together.
- Take a look at the following code snippet, which relies on a static import for the java.time.format.FormatStyle.SHORT 
  value:


```java
package chapter_5.locale;

import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;

public class LocalizingDates_v1 {

    public static void print(DateTimeFormatter dtf,
                             LocalDateTime dateTime, Locale locale) {
        System.out.println(dtf.format(dateTime) + ", "
            + dtf.withLocale(locale).format(dateTime));
    }

    public static void main(String[] args) {
        Locale.setDefault(new Locale("en", "US"));
        var italy = new Locale("it", "IT");
        var dt = LocalDateTime.of(2020, Month.OCTOBER, 20, 15, 12, 34);

        // 10/20/20, 20/10/20
        print(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT), dt, italy);

        // 3:12 PM, 15:12
        print(DateTimeFormatter.ofLocalizedTime(FormatStyle.SHORT), dt, italy);

        // 10/20/20, 3:12 PM, 20/10/20, 15:12
        print(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT, FormatStyle.SHORT), dt , italy);
    }
}
```


- First, we established en_US as the default locale, with it_IT as the requested locale.
- We then output each value using the two locales.


## Specifying a Locale Category

- When you call Locale.setDefault() with a locale, several and formatting options are internally selected.
- If you required finer-grained control of the default locale, Java actually subdivides the underlying formatting options
  into distinct categories, with the Locale.Category enum


---
### TABLE 5.10 Locale.Category values ###

| Value    | Description                                                |
|----------|------------------------------------------------------------|
| DISPLAY  | Category used for displaying data about the locale         |
| FORMAT   | Category used for formatting dates, numbers, or currencies |
---

- When you call Locale.setDefault() with a locale, both the DISPLAY and FORMAT are set together.
- Let's take a look at an example:

```java
package chapter_5.locale;

import java.text.NumberFormat;
import java.util.Locale;

public class LocaleCategory_v1 {

    private static void printCurrency(Locale locale, double money) {
        System.out.println(
                NumberFormat.getCurrencyInstance().format(money)
                + ", " + locale.getDisplayLanguage());
    }


    public static void main(String[] args) {

        var spain = new Locale("es", "ES");
        var money = 1.23;

        // Print with default locale
        Locale.setDefault(new Locale("en", "US"));
        printCurrency(spain, money); // $1.23, Spanish

        // Print with default locale and selected locale display
        Locale.setDefault(Locale.Category.DISPLAY, spain);
        printCurrency(spain, money); // $1.23, español

        // Print with default locale and selected locale format
        Locale.setDefault(Locale.Category.FORMAT, spain);
        printCurrency(spain, money); // 1,23 €, español
    }
}
```

- First, it prints the language of the spain and money variables using the locale en_US.
- Then, it prints it using the DISPLAY category of es_ES, while the FORMAT category remains en_US.
- Finally, it prints the data using both category set to es_ES.


## Loading Properties with Resources

- A resource bundle contains the locale-specific objects to be used by a program.
- It is like a map with keys and values.
- The resource bundle is commonly stored in a properties file.
- A properties file is a text in a specific format with key/value pairs.


> Note
> For the exam, you only need to know about resources bundles that are created from properties files.
> That said, you can also create a resource bundle from a class by extending ResourceBundle.
> One advantage of this approach is that it allows you to specify values using a method or in formats other than
> String, such as other numeric primitives, objects, or lists.


- We immediately realize that we are going to need to internationalize our program.
- Resource bundles will be quite helpful.
- They will let us easily translate our application to multiple locales or even support multiple locales at once.
- It will also be easy to add more locales later if we get zoos in even more countries interested.
- We thought about which locales we need to support, and we came up with four.


```
Locale us = new Locale("en", "US");
Locale france = new Locale("fr", "FR");
Locale englishCanada = new Locale("en", "CA");
Locale frenchCanada = new Locale("fr", "CA");
```

- In the next sections, we will create a resource bundle using properties files.
- A properties file is a text file that contains a list of key/value pairs.
- It is conceptually similar to a Map<String,String>, with each line representing a different key/value.
- The key and value are separated by an equal sign (=) or colon (:).
- To keep things simple, we use an equal sign throughout this chapter. 
- We will also look at how Java determines which resource bundle to use.



## Creating a Resource Bundle

- We're going to update our application to support the four locales listed previously.
- Luckily, Java doesn't require us to create four different resource boundles.
- If we don't have a country-specific resource bundle, Java will use a language-specific one.
- It's a bit more involved than this, but let's start with a simple example.

- For now, we need English and French properties files for our Zoo resource bundle.








































