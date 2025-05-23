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
- By *legacy*, we mean that the preferred approach for working with files and directories with newer software
  applications
  is to use NIO.2, provides many features and performance improvements than the legacy class supported.

## What About NIO?

- This chapter focuses on NIO.2, not NIO.
- Java includes an NIO library that uses buffers and channels, in place of I/O streams.
- The NIO API was never popular.

## Introduction Path

- The cornerstone of NIO.2 is the *java.nio.file.Path* interface.
- A *Path* instance represents a hierarchical path on the storage system to a file or directory.
- You can think of a Path as the NIO.2 replacement for the *java.io.File* class, although how you use it is a bit
  different.
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
| Symbol | Description |
| . | A reference to the current directory |
| .. | A reference to the parent of the current directory |
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

```
Path p = Path.of("whale");
p.resolve("krill");
System.out.println(p);
```

- Many of the methods available in the *Path* interface transform the path value in some way and return a new *Path*
  object, allowing the methods to be chained.

```
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
        for (int i = 0; i < path.getNameCount(); i++) {
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
        for (int i = 0; i < p.getNameCount(); i++) {
            System.out.println(" Element " + i + " is: " + p.getName(i));
        }

        System.out.println();
        System.out.println("subpath(0,3): " + p.subpath(0, 3));
        System.out.println("subpath(1,2): " + p.subpath(1, 2));
        System.out.println("subpath(1,3): " + p.subpath(1, 3));
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
var q = p.subpath(0, 4);  //IllegalArgumentException
var q = p.subpath(1, 1);  //IllegalArgumentException
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

- Suppose you want to concatenate paths in similar manner as we concatenate string. The *Path* interface provides two
  *resolve()* methods for doing just that.

```java
public Path resolve(Path other);

public Path resolve(String other);
```

- The first method takes a *Path* parameter, while the overloaded version is a shorthand form of the first that takes a
  String (and constructs the Path for you). The object on which the resolve() method is invoked becomes the basis of the
  Path object, with the input being appended onto the Path.

```java
package chapter_9.path.methods;

import java.nio.file.Path;

public class ResolveExample {

    public static void main(String[] args) {
        Path path1 = Path.of("/cats/../panther");
        Path path2 = Path.of("food");
        System.out.println(path1.resolve(path2));
    }
}
```

- The code snippet generates the following output:

```markdown
/cats/../panther/food
```

- In this example, the input argument to the *resolve()* method was a relative path, but what if it had been an absolute
  path?

```java
package chapter_9.path.methods;

import java.nio.file.Path;

public class ResolveExample {

    public static void main(String[] args) {
        Path path3 = Path.of("/turkey/food");
        System.out.println(path3.resolve("/tiger/cage"));
    }
}

```

- Since the input parameter path3 is an absolute path, the output would be the following:

```markdown
/tiger/cage
```

```markdown
Tip: On the exam, when you see Files.resolve(), think concatenation.
```

## Deriving a Path with relativize()

- The *Path* interface includes a method for constructing the relative path from one *Path* to another, often using path
  symbols.

```java
public Path relativeze();
```

```java
package chapter_9.path.methods;

import java.nio.file.Path;

public class RelativizeExample {

    public static void main(String[] args) {

        var path1 = Path.of("fish.txt");
        var path2 = Path.of("friend/birds.txt");
        System.out.println(path1.relativize(path2));  // ../friend/birds.txt
        System.out.println(path2.relativize(path1));  // ../../fish.txt
    }
}
```

- The examples print the following:

```markdown
../friend/birds.txt
../../fish.txt
```

- If you are pointed at a path in the file system, what steps would you need to take to reach the other path?
- For example, to get to *fish.txt* from *friend/birds.txt* you need to go up two levels (the file itself counts as one
  one level) and then select *fish.txt*.

- If both path values are relative, then the *relativize()* method computes the paths as if they are in the same current
  working directory.
- Alternatively, if both path values are absolute, then the method computes the relative path from one absolute location
  to another, regardless of the current working directory.
- The following example demonstrates this property when run on a Windows computer:

```java
private static void example2() {
    Path path3 = Paths.get("E:\\habitat");
    Path path4 = Paths.get("E:\\sanctuary\\raven\\poe.txt");
    System.out.println(path3.relativize(path4));
    System.out.println(path4.relativize(path3));
}
```

- This code snippet produces the following output:

```markdown
..\sanctuary\raven\poe.txt
..\..\..\habitat
```

- The code snippet works even if you do now have an E: in your system.
- Remember, most methods defined in the *Path* interface do not require the path to exist.

- The *relativize()* method requires that both paths are absolute or both relative and throws an exception if the types
  are mixed.

```java
package chapter_9.path.methods;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RelativizeExample {

    public static void main(String[] args) {
        Path path1 = Paths.get("/primate/chimpanzee");
        Path path2 = Paths.get("bananas.txt");
        path1.relativize(path2); // java.lang.IllegalArgumentException
    }
}
```

- On Windows-based systems, it also requires that if absolute paths are used, then both paths must have the same root
  directory or drive letter.
- For example, the following would also throw an *IllegalArgumentException* on a Windows-based system:

```java
package chapter_9.path.methods;

import java.nio.file.Path;
import java.nio.file.Paths;

public class RelativizeExample {

    public static void main(String[] args) {
        Path path3 = Paths.get("c:\\primate\\chimpanzee");
        Path path4 = Paths.get("d:\\storage\\bananas.txt");
        path3.relativize(path4); // java.lang.IllegalArgumentException
    }
}
```

## Cleaning Up a Path with normalize()

- So far, we've presented a number of examples that included path symbols that were unnecessary.
- Luckly, Java provides a method to eliminate unnecessary redundancies in a path.

```java
public Path normalize();
```

- We can apply *normalize()* to some of our previous paths.

```java
package chapter_9.path.methods;

import java.nio.file.Path;

public class NormalizeExample {

    public static void main(String[] args) {

        var p1 = Path.of("./armadillo/../shells.txt");
        System.out.println(p1.normalize()); // shells.txt

        var p2 = Path.of("/cats/../panther/food");
        System.out.println(p2.normalize()); // /panther/food

        var p3 = Path.of("../../fish.txt");
        System.out.println(p3.normalize()); // ../../fish.txt
    }
}
```

- The first two above examples, remove the redundancies.
- The last one, simplified as it can be.
- The *normalize()* method does not remove all of the path symbols; only the ones that can be reduced.

- The *normalize()* method also allows us to compare equivalent paths. Consider the following example:

```java
package chapter_9.path.methods;

import java.nio.file.Paths;

public class NormalizeExample2 {

    public static void main(String[] args) {
        var p1 = Paths.get("/pony/../weather.txt");
        var p2 = Paths.get("/weather.txt");
        System.out.println(p1.equals(p2));                          // false
        System.out.println(p1.normalize().equals(p2.normalize()));  // true
    }
}
```

- The *equals()* method returns true if two paths represent the same value.
- In the second comparasion, the path values have both been reduced to the same normalized value, /weather.txt.

## Retrieving the File System Path with toRealPath()

- While working with theorical paths is useful, sometimes you want to verify the path actually exists within the
  file system.

```java
import java.io.IOException;

public Path toRealPath(LinkOption... options) throws IOException;
```

- This method is similar to *normalize(), in that it eliminates any redundant path symbols.
- It is also similar to *toAbsolutePath()*, in that it will join the path with the current working directory if the path
  is relative.

- Let's say that we have a file system in which we have a symbolic link from /zebra to /horse.
- What do you think the following will print, given a current working directory of /horse/schedule?

```java
package chapter_9.path.methods;

import java.io.IOException;
import java.nio.file.Paths;

public class ToRealPathExample {

    public static void main(String[] args) throws IOException {

        System.out.println(Paths.get("/zebra/food.txt").toRealPath());
        System.out.println(Paths.get(".././food.txt").toRealPath());

    }
}
```

- The output of both lines is the following:

```markdown
/horse/food.txt
```

- We can also use the *toRealPath()* method to gain access to the current working directory as a *Path* object.

```markdown
System.out.println(Paths.get(".").

toRealPath());
```

## Reviewing Path Methods

- Table 9.3 Path methods

```markdown
|------------------------------|--------------------------------|
| Path of(String, String...)   | Path getParent()               |
| URI toURI()                  | Path getRoot()                 |
| File toFile()                | boolean isAbsolutePath()       |
| String toString()            | Path toAbsolutePath()          |
| int getNameCount()           | Path relativize()              |
| Path getName(int)            | Path resolve(Path)             |
| Path subpath(int, int)       | Path normalize()               |
| Path getFileName()           | Path toRealPath(LinkOption...) |
```

- Other than the *static* method *Path.of()*, all the methods in Table 9.3 are instance methods that can be called on
  any *Path* instance. In addition, only *toRealPath()* declares an IOException.

## Operating on File and Directories

- If you want to rename a directory, copy a file, or read the contents of a file.
- Enter the NIO.2 File class.
- The *Files* helper class is capable of interacting with real files and directories within the system.
- Because of this, most of the methods in this part of the chapter take optional parameters and throw an *IOException*
  if the path does not exist.
- The *Files* class also duplicates numerous methods found in the *java.io.File* class, albeit often with a different
  name or list of parameters.

## Checking for Existence with exists()

```java
public static boolean exists(Path path, LinkOption... options);
```

```java
package chapter_9.files;

import java.nio.file.Files;
import java.nio.file.Paths;

public class ExistsExample {

    public static void main(String[] args) {

        var b1 = Files.exists(Paths.get("/ostrich/feathers.png"));
        System.out.println("Path " + (b1 ? "Exists" : "Missing"));

        var b2 = Files.exists(Paths.get("/ostrich"));
        System.out.println("Path " + (b2 ? "Exists" : "Missing"));
    }
}
```

- The first example checks whether a file exits.
- The Second example checks whether a directory exists.

```
Tip: File and Directory can contain extension. Unless the exam tells you whether the path refers to a file or directory,
do not assume either.
```

## Testing Uniqueness with isSameFile()

- This method checks whether the file is the same or not. Because the path may include path symbols and symbolic links
  within a file system.

```java
import java.io.IOException;

public static boolean isSameFile(Path path, Path path2) throws IOException;
```

- This methods resolve all path symbols, and follows symbolic links.
- Despite the name, the method can also be used to determine whether two Path objects to the same directory.
- While most usages of *isSameFile()* will trigger an exception if the paths do not exist, there is a special case in
  which it does not. If the two path objects are equal, in terms of *equals()*, then the method will just return *true*
  without checking whether the file exists.
- Assume the file system as shown in Figure 9.4 with a symbolic link from /animal/snake to /animal/cobra.

<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_9/images/figure_9_4.png?raw=true" width="350" />

- Given the structure defined in Figure 9.4, what does the following output?

```java
package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class IsSameFileExample {

    public static void main(String[] args) throws IOException {

        System.out.println(Files.isSameFile(
                Path.of("/animals/cobra"),
                Path.of("/animal/snake")));

        System.out.println(Files.isSameFile(
                Path.of("/animals/monkey/ears.png"),
                Path.of("/animal/wolf/ears.png")));
    }
}
```

- Since cobra is a symbolic link to snake, the first example outputs true.
- In the second example, the paths refer to different files, so false is printed.

```markdown
Note: This isSameFile() method does not compare the contents of the files.
Two files may have identical names, content, and attributes, but if they are in different locations, then this
method will return false.
```

## Making Directories with createDirectory() and createDirectories()

- To create a directory, we use these *Files* methods:

```
public static Path createDirectory(Path dir, FileAttribute<?>... attrs) throws IOException;

public static Path createDirectories(Path dir, FileAttribute<?>... attrs) throws IOException;
```

- The **createDirectory()** will create a directory and throw and exception if it already exists or the paths leading up
  to the directory do not exist.

- The **createDirectories()**. If all of the directories already exist, *createDirectories()* will simply complete
  without doing anything.

- The following shows how to create directories in NIO.2:

```markdown
Files.createDirectory(Path.of("/bison/field"));
Files.createDirectories(Path.of("/bison/field/pasture/green"));
```

- The first example creates a new directory, *field*, in the directory */bison*, assuming */bison* exists; or else an
  exception is thrown. Contrast this with the second example, which creates the directory *green* along with any of the
  following parent directories if they do not already exist, including *bison*, *field*, and *pasture*.

## Copying Files with copy()

- The NIO.2 *Files* class provides a method for copying files and directories within the file system.

```markdown
public static Path copy(Path source, Path target, CopyOption... options) throws IOException
```

- The method copies a file or directory from one location to another using *Path* objects.
- The following shows an example of copying a file and a directory:

```markdown
Files.copy(Paths.get("/panda/bamboo.txt"),
Paths.get("/panda-save/bamboo.txt"));
```

```markdown
Files.copy(Paths.get("/turtle"), Paths.get("/turtleCopy"));
```

- When directories are copied, the copy is shallow.
- A *shallow copy* means that the files and subdirectories within the directory are not copied.
- A *deep copy* means that the entire tree is copied, including all of its content and subdirectories.

## Copying and Replacing Files

- By default, if the target already exits, the *copy()* method will throw an exception.
- You can change this behaviour by providing the *StandardCopyOption* enum value *REPLACE_EXISTING* to the method.

```markdown
Files.copy(Paths.get("book.txt"), Paths.get("movie.txt"),
StandardCopyOption.REPLACE_EXISTING);
```

## Copying Files with I/O Streams

- The *Files* class includes two *copy()* methods that operate with I/O streams.

```markdown
public static long copy(InputStream in, Path target,
CopyOption... options) throws IOException

public static long copy(Path source, OutputStream out)
throws IOException
```

- The first method reads the contents of a stream and writes the output to a file.
- The second method reads the contents of a file and writes the output to a stream.

```java
package chapter_9.files;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class CopyFileExample {

    public static void main(String[] args) throws IOException {

        try (var is = new FileInputStream("source-data.txt")) {
            // Write stream data to a file
            Files.copy(is, Paths.get("/mammals/wolf.txt"));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        Files.copy(Paths.get("/fish/clown.xsl"), System.out);
    }
}

```

## Copying Files into a Directory

- Let's say we have a file, *food.txt*, and a directory, */enclosure*.
- Both the file and directory exist.
- What do you think is the result of executing the following process?

```
import java.nio.file.Paths;

var file = Paths.get("food.txt");
var directory = Paths.get("enclosure");
Files.copy(file, directory);
```

- The code above throws an exception. The command tries to create a new file, named */enclosure*.
- Since the path */enclosure* already exists, an exception is thrown at runtime.
- On the other hand, if the directory did not exist, then it would create a new file with contents of *food.txt*, but
  it would be called */enclosure*.
- Remember, files not need to have extensions, and in this example, it matters.
- This behaviour applies to both the copy() and the *move()* methods.
  <br>
- This is the correct way to copy the file into a directory.

```
import java.nio.file.Paths;

var file = Paths.get("food.txt");
var directory = Paths.get("/enclosure/food.txt");
Files.copy(file, directory);
```

- You also define *directory* using the *resolve()* method we saw earlier. which saves you from having to write the
  filename twice.

```java
var directory = Paths.get("/enclosure").resolve(file.getFileName());
```

## Moving or Renaming Paths with move()

- The *Files* class provides a useful method for moving or renaming files and directories.

```java
import java.io.IOException;
import java.nio.file.CopyOption;

public static Path move(Path source, Path target,
                        CopyOption... options) throws IOException;
```

- The following is some sample code that uses the *move()* method:

```java
package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class MoveRenameExample {

    public static void main(String[] args) throws IOException {

        Files.move(Path.of("c:\\zoo"), Path.of("c:\\zoo-new"));

        Files.move(Path.of("c:\\user\\addresses.txt"),
                Path.of("c:\\zoo-new\\addresses2.txt"));
    }
}
```

- The first example renames the *zoo* directory to a *zoo-new* directory, keeping all of the original contents from the
  source directory.
- The second example moves the *addresses.txt* file from the directory *user* to the directory *zoo-new*, and it renames
  it to *address2.txt*.

## Similarities between move() and copy()

```markdown
- Like copy(), move() requires REPLACE_EXISTING to overwrite the target if it exists, else it will throw an exception.
  Also like copy(), move() will not put a file in a directory if the source is a file and the target is a directory.
  Instead, it will create a new file with the name of the directory.
```

## Performing an Atomic Move

```markdown
Files.move(Path.of("move.txt"), Path.of("gerbil.txt"), StandardCopyOption.ATOMIC_MOVE);
```

- Remember the atomic from Chapter 7, "Concurrency", and the principal of an atomic move is similar.
- An atomic move is one in which a file is moved within the file system never sees an incomplete or partially written
  file. If the file system does not support this feature, an *AtomicMoveNotSupportedException" will be thrown.

## Deleting a File with delete() and deleteIfExists()

- The *Files* class includes two methods that delete a file or empty directory within a file system.

```markdown
public static void delete(Path path) throws IOException
public static boolean deleteIfExists(Path path) throws IOException
```

- To delete a directory, it must be empty.
- Both of these methods throw an exception if operated on a noneempty directory.
- If the path is a symbolic link, then the symbolic link will be deleted, not the path that the symbolic link points so.

```java
package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class DeleteExample {

    public static void main(String[] args) throws IOException {
        Files.delete(Paths.get("/vulture/feathers.txt"));
        Files.deleteIfExists(Paths.get("/pigeon"));
    }
}

```

- The first example deletes a *feather.txt* file in the *vulture* directory, and it throws a *NoSuchFileException* if
  the file or directory does not exist.
- The second example deletes the *pigeon* directory, assuming it is empty. If the *pigeon* directory does not exit, then
  the second line will not throw an exception.

## Reading and Writing Data with newBufferedReader() and new BufferedWriter()

- NIO.2 includes two convenient methods for working with I/O streams.

```markdown
public static BufferedReader newBufferedReader(Path path) throws IOException

public static BufferedWriter newBufferedWriter(Path path, OpenOption... options) throws IOException
```

- You can wrap I/O stream constructors to produce the same effect, although it's a lot easier to use the factory method.

```markdown
Note:

- There are overloaded versions of these methods that take a Charset. You may remember that we briefly discussed
  character encoding and Charset in Chapter 8.
- For this chapter, you just need to know that characters can be encoded in bytes in a variety of ways.
```

- The first method, *newBufferedReader()* reads the file specified at the *Path* location using a *BufferedReader*
  object.

```java
package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class NewBufferedReaderExample {

    public static void main(String[] args) throws IOException {
        var path = Path.of("/animals/gopher.txt");
        try (var reader = Files.newBufferedReader(path)) {
            String currentLine = null;
            while ((currentLine = reader.readLine()) != null) {
                System.out.println(currentLine);
            }
        }
    }
}
```

- This example reads the lines of the files using a *BufferedReader* and outputs the contents to the screen.
- As you shall see shortly, there are other methods that do this without having to use an I/O stream. \


- The second method, *newBufferedWriter(), writes to a file specified at the *Path* location using a *BufferedWriter*.

```java
package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class NewBufferedWriterExample {

    public static void main(String[] args) {
        var list = new ArrayList<String>();
        list.add("Smokey");
        list.add("Yogi");

        var path = Path.of("animals/bear");
        try (var writer = Files.newBufferedWriter(path)) {
            for (var line : list) {
                writer.write(line);
                writer.newLine();
                ;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
```

## Reading a File with readAllLines

- The *Files* class includes a convenient method for turning the lines of a file into a *List*.

```markdown
public static List<String> readAllLines(Path path( throws IOException
```

- The following sample code reads the lines of the file and outputs them to the user:

```java
package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class ReadAllLinesExamples {

    public static void main(String[] args) throws IOException {

        var path = Path.of("animals/gopher.txt");
        final List<String> lines = Files.readAllLines(path);
        lines.forEach(System.out::println);
    }
}
```

## Reviewing Files Methods

- Table 9.4 shows the *static* methods in the methods in the *Files* class you should be familiar with for the exam.

```
| Methods                                           | Methods
|---------------------------------------------------|----------------------------------------|
| boolean exists(Path, LinkOption...)               | Path move(Path, Path, CopyOption...)   |
| boolean isSameFile(Path, Path)                    | void delete(Path)                      |
| Path createDirectory(Path, FileAttribute<?>...)   | boolean deleteIfExists(Path)           |
| Path createDirectories(Path, FileAttribute<?>...) | BufferedReader newBufferedReader(Path) |
| Path copy(Path, Path, CopyOption...)              | List<String> readAllLines(Path)        | 
| long copy(Path, OutputStream)                     |                                        |   

```

- All of these methods except *exists()* declare *IOException*

## Managing File Attributes

- The *Files* class also provides numerous methods for accessing file and directory metadata, referred to as
  *file attributes*. A file attribute is data about about a file within the system, such as its size and visibility,
  that is not part of the file contents. In this section, we'll show how to read file attributes individually or as a
  single.

## Discovering File Attributes

- These methods are usable within any file system although they may have limited meaning in some file systems.

## Reading Common Attributes with isDirectory, isSymbolicLink(), and isRegularFile()

- The *Files* class includes three methods for determining type of a *Path*.

```markdown
public static boolean isDirectory(Path path, LinkOption... options)

public static boolean isSymbolicLink(Path path)

public static boolean isRegularFile(Path path, LinkOption... options)
```

- Java defines a *regular file* as one that can contain content, as opposed to a symbolic link, directory, resource,
  or other non regular file that may be present in some operating systems.
- If the symbolic link points to an actual file, Java will perform the check on the target of the symbolic link.
- In other words, it is possible for *isRegularFile()* to return *true* for a symbolic link, as long as the link
  resolves to a regular file.

- Let's take a look at some sample code.

```java
package chapter_9.files;

import java.nio.file.Files;
import java.nio.file.Paths;

public class CommonAttributeExample {

    public static void main(String[] args) {
        System.out.println(Files.isDirectory(Paths.get("/canine/fur.jpg")));
        System.out.println(Files.isSymbolicLink(Paths.get("/canine/coyote")));
        System.out.println(Files.isRegularFile(Paths.get("/canine/types.txt")));
    }
}
```

- The first example prints *true* if *fur.jpg* is a directory or a symbolic link to a directory and *false* otherwise.
- The second example prints *true* if */canine/coyote* is a symbolic link, regardless of whether the file or directory
  it points to exists.
- The third example prints *true* if *types.txt* points to a regular file or alternatively a symbolic link that points
  to a regular file.

```markdown
- These 3 methods do not throw IOException. They return *false* if the path does not exist.
```

## Checking File Accessibility with isHidden(), isReadable(), isWritable(), and isExecutable()

- In many file systems, it is possible to set a *boolean* attribute to a file that marks it hidden, readable, or
  executable. The *Files* class includes methods that expose this information.

```markdown
public static boolean isHidden(Path path) throws IOException

public static boolean isReadable(Path path)

public static boolean isWritable(Path path)

public static boolean isExecutable(Path path)
```

Here we present sample usage of each method:

```java
package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileAccessibleExamples {

    public static void main(String[] args) throws IOException {

        System.out.println(Files.isHidden(Paths.get("/walrus.txt")));
        System.out.println(Files.isReadable(Paths.get("/seal/baby.png")));
        System.out.println(Files.isWritable(Paths.get("dolphin.txt")));
        System.out.println(Files.isExecutable(Paths.get("whales.png")));
    }
}
```

- Note that the file extension does not necessarily determine whether a file is executable.
- For example, an image file that ends in .png could be marked executable in some file system.
- With the exception of isHidden(), these methods do not declare any checked exceptions and return *false* if the file
  does not exist.

## Reading File Size with size()

- The *Files* class includes a method to determine the size of the file in bytes.

```markdown
public static long size(Path path) throws IOException
```

- The size returned by this method represents the conceptual size of the data, and this may differ from the actual size
  on the persistent storage device.

- A sample call to the *size()* method.

```java
package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SizeExample {

    public static void main(String[] args) throws IOException {
        System.out.println(Files.size(Paths.get("/zoo/animals.txt")));
    }
}
```

```markdown
Note: The *Files.size()* method is defined only on files.
Calling *Files.size()* on a directory is undefined, and the result depends on the file system.
If you need to determine the size of a directory and its contents, you'll need to talk the directory tree.
We'll show you how to do this later in the chapter.
```

## Checking for File Changes with getLastModifiedTime()

- The *Files* class provides the following method to retrieve the last time a file was modified:

```
public static FileTime getLastModifiedTime(Path path, LinkOption... options) throws IOException
```

- The method returns a *FileTime* object, which represents a timestamp.
- For convenience, it has a *toMillis()* method that returns the epoch time, which is the number of milliseconds since
  12 a.m. UTC on January 1, 1970.

```java
package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class LastModifiedTimeExample {

    public static void main(String[] args) throws IOException {
        final Path path = Paths.get("animals/bear");
        System.out.println(Files.getLastModifiedTime(path).toMillis());
    }
}
```

## Improve Attribute Access

- Up until now, we have been accessing individual file attributes with multiple method calls.
- While this is functionally correct, there is often a cost each time one of these methods is called.
- Put simply, it is far more efficient to ask the file system for all of the attributes at once rather than performing
  multiple round-trips to the file system.

## Understanding Attribute and View Types

- NIO.2 includes two methods for working with attributes in a single method call:
- a read-only attribute method and an updatable view method.
- For each method, you need to provide a file system type object, which tells the NIO.2 method which type of view
  you are requesting. By updatable view, we mean that we can both read and write attribute with the same object.

Table 9.5 The attributes and view types

```markdown
| Attributes interface |     View interface     |                       Description                                                                  |
| ---------------------|------------------------|----------------------------------------------------------------------------------------------------|
| BasicFileAttributes  | BasicFileAttributeView | Basic set of attributes supported by all file systems                                              |
| DosFileAttributes    | DosFileAttributeView   | Basic set of attributes along with those supported by DOS/Windows-based systems                    |
| PosixFileAttributes  | PosixFileAttributeView | Basic set of attributes along with those supported by POSIX systems, such as UNIX, LINUX, Mac, etc |
```

## Retrieving Attributes with readAttributes()

- The *Files* class includes the following method to read attributes of a class in a read-only capacity.

```markdown
public static <A extends BasicFileAttributes> A readAttributes(
Path path,
Class<A> type,
LinkOption... options) throws IOException
```

- Applying it requires specifying the *Path* and *BasicFileAttributes.class* parameters.

```java
package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

public class BasicFileAttributesExample {

    public static void main(String[] args) throws IOException {


        var path = Paths.get("animals/bear");
        BasicFileAttributes data = Files.readAttributes(path, BasicFileAttributes.class);

        System.out.println("Is a directory? " + data.isDirectory());
        System.out.println("Is a regular file? " + data.isRegularFile());
        System.out.println("Is a symbolic link? " + data.isSymbolicLink());
        System.out.println("Size (in bytes): " + data.size());
        System.out.println("Last modified: " + data.lastModifiedTime());

    }
}
```

- The *BasicFileAttributes* class includes many values with the same name as the attributes methods in the *Files*
  class.
- The advantage of using this method, though, is that all of the attributes are retrieved at once.

## Modifying Attributes with getFileAttributeView()

- The following *Files* method returns an updatable view:

```markdown
public static <V extends FileAttributeView> V getFileAttributeView(
Path path,
Class<V> type,
LinkOption... options)
```

- We can use the updatable view to increment a file's last modified date/time value by 10,000 milliseconds, or 10
  seconds.

```java
package chapter_9.files;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.FileTime;

public class FileAttributeViewExample {

    public static void main(String[] args) throws IOException {

        // Read file attributes
        var path = Paths.get("chapter_9/resources/turtles/sea.txt");
        BasicFileAttributeView view = Files.getFileAttributeView(path,
                BasicFileAttributeView.class);
        BasicFileAttributes attributes = view.readAttributes();

        // Modify file last modified time
        FileTime lastModifiedTime = FileTime.fromMillis(
                attributes.lastModifiedTime().toMillis() + 10_000);
        view.setTimes(lastModifiedTime, null, null);

    }
}
```

- After the updatable view is retrieved, we need to call *readAttributes()* on the view to obtain the file metadata.
- From there, we create a new *FileTime* value and set it using the setTimes() method.

```markdown
// BasicFileAttributeView instance method
public void setTimes(FileTime lastModifiedTime,
FileTime lastAccessTime, FileTime createTime)
```

- This method allows to pass *null* for any date/time value that we do not want to modify.
- In our sample code, only the last modified date/time is changed.

```markdown
Note: Not all file attributes can be modified with a view. For example, you cannot set a property that changes a file
a directory. Likewise, you cannot change the size of the object without modifying its contents.
```

## Applying Functional Programming

- In this part of the chapter, we'll combine everything we've presented so far with functional programming to perform
  extremely powerful file operations, often with only a few lines of code. The *Files* class includes some incredibly
  useful Stream API methods that operate on files, directories, and directory trees.

## Listing Directory Contents

- Let's start with a simple Stream API method. The following *Files* method lists the contents of a directory:

```markdown
public static Stream<Path> list(Path dir) throws IOException
```

```java
package chapter_9.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class ListDirectoryContentExample {

    public static void main(String[] args) throws IOException {
        try (Stream<Path> s = Files.list(Path.of("src/main/resources"))) {
            s.forEach(System.out::println);
        }
    }
}
```

- Earlier, we presented the *Files.copy()* method and showed that it only performed a shallow copy of a directory.
- We can use the *Files.list()* to perform a deep copy.

```java
package chapter_9.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class DeepCopyExample {

    public static void main(String[] args) {

    }

    public void copyPath(Path source, Path target) {

        try {
            Files.copy(source, target);
            if (Files.isDirectory(source))
                try (Stream<Path> s = Files.list(source)) {
                    s.forEach(p -> copyPath(p,
                            target.resolve(p.getFileName())));
                }
        } catch (IOException e) {
            //Handle exception
        }
    }
}
```

- For now, you just need to know the JVM will not follow symbolic links when using this stream method.

```markdown
Closing the Stream

- The NIO.2 stream-based methods open a connection to the *file system that must be properly closed*, else a resource
  leak could ensue. A resource leak within the file system means the path may be locked from modification long after the
  process that used it completed.

- If you assumed a stream's terminal operation would automatically close the unerlying file resources, you'd be wrong.
- There was a lot of debate about this behaviour when it was first presented, but in short, requiring developers to
  close
  the stream won out.

- On the plus side, not all streams need to be closed, only those that open resources, like the ones found in NIO.2.
- For instance, you didn't need to close any of the streams you worked with in Chapter 4.

- Finally, the exam doesn't always propertly close NIO.2 resources. To match the exam, we will sometimes skip closing
  NIO.2 resources in review and practice questions.
- **PLEASE**, in your own code, always use try-with-resources statements with these NIO.2 methods.
```

## Traversing a Directory Tree

- While the *Files.list()* method is useful, it traverses the contents of only a single directory.
- What if we want to visit all the paths within a directory tree?
- *Traversing a directory*, also referred to as walking a directory tree, is the process by which you start with a
  parent directory and iterate over all of its descendants until some conditions is met or there are no more elements
  over which to iterate. For example, if we're searching for a single file, we can end the search when the file is found
  or when we've checked all files and come up empty. The starting path is usually a specific directory; after all, it
  would be time-consuming to search the entire file system on every request!

```markdown
Don't use DirectoryStream and FileVisitor

- While browsing the NIO.2 Javadocs, you may come across methods that use the *DirectoryStream* and *FileVisitor*
  classes
  to traverse a directory. These methods predate the existence of the Stream API and were even required knowledge for
  older Java certifications exams. Even worse, despite its name, *DirectoryStream* is not a Stream API class.

  The best advice we can give you is to not use them. The newer Stream API-based methods are superior and accomplish the
  same thing often with much less code.
```

## Selecting a Search Strategy

- There are two common strategies associated with walking a directory tree:
- a depth-first search and a breadth-first search.
- A *depth-first search* traverses the structure from the root to an arbitrary leaf and then navigates back up towards
  the root, traversing fully down any paths is skipped along the way.
- The *search-depth* is the distance from the root to current node. To prevent endless searching, Java includes a search
  depth that is used to limit how many levels (or hops) from the root the search is allowed to go.
  <br><br>
- Alternatively, a *breadth-first search* starts at the root and processes all elements of each particular depth, before
  proceeding to the next depth level. The result are ordered by depth, with all nodes at depth 1 read before all nodes
  at
  depth 2, and so on. While a *breadth-first* tends to be balanced and predictable, it also requires more memory since a
  a list of visited nodes must be maintained.
  <br><br>
- For the exam, you don't have to understand the details of each search strategy that Java employs; you just need to be
  aware that the NIO.2 Streams API methods use depth-first searching with a depth limit, which can be optionally
  changed.

## Walking a Directory with walk()

- Let's get to more Stream API methods.
- The *Files* class includes two methods for walking the directory tree using a depth-first search.

```markdown
public static Stream<Path> walk(Path start,
FileVisitOption... options) throws IOException

public static Stream<Path> walk(Path start, int maxDepth,
FileVisitOption) thows IOException
```

- Like our other stream methods, *walk()* uses lazy evaluation and evaluates a *Path* only as it gets to it.
- This means that even if the directory tree includes hundreds or thousands of files, the memory required to process a
  directory tree includes hundreds or thousands of files, the memory required to process a directory tree is low.
- The first *walk()* method relies on a default maximum depth of *Integer.MAX_VALUE*, while the overloaded version
  allows the user to set a maximum depth. This is useful in cases where the file system might be large, and we know the
  information we are looking for is near the root.

```java
package chapter_9.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SizeExample {

    private long getSize(Path p) {
        try {
            return Files.size(p);
        } catch (IOException e) {
            // Handle exception
        }

        return 0L;
    }

    public long getPathSize(Path source) throws IOException {
        try (var s = Files.walk(source)) {
            return s.parallel()
                    .filter(p -> !Files.isDirectory(p))
                    .mapToLong(this::getSize)
                    .sum();
        }
    }

}
```

## Applying a Depth Limit

- Let's say our directory tree was quite deep, so we apply a depth limit by changing one line of code in our
  *getPathSize()*
  method.

```markdown
try (var s = Files.walk(source, 5)) {
```

- This new version checks for files only within 5 steps of the starting node.
- A depth value of 0 indicates the current path itself.
- Since the method calculates values only on files, you'd have to set a depth limit of at least 1 to get a nonzero
  result
  when this method is applied to a directory tree.

## Avoiding Circular Paths

- Many of our earlier NIO.2 methods traverse symbolic links by default, with a NOFOLLOW_LINKS used to disable this
  behavior.
- The *walk()* method is different in that it does *not follow* symbolic links by default and requires the FOLLOW_LINKS
  option to be enabled.
- We can alter our *getPathSize()* method to enable following symbolic links by adding the *FileVisitOption*:

```java
public long getPathSizeFollowLinks(Path source) throws IOException {
    try (var s = Files.walk(source,
            FileVisitOption.FOLLOW_LINKS)) {
        return s.parallel()
                .filter(p -> !Files.isDirectory(p))
                .mapToLong(this::getSize)
                .sum();
    }
}
```

- When traversing a directory tree, your program needs to be careful of symbolic links if enabled.
- For example, if our process comes across a symbolic link that points to the root directory of the file system, then 
  every file in the system would be searched!
- Worse yet, a symbolic link could lead to a cycle, in which a path is visited repeatedly.
- A *cycle* is an infinite circular dependency in which an entry in a directory tree points to one of its ancestor 
  directories.
- Let's say we had a directory tree as shown in Figure 9.6, with the symbolic link /birds/robin/allBirds that points to 
  /birds.

<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_9/images/figure_9_6.png?raw=true" width="350" />

- What happens if we try to traverse this tree and follow all symbolic links, starting with /birds/robin?
  Table 9.6 shows the paths visitied after walking a depth of 3.
- For simplicity, we'll walk the tree in a breadth-first ordering, *although a cycle occurs regardless of the search 
  strategy used.*

### Table 9.6 Walking a directory with a cycle using breadth-first search

| Depth | Path reached                                                                                 |
|-------|----------------------------------------------------------------------------------------------|
| 0     | /birds/robin                                                                                 |
| 1     | /birds/robin/pictures                                                                        |
| 1     | /birds/robin/allBirds <br> -> birds                                                          |
| 2     | /birds/robin/pictures/nest.png                                                               |
| 2     | /birds/robin/pictures/nest.gif                                                               |
| 2     | /birds/robin/allBirds/robin <br> -> /birds/robin                                             |
| 3     | /birds/robin/allBirds/robin/pictures <br> /birds/robin/pictures                              |
| 3     | /birds/robin/allBirds/robin/pictures/allBirds <br> -> /birds/robin/allBirds <br> -> /birds   |


- After walking a distance of 1 from the start, we hit the symbolic link /birds/robin/allBirds and go back to the top of 
  the directory tree /birds.
- That's OK because we haven't visited /birds yet, so there's no cycle yet!

- Unfortunately, at depth 2, we encounter a cycle. We've already visited the */birds/robin* directory on our first step,
  and now we're encountering it again. If the process continues, we'll be doomed to visit the directory over and over
  again.
- Be aware that wen the FOLLOW_LINKS option is used, the *walk()* method will track all of the paths it has visited,
  throwing  a *FileSystemLoopException* if a path is visited twice.

## Searching a Directory with find()

- In the previous example, we applied a filter to the *Stream<Path>* object to filter the results, although NIO.2 
  provides a more convenient method.

```markdown
public static Stream<Path> find(Path start,
    int maxDepth,
    BiPredicate<Path, BasicFileAttribute> matcher,
    FileVisitOption... options) thorws IOException
```

- The two parameters of the *BiPredicate* are a **Path** object and a **BasicFileAttribute** object, which you saw
  earlier in the chapter. In this manner, NIO.2 automatically retrieves the basic file information for you, allowing you 
  to write complex lambda expression that have direct access to this object.
- We illustrate this with the following example:

```java
package chapter_9.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FindExample {

  public static void main(String[] args) throws IOException {
    Path path = Paths.get("/bigcats");
    long minSize = 1_000;
    try (var s = Files.find(path, 10,
            (p, a) -> a.isRegularFile()
                    && p.toString().endsWith(".java")
                    && a.size() > minSize)) {
      s.forEach(System.out::println);
    }
  }
}
```

- This example searches a directory tree and prints all *.java* files with a size of at least 1,000 bytes,
  using a depth limit of 10. 
- While we could have accomplished this using the *walk()* method along with a call to *readAttributes()*, this
  implementation is a lot shorter and more convenient than those would have been. We also don't have to worry about any
  methods within the lambda expression declaring a checked exception, as we saw in the *getPathSize()* example.

## Reading a File with lines()

- Earlier in the chapter, we presented *Files.readAllLines()* and commented that using it to read a very large file could 
  result in a *OutOfMemoryError* problem.
- Luckily, NIO.2 solves this problem witha Stream API method.

```java
public static Stream<String> lines(Path path) throws IOException;
```

- The contents of the file are read and processed lazily, which means that only a small portion of the file is stored
  in memory at any given time.

```java
package chapter_9.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadLinesLazilyExample {

    public static void main(String[] args) throws IOException {
        Path path = Paths.get("/fish/sharks.log");
        try (var s = Files.lines(path)){
            s.forEach(System.out::println);
        }
    }
}
```

- Taking things one step further, we can leverage other stream methods for a more powerful example.

```java
package chapter_9.streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ReadLineStreamExample {

  public static void main(String[] args) throws IOException {

    Path path = Paths.get("/fish/sharks.log");
    try (var s = Files.lines(path)) {
      s.filter(f -> f.startsWith("WARN"))
              .map(f -> f.substring(5))
              .forEach(System.out::println);
    }
  }
}
```

- This sample code searches a log for lines that start with WARN:, outputting the text that follows.
- Assuming that the input file *sharks.log* is a follows:

<img src="https://github.com/marodrigues20/java-certifications/blob/main/ocp-java-11-programmer-2/src/main/java/chapter_9/images/figure_9_7.png?raw=true" width="350" />

- Then, the sample output would be the following:

```markdown
No database could be detected
Performing manual recovery
```

- As you can see, functional programming plus NIO.2 give us the ability to manipulate files in complex ways, often with 
  a few short expressions.

## Comparing Legacy java.io.File and NIO.2 Methods

- Table below shows the comparasion between some of the legacy *java.io.File* methods described in Chapter 8 and the new 
  NIO.2 methods described in this chapter. 
- In this table, *file* refers to an instance of the *java.io.File* class, while *path* and *otherPath* refer to instances
  of the NIO.2 *Path* interface.

  

| Legacy I/O File method    | NIO.2 method                    |
|---------------------------|---------------------------------|
| file.delete()             | Files.delete(path)              |
| file.exists()             | Files.exists(path)              |
| file.getAbsolutePath()    | path.toAbsolutePath()           |
| file.getName()            | path.getFileName()              |
| file.getParent()          | path.getParent()                |
| file.isDirectory()        | Files.isDirectory(path)         |
| file.isFile()             | Files.isRegularFile()           |
| file.lastModified()       | Files.getLastModifiedTime(path) |
| file.length()             | Files.size(path)                |
| file.listFiles()          | Files.list(path)                |
| file.mkdir()              | Files.createDirectory(path)     |
| file.mkdirs()             | Files.createDirectories(path)   |
| file.renameTo(otherFile)  | Files.move(path, otherPath)     |


- Bear in mind that a number of methods and features are available in NIO.2 that are not available in the legacy API, 
  such as support for symbolic links, setting file system-specific attributes, and so on.
- The NIO.2 is a more developed, much more powerful API than the legacy *java.io.File* class.

## Main Points - Summary

### Be able to manipulate Path objects.
- Most of the methods defined on *Path* do not require any checked exceptions and do not required the path to exist 
  within the file system, with system, with the exception of **toRealPath()**.

### Manage file attributes.
- The NIO.2 *Files* class includes many methods for reading single file attributes such as its size or whether it is a 
  directory, a symbolic link, hidden, etc. NIO.2 also supports reading all of the attributes in a single call. 
  An attribute type is used to support operating system-specific views.
- Finally, NIO.2 support updatable views for modified select attributes.

### Be able to operate on directories using functinal programming.
- NIO.2 includes the Stream API for manipulating directories. The **Files.list()** method iterates over the contents of 
  single directory, while the *Files.walk()* method lazily traverse a directory tree in a depth-first manner.
- The *Files.find()* method also traverses a directory but requires a filter to be applied. Both *File.walk()* and 
  *Files.find()* support a search depth limit. Both methods will also throw an exception if they are directed to follow 
  symbolic links and detect a cycle.

### Understand the difference between readAllLines() and lines().
- *Files.readAllLines()* method reads all the lines of a file into memoery and returns the result as a List<String>.
- The *Files.lines()* method lazily reads the lines of a file,instead returning a functional programming *Stream<Path> 
  object.
- While both methods will correctly read the lines of a file, *lines()* is considered safer for larger files since it 
  does not require file to be stored in memory.

