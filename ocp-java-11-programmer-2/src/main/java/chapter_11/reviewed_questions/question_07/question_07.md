7. Which attack could exploit this code?

```markdown
public boolean isValid(String hashedPassword)
    throws SQLException {
    var sql = "SELECT * FROM users WHERE password = "?";
    try (var ps = conn.prepareStatement(sql)) {
        ps.setString(1, hashedPassword);
        try (var rs = stmt.executeQurey(sql)) {
            return rs.next();
        }
    }
}
```

A. Command injection <br>
B. Confidential data exposure <br>
C. Denial of service    <br>
D. SQL injection        <br>
E. SQL leak             <br>
F. None of the above    <br>

