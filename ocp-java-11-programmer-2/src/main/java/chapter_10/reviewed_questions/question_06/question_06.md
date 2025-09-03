6. Which of the options can fill in the blanks in order to make the code compile?

```markdown
boolean bool = ps.______________();
int num = ps.______________();
ResultSet rs = ps.______________();
```

A. execute, executeQuery, executeUpdate
B. execute, executeUpdate, executeQuery
C. executeQuery, execute, executeUpdate
D. executeQuery, executeUpdate, execute
E. executeUpdate, execute, executeQuery
F. executeUpdate, executeQuery, execute


Answer: B

- The first line has a return type of *boolean*, making it an *execute()* call.
- The second line returns the number of modified rows, making it an *executeUpdate()* call.
- The third line returns the results of a query, making it an *executeQuery()* call.