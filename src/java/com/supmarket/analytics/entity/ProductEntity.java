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
@Table(name = "product")
@XmlRootElement(name = "product")
public class ProductEntity implements Serializable {
    
    @Id
    private Integer prodId;
    private String prodCategory;
    private String prodCategoryDesc;
    private Integer prodCategoryId;
    private String prodDesc;
    private String prodEffFrom;
    private String prodEffTo;
    private BigDecimal prodListPrice;
    private BigDecimal prodMinPrice;
    private String prodName;
    private String prodPackSize;
    private Integer prodSrcId;
    private String prodStatus;
    private String prodSubcategory;
    private String prodSubcategoryDesc;
    private Integer prodSubcategoryId;
    private String prodTotal;
    private Integer prodTotalId;
    private String prodUnitOfMeasure;
    private String prodValid;
    private Integer prodWeightClass;
    private Integer supplierId;

    public Integer getProdId() {
        return prodId;
    }

    public void setProdId(Integer prodId) {
        this.prodId = prodId;
    }

    public String getProdName() {
        return prodName;
    }

    public void setProdName(String prodName) {
        this.prodName = prodName;
    }

    public String getProdCategory() {
        return prodCategory;
    }

    public void setProdCategory(String prodCategory) {
        this.prodCategory = prodCategory;
    }

    public BigDecimal getProdListPrice() {
        return prodListPrice;
    }

    public void setProdListPrice(BigDecimal prodListPrice) {
        this.prodListPrice = prodListPrice;
    }

    public BigDecimal getProdMinPrice() {
        return prodMinPrice;
    }

    public void setProdMinPrice(BigDecimal prodMinPrice) {
        this.prodMinPrice = prodMinPrice;
    }

    public String getProdCategoryDesc() {
        return prodCategoryDesc;
    }

    public void setProdCategoryDesc(String prodCategoryDesc) {
        this.prodCategoryDesc = prodCategoryDesc;
    }

    public Integer getProdCategoryId() {
        return prodCategoryId;
    }

    public void setProdCategoryId(Integer prodCategoryId) {
        this.prodCategoryId = prodCategoryId;
    }

    public String getProdDesc() {
        return prodDesc;
    }

    public void setProdDesc(String prodDesc) {
        this.prodDesc = prodDesc;
    }

    public String getProdEffFrom() {
        return prodEffFrom;
    }

    public void setProdEffFrom(String prodEffFrom) {
        this.prodEffFrom = prodEffFrom;
    }

    public String getProdEffTo() {
        return prodEffTo;
    }

    public void setProdEffTo(String prodEffTo) {
        this.prodEffTo = prodEffTo;
    }

    public String getProdPackSize() {
        return prodPackSize;
    }

    public void setProdPackSize(String prodPackSize) {
        this.prodPackSize = prodPackSize;
    }

    public Integer getProdSrcId() {
        return prodSrcId;
    }

    public void setProdSrcId(Integer prodSrcId) {
        this.prodSrcId = prodSrcId;
    }

    public String getProdStatus() {
        return prodStatus;
    }

    public void setProdStatus(String prodStatus) {
        this.prodStatus = prodStatus;
    }

    public String getProdSubcategory() {
        return prodSubcategory;
    }

    public void setProdSubcategory(String prodSubcategory) {
        this.prodSubcategory = prodSubcategory;
    }

    public String getProdSubcategoryDesc() {
        return prodSubcategoryDesc;
    }

    public void setProdSubcategoryDesc(String prodSubcategoryDesc) {
        this.prodSubcategoryDesc = prodSubcategoryDesc;
    }

    public Integer getProdSubcategoryId() {
        return prodSubcategoryId;
    }

    public void setProdSubcategoryId(Integer prodSubcategoryId) {
        this.prodSubcategoryId = prodSubcategoryId;
    }

    public String getProdTotal() {
        return prodTotal;
    }

    public void setProdTotal(String prodTotal) {
        this.prodTotal = prodTotal;
    }

    public Integer getProdTotalId() {
        return prodTotalId;
    }

    public void setProdTotalId(Integer prodTotalId) {
        this.prodTotalId = prodTotalId;
    }

    public String getProdUnitOfMeasure() {
        return prodUnitOfMeasure;
    }

    public void setProdUnitOfMeasure(String prodUnitOfMeasure) {
        this.prodUnitOfMeasure = prodUnitOfMeasure;
    }

    public String getProdValid() {
        return prodValid;
    }

    public void setProdValid(String prodValid) {
        this.prodValid = prodValid;
    }

    public Integer getProdWeightClass() {
        return prodWeightClass;
    }

    public void setProdWeightClass(Integer prodWeightClass) {
        this.prodWeightClass = prodWeightClass;
    }

    public Integer getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Integer supplierId) {
        this.supplierId = supplierId;
    }
    
    
}
