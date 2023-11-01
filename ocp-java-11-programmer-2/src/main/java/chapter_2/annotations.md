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


## Limiting Usage with @Target

- When defining your own annotation, you might want to limit it to a particular type or set of types. After all, it may not
make sense for a particular annotation to be applied to a method parameter or local variable.
- Many annotation declaration include @Target annotation, which limits the types the type the annotation can be applied to.
More specifically, the @Target annotation takes an array of ElementType enum values as its value() element.

## Learning the ElementType Values

- Table 2.1 shows all of the values available for the @Target annotation.

| ElementType value | Applies to                                                        |
|-------------------|-------------------------------------------------------------------|
| TYPE              | Classes, interfaces, enums, annotations                           |
| FIELD             | Instances and static variables, enum values                       |
| METHOD            | Method declarations                                               |
| PARAMETER         | Constructor, method, and lambda parameters                        |
| CONSTRUCTOR       | Constructor declarations                                          |
| LOCAL_VARIABLE    | Local variables                                                   |
| ANNOTATION_TYPE   | Annotations                                                       |
| PACKAGE*          | Packages declared in package-info.java                            |
| TYPE_PARAMETER    | Parameterized types, generic declarations                         |
| TYPE_USE          | Able to be applied anywhere there is a Java type declared or used |
| MODULE*           | Modules                                                           |


* Applying these with annotations is out of scope for the exam.

- You might notice that some of the ElementType applications overlap. For example, to create an annotation usable on
other annotation, you could declare an @Target with ANNOTATION_TYPE or TYPE. Either will work for annotations, although
the second option opens the annotation usage to other types like classes and interfaces.

### Consider the following annotation

- Explanations are in the classes
  - chapter_2.target_annotation.ZooAttraction.java
  - chapter_2.target_annotation.RollerCoaster.java
  - chapter_2.target_annotation.Events.java

## Understanding the TYPE_USE Value

- While most of the value in Table 2.1 are straightforward, TYPE_USE is without a doubt the most complex. The TYPE_USE
parameter can be used anywhere there is a Java type. By including, it in @Target, it actually includes nearly all the 
values in Table 2.1 including classes, interfaces, constructors, parameters, and more. There are a few exceptions;
for example, it can be used only on a method that returns a value. Methods that return void would still need the METHOD
value defined in the annotation.


  - chapter_2.type_used.NetworkRepair.java

- For the exam, you don't need to know all of the places TYPE_USE can be used, nor what applying it to these locations
actually does, but you do need to recognize that they can be applied in this manner if TYPE_USE is one of the @Target
options.


## Storing Annotations with @Retention

- The compiler discards certain types of information when converting your source code into a .class file. With generics,
this is known as type erasure.
- Annotations may be discarded by the compiler or at runtime.
- We say 'may' because we can actually specify how they are handled using the @Retention annotation. This annotation takes
a value() of the enum RetentionPolicy.

| RetentionPolicy value | Description                                                                         |
|-----------------------|-------------------------------------------------------------------------------------|
| SOURCE                |  Used only in the source file, discarded by the compiler                            |
| CLASS                 | Stored in the .class file but not available at runtime (default compiler behaviour) | 
| RUNTIME               | Stored in the .class file and available at runtime                                  |


    - chapter_2.retention_annotation.Flier.java
    - chapter_2.retention_annotation.Swimmer.java

- In this example, both annotations will retain the annotation information in their .class files, although only Swimmer
will be available (via reflection) at runtime.

## Generating Javadoc with @Documented

- When trying to determine what methods or classes are available in Java or a third-party, you've undoubtedly relied
on we pages built with Javadoc.

    - chapter_2.documented_annotation.Hunter.java
    - chapter_2.documented_annotation.Lion.java

In this example, the @Hunter annotation would be published with the Lion Javadoc information because it's marked with the
@Documented annotation.

Note: Be careful not to confuse Javadoc annotations with the Java annotations. Annotation "@deprecated" and "@Deprecated".

## Inheriting Annotations with @Inherited

- When this annotation is applied to a class, subclasses will inherit the annotation information found in the parent class.


## Supporting Duplicates with @Repeatable

- The @Repeatable annotation is used when you want to specify an annotation more than once on a type.
- First rule: Without the @Repeatable annotation, an annotation can be applied only once.
- Second rule: To declare @Repeatable annotation, you must define a containing annotation type value.

    - chapter_2.repeatable_annotation.Zoo.java
    - chapter_2.repeatable_annotation.Risks.java
    - chapter_2.repeatable_annotation.Risk.java


## Repeatable Annotations vs. an Array of Annotations

- Repeatable annotations were added in Java 8. Prior to this, you would have had to use the @Risks containing annotation
directly. With this implementation, @Repeatable is not required in the Risk annotation declaration.


## Reviewing Annotation-Specific Annotations

| Annotation  | Marker annotation | Type of value()      | Default compiler behavior (if annotation not present)                             |
|-------------|-------------------|----------------------|-----------------------------------------------------------------------------------|
| @Target     | No                | Array of ElementType | Annotation able to be applied to all locations except TYPE_USE and TYPE_PARAMETER |
| @Retention  | No                | RetentionPolicy      | RetentionPolicy.CLASS                                                             |
| @Documented | Yes               | -------------------- | Annotations are not included in the generated Javadoc                             |
| @Inherited  | Yes               | -------------------- | Annotations in supertypes are not inherited                                       |
| @Repeatable | No                | Annotation           | Annotation cannot be repeated                                                     |


Note: The @Target annotation is a bit of a special case. When @Target is not present, an annotation can be used in any 
place except TYPE_USE or TYPE_PARAMETER scenarios (cast operations, object creation, generic declarations, etc.).


## Using Common Annotations

For the exam, you'll need to know about a set of built-in annotations, which apply to various types and methods.

## Marking Methods with @Override

The @Override is a marker annotation that is used to indicate a method is overriding an inherited method, whether it be 
inherited from an interface or parent class.

    - chapter_2.override_annotation.Intelligence.java
    - chapter_2.override_annotation.Canine.java
    - chapter_2.override_annotation.Wolf.java
    - chapter_2.override_annotation.Dog.java


- In this example, the @Override annotation is applied to three methods that it inherits from the parent class or interface.

Note: The annotation in this section are entirely optional but help improve the quality of the code.


## Declaring Interfaces with @FunctionalInterface

- The @FunctionalInterface marker annotation can be applied to any valid functional interface.


    - chapter_2.funcional_interface_annotation.Intelligence.java
    - chapter_2.funcional_interface_annotation.Reptile.java
    - chapter_2.funcional_interface_annotation.Rough.java
    - chapter_2.funcional_interface_annotation.Scaley.java
    - chapter_2.funcional_interface_annotation.Slimy.java
    - chapter_2.funcional_interface_annotation.Smooth.java


## Retiring Code with @Deprecated

- What we want is a way to notify our users that a new version of the method is available and give them time to migrate
their code code to the new version before we finally remove the old version.
- With those ideas in mind Java includes the @Deprecate annotation. The @Deprecated annotation is similar to a marker 
annotation, in that it can be used without any values, but it includes some optional elements. The @Deprecated annotation
can be applied to nearly any Java declaration, such as classes, methods, or variables.

- chapter_2.deprecated_annotation.ZooPlanner.java

### Always Document the Reason for Deprecation

- Earlier, we discussed @Deprecated and @deprecated, the former being a Java annotation and the latter being a Javadoc
annotation. Whenever you deprecate a method, you should add a Javadoc annotation to instruct users on how they should
update their code.
- For the exam, you should know that it is good practice to document why a type is being deprecated and be able to
suggest possible alternatives.

- chapter_2.deprecated_annotation.ZooPlanner.plan()

Note that the @Deprecate annotation does not allow you to provide any suggested alternatives. For that, you should use 
the Javadoc annotation.


## Ignoring Warnings with @SuppressWarnings

- While the compiler can be helpful in warning you of potential coding problems, sometimes you need to perform a
particular operation, an dyou don't care whether or not it is a potential programming problem.

### Table 2.4 Common @SupressWarnings values

| Value           | Description                                                                           |
|-----------------|---------------------------------------------------------------------------------------|
| "deprecation"   | Ignore warnings related to types or methods marked with the @Deprecated annotation.   |
| "unchecked"     | Ignore warnings related to the use of raw types, such as List instead of List<String> |


The annotation actually supports a lot of other values, but for the exam, you only need to know the ones listed in this
table. Let's try an example:


- chapter_2.surpresswarnings_annotation.Nightingale.java


## Protecting Arguments with @SafeVarargs

- The @SafeVargs marker annotation indicates that the method does not perform any potential unsafe operations on its 
varargs parameter. It can be applied only to constructors or methods that cannot be overriden (aka methods marked private
, static, or final)
- The @SafeVargs annotation is used to indicate to other developers that your method does not perform any unsafe operations.
It basically tells other developers, "Don't worry about the varargs parameter; I promise this method won't do anything
bad with it!" It also suppresses unchecked compiler warnings for the varargs parameter.

- In the following example, thisIsUnsafe() performs an unsafe operation using its varargs parameter:

- chapter_2.safevarargs_annotation.NeverDoThis.java

Note: For the exam you don't need to know how to create or resolve unsafe operations, as that can be complex.
You just need to be able to identify unsafe operations and know they often involve generics.

Note 2: You should also know the annotation can be applied only to methods that contain a varargs parameter and are not 
able to be overridden. For example:

```
@SafeVarargs
public static void eat(int meal) {} // DOES NOT COMPILE
```

## Reviewing Common Annotations