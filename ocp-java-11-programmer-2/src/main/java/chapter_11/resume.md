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
c:/data/diets/mammals/Platypus.txt
```


<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_11/images/figure_11_3.png?raw=true" width="500" />

- Then Hacker Harry came along and typed .. as the directory name.



