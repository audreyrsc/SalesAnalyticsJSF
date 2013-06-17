/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.supmarket.analytics.rest;

import com.supmarket.analytics.entity.AgencyUserEntity;
import com.supmarket.analytics.service.UserService;
import javax.ejb.EJB;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Audrey
 */
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserResources {
    
    @EJB
    private UserService service;
    
    @GET
    public Response test() {
        return Response.ok().build();
    }
    
    @POST @Path("login")
    public AgencyUserEntity checkLogin(AgencyUserEntity user) {
        System.out.println(user.getEmail());
        
        user = service.login(user.getEmail(), user.getHashedPassword());
        
        System.out.println(user!=null ? user.getEmail() : "null");
        return user;
    }
}
