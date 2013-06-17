/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.supmarket.analytics.dao.AgencyDao;
import com.supmarket.analytics.dao.SaleDao;
import com.supmarket.analytics.entity.AgencyEntity;
import com.supmarket.analytics.entity.ChannelEntity;
import com.supmarket.analytics.entity.CustomerEntity;
import com.supmarket.analytics.entity.ProductEntity;
import com.supmarket.analytics.entity.SaleEntity;
import com.supmarket.analytics.model.SalesModel;
import com.supsellers.us.sales.export.Sale;
import com.supsellers.us.sales.export.SalesExport;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 *
 * @author Audrey
 */
@Stateless
public class SaleService {
    
    @EJB
    private SaleDao saleDao;
    @EJB
    private AgencyDao agencyDao;
    @EJB
    private ChannelService channelService;
            
    public List<Sale> retrieveSalesFromWsdl() {
        List<Sale> sales = new ArrayList<Sale>();
        
        try {
            URL wsdlLocation = URI.create("http://supseller-paris.servme.fr/app/SalesExportService?wsdl").toURL();
            QName serviceName = QName.valueOf("{http://us.supsellers.com/sales/export}SalesExportService");
            Service service = Service.create(wsdlLocation,serviceName);
            SalesExport webService = service.getPort(SalesExport.class);
            sales.addAll(webService.getFranceSales());
            return sales;
        } catch (MalformedURLException ex) {
            System.err.println(ex.getMessage());
            return sales;
        }
    }
    
    public List<Sale> retrieveSalesFromJson() {
        List<Sale> sales = new ArrayList<Sale>();
        try {
            System.out.println("get sales json");
            URL url = new URL("http://supsellermontreal.supinfo.cloudbees.net/sales/export.json");
            
            StringBuilder builder = new StringBuilder();
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            
            char[] buff = new char[256];
            while(reader.read(buff) != -1) {
                builder.append(buff);
//                System.out.println(buff);
                buff = new char[256];
            }
//            System.out.println(builder.subSequence(builder.length()-200, builder.length()));
            Gson gson = new Gson();
            
            List<SaleEntity> list = new ArrayList<SaleEntity>();
            
//            JSONArray arrayJson = new JSONArray(builder.toString());
//            System.out.println("test");
//            for(int i=0; i<arrayJson.length(); i++) {
//                JSONObject obj = arrayJson.getJSONObject(i);
//                list.add(gson.fromJson(obj.toString(), SaleEntity.class));
//            }

            list = gson.fromJson(builder.toString(), new TypeToken<List<SaleEntity>>(){}.getType());
            
            System.out.println("list size: "+list.size());
        } catch (IOException ex) {
            System.err.println("IOException: "+ex.getMessage());
        } 
//        catch (JSONException ex) {
//            System.err.println("JSONException: "+ex.getMessage());
//        }

        return sales;
    }
    
    public SaleEntity addSaleFromWsdl(Sale sale, ChannelEntity channel, CustomerEntity customer, ProductEntity product) {
        SaleEntity saleEntity = saleDao.findSaleById(sale.getSaleId());
        if(saleEntity==null) {
            saleEntity = new SaleEntity();
            saleEntity.setAmountSold(sale.getAmountSold());
            saleEntity.setChannel(channel);
            saleEntity.setCustomer(customer);
            saleEntity.setProduct(product);
            saleEntity.setAgency(agencyDao.findAgencyByName("Paris"));
//                saleEntity.setPromotion(promo);
            saleEntity.setQuantitySold(sale.getQuantitySold());
            saleEntity.setSaleId(sale.getSaleId());
            saleEntity.setTimeId(sale.getTimeId());
            saleDao.addSale(saleEntity);
        }
        return saleEntity;
    }
    
    public long getCountSales() {
        return saleDao.getCountSales();
    }
    
    public long getCountSalesByFilter(Map<String,String> params) {
        return saleDao.getCountSalesByFilter(params);
    }
    
    public BigDecimal getAmountSales() {
        return saleDao.getAmountSales();
    }
    
    public BigDecimal getAmountSalesByFilter(Map<String,String> params) {
        return saleDao.getAmoutSalesByFiltre(params);
    }
    
    public long countOfSalesByChannel(ChannelEntity channel) {
        return saleDao.countOfSalesByChannel(channel);
    }
    
    public long countOfSalesByAgency(AgencyEntity agency) {
        return saleDao.countOfSalesByAgency(agency);
    }
    
    public long countSalesByGender(String gender) {
        return saleDao.countOfSalesByGender(gender);
    }
    
    public long countSalesByMaritalStatus(String maritalStatus) {
        return saleDao.countSalesByMaritalStatus(maritalStatus);
    }
    
    public long countSalesByIncomeLevel(String incomeLevel) {
        return saleDao.countSalesByIncomeLevel(incomeLevel);
    }
    
    public List<SalesModel> getBestSellingProductsModel() {
        return saleDao.getBestSellingProductsModel();
    }
    
    public List<SalesModel> getBestSellingProductsModelByFilter(Map<String,String> params) {
        return saleDao.getBestSellingProductsModelByFilter(params);
    }
    
    public List<SalesModel> getLessSellingProductsModel() {
        return saleDao.getLessSellingProductsModel();
    }
    
    public List<SalesModel> getLessSellingProductsModelByFilters(Map<String,String> params) {
        return saleDao.getLessSellingProductsModelByFilters(params);
    }
    
    public List<SalesModel> getMostLoyalCustomers() {
        return saleDao.getMostLoyalCustomersModel();
    }
    
    public List<SalesModel> getBreakdownByChannel() {
        List<ChannelEntity> channels = channelService.getChannels();
        List<SalesModel> sales = new ArrayList<SalesModel>();
        
        for(ChannelEntity channel : channels) {
            sales.add(new SalesModel(channel.getChannelDesc(),(double)countOfSalesByChannel(channel)));
        }
        
        return sales;
    }
    
    public List<SalesModel> getBreakdownByAgency() {
        List<AgencyEntity> agencies = agencyDao.getAgencies();
        List<SalesModel> sales = new ArrayList<SalesModel>();
        
        for(AgencyEntity agency : agencies) {
            sales.add(new SalesModel(agency.getName(),(double)countOfSalesByAgency(agency)));
        }
        
        return sales;
    }
}
