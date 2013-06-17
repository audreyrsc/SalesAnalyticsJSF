/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.dao;

import com.supmarket.analytics.entity.AgencyEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Audrey
 */
@Local
public interface AgencyDao {
    
    public void addAgency(AgencyEntity agency);
    public AgencyEntity findAgencyByName(String name);
    public List<AgencyEntity> getAgencies();
    
}
