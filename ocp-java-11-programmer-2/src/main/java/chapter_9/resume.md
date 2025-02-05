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

- Determining whether a path is relative or absolute is actually file-system dependent.