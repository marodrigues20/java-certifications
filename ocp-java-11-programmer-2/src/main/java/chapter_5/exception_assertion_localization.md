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
  languages right away.*** It just means your application can be more early adapted in the feature.
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

| ArithmeticExceptions      | ArrayIndexOutOfBoundException |
|---------------------------|-------------------------------|
| ArrayStoreException       | ClassCastException            |
| IllegalArguementException | IllegalStateException         |
| MissingResourceException  | NullPointerException          |
| NumberFormatException     | UnsupportedOperationException |

- Table 5.2 presents the checked exceptions you shoudl also be familiar with.

### TABLE 5.2 Checked exceptions

| FileNotFoundException    | IOException    |
|--------------------------|----------------| 
| NotSerializableException | ParseException |
| SQLException             | N/A            |

## Inheriting Exception Classes