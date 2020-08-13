package khoaphd.conn;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author KhoaPHD
 */
public class MyConnection implements Serializable {
    
    public static Connection getMyConnection() throws Exception {
        Connection conn = null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url = "jdbc:sqlserver://localhost:1433;databaseName=UserManager";
        conn = DriverManager.getConnection(url, "sa", "123456789");
        return conn;
    }
}