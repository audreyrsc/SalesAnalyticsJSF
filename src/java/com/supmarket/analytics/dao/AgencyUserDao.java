/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.dao;

import com.supmarket.analytics.entity.AgencyUserEntity;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Audrey
 */
@Local
public interface AgencyUserDao {
    
    public void addUser(AgencyUserEntity user);
    public AgencyUserEntity findUserByLoginPassword(String email, String password);
    public List<AgencyUserEntity> getUsers();
    
}
