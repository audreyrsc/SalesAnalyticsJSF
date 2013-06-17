/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.dao.jpa;

import com.supmarket.analytics.dao.AgencyUserDao;
import com.supmarket.analytics.entity.AgencyUserEntity;
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
public class JpaAgencyUserDao implements AgencyUserDao {

    @PersistenceContext
    private EntityManager em;
    
    @Override
    public void addUser(AgencyUserEntity user) {
        em.persist(user);
    }

    @Override
    public AgencyUserEntity findUserByLoginPassword(String email, String password) {
        try {
            Query query = em.createQuery("SELECT u FROM AgencyUserEntity u WHERE u.email= :email AND u.hashedPassword= md5(:password)");
            query.setParameter("email", email);
            query.setParameter("password", password);
            return (AgencyUserEntity) query.getSingleResult();
        } catch(NoResultException e) {
//            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public List<AgencyUserEntity> getUsers() {
        List<AgencyUserEntity> list = new ArrayList<AgencyUserEntity>();
        
        try {
            list.addAll(em.createQuery("SELECT u FROM AgencyUserEntity u").getResultList());
            return list;
        } catch(PersistenceException e) {
           return list;
        }
    }
    
}
