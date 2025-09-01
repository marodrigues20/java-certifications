10. Suppose that the table *enrichment* has three rows with the animals *bat*, *rat*, and *snake*.
    How many lines does this code print?

```markdown
var sql = "SELECT toy FROM enrichment WHERE animal = ?";
try (var ps = conn.prepareStatement(sql)) {
    ps.setString(1, "bat");

    try (var rs = ps.executeQuery(sql)){
        while (rs.next())
            System.out.println(rs.getString(1));
    }
}
```


A. 0 <br>
b. 1 <br>
C. 3 <br>
D. The code does not compile. <br>
E. A *SQLException* is thrown. <br>
F. A different exception is thrown. <br>


Answer: E