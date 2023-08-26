package com.example.controller;

import java.util.List;

import org.eclipse.microprofile.openapi.annotations.parameters.RequestBody;

import com.example.model.EmpDetails;
import com.example.service.EmpService;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/EmpDetails")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmpResource {

   
    @Inject
    EmpService empService;

    @POST
    @Path("/create")
    @Transactional
   public EmpDetails addDetails(@RequestBody EmpDetails empDetails) {
    EmpDetails.persist(empDetails);
    return empDetails;
   }
    
   @GET
   @Path("/list-emp-details")
   public List<EmpDetails> getAllEmpDetails() {
    return empService.getallEmpDetails();
   }

   @PUT
   @Path("/update/{id}")
   @Transactional
   public EmpDetails updateDetails(@PathParam("id") Long id, @RequestBody EmpDetails empDetails) {
    
    return empService.updateEmpDetails(id, empDetails);

   }

   @DELETE
   @Path("/{id}")
   @Transactional
   public void deleteEmpDetails(@PathParam("id") Long id) {
    empService.deletEmpDetails(id);
   }

   @DELETE
   @Path("/delete/{name}")
   @Transactional
   public void deleteEmployeeByName(@PathParam("name") String name) {
       empService.deleteEmpByName(name);
   }

   @DELETE
   @Path("/deleteidandname/{id}/{name}")
   @Transactional
   public void deleteEmpByIdandName(@PathParam("id") Long id,@PathParam("name") String name) {
    empService.deleteByIdandName(id, name);
   }

   @GET
   @Path("/getbyname/{name}")
   @Transactional
   public EmpDetails getByName(@PathParam("name") String name) {
       return empService.getByName(name);
   }

   @GET
   @Path("/getidandname/{id}/{name}")
   @Transactional
   public EmpDetails getEmpDetailsByIdandName(@PathParam("id") Long id, @PathParam("name") String name){
    return empService.getEmpDetailsByIdandName(id, name);
   }

   @GET
   @Path("/getbyid/{id}")
   @Transactional
   public EmpDetails getById(@PathParam("id") Long id) {
    return empService.getById(id);
   }
}
