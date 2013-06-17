/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Audrey
 */
@Entity
@Table(name = "agency")
public class AgencyEntity implements Serializable {
    
    @Id
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
    
}
