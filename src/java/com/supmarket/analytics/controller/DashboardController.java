/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.controller;

import com.supmarket.analytics.dao.AgencyDao;
import com.supmarket.analytics.entity.AgencyEntity;
import com.supmarket.analytics.entity.AgencyUserEntity;
import com.supmarket.analytics.entity.ChannelEntity;
import com.supmarket.analytics.model.SalesModel;
import com.supmarket.analytics.service.ChannelService;
import com.supmarket.analytics.service.CustomerService;
import com.supmarket.analytics.service.SaleService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import org.primefaces.model.chart.PieChartModel;

/**
 *
 * @author Audrey
 */
@ManagedBean
public class DashboardController {
    
    // Filters - Lists of items //
    private List<AgencyEntity> agencies;
    private List<String> agenciesNames;
    private List<String> genders;
    private List<String> maritalStatus;
    private List<String> incomeLevels;
    
    // Filters - Items selected //
    private AgencyEntity agencySelected;
    private String agencyNameSelected;
    private String genderSelected;
    private String maritalStatusSelected;
    private String incomeLevelSelected;

    // Datas //
    private long countSales;
    private BigDecimal amountSales;
    
    // Charts //
    private PieChartModel bestSellingProdChart, breakdownChannelChart, breakdownAgencyChart,
            breakdownGenderChart, breakdownMaritalStatusChart, breakdownIncomeLevelChart;
    
    // List data models //
    private DataModel bestSellingProdModel;
    private DataModel lessSellingProdModel;
    private DataModel breakdownIncomeLevelModel;
    private DataModel mostLoyalCustomersModel;
    
    @ManagedProperty(value = "#{userController.currentUser}")
    private AgencyUserEntity user;
    
    @EJB
    private AgencyDao agencyDao;
    @EJB
    private CustomerService customerService;
    @EJB
    private SaleService saleService;
    @EJB
    private ChannelService channelService;

    
    public Map<String,String> getFilters() {
        Map<String,String> filters = new HashMap<String, String>();
        
        if(agencyNameSelected != null && !agencyNameSelected.isEmpty()) {
            filters.put("agency", agencyNameSelected);
        }
        if(genderSelected != null && !genderSelected.isEmpty()) {
            filters.put("gender", genderSelected);
        }
        if(maritalStatusSelected != null && !maritalStatusSelected.isEmpty()) {
            filters.put("maritalStatus", maritalStatusSelected);
        }
        if(incomeLevelSelected != null && !incomeLevelSelected.isEmpty()) {
            filters.put("incomeLevel", incomeLevelSelected);
        }
        
        return filters;
    }
 
    
    // GETTERS & SETTERS //
    
    // Pour les filtres //
    public List<AgencyEntity> getAgencies() {
        agencies = agencyDao.getAgencies();
        return agencies;
    }
    public List<String> getAgenciesNames() {
        List<AgencyEntity> list = agencyDao.getAgencies();
        
        agenciesNames = new ArrayList<String>();
        for(AgencyEntity e : list) {
            agenciesNames.add(e.getName());
        }
        
        return agenciesNames;
    }
    public List<String> getGenders() {
        genders = customerService.getGenders();
        return genders;
    }
    public List<String> getMaritalStatus() {
        maritalStatus = customerService.getMaritalStatus();
        maritalStatus.remove(null);
        return maritalStatus;
    }
    public List<String> getIncomeLevels() {
        return customerService.getIncomeLevels();
    }

    // Val des filtres selectionnés //
    public AgencyEntity getAgencySelected() {
        return agencySelected;
    }
    public void setAgencySelected(AgencyEntity agencySelected) {
        this.agencySelected = agencySelected;
    }
    public String getAgencyNameSelected() {
        return agencyNameSelected;
    }
    public void setAgencyNameSelected(String agencyNameSelected) {
        this.agencyNameSelected = agencyNameSelected;
    }
    public String getGenderSelected() {
        return genderSelected;
    }
    public void setGenderSelected(String genderSelected) {
        this.genderSelected = genderSelected;
    }
    public String getMaritalStatusSelected() {
        return maritalStatusSelected;
    }
    public void setMaritalStatusSelected(String maritalStatusSelected) {
        this.maritalStatusSelected = maritalStatusSelected;
    }
    public String getIncomeLevelSelected() {
        return incomeLevelSelected;
    }
    public void setIncomeLevelSelected(String incomeLevelSelected) {
        this.incomeLevelSelected = incomeLevelSelected;
    }

    // les charts //
    public PieChartModel getBestSellingProdChart() {
        Map<String,String> filters = getFilters();
        List<SalesModel> bestSellings = filters.size()>0 ? saleService.getBestSellingProductsModelByFilter(filters) : saleService.getBestSellingProductsModel();
        bestSellingProdChart = new PieChartModel();

        for(SalesModel model : bestSellings) {
            bestSellingProdChart.set(model.getLabel(), model.getCount());
        }
        return bestSellingProdChart;
    }

    public PieChartModel getBreakdownChannelChart() {
        List<SalesModel> sales = saleService.getBreakdownByChannel();
        breakdownChannelChart = new PieChartModel();
        
        for(SalesModel model : sales){
            breakdownChannelChart.set(model.getLabel(), model.getCount());
        }
        
        return breakdownChannelChart;
    }

    public PieChartModel getBreakdownAgencyChart() {
        List<SalesModel> sales = saleService.getBreakdownByAgency();
        breakdownAgencyChart = new PieChartModel();
        
        for(SalesModel model : sales){
            breakdownAgencyChart.set(model.getLabel(), model.getCount());
        }
            
        return breakdownAgencyChart;
    }

    public PieChartModel getBreakdownGenderChart() {
        breakdownGenderChart = new PieChartModel();
        for(String gender : getGenders()) {
            breakdownGenderChart.set(gender, (double)saleService.countSalesByGender(gender));
        }
        
        return breakdownGenderChart;
    }

    public PieChartModel getBreakdownMaritalStatusChart() {
        breakdownMaritalStatusChart = new PieChartModel();
        for(String status : getMaritalStatus()) {
                breakdownMaritalStatusChart.set(status, (double)saleService.countSalesByMaritalStatus(status));
            }
        return breakdownMaritalStatusChart;
    }

    public PieChartModel getBreakdownIncomeLevelChart() {
        breakdownIncomeLevelChart = new PieChartModel();
        for(String level : getIncomeLevels()) {
                breakdownIncomeLevelChart.set(level, (double)saleService.countSalesByIncomeLevel(level));
            }
        return breakdownIncomeLevelChart;
    }
    
    
    // DataModel //
    
    public DataModel getBestSellingProdModel() {
        Map<String,String> filters = getFilters();
        List<SalesModel> list = filters.size()>0 ? saleService.getBestSellingProductsModelByFilter(filters) : saleService.getBestSellingProductsModel();
        
        for(SalesModel m :list) {
            System.out.println(m.getLabel()+" "+m.getCount());
        }
        
        bestSellingProdModel = new ListDataModel(list);
        
        
        return bestSellingProdModel;
    }

    public DataModel getLessSellingProdModel() {
        Map<String,String> filters = getFilters();
        lessSellingProdModel = new ListDataModel(filters.size()>0 ? saleService.getLessSellingProductsModelByFilters(filters) : saleService.getLessSellingProductsModel());
        return lessSellingProdModel;
    }

    public DataModel getBreakdownIncomeLevelModel() {
        List<SalesModel> sales = new ArrayList<SalesModel>();
        
        for(String level : getIncomeLevels()) {
            sales.add(new SalesModel(level, (double)saleService.countSalesByIncomeLevel(level)));
        }
        
        breakdownIncomeLevelModel = new ListDataModel(sales);
        return breakdownIncomeLevelModel;
    }

    public DataModel getMostLoyalCustomersModel() {
        mostLoyalCustomersModel = new ListDataModel(saleService.getMostLoyalCustomers());
        return mostLoyalCustomersModel;
    }

    
    
    public AgencyUserEntity getUser() {
        return user;
    }

    public void setUser(AgencyUserEntity user) {
        this.user = user;
    }

    public long getCountSales() {
        Map<String,String> filters = getFilters();
        countSales = filters.size()>0 ? saleService.getCountSalesByFilter(filters) : saleService.getCountSales();
        return countSales;
    }

    public BigDecimal getAmountSales() {
        Map<String,String> filters = getFilters();
        amountSales = filters.size()>0 ? saleService.getAmountSalesByFilter(filters) : saleService.getAmountSales();
        return amountSales;
    }

}
