/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.service;

import com.supmarket.analytics.dao.AgencyUserDao;
import com.supmarket.analytics.entity.AgencyUserEntity;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author Audrey
 */
@Stateless
public class UserService {
    
    @EJB
    private AgencyUserDao userDao;
    
    public AgencyUserEntity login(String email, String password) {
        return userDao.findUserByLoginPassword(email, password);
    }
    
}
