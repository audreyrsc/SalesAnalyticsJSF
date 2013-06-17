/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.service;

import com.supmarket.analytics.dao.ProductDao;
import com.supmarket.analytics.entity.ProductEntity;
import com.supsellers.us.sales.export.Product;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Audrey
 */
@Stateless
public class ProductService {
    
    @EJB
    private ProductDao productDao;
    
    public ProductEntity addProductFromWsdl(Product productWsdl) {
        ProductEntity product = productDao.findProductById(productWsdl.getProdId());
        if(product==null) {
            product = new ProductEntity();
            product.setProdId(productWsdl.getProdId());
            product.setProdName(productWsdl.getProdName());
            product.setProdListPrice(productWsdl.getProdListPrice());
            product.setProdMinPrice(productWsdl.getProdMinPrice());
            productDao.addProduct(product);
        }
        return product;
    }
}
