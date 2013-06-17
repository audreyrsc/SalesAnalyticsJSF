/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.dao.jpa;

import com.supmarket.analytics.dao.ProductDao;
import com.supmarket.analytics.entity.ProductEntity;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;

/**
 *
 * @author Audrey
 */
@Stateless
public class JpaProductDao implements ProductDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addProduct(ProductEntity product) {
        try {
            em.persist(product);
        }catch(PersistenceException e) {
            System.err.println("Add product: "+e.getMessage());
        }
    }

    @Override
    public ProductEntity findProductById(Integer id) {
        return em.find(ProductEntity.class, id);
    }
    
}
