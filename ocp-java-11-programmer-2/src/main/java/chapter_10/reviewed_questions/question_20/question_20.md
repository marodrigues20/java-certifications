20. Which of the following could be true of the following code? (Choose all that apply.)

```markdown
var sql = "{call transform(?)}";
try (var cs = conn.prepareCall(sql)) {
    cs.registerOutParameter(1, Types.INTEGER);
    cs.execute();
    System.out.println(cs.getInt(1));
}
```

A. The stored procedure can declare an IN or INOUT parameter. <br>
B. The stored procedure can declare an INOUT or OUT parameter. <br>
C. The stored procedure must declare an IN parameter.   <br>
D. The stored procedure must declare an INOUT parameter.    <br>
E. The stored procedure must declare an OUT parameter.  <br>

Answer: C, E