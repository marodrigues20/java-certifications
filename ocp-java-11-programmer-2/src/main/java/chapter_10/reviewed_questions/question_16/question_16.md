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


Answer: D, E


