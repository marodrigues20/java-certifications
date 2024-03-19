4. Which of the following statements are true? (Choose all that apply.)

A. Modules with cyclic dependencies will not compile. <br>
B. Packages with a cyclic dependency will not compile. <br>
C. A cyclic dependency always involves exactly two modules. <br>
D. A cyclic dependency always involves three or more modules. <br>
E. A cyclic dependency always involves at least two *requires* statements. <br>
F. An unnamed module can be involved in a cyclic dependency with an automatic module. <br>


Answer: A; C; 

---
### Explanation ###

- Correct Answer: A and E

- A cyclic dependency is when a module graph forms a circle
- Option A is correct because the Java Platform Module System does not allow cyclic dependencies between modules.
- No such restriction exists for packages, making option B incorrect.
- A cyclic dependency can involve two or more modules that require each other, making option E correct, while option C
  and D are incorrect.
- Finally, Option F is incorrect because unnamed modules cannot be referenced from from an automatic module.
---