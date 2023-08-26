package com.detail;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.detail.model.TicketBook;
import com.detail.repository.TicketRepo;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestTransaction
public class TicketResourceTest {

    @Inject
    TicketRepo ticketRepo;
    

    // @Test
    // @Order(1)
    // public void testgetAllTicketBooks(){
    //     java.util.List<TicketBook> tickets = given().when().get("/book/alltickets").then().statusCode(200).extract()
    //                 .body().jsonPath().getList(".",TicketBook.class);
    //                 assertEquals(tickets.size(),1);
    // }

    @Test
    @Order(2)
    public void testgetTicketDetailsByMobileNumber(){
        Long mobileno = 45678L;
        TicketBook ticketbymobileno = given().pathParam("mobileno", 45678L).when().get("/book/getbymobno/{mobileno}").then().statusCode(200).extract()
                    .body().as(TicketBook.class);
                    assertNotNull(ticketbymobileno);
                    assertEquals(45678L,ticketbymobileno.getMobileno());        
    }


    @Test
    @Order(3)
    public void testcreateTicketBook() {
        TicketBook book = new TicketBook();
        book.setMobileno(875486L);
        book.setTicketno(12L);
        book.setTicketcount(20);
        book.setTicketprice(200);
        TicketBook tBook = given().body(book).contentType(ContentType.JSON).when().post("/book/create").then().statusCode(201).extract()
                    .body().as(TicketBook.class);
                    assertNotNull(tBook);
                    assertNotNull(tBook.id);
    }

    @Test
    @Order(4)
    public void testcreateTicketRepository() {
        TicketBook book = new TicketBook();
        book.setMobileno(875486L);
        book.setTicketno(12L);
        book.setTicketcount(20);
        book.setTicketprice(200);
        ticketRepo.persist(book);
        assertNotNull(book);
        assertNotNull(book.id);
     }

     @Test
     @Order(5)
     public void testupdateTicketBook() {
        Long mobileno = 77839373L;
        TicketBook updateTicketBook = new TicketBook();
        updateTicketBook.setTicketcount(1);
        given().pathParam("mobileno",mobileno).body(updateTicketBook).contentType(ContentType.JSON).when().put("/book/update/{mobileno}")
        .then().statusCode(200).extract().body().as(TicketBook.class);;
     }

     
        // @Test
        // @Order(6)
        // public void testdeleteTicketByMobileNumber() {
        //     Long mobileno = 7887455L;
        //     given().pathParam("mobileno",mobileno).when().delete("/book/{mobileno}").then()
        //     .statusCode(204);
        // }


     
}
