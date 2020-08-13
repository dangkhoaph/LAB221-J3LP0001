package khoaphd.models;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;
import khoaphd.conn.MyConnection;
import khoaphd.dtos.PromotionsDTO;

/**
 *
 * @author KhoaPHD
 */
public class PromotionsDAO implements Serializable {
    
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
    
    public Vector<PromotionsDTO> getAllPromotions() throws Exception {
        Vector<PromotionsDTO> result = new Vector<>();
        String id = null;
        String name = null;
        String description = null;
        PromotionsDTO dto = null;
        try {
            String sql = "SELECT PromoID, PromoName, PromoDes FROM Promotions";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            rs = preStm.executeQuery();
            while (rs.next()) {                
                id = rs.getString("PromoID");
                name = rs.getString("PromoName");
                description = rs.getString("PromoDes");
                dto = new PromotionsDTO(id, name, description);
                result.add(dto);
            }
        } finally {
            closeConnection();
        }
        return result;
    }
    
    public PromotionsDTO getPromotionByID(String id) throws Exception {
        String name = null;
        String description = null;
        PromotionsDTO dto = null;
        try {
            String sql = "SELECT PromoName, PromoDes FROM Promotions WHERE PromoID = ?";
            conn = MyConnection.getMyConnection();
            preStm = conn.prepareStatement(sql);
            preStm.setString(1, id);
            rs = preStm.executeQuery();
            if (rs.next()) {
                name = rs.getString("PromoName");
                description = rs.getString("PromoDes");
                dto = new PromotionsDTO(id, name, description);
            }
        } finally {
            closeConnection();
        }
        return dto;
    }
}