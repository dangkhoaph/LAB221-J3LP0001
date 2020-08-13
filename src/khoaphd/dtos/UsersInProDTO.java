package khoaphd.dtos;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author KhoaPHD
 */
public class UsersInProDTO implements Serializable {
    
    private String promoID, userID;
    private int rank;

    public UsersInProDTO(String promoID, String userID, int rank) {
        this.promoID = promoID;
        this.userID = userID;
        this.rank = rank;
    }
    
    public Vector toVector() {
        Vector v = new Vector();
        v.add(userID);
        v.add(rank);
        return v;
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
}