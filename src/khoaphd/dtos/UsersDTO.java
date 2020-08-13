package khoaphd.dtos;

import java.io.Serializable;
import java.util.Vector;

/**
 *
 * @author KhoaPHD
 */
public class UsersDTO implements Serializable {
    
    private String userID, password, username, email, phone, photoName, role;

    public UsersDTO(String userID, String password, String username, String email, String phone, String photoName, String role) {
        this.userID = userID;
        this.password = password;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.photoName = photoName;
        this.role = role;
    }

    public UsersDTO(String userID, String username, String email, String phone, String photoName, String role) {
        this.userID = userID;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.photoName = photoName;
        this.role = role;
    }

    public UsersDTO(String userID, String username, String role) {
        this.userID = userID;
        this.username = username;
        this.role = role;
    }
    
    public Vector toVectorLong() {
        Vector v = new Vector();
        v.add(userID);
        v.add(username);
        v.add(email);
        v.add(phone);
        v.add(photoName);
        v.add(role);
        return v;
    }
    
    public Vector toVectorShort() {
        Vector v = new Vector();
        v.add(userID);
        v.add(username);
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}