package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.Vector;
import khoaphd.conn.MyConnection;
import khoaphd.dtos.HistoryDTO;

/**
 *
 * @author KhoaPHD
 */
public class HistoryDAO implements Serializable {
    
    private Connection conn;
    private PreparedStatement preStm;
    private ResultSet rs;
    
    public void closeConnection() throws Exception {
        if (rs != null)
            rs.close();
        if (preStm != null)
            preStm.close();
        if (conn != null)
            conn.close();
    }
    
    public Vector<HistoryDTO> getHistoryByID(String userID) throws Exception {
        Vector<HistoryDTO> result = new Vector<>();
        String promoID = null;
        int rank = 0;
        boolean status = false;
        Timestamp datetime = null;
        HistoryDTO dto = null;
        try {
            String sql = "SELECT PromoID, Rank, Status, Date FROM History WHERE UserID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, userID);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                promoID = rs.getString("PromoID");
                rank = rs.getInt("Rank");
                status = rs.getBoolean("Status");
                datetime = rs.getTimestamp("Date");
                dto = new HistoryDTO(promoID, userID, rank, status, datetime);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
}