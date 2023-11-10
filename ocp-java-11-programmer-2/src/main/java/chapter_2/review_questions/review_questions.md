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
A: C, D, F. The @SafeVarargs annotation can be applied to a constructor or private, static, or final method that includes
a varargs parameter. For these reason, options C, D, and F are correct. Option A is incorrect, as the compiler cannot 
actually enforce that the operations are safe. It is up to the developer writing the method to verify that. Option B is 
incorrect as the annotation can be applied only to methods that cannot be override. Finally, option E is incorrect, 
as it is applied to the declaration, not the parameters.

12. Which of the following lines of code do not compile?
A:
chapter_2.review_questions.Cold.java

13. Which statements about an optional annotation are correct?
A: A, D. An optional annotation element is one that is declared with a default value that may be optionally replaced 
used in an annotation. For these reason, options A and D are correct.

14. Fill in the blanks: The _________________________ annotation determines whether annotations are discarded at runtime,
while the _________________ annotation determines whether they are discarded in generated Javadoc.
A: D. The @Retention annotation determines whether annotations are discarded when the code is compiled, at runtime. The
presence, or absence, of the @Documented annotation determines whether annotations are discarded within generated Javadoc.
For these reasons, option D is correct.

15. What statement about marker annotations is correct?
A: B. A marker annotation is an annotation with no elements. It may or may not have constant
variable, making option B correct. Option E is incorrect as no annotation can be extended.

16. Which options, when inserted into the blank in the code, allow the code to compile without any warnings?
A: chapter_2.review_questions.Donkey.java

17. What motivations would a developer have for applying the @FunctionalInterface annotation to an interface?
A: B, E. The @FunctionalInterface marker annotation is used to document that an interface is a valid functional interface
that contains exactly one abstract method, making option B correct. It is also useful in determining whether an interface
is a valid functional interface, as the compiler will report an error if used incorrectly, making option E correct.
The compiler can detect whether an interface is a functional interface even without the annotation, making options A and
C incorrect.

18. Which of the following lines of code do not compile?
A: chapter_2.review_questions.Wind.java

19. Which annotations can be added to an existing method declaration but could cause a compiler error depending on the 
method signature?
A: A, F. The @Override annotation can be applied to a method but will trigger a compiler error if the method signature
does not match an inherited method, making option A correct. The annotation @Deprecated can be applied to a method
but will not trigger any compiler errors based on the method signature. The annotations @FunctionalInterface, 
@Repeatable, and @Retention cannot be applied to methods, making these options incorrect. Finally, @SafeVarargs can be
applied to a method but will trigger a compiler error if the method does not contain a varargs parameter or is able to
be overridden (not marked private, static, or final).

20. Given the Floats annotation declaration, which lines in the Birch class contain compiler errors?
A: chapter_2.review_questions.question_20.Birch.java

21. Fill in the blanks. The ___________________________ annotation determines what annotations from a superclass or 
interface are applied, while the _______________________________ annotation determines what declarations an annotation
can be applied to.
A: G. The @Inherited annotation determines whether or not annotation defined in a super type are automatically inherited 
in a child type. The @Target annotation determines the location or locations an annotation can be applied to. Since this
was not an answer choice, option G is correct Note that ElementType is an enum used by @Target, but it is not an 
annotation.

22. Which annotation can cancel out a warning on a method using @Deprecated API at compile time?
A: F. If @SuppressWarnings("deprecation") is applied to a method that is using a deprecated API, then warnings related
to the usage will not be shown at compile time, making option F correct. Note that there are no built-in annotations
called @Ignore or @IgnoreDeprecated.


