package com.detail.service;

import java.util.List;

import com.detail.model.TicketBook;
import com.detail.repository.TicketRepo;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class TicketService {
    
    @Inject
    TicketRepo ticketRepo;

     @Transactional
    public TicketBook createTicketBook(TicketBook ticketBook) {
        int totalAmount = ticketBook.getTicketcount() * 200;
        ticketBook.setTotamount(totalAmount);
        TicketBook.persist(ticketBook);
        return ticketBook;
    }

    @Transactional
    public List<TicketBook> getallTicketBooks(){
        return ticketRepo.listAll();
    }

    @Transactional
    public TicketBook getTicketDetailsByMobileNumber(Long mobileno) {
        return ticketRepo.findByMobileNumber(mobileno);
    }

  
    @Transactional
    public TicketBook updateTicketBook(Long mobileno,TicketBook ticketBook) {
        TicketBook book = ticketRepo.findByMobileNumber(mobileno);
        book.setTicketcount(ticketBook.getTicketcount());
        book.setTotamount(ticketBook.getTicketcount()*200);
        return book;
    }


    @Transactional
    public void deleteTicketByMobileNumber(Long mobileno) {
        TicketBook ticketBook = ticketRepo.findByMobileNumber(mobileno);
        if (ticketBook != null) {
            ticketRepo.delete(ticketBook);
        }
    }

}
