/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.converter;

import com.supmarket.analytics.dao.AgencyDao;
import com.supmarket.analytics.entity.AgencyEntity;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Audrey
 */
@FacesConverter(value = "agencyConverter")
@ManagedBean
public class AgencyConverter implements Converter{

    @EJB
    private AgencyDao agencyDao;
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        System.out.println("get as object");
        System.out.println(value);
        AgencyEntity e = (AgencyEntity) agencyDao.findAgencyByName(value);
        System.out.println(e==null ? "not find" : "plop");
        System.out.println(e);
        System.out.println("end");
        return (AgencyEntity) agencyDao.findAgencyByName(value);
        
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if(value != null && value instanceof AgencyEntity) {
            return ((AgencyEntity)value).getName();
        } else {
            return "";
        }
    }
    
}
