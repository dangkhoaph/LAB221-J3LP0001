package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import khoaphd.conn.MyConnection;
import khoaphd.dtos.UsersDTO;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author KhoaPHD
 */
public class UsersDAO implements Serializable {
    
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    private static final boolean ACTIVE = true;
    private static final boolean INACTIVE = false;
    
    public void closeConnection() throws Exception {
        if (rs != null)
            rs.close();
        if (preStm != null)
            preStm.close();
        if (conn != null)
            conn.close();
    }
    
    public Vector<UsersDTO> getAllUsersLong() throws Exception {
        Vector<UsersDTO> result = new Vector<>();
        String id = null;
        String name = null;
        String email = null;
        String phone = null;
        String photoName = null;
        String role = null;
        UsersDTO dto = null;
        try {
            String sql = "SELECT UserID, Username, Email, Phone, PhotoName, Role"
                    + " FROM Users WHERE Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, ACTIVE);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                id = rs.getString("UserID");
                name = rs.getString("Username");
                email = rs.getString("Email");
                phone = rs.getString("Phone");
                photoName = rs.getString("PhotoName");
                role = rs.getString("Role");
                dto = new UsersDTO(id, name, email, phone, photoName, role);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public Vector<UsersDTO> getAllUsersShort() throws Exception {
        Vector<UsersDTO> result = new Vector<>();
        String id = null;
        String name = null;
        String role = null;
        UsersDTO dto = null;
        try {
            String sql = "SELECT UserID, Username, Role FROM Users WHERE Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, ACTIVE);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                id = rs.getString("UserID");
                name = rs.getString("Username");
                role = rs.getString("Role");
                dto = new UsersDTO(id, name, role);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public Vector<UsersDTO> getUsersByRole(String role) throws Exception {
        Vector<UsersDTO> result = new Vector<>();
        String id = null;
        String name = null;
        String email = null;
        String phone = null;
        String photoName = null;
        UsersDTO dto = null;
        try {
            String sql = "SELECT UserID, Username, Email, Phone, PhotoName"
                    + " FROM Users WHERE Role = ? AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, role);
            preStm.setBoolean(2, ACTIVE);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                id = rs.getString("UserID");
                name = rs.getString("Username");
                email = rs.getString("Email");
                phone = rs.getString("Phone");
                photoName = rs.getString("PhotoName");
                dto = new UsersDTO(id, name, email, phone, photoName, role);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public UsersDTO getUserByID(String id) throws Exception {
        UsersDTO dto = null;
        String name = null;
        String email = null;
        String phone = null;
        String photoName = null;
        String role = null;
        try {
            String sql = "SELECT Username, Email, Phone, PhotoName, Role"
                    + " FROM Users WHERE UserID = ? AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            preStm.setBoolean(2, ACTIVE);
            rs = preStm.executeQuery();
            if (rs.next()) {
                name = rs.getString("Username");
                email = rs.getString("Email");
                phone = rs.getString("Phone");
                photoName = rs.getString("PhotoName");
                role = rs.getString("Role");
                dto = new UsersDTO(id, name, email, phone, photoName, role);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
    
    public Vector<UsersDTO> getUsersByLikeName(String nameSearch) throws Exception {
        Vector<UsersDTO> result = new Vector<>();
        String id = null;
        String name = null;
        String email = null;
        String phone = null;
        String photoName = null;
        String role = null;
        UsersDTO dto = null;
        try {
            String sql = "SELECT UserID, Username, Email, Phone, PhotoName, Role"
                    + " FROM Users WHERE Username LIKE ? AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + nameSearch + "%");
            preStm.setBoolean(2, ACTIVE);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                id = rs.getString("UserID");
                name = rs.getString("Username");
                email = rs.getString("Email");
                phone = rs.getString("Phone");
                photoName = rs.getString("PhotoName");
                role = rs.getString("Role");
                dto = new UsersDTO(id, name, email, phone, photoName, role);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public Vector<UsersDTO> getUsersByLikeNameAndRole(String nameSearch, String role) throws Exception {
        Vector<UsersDTO> result = new Vector<>();
        String id = null;
        String name = null;
        String email = null;
        String phone = null;
        String photoName = null;
        UsersDTO dto = null;
        try {
            String sql = "SELECT UserID, Username, Email, Phone, PhotoName"
                    + " FROM Users WHERE Username LIKE ? AND Role = ? AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, "%" + nameSearch + "%");
            preStm.setString(2, role);
            preStm.setBoolean(3, ACTIVE);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                id = rs.getString("UserID");
                name = rs.getString("Username");
                email = rs.getString("Email");
                phone = rs.getString("Phone");
                photoName = rs.getString("PhotoName");
                dto = new UsersDTO(id, name, email, phone, photoName, role);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean insert(UsersDTO dto) throws Exception {
        String encryptPass = DigestUtils.sha256Hex(dto.getPassword());
        boolean result = false;
        try {
            String sql = "INSERT INTO Users VALUES (?,?,?,?,?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUserID());
            preStm.setString(2, encryptPass);
            preStm.setString(3, dto.getUsername());
            preStm.setString(4, dto.getEmail());
            preStm.setString(5, dto.getPhone());
            preStm.setString(6, dto.getPhotoName());
            preStm.setString(7, dto.getRole());
            preStm.setBoolean(8, ACTIVE);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean update(UsersDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE Users"
                    + " SET Username = ?, Email = ?, Phone = ?, PhotoName = ?, Role = ?"
                    + " WHERE UserID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getUsername());
            preStm.setString(2, dto.getEmail());
            preStm.setString(3, dto.getPhone());
            preStm.setString(4, dto.getPhotoName());
            preStm.setString(5, dto.getRole());
            preStm.setString(6, dto.getUserID());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean delete(String id) throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE Users SET Status = ? WHERE UserID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, INACTIVE);
            preStm.setString(2, id);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean resetPass(String id) throws Exception {
        String defaultPass = DigestUtils.sha256Hex("123456");
        boolean result = false;
        try {
            String sql = "UPDATE Users SET Password = ? WHERE UserID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, defaultPass);
            preStm.setString(2, id);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean changePass(String id, String oldPass, String newPass) throws Exception {
        String oldPassEncr = DigestUtils.sha256Hex(oldPass);
        String newPassEncr = DigestUtils.sha256Hex(newPass);
        boolean result = false;
        try {
            String sql = "UPDATE Users SET Password = ? WHERE UserID = ? AND Password = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, newPassEncr);
            preStm.setString(2, id);
            preStm.setString(3, oldPassEncr);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}