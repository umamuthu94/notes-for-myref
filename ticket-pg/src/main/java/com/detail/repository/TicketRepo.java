package com.detail.repository;

import com.detail.model.TicketBook;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class TicketRepo implements PanacheRepository<TicketBook> {
    public TicketBook findByMobileNumber(Long mobileno) {
        return find("mobileno", mobileno).firstResult();
    }    
}
