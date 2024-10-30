3. What is the value of *name* after instance of *Eagle* created in the *main()* method is serialized 
   and then deserialized? 


```java
import java.io.Serializable;

class Bird{
    protected transient String name;
    public void setName(String name) { this.name = name; }
    public String getName() { return name; }
    public Bird() {
        this.name = "Matt";
    }
}

public class Eagle extends Bird implements Serializable{
    { this.name = "Olivia"; }
    public Eagle(){
        this.name = "Bridget";
    }
    
    
    public static void main(String[] args){
        var e = new Eagle();
        e.name = "Adeline";
    }
}
```

A. Adeline <br>
B. Matt <br>
C. Olivia <br>
D. Bridget <br>
E. null <br>
F. The code does not compile. <br>
G. The code compiles will allow the following to compile? (Choose all that apply.) <br>


- Correct Answer: B

- The code compiles and runs without issue, so options F and G are incorrect.
- The key here is that while *Eagle* is serializable, its parent class *Bird* is not.
- Therefore, none of the members of *Bird* will be serialized. Even if you didn't know that,
  you should know what happens on deserialization.
- **During deserialization, Java calls the constructor of the first nonserializable parent**. In this case,
  the *Bird* constructor is called, with *name* being set to *Matt*, making option B correct.
- Note that none of the constructor or instance initializers in *Eagle* is executed as part of deserialization.