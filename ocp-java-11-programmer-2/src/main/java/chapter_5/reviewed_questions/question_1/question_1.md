1. Which of the following classes contain at least one compiler error?
- Answer: C
- Exception and RuntimeException, along with many other exceptions in the Java API, define a no-argument constructor,
  a constructor that takes a String, and a constructor that takes a Throwable.
- For this reason, Danger compiles without issue.
- Catastrophe also compiles without issue. Just creating a new checked exception, without throwing it, does not require 
  it to be handled or declared.
- Finally, Emergency does not compile. The no-argument constructor in Emergency must explicitly call a parent constructor,
  since Danger does not define a no-argument constructor.