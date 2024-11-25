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
    }
    
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