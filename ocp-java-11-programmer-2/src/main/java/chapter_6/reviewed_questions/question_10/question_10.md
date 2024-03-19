10. For a top-down migration, all modules other than named modules are __________ modules and on the ____________.

A. automatic, classpath <br>
B. automatic, module path <br>
C. unnamed, classpath <br>
D. unnamed, module path <br>
E. None of the above <br>


---
### Explanation ###

- Correct Answer: B
- A top-down migration strategy first places all JARs on the module path.
- Then it migrates the top-level module to be a named module, leaving the other modules as automatic modules.
- Option B is correct as it matches both of those characteristics.
---
