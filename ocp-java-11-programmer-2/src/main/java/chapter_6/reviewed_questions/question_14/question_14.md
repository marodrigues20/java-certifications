14. Suppose you have a project with one package named *magic.wand* and another project with one package named
    *magic.potion*. These projects have a circular dependency, so you decide to create a third project named 
    *magic.helper*. The *magic.helper* module has the common code containing a package named *magic.util*. For simplicity, let's give 
    each module the same name as the package. Which of the following need to appear in your *module-info* files
    (Choose all that apply.)

A. exports magic.potion; in the potion project
B. exports magic.util; in the magic helper project
C. exports magic.wand; in the wand project
D. requires magic.util; in the magic helper project
E. requires magic.util; in the potion project
F. requires magic.util; in the wand project




---
### Explanation ###

- Correct Answer: B; E; F

- Since the new project extracts the common code, it must have an *exports* directive for that code, making option B correct.
- The other two modules to not have to expose anything. They must have a *requires* directive to be able to use the 
  export code, making options £ and F correct.
---


