package com.example;

import io.quarkus.test.TestTransaction;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import jakarta.inject.Inject;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.example.model.EmpDetails;
import com.example.repository.EmpRepo;
// import com.google.inject.Inject;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestTransaction
public class EmpResourceTest {
    @Inject
    EmpRepo empRepo;

    // @Test
    // @Order(1)
    // public void getAllEmpDetails() {
    //     java.util.List<EmpDetails> employees = given().when().get("/EmpDetails/list-emp-details").then().statusCode(200)
    //             .extract().body().jsonPath().getList(".", EmpDetails.class);
    //     assertEquals(employees.size(), 4);
    // }

    @Test
    @Order(2)
    public void getById() {
        EmpDetails empId = given().pathParam("id", 751L).when().get("/EmpDetails/getbyid/{id}").then().statusCode(200)
                .extract().body().as(EmpDetails.class);
        assertNotNull(empId);
        assertEquals(751L, empId.id);
    }

    @Test
    @Order(3)
    public void getByName() {
        String name = "sharanyasekar";
        EmpDetails empName = given().pathParam("name", name).when().get("/EmpDetails/getbyname/{name}").then()
                .statusCode(200).extract().body().as(EmpDetails.class);
        assertNotNull(empName);
        assertEquals(name, empName.getName());
    }

    @Test
    @Order(4)
    public void getEmpDetailsByIdandName() {
        Long id = 751L;
        String name = "sharanyasekar";
        EmpDetails empIdandName =
        given().pathParam("id", id).pathParam("name", name)
                .contentType(ContentType.JSON)
                .get("/EmpDetails/getidandname/{id}/{name}").then().statusCode(200).extract().body()
                .as(EmpDetails.class);
        assertNotNull(empIdandName);
        assertEquals(id,empIdandName.id);
        assertEquals(name,empIdandName.getName());
    }

    @Test
    @Order(5)
    public void testupdateDetails() {

        EmpDetails updatedDetails = new EmpDetails();
        updatedDetails.setName("raji");
        updatedDetails.setMobilenumber(7723779235L);
        updatedDetails.setAge("57");
        given().pathParam("id", 4402L).body(updatedDetails).contentType(ContentType.JSON)
                .when().put("/EmpDetails/update/{id}").then()
                .statusCode(200).extract().body().as(EmpDetails.class);
        // assertNotNull(updatedDetails);
        // assertEquals(id,updatedDetails.id);
    }

//     @Test
//     @Order(6)
//     public void deleteEmpDetailsById() {
//         Long id = 4401L;
//         // RestAssured.defaultParser = Parser.JSON;
//         given().pathParam("id",id).contentType(ContentType.JSON).when().delete("/EmpDetails/{id}").then().statusCode(204);
// }
      

    // @Test
    // public void addDetails(){
    // EmpDetails emp = new EmpDetails();
    // emp.setAge("22");
    // emp.setMobilenumber(1L);
    // emp.setName("sharanyasekar");
    // EmpDetails empDet = given().body(emp).contentType(ContentType.JSON)
    // .when().post("/EmpDetails/create").then()
    // .statusCode(200).extract().body().as(EmpDetails.class);
    // assertNotNull(empDet);
    // assertNotNull(empDet.id);
    // }

    @Test
    public void addDetailsRepository() {
        EmpDetails emp = new EmpDetails();
        emp.setAge("22");
        emp.setMobilenumber(1L);
        emp.setName("sharanyasekar");
        empRepo.persist(emp);
        assertNotNull(emp);
        assertNotNull(emp.id);
    }
}
