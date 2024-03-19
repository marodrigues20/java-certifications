11. Suppose you have separate modules for a service provider interface, service provider, service locator, and consumer.
    if you add a second service provider module, how many of these modules do you need to recompile?

A. Zero  <br>
B. One   <br>
C. Two   <br>
D. Three <br>
E. Four  <br>


---
### Explanation ###

- Correct answer: A

- Since this is a new module, you need to compile the new module.
- However, none of the existing modules needs to be recompiled, making option A correct.
- The service locator will see the new service provider simply by having the new service provider on the module path.
---