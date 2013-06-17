/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.dao;

import com.supmarket.analytics.entity.CustomerEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Audrey
 */
@Local
public interface CustomerDao {
    
    public void addCustomer(CustomerEntity customer);
    public CustomerEntity findCustomerById(Integer id);
    
    public List<String> getGenders();
    public List<String> getMaritalStatus();
    public List<String> getIncomeLevels();
    
}
