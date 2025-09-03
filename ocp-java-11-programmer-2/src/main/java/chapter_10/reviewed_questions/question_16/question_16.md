16. Which of the following can fill in the blank? (Choose all that apply.)

```markdown
var sql = "____________________";
try (var ps = conn.prepareStatement(sql)) {
    ps.setObject(3, "red");
    ps.setInt(2, 8);
    ps.setString(1, "ball");
    ps.executeUpdate();
```

A. { call insert_toys(?, ?) }
B. { call insert_toys(?, ?, ?) }
C. { call insert_toys(?, ?, ?, ?) }
D. INSERT INTO toys VALUES (?, ?)
E. INSERT INTO toys VALUES (?, ?, ?)
F. INSERT INTO toys VALUES (?, ?, ?, ?)


Answer: E

- First, notice that this code uses a *PreparedStatement*. Option A, B, and C are incorrect because they are for a *CallableStatement*.
- Next, remember that the number of parameter must be an exact match, making option E correct.
- Remember that you will not be tested on SQL syntax. 
- When you see a question that appears to be about SQL, think about what it might be trying to test you on.


