/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.dao.jpa;

import com.supmarket.analytics.dao.CustomerDao;
import com.supmarket.analytics.entity.CustomerEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Audrey
 */
@Stateless
public class JpaCustomerDao implements CustomerDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addCustomer(CustomerEntity customer) {
        try {
            em.persist(customer);
        } catch(PersistenceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public CustomerEntity findCustomerById(Integer id) {
        return em.find(CustomerEntity.class, id);
    }

    @Override
    public List<String> getGenders() {
        List<String> genders = new ArrayList<String>();
        try {
            Query query = em.createQuery("SELECT DISTINCT c.custGender FROM CustomerEntity c");
            genders.addAll(query.getResultList());
            return genders;
        } catch(PersistenceException e) {
            System.out.println(e.getMessage());
            return genders;
        }
    }

    @Override
    public List<String> getMaritalStatus() {
        List<String> status = new ArrayList<String>();
        try {
            Query query = em.createQuery("SELECT DISTINCT c.custMaritalStatus FROM CustomerEntity c");
            status.addAll(query.getResultList());
            return status;
        } catch(PersistenceException e) {
            System.out.println(e.getMessage());
            return status;
        }
    }

    @Override
    public List<String> getIncomeLevels() {
        List<String> levels = new ArrayList<String>();
        try {
            Query query = em.createQuery("SELECT DISTINCT c.custIncomeLevel FROM CustomerEntity c");
            levels.addAll(query.getResultList());
            return levels;
        } catch(PersistenceException e) {
            System.out.println(e.getMessage());
            return levels;
        }
    }
    
}
