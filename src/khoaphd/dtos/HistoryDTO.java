package khoaphd.dtos;

import java.io.Serializable;
import java.sql.Timestamp;

/**
 *
 * @author KhoaPHD
 */
public class HistoryDTO implements Serializable {
    
    private String promoID, userID;
    private int rank;
    private boolean status;
    private Timestamp datetime;

    public HistoryDTO(String promoID, String userID, int rank, boolean status, Timestamp datetime) {
        this.promoID = promoID;
        this.userID = userID;
        this.rank = rank;
        this.status = status;
        this.datetime = datetime;
    }
    
    public String getPromoID() {
        return promoID;
    }

    public void setPromoID(String promoID) {
        this.promoID = promoID;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }
}