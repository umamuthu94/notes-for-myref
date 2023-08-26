package com.detail.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "recerving")
public class TicketBook extends PanacheEntity{
    private Long ticketno;  //private String name;
    private Long mobileno;
    private Integer ticketprice;
    private Integer ticketcount;
    private Integer totamount; 
}
