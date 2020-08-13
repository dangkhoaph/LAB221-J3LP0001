package khoaphd.dtos;

import java.io.Serializable;

/**
 *
 * @author KhoaPHD
 */
public class PromotionsDTO implements Serializable {
    
    private String promoID, promoName, description;

    public PromotionsDTO(String promoID, String promoName, String description) {
        this.promoID = promoID;
        this.promoName = promoName;
        this.description = description;
    }

    public String getPromoID() {
        return promoID;
    }

    public void setPromoID(String promoID) {
        this.promoID = promoID;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}