package com.practice.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.practice.model.UserInfo;
import com.practice.service.UserService;

import io.vertx.mutiny.ext.auth.User;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.Response;

@Path("/user")
public class UserResource {

    @Inject
    UserService userInfoService;

    @Inject
    EntityManager eManager;

    @POST
    @Path("/add")
    @Transactional
    public UserInfo addUser(@RequestBody UserInfo userInfo) {

        // Make sure the entity is managed or reattach it if detached
        // UserInfo managedUserInfo = eManager.merge(userInfo);
        UserInfo.persist(userInfo);
        // Now the managedUserInfo is part of the current persistence context
        return userInfo;
    }

    @GET
    public List<UserInfo> getAllUsers() {
        return userInfoService.getallUsers();
    }

    @PUT
    @Path("/update")
    @Transactional
     public Response updateUser(@QueryParam("id") Long id, UserInfo updatedUserInfo) {

        System.out.println("-------update id------------- " + id);
        System.out.println("-------update info------------- " + updatedUserInfo.toString());
        UserInfo userInfo = userInfoService.getUserById(id);
        System.out.println("------user info details--------- " + userInfo);
        if(userInfo == null){
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        userInfo.setName(updatedUserInfo.getName());
        userInfo.setAge(updatedUserInfo.getAge());
        return Response.status(Response.Status.NO_CONTENT).build();
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public void deleteUser(@PathParam("id") Long id) {
        userInfoService.deleteUser(id);
    }
}


