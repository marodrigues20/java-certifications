11. Suppose that the table *food* has five rows and this SQL statement updates all of them.
    What is the result of this code?

```markdown
public static void main(String[] args) {
    var sql = "UPDATE food SET amount = amount + 1";
    try (var ps = DriverManager.getConnection("jdbc:derby:zoo");
        var ps = conn.prepareStatement(sql)) {
        
        var result = ps.executeUpdate();
        System.out.println(result);
    }
}
```


A. 0 <br>
B. 1 <br>
C. 5 <br>
D. The code does not compile.
E. A *SQLException* is thrown.
F. A different exception is thrown.


Answer:  D

- JDBC code throws a *SQLException*,  which is a checked exception.
- The code does not handle or declare this exception, and therefore it doesn't compile.
- Since the code doesn't, option D is correct.
- If the exception were handled or declared, the answer would be option C.