# Review Questions

1. What modifier is used to mark that an annotation element is required?
A: E. In an annotation, an optional element is specified with the default modifier, followed by a constant value. 
Required elements are specified by not providing a default value. Therefore, the lack of default term indicates the 
element is required.

2. Which of the following lines of code do not compile?
A: Dirt.java

3. Which built-in annotations can be applied to an annotation declaration?
A: B, D, E. The annotations @Target and @Repeatable are specifically designed to be applied to annotations, marking
options D and E correct. Option B is also correct, as @Deprecated can be applied to almost any declaration. Option A is
incorrect because @Override can be applied only to methods. Option C and F are incorrect because they are not the names
of built-in annotations.

4. Given an automobile sales system, which of the following information is best stored using an annotation?
A: D. Annotations should include metadata (data about data) that is relatively constant, as opposed to attribute data,
which is part of the object and can be change frequently. The price, sales, inventory, and people who purchased a vehicle
could fluctuate often, so using an annotation would be a poor choice. On the other hand, the number of passengers a
vehicle is rated for extra information about vehicle and unlikely to change once established. Therefore, it is appropriate
metadata and best served using an annotation.

5. Which of the following lines of code do not compile?
A: Unexpected.java

6. Which annotation, when applied independently, allow the following program to compile?
A: Driver.java

7. Annotations can be applied to which of the following?
A: The annotations can be applied for Class declarations, Constructor parameters, Local variable declarations,
Cast operations, Lambda expression parameters and Interface declarations.

8. Fill in the blanks with the correct answers that allow the entire program to compile.
A: B, F. In this question, Ferocious is the repeatable annotation, while FerociousPack is the containing type annotation.
The containing type annotation should contain a single value() element that is an array of the repeatable annotation type.
For the reason, option B is correct. Option A would allow FerociousPack to compile, but not Ferocious.
Option C is an invalid annotation element type.
The repeatable annotation needs to specify the class name of its containing type annotation, making option F correct.
While it is expected for repeatable annotations to contain elements to differentiate its usage, it is not required.
For this reason, the usage of @Ferocious is a valid marker annotation on the Lion class, making option G incorrect.
question_8.Ferocious.java
question_8.FerociousPack.java

9. What properties must be true to use an annotation with an element value, but no element name?
A: D. To use an annoation with a value but not element name, the element must be declared with the name value(), not 
values(), making option A incorrect.

10. Which statement about the following code is correct?
chapter_2.review_questions.question_10.Furry.java
chapter_2.review_questions.question_10.Bunny.java

11. What properties of applying @SafeVarargs are correct?


