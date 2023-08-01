package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.given;

public class UserAssertionTest {

     static ValidatableResponse response;

     @BeforeClass
    public static void inIt(){
         RestAssured.baseURI ="https://gorest.co.in/public/v2";
         response = given()
                 .queryParams("page",1)
                 .queryParams("per_page",20)
                 .get("/users")
                 .then().statusCode(200);

     }



    //1. Verify the if the total record is 20
    @Test
    public void test001(){
         response.body("total.size", equalTo(20));
    }
    //2. Verify the if the name of id =4040708 is equal to ”Kama Asan”
    @Test
    public void test002(){
         response.body("[6].name",equalTo("Kama Asan"));
    }

    //3. Check the single ‘Name’ in the Array list (Sanya Kaur)
    @Test
    public void test003(){
         response.body("[2].name", equalTo("Sanya Kaur"));
    }
    //4. Check the multiple ‘Names’ in the ArrayList (Ms. Vaishvi Shukla, Baladitya Khan,Sanya Kaur)
    @Test
    public void test004(){
         response.body("name",hasItems("Ms. Vaishvi Shukla","Baladitya Khan","Sanya Kaur"));
    }
    //5. Verify the email of userid = 4040712 is equal “mrs_kumar_ahluwalia@lueilwitz-gibson.test”
    @Test
    public void test005(){
         response.body("find{it.id=4040712}.email",equalTo("shukla_vaishvi_ms@sauer.example"));
    }
    //6. Verify the status is “Active” of username is “Mr. Preity Patel”
    @Test
    public void test006(){
         response.body("find{it.name = 'Mr. Preity Patel'}.status", equalTo("active"));
    }
    //7. Verify the Gender = male of username is “Mr. Preity Patel”
    @Test
    public void test007(){
         response.body("find{it.name = 'Mr. Preity Patel'}.status", equalTo("male"));
    }


}
