/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.service;

import com.supmarket.analytics.dao.ChannelDao;
import com.supmarket.analytics.entity.ChannelEntity;
import com.supsellers.us.sales.export.Channel;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Audrey
 */
@Stateless
public class ChannelService {
    
    @EJB
    private ChannelDao channelDao;
    
    public ChannelEntity addChannelFromWsdl(Channel channelWsdl) {
        ChannelEntity channel = channelDao.findChannelById(channelWsdl.getChannelId());
        if(channel==null) {
            channel = new ChannelEntity();
            channel.setChannelId(channelWsdl.getChannelId());
            channel.setChannelDesc(channelWsdl.getChannelDesc());
            channel.setChannelClass(channelWsdl.getChannelClass());
            channel.setChannelClassId(channelWsdl.getChannelClassId());
            channel.setChannelTotal(channelWsdl.getChannelTotal());
            channel.setChannelTotalId(channelWsdl.getChannelTotalId());
            channelDao.addChannel(channel);
        }
        return channel;
    }
    
    public List<ChannelEntity> getChannels() {
        return channelDao.getDistinctChannels();
    }
}
