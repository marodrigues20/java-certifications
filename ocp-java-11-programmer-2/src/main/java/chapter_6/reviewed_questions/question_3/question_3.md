3. An automatic module name is generated if one is not supplied. Which of the following JAR filename and generated 
   automatic module name pairs are correct? (Choose all that apply.)

A. emily-1.0.0.jar and emily <br>
B. emily-1.0.0-SNAPSHOT.jar and emily <br>
C. emily_the_cat-1.0.0.jar and emily_the_cat <br>
D. emily_the_cat-1.0.0.jar and emily-the-cat <br>
E. emily.$.jar end emily <br>
F. emily.$.jar and emily. <br>
G. emily.$.jar and emily.. <br>

---
### Explanation ###

- Correct Answer: A; B; E

- Any version information at the end of the JAR filename is removed, making options A and B correct.
- Underscores (_) are turned into dots (.), making options C and D incorrect.
- Other special characters like a dollar sign ($) are also turned into dots. However, adjacent dots  are merged, and 
  leading/trailing dots are removed. Therefore, option E is correct.

---