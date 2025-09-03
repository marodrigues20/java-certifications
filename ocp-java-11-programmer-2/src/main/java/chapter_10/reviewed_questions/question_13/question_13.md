13. Suppose that the table *counts* has five rows with the numbers 1 to 5. How many lines does this code print?


```markdown
var sql = "SELECT num FROM counts WHERE num> ?";
try (var ps = conn.prepareStatement(sql)) {
    ps.setInt(1, 3);
    
    try (var rs = ps.executeQuery()) {
        while (rs.next())
            System.out.println(rs.getObject(1));
    }
    ps.setInt(1, 100);

    try (var rs = ps.executeQuery()) {
        while (rs.next())
            System.out.println(rs.getObject(1));
    }
}
```

A. 0 <br>
B. 1 <br>
C. 2    <br>
D. 4    <br>
E. The code does not compile. <br>
F. The code throws an exception. <br>


Answer: C

- This code calls the *PreparedStatement* twice.
- The first time, it gets the number greater than 3.
- Since there are two such numbers, it prints two lines.
- The second time, it gets the numbers greater than 100.
- There are no such numbers, so the *ResultSet* is empty. A total of two lines is printed, making option C correct.

