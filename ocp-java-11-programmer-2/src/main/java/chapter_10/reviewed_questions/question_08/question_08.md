8. Suppose that the table *animal* has five rows and the following SQL statement updates all of them.
   What is the result of this code?


```markdown
public static void main(String[] args) throws SQLException {
    var sql = "UPDATE names SET name = 'Animal'";
    try ( var conn = DriverManager.getConnection("jdbc:derby:zoo");
        var ps = conn.prepareStatement(sql)) {
        
        var result = ps.executeUpdate();
        System.out.println(result);
    }
}
```

A. 0  <br>
B. 1   <br> 
C. 5    <br>
D. The code does not compile. <br>
E. A *SQLException* is thrown. <br>
F. A different exception is thrown. <br>


Answer: D


