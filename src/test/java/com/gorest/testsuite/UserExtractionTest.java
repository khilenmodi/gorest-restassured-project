package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;
public class UserExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt(){
        RestAssured.baseURI ="https://gorest.co.in/public/v2";
        response = given()
                .queryParams("page",1)
                .queryParams("per_page", 20)
                .get("/users")
                .then().statusCode(200);

    }

    //1. Extract the All Ids
    @Test
    public void test001(){
        List<Integer> ID = response.extract().path("id");
        System.out.println(ID);
    }
    //2. Extract the all Names
    @Test
    public void test002(){
        List<String> name = response.extract().path("name");
        System.out.println(name);
    }
    //3. Extract the name of 5th object
    @Test
    public void test003(){
        String nameOfObject = response.extract().path("[4].name");
        System.out.println(nameOfObject);
    }
    //4. Extract the names of all object whose status = inactive
    @Test
    public void test004(){
    List<HashMap<String,?>> status = response.extract().path("findAll{it.status = 'inactive'}.name");
    System.out.println(status);
    }
    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005(){
        List<HashMap<String,?>> gender = response.extract().path("findAll{it.status = 'active'}.gender");
        System.out.println(gender);
    }
    //6. Print the names of the object whose gender = female
    @Test
    public void test006(){
        List<HashMap<String,?>> names = response.extract().path("findAll{it.gender = 'female'}.name");
        System.out.println(names);
    }
    //7. Get all the emails of the object where status = inactive
    @Test
    public void test007(){
        List<HashMap<String,?>> emails = response.extract().path("findAll{it.status = 'inactive'}.email");
        System.out.println(emails);
    }
    //8. Get the ids of the object where gender = male
    @Test
    public void test008(){
        List<HashMap<String,?>> ids = response.extract().path("findAll{it.gender = 'male'}.id");
        System.out.println(ids);
    }
    //9. Get all the status
    @Test
    public void test009(){
        List<String> status = response.extract().path("status");
        System.out.println(status);
    }
    //10. Get email of the object where name = Tarun Rana
    @Test
    public void test010(){
      String email = response.extract().path("find{it.name = 'Tarun Rana'}.email");
        System.out.println(email);
    }
    //11. Get gender of id =4040715

    @Test
    public void test011(){
        String gender = response.extract().path("find{it.id = '4040715'}.gender");
        System.out.println(gender);
    }
}
