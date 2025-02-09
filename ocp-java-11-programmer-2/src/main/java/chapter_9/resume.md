# Chapter 9
# NIO.2

## I/O (Fundamentals and NIO2)
- Use the Path interface to operate on file and directory paths
- Use the Files class to check, delete, copy or move a file or directory
- Use the Stream API with Files


# In this Chapter

- We focus on the *java.nio* version2 API, or NIO.2 for short, to interact with files.
- NIO.2 is an acronym that stands for the second version of the Non-blocking Input/Output API, and it is sometimes
  referred to as the "New I/O."
- In this chapter, we will show how NIO.2 allows us to do a lot more with files and directories than the original
  *java.io* API.
- We'll also show you how to apply the Streams API to perform complex file and directory operations.
- We'll conclude this chapter by showing the various file attribute can be read and written using NIO.2.


## Introduction NIO.2

- At its core, NIO.2 is a replacement for the legacy *java.io.File* class you learned about in Chapter 8.
- By *legacy*, we mean that the preferred approach for working with files and directories with newer software applications
  is to use NIO.2, provides many features and performance improvements than the legacy class supported.


## What About NIO?

- This chapter focuses on NIO.2, not NIO.
- Java includes an NIO library that uses buffers and channels, in place of I/O streams.
- The NIO API was never popular.

## Introduction Path

- The cornerstone of NIO.2 is the *java.nio.file.Path* interface.
- A *Path* instance represents a hierarchical path on the storage system to a file or directory.
- You can think of a Path as the NIO.2 replacement for the *java.io.File* class, although how you use it is a bit different.
- Both *java.io.File* and *Path* objects may refer to an absolute path or relative path within the file system.
- In addition, both may refer to a file or a directory.
- Unlike the *java.io.File* class, the *Path* interface contains support for symbolic links. A *symbolic links* is a 
  a special file within a file system that servers as a reference or pointer to another file or directory.


<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_9/images/figure_9_1.png?raw=true" width="350" />

- In Figure above, the lion folder an its elements can be accessed directory or via the symbolic.
- For example, the following paths reference the same file:

```
/zoo/cats/lion/Cubs.java
/zoo/favorite/Cubs.java
```

```
Note:
You might wonder, why is Path an interface?
When a Path is created, the JVM returns a file system-specific implementation, such as a Windows or Unix Path class.
In the vas majority of circumstances, we want to perform the same operations on the Path, regardless of the file system.
By providing Path as an interface using the factory pattern, we avoid having to write complex or custom code for each 
type of file system.
```


## Obtain a Path with the Path Interface

- The simplest and most straightforward way to obtain a Path object is to use the static factory method defined within 
  Path interface.

// Path factory method
```
public static Path of (String first, String ... more)
```

```java
package chapter_9.path;

import java.nio.file.Path;

public class PathExample {

    public static void main(String[] args) {

        Path path1 = Path.of("pandas/cuddly.png");
        Path path2 = Path.of("c:\\zooinfo\\November\\employees.txt");
        Path path3 = Path.of("/home/zoodirectory");
    }
}
```

- The first example creates a reference to a relative path in the current working directory.
- The second example creates a reference to an absolute file path in a Windows-based system.
- The third example creates a reference to an absolute directory path in a Linux or Mac-based system.


## Absolute vs. Relative Paths

```
- Determining whether a path is relative or absolute is actually file-system dependent.
- If a path starts with a forward slash (/), it is absolute, with / as the root directory.
  Examples: /bird/parrot.png and /bird/../data/./info
- If a path starts with a driver letter (c:), it is absolute, with the drive letter as the root directory. 
  Examples: c:/bird/parrot.png and d:/bird/../data/./info
```

- The **Path.of()** method also includes a varargs to pass additional path elements.
- The values will be combined and automatically separated by the operational system dependent file separator you leraned 
  Chapter 8.

```java
package chapter_9.path;

import java.nio.file.Path;

public class PathExample_2 {

  public static void main(String[] args) {
    Path path1 = Path.of("pandas", "cuddly.png");
    Path path2 = Path.of("c:", "zooinfo", "November", "employees.txt");
    Path path3 = Path.of("/", "home", "zoodirectory");
  }
}
```
Note: These examples are just rewrites of our previous set of *Path* examples, using the parameter list of *String*
      values instead of a single *String* value. The advantage of the varargs is that it is more roboust, as it inserts
      the proper operating system path separator for you.

## Obtaining a Path with the Paths Class

- Another way to obtain a *Path* instance is from the *java.nio.file*Paths* factory class.
- Note the *s* at the end of the *Paths* to distinguish it from the *Path* interface.

// Paths factory method
```
public static Path get(String first, String ... more)
```

- Rewriting our previous examples is easy.

```java
package chapter_9.path;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample_3 {
    
    Path path1 = Paths.get("pandas/cuddly.png");
    Path path2 = Paths.get("c:\\zooinfo\\November\\employees.txt");
    Path path3 = Paths.get("/", "home", "zoodirectory");
}

```

## Obtaining a Path with a URI Class

- Another way to construct a **Path** using the *Paths* class is with URI value.
- A *uniform resource identifier (URI)* is a string of characters that identify a resource.
- It begins with a schema values include *file://* for local file system, and *http://*, *https://*, and *ftp://* for 
  remote file systems.

- The *java.net.URI* class is used to create URI values.

// URI Constructor <br>
```java
public URI(String str) throws URISyntaxException;
```

- Java includes multiples methods to convert between *Path* and URI objects.

// URI to Path, using Path factory method <br>
```java
public static Path of(URI uri);
```

// URI to Path, using Paths factory method
```java
public static Path get(URI uri);
```

// Path to URI, using Path instance method
```java
public URI toURI();
```


- The following examples all reference the same file:

```java
package chapter_9.path;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class PathExample_4 {

    public static void main(String[] args) throws URISyntaxException {
     
        URI a = new URI("file://icecream.txt");
        Path b = Path.of(a);
        Path c = Paths.get(a);
        URI d = b.toUri();
    }
}

```

- Some of these examples may actually throw an *IllegalArgumentException* at runtime, as some system 
  require URIs to be absolute.


## Connecting to Remote File Systems

- The *FileSystems* class does give us the freedom to connect to a remote file system, as follows:

// FileSystem factory method
public static FileSystem getFileSystem(URI uri)

The following shows how such a method can be used:

```java
package chapter_9.path;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class FileSystemExample {

    public static void main(String[] args) throws URISyntaxException {
        FileSystem fileSystem = FileSystems.getFileSystem(new URI("http://wwww.selikoff.net"));
        Path path = fileSystem.getPath("duck.txt");
    }
}

```

- This code is useful when we need to construct *Path* objects frequently for a remote file system.
- NIO.2 gives us the power to connect to both local and remote file systems, which is a major improvements over the 
  legacy *java.io.File* class.


## Obtain a Path from the java.io.File Class

// Path to File, using Path instance method
```java
public default File toFile();
```

// File to Path, using java.io.File instance method
```java
public Path toPath();
```

These methods are available for convenience and also to help facilitate integration between older and newer APIs.

```java
package chapter_9.path;

import java.io.File;
import java.nio.file.Path;

public class FileAndPathIntegrationExample {

    public static void main(String[] args) {
        File file = new File("husky.png");
        Path path = file.toPath();
        File backToFile = path.toFile();
    }
}
```

- NIO.2 Path interface contains a lot more features.


## Reviewing NIO.2 Relationships

- By now, you should realize that NIO.2 makes extensive use of the factory pattern.


<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_9/images/figure_9_2.png?raw=true" width="500" />


```markdown
Note: 
- The java.io.File is the I/O class you worked with in Chapter 8, while **Files** is an NIO.2 helper class.
- ** Files ** operates on *Path* instances, not *java.io.File* instances.
- We know this is confusing, but they are from completely different APIs!
```

## Understanding Common NIO.2 Features

- Throughout this chapter, we introduce numerous methods you should know for the exam.
- Before getting into the specifics of each method, we present many of these common features in this section so you are
  not surprised when you see them.

## Applying Path Symbols

- Absolute and relative paths can contain path symbols.
- For the exam, there are two path symbols you need to know, as listed below:

```markdown
| Symbol | Description                                          |
| .      | A reference to the current directory                 |
| ..     | A reference to the parent of the current directory   |
```

<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_9/images/figure_9_3.png?raw=true" width="500" />


## Providing Optional Arguments

- Many of the methods in this chapter include a varargs that takes an optional list of values.

<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_9/images/Table_9_2.png?raw=true" width="500" />


- Note that some of the enums Table 9.2 inherit an interface. That means some methods accept a variety of enums types.
- For example, the *File.move()* method takes a *CopyOption* varargs so it can take enums of different types.

```java
package chapter_9.path;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class Table9_2_Example {

    void copy(Path source, Path target) throws IOException {
        Files.move(source, target, LinkOption.NOFOLLOW_LINKS, StandardCopyOption.ATOMIC_MOVE);
    }
}

```


```markdown
Note: Many of the NIO.2 methods use a varargs for passing options, even when there is only one enum value available.
The advantage of this approach, as opposed to say just passing a boolean argument, is future-proofing.
These method signatures are insulated from changes in the Java language down the road when future options are available.
```

## Handling Methods That Declare IOException

- Many of the methods presented in this chapter declare IOException.
- Common causes of a method throwing this exception include the following:

```markdown
- Loss of communication to underlying file system.
- File or directory exist but cannot be accessed or modified.
- File exists but cannot be overwritten.
- File or directory is required but does not exist.
```

- In general, methods that operate on abstract *Path* values, such as those in the *Path* interface or *Paths* class,
  often do not throw any checked exceptions.
- Methods that operate or change files and directories, such as those in the *Files* class, often declare *IOException*.


## Interacting with Paths

- NIO.2 provides a *rich plethora* of methods and classes that operate on *Path* objects - far more than were available
  in the *java.io* API.
- Just like *String* values, *Path* instances are immutable.

```java
Path p = Path.of("whale");
p.resolve("krill");
System.out.println(p);
```

- Many of the methods available in the *Path* interface transform the path value in some way and return a new *Path* 
  object, allowing the methods to be chained.

```java
Path.of("/zoo/../home").getParent().normalize().toAbsolutePath();
```

## Viewing the Path with toString(), getNameCount(), and getName()

- The *Path* interface contains three methods to retrieve basic information about the path representation.

```java
public String toString();
public int getNameCount();
public Path getName(int index);
```



```java
package chapter_9.path.methods;

import java.nio.file.Path;
import java.nio.file.Paths;

public class BasicMethodPathExample {

  public static void main(String[] args) {
    Path path = Paths.get("/land/hippo/harry.happy");
    for (int i = 0; i < path.getNameCount(); i ++){
      System.out.println(" Element " + i + " is: " + path.getName(i));
    }
  }
}

```

- Output
```
 Element 0 is: land
 Element 1 is: hippo
 Element 2 is: harry.happy
```

- Even though this is an absolute path, the root element is not included in the list of names.
- As we said, these methods do not consider the root as part of the path.


```
var p = Path.of("/");
System.out.print(p.getNameCount()); // 0
System.out.println(p.getName(0)); // IllegalArgumentException
```

- Notice that if you try to call getName() with an invalid index, it will throw an exception at runtime.


## Creating a New Path with subpath()

- The *Path* interface includes a method to select portions of a path.

```java
public Path subpath(int beginIndex, int endIndex);
```

- The following code snippet shows how *subpath()* works. We also print the elements of the *Path* using *getName()* 
  so that you can see how the indices are used.

```java
package chapter_9.path.methods;

import java.nio.file.Paths;

public class SubPathExample {

    public static void main(String[] args) {

        var p = Paths.get("/mammal/omnivore/raccoon.image");
        System.out.println("Path is: " + p);
        for(int i = 0; i < p.getNameCount(); i++){
            System.out.println(" Element " + i + " is: " + p.getName(i));
        }

        System.out.println();
        System.out.println("subpath(0,3): " + p.subpath(0,3));
        System.out.println("subpath(1,2): " + p.subpath(1,2));
        System.out.println("subpath(1,3): " + p.subpath(1,3));
    }
}
```

```
Path is: /mammal/omnivore/raccoon.image
Element 0 is: mammal
Element 1 is: omnivore
Element 2 is: raccoon.image

subpath(0,3): mammal/omnivore/raccoon.image
subpath(1,2): omnivore
subpath(1,3): omnivore/raccoon.image
```

- Like getNameCount() and getName(), subpath() is 0-indexed and does not include the root.
- Also like getName(), subpath() throws an exception if invalid indices are provided.

```java
var q = p.subpath(0,4);  //IllegalArgumentException
var q = p.subpath(1,1);  //IllegalArgumentException
```

- The first example throws an exception at runtime, since the maximum index value allowed is 3.
- The second example throws an exception since the start and end indexes are the same, leading to an empty path value.


## Accessing Path Elements with getFileName(), getParent(), and getRoot()

- The *Path* interface contains numerous methods for retrieving particular elements of a *Path*, returned as *Path*
  objects themselves.

```java
public Path getFileName(); // Returns teh Path Element of the current file or directory
public Path getParent(); // Returns the full path of the containing directory. Returns null if it is root path
public Path getRoot(); //Returns the root element of the file within the fily system, or null if the path is a relative path
```

```java
package chapter_9.path.methods;

import java.nio.file.Path;
import java.nio.file.Paths;

public class PrintPathInformationExample {

    public static void main(String[] args) {
        PrintPathInformationExample printPathInformationExample = new PrintPathInformationExample();
        printPathInformation(Paths.get("zoo"));
        printPathInformation(Paths.get("/zoo/armadillo/shells.txt"));
        printPathInformation(Paths.get("./armadillo/../shells.txt"));
    }

    public static void printPathInformation(Path path) {
        System.out.println("Filename is: " + path.getFileName());
        System.out.println(" Root is: " + path.getRoot());
        Path currentParent = path;
        while ((currentParent = currentParent.getParent()) != null) {
            System.out.println(" Current parent is: " + currentParent);
        }
    }
}
```

- This sample application produces the following output:

```
Filename is: zoo
 Root is: null
Filename is: shells.txt
 Root is: /
 Current parent is: /zoo/armadillo
 Current parent is: /zoo
 Current parent is: /
Filename is: shells.txt
 Root is: null
 Current parent is: ./armadillo/..
 Current parent is: ./armadillo
 Current parent is: .
```

## Checking Path Type with isAbsolute() and toAbsolutePath()

- The Path interface contains two methods for assisting with relative and absolute paths:

```java
public boolean isAbsolute(); // Returns true if the path the object references is absolute and false if it is relative
public Path toAbsoutePath(); // Converts a relative Path object to an absolute Path object by joining it to the current working directory.
```

```markdown
Tip: The current working directory can be selected from System.getProperty("user.dir").
     This is the value that toAbsolutePath() will use when applied to a relative path.
```

- The following code snippet shows usage of both of these methods when run on a Windows and Linux system, respectively:

```java
package chapter_9.path.methods;

import java.nio.file.Paths;

public class AbsolutePathExample {

    public static void main(String[] args) {

        var path1 = Paths.get("C:\\birds\\egret.txt");
        System.out.println("Path1 is Absolute? " + path1.isAbsolute());
        System.out.println("Absolute Path1: " + path1.toAbsolutePath());

        var path2 = Paths.get("birds/condor.txt");
        System.out.println("Path2 is Absolute? " + path2.isAbsolute());
        System.out.println("Absolute Path2 " + path2.toAbsolutePath());
    }
}
```
- For the second example, assume the current working directory is /home/work.

```
Path1 is Absolute? true
Absolute Path1: C:\birds\agret.txt
Path2 is Absolute? false
Absolute Path2 /home/work/birds/condor.txt
```

## Joining Paths with resolve()

- 
