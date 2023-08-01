package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostsExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "https://gorest.co.in/public/v2";
        response = given()
                .queryParams("page", 1)
                .queryParams("per_page", 25)
                .get("/posts")
                .then().statusCode(200);
    }

    //1. Extract the title
    @Test
    public void test001() {
        List<String> title = response.extract().path("title");
        System.out.println("All the title are :" + title);
    }
    //2. Extract the total number of record
    @Test
    public void test002() {
        List<Integer> totalNumber = response.extract().path("id");
        System.out.println("Total number of records are :" + totalNumber.size());
    }
    //3. Extract the body of 15th record
    @Test
    public void test003(){
       String body = response.extract().path("[14].body");
        System.out.println(body);
    }
    //4. Extract the user_id of all the records
    @Test
    public void test004(){
        List<Integer> userId = response.extract().path("user_id");
        System.out.println(userId);
    }
    //5. Extract the title of all the records
    @Test
    public void test005(){
        List<String> title = response.extract().path("title");
        System.out.println(title);
    }
    //6. Extract the title of all records whose user_id = 4040679
    @Test
            public void test006() {
        List<HashMap<String, ?>> Title = response.extract().path("findAll{it.user_id = '4040679'}.title");
        System.out.println(Title);
    }
    //7. Extract the body of all records whose id = 2671
    @Test
    public void test007(){
        List<HashMap<String, ?>> body = response.extract().path("findAll{it.user_id = '4040679'}.body");
        System.out.println(body);
    }


}

