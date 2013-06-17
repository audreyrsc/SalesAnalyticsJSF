/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.supmarket.analytics.model;

import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Audrey
 */
@XmlRootElement(name="sales_model")
public class SalesModel {
    
    private String label;
    private Double count;

    
    public SalesModel(String label, Double count) {
        this.label = label;
        this.count = count;
    }

    
    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public Double getCount() {
        return count;
    }

    public void setCount(Double count) {
        this.count = count;
    }
    
}
