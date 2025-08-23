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

<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_10/images/figure_10_2.png?raw=true" width="350" />



