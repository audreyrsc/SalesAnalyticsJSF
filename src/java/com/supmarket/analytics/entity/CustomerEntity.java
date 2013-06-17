/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Audrey
 */

@Entity
@Table(name = "customer")
@XmlRootElement(name = "customer")
public class CustomerEntity implements Serializable {
    
    @Id
    private Integer custId;
    private Long countryId;
    private String custCity;
    private Integer custCityId;
    private Integer custCreditLimit;
    @Temporal(TemporalType.DATE)
    private Date custEffFrom;
    @Temporal(TemporalType.DATE)
    private Date custEffTo;
    private String custEmail;
    private String custFirstName;
    private String custGender;
    private String custIncomeLevel;
    private String custLastName;
    private String custMainPhoneNumber;
    private String custMaritalStatus;
    private String custPostalCode;
    private Integer custSrcId;
    private String custStateProvince;
    private Integer custStateProvinceId;
    private String custStreetAddress;
    private String custTotal;
    private Integer custTotalId;
    private String custValid;
    private Integer custYearOfBirth;

    public Integer getCustId() {
        return custId;
    }

    public void setCustId(Integer custId) {
        this.custId = custId;
    }

    public String getCustEmail() {
        return custEmail;
    }

    public void setCustEmail(String custEmail) {
        this.custEmail = custEmail;
    }

    public String getCustFirstName() {
        return custFirstName;
    }

    public void setCustFirstName(String custFirstName) {
        this.custFirstName = custFirstName;
    }

    public String getCustLastName() {
        return custLastName;
    }

    public void setCustLastName(String custLastName) {
        this.custLastName = custLastName;
    }

    public String getCustMaritalStatus() {
        return custMaritalStatus;
    }

    public void setCustMaritalStatus(String custMaritalStatus) {
        this.custMaritalStatus = custMaritalStatus;
    }

    public String getCustGender() {
        return custGender;
    }

    public void setCustGender(String custGender) {
        this.custGender = custGender;
    }

    public String getCustIncomeLevel() {
        return custIncomeLevel;
    }

    public void setCustIncomeLevel(String custIncomeLevel) {
        this.custIncomeLevel = custIncomeLevel;
    }

    public Long getCountryId() {
        return countryId;
    }

    public void setCountryId(Long countryId) {
        this.countryId = countryId;
    }

    public String getCustCity() {
        return custCity;
    }

    public void setCustCity(String custCity) {
        this.custCity = custCity;
    }

    public Integer getCustCityId() {
        return custCityId;
    }

    public void setCustCityId(Integer custCityId) {
        this.custCityId = custCityId;
    }

    public Integer getCustCreditLimit() {
        return custCreditLimit;
    }

    public void setCustCreditLimit(Integer custCreditLimit) {
        this.custCreditLimit = custCreditLimit;
    }

    public Date getCustEffFrom() {
        return custEffFrom;
    }

    public void setCustEffFrom(Date custEffFrom) {
        this.custEffFrom = custEffFrom;
    }

    public Date getCustEffTo() {
        return custEffTo;
    }

    public void setCustEffTo(Date custEffTo) {
        this.custEffTo = custEffTo;
    }

    public String getCustMainPhoneNumber() {
        return custMainPhoneNumber;
    }

    public void setCustMainPhoneNumber(String custMainPhoneNumber) {
        this.custMainPhoneNumber = custMainPhoneNumber;
    }

    public String getCustPostalCode() {
        return custPostalCode;
    }

    public void setCustPostalCode(String custPostalCode) {
        this.custPostalCode = custPostalCode;
    }

    public Integer getCustSrcId() {
        return custSrcId;
    }

    public void setCustSrcId(Integer custSrcId) {
        this.custSrcId = custSrcId;
    }

    public String getCustStateProvince() {
        return custStateProvince;
    }

    public void setCustStateProvince(String custStateProvince) {
        this.custStateProvince = custStateProvince;
    }

    public Integer getCustStateProvinceId() {
        return custStateProvinceId;
    }

    public void setCustStateProvinceId(Integer custStateProvinceId) {
        this.custStateProvinceId = custStateProvinceId;
    }

    public String getCustStreetAddress() {
        return custStreetAddress;
    }

    public void setCustStreetAddress(String custStreetAddress) {
        this.custStreetAddress = custStreetAddress;
    }

    public String getCustTotal() {
        return custTotal;
    }

    public void setCustTotal(String custTotal) {
        this.custTotal = custTotal;
    }

    public Integer getCustTotalId() {
        return custTotalId;
    }

    public void setCustTotalId(Integer custTotalId) {
        this.custTotalId = custTotalId;
    }

    public String getCustValid() {
        return custValid;
    }

    public void setCustValid(String custValid) {
        this.custValid = custValid;
    }

    public Integer getCustYearOfBirth() {
        return custYearOfBirth;
    }

    public void setCustYearOfBirth(Integer custYearOfBirth) {
        this.custYearOfBirth = custYearOfBirth;
    }
    
}
