package hellofx;

import java.sql.*;

class database {
    public Statement get() throws ClassNotFoundException, SQLException {
        
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/BABARIA", "root", "");
            Statement stmt = con.createStatement();
            return stmt;
    }
}