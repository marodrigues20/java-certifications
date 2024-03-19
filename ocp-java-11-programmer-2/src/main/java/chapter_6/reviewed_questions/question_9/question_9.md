9. Which command can you run to determine whether you hava any code in you JAR file that depends on unsupported internal
   APIs and suggest an alternative?

A. jdeps -internal-jdk <br>
B. jdeps --internaljdk <br>
C. jdeps --internal-jdk <br>
D. jdeps -s  <br>
E. jdeps -unsupported <br>
F. jdeps -unsupportedapi <br>
G. jdeps -unsupported-api <br>
H. None of the above <br>




---
### Explanation ###

- Correct Answer: C
- The *jdeps* command has an option *--internal-jdk* that lists any code using unsupported/internal APIs and prints a 
  table with suggested alternatives. This makes option C correct.
- Option D is incorrect because it does not print out the table with a suggested alternative.
- Options A, B, E, F, and G are incorrect because those options do not exist.
---