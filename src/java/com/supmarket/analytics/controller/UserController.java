/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.controller;

import com.supmarket.analytics.entity.AgencyUserEntity;
import com.supmarket.analytics.service.UserService;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

/**
 *
 * @author Audrey
 */
@ManagedBean
@SessionScoped
public class UserController implements Serializable {
    
    private String email;
    private String password;
    private AgencyUserEntity currentUser;
    
    @EJB
    private UserService userService;
    
    public String displayLoginPage() {
        return "login";
    }
    
    public String checkUser() {
        
       currentUser = userService.login(email, password);
       
        if(currentUser != null) {
            System.out.println("user exists");
            return "dashboard";
        } else {
            System.out.println("user doesn't exist");
            return "login";
        }
       
    }
    
    // GETTERS & SETTERS //
    
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public AgencyUserEntity getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(AgencyUserEntity currentUser) {
        this.currentUser = currentUser;
    }

//    public String getLoginMessage() {
//        if(currentUser==null) {
//            return "Login";
//        }else {
//            return "Logout";
//        }
//    }
    
}
