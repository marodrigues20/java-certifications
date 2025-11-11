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


Answer: F

- This is a trick question - there is no attack.
- Option E is incorrect SQL leak is not the name of an attack.
- Option C is incorrect because *PreparedStatement* and *ResultSet* are closed in a try-with-resources block.
- While we do not see the *Connection* closed, we also don't see it opened.
- The exam allows us to assume code that we can't see is correct.
- Option D is an incorrect answer because bind variables are being used properly with a *PreparedStatement*.
- Option A and B are incorrect because they are not related to the example.
- Since none of these attacks applies here, option F is correct.
