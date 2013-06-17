/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.service;

import com.supmarket.analytics.dao.CustomerDao;
import com.supmarket.analytics.entity.CustomerEntity;
import com.supsellers.us.sales.export.Customer;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Audrey
 */
@Stateless
public class CustomerService {
    
    @EJB
    private CustomerDao customerDao;
    
    public CustomerEntity addCustomerFromWsdl(Customer customerWsdl) {
        CustomerEntity customer = customerDao.findCustomerById(customerWsdl.getCustId());
        if(customer==null) {
            customer = new CustomerEntity();
            customer.setCustId(customerWsdl.getCustId());
            customer.setCustEmail(customerWsdl.getCustEmail());
            customer.setCustFirstName(customerWsdl.getCustFirstName());
            customer.setCustLastName(customerWsdl.getCustLastName());
            customer.setCustGender(customerWsdl.getCustGender());
            customer.setCustMaritalStatus(customerWsdl.getCustMaritalStatus());
            customer.setCustIncomeLevel(customerWsdl.getCustIncomeLevel());
            customerDao.addCustomer(customer);
        }
        return customer;
    }
    
    public List<String> getGenders() {
        return customerDao.getGenders();
    }
    
    public List<String> getMaritalStatus() {
        return customerDao.getMaritalStatus();
    }
    
    public List<String> getIncomeLevels() {
        return customerDao.getIncomeLevels();
    }
}
