# Chapter 10 - JDBC


## Database Applications with JDBC
    - Connect to databases using JDBC URLs and DriveManager
    - Use PreparedStatements to perform CRUD operations
    - Use PreparedStatements and CallableStatements APIs to perform databases operations


## This Chapter

    - It will introduce you to the basics of accessing databases from Java.
    - We will cover the key interfaces for how to connect, perform queries, and process the results
    

## Introducing Relational Databases and SQL 

- There are two main ways to access a relational databases from Java.
  - *Java Database Connectivity Language (JDBC):* Accesses data as rows and columns.
  - *Java Persistence API (JPA):* Accesses data through Java objects using a concept called object-relational mapping
    (ORM). The idea is that you don't have to write as much code, and you get your data in Java objects. JPA is not on 
    the exam, and therefore it is not convered in this chapter.

- A relational database is accessed through Structure Query Language(SQL).
- In additional to relational databases, there is another type of database called a NoSQL *database*. This is for 
  databases that store their data in a format other than tables, such as key/value, document stores, and graph-based 
  databases. NoSQL is out of scope for the exam as well.

## Writing Basic SQL Statements

- TABLE 10.1 CRUD operations

| Operation | SQL Keyword | Description                                |
|-----------|-------------|--------------------------------------------|
| Create    | INSERT      | Adds a new row to the table                |
| Read      | SELECT      | Retrieves data from the table              |
| Update    | UPDATE      | Changes zero or more rows in the tabel     |
| Delete    | DELETE      | Removes zero or more rows from the table   |


## Introducing the Interfaces of JDBC

- For the exam you need to know five key interfaces of JDBC.
- With JDBC, the concrete classes come from the JDBC driver. Each database has a different JAR file with these classes.
  For example, PostgresSQL's JAR is called something like *postgresql-9.4-1201.jdbc4.jar. MySQL's JAR is called something
  like mysql-connector-java-5.1.36.jar. The exact name depends on the vendor and version on the driver JAR.
- This driver JAR contains an implementation of these key interfaces along with number of other interfaces. The key is
  that the provided implementations know how to communicate with a database. There are also different types of drivers; 
  luckily, you don't need to know about this for the exam.

- Figure 10.2 shows the five key interfaces that you need to know. It also shows that the implementation is provided by 
  an imaginary *Foo* driver JAR. They cleverly stick the name *Foo* in all classes.

<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_10/images/figure_10_2.png?raw=true" width="500" />


- You shouldn't know what the implementation classes are called in any real database.
- With JDBC, you use only the interfaces in your code and never the implementation classes directly.
  In fact, they might not even be *public* classes.

- What do these five interfaces do?
  - Driver: Establishes a connection to the database
  - Connection: Sends commands to a database
  - PreparedStatement: Executes a SQL query
  - CallableStatement: Executes commands stored in the database
  - ResultSet: Reads results of a query

Note: All database interfaces are in the package java.sql, so we will often omit the imports

- We show you what JDBC code looks like end to end.

```java
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class MyFirstDatabaseConnection {

  public static void main(String[] args) throws SQLException {

    String url = "jdbc:derby:zoo";
    try (Connection conn = DriverManager.getConnection(url);
         PreparedStatement ps = conn.preparedStatement(
                 "SELECT name FROM animal" );
         ResultSet rs = ps.executeQuery()) {
        while(rs.next())
          System.out.println(rs.getString(1));
    }
  }
}
```

## Connecting to a Database

- The first step in doing anything with a database is connecting to it.

### Building a JDBC URL

- Unlike web URLs, a JDBC URL has a variety of formats. They have three parts in common, as shown in 
  Figure 10.3. The first piece is always the same. It is the protocol *jdbc*. The second part it the *subprotocol*, which
  is the name of the database such as *derby, mysql*, or *postgres*. The third part is the *subname*, which is a database-specific 
  format. Colons (:) separate the three parts.


<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_10/images/Figure_10_3.png?raw=true" width="500" />


- To make sure you get this, do you see what is wrong with each of the following?

```markdown
jdbc:postgresql://local/zoo
jdbc:mysql://123456/zoo
jdbc;oracle;thin;/localhost/zoo
```

- This first one *local* instead of *localhost*. The literal *localhost* is a specially defined name.
- You can't just make up a name. Granted, it is possible for our databas server to named *local*, but the exam won't have 
  you assume names. If the dtabase server has a special name, the question will let you know it.
- The second one says that the location of the database is *123456*. This doesn't make any random number. The third one is 
  no good because it uses semicolons (;) instead of colons(:).

---
**Real World Scenario**

- Using a DataSource

- In real applications, you should use a *DataSource* rather than *DriveManager* to get a *Connection*. For one thing,
  there's no reason why you should have to know the database password. It's far better if the database team or another
  team can set up a data source that you can reference.
- Another reason is that a *DataSource* maintains a connection pool so that you can keep reusing the same connection
  rather than needing to get a new one each time. Even the Javadoc says *DataSource* is prefered over *DriverManager*.
  But *DriverManager* is in the exam objects, so you still have to know it.
---


- The *DriverManager* class is in the JDK, as it is an API that comes with Java. It uses the factory pattern, which means
  that you call a *static* method to get a *Connection*, rather than calling a constructor.
- The factory pattern means taht you can get any implementation of the interface when calling the method. The good news 
  is that the method has an easy-to-remember name - *getConnection()*.


- To get *Connection* from the Derby database, you write the following:


```java
import java.sql.*;
public class TestConnect {
    public static void main(String[] args) throws SQLException {
        Connection conn = 
                DriverManager.getConnection("jdbc:derby:zoo");
      System.out.println(conn);
    }
}
```

- Running this example as *java TestConnect.java* will give you an error that looks like this:


<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_10/images/error_testconnection.png?raw=true" width="500" />

- Seeing SQLException means "something went wrong when connecting to or accessing the database."
- You will need to recognize when a *SQLException* is thrown, but not the exact message.

---
**Note**

- If code snippets aren't in a method, you can assume they are in a context where checked exception are handled or declared.
---

- In this case, we didn't tell Java where to find the database driver JAR file. 
- Remember that the implementation class for *Connection* is found inside a driver JAR.
- We try this again by adding the classpath with the following:

```java

java -cp "<path_to_derby>/derby.jar" TestConnect.java
```

- Remember to substitute the location of where the Derby JAR is located.

---
**Note**

- Notice that we are using single-file source execution rather than compiling the code first. This allows us to use a 
  simpler classpath since it has only one element.
---

- This time the program runs successfully and prints something like the following:

<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_10/images/success_testconnection.png?raw=true" width="500" />

- The details of the output aren't important. Just notice that the class is not *Connection*. It is a vendor implementation
  of *Connection*. It is a vendor implementation of *Connection*.

```java

import java.sql.DriverManager;
import java.sql.SQLException;

public class TestExternal {

  public static void main(String[] args) throws SQLException {
    Connection conn = DriverManager.getConnection(
            "jdbc:postgresql://localhost:5432/ocp-book",
            "username",
            "Password20182");
    System.out.println(conn);
  }
}
```

- It should go without saying that our password is not *Password20182. Also, don't put your password in real code. It is
  a horrible practice. Always load it from some kind of configuration, ideally one that keeps the store value encrypted.

- If you were to run this with the Postgres driver JAR, it would print something like this:

```markdown
org.postgresql.jdbc4.Jdbc4Connection@eed1f14
```

- Unless the exam specifies a command line, you can assume that the correct JDBC driver JAR is in the classpath.
- The exam creators explicitly ask about the driver JAR if they want you to think about it.
- *DriverManager* looks  through any drivers it can find to see whether they can handle the JDBC URL. If so, it creates 
  a *Connection* using that *Driver*. If not, it gives up and throws a *SQLException*.


---
**Real World Scenario**

- Using **Class.forName()**

- You might see *Class.forName()* in code. It was required with older drivers (that were designed for older version of 
  JDBC) before getting a *Connection*. It looks like this:

```java

import java.sql.DriverManager;
import java.sql.SQLException;

public static void main(String[] args) throws SQLException, ClassNotFoundException {

  Class.forName("org.postgresql.Driver");
  Connection conn = DriverManager.getConnection(
          "jdbc:postgresql://localhost:5432/ocp-book",
          "username",
          "passoword");
}
```

- *Class.forName()* loads a class before it is used. With newer drivers,
  *Class.forName()* is no longer required.
---

## Working with a PreparedStatement

- In Java, you have a choice of working with *Statement*, *PreparedStatement*, or *CallableStatement*. The latter two 
  are subinterfaces of *Statement*, as shown in Figure 10.4.

<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_10/images/Figure_10_4.png?raw=true" width="500" />


- *Statement* is an interface that both *PreparedStatement* and *CallableStatement* extend. A *Statement* and 
  *PreparedStatement* are similar to each other, except that a *PreparedStatement* takes parameters, while a *Statement*
  does not.
- A *Statement* just executes whatever SQL query you give it.

- While it is possible to run SQL directly with *Statement*, you shouldn't.
- *PreparedStatement* is far superior for the following reasons:

- **Performance**: In most programs, you run similar queries multiple times. A *PreparedStatement* figures out a plan 
  to run the SQL well and remembers it.
- **Security**: As you will see in Chapter 11, "Security", you are protected against an attack called SQL injection when 
  using a *PreparedStatement* correctly.
- **Readability**: It's nice not to have to deal with string concatenation in building a query string with lots of 
  parameters.
- **Future use**: Even if you query is being run only once or doesn't have any parameters, you should still use a 
  *PreparedStatement*. That way future editors of the code won't add a variable and have to remember to change to 
  *PreparedStatement* then.


- Using the *Statement* interface is also no longer in scope for the JDBC exam, so we do not cover in this book.


## Obtaining a PreparedStatement

- To run SQL, you need to tell a *PreparedStatement* about it. Getting a *PreparedStatement* from a *Connection* is easy.

```
try ( PreparedStatement ps = conn.prepareStatement(
        "SELECT * FROM exhibits")) {
         // work with ps
        }
```

- An instance of a *PreparedStatement* represents a SQL statement that you want to run using the *Connection*.
- It does not actually execute the query yet!

- Passing a SQL statement when creating the object is mandatory. You might see a trick on the exam.

```java
try (var ps = conn.prepareStatement()) { // DOES NOT COMPILE
}
```

- The previous example does not compile, because SQL is not supplied at the time a *PreparedStatement* is requested.
- There are overloaded signatures that allow you to specify a *ResultSet* type and concurrency mode. On the exam, you 
  only need to know how to use the default options, which process the result in order.


## Executing a PreparedStatement

### Modifying Data with executeUpdate()

- The name is a little tricky because the SQL UPDATE statement is not the only statement that uses this method.
- The method takes the SQL statement to run as a parameter. It returns the number of rows that were inserted, deleted, or
  changed. Here's an example of all three update types:

```
var insertSql = "INSERT INTO exhibits VALUES(10, 'Dear', 3)";
var updateSql = "UPDATE exhibits SET name = '' " + 
    "WHERE name = 'None'";
var deleteSql = "DELETE FROM exhibits WHERE id = 10";


try (var ps = conn.prepareStatement(insertSql)) {
    int result = ps.executeUpdate();
}

try (var ps = conn.prepareStatement(updateSql)) {
    int result = ps.executeUpdate();
    System.out.println(result); // 1
}

try (var ps = conn.prepareStatement(deleteSql)) {
    int result = ps.executeUpdate();
    System.out.println(result); // 1
}
```

- For the exam, you don't need to read SQL. The question will tell you how many rows are affected if you need to know.
- Note how each distinct SQL statement needs its own *prepareStatement()* call.


### Reading Data with execute Query()

```
var sql = "SELECT * FROM exhibits";
try (var ps = conn.prepareStatement(sql);
    ResultSet rs = ps.executeQuery() ) {
    // work with rs
}
```


### Processing Data with execute()

- There's a third method called *executed()* that can run either a query or an update. It returns a *boolean* so that we
  know whether there is a *ResultSet*. That way, we can call the proper method to get more detail. The pattern looks like 
  this:

```
boolean isResultSet = ps.execute();
if (isResultSet){
    try( ResultSet rs = ps.getResultSet()) {
        System.out.println("ran a query")
    }
} else {
    int result = ps.getUpdateCount();
    System.out.println("ran an update");
}
```

- If the *PreparedStatement* refers to *sql* that is a SELECT, the *boolean* is true and we can get *ResultSet*.
- If it is not a SELECT, we can get the number of rows updated.

### Reviwing PreparedStatement Methods

TABLE 10.2 SQL runnable by the *execute* method

| Method             | DELETE  | INSERT  | SELECT  | UPDATE  |
|--------------------|---------|---------|---------|---------|
| ps.execute()       | Yes     | Yes     | Yes     | Yes     |
| ps.executeQuery()  | No      | No      | Yes     | No      |
| ps.executeUpdate() | Yes     | Yes     | No      | Yes     |


TABLE 10.3 Return types of *execute* methods

| Method | Return type | What is returned for SELECT | What is returned for DELETE / INSERT / UPDATE |
| ps.execute() | boolean | true | false| 
| ps.executeQuery() | ResultSet | The rows and columns returned | n/a |
| ps.executedUpdate() | int | n/a | Number of rows added / changed / removed |


### Working with Parameters

- A *PreparedStatement*  allows to set parameters. Instead of specifying the three values in the SQL, we can use a 
  question mark(?) instead. A *bind variable* is a placeholder that leds you specify the actual values at runtime.
  A bind variable is like a parameter, and you will see bind variables referenced as both variables and parameters.

```java
String sql = "INSERT INTO names VALUES(?,?,?)";
```

- Bind variables make the SQL easier to read since you no longer need to use quotes around *String* values in the SQL.
- Now we can pass the parameters to the method itself.

```java
import java.sql.PreparedStatement;

public static void register(Connection conn, int key,
                            int type, String name) throws SQLException {

  String sql = "INSERT INTO names VALUES(?, ?, ?)";

  try (PreparedStatement ps = conn.prepareStatement(sql)){
      ps.setInt(1, key);
      ps.setString(3, name);
      ps.setInt(2, type);
      ps.executeUpdate();
  }
}
```


---
**Note**

- Remember that JDBC starts counting columns with 1 rather than 0. A common exam (and interview) question tests that you 
  know this!
---

- In the previous example, we set the paramters out of the order. That's perfectly fine.
- The rule is only that they are each set before the query is executed.
- Lest's see what happens if you don't set all the bind variables.

```
var sql = "INSERT INTO names VALUES(?,?,?)";
try (var ps = conn.prepareStatement(sql)){
    ps.setInt(1, key);
    ps.setInt(2, type);
    // missing the set for parameter number 3
    ps.executeUpdate();
}
```

- The code compiles, and you get a SQLException. The message may vary based on your database driver.

```markdown
At least one parameter to the current statement is uninitialized.
```

- What about if you try to set more values than you have as bind variables?

```
var sql = "INSERT INTO names VALUES(?,?)";
try (var ps = conn.prepareStatement(sql)){
    ps.setInt(1, key);
    ps.setInt(2, type);
    ps.setInt(3, name);
    ps.executeUpdate();
}
```

- Again, you get a SQLException, this time with a different message. On Derby, that message was as follows:

```markdown
The number of values assigned is not the same as the number of specified or implied columns.
```

Table 10.4 *PreparedStatement* methods

| Method name  | Parameter type | Example database type |
|--------------|----------------|-----------------------|
| setBoolean   | Boolean        | BOOLEAN               |
| setDouble    | Double         | DOUBLE                |
| setInt       | Int            | INTEGER               |
| setLong      | Long           | BIGINT                |
| setObject    | Object         | Any type              |
| setString    | String         | CHAR, VARCHAR         |


- You need to know only the first two columns for the exam because the third columns depends on the Database.
- Notice the setObject() method works with any Java type. If you pass a primitive, it will be autoboxed into wrapped type.
- That means we can rewrite our examples as follows:

```markdown
String sql = "INSERT INTO names VALUES(?, ?, ?)";
try (PreparedStatement ps = conn.prepareStatement(sql)) {
    ps.setObject(1, key);
    ps.setObject(2, type);
    ps.setObject(3, name);
    ps.executeUpdate();
}
```

- Java will handle the type conversion for you.
- It is still better to call the more specific setter methods since that will give you a compile-time error if you pass
  the wrong type instead of a runtime error.

### Updating Multiple Times

- Suppose we get two new elephants and want to add both. We can use the same *PreparedStatement* object.

```markdown
var sql = "INSERT INTO names VALUES(?, ?, ?)";

try (var ps = conn.prepareStatement(sql)) {
    
    ps.setInt(1, 20);
    ps.setInt(2, 1);
    ps.setString(3, "Ester");
    ps.executeUpdate();

    ps.setInt(1, 21);
    ps.setString(3, "Elias");
    ps.executeUpdate();

}
```

- Note that we set all three parameters when adding *Ester*, but only two for *Elias*.
- The *PreparedStatement* is smart enough to remember the parameter that were already set and retain them.
- You only have to set the ones that are different.



---
**Real World Scenario**

- Batching Statements

- JDBC supports batching so you can run multiple statements in fewer trips to the database. Often the database is located
  on a different machine than the Java code runs on. Savings trips to the database saves time because network call can be 
  expensive. For example, if you need to insert 1,000 records into the database, then inserting them as a single network 
  call (as opposed to 1,000 network calls) is usually a lot faster.

- You don't need to know the *addBatch()* and *excuteBatch()* methods for the exam, but they are useful in practice.

```java

public static void register(Connection conn, int firstKey, int type, String... names) throws SQLException {
    
    var sql = "INSERT INTO names VALUES(?, ?, ?, ?)";
    var nextIndex = firstKey;
    
    try (var ps = conn.prepareStatement(sql)) {
        ps.setInt(2, type);
        
        for(var name: names) {
            ps.setInt(1, nextIndex);
            ps.setString(3, name);
            ps.addBatch();
            
            nextIndex++;
        }
        int[] result = ps.executeBatch();
        System.out.println(Arrays.toString(result));
    }
}
```

- Now we call this method with two names:

```markdown
register(conn, 100, 1, "Elias", "Ester");
```

- The output shows the array has two elements since there are two different items in the batch.
- Each one added one row in the database.

```markdown
  [1, 1]
```

- When using batching, you should call *executeBatch()* at a set interval, such as every 10,000 records (rather than after ten million).
- Waiting too long to send the batch to the database could produce operations that are so large that they freeze the client
  (or even worse the database!).
---


## Getting Data from a ResultSet

### Reading a ResultSet

- When working with a *ResultSet*, most of the time you will write a loop to look at each row. The code looks like this:

```
String sql = "SELECT id, name FROM exhibits";
Map<Integer, String> idToNameMap = new HashMap<>();

try (var ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
    
    while (rs.next()){
        int id = rs.getInt("id");
        String name = rs.getString("name");
        idToNameMap.put(id, name);
    }
    
    System.out.println(idToNameMap);
}
```

- It outputs this:

```markdown
{1=African Elephant, 2=Zebra}
```

- A *ResultSet* has a cursor, which points to the current location in the data. Figure 10.5 shows the position as we 
  we loop through.

<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_10/images/Figure_10_5.png?raw=true" width="500" />

- Rewriting this same example with column numbers looks like the following:

```
String sql = "SELECT id, name FROM exhibits";
Map<Integer, String> idToNameMap = new HashMap<>();

try (var ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
    
    while (rs.next()){
        int id = rs.getInt(1);
        String name = rs.getString(2);
        idToNameMap.put(id, name);
    }
    
    System.out.println(idToNameMap);
}
```

- Sometimes you want to get only row the table. Maybe you need only one piece of data. Or maybe the SQL is just returning
  the number of rows in the table. 
- When you want only one row, you use an *if* statement rather than a *while* loop.

```
String sql = "SELECT id, name FROM exhibits";
Map<Integer, String> idToNameMap = new HashMap<>();

try (var ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery()) {
    
    if (rs.next()){
        int count = rs.getInt(1);
        System.out.println(count);
    }
}
```

- It is important to check that *rs.next()* returns *true* before trying to call a getter on the *ResultSet*.
- If a query didn't return any rows, it would throw a *SQLException*, so the *if* statement checks that it is safe to call.
- Alternatively, you can use the column name.

```
String sql = "SELECT count(*) AS count FROM exhibits";


try (var ps = conn.prepareStatement(sql);
        var rs = ps.executeQuery()) {
    
    if (rs.next()){
        int count = rs.getInt("count");
        System.out.println(count);
    }
}
```

- Let's try to read a column that does not exist.

```
String sql = "SELECT count(*) AS count FROM exhibits";

try (var ps = conn.prepareStatement(sql);
        var rs = ps.executeQuery()) {
    
    if (rs.next()){
        int count = rs.getInt("total");
        System.out.println(count);
    }
}
```

- This throws a *SQLException* with a message like this:

```markdown
Column 'total' not found.
```

- Attempting to access a column name or index that does not exist thorws a *SQLException*, as does getting data from a 
  *ResultSet* when it isn't pointing a valid row.

```
String sql = "SELECT * FROM exhibits WHERE name = 'Not in table'";

try (var ps = conn.prepareStatement(sql);
      var rs = ps.executeQuery()) {
    
      rs.next();
      rs.getInt(1); //SQLException
}
```


- In the next example, not calling *rs.next()* at all is a problem. 
- The result set cursor is still pointing to a location before the first row, so the getter has nothing to point to.
- How about this one?

```
String sql = "SELECT count(*) FROM exhibits";

try (var ps = conn.prepareStatement(sql);
      var rs = ps.executeQuery()) {
    
      if (rs.next())
        rs.getInt(0); //SQLException
}
```

- In the below example, since column indexes begin with 1, there is no column 0 to point to and a *SQLException* is thrown.

```
String sql = "SELECT count(*) FROM exhibits";

try (var ps = conn.prepareStatement(sql);
      var rs = ps.executeQuery()) {
    
      if (rs.next())
        rs.getInt("badColumn); //SQLException
}
```

- Trying to get a column that isn't in the *ResultSet* is just a bad as an invalid column index, and it also throws a 
  *SQLException*.
- To sum up this section, it is important to remember the following:
  - Always use an *if* statement or *while* loop when calling *rs.next()*.
  - Column indexes begin with 1.

## Getting Data a Column

- There are lots of *get* methods on the *ResultSet* interface.
- Table 10.5 shows the get methods that you need to know.
- These are the getter equivalents of the setters in Table 10.4.


| Method name  | Return type  |
|--------------|--------------|
| getBoolean   | boolean      |
| getDouble    | double       |
| getInt       | int          |
| getLong      | long         |
| getObject    | Object       |
| getString    | String       |


- Not all of the primitive types are in Table 10.5.
- There are *getByte()* and *getFloat()* methods, but you don't need to know them for the exam.
- There is no *getChar()* method. The exam will not try to trick you by using a *get* method name that doesn't exist for 
  JDBC. 

- The *getObject()* method can return any type. For a primitive, it uses the wrapper class. 
- Let's look at the following example.

```
var sql = "SELECT id, name FROM exhibits";

try (var ps = conn.prepareStatement(sql);
  var rs = ps.executeQuery()) {
  
  while (rs.next()) {
    Object idField = rs.getObject("id");
    Object nameField = rs.getObject("name");
    if (idField instanceof Integer) {
      int id = (Integer) idField;
      System.out.println(id);
    }
    if (nameField instanceof String) {
      String name = (String) nameField;
      System.out.println(name);
    }
  }
}
```

## Using Bind Variables

- We've been creating the *PreparedStatement* and *ResultSet* in the same *try-with-resources* statement.
- This doesn't work if you have bind variables because they need to be set in between. Luckily, we can nest try-with-resources
  to handle this.
- This code prints out the ID for any exhibits matching a given name:

```markdown
30: var sql = "SELECT id FROM exhibits WHERE name = ?";
31:
32: try (var ps = conn.prepareStatement(sql)) {
33:    ps.setString(1, "Zebra");
34: 
35:    try (var rs = ps.executeQuery()) {
36:        while (rs.next()) {
37:            int id = rs.getInt("id");
38:            System.out.println(id);
39:        }
40:    }
41: }
```

- Pay attention to the flow here. First, we create the *PreparedStatement* on line 32.
- Then we set the bind variable on line 33. It is only after these are both done that we have a nested try-with-resources
  on line 35 to create the *ResultSet*.


## Calling a CallableStatement

- Sometimes you want your SQL to be directly in the database instead of packaged with your Java code.
- This is particularly useful when you have many queries and there are complex.
- A *stored procedure* is code that is compiled in advance and stored in the database.
- Stored procedures are commonly written in a database-specific variant of SQL, which varies among database software provides.

- Using a stored procedure reduces network round-trips. It also allows database experts to own that part of the code.
- However, stored procedures are database-specific and introduce complexity of maintaining your application. On the exam, 
  you need to know how to call a stored procedure but not decide when to use one.


TABLE 10.6 Sample stored procedures

| Name                   | Parameter name  | Parameter type  | Description                                                                                   |
|------------------------|-----------------|-----------------|-----------------------------------------------------------------------------------------------|
| read_e_names()         | n/a             | n/a             | Returns all rows in the *names* table that have a name beginning with an e                    |
| read_names_by_letter() | prefix          | IN              | Returns all rows in the *names* table that have a name beginning with the specified parameter |
| magic_number()         | Num             | OUT             | Returns the number 42                                                                         |
| double_number()        | Num             | INOUT           | Muliplies the parameter by two and returns that number                                        |



## Calling a Procedure without Parameters

- Our read_e_names() stored procedures doesn't take any parameter.
- It does return a *ResultSet*.
- Since we worked with a *ResultSet* in the *PreparedStatement* section, here we can focus on how the stored procedure is called.

```markdown
12: String sql = "{call read_e_names()}";
13: try (CallableStatement cs = conn.prepareCall(sql);
14:     ResultSet rs = cs.executeQuery()) {
15:
16:     while (rs.next()) {
17:         System.out.println(rs.getString(3));
18:     }
19: }
```


## Passing an IN Parameter

- The *read_names_by_letter()* stored procedure takes a parameter for the prefix or first letter of the store procedure.
- An *IN* parameter is used for input.
- There are two differences in calling it compared to our previous stored procedure.

```markdown
25: var sql = "{call read_names_by_letter(?)}";
26: try (var cs = conn.prepareCall(sql)) {
27:     cs.setString("prefix", "Z");
28:
29:     try (var rs = cs.executeQuery()) {
30:         while (rs.next()) {
31:             System.out.println(rs.getString(3));
32:         }
33:     }
34: }
```

- On line 25, we have to pass a ? to show we have a parameter. This should be familiar from bind variables with a
  *PreparedStatement*.
- On line 27, we set the value of that parameter. Unlike with *PreparedStatement*, we can use either the parameter number
  (starting with 1) or the parameter name. That means these two statement are equivalent:

```markdown
cs.setString(1, "Z");
cs.setString("prefix", "Z");
```

## Returning an OUT Parameter

- In our previous examples, we returned a *ResultSet*. Some stored procedures return other information. Luckily, stored 
  procedures can have OUT parameters for output.
- The *magic_number()* stored procedures sets its *OUT* parameter to 42. There are a few differences here:


```markdown
40: var sql = "{?= call magic_number(?) }";
41: try (var cs = conn.prepareCall(sql)) {
42:     cs.registerOutParameter(1, Types.INTEGER);
43:     cs.execute();
44:     System.out.println(cs.getInt("num"));
45: }
```

- On line 40, we included two special characters (?=) to specify that the stored procedure has an output value.
- This is optional since we have the *OUT* parameter, but it does aid in readability.
- On line 42, we register the *OUT* parameter. This is important. It allows JDBC to retrieve the value on line 44.
- Remember to always call *registerOutParameter()* for each *OUT* or *INOUT* parameter (which we will cover next).
- On line 43, we call *execute()* instead of *executeQuery()* since we are not returning a *ResultSet* from our stored
  procedure.


---
**Database-Specific Behaviour**

- Some databases are lenient about certain things this chapter says are required.
- For example, some databases allow you to omit the following:
  - Braces ({})
  - Bind variables (?) if it is an *OUT* parameter
  - Call to *registerOutParameter()*

- For the exam, you need to answer according to the full requirement, which are described in this book.
- For example, you should answer exam questions as if braces are required.
---


## Working with an INOUT Parameter

- Finally, it is possible to use the same parameter for both input and output.

```markdown
50: var sql = "{call double_number(?)}";
51: try (var cs = conn.prepareCall(sql)) {
52:     cs.setInt(1, 8);
53:     cs.registerOutParameter(1, Types.INTEGER);
54:     cs.execute();
55:     System.out.println(cs.getInt("num"));
56: }
```
- For an *IN* parameter, line 50 is required since it passes the parameter.
- Similarly, line 52 is required since it sets the parameter.
- For an *OUT* parameter, line 53 is required to register the parameter.
- Line 54 uses *execute()* again because we are not returning a *ResultSet*.
- Remember that an *INOUT* parameter acts as both an *IN* parameter and an *OUT* parameter, so it has all the requirements
  of both.

## Comparing Callable Statement Parameters

- TABLE 10.7 Stored procedure parameters types

| d                                  | IN  | OUT  | INOUT |
|------------------------------------|-----|------|-------|
| Used for input                     | Yes | No   | Yes   |
| Used for output                    | No  | Yes  | Yes   |
| Must set parameter value           | Yes | No   | Yes   |
| Must call *registerOutParameter()* | No  | Yes  | Yes   |
| Can include ?=                     | No  | Yes  | Yes   |


## Closing Database Resources

- JDBC resources, such as a *Connection*, are expensive to create. Not closing them creates a *resources leak* that will 
  eventually slow down your program.
- We've been using try-with-resources syntax from Chapter 5. The resources need to be closed ina specific order.
- The *ResultSet* is closed first, followed by the *PreparedStatement* (or *CallableStatement*) and then the *Connection*.
- While it is good habit to close all three resources, it isn't strictly necessary. Closing a JDBC resource should close 
  any resources that it created. In particular, the following are true:
  - Closing a *Connection* also closes *PreparedStatement* (or *CallableStatement*) and *ResultSet*.
  - Closing a *PreparedStatement* (or *CallableStatement*) also closes the *ResultSet*.
- It is important to close resources in the right order. This avoids both resources leaks and exceptions.


---
**Writing a Resource Lead**

- In Chapter 5, you learned that it is possible to declare a type before a try-with-resources statement.
- Do you see why this method is bad?

```markdown
40: public void bad() throws SQLException {
41:     var url = "jdbc:derby:zoo";
42:     var sql = "SELECT not_a_column FROM names";
43:     var conn = DriverManager.getConnection(url);
44:     var ps = conn.prepareStatement(sql);
45:     var rs = ps.executeQuery();
46:
47:     try ( conn; ps; rs) {
48:         while ( rs.next())
49:             System.out.println(rs.getString(1));
50:     }
51: }
```

- Suppose an exception is thrown on line 45. The try-with-resources block is never entered, so we don't benefit from 
  automatic resources closing. That means this code has a resources leak if it fails. Do not write code like this.
---

- There's another way to close a *ResultSet*. JDBC automatically closes a *ResultSet* when you run another SQL statement
  from the same *Statement*. This could be a *PreparedStatement* or a *CallableStatement*.
- How many resources are closed in this code?

```markdown
14: var url = "jdbc:derby:zoo";
15: var sql = "SELECT count(*) FROM names where id = ?";
16: try (var conn = DriverManager.getConnection(url);
17:     var ps = conn.preparedStatement(sql)) {
18:
19:     ps.setInt(1, 1);
20:
21:     var rs1 = ps.executeQuery();
22:     while (rs1.next()) {
23:         System.out.println("Count: " + rs1.getInt(1));
24:     }
25: 
26:     ps.setInt(1, 2);
27:
28:     var rs2 = ps.executeQuery();
29:     while (rs.next()) {
30:         System.out.println("Count: " + rs2.getInt(1));
31:     }
32:     rs2.close();
33: }
```

- The correct answer is four. On line 28, rs1 is closed because the same *PreparedStatement* runs another query.
- On line 32, rs2 is closed in the method call.
- Then the try-with-resources statement runs and closes the *PreparedStatement* and *Connection* objects.


---
**Real World Scenario**

**Dealing with Exception**

- In most of this chapter, we've lived in a perfect world, we mentioned that a checked *SQLException* might be thrown by 
  any JDBC method - but we never actually caught it. We just declared it and the caller deal with it. Now let's catch it.
- We just declared it and the caller deal with it.
- Now let's catch the exception.

```markdown

  var sql = "SELECT not_a_column FROM name";
  var url = "jdbc:derby:zoo";
  try (var conn = DriverManager.getConnection(url);
    var ps = conn.prepareStatement(sql);
    var rs = ps.executiveQuery()) {
  
    while (rs.next())
        System.out.println(rs.getString(1));
    }
        System.out.println(e.getMessage());
        System.out.println(e.getSQLState());
        System.out.println(e.getErrorCode());
    }
```

- The output looks like this:
  
```markdown
  Column 'NOT_A_COLUMN' is either not in any table ...
  42X04
  30000
```

- Each of these methods gives you a different piece of information.
- The *getMessage()* method returns a human-readable message as to what wrong.
- We've only included the beginning of it here.
- The *getSQLState()* method returns a code as to what went wrong.
- You can Google tha name of your database and the SQLstate to get more information about the error.
- By comparison, *getErrorCode()* is a database-specific code.
- On this database, it doesn't do anything.

---



