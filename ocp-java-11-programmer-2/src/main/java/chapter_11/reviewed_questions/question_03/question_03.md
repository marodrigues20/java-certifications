3. Which can fill in the blank to make this code compile?

```markdown
import java.io.*;

public class AnimalCheckup {
    private String name;
    private int age;
    
    private static final ObjectStreamField[] 
    serialPersistentFields = 
            { new ObjectStreamField("name", String.class)};
    
    private void writeObject(ObjectOutputStream stream) 
            throws Exception {
        
        ObjectOutputStream._______ fields = stream.putFields();
        fields.put("name", name);
        stream.writeFields();
    }
    // readObject method omitted
}
```

A. PutField <br>
B. PutItem  <br>
C. PutObject  <br>
D. UpdateField  <br>
E. UpdateItem  <br>
F. UpdateObject <br>

Answer: A