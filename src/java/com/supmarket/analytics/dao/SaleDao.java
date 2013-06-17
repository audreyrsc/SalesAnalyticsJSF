/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.dao;

import com.supmarket.analytics.entity.AgencyEntity;
import com.supmarket.analytics.entity.ChannelEntity;
import com.supmarket.analytics.entity.ProductEntity;
import com.supmarket.analytics.entity.SaleEntity;
import com.supmarket.analytics.model.SalesModel;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import javax.ejb.Local;

/**
 *
 * @author Audrey
 */
@Local
public interface SaleDao {
    
    public void addSale(SaleEntity sale);

    public long getCountSales();
    public long getCountSalesByFilter(Map<String,String> params);
    public BigDecimal getAmountSales();
    public BigDecimal getAmoutSalesByFiltre(Map<String,String> params);
    
    /**
     * Get the top 10 products sold
     * @return a map with product's names in key and the count of sales in value
     */
    public List<SalesModel> getBestSellingProductsModel();
    public List<SalesModel> getBestSellingProductsModelByFilter(Map<String,String> params);
    
    /**
     * Get the less 10 products sold
     * @return a map with product's names in key and the count of sales in value
     */
    public List<SalesModel> getLessSellingProductsModel();
    public List<SalesModel> getLessSellingProductsModelByFilters(Map<String,String> params);
    
    public long countOfSalesByChannel(ChannelEntity channel);
    
    public long countOfSalesByAgency(AgencyEntity agency);
    public long countOfSalesByGender(String gender);
    public long countSalesByMaritalStatus(String maritalStatus);
    public long countSalesByIncomeLevel(String incomeLevel);
    
    public SaleEntity findSaleById(Integer id);
    
    public List<SalesModel> getMostLoyalCustomersModel();
}
