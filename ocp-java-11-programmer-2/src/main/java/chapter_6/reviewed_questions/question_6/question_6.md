6. Suppose you are creating a service provider that contains the following class. Which line of code needs to be in your
   *module-info.java*?

```java
package dragon;
import magic.*;
public class Dragon implements Magic {
    public String getPower(){
        return "breathe fire";
    }
}
```

A. provides dragon.Dragon by magic.Magic; <br>
B. provides dragon.Dragon using magic.Magic; <br>
C. provides dragon.Dragon with magic.Magic; <br>
D. provides magic.Magic by dragon.Dragon; <br>
E. provides magic.Magic using dragon.Dragon; <br>
F. provides magic.Magic with dragon.Dragon; <br>


---
### Explanation ###

- Correct Answer: F
- The *provides* directive takes the interface name first and the implementation class name second.
- The *with* keyword is used. Only option F meets these two criteria, making it the correct answer.
---