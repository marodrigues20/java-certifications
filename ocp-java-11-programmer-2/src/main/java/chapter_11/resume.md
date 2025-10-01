# Chapter 11 - Security

## OCP Exam Objectives Covered in this chapter

- Secure Coding in Java SE Application
  - Prevent Denial of Service in Java applications
  - Secure confidential information in Java application
  - Implement Data integrity guidelines - injections and inclusions and input validation
  - Prevent external attack of the code by limiting Accessibility and Extensibility, properly handling input validation,
    and mutability.
  - Securely constructing sensitive objects
  - Secure Serialization and Deserialization

---
- The exam only covers Java SE (Standard Edition) application. It does not cover web applications or any other advanced
  Java.
---

- We will learn how Hacker Harry tries to do bad things and Security Sienna protects her application.

## Limiting Accessibility

- Hacker Harry heard that the zoo uses combination locks for the animals' enclosures. 
- He would very much like to get all the combinations.
- Let's start witha terrible implementation.

```markdown
package animals.security;
public class ComboLocks {
    public Map<String, String> combos;
}
```

- This is terrible because the *combos* object has *public* access. 
- This is also poor encapsulation.
- A key security principle is to limit access as much as possible.
- Think of it as "need to know" for objects. This is called the *principle of least privilege.*
- When studying for the 1ZO-815 exam, you learned about the four levels of acess control. It would be better to make the
  *combos* object *private* and write a method to provide the necessary functionality.

```markdown
package animals.security;
public class ComboLocks {
    private Map<String, String> combos;
    
    public boolean isComboValid(String animal, String combo) {
        var correctCombo = combos.get(animal);
        return combo.equals(correctCombo);
    }
}
```

- This is far better, we don't expose the combination map to any classes outside the *ComboLocks* class. For example,
  package-private is better than *public*, and *private* is better than package-private.
- If your application is using modules, you can do even better by only exporting  the security packages to the specific
  modules that should have access. Here's an example:

```markdown
exports animals.security to zoo.staff;
```

- In this example, only the *zoo.staff* module can access the *public* classes in the *animals.security* package.

## Restricting Extensibility

- Supposing you are working on a class that uses *ComboLocks*.

```markdown
public class GrasshopperCage {
    public static void openLock(ComboLocks comboLocks, String combo) {
        if (comboLocks.isComboValid("grasshopper", combo))
            System.out.println("Open!");
    }
}
```

- Ideally, the first variable passed to this method is an instance of the *ComboLocks* class.
- However, Hacker Harry is hard at work and has created this subclass of *ComboLocks*.

```markdown
public class EvilComboLocks extends ComboLocks {
    public boolean isComboValid(String animal, String combo) {
        var valid = super.isComboValid(animal, combo);
        if (valid) {
            // email the password to Hacker Harry
        }
        return valid;
    }
}
```

- This is great. Hacker Harry can check whether the password is valid and email himself all the valid passowrd.
- Luckily, there is an easy way to prevent this problem. Marking a sensitive class as *final* prevents any subclasses.

```markdown
public final class ComboLocks {
    private Map<String, String> combos;
    // instantiate combos object

    public boolean isComboValid(String animal, String combo){
        var correctCombo = combos.get(animal);
        return combo.equals(correctCombo);
    }
}
```

- Hacker Harry can't create his evil class, and users of the *GrasshopperCage* have the assurance that only the expected
  *ComboLocks* class can make an appearance.


| Question                                        | Answer                                                                                                                                                          |
| ----------------------------------------------- | --------------------------------------------------------------------------------------------------------------------------------------------------------------- |
| **How can a hacker extend a class in a JAR?**   | By creating their own subclass in another JAR or classloader, then passing it into your code via dependency injection, plugins, reflection, or deserialization. |
| **Is the author saying never use inheritance?** | No — just don’t allow it for **security-critical classes**. Inheritance is fine when designed carefully for well-documented APIs.                               |




## Creating Immutable Objects

- An immutable object is one that cannot change state after it is created.
- Immutable objects are helpful when writing secure code because you don't have to worry about the values changing.
- They also simplify code when dealing with concurrency.
- The *String* class used througout the book is immutable.
- In Chapter 3, "Generics and Collections", you used *List.of()*, *Set.of()*, and *Map.of()*. All three of these methods
  return immutable types.

---
- Although there are a variety of techniques for writing an immutable class, you should be familiar with a common strategy
  for making a class immutable.
1. Mark the class as final
2. Markall the instance variable *private*.
3. Don't define any setter methods and make fields final.
4. Don't allow referenced mutable objects to be modified.
5. Use a constructor to set all properties of the object, making a copy if needed.
---

- The fourth rule is subtler. Basically it means you shouldn't expose a getter method for a mutable object.
- For example, can you see why the following is not an immutable object?

```markdown
import java.util.*;

public final class Animal {
    public final ArrayList<String> favoriteFoods;
    
    public Animal() {
        this.favoriteFoods = new ArrayList<String>();
        this.favoriteFoods.add("Apples");
    }

    public List<String> getFavoriteFoods() {
        return favoriteFoods;
    }
}
```

- Hacker Harry can modify our data by calling *getFavoriteFoods().clear()* or add a food to the list that our animal doesn't like.
- It's not an immutable object if we can change it contents!
- If we don't have a getter for the *favoriteFoods* object, how do callers access it?
- Simple, by using delegate methods to read the data, as shown in the following:

```markdown
1: import java.util.*;
2:
3: public final class Animal {
4:    private final ArrayList<String> favoriteFoods;
5:
6:    public Animal() {
7:        this.favoriteFoods = new ArrayList<String>();
8:        this.favoriteFoods.add("Apples");
9:    }
10:   public int getFavoriteFoodsCount() {
11:         return favoriteFoods.size();
12:    }
13:    public String getFavoriteFoodsElement(int index) {
14:        return favoriteFoods.get(index);
15:    }
16: }
```

- In this improved version, the data is still available. 
- However, it is a true immutable object because the mutable variable connot be modified by the caller.
- Another option is to create a copy of the *favoriteFoods* object and return the copy anytime it is requested, 
  so the original remains safe.

```markdown
10:   public ArrayList<String> getFavoriteFoods() {
11:         return new ArrayList<String>(this.favoriteFoods);
12:    }
```

- Of course, changes in the copy won't be reflected in the original, but at least the original is protected from external changes.
- So, what's this about the last rule for creating immutable objects?
- Let's say we want to allow the user to provide the *favoriteFoods* data, so we implement the following:

```markdown
1:  import java.util.*;
2:  
3:   public final class Animal {
4:       private final ArrayList<String> favoriteFoods;
5: 
6:   public Animal(ArrayList<String> favoriteFoods) {
7:       if(favoriteFoods == null)
8:           throw new RuntimeException("favoriteFoods is required");
9:       this.favoriteFoods = favoriteFoods;
10:  }
11:  public int getFavoriteFoodsCount() {
12:      return favoriteFoods.size();
13:  }
14:  public String getFavoriteFoodsElement(int index){
15:      return favoriteFoods.get(index);
16:  }
17: }
```

- To ensure that *favoriteFoods* is not *null*, we validate it in the constructor and throw an exception if it is not provided.
- Hacker Harry is tricky, though. He decides to send us a *favoriteFood* object but keep his own secret reference to it,
  which he can modify directly.

```markdown
void modifyNotSoImmutableObject() {
    var favorites = new ArrayList<String>();
    favorites.add("Apples");
    var animal = new Animal(favorites);
    System.out.print(animal.getFavoriteFoodsCount());
    **favorites.clean();**
    System.out.print(animal.getFavoriteFoodsCount());
```

- This methods prints 1, followed by 0. Whoops!
- It seems like *Animal* is not immutable anymore, since is contents can change after it is created.
- The solution is to use a *copy* constructor to make a copy of the list object containing the same elements.

```markdown
6: public Animal(List<String> favoriteFoods) {
7:      if(favoriteFoods == null)
8:          throw new RuntimeException("favoriteFoods is required");
9:      this.favoriteFoods = new ArrayList<String>(favoriteFoods);
10: }
```

- The copy operation is called a *defensive copy* because the copy is being made in case other code does something unexpected.
- It's the same idea as defensive driving.
- Security Sienna has to be safe because she can't control what others do.
- With this approach, Hacker Harry is defeated. He can modify the original *favoriteFoods* all he wants, but it doesn't change the 
  *Animal* object's contents.


## Clonning Objects

- Java has a *Clonable* interface that you can implement if you want classes to be able to call the *clone()* method on
  your object.
- This helps with making defensive copies.
- The *ArrayList* class does just that, which means there's another way to write the statement on the line 9.

```markdown
9:      this.favoriteFoods = new ArrayList<String>(favoriteFoods);
```

- The *clone()* method makes a copy of an object. Let's give it a try by changing line 3 of the privous example to the following:

```markdown
public final class Animal implements Clonable {
```

Now we can write a method withing the Animal class:

```markdown
public static void (String... args) throws Exception {
    ArrayList<String> food = new ArrayList();
    food.add("grass");
    Animal sheep = new Animal(food);
    Animal clone = (Animal) sheep.clone();
    System.out.println(sheep = clone);
    System.out.println(sheep.favoriteFoods == clone.favoriteFoods);
}    
```

- This code outputs the following:

```markdown
false
true
```

- By default, the *clone* method makes a *shallow copy* of the data, which means only the top-level object references and
  primitives are copied. No new objects from within the cloned object are created. For example, if the object contains a
  reference to an *ArrayList*, a shallow copy contains a reference to that same *ArrayList*. Changes to the *ArrayList*
  in one object will be visible in the other since it is the same object.
- By contrast, you can write an implementation that does a deep copy and clones the objects inside. A deep copy does make
  a new *ArrayList* object. Changes to the cloned object do not affect the original.

```markdown
public Animal clone() {
    ArrayList<String> listClone = (ArrayList) favoriteFoods.clone();
    return new Animal(listClone);
}
```

- Now the *main()* method prints *false* twice because the *ArrayList* is also cloned.
- Inside the *Object* class there is a *clone()* method.
- The default implementation throws an exception that tells you the *Object* didn't implement *Clonable*.
- If the class implements *Cloneable*, you can call *clone()*.
- Classes that implement *Clonable* can also provide a custom implementation of *clone()*, which is useful when the class
  wants to make a deep copy.


<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_11/images/figure_1_1.png?raw=true" width="500" />

- In the last block, implementing-dependent means you should probably check the Javadoc of the overridden *clone()* method
  before using it. It may provide a shallow copy, a deep copy, or something else entirely.
- For example, it may be a shallow copy limited to three levels.

## Introducing Injection and Input Validation

- *Injection* is an attack where dangerous input runs in a program as a part of a command.
- For example, user input is often used in database queries or I/O.
- There are many sources of untrusted data. For the exam, you need to be aware of user input, reading from files, and 
  retrieving data from a database.
- In the real world, any data that did not originate from your program should be considered suspect.


## Preventing Injection with a PreparedStatement

- Our zoo application has a tabel named *hours* that keeps track of when the *zoo* is open to the public.

<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_11/images/figure_11_2.png?raw=true" width="500" />


## Using Statement

- We wrote a method that uses a *Statement*.
- In Chapter 10, "JDBC", we didn't use *Statement* because it is often unsafe.


```markdown
public int getOpening(Connection conn, String day) throws SQLException {
    String sql = 'SELECT opens FROM hours WHERE day = '" + day +"'";
    try (var stmt = conn.createStatement();
        var rs = stmt.executeQuery(sql)) {
        if (rs.next())
            return rs.getInt("opens");
    }
    return -1;
}
```

- Then, we call the code with one of the dats in the table.

```markdown
int opening = attack.getOpening(conn, "monday"); // 10
```

- This code does what we want.
- It queries the database and returns the opening time on the requested day.
- So far, so good. Then Hacker Harry comes along to call the method. He writes this:

```markdown
int evil = attack.getOpening(conn,
    "monday' OR day IS NOT NULL OR day = 'sunday");  // 9
```

- This does not return the expected value. 
- It returned 9 when we run it.
- Let's take a look at what Hacker Harry tricked our database into doing.
- Hacker Harry's parameter results in the following SQL, which we've formatted for readability:


```markdown
SELECT opens FROM hours
    WHERE day = 'monday'
       OR day IS NOT NULL
       OR day = 'sunday'
```

- It says to return any rows where *day* is *sunday*, *monday*, or any value that isn't *null*.
- Since none of the values in Figure 11.2 is *null*, this means all the rows are returned.
- Luckily, the database is kind enough to return the rows in the order they were inserted; our code reads the first row.


## Using PreparedStatement

- She reminds us that *Statement* is insecure because it is vulnerable to SQL injection.
- We switch our code to use *PreparedStatement*.

```markdown
public int getOpening(Connection conn, String day) throws SQLException {
    String sql = "SELECT opens FROM hours WHERE day = '" + day +"'";
    try (var ps = conn.prepareStatement(sql);
        var rs = ps.executeQuery()) {
        if (rs.next())
            return rs.getInt("opens");
    }
    return -1;
}
```

- Hacker Harry run his code, and the behavior hasn't changed.
- We haven't fixed the problem!
- A *PreparedStatement* isn't magic. It gives you the capability to be safe, but only if you use it properly.
- Security Sienna shows us that we need to rewrite the SQL statement using bind variables like we did in Chapter 10.

```markdown
public int getOpening(Connection conn, String day) throws SQLException {
    String sql = "SELECT opens FROM hours WHERE day = ?";
    try (var ps = conn.prepareStatement(sql)) {
      **ps.setString(1, day);**
        try (var ps = conn.prepareStatement(sql)) {
            if (rs.next())
                return rs.getInt("opens");
        }
        return -1;
}
```

- This time, Hacker Harry's code does behave differently.

```markdown
int evil = attack.getOpening(conn,
    "monday' or day is not null or day = 'sunday"); // -1
```

- The entire string is matched against the *day* column.
- Since there is no match, no rows are returned. This is far better!


## Invalidating Invalid Input with Validation

- SQL injection isn't the only type of injection.
- *Command injection* is another type that uses operating system commands to do something unexpected.
- The following code attempts to read the name of a subdirectory of *diets* and print out the names of all the *.txt* 
  files in that directory:


```markdown
Console console = System.console();
String dirName = console.readLine();
Path path = Paths.get("c:/data/diets/" + dirName);
try (Stream<Path> stream = Files.walk(path)) {
    stream.filter(p -> p.toString().endsWith(".txt"))
        .forEach(System.out::println);
```

- We tested it by typing in *mammals* and got the expected output.

```markdown
c:/data/diets/mammals/platypus.txt
```


<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_11/images/figure_11_3.png?raw=true" width="500" />

- Then Hacker Harry came along and typed .. as the directory name.

```markdown
c:/data/diets/../secrets/giraffeDueDate.txt
c:/data/diets/../diets/mammals/Platypus.txt
c:/data/diets/../diets/birds/turkey.txt
```

- Oh, no! Hacker Harry knows we are expecting a baby giraffe just from the filenames.
- We were not intending for him to see the *secret* directory.
- We decided to chat with Security Sienna about this problem. She suggests we validate the input.
- We will use a *whitelist* that allows us to specify which values are allowed.

```markdown
Console console = System.console();
String dirName = console.readLine();
    if (dirName.equals("mammal") || dirName.equals("birds")) {
      Path path = Paths.get("c:/data/diets/" + dirName); {
      try (Stream<Path> stream = Files.walk(path)) {
            stream.filter(p -> p.toString().endsWith(".txt"))
            .forEach(System.out::println);
      } catch (IOException e) {
        throw new RuntimeException(e);
      }
    }
}
```

- This time when Hacker Harry strikes, he doesn't see any output at all. 
- His input did not match the whitelist. When validation fails, you can throw an exception, log a message, or take any 
  other action of your choosing.

## Working with Confidential Information

- When working on a project, you will often encounter confidential or sensitive data.
- Sometimes there are even law that mandate proper handling of data like the Health Insurance Portability and Accountability Act
  (HIPAA) in the United States.
- Table 11.1 lists some examples of confidential information.

| Category                                | Examples                                                                                                    |
|-----------------------------------------|-------------------------------------------------------------------------------------------------------------|
| Login information                       | Usernames, <br> Passwords, <br> Hashes of passwords                                                         |
| Banking                                 | Credit card numbers, <br> Account balances, <br> Credit score                                               |
| PII (Personal identifiable information) | Social Security number (or other government ID, <br> Mother's maiden name, <br> Security questions/answers  |


## Guarding Sensitive Data from Output

- Avoid putting confidential information in a *toString()* method. That's just inviting  the information to wind up logged
  somewhere you didn't intend.
- Careful what methods call in these sensitive information. It is important to make sure it is being shared only per the requirements.

## Protecting Data in Memory

- Security Sienna needs to be careful about what is in memory.
- If her application crashes, it may generate a dump file.
- That contains values of everything in memory.
- When calling the *readPasssword()* on *Console*, it returns a *char[]* instead of a *String*.
- This is safer for two reasons.
  - It is not stored as a *String* pool, where it could exist in memory long after the code that used it is run.
  - You can *null* out the value of the array element rather than waiting for the garbage collector to do it.

- For example, this code overlays the password characters with the letter x:

```
  Console console = System.console();
  char[] password = console.readPassword();
  Arrays.fill(password, 'x');
```

- When the sensitive data cannot be overwritten, it is good practice to set confidential data to *null* when you're done 
  using it. If the data can be garbage collected, you don't have to worry about it being exposed later.
- Here's an example:

```
  LocalDate dateOfBirth = getDateOfBirth();
  // use date of birth
  dateOfBirth = null;
```

- The idea is to have confidential data in memory for as short a time as possible.

## Limiting File Access

- We saw ealier how to prevent command injection by validating requests. 
- Another way is to use a security policy to control what the program can access.


---
**Defense in Depth**
- Validation and using a security policy are good techniques to use together to apply defense in depth.
---

- For the exam, you don't need to know how to write or run a policy.
- You do need to be able to read one to understand security applications.
- Luckly, they are fairly self-explanatory.
- Here's an example of a policy.


```markdown
  grant {
      permission java.io.FilePermission
          "C:\\water\fish.txt",
          "read";
  }
```

- This policy gives the programmer permission to read, but not update, the *fish.txt* file.
- If the program is allowed to read and write the file, we specify the following:

```markdown
    grant {
      permission java.io.FilePermission
          "C:\\water\\fish.txt",
          "read, write";
    };
```

- When looking at a policy, pay attention to whether the policy grants access to more than is needed to run the program.
- If our application needs to read a file, it should only have *read* permissions. 
- This is the principle of least privilege we showed you earlier.

## Serializing and Deserialing Objects

- Imagine we are storing data in an **Employee** record.
- We want to write this data to a file and read this data back into memory, but we want to do so without writing any
  potentially sensitive data to disk.
- From *Chapter 8*, you should already know how to do this with serializaiton.
- Recall from *Chapter 8* that Java skips calling the constructor when deserializing an object. This means it is important
  not rely on the constructor for custom validation logic.
- Let's define our *Employee* class used throughout this section. Remember, it's important to mark it *Serializable*.


````markdown
import java.io.*;

public class Employee implements Serializable {
    private String name;
    private int age;
    // Constructor/getters/setters
````

## Specifying Which Fields to Serialize

- Our zoo has decided that employee age information is sensitive and shouldn't be written to disk.
- From *Chapter 8*, you should already know how to do this.
- Sienna reminds us that marking a field as *transient* prevents it from being serialized.

```markdown
private transient int age;
```

- Alternatively, you can specify fields to be serialized in an array.

```markdown
private static final ObjectStreamField[] serialPersistentFields = 
    { new ObjectStreamField("name", String.class) };
```

- You can think of *serialPersistentFields* as the opposite of *transient*.
- The former is a whitelist of fields that should be serialized, while the latter is a blacklist of fields that should not.

---
**Tip**

- If you go with the array approach, make sure you remember to use the private, static, and final modifiers.
- Otherwise, the field will be ignored.
---


## Customizing the Serialization Process

- Security may demand custom serialization. In our case, we got a new requirement to add the Social Security number to
  add the Social Security number to our object.
- Unlike age, we do need to serialize this information. However, we don't want to store the Social Security number in plain
  text, so we need to write some custom code.
- Take a look at the following implementation that uses *writeObject()* and *readObject()* for serialization, which you
  learned about in **Chapter 8**. For brevity, we'll use *snn* to stand for Social Security number.

```markdown
import java.io.*;

public class Employee implements Serializable {

    private String name;
    private String ssn;
    private int age;

    // Constructor/getters/setters

    private static final ObjectStreamField[] serialPersistentFields = 
        { new ObjectStreamField("name", String.class),
        new ObjectStreamField("snn", String.class) };


    private static String encrypt(String input) {
        // Implementation omitted
    }
    
    private static String decrypt(String input) {
        // Implemented omitted
    }

    private void writeObject(ObjectOutputStream s) throws Exception {
        ObjectOutputStream.PutField fields = s.putFields();
        fields.put("name", name);
        fields.put("ssn", encrypt(ssn));
        s.writeFields();
    }

    private void readObject(ObjectInputStream s) throws Exception {
        ObjectInputStream.GetField fields = s.readFields();
        this.name = (String) fields.get("name", null);
        this.ssn = decrypt((String) fields.get("ssn", null));
    }
}
```

- This version skips the *age* variable as before, although this time without using the *transient* modifier.
- It also uses custom read and write methods to securely encrypt/decrypt the Social Security number.
- Notice the *PutField* and *GetField* classes are used in order to write and read the fields easily.
- Suppose we were to update our *writeObject()* method with the *age* variable.

```markdown
  fields.put("age", age);
```

- When using serialization, the code would result in an exception.

```markdown
java.lang.IllegalArgumentException: no such field age with type int
```

- This shows the *serialPersistentFields* variables is really being used.
- Java is preventing us from referencing fields that were not declared to be serializable.


---
*Working with Passwords*

- In this example, we encrypted and then decrypted the Social Security number to show how to perform custom serialization
  for security reasons. Some fields are too sensitive even for that. In particular, you should never be able to decrypt 
  a password.
- When a password is set for a user, it should be converted to a *String* value using a salt (initial random value) and 
  one-way hashing algorithm. Then, when a user logs in, convert the value they type in using the same algorithm and 
  compare it with the stored value. This allows you to authenticate a user without having to expose their passwords.
- Databases of stored passwords can (and very often do) get stolen. Having them properly encrypted means the attacker can't 
  do much with them, like decrypt them and use them to log in to the system. They also can't use them to log in to other
  system in which the user used the same passwords more than once.
---


## Pre/Post-Serialization Processing

- Suppose our zoo employee application is having a problem with duplicate records being created for each employee.
- They decide that they want to maintain a list of all employees in memory and only create users as needed. Furthermore, 
  each employee' name is guaranteed to be unique. Unlikely in practice we know, but this is a special zoo!
- From what you learned about concurrent collection in *Chapter 7*, "Concurrency," and factory methods, we can accomplish
  this with a *private* constructor and factory method.

```markdown
  import java.io.*;
  import java.util.Map;
  import java.util.concurrent.ConcurrentHashMap;

  public class Employee implements Serializable {
  ...
  private Employee() {}
  private static Map<String, Employee> pool = 
     new ConcurrentHashMap<>();

  public synchronized static Employee getEmployee(String name) {
     if (pool.get(name)==null) {
        var e = new Employee();
        e.name = name;
        pool.put(name, e);
     }
     return pool.get(name);
  }
```

- This method creates a new *Employee* if one does not exist. Otherwise, it returns the one storedin the memory pool.


## Applying readResolve()

- Now we want to start reading/writing the employee data to disk, but we have a problem.
- When someone reads the data from the disk, it deserializes it into a new object, not the one in memory pool. This could 
  result in two users holding different versions of the *Employee* in memory!
- Enter the *readResolve()* method. When this method is present, it is run after the *readObject()* method and is capable
  of the object returned by deserialization.

```
import java.io.*;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Employee implements Serializable() {
  ...
  public synchronized Object readResolve() throws ObjectStreamException {
      var existingEmployee = pool.get(name);
      if(pool.get(name) == null) {
        // New employee not in memory
        pool.put(name, this);
        return this;
      } else {
        // Existing user already in memory
        existingEmployee.name = this.name
        existingEmployee.ssn = this.ssn;
        return existingEmployee;
      }
  }
}
```

- If the object is not in memory, it is added to the pool and returned. Otherwise, the version in memory is updated, and 
  its reference is returned.
- Notice that we added the *synchronized* modifier to this method. 
- Java allow any method modifiers (except *static*) for the *readResolve()* method including any access modifier.
- This rule applies to *writeReplace()*, which is up next.


## Applying writeReplace()

- Now, what if we want to write an *Employee* record to disk but we don't completely trust the instance we are holding?
- For example, we want to always write the version of the object in the pool rather than the *this* instance.
- By construction, there should be only one version of this object in memory, but for this example let's pretend we're 
  not 100 percent confident of that.
- The *writeReplace()* method is run **before** *writeObject()* and allows us to replace the object that gets serialized.


```markdown
  import java.io.*;
  import java.util.Map;
  import java.util.concurrent.ConcurrentHashMap;
  
  public class Employee implements Serializable {
    ...
    public Object writeReplace() throws ObjectStreamException {
        var e = pool.get(name);
        return e != null ? e : this;
    }
  }
```

- This implementation checks whether the object is found in the pool.
- If it is found in the pool, that version is sent for serialization; otherwise, the current instance is used.
- We could also update this example to add it to the pool if it is somehow missing.


