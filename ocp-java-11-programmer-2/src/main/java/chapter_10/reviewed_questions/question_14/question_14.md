14. Which of the following can fill in the blank correctly? (Choose all that apply.)

```markdown
var rs = ps.executeQuery();
if (rs.next()) {
    _____________________________;
}
```

A. String s = rs.getString(0) <br>
B. String s = rs.getString(1) <br>
C. String s = rs.getObject(0) <br>
D. String s = rs.getObject(1) <br>
E. Object s = rs.getObject(0) <br>
F. Object s = rs.getObject(1) <br>


Answer: B, F


- In a *ResultSet*, columns are indexed starting with 1, not 0.
- Therefore, option A, C, and E are incorrect. 
- There are methods to get the column as a *String* or *Object*.
- However, optionD is incorrect because an *Object* cannot be assigned to a *String* without a cast.