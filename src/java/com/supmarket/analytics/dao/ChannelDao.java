/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.dao;

import com.supmarket.analytics.entity.ChannelEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Audrey
 */
@Local
public interface ChannelDao {
    
    public void addChannel(ChannelEntity channel);
    public ChannelEntity findChannelById(Integer id);
    public List<ChannelEntity> getDistinctChannels();
}
