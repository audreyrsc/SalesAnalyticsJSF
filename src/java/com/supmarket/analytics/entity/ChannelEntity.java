/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Audrey
 */
@Entity
@Table(name = "channel")
@XmlRootElement(name = "channel")
public class ChannelEntity implements Serializable {
    
    @Id
    private Integer channelId;
    private String channelClass;
    private Integer channelClassId;
    private String channelDesc;
    private String channelTotal;
    private Integer channelTotalId;

    public String getChannelClass() {
        return channelClass;
    }

    public void setChannelClass(String channelClass) {
        this.channelClass = channelClass;
    }

    public Integer getChannelClassId() {
        return channelClassId;
    }

    public void setChannelClassId(Integer channelClassId) {
        this.channelClassId = channelClassId;
    }

    public String getChannelDesc() {
        return channelDesc;
    }

    public void setChannelDesc(String channelDesc) {
        this.channelDesc = channelDesc;
    }

    public Integer getChannelId() {
        return channelId;
    }

    public void setChannelId(Integer channelId) {
        this.channelId = channelId;
    }

    public String getChannelTotal() {
        return channelTotal;
    }

    public void setChannelTotal(String channelTotal) {
        this.channelTotal = channelTotal;
    }

    public Integer getChannelTotalId() {
        return channelTotalId;
    }

    public void setChannelTotalId(Integer channelTotalId) {
        this.channelTotalId = channelTotalId;
    }
    
}
