/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Audrey
 */
@Entity
@Table(name = "sale")
@XmlRootElement(name = "sale")
public class SaleEntity implements Serializable {
    
    @Id
    private Integer saleId;
    private BigDecimal amountSold;
    @ManyToOne @JoinColumn(name = "channel")
    private ChannelEntity channel;
    @ManyToOne @JoinColumn(name = "customer")
    private CustomerEntity customer;
    @ManyToOne @JoinColumn(name = "product")
    private ProductEntity product;
    @ManyToOne @JoinColumn(name = "promotion")
    private PromotionEntity promotion;
    private String timeId;
    private BigDecimal quantitySold;
    
    @ManyToOne @JoinColumn(name = "agency")
    private AgencyEntity agency;

    
    public Integer getSaleId() {
        return saleId;
    }

    public void setSaleId(Integer saleId) {
        this.saleId = saleId;
    }

    public BigDecimal getAmountSold() {
        return amountSold;
    }

    public void setAmountSold(BigDecimal amountSold) {
        this.amountSold = amountSold;
    }

    public ChannelEntity getChannel() {
        return channel;
    }

    public void setChannel(ChannelEntity channel) {
        this.channel = channel;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public ProductEntity getProduct() {
        return product;
    }

    public void setProduct(ProductEntity product) {
        this.product = product;
    }

    public PromotionEntity getPromotion() {
        return promotion;
    }

    public void setPromotion(PromotionEntity promotion) {
        this.promotion = promotion;
    }

    public String getTimeId() {
        return timeId;
    }

    public void setTimeId(String timeId) {
        this.timeId = timeId;
    }

    public BigDecimal getQuantitySold() {
        return quantitySold;
    }

    public void setQuantitySold(BigDecimal quantitySold) {
        this.quantitySold = quantitySold;
    }

    @XmlTransient
    public AgencyEntity getAgency() {
        return agency;
    }

    public void setAgency(AgencyEntity agency) {
        this.agency = agency;
    }
    
    
}
