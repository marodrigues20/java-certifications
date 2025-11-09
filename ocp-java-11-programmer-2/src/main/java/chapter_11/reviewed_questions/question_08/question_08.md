8. You go to the library and want to read a book. Which is true?

```markdown
grant {
    permission java.io.FilePermission
        "/usr/local/library/book.txt/",
       "read,write";
}
```

A. The police is correct. <br>
B. The police is incorrect because the file permission cannot be granted this way. <br>
C. The policy is incorrect because read should not be included. <br>
D. The policy is incorrect because the permissions should be separated with semicolons. <br>
E. The policy is incorrect because *write* should not be included. <br>


Answer: A


