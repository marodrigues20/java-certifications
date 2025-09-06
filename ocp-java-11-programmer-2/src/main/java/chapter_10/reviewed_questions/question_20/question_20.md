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

Answer: E

- Since de codes calls *registerOutParameter()*, we know the stored procedure cannot use an *IN* parameter.
- Further, there is no *setInt()*, so it cannot be an *INOUT* parameter either.
- Therefore, the stored procedure must use an *OUT* parameter, making option E the answer.