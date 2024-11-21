11. Given a directory */storage* full of multiple files and directories, what is the result of executing the 
    deleteTree("/storage") method on it?


```java
public static void deleteTree(File file){
    if(!file.isFile())
        for(File entry: file.listFiles())
            deleteTree(entry);
    else file.delete();
}
```

A. It will delete only the empty directories.   <br>
B. It will delete the entire directory tree including the */storage* directory itself.  <br>
C. It will delete all files within the directory tree.  <br>
D. A *NullPointerException* is thrown.  <br>
E. None of the above, as the code does not compile. <br>


Correct Answer: C

- The code compiles, so options D and E are incorrect.
- The method looks like it will delete a directory tree but contains a bug.
- It never deletes any directory, only files.
- The result of executing this program is that it will delete all files within a directory tree, but none of the 
  directories. For this reason, option C is correct.