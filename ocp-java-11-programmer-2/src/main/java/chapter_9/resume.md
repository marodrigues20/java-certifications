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




