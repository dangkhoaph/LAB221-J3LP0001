package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import khoaphd.conn.MyConnection;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author KhoaPHD
 */
public class AccountsDAO implements Serializable {
    
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    private static final boolean ACTIVE = true;
    
    public void closeConnection() throws Exception {
        if (rs != null)
            rs.close();
        if (preStm != null)
            preStm.close();
        if (conn != null)
            conn.close();
    }
    
    public String checkLogin(String id, String password) throws Exception {
        String encryptPass = DigestUtils.sha256Hex(password);
        String role = "Failed";
        try {
            String sql = "SELECT Role FROM Users WHERE UserID = ? AND Password = ? AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            preStm.setString(2, encryptPass);
            preStm.setBoolean(3, ACTIVE);
            rs = preStm.executeQuery();
            if (rs.next()) {
                role = rs.getString("Role");
            }
        } finally {
            closeConnection();
        }
        return role;
    }
}