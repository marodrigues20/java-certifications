# Chapter 8

##Objectives
- Read data from and write console and data using I/O Streams
- Use I/O Streams to read and write files
- Read and write objects using serialization


How can they save data so that information is not lost every time the program is terminated. They use files, of course!
You can design code writes the current state of an application to a file every time the application is closed and then
reloads the data when the application is executed the next time. This chapter is using the java.io API to interact with
fields and streams.

Note: I/O streams are completely unrelated to the streams you saw in Chapter 4, "Functional Programming".

## Conceptualizing the File System

When working with directories in Java, we often treat them like a files. In fact, we use many of the same classes to 
operate on files and directories. For example, a file and directory both can be renamed with the same Java method.

To interact with the files, we need to connect to the file system. The file system is in charge of reading and writing 
data withing a computer. Different operating system use different file systems to manage their data.

Next, the root directory is the topmost directory in the file system, from which all files and directories inherit.

c:\ -> Windows
/   -> Linux

## Storing Data as Bytes

Data is stored in a file system (and memory) as a 0 or 1, called a bit. Since it's really hard for humans to read/write
data that is just 0s they are grouped into a set of 8 bits, called a byte. Values are often read or written streams
using byte values and arrays.

## The ASCII Characters

Using a little arithmetic (2 power 8), we see a byte can be set in one 256 possible permutations. These 256 values form
the alphabet for our computer system to be able to type characters like a, #, and 7.

Historically, the 256 characters are referred to ASCII characters, based on the encoding standard that defined them.
Given all of the languages and emojis available today, 256 characters is pretty limiting. Many newer standards have been
developed that rely on additional bytes to display characters.

## Introducing the File Class

The File class is used to read information about existing files and directors, list of contents of a directory, and 
create/delete files and directories.
An instance of a File class represents a path to a particular file or directory on the file system. The File class 
cannot read or write data within a file, although it can be passed as a reference to many stream classes to read and 
write data.

## Creating a File Object

A File object often is initialized with a String containing either an absolute or relative path to the file or directory
within the file system. The absolute path of a file or directory is the full path.
Alternatively, the relative path of a file or directory is path from the current working directory to the file or 
directory.
For convenience, Java offers two options to retrieve the local separator character: a system property and a static 
variable defined in the File class. Both of the following examples will output the separator character for the current
environment:

## The File Object vs. the Actual File

When working with an instance of the File class, keep in mind that it only represents a path to a file. Unless operated
upon, it is not connected to an actual file within the file system.

If you try to operate on a file that does not exist or you do not have access to, some File methods will throw an 
exception.

## Working with a File Object

Method Name:
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

## Understanding I/O Stream Fundamentals

The contents of a file may be accessed or written via stream via a stream, which is a list of data elements presented
sequentially. Stream should be conceptually thought of as a long, nearly never-ending "stream of water" with data
presented one "wave" at a time.
The stream is so large that once we start reading it, we have no idea where the beginning or the end is. We just have
a pointer to our current position in the stream and read data one block at a time.

Each type of stream segments data into a "wave" or "block" in a particular way. For example, some stream classes read 
or write data a individual bytes. Other stream classes read or write individual characters or strings of characters. 
On top of that, some stream classes read or write larger groups of bytes or characters at a time, specifically those 
with the word Buffered in their name.

## All Java I/O Stream Use Bytes

Although the java.io API is full of stream that handle characters, strings, groups of bytes, and so on, nearly all built
on top of reading or writing  an individual byte or an array of bytes at a time. The reason higher-level stream exist
is for convenience, as well as performance.

For example, writing a file one byte at a time is time-consuming and slow in practice because the round-trip between
the Java application and the file system is relatively expensive. By utilizing a BufferedOutputStream, the Java 
application can write a large chunk of bytes at a time, reducing the round-trips and drastically improving performance.

Although streams are commonly used with file I/O, they are more generally used to handle the reading/writing of any
sequential data source. For example, you might construct a Java application that submits data to a website an output
stream and reads the result via an input stream.

## I/O Streams Can Be Big

The file can still be read and written by a program with very little memory, since the stream allows the application to
focus on only a small portion of the overall stream at any given time.

## Byte Stream vs. Character Stream

The java.io API defines two sets of stream classes for reading and writing streams. 

1- Bytes streams read/write binary data (0s and 1s) and have class names that end in InputStream or OutputStream.
2- Character streams read/write text data and have class names that end in Reader or Writer.

The API frequently includes similar classes for both byte and characters streams, such as FileInputStream and FileReader.

It's important to remember that even though character streams do not contain the word Stream in their class name, 
they are still I/O stream.

The byte stream are primarily used to work with binary data, such as an image or executable file, while characters
streams are used to work with text files.

The character encoding determines how characters are encoded and stored in bytes in a stream and later read back or 
decoded as characters. Although this may sound simple, Java supports a wide variety of characters encoding, ranging
from ones that may use one byte for Latin characters, UTF-8 and ASCII for example, to using two or more bytes per 
characters, such as UTF-16.


For character encoding, just remember that using a character stream is better for working with text data than a byte
stream. The character stream classes were created for convenience, and you should certainly take advantage of them
when possible.

## Input vs. Output Stream
Most InputStream classes have a corresponding OutputStream class, and vice versa. For example, the FileOutputStream 
class writes data that can be read by a FileInputStream. It follows, then, that most Reader classes have a corresponding
Writer class. For example, the FileWriter class writes data that can be read by a FileReader.

There are exceptions to this rules. For the exam, you should know that PrintWriter has no accompanying PrintReader class. 


## Low-level vs. High-Level Stream

A low-level stream connects directly with the source of the data, such as a file, an array, or a String. Low-level
stream process the raw data or resource and are accessed in a direct and unfiltered manner. For example, a 
FileInputStream is a class that reads file data at a time.
Alternatively, a high-level stream is built on top of another stream using wrapping. Wrapping is the process by which
an instance is passed to the constructor of another class, and operations on the resulting instance are filtered and 
applied to the original instance.

## Use Buffered Stream When Working with Files

Buffered classes read or write data in groups, rather than a single byte or character at a time. The performance gain
from using a Buffered class to access a low-level file stream cannot be overstated. Unless you are doing something very
specialized in your application, you should always wrap a file stream with a Buffered class in practice.

One of the reasons that Buffered stream tend to perform so well in practice is that many file system are optimized for
sequential disk access. For example, accessing 1,600 sequential bytes is a lot faster than accessing 1,600 bytes spread
across the hard drive.

The java.io library defines four abstract classes that are the parents of all stream classes defined within the API:
InputStream, OutputStream, Reader ,and Writer.

## Note: 
A low-level stream connects directly with the source of the data.

## The java.io abstract stream base classes

 Class Name   | Description                                
 InputStream  | Abstract class for all input byte stream   
 OutputStream | Abstract class for all output  byte stream 
 Reader       | Abstract class for all input character
 Writer       | Abstract class for all output character stream


## The java.io concrete stream classes

Class Name           | Low/High Level | Description
FileInputStream      | Low            | Reads file data as byte
FileOutputStream     | Low            | Writes file data as bytes
FileReader           | Low            | Reads file data as character
FileWriter           | Low            | Writes file data as character
BufferedInputStream  | High           | Reads byte data from an existing InputStream in a buffered manner, which improves efficiency and performance
BufferedOutputStream | High           | Writes byte data to an existing OutputStream in a buffered manner, which improves efficiency and performance
BufferedReader       | High           | Reads character data from an existing Reader in a buffered manner, which improves efficiency and performance
BufferedWriter       | High           | Writes character data to an existing Writer in a buffered manner, which improves efficiency and performance
ObjectInputStream    | High           | Deserializes primitive Java data types and graphs of Java
ObjectOutputStream   | High           | Serializes primitive Java data types and graphs of Java
PrintStream          | High           | Writes formatted representations of Java objects to a binary stream.
PrintWriter          | High           | Writes formatted representations of Java objects to a character stream.


## Common I/O Stream Operations

While there are a lot stream classes, many share a lot of the same operations.

## Reading and Writing Data

Most important methods are read() and write(). Both InputStream  and Reader declare the following method to read byte
data from a stream:

// InputStream and Reader
public int read() throws IOException

// OutputStream and Writer
public int write() throws IOException

We said we are reading and writing bytes, so why do the methods use int instead of byte?
Remember, the byte data type has a range of 256 characters. They needed an extra value to indicate the end of a stream.
The authors of Java decided to use a larger data type, int, so that special values like -1 would indicate the end of a
stream. The output stream classes use int  as well, to be consistent with the input stream classes.

The byte stream classes also include overloaded methods for reading and writing multiples bytes at a time.

// InputStream
public int read(byte[] b) throws IOException
public int read(byte[] b, int offset, int length) throws IOException

// OutputStream
public void write(byte[] b) throws IOException
public void write(byte[] b, int offset, int length) throws IOException

The offset and length are applied to the array itself. For example, an offset of 5 and length of 3 indicates that 
the stream should read up to 3 bytes of data and put them into the array starting with position 5.


## Close the Stream

Since streams are considered resources, it is imperative that all I/O streams be closed after they are used lest they
lead to resources leaks. Since all I/O streams implement Closeable, the best way to do this is with a try-with-resources
statement, which  you saw in Chapter 5, "Exception, Assertions, and Localization". We will close stream resources using
the try-with-resources syntax since this is the preferred way of closing resources in Java.

What about if you need to pass a stream to a method? That's fine, but the stream should be closed in the method that 
created it. (More example: StreamToMethod.java)


## Closing Wrapped Stream

When working with a wrapped stream, you only need to use close() on the topmost object.


## Manipulating Input Stream

All input stream classes include the following method to manipulate the order in which data is read from a stream:

// InputStream and Read
public boolean markSupported()
public void mark(int readLimit)

The mark() and reset() methods return a stream to an earlier position. Before calling either of these methods, you 
should call the markSupported() methods, which returns true only if mark() is supported. The skip() method is pretty
simple; it basically reads data from the stream and discard the contents.

Note: Not all input stream classes support mark() and reset(). Make sure to call markSupported() on the stream before
calling these methods or an exception will be thrown at runtime.


























