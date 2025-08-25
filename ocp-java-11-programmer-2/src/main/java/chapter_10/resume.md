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