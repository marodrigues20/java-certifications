22. Which of the following fields will be *null* after instance of the class created on line 15 is serialized and then
    deserialized using *ObjectOutputStream* and *ObjectInputStream*? (Choose all that apply.)

```java
import java.io.Serializable;
import java.util.List;
public class Zebra implements Serializable {
    private transient String name = "George";
    private static String birthPlace = "Africa";
    private transient Integer age;
    List<Zebra> friends = new java.util.ArrayList<>();
    private Object stripes = new Object();
    { age = 10; }
    public Zebra(){
        this.name = "Sophia";
    }
    static Zebra writeAndRead(Zebra z){
        // Implementation omitted
    } // LINE 15
    public static void main(String[] args){
        var zebra = new Zebra();
        zebra = writeAndRead(zebra);
    }
}
```

A. name <br>
B. stripes <br>
C. age <br>
D. friends <br>
E. birthPlace <br>
F. The code does not compile <br>
G. The code compiles but throws an exception at runtime.

Correct Answer: G

- The code compiles, so option F is incorrect.
- To be serializable, a class must implement the *Serializable* interface, which *Zebra* does.
- It must also contain instance members that either are marked *transient* or are serializable.
- If *Object* implemented *Serializable*, then all objects would be serializable by default, defeating the purpose of
  having the *Serialiable* interface.
- Therefore, the *Zebra* class is not serializable, with the program throwing an exception at runtime if serialized and 
  making option G correct.
- If *stripes* were removed from the class, then options A and C would be the correct answer, as *name* and *age* are 
  both marked *transient*.