# Chapter 8

## Objectives

- Read data from and write console and data using I/O Streams
- Use I/O Streams to read and write files
- Read and write objects using serialization


- How can they save data so that information is not lost every time the program is terminated. They use files, of course!
- You can design code writes the current state of an application to a file every time the application is closed and then<br>
  reloads the data when the application is executed the next time. This chapter is using the java.io API to interact with
  fields and streams.

- Note: I/O streams are completely unrelated to the streams you saw in Chapter 4, "Functional Programming".

## Conceptualizing the File System

- When working with directories in Java, we often treat them like a files. 
- In fact, we use many of the same classes to operate on files and directories. 
- For example, a file and directory both can be renamed with the same Java method.

- To interact with the files, we need to connect to the ***File System***. 
- The file system is in charge of reading and writing data withing a computer. 
- Different ***Operating System*** use different ***File Systems*** to manage their data.

- Next, the ***root directory*** is the topmost directory in the ***File System***, from which all files and 
  directories inherit.

```
c:\ -> Windows
/   -> Linux
```


## Storing Data as Bytes

- Data is stored in a ***File System*** (and memory) as a *0* or *1*, called a bit. 
- Since it's really hard for humans to read/write data that is just *0s* they are grouped into a set of *8 bits*, 
  called a *byte*. 
- Values are often read or written streams using byte values and arrays.

## The ASCII Characters

- Using a little arithmetic *(2 power 8)*, we see a byte can be set in one *256* possible permutations. 
- These *256* values form the alphabet for our *computer system* to be able to type characters like ***a***, ***#***, and ***7***.

- Historically, the *256* characters are referred to ***ASCII characters***, based on the *encoding standard* that defined them.
- Given all of the *languages* and *emojis* available today, *256 characters* is pretty limiting. 
- Many newer standards have been developed that rely on additional bytes to display characters.

## Introducing the File Class

- The File class is used to read information about existing files and directors, list of contents of a directory, and 
  create/delete files and directories.
- An instance of a File class represents a path to a particular file or directory on the file system. The File class 
  cannot read or write data within a file, although it can be passed as a reference to many stream classes to read and 
  write data.

## Creating a File Object

- A File object often is initialized with a String containing either an absolute or relative path to the file or directory
  within the file system. The absolute path of a file or directory is the full path.
- Alternatively, the relative path of a file or directory is path from the current working directory to the file or 
  directory.
- For convenience, Java offers two options to retrieve the local separator character: a system property and a static 
  variable defined in the File class. Both of the following examples will output the separator character for the current
  environment:

```java
package chapter_8.file;

import java.io.File;

/**
 * The following code creates a File object and determines whether the path it references exists within the file system:
 */
public class FileSeparator {

    public static void main(String[] args) {
        System.out.println(System.getProperty("file.separator"));
        System.out.println(File.separator);

        System.out.println(System.getProperties());
    }
}
```


## The File Object vs. the Actual File

- When working with an instance of the File class, keep in mind that it only represents a path to a file. Unless operated
  upon, it is not connected to an actual file within the file system.

- If you try to operate on a file that does not exist or you do not have access to, some File methods will throw an 
  exception.

## Working with a File Object

Method Name:
```
boolean delete()
boolean exists()
String getAbsolutePath()
String getName()
String getParent()
boolean isDirectory()
boolean isFile()
long lastModified()
long length()
File[] listFiles()
boolean mkdir()
boolean mkdirs()
boolean renameTo(File dest)
```


## Understanding I/O Stream Fundamentals

- The contents of a file may be accessed or written via a stream, which is a list of data elements presented
sequentially. Stream should be conceptually thought of as a long, nearly never-ending "stream of water" with data
presented one "wave" at a time.
The stream is so large that once we start reading it, we have no idea where the beginning or the end is. We just have
a pointer to our current position in the stream and read data one block at a time.

- Each type of stream segments data into a "wave" or "block" in a particular way. 
- For example, some stream classes read or write data an individual bytes. 
- Other stream classes read or write individual characters or strings of characters. 
- On top of that, some stream classes read or write larger groups of bytes or characters at a time, specifically those 
  with the word Buffered in their name.

## All Java I/O Stream Use Bytes

- Although the java.io API is full of stream that handle characters, strings, groups of bytes, and so on, nearly all built
on top of reading or writing  an individual byte or an array of bytes at a time. 
- The reason higher-level stream exist is for convenience, as well as performance.

- For example, writing a file one byte at a time is time-consuming and slow in practice because the round-trip between
 the Java application and the file system is relatively expensive. 
- By utilizing a BufferedOutputStream, the Java application can write a large chunk of bytes at a time, reducing the 
  round-trips and drastically improving performance.

- Although streams are commonly used with file I/O, they are more generally used to ***handle the reading/writing of any
sequential data source***. 
- For example, you might construct a Java application that submits data to a website an output stream and reads the 
  result via an input stream.

## I/O Streams Can Be Big

- The file can still be read and written by a program with very little memory, since the stream allows the application to
focus on only a small portion of the overall stream at any given time.

## Byte Stream vs. Character Stream

- The java.io API defines two sets of stream classes for reading and writing streams. 

1- ***Bytes streams** read/write binary data (0s and 1s) and have class names that ***end in InputStream or OutputStream***.
2- ***Character streams** read/write *text data* and have class names that ***end in Reader or Writer***.

- The API frequently includes similar classes for ***both Byte and Characters streams***, such as 
  ***FileInputStream*** and ***FileReader***.

- It's important to remember that even though ***character streams*** do not contain the word ***Stream*** in their 
  class name, they are still ***I/O stream***.

- The ***Bytes streams*** are primarily used to work with ***binary data***, such as ***an image*** or ***executable file***, 
  while ***Characters streams*** are used to work with ***text files***.

- The ***character encoding*** determines how characters are encoded and stored in bytes in a stream and later read back 
  or ***decoded as characters***. 
- Although this may sound simple, Java supports a wide variety of characters encoding, ranging from ones that may use 
  one byte for Latin characters, UTF-8 and ASCII for example, to using two or more bytes per characters, such as UTF-16.

- For ***character encoding***, just remember that using a ***Character stream*** is better for working with text data 
  than a ***Byte stream***. The ***Character stream*** classes were created for convenience, and you should certainly 
  take advantage of them when possible.


## Input vs. Output Stream

- Most ***InputStream*** classes have a corresponding ***OutputStream*** class, and vice versa. 
- For example, the ***FileOutputStream*** class writes data that can be read by a ***FileInputStream***. 
- It follows, then, that most *Reader classes* have a corresponding *Writer class*. 
- For example, the *FileWriter class* writes data that can be read by a *FileReader class*.

- There are exceptions to this rules. 
- For the exam, you should know that ***PrintWriter*** *HAS NO* accompanying ***PrintReader*** class. 


## Low-level vs. High-Level Stream

- A low-level stream connects directly with the source of the data, such as:
  - a file
  - an array
  - a String. 
- Low-level stream process the raw data or resource and are accessed in a direct and unfiltered manner. 
- For example, a FileInputStream is a class that reads file data at a time.
- Alternatively, a high-level stream is built on top of another stream using wrapping. 
- Wrapping is the process by which an instance is passed to the constructor of another class, and operations on the 
  resulting instance are filtered and applied to the original instance.

```
  try ( var br = new BufferedReader(new FileReader("zoo-data.txt"))) {
      System.out.println(br.readLine());
  }
```

```
  try (var ois = new ObjectInputStream( new BufferedInputStream( new FileInputStream("zoo-data.txt")))){
    System.out.print(ois.readObject());
  }
```

## Use Buffered Stream When Working with Files

- Buffered classes read or write data in groups, rather than a single byte or character at a time. 
- The performance gain from using a Buffered class to access a low-level file stream cannot be overstated. 
- Unless you are doing something very specialized in your application, you should always wrap a file stream with a 
  Buffered class in practice.

- One of the reasons that Buffered stream tend to perform so well in practice is that many file system are optimized for
  sequential disk access. 
- For example, accessing 1,600 sequential bytes is a lot faster than accessing 1,600 bytes spread
  across the hard drive.

- The java.io library defines four abstract classes that are the parents of all stream classes defined within the API:
  - InputStream
  - OutputStream
  - Reader
  - Writer

## Note: 
- A low-level stream connects directly with the source of the data.

## The java.io abstract stream base classes

 | Class Name   | Description                                    |
 |--------------|------------------------------------------------|
 | InputStream  | Abstract class for all input byte stream       |
 | OutputStream | Abstract class for all output  byte stream     |
 | Reader       | Abstract class for all input character         |
 | Writer       | Abstract class for all output character stream |


## The java.io concrete stream classes

 |      Class Name      |  Low/High Level  |                                                 Description                                                  |
 |:--------------------:|:----------------:|:------------------------------------------------------------------------------------------------------------:|
 |   FileInputStream    |       Low        |                                           Reads file data as byte                                            |
 |   FileOutputStream   |       Low        |                                           Writes file data as byte                                           |
 |      FileReader      |       Low        |                                         Reads file data as character                                         |
 |      FileWriter      |       Low        |                                        Writes file data as character                                         |
 | BufferedInputStream  |       High       | Reads byte data from an existing InputStream in a buffered manner, which improves efficiency and performance |
 | BufferedOutputStream |       High       | Writes byte data to an existing OutputStream in a buffered manner, which improves efficiency and performance |
 |    BufferedReader    |       High       | Reads character data from an existing Reader in a buffered manner, which improves efficiency and performance |
 |    BufferedWriter    |       High       | Writes character data to an existing Writer in a buffered manner, which improves efficiency and performance  |
 |  ObjectInputStream   |       High       |                          Deserializes primitive Java data types and graphs of Java                           |
 |  ObjectOutputStream  |       High       |                           Serializes primitive Java data types and graphs of Java                            |
 |     PrintStream      |       High       |                     Writes formatted representations of Java objects to a binary stream.                     |
 |     PrintWriter      |       High       |                   Writes formatted representations of Java objects to a character stream.                    |


## Common I/O Stream Operations

- While there are a lot stream classes, many share a lot of the same operations.

## Reading and Writing Data

- Most important methods are read() and write(). 
- Both InputStream and Reader declare the following method to read byte data from a stream:

// InputStream and Reader
```java
  public int read() throws IOException
```


// OutputStream and Writer
```java
public int write() throws IOException
```


- We said we are reading and writing bytes, so why do the methods use int instead of byte?
- Remember, the byte datatype has a range of 256 characters. 
- They needed an extra value to indicate the end of a stream.
- The authors of Java decided to use a larger datatype, int, so that special values like -1 would indicate the end of a
  stream. 
- The output stream classes use int as well, to be consistent with the input stream classes.
- The byte stream classes also include overloaded methods for reading and writing multiples bytes at a time.

// InputStream
```java
public int read(byte[] b) throws IOException
public int read(byte[] b, int offset, int length) throws IOException
```


// OutputStream
```java
public void write(byte[] b) throws IOException
public void write(byte[] b, int offset, int length) throws IOException
```


- The offset and length are applied to the array itself. 
- For example, an offset of 5 and length of 3 indicates that the stream should read up to 3 bytes of data and put them 
  into the array starting with position 5.


## Close the Stream

- Since streams are considered resources, it is imperative that all I/O streams be closed after they are used lest they
  lead to resources leaks. 
- Since all I/O streams implement Closeable, the best way to do this is with a try-with-resources statement, which you 
  saw in Chapter 5, "Exception, Assertions, and Localization". 
- We will close stream resources using the try-with-resources syntax since this is the preferred way of closing 
  resources in Java.

- What about if you need to pass a stream to a method? 
- That's fine, but the stream should be closed in the method that created it. (More example: StreamToMethod.java)

```java

/**
 * In this example, the stream is created and closed in the readFile() method, with the printData() processing the
 * contents.
 */
public class StreamToMethod {

    public static void main(String[] args) throws IOException {
        StreamToMethod streamToMethod = new StreamToMethod();
        streamToMethod.readFile("pom.xml");
    }

    public void readFile(String fileName) throws IOException {
        try (var fis = new FileInputStream(fileName)) {
            printData(fis);
        }
    }

    private void printData(FileInputStream fis) throws IOException {
        int b;
        while((b = fis.read()) != -1){
            System.out.println(b);
        }
    }
}
```


## Closing Wrapped Stream

- When working with a wrapped stream, you only need to use close() on the topmost object.


## Manipulating Input Stream

- All input stream classes include the following method to manipulate the order in which data is read from a stream:

// InputStream and Read
```java
public boolean markSupported();
public void mark(int readLimit);
```

- The mark() and reset() methods return a stream to an earlier position. 
- Before calling either of these methods, you should call the markSupported() methods, which returns *true* only if 
  mark() is supported. 
- The skip() method is pretty simple; it basically reads data from the stream and discard the contents.

Note: 
- Not all input stream classes support mark() and reset(). 
- Make sure to call markSupported() on the stream before calling these methods or an exception will be thrown at runtime.

```java
public class MarkMethodExample {

    public static void main(String[] args) throws IOException {

        String str = "LION";
        InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.US_ASCII));
        readData(is);
    }

    private static void readData(InputStream is) throws IOException {
        System.out.print((char) is.read()); //L
        if(is.markSupported()){
            is.mark(100); //Marks up to 100 bytes
            System.out.print((char) is.read()); // I
            System.out.print((char) is.read()); // O
            is.reset(); //Resets stream to position before I
        }
        System.out.print((char) is.read()); // I
        System.out.print((char) is.read()); // O
        System.out.print((char) is.read()); // N
    }


}
```

## skip()

```java
public class SkipMethodExample {

    public static void main(String[] args) throws IOException {
        String str = "TIGERS";
        InputStream is = new ByteArrayInputStream(str.getBytes(StandardCharsets.US_ASCII));
        readData(is);
    }

    private static void readData(InputStream is) throws IOException {

        System.out.print( (char) is.read()); // T
        is.skip(2); // Skip I and G
        is.read(); // Reads E but doesn't output it
        System.out.print( (char) is.read()); // R
        System.out.print( (char) is.read()); // S

    }
}
```

## Flushing Output Stream

- When data is written to an output stream, the underlying operating system does not guarantee that the data will make 
  it to the file system immediately. 
- In many operating system, the data may be cached in memory, with write occurring only after a temporary cache is 
  filled or after some amount of time has passed.
- If the data is cached in memory and the application terminates unexpectedly, the data would be lost, because it was 
  never written to the file system.

// OutputStream and Writer
```java
public void flush() throws IOException;
```

```java
package chapter_8.stream;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class FlushMethodExample {

    public static void main(String[] args) {

        try (var fos = new FileOutputStream("alex.txt")) {
            for (int i = 0; i < 1000; i++) {
                fos.write('a');
                if (i % 100 == 0) {
                    fos.flush();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
```

- Note 1: ***The flush() method helps reduce the amount of data lost if the application terminates unexpectedly.***
- Note 2: ***Each time it is used, it may cause a noticeable delay in the application, especially for large files.***
- Note 3: ***You don't need to call flush() method when you have finished writing data, since the close() method will 
        automatically do this.***

## Reviewing Common I/O Stream Methods

| Stream Classes    | Method Name                                  |
|-------------------|----------------------------------------------|
| All Stream        | void close()                                 |
| All input Stream  | int read()                                   | 
| InputStream       | int read(byte[] b)                           |
| Reader            | int read(char[] c)                           |
| InputStream       | int read(byte[] b, int offset, int length)   |
| Reader            | int read(char[] c, int offset, int length)   |
| All output stream | void write(int)                              |
| OutputStream      | void write(byte[] b)                         |
| Write             | void write(char[] c)                         |
| OutputStream      | void write(byte[] b, int offset, int length) |
| Write             | void write(char[] c, int offset, int length) |
| All input stream  | boolean markSupport()                        |
| All input stream  | mark(int readLimit)                          |
| All input stream  | void reset()                                 |
| All input stream  | long skip(long n)                            |
| All output stream | void flush()                                 |



## Reading and Writing Binary Data

- The most basic file stream classes, ***FileInputStream*** and ***FileOutputStream***. 
- They are used reading bytes from a file or write bytes to a file.

```java
public FileInputStream(File file) throws FileNotFoundException;
public FileInputStream(String name) throws FileNotFoundException;
public FileOutputStream(File file) throws FileNotFoundException;
public FileOutputStream(String file) throws FileNotFoundException;
```


- If you need to append to an existing file, there's a constructor for that. 
- The ***FileOutputStream*** class includes overloaded constructor that take a *boolean* append flag. 
- When set to *true*, the output stream will append to the end of a file if it already exists. 
- This is useful for writing to the end of log files, for example.


## Buffering Binary Data

- We can easily enhance our implementation using ***BufferedInputStream*** and ***BufferOutputStream***. 
- As high-level stream, these classes include constructor that take other streams as input.

```java
public BufferedInputStream(InputStream in);
public BufferedOutputStream(OutputStream out);
```


- Why Use the Buffered Classes?

- Put simply, the Buffered classes contain a number of performance improvements for managing data in memory.
- For example, the BufferedInputStream class is capable of retrieving and storing in memory more data than you might 
- request with a single read(byte[]) call.

- The following shows how to apply these streams:

```java
public class CopyFileWithBufferExample {

    private void copyFileWithBuffer(File src, File dest) {
        try (var in = new BufferedInputStream(
                new FileInputStream(src));
             var out = new BufferedOutputStream(
                     new FileOutputStream(dest))) {
            var buffer = new byte[1024];
            int lengthRead;
            while ((lengthRead = in.read(buffer)) > 0) {
                out.write(buffer, 0, lengthRead);
                out.flush();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

```

## Choosing a Buffer Size

- Given the way computers organize data, it is often appropriate to choose a buffer size that is a power of 2, such as
  *1,024*. 
- Performance tuning often involves determining what buffer size is most appropriate for your application.
What buffer size should you use? Any buffer size that is power of 2 from 1,024 to 65,536 is a good choice in practice.

## Reading and Writing Characters Data

- The ***FileReader*** and ***FileWriter*** classes, along with their associated buffer classes, are among the most 
   convenient ***I/O classes*** of their ***built-in*** support for ***text data***. 
- They include constructors that take the same input as binary file classes.

```java
public FileReader(File file) throws FileNotFoundException;
public FileReader(String name) throws FileNotFoundException;

public FileWriter(File file) throws FileNotFoundException;
public FileWriter(String file) throws FileNotFoundException;
```

```java
package chapter_8.stream;

import java.io.*;

public class CopyTextFileExample {

    public static void main(String[] args) {
        CopyTextFileExample copyTextFileExample = new CopyTextFileExample();
        copyTextFileExample.copyTextFile(new File(""),new File(""));
    }

    private void copyTextFile(File src, File dest){
        try(var reader = new FileReader(src);
            var writer = new FileWriter(dest)) {
            int b;
            while((b = reader.read()) != -1){
                writer.write(b);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## Buffering Characters Data

- Like we saw with byte streams, Java includes high-level buffered characters streams that improve performance.

```java
public BufferedReader(Reader in);
public BufferedWriter(Writer out);
```

- They add two new methods:
  - readLine() 
  - newLine()
- That are particularly useful when working with String values.

```java
package chapter_8.stream;

import java.io.*;

/**
 * We are checking for the end of stream with a null value instead of -1. Finally, we are inserting a newLine() on every
 * iteration of the loop. This is because readLine() strips out the line break character. Without the call to newLine(),
 * the copied file would have all of its line breaks removed.
 */
public class CopyTextFileWithBufferExample {

    public static void main(String[] args) {
        CopyTextFileWithBufferExample copyTextFileWithBufferExample = new CopyTextFileWithBufferExample();
        copyTextFileWithBufferExample.copyTextFileWithBuffer(new File(""), new File(""));
    }

    private void copyTextFileWithBuffer(File src, File dest) {
        try (var reader = new BufferedReader(new FileReader(src));
             var write = new BufferedWriter(new FileWriter(dest))) {
            String s;
            while ((s = reader.readLine()) != null) {
                write.write(s);
                write.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
```

## Serializing Data

- **Serialization** is the **process** of **converting** an **in-memory object** to a **byte stream**. 
- Likewise, **deserialization** is the **process** of converting from a **byte stream** into an **object**. 
- **Serialization** often involves writing an object to a **stored** or **transmittable format**, 
  while **deserialization** is the **reciprocal process**.

## Applying the Serialization Interface

- To serialize an object using the I/O API, the object must implement the **java.io.Serializable interface**. 
- The Serializable interface is a maker interface. 
- By **marker interface**, it means the **interface does not have any methods**. 
- Any class can implement the Serializable interface since there are no required methods to implement.

- Note: Generally speaking, you should only mark **data-oriented classes** serializable.

- The purpose of using the Serializable interface is to inform any process attempting to serialize the object that you
  have taken the proper steps to make the object serializable.

```java
package chapter_8.serializable.domain;

import java.io.Serializable;

/**
 * In this example, the Gorilla class contains three instance members (name, age, friendly) that will be saved to a
 * stream if the class is serialized.
 *
 * What about the favoriteFood field that is market transient? Any field that is marked transient will not be saved
 * to a stream when the class is serialized.
 */
public class Gorilla implements Serializable {

    private static final long serialVersionUID = 1L;
    private String name;
    private int age;
    private Boolean friendly;
    private transient String favoriteFood;

    public Gorilla(String name, int age, Boolean friendly) {
        this.name = name;
        this.age = age;
        this.friendly = friendly;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Boolean getFriendly() {
        return friendly;
    }

    public void setFriendly(Boolean friendly) {
        this.friendly = friendly;
    }

    public String getFavoriteFood() {
        return favoriteFood;
    }

    public void setFavoriteFood(String favoriteFood) {
        this.favoriteFood = favoriteFood;
    }

    @Override
    public String toString() {
        return "Gorilla{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", friendly=" + friendly +
                ", favoriteFood='" + favoriteFood + '\'' +
                '}';
    }
}

```

## Maintaining a serialVersionUID

- It's good practice declaring a *static serialVersionUID* variable in every class that implements *Serializable*. 
- The *version* is stored with each object as part of serialization. 
- Then, every time the class structure changes, this value is updated or incremented.

- The *serialVersionUID* helps inform the JVM that the stored data may not match the new class definition. 
- If an older version of class is encountered during *deserialization*, a **java.io.InvalidClassException** may be thrown. 
- Alternatively, some APIs support converting data between versions.


## Marketing Data transient

- Oftentimes, the **transient** modifier is used for sensitive data of the class, like a password.
- What happens to data marked **transient** on deserialization? 
- It reverts to its default Java values, such as **0.0 for double**, or **null for an object**. 

- Note: Other than the *serialVersionUID*, only the instance members of a class are *serialized*.

## Ensuring a Class Is Serializable

- Any process attempting to **serialize** an object will throw a **NotSerializableException** if the class does not 
  implement the **Serializable** interface properly.

## How to Make a Class Serializable

1. The class must be marked **Serializable**
2. Every instance member of the class is *serializable*, marked *transient*, or has a *null* value at the time of serialization.

- Be careful with the second rule. 
- For a **class to be serializable**, we must apply the second rule recursively.

## Storing Data with ObjectOutputStream and ObjectInputStream

- The **ObjectInputStream** class is used to *deserialize* an object from a *stream*, while the **ObjectOutputStream** 
  is used to *serialize* an Object to a *stream*. They are *high-level stream* that operate on existing streams.

```java
public ObjectInputStream(InputStream in) throws IOException;
public ObjectOutputStream(OutputStream out) throws IOException;
```

- Two **methods** you need to know for the **exam** are the ones related to working with **objects**.

```java
// ObjectInputStream
public Object readObject() throws IOException, ClassNotFoundException;

// ObjectOutputStream
public void writeObject(Object obj) throws IOException;
```

- We now provide a sample method that serializes a List of Gorilla objects to a file.

```java
package chapter_8.serializable;

import chapter_8.serializable.domain.Gorilla;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A sample method that serializes a List of Gorilla object to a file.
 */
public class SerializeGorillaSample {

    /**
     * The following code snippet shows how to call the serialization methods.
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var gorillas = new ArrayList<Gorilla>();
        gorillas.add(new Gorilla("Alex", 5, false));
        gorillas.add(new Gorilla("Ishmael", 8, true));
        File dataFile = new File("gorilla.data");

        SerializeGorillaSample sample = new SerializeGorillaSample();
        sample.saveToFile(gorillas, dataFile);

        var gorillasFromDisk = sample.readFromFile(dataFile);
        System.out.println(gorillasFromDisk);
    }

    /**
     * Serialize a List of Gorillas
     *
     * @param gorillas
     * @param dataFile
     * @throws IOException
     */
    private void saveToFile(List<Gorilla> gorillas, File dataFile) throws IOException {
        try (var out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(dataFile)))) {
            for (Gorilla gorilla : gorillas) {
                out.writeObject(gorilla);
            }
        }
    }

    /**
     * When calling readObject, null and -1 do not have any special meaning, as someone might have serialized objects
     * with those values. Unlike our earlier techniques for reading methods from an input stream, we need to use an
     * infinite loop to process the data, which throws an EOFException when the end of the stream is reached.
     *
     * Tip: If your program happens to know the number of objects in the stream, then you can call readObject() a fixed
     * number of times, rather than using an infinite loop.
     *
     * Notice that readObject() declares a checked ClassNotFoundException since the class might not be available on
     * deserialization.
     *
     * Note: ObjectInputStream inherits an available() method from InputStream that you might think can be used to
     * check for the end of stream rather than throwing an EOFException. Unfortunately, this only tells you the number
     * of blocks that can be read without blocking another thread. In other words, it can return 0 even if there are
     * more bytes to be read.
     *
     * @param dataFile
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private List<Gorilla> readFromFile(File dataFile) throws IOException, ClassNotFoundException {

        var gorillas = new ArrayList<Gorilla>();

        try (var in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(dataFile)))) {
            while (true) {
                var object = in.readObject();
                if (object instanceof Gorilla)
                    gorillas.add((Gorilla) object);
            }
        } catch (EOFException e) {
            // File and reached
        }
        return gorillas;
    }
}

```

## Understand the Deserialization Creating Process

- When you deserialize an object, the constructor of the serialized class, along with any instance initializers, is not
  called when the object is created. 
- Java will call the **no-arg constructor** of the first **nonserializable parent class** it can find in the **class hierarchy**. 
- In our **Gorilla** example, this would just be the **no-arg constructor of Object**.

- As we stated earlier, **any static** or **transient fields** are **ignored**. 
- Values that are not provided will be given their **default Java value**, such as **null for String**, or **0** for **int values**.

```java
package chapter_8.serializable.domain;

import java.io.Serializable;

public class Chimpanzee implements Serializable {

    private static final long serialVersionUID = 2L;

    private transient String name;
    private transient int age = 10;
    private static char type = 'C';

    {
        this.age = 14;
    }

    public Chimpanzee(){
        this.name = "Unknown";
        this.age = 12;
        this.type = 'Q';
    }

    public Chimpanzee(String name, int age, char type){
        this.name = name;
        this.age = age;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public static char getType() {
        return type;
    }

    public static void setType(char type) {
        Chimpanzee.type = type;
    }

    @Override
    public String toString() {
        return "Chimpanzee{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", type=" + type +
                '}';
    }
}

```

```java
package chapter_8.serializable;

import chapter_8.serializable.domain.Chimpanzee;
import chapter_8.serializable.domain.Gorilla;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A sample method that serializes a List of Chimpanzee object to a file.
 *
 * Explanation:  Well, for starters, none of the instance members would be serializable to a file. The name and age
 * variables are both market transient, while the type variable are both market transient, while the type variable
 * is static. We purposely accessed the type variable using this to see whether you were paying attention.
 * Upon deserialization, none of the constructor in Chimpanzee is called. Even the no-arg constructor that sets the
 * value [ name=Unknown, age=12, type=Q ] is ignored. The instance initializer that sets age to 14 is also not executed.
 *
 * In this case, the name variable is initialized to null since that's the default value for String in Java.
 * Likewise, the age variable is initialized to 0. The program prints the following, assuming the toString() methods is
 * implemented.
 *
 * [Chimpanzee{name='null', age=0, type=B},
 *  Chimpanzee{name='null', age=0, type=B}]
 *
 *  What about the type variable? Since it's static, it will actually display whatever value was set last. If the data
 *  is serialized and deserialized within the same execution, then it will display B, since that was the last
 *  Chimpanzee we created. On the other hand, if the program performs the deserialization and print  on startup, then
 *  it will be C, since that is the value the class is initialized with.
 */
public class SerializeChimpanzeeSample {

    /**
     * The following code snippet shows how to call the serialization methods.
     * @param args
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        var chimpanzee = new ArrayList<Chimpanzee>();
        chimpanzee.add(new Chimpanzee("Ham", 2, 'A'));
        chimpanzee.add(new Chimpanzee("Enos", 4, 'B'));
        File dataFile = new File("chimpanzee.data");

        SerializeChimpanzeeSample sample = new SerializeChimpanzeeSample();
        sample.saveToFile(chimpanzee, dataFile);

        var chimpanzeesFromDisk = sample.readFromFile(dataFile);
        System.out.println(chimpanzeesFromDisk);
    }

    /**
     * Serialize a List of Chimpanzees
     *
     * @param chimpanzees
     * @param dataFile
     * @throws IOException
     */
    private void saveToFile(List<Chimpanzee> chimpanzees, File dataFile) throws IOException {
        try (var out = new ObjectOutputStream(
                new BufferedOutputStream(
                        new FileOutputStream(dataFile)))) {
            for (Chimpanzee chimpanzee : chimpanzees) {
                out.writeObject(chimpanzee);
            }
        }
    }


    private List<Chimpanzee> readFromFile(File dataFile) throws IOException, ClassNotFoundException {

        var chimpanzees = new ArrayList<Chimpanzee>();

        try (var in = new ObjectInputStream(
                new BufferedInputStream(
                        new FileInputStream(dataFile)))) {
            while (true) {
                var object = in.readObject();
                if (object instanceof Chimpanzee)
                    chimpanzees.add((Chimpanzee) object);
            }
        } catch (EOFException e) {
            // File and reached
        }
        return chimpanzees;
    }
}

```

## Real World Scenario

- We focus on serialization using the **I/O streams**, such as **ObjectInputStream** and **ObjectOutputStream**. 
- You should be **aware** there are many others **APIs to serialize data** to **JSON** or **encrypted data files**.
- While these APIs might not use **I/O stream classes**, many make use of **built-in Serializable interface** and 
  **transient modifier**. 
- Some of these APIs also include annotation to customize the serialization and deserialization of objects,
  such as what to do when are missing or need to be translated.


## Printing Data

- **PrintStream** and **PrintWriter** are **high-level output print streams classes** that are useful for writing 
  text data to a stream. **PrintStream** and **PrintWriter** include many of the same methods. Just remember that one 
  operates on an **OutputStream** and the other a **Writer**.

- The **PrintStream** classes have the *distinction* of being the **only I/O stream classes** we cover that **do not have**
  corresponding **input stream classes**. 
- And unlike other **OutputStream classes**, **PrintStream** does not have **Output** in its name.

- Following constructors:

```java
public PrintStream(OutputStream out);
public PrintWriter(Writer out);
```


- For convenience, **these classes** also include **constructors that automatically wrap** the print stream around a 
  low-level **file stream** class, such as **FileOutputStream** and **FileWriter**.

```java
public PrintStream(File file) throws FileNotFoundException;
public PrintStream(String file) throws FileNotFoundException;
```

```java
public PrintWriter(File file) throws FileNotFoundException;
public PrintWriter(String file) throws FileNotFoundException;
```

- The **PrintWriter** class even has a constructor that takes an **OutputStream** as input. 
- This is one of the **few exceptions** in which we can **mix a byte and character stream**.

```java
public PrintWriter(OutputStream out);
```

- Note: You've been regularly using a **PrintStream** throughout this book. 
- Both **System.out** and **System.err** are **PrintStream** objects. 
- Likewise, **System.in**, often useful for reading **user input**, is an **InputStream**.

- Besides, the inherited **write()** methods, the **print stream classes** include numerous methods for writing data 
  including **print(), println(), and format()**.

- When working with **String data, you should use Writer**, so our examples in this part of the chapter use **PrintWriter**.

## print()

- The most basic of the print-based methods is **print()**. 
- The **print stream classes** include numerous overloaded versions of print().

## println()

- The next methods available in the **PrintStream and PrintWriter** classes are the **println() methods**, which are 
- virtually identical to the **print() methods**, except that they also print a **line break** after the String value is 
  written.
- The **println()** methods are especially helpful, as the **line break character** is dependent on the operating system. 
- For example, in some system a line feed symbol, **\n**, signifies a **line break**, whereas **other system** use a 
  **carriage return symbol followed by a line feed symbol, \r\n**, to signify a **line break**.

```java
System.getProperty("line.separator");
System.lineSeparator();
```


## format()

- Each, **print stream** class includes a **format() method**, which includes an overloaded version that takes a Locale.

```java
// PrintStream
public PrintStream format(String format, Object args ...);
public PrintStream format(Locale loc, String format, Object ...);
```

```java
// PrintWriter
public PrintWriter format(String format, Object args ...);
public PrintWriter format(Locale loc, String format, Object args ...);
```


- Note: Java includes **printf() methods**, which function **identically** to the **format() methods**. 
- The only thing **you need to know** about these methods is that **they are interchangeable with format()**.

```java
package chapter_8.print;

public class FormatSample {

    public static void main(String[] args) {
        //format();
        //format2();
        //printException();
        format3();
    }


    /**
     * In the second format() operation, the parameters are inserted and formatted via symbols in the order that
     * they are provided in the vararg.
     */
    static void format(){
        String name = "Lindsey";
        int orderId = 5;

        // Both print: Hello Lindsey, order 5 is ready
        System.out.format("Hello " + name + ", order " + orderId + " is ready. ");
        System.out.format("Hello %s, order %d is ready.", name, orderId);
    }

    /**
     * The following example uses all four symbols.
     */
    static void format2(){
        String name = "James";
        double score = 90.25;
        int total = 100;

        System.out.format("%s:%nScore: %f out of %d", name, score, total);
    }

    /**
     * Mixing data type may cause exceptions at runtime. For example, the following throws an exception because a
     * floating-point number is used when an integer value is expected.
     */
    static void printException() {
        System.out.format("Food: %d tons", 2.0); // IllegalFormatConversionException
    }

    /**
     * The format() method supports a lot of other symbols and flags. You don't need to know any of them for the
     * exam beyond what we've discussed already.
     */
    static void format3(){

        var pi = 3.14159265359;
        System.out.format("[%f]", pi);  // [3.141593]
        System.out.format("[%12.8f]", pi);  // [  3.14159265]
        System.out.format("[%012f]", pi);  // [00003.141593]
        System.out.format("[%12.2f]", pi);  // [        3.14]
        System.out.format("[%.3f]", pi);  // [3.142]
    }
}

```


| Symbol | Description                                                     |
|:------:|-----------------------------------------------------------------|
|   %s   | Applies to any type, commonly String values                     |
|   %d   | Applies to integer values like int and long                     |
|   %f   | Applies to floating-point values like float and double          |
|   %n   | Inserts a line break using the system-dependent line separator. |

## Using format() with Flags

- By default, %f display six digits past the decimal. 
- If you want to display only one digit after the decimal, you could use %.1f instead %f. 
- The format() method relies on rounding, rather than truncating when shortening numbers. 
- For example, 90.250000 will be displayed as 90.3 (not 90.2) when passed to format() with %.1f

- You can specify the total length of output by using a number before the decimal symbol. 
- By default, the method will fill the empty space with black spaces. 
- You can also fill the empty space with zeros, by placing a single zero before the decimal symbol. 
- The following examples use brackets, [], to show the start/end of the formatted value:

```java
/**
     * The format() method supports a lot of other symbols and flags. You don't need to know any of them for the
     * exam beyond what we've discussed already.
     */
    static void format3(){
        var pi = 3.14159265359;
        System.out.format("[%f]", pi);  // [3.141593]
        System.out.format("[%12.8f]", pi);  // [  3.14159265]
        System.out.format("[%012f]", pi);  // [00003.141593]
        System.out.format("[%12.2f]", pi);  // [        3.14]
        System.out.format("[%.3f]", pi);  // [3.142]
    }
```

## Sample PrintWriter Program

```java
package chapter_8.print;

import java.io.*;

/**
 * You should pay close attention to the line breaks in the sample. For example, we called println() after our format(),
 * since format() does not automatically insert a line break after next text. One of the most common bugs with printing
 * data in practice is failing to account for line breaks properly.
 */
public class PrintWriteSample {

    public static void main(String[] args) throws IOException {
        File source = new File("zoo.log");
        try(var out = new PrintWriter(
                new BufferedWriter(new FileWriter(source)))){

            out.print("Today's weather is: ");
            out.println("Sunny");
            out.print("Today's temperature at the zoo is:");
            out.print(1 / 3.0);
            out.println('C');
            out.format("It has rained %5.2f inches this year %d", 10.2, 2021);
            out.println();
            out.printf("It may rain %s more inches this year", 1.2);
        }
    }
}

```

## Review of Stream Classes

- FilterInputStream and FilterOutputStream are high-level superclass that filter or transform data. 
- They are rarely used directly.

## InputStreamReader and OutputStreamWriter

- Most of the time, you can't wrap byte and character stream with each other, although as we mentioned, there are exceptions.

```java
package chapter_8.stream;

import java.io.*;

/**
 * The InputStreamReader class wraps an InputStream with a Reader, while the OutputStreamWriter class wraps an
 * OutputStream with a Writer.
 *
 * These classes are incredibly convenient and are also unique in that they are the only I/O stream classes to have
 * both InputStream / OutputStream and Reader / Writer in their name.
 */
public class ByteAndCharTogetherSample {

    public static void main(String[] args) throws IOException {
        try(Reader r = new InputStreamReader(System.in);
            Writer w = new OutputStreamWriter(System.out)){

        }
    }
}
```

## Interacting with Users

- The java.io API includes numerous classes for interacting with the user.

### Printing Data to the User

- The syntax for calling and using **System.err** is the same as **System.out** but is used to report errors to the user 
  in a separate stream from the regular output information.

```java
package chapter_8.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * If you are running from a command prompt, they will likely print text in the same format. On the other hand,
 * if you are working in an integrated development environment (IDE), they might print  the System.err  text in a different
 * color. 
 * Finally, if the code is being run on another server, the System.err stream might write to a different log file.
 */
public class SystemPrintExample {

    public static void main(String[] args) throws IOException {
        try(var in = new FileInputStream("zoo.txt")){
            System.out.println("Found file!");
        }catch (FileNotFoundException e){
            System.err.println("File not Found!");
        }
    }
}
```

## Reading Input as a Stream

- The **System.in** returns an **InputStream** and is used to retrieve text input from the user. 
- It is commonly wrapped with a **BufferedReader** via an **InputStreamReader** to use the **readLine()** method.

```java
package chapter_8.stream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * When executed, this application first fetches text from the user until the user presses the Enter key. 
 * It then outputs the text the user entered to the screen.
 */
public class ReadInputAsStreamSample {

    public static void main(String[] args) throws IOException {
        var reader = new BufferedReader( new InputStreamReader(System.in));
        String userInput = reader.readLine();
        System.out.println("You entered: " + userInput);
    }
}
```

## Closing System Streams

- You might have noticed that we never created or closed **System.out**, **System.err**, and **System.in** when we used them.
- Because these are static objects, the **System stream** are shared by the entire application. 
- The *JVM* **creates and opens** them for us.


What do you think the following code snippet prints?

```
try(var out = System.out){}
System.out.println("Hello");
```

- Nothing. It prints nothing. 
- Remember, the method of **PrintStream** do not throw any **checked exceptions** and rely on the **checkError()**
  to report errors. so they **fail silently**.

- What about this example?

```
try(var err = System.err){}
System.err.println("Hello);
```

- Nothing. Like **System.out**, **System.err** is a **PrintStream**. 
- Even if it did throw an exception, though, we'd have a hard time seeing it since our stream for reporting errors is closed! 
- Closing System.err is a particularly bad idea, since the stack traces from all exceptions will be hidden.

- What about this example?

```
var reader = new BufferedReader( new InputStreamReader(System.in));
try (reader){}
String data = reader.readline(); //IOException
```

- It prints an exception at runtime. 
- Unlike the **PrintStream** class, most **InputStream** implementations will **throw exception** if you try to operate 
- on a closed stream.


## Acquiring Input with Console

- The **java.io.Console** class is specifically designed to **handle user interactions**. 
- After all, **System.in** and **System.out** are just raw stream, whereas **Console** is a class with numerous methods 
  centered around user input.
- The **Console** class is a singleton because it is accessible only from a factory method and only one instance of 
  it is created by the JVM.

```java
package chapter_8.stream;

import java.io.Console;
import java.util.Arrays;
import java.util.Locale;

/**
 * The Console object may not be available, depending on where the code is being called. If it is not available, then
 * System.console() return null. It is imperative that you check for a null value before attempting to use a Console
 * object
 */
public class ConsoleSample {

    public static void main(String[] args) {

        console();
        consoleFormat();
        consoleWithLocale();
        consolePasswordMethod();
    }



    private static void consoleFormat() {
        Console console = System.console();
        if(console == null){
            throw new RuntimeException("Console not available");
        }else {
            console.writer().println("Welcome to Our Zoo!");
            console.format("It has %d animals and employs %d people", 391, 25);
            console.writer().println();
            console.printf("The zoo spans %5.1f acres", 128.91);
        }
    }

    private static void console() {
        Console console = System.console();
        if(console != null){
            String userInput = console.readLine();
            console.writer().println("You entered: " + userInput);
        }else {
            System.err.println("Console not available");
        }
    }

    private static void consoleWithLocale() {
        Console console = System.console();
        console.writer().format(new Locale("fr", "CA"), "Hello World");
    }

    private static void consolePasswordMethod(){

        Console console = System.console();
        if(console == null){
            throw new RuntimeException("Console not available");
        }else {
            String name = console.readLine("Please enter your name: ");
            console.writer().format("Hi %s", name);
            console.writer().println();

            console.format("What is your address? ");
            String address = console.readLine();

            char[] password = console.readPassword("Enter a password " + "between %d and %d characters ", 5, 10);

            char[] verify = console.readPassword("Enter your password again: ");

            console.printf("Passwords " + ((Arrays.equals(password, verify)) ? "match" : "do not match"));
        }

    }
}
```

## reader() and writer()

- The Console class includes access to two streams for reading and writing data.

```java
public Reader reader();
public PrintWriter writer();
```

- Accessing these classes is analogous to calling **System.in** and **System.out** directly, although they use 
  characters streams rather than byte stream. 
- In this manner, they are more appropriate for handling text data.

## format()

- For printing data with a Console, you can skip calling the **writer().format()** and output the data directly 
  to stream in a single call.

```
public Console format(String format, Object args...);
```

- The **format() method** behaves the same as the **format() method** on the **stream classes**, formatting and 
  printing a String while applying various arguments.

```java
private static void consoleFormat() {
        Console console = System.console();
        if(console == null){
            throw new RuntimeException("Console not available");
        }else {
            console.writer().println("Welcome to Our Zoo!");
            console.format("It has %d animals and employs %d people", 391, 25);
            console.writer().println();
            console.printf("The zoo spans %5.1f acres", 128.91);
        }
    }
```

## Using Console with a Locale

- Unlike the **print stream**, **Console** does not include an overloaded **format()** method that takes a 
  **Locale** instance. 
- Instead, **Console** relies on the **system locale**. Of course, you could always use a specific Locale by retrieving 
  the **Writer** object and passing your own **Locale** instance, such as in the following example:

```java
private static void consoleWithLocale() {
        Console console = System.console();
        console.writer().format(new Locale("fr", "CA"), "Hello World");
    }
```

```
readLine() and realPassword()
```


- The Console class includes four methods for retrieving regular text data from user.

```java
public String readLine();
public String readLine(String fmt, Object... args);
```

```java
public char[] readPassword();
public char[] readPassword(String fmt, Object... args);
```



- Like using **System.in** with a **BufferedReader**, the Console **readLine()** methods reads input until the user 
- presses the Enter key. 
- The overloaded version of readLine() displays a formatted message prompt prior to requesting input.

- The readPassword() methods are similar to the readLine() method with two important differences.

1) The text the user types is not echoed back and displayed on the screen as they are typing.
2) The data is returned as a char[] instead of a String.

- The first feature improves security by no showing the password on the screen if someone happens to be next to you. The
second feature  involves preventing passwords from entering the String pool discussed in Chapter 11.

```java
private static void consolePasswordMethod(){

        Console console = System.console();
        if(console == null){
            throw new RuntimeException("Console not available");
        }else {
            String name = console.readLine("Please enter your name: ");
            console.writer().format("Hi %s", name);
            console.writer().println();

            console.format("What is your address? ");
            String address = console.readLine();

            char[] password = console.readPassword("Enter a password " + "between %d and %d characters ", 5, 10);

            char[] verify = console.readPassword("Enter your password again: ");

            console.printf("Passwords " + ((Arrays.equals(password, verify)) ? "match" : "do not match"));
        }

    }
```


## Summary

- A common practice is to start with a low-level resource or file stream and wrap it in a buffered stream to improve
performance.

- Byte streams operate on binary data and have names that end with Stream, while characters stream operate on text data
and have names that end in Reader or Writer.

- Distinguish between low-level and high-level streams. 
- A low-level stream is one that operates directly on the underlying resources, such as file or network connections. 
- A high-level stream is one that operates on a low-level or other high-level stream to filter data, convert data, or improve performance.

- All streams include a **close()** method, which can be invoked automatically with a **try-with-resources**.

- Remember to call **markSupported()** before using **mark()** and **reset()**, as some streams do not support this operation.