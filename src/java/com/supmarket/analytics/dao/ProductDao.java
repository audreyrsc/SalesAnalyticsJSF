/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.dao;

import com.supmarket.analytics.entity.ProductEntity;
import javax.ejb.Local;

/**
 *
 * @author Audrey
 */
@Local
public interface ProductDao {
    
    public void addProduct(ProductEntity product);
    public ProductEntity findProductById(Integer id);
    
}
