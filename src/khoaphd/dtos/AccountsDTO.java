package khoaphd.dtos;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author KhoaPHD
 */
public class AccountsDTO implements Serializable {
    
    private String userID, password, role;

    public AccountsDTO(String userID, String role) {
        this.userID = userID;
        this.role = role;
    }
    
    public Vector toVector() {
        Vector v = new Vector();
        v.add(userID);
        v.add(role);
        return v;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}