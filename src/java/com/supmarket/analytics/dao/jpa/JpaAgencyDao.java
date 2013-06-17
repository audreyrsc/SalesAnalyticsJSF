/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.dao.jpa;

import com.supmarket.analytics.dao.AgencyDao;
import com.supmarket.analytics.entity.AgencyEntity;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Audrey
 */
@Stateless
public class JpaAgencyDao implements AgencyDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addAgency(AgencyEntity agency) {
        try {
            em.persist(agency);
        } catch(PersistenceException e) {
            System.err.println(e.getMessage());
        }
    }

    @Override
    public AgencyEntity findAgencyByName(String name) {
        try {
            return em.find(AgencyEntity.class, name);
        } catch(NoResultException e) {
            System.err.println("The agency named "+name+" doesn't exist.");
            return null;
        } catch(Exception e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<AgencyEntity> getAgencies() {
        List<AgencyEntity> agencies = new ArrayList<AgencyEntity>();
        
        try {
            Query query = em.createQuery("SELECT a FROM AgencyEntity a");
            agencies.addAll(query.getResultList());
            return agencies;
        }catch(PersistenceException e) {
            System.err.println(e.getMessage());
            return agencies;
        }
    }
    
}
