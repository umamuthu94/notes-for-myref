package com.detail.controller;


import java.util.List;

import com.detail.model.TicketBook;
import com.detail.service.TicketService;

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
import jakarta.ws.rs.core.Response;

@Path("/book")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TicketResource {

    @Inject
    TicketService ticketService;
    
       
    @POST
    @Path("/create")
@Transactional
public Response createTicketBook(TicketBook ticketBook) {
    TicketBook newTicketBook = new TicketBook();
    newTicketBook.setTicketno(ticketBook.getTicketno());
    newTicketBook.setMobileno(ticketBook.getMobileno());
    newTicketBook.setTicketprice(ticketBook.getTicketprice());
    newTicketBook.setTicketcount(ticketBook.getTicketcount());

    TicketBook createdTicket = ticketService.createTicketBook(newTicketBook);

    return Response.status(Response.Status.CREATED)
            .entity(createdTicket)
            .build();
}

@GET
@Path("/alltickets")
@Transactional
// public List<TicketBook> getAllTicketBooks(){
//     return ticketService.getallTicketBooks();
// }
public Response getAllTicketBooks() {
    try {
        List<TicketBook> ticketBook =ticketService.getallTicketBooks();
        return Response.status(200).entity(ticketBook).build();
    } catch (Exception e) {
        return Response.status(500).entity(e.getMessage()).build();
      
    }
}


 @GET
 @Path("/getbymobno/{mobileno}")
 @Transactional
    // public TicketBook getTicketDetailsByMobileNumber(@PathParam("mobileno") Long mobileno) {
    //     return ticketService.getTicketDetailsByMobileNumber(mobileno);
    // }
    public Response getTicketDetailsByMobileNumber(@PathParam("mobileno") Long mobileno) {
        try {
            TicketBook ticketBook = ticketService.getTicketDetailsByMobileNumber(mobileno);
            return Response.status(200).entity(ticketBook).build();
            
        } catch (Exception e) {
           return Response.status(500).entity(e.getMessage()).build();
        }
    }

   
    @PUT
    @Path("/update/{mobileno}")
    @Transactional
    // public Response updateTicketBook(@PathParam("mobileno") Long mobileno,TicketBook ticketBook) {
    //     //System.out.println("--------------------->>>>>"+ticketBook);
    //     return Response.ok(ticketService.updateTicketBook(mobileno, ticketBook)).build();
    // }
    public Response updateTicketBook(@PathParam("mobileno") Long mobileno,TicketBook ticketBook) {
        try {
             TicketBook book = ticketService.updateTicketBook(mobileno, ticketBook);
        return Response.status(200).entity(book).build();            
        } catch (Exception e) {
            return Response.status(500).entity(e.getMessage()).build();
        }
       
    }

    @DELETE
    @Path("/{mobileno}")
    @Transactional
    public Response deleteTicketByMobileNumber(@PathParam("mobileno") Long mobileno) {
        ticketService.deleteTicketByMobileNumber(mobileno);
        return Response.status(Response.Status.NO_CONTENT).build();
    }

}
