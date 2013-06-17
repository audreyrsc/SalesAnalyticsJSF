/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.rest;

import com.supmarket.analytics.model.SalesModel;
import com.supmarket.analytics.service.ChannelService;
import com.supmarket.analytics.service.SaleService;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Audrey
 */
@Path("/sales")
@Produces(MediaType.APPLICATION_JSON)
public class SalesResources {
    
    @EJB
    private SaleService service;

    @GET @Path("nbSales")
    public String getNbSales() {
        long count = service.getCountSales();
        
        StringBuilder builder = new StringBuilder();
        builder.append("{\"count\":\"").append(count).append("\"}");
        
        return builder.toString();
    }
    
    @GET @Path("amountSales")
    public String getAmountSales() {
        double amount = service.getAmountSales().doubleValue();
        
        StringBuilder builder = new StringBuilder();
        builder.append("{\"amount\":\"").append(amount).append("\"}");
        
        return builder.toString();
    }
    
    @GET @Path("bestSelling")
    public List<SalesModel> getBestSellingProducts(String json) {
        return service.getBestSellingProductsModel();
    }
    
    @GET @Path("lessSelling")
    public List<SalesModel> getLessSellingProducts(String json) {
        return service.getLessSellingProductsModel();
    }
    
    @GET @Path("breakdownChannel")
    public List<SalesModel> getBreakdownByChannel(String json) {
        return service.getBreakdownByChannel();
    }
    
    @GET @Path("breakdownAgency")
    public List<SalesModel> getBreakdownByAgency() {
        return service.getBreakdownByAgency();
    }
    
    @GET @Path("mostLoyalCustomers")
    public List<SalesModel> getMostLoyalCustomers() {
        return service.getMostLoyalCustomers();
    }
}
