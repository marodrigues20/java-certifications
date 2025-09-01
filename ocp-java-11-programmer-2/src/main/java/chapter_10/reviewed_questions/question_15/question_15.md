15. Suppose *learn()* is a stored procedure that takes one *IN* parameter and one *OUT* parameter.
    What is wrong with the following code? (Choose all that apply.)

```markdown
18: var sql = "{?= call learn(?)};
19: try (var cs = conn.prepareCall(sql)) {
20:     cs.setInt(1, 8);
21:     cs.execute();
22:     System.out.println(cs.getInt(1));
```

A. Line 18 does not call the stored procedure properly. <br>
B. The parameter value is not set for input. <br>
C. The parameter is not registered for output. <br>
D. The code does not compile. <br>
E. Something else is wrong with the code. <br>
F. None of the above. This code is correct. <br>


Answer: C, D