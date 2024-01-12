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


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_5/images/figure_5_1.png?raw=true)


- A traditional try statement must have at least one of the following:
- a catch block or a finally block. It can have more than one catch block, including multi-catch blocks, but at most 
  one finally block.


Tip: Swallowing an exception
  - Swallowing an exception is when you handle it with an empty catch block.
  - When presenting a topic, we often do this to keep things simple.
  - Please, never do this in practice! Oftentimes, it is added by developers who do not want to handle or declare an 
    exception properly and can lead to bugs in production code.















