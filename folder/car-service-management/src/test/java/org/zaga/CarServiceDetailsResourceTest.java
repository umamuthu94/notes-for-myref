package org.zaga;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.zaga.Entity.CarEnum;
import org.zaga.Entity.CarServiceDetails;
import org.zaga.Repository.CarServiceDetailsRepo;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.inject.Inject;

import static io.restassured.RestAssured.given;

@QuarkusTest
@TestTransaction
public class CarServiceDetailsResourceTest {

    @Inject
    CarServiceDetailsRepo carServiceDetailsRepo;

    // @Test
    // @Order(1)
    // public void testcreatesCarDetails(){
    //     CarServiceDetails details = new CarServiceDetails();
    //     details.setCarNumber("TN67");
    //     details.setCustomerName("uma");
    //     CarServiceDetails cDetails = given().body(details).contentType(ContentType.JSON).when().post("/v1/car-management/create-car-details")
    //     .then().statusCode(200).extract().body().as(CarServiceDetails.class);
    //     assertNotNull(cDetails);
    //     assertNotNull(cDetails.id);
    // }

    // @Test
    // @Order(2)
    // public void testcreatesCarDetailsRepository() {
    //     CarServiceDetails details =new CarServiceDetails();
    //     details.getCarNumber();
    //     details.getCustomerName();
    //     carServiceDetailsRepo.persist(details);
    //     assertNotNull(details);
    //     assertNotNull(details.id);
    // }

    @Test
    @Order(3)
    public void testviewCarDetails() {
        CarServiceDetails carServiceDetails = given().queryParam("carNumber","TN69").pathParam("customerName","mareesh").when().get("/v1/car-management/view-car-details/{customerName}").then().statusCode(200).extract()
            .body().as(CarServiceDetails.class);
            //assertNotNull(carServiceDetails);
        }

     @Test
     @Order(4)
     public void testupdateCarDetails() {
        String carNumber = "TN70";
        CarServiceDetails updateCarServiceDetails = new CarServiceDetails();
        updateCarServiceDetails.setServiceStatus(CarEnum.DONE);
        given().queryParam("carNumber","TN70").queryParam("custName","arun").body(updateCarServiceDetails).contentType(ContentType.JSON).when().put("/v1/car-management/update-car-details")
        .then().statusCode(200).extract().body().as(CarServiceDetails.class);
     }

        //  @Test
        //  @Order(5)
        //  public void testdeleteCarDetails() {
        //     String carNumber = "TN69";
        //     String customerName = "mareesh";
        //     given().queryParam("carNumber","TN69").when().delete("/v1/car-management/delete-car-details").then()
        //     .statusCode(200);
        //  }

        

}
