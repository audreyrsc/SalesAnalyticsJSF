/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.dao.jpa;

import com.supmarket.analytics.dao.ChannelDao;
import com.supmarket.analytics.entity.ChannelEntity;
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
public class JpaChannelDao implements ChannelDao {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public void addChannel(ChannelEntity channel) {
        try {
            em.persist(channel);
        } catch(PersistenceException e) {
            System.err.println("Persist channel: "+e.getMessage());
        }
    }

    @Override
    public ChannelEntity findChannelById(Integer id) {
        return em.find(ChannelEntity.class, id);
    }

    @Override
    public List<ChannelEntity> getDistinctChannels() {
        List<ChannelEntity> channels = new ArrayList<ChannelEntity>();
        
        try {
            channels.addAll(em.createQuery("SELECT c FROM ChannelEntity c").getResultList());
            return channels;
        } catch(PersistenceException e) {
            System.err.println(e.getMessage());
            return channels;
        }
    }
    
    
}
