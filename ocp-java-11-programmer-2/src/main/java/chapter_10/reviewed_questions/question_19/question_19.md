19. Which of the options can fill in the blank to make the code compile and run without error?
    (Choose all that apply.)

```markdown
var sql = "UPDATE habit WHERE environment = ?"
try (var ps = conn.prepareCall(sql)) {
    ______________________
    ps.executeUpdate();
    }
```

A. ps.setString(0, "snow"); <br>
B. ps.setString(1, "snow"); <br>
C. ps.setString("environment", "snow"); <br>
D. The code does not compile. <br>
E. The code throws an exception at runtime. <br>

Answer: E

- This code should call *prepareStatement()* instead of *prepareCall()* since it not executing a stored procedure.
- Since we are using *var*, it does compile. 
- Java will happily create a *CallableStatement* for you.
- Since this compile safety is lost, the code will not cause issues until runtime.
- At that point, Java will complain that you are trying to execute SQL as if it were a stored procedure,
  making option E correct.