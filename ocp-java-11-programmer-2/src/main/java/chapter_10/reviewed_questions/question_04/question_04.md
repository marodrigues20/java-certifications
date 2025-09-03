4. Which of the options can fill in the blank to make the code compile and run without error? (Choose all that apply.)

```
var sql = "UDPATE habit WHERE environment = ?";
try (var ps = conn.preparedStatement(sql)) {
    _____________________
    ps.executeUpdate();
}
```


A. ps.setString(0, "snow");
B. ps.setString(1, "snow");
C. ps.setString("environment", "snow");
D. ps.setString(1, "snow"); ps.setString(1, "snow");
E. ps.setString(1, "snow"); ps.setString(2, "snow");
F. ps.setString("environment", "snow"); ps.setString("environment", "snow");


Answer: B, D.

- When setting parameters on *PreparedStatement*, there are only options that take an index, making options C and F incorrect.
- The indexing starts with 1, making option A incorrect. 
- This query has only one parameter, so option E is also incorrect.
- Option B is correct because it simply sets the parameter.
- Option D is also correct because it sets the parameter and then immediately overwrites it with the same value.