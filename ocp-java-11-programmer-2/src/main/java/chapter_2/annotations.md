# Chapter 2 - Annotations

## OCP Exam Objectives Covered in this Chapter:

- Describe the purpose of annotations and typical patterns
- Apply annotations to classes and methods
- Describe commonly used annotations in the JDK
- Declare custom annotations

Annotations were added to the Java language to make a developer's life a lot easier.
In this chapter, we define what an annotation is, how to create a custom annotation, and how to properly apply annotation.

## Introduction Annotation

Annotation are all about metadata.

## Understanding Metadata

Metadata is data that provides information about other data.

## Purpose of Annotations

The purpose of an annotation is to assign metadata attributes to classes, methods, variables, and other Java types.
Just need to know that annotations start with the at (@) symbol and can contain attribute/value pairs called elements.

```
public class Mammal{}
public class Bird{}

@ZooAnimal public class Lion extends Mammal {}
@ZooAnimal puplic class Peacock extends Bird{}
```

- So if annotations function like interfaces, why don't we just use interfaces? While interfaces can be applied only to
classes, annotations can be applied to any declaration including classes, methods, expressions, and even other
annotations.

Consider the following Veterinarian class:

```
@ZooAnimal (habitat="Infirmary") private Lion sickLion
@ZooAnimal (havitat="Safari") private Lion healthyLion;
@ZooAnimal (habitat="Special Enclousure") private Lion blindLion;
```

This class defines three variables, each with an associated habitat value. The habitat value is part of the type 
declaration of each variable, not an individual object. For example, the healthyLion may change the object it points to,
but the value of the annotation does not. Without annotations, we'd have to define a new Lion type for application.

That brings us to our second rule about annotations: annotations establish relationship that make it easier to manage
data about our application.

- Third rule about annotations: an annotation ascribes custom information on the declaration where it is defined.
- Final rule about annotation you should be familiar with: annotation are optional metadata and by themselves do not
do anything.
- This last rule might seem a little counterintuitive at first, but it refers to the fact that annotations aren't 
utilized where they are defined.


## Creating Custom Annotations

- We use an annotation to create an annotation! The Exercise annotation is referred to as a marker annotation, since
it does not contain any elements.

  - chapter_2.creating_annotation.stage_01.Exercise.java

- How do we use our new annotation? It's easy. We use the at (@) symbol, followed by the type name. In this case,
the annotation it @Exercise. Then, we apply the annotation to other Java code, such as class.
- Let's apply @Exercise to some classes.

- chapter_2.creating_annotation.stage_01.Cheetah.java
- chapter_2.creating_annotation.stage_01.Sloth.java
- chapter_2.creating_annotation.stage_01.ZooEmployee.java

- You might have noticed that Cheetah.java and Sloth.java differ on their usage of the annotation. One uses parentheses, (),
  while the other does not. When using a marker annotation, parentheses are optional. Once we start adding elements,
  though, they are required if the annotation includes any values.

- Note: Whether you put annotation on the same line as the type they apply to or on separate lines is a matter of style.
Either is acceptable.

```
@Scaley @Flexible
 @Food("insect") @Food("rodent") @FriendyPet
@Limbless public class Snake {}
```

In this example, some annoations are all lowercase, while others are mixed case. Annotation names are case sensitive.
Like class and interfaces names, it is common practice to have them start with an uppercase letter, although it is not
required.
Finally, some annotation, like @Food, can be aplied more than once. We'll cover repeatable annotation later in this 
chapter.

## Specifying a Required Element

- An annotation element is an attribute that stores values about the particular usage of an annotation. To make our 
previous example more useful, let's change @Exercise from a marker annotation to one that includes an element.

- chapter_2.creating_annotation.stage_02.Exercise.java

## Providing an Optional Element

- For an element to be optional, rather than required, it must include a default value.

- chapter_2.creating_annotation.stage_02.Exercise.java

- When we have more than one element value within an annotation, we separate them by comma(,). Next, each element is
written using the syntax elementName = elementValue. It's a shorthand for a Map. Also, the order of each element does 
not matter. Cheetah, could have listed hoursPerDay first.


## Selecting an Element Type

- Similar to a default element value, an annotation element cannot be declared with just any type. It must be a 
primitive type, a String, a Class, an enum, another annotation, or an array of any of these types.

- chapter_2.creating_annotation.stage_03.Panda.java


## Applying Element Modifiers

- Like abstract interfaces methods, annotation elements are implicitly abstracts and public, whether you declare them
that way or not.

- chapter_2.creating_annotation.stage_04.Fluffy.java


## Adding a Constant Variable

- Annotations can include constant variables that can be accessed by other classes without actually creating the 
annotation.

- chapter_2.creating_annotation.stage_05.ElectricitySource.java


## Reviewing Annotation Rules

![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_2/images/review.png?raw=true)


## Applying Annotations

It's time to discuss other ways to apply annotations.

### Using Annotations in Declaration

- We've only been applying annotations to classes and methods, but they can be applied to any Java declaration including
the following.

- Classes, interfaces, enums, and modules
- Variables (static, instance, local)
- Methods and constructors
- Method, constructor, and lambda parameters
- Cast expression
- Other annotations


![alt text](https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_2/images/annotation_in_declaration.png?raw=true)

- Lines 1, 4, and 5 apply annotations to the interface and class declarations. Some of the annotations, like @Cuddly, 
do not require any values, while others, like @Speed, do provide values.
- Lines 6 and 8 contain annotations applied to constructor and method declarations. These lines also contain annotations
applied to their parameters.
- Line 9 contains the annotation @Food applied to a local variable, along with the annotation @Tasty applied to a cast
expression.

Note: When applying an annotation to an expression, a cast operation including the Java type is required. On line 9, the 
expression was cast to String, and the annotation @Tasty was applied to the type.

- Line 11 applies an annotation to the type in the anonymous class declaration, and line 17 shows an annotation in a 
lambda expression parameter.

## Mixing Required and Optional Elements

- One of the most important rules when applying annotations is the following: to use an annotation, all required values
must be provided.

- chapter_2.applying_annotation.Amphibiam.java
- chapter_2.applying_annotation.Tadpole.java
- chapter_2.applying_annotation.Frog.java
- chapter_2.applying_annotation.Reptile.java
- chapter_2.applying_annotation.Snake.java

## Creating a value() Element

- In your development experience, you may have seen an annotation with a value, written without the elementName. For 
example, the following is valid syntax under the right condition:

- chapter_2.value_element.Monkey.java

- This is considered a shorthand or abbreviated annotation notation. An annotation must adhere to the following rules
to be used without a name:

- The annotation declaration must contain an element named value(), which may be optional or required.
- The annotation declaration must not contain any other elements that are required.
- The annotation usage must not provide values for any other elements.

- chapter_2.value_element.Monkey.java
- chapter_2.value_element.Elephant.java


Tip: Typically, the value() of an annotation should be related to the name of the annotation. In our previous example,
@Injured was the annotation name, and the value() referred to the item that was impacted. This is especially important
since all shorthand elements use the same element name, value().

- The following annotation declaration cannot be used with a shorthand annotation:
  - chapter_2.value_element.Sleep.java
  - chapter_2.value_element.Wake.java

- This annotation is not valid as it provides more than on value:
  - chapter_2.value_element.Bear.java


## Passing an Array of Values

- Annotations support a shorthand notation for providing an array that contains a single element.

  - chapter_2.array_values.Music.java

- We can provide two ways to write the annotation.

  - chapter_2.array_values.Giraffe.java

- Check a class and see with annotations compiles:
  - chapter_2.array_values.Reindeer.java


## Combining Shorthand Notations

- chapter_2.array_values.Rhythm.java
- chapter_2.array_values.Capybara.java


