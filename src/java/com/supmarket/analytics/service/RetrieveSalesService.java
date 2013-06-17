/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.service;

import com.supmarket.analytics.entity.ChannelEntity;
import com.supmarket.analytics.entity.CustomerEntity;
import com.supmarket.analytics.entity.ProductEntity;
import com.supsellers.us.sales.export.Sale;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Lock;
import javax.ejb.LockType;
import javax.ejb.Schedule;
import javax.ejb.Singleton;

/**
 *
 * @author Audrey
 */
@Singleton
public class RetrieveSalesService {
    
    @EJB
    private CustomerService customerService;
    @EJB
    private ProductService productService;
    @EJB
    private ChannelService channelService;
    @EJB
    private SaleService saleService;
    
    @Schedule(hour = "6", minute = "0", dayOfWeek = "*")
    public void retrieveParisSales() {
        System.out.println("schedule paris");
        List<Sale> sales = saleService.retrieveSalesFromWsdl();
        
        for(Sale sale : sales) {
            CustomerEntity customer = customerService.addCustomerFromWsdl(sale.getCustomer());
            ChannelEntity channel = channelService.addChannelFromWsdl(sale.getChannel());
            ProductEntity product = productService.addProductFromWsdl(sale.getProduct());
            saleService.addSaleFromWsdl(sale,channel,customer,product);
        }
    }
    
    @Schedule(hour = "6", minute = "0", dayOfWeek = "*")
    public void retrieveMontrealSales() {
        System.out.println("schedule montreal");
        saleService.retrieveSalesFromJson();
    }
    
    @Schedule(hour = "6", minute = "0", dayOfWeek = "*")
    public void retrieveTokyoSales() {
        System.out.println("schedule tokyo");
    }
}
