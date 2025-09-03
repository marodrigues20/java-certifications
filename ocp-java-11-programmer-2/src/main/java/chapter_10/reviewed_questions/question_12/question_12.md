12. Suppose we have a JDBC program that calls a stored procedure, which returns a set of result.
    Which is the correct order in which to close database resources.


A. Connection, ResultSet, CallableStatement <br>
B. Connection, CallableStatement, ResultSet <br>
C. ResultSet, Connection, CallableStatement <br>
D. ResultSet, CallableStatement, Connection <br>
E. CallableStatement, Connection, ResultSet <br>
F. CallableStatement, ResultSet, Connection <br>


Answer: D

- JDBC resources should be closed in the reverse order from that in which they were opened.
- The order for opening is *Connection, CallableStatement*, and *ResultSet*. 
- The order for closing is *ResultSet, CallableStatement*, and *Connection*.


