# Review Questions

2) Which of the following statements about var are true? (Choose all that apply.)

A. A var can be used as a constructor parameter.  <br>
B. The type of var is known at compile time  <br>
C. A var cannot be used as an instance variable  <br>
D. A var can be used in a multiple variable assignment statement.  <br>
E. The value of var cannot change at runtime.  <br>
F. The type of var cannot change at runtime.  <br>
G. The word var is a reserved word in Java.  <br>


Correct Answers: B; C; F


4) Which of the following is an advantage of the Java Platform Module System?

A. A central repository of all modules  <br>
B. Encapsulating packages  <br>
C. Encapsulating objects  <br>
D. No defined types  <br>
E. Platform independence  <br>

Correct Answer: B

5) Which statement is true of the following module?


```
zoo.staff
| --- zoo
    |--- staff
        |--- Vet.java
```



A. The directory structure shown is a valid module.  <br>
B. The directory structure would be a valid module if module.java were added directly underneath zoo.staff.  <br>
C. The directory structure would be a valid module if module.java were added directly underneath zoo.  <br>
D. The directory structure would be a valid module if module-info.java were added directly underneath zoo.staff.  <br>

Correct Answer: D

Modules are required to have a module-info.java file at the root directory of the module. 


6. Suppose module *puppy* depends on module *dog*, and module *dog* depends on module *animal*.
Fill in the blank so that code in module *dog* can access the *animal.behaviour* package in module *animal*.

```
module animal {
  _________________ animal.behavior
}
```

A. export  <br>
B. exports  <br>
C. require  <br>
D. requires  <br>
E. require transitive  <br>
F. requires transitive  <br>
G. None of the above  <br>


Correct Answer: B

Options A, C, and E are incorrect because they refer to keywords that don't exist.
The *exports* keyword is used when allowing a package to be called by code outside of the module,
making option B the correct answer. Notice that options D and F are incorrect because *requires* uses module names
and not package names.


7. Fill in the blanks so this command to run the program is correct:

```
java
_________ zoo.animal.talks/zoo/animal/talks/Peacocks
_________ modules
```

A. -d and -m
B. -d and -p
C. -m and -d
D. -m and -p
E. -p and -d
F. -p and -m
G. None of the above

Correct Answer: G

The *-m* or *--module* option is used to specify the module and class name. The *-p* or *-module-path* option is used to 
specify the location of the modules. Option D would be correct if the rest of the command were correct.
However, running a program requires specifying the package name with periods(.) instead of slashes.
Since the command is incorrect, option G is correct.



8. Which of the following statements are true in a *module-info.java* file?
   (Choose all that apply.)

A. The *opens* keyword allows the use of reflection.  <br>
B. The *opens* keyword declares an API is called.  <br>
C. The *use* keyword allows the use of reflection.  <br>
D. The *use* keyword declares an API is called.   <br>
E. The *uses* keyword allows the use of reflection.  <br>
F. The *uses* keyword declares an API is called.  <br>
G. The file can be empty (zero bytes).  <br>


Correct Answer: A, F, G.

Options C and D are incorrect because there is no *use* keyword. Options A and F are correct because *opens* is for 
reflection and *uses* declares an API that consumes a service. Option G is also correct as the file can be completely 
empty. This is just something you have to memorize.


9. What is true of a module containing a file named *module-info.java* with the following contents?
   (Choose all that apply.)

```
module com.food.supplier {}
```

A. All packages inside the module are automatically exported.  <br>
B. No packages inside the module are automatically exported.   <br>
C. A main method inside the module can be run.  <br>
D. A main method inside the module cannot be run since the class is not exposed.  <br>
E. The *module-info.java* file contains a compiler error.  <br>
F. The *module-info.java* filename is incorrect.  <br>


Correct Answer: B, C

Packages inside a module are not exported by default, making option B correct and option A incorrect.
Exporting is necessary for other code to use the packages; it is not necessary to call the main method at the
command line, making option C correct and option D incorrect. The *module-info.class* file has the correct name and 
compiles, making options E and F incorrect.


10. Suppose module *puppy* depends on module *dog* and module *dog* depends on module *animal*. Which two lines allow
module *puppy* to access the *animal.behavior* package in module *animal*? (Choose two.)

```
module animal {
    exports animal.behaviour;
}

module dog {
    _________ animal;   // line S
}

module puppy {
    _________ dog;   // line T
}
```

A. *require* on line S <br>
B. *require* on line T <br>
C. *requires* on line S <br>
D. *requires* on line T <br>
E. *require transitive* on line S <br>
F. *require transitive* on line T <br>
G. *requires transitive* on line S <br>
H. *requires transitive* on line T <br>


Correct Answer: D; G

Options A, B, E, and F are incorrect because they refer to keywords that don't exist.
The *requires transitive* keyword is used when specifying a module to be used by the requesting module and any other
modules that use the requesting module. Therefore, *dog* needs to specify the transitive relationship, and option G
is correct. The module *puppy* just needs to *require* dog, and it gets the transitive dependencies, making option D
correct.



11. Which commands take a *--module-path* parameter? (Choose all that apply.)

A. javac <br>
B. java <br>
C. jar <br>
D. jdeps <br>
E. jmod <br>
F. None of the above <br>

Correct Answer: A; B; D

Options A and B are correct because the *-p(--module-path)* option can be passed when compiling or running a program.
Option D is also correct because *jdeps* can use the *--module-path* option when listing dependency information.


12. Which of the following are legal commands to run a modular program? (Choose all that apply.)

A. java -p x -m x/x <br>
B. java -p x-x -m x/x <br>
C. java -p x -m x-x/x <br>
D. java -p x -m x/x-x <br>
E. java -p x -m x.x <br>
F. java -p x.x -m x.x  <br>
G. None of the above <br>

Correct Answer: 
























