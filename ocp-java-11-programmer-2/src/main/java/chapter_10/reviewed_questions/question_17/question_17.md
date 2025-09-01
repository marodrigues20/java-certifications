17. Suppose that the table *counts* has five rows with the numbers 1 to 5.
    How many lines does this code print?


```markdown
var sql = "SELECT num FROM counts WHERE num> ?";
try (var ps = conn.prepareStatement(sql)) {
    ps.setInt(1, 3);

    try (var rs = ps.executeQuery()) {
        while (rs.next())
            System.out.println(rs.getObject(1));
    }

    try (var rs = ps.executeQuery()) {
        while (rs.next())
            System.out.println(rs.getObject(1));
    }
}
```

A. 0    <br>
B. 1    <br>
C. 2    <br>
D. 4    <br>
E. The code does not compile.   <br>
F. The code throws an exception. <br>



Answer: E