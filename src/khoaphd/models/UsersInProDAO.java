package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import khoaphd.conn.MyConnection;
import khoaphd.dtos.UsersInProDTO;

/**
 *
 * @author KhoaPHD
 */
public class UsersInProDAO implements Serializable {
    
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
    
    public Vector<UsersInProDTO> getUsersByPromoID(String promoID) throws Exception {
        Vector<UsersInProDTO> result = new Vector<>();
        String userID = null;
        UsersInProDTO dto = null;
        int rank;
        try {
            String sql = "SELECT UserID, Rank FROM UsersInPro WHERE PromoID = ? AND Status = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, promoID);
            preStm.setBoolean(2, ACTIVE);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                userID = rs.getString("UserID");
                rank = rs.getInt("Rank");
                dto = new UsersInProDTO(promoID, userID, rank);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean insert(UsersInProDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "INSERT INTO UsersInPro (PromoID,UserID,Rank,Status) VALUES (?,?,?,?)";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, dto.getPromoID());
            preStm.setString(2, dto.getUserID());
            preStm.setInt(3, dto.getRank());
            preStm.setBoolean(4, ACTIVE);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean restore(String promoID, String userID) throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE UsersInPro SET Status = ? WHERE PromoID = ? AND UserID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, ACTIVE);
            preStm.setString(2, promoID);
            preStm.setString(3, userID);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean updateRank(UsersInProDTO dto) throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE UsersInPro SET Rank = ? WHERE PromoID = ? AND UserID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setInt(1, dto.getRank());
            preStm.setString(2, dto.getPromoID());
            preStm.setString(3, dto.getUserID());
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean delete(String promoID, String userID) throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE UsersInPro SET Status = ? WHERE PromoID = ? AND UserID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, INACTIVE);
            preStm.setString(2, promoID);
            preStm.setString(3, userID);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public boolean deleteUserFromAllPromotions(String userID) throws Exception {
        boolean result = false;
        try {
            String sql = "UPDATE UsersInPro SET Status = ? WHERE UserID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setBoolean(1, INACTIVE);
            preStm.setString(2, userID);
            result = preStm.executeUpdate() > 0;
        } finally {
            closeConnection();
        }
        return result;
    }
}