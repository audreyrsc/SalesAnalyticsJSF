/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Audrey
 */
@Entity
@Table(name = "promotion")
@XmlRootElement(name = "promotion")
public class PromotionEntity implements Serializable {
    
    @Id
    private Integer promoId;
    private String promoBeginDate;
    private String promoCategory;
    private Integer promoCategoryId;
    private BigDecimal promoCost;
    private String promoEndDate;
    private String promoName;
    private String promoSubcategory;
    private Integer promoSubcategoryId;
    private String promoTotal;
    private Integer promoTotalId;

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public String getPromoBeginDate() {
        return promoBeginDate;
    }

    public void setPromoBeginDate(String promoBeginDate) {
        this.promoBeginDate = promoBeginDate;
    }

    public String getPromoCategory() {
        return promoCategory;
    }

    public void setPromoCategory(String promoCategory) {
        this.promoCategory = promoCategory;
    }

    public Integer getPromoCategoryId() {
        return promoCategoryId;
    }

    public void setPromoCategoryId(Integer promoCategoryId) {
        this.promoCategoryId = promoCategoryId;
    }

    public BigDecimal getPromoCost() {
        return promoCost;
    }

    public void setPromoCost(BigDecimal promoCost) {
        this.promoCost = promoCost;
    }

    public String getPromoEndDate() {
        return promoEndDate;
    }

    public void setPromoEndDate(String promoEndDate) {
        this.promoEndDate = promoEndDate;
    }

    public String getPromoSubcategory() {
        return promoSubcategory;
    }

    public void setPromoSubcategory(String promoSubcategory) {
        this.promoSubcategory = promoSubcategory;
    }

    public Integer getPromoSubcategoryId() {
        return promoSubcategoryId;
    }

    public void setPromoSubcategoryId(Integer promoSubcategoryId) {
        this.promoSubcategoryId = promoSubcategoryId;
    }

    public String getPromoTotal() {
        return promoTotal;
    }

    public void setPromoTotal(String promoTotal) {
        this.promoTotal = promoTotal;
    }

    public Integer getPromoTotalId() {
        return promoTotalId;
    }

    public void setPromoTotalId(Integer promoTotalId) {
        this.promoTotalId = promoTotalId;
    }
    
}
