18. There are currently 100 rows in the table *species* before inserting a new row.
    What is the output of the following code?


```markdown
String insert = "INSERT INTO species VALUES(3, 'Ant', .05)";
String select = "SELECT count(*) FROM species";
try (var ps = conn.prepareStatement(insert)) {
    ps.executeUpdate();
}
try (var ps = conn.prepareStatement(select)) {
    var rs = ps.executeQuery();
    System.out.println(rs.getInt(1));
}
```

A. 100  <br>
B. 101  <br>
C. The code does not compile. <br>
D. A *SQLException* is thrown. <br>
E. A different exception is thrown. <br>


Answer: D