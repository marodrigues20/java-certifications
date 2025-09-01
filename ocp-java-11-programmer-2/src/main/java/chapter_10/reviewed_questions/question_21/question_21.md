21. Which is the first line containing a compiler error?

```markdown
25: String url = "jdbc:derby:zoo:;
26: try (var conn = DriverManager.getConnection(url);
27:     var ps = conn.prepareStatement();
28:     var rs = ps.executeQuery("SELECT * FROM swings")) {
29:     while (rs. next()) {
30:         System.out.println(rs.getInteger(1));
31:     }
32: }
```


A. Line 26 <br>
B. Line 27 <br>
C. Line 28 <br>
D. Line 29 <br>
E. Line 30 <br>
F. None of the above <br>


Answer: C