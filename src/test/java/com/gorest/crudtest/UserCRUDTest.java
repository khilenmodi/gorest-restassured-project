package com.gorest.crudtest;

import com.gorest.model.PostsPojo;
import com.gorest.model.UserPojo;
import com.gorest.testbase.TestBase;
import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class UserCRUDTest extends TestBase {

//4134891


    @Test
            public void createUser(){

        String email = getRandomValue() + "kamleshpatel123@gamil.com";
        PostsPojo postsPojo = new PostsPojo();
        postsPojo.setName("kamlesh");
        postsPojo.setEmail(email);
        postsPojo.setGender("male");
        postsPojo.setStatus("active");

        Response response = given()
                .header("Authorization","Bearer 729b4b499368ff27fc26cd366161b6db88400b49b82f58751588a296c6bfd498")
                .header("Content-Type","application/json")
                .when()
                .body(postsPojo)
                .post("/users");
        response.prettyPrint();
        response.then().statusCode(201);


    }

    @Test
    public void updateUser(){
        String email = getRandomValue() + "kamleshpatel123@gamil.com";

        UserPojo userPojo = new UserPojo();
        userPojo.setName("kamal");
        userPojo.setEmail(email);
        userPojo.setStatus("inactive");

        Response response = given()
                .header("Authorization","Bearer 729b4b499368ff27fc26cd366161b6db88400b49b82f58751588a296c6bfd498")
                .header("Content-Type","application/json")
                .when()
                .body(userPojo)
                .put("/users/4135111");
        response.prettyPrint();
        response.then().statusCode(200);


    }

    @Test
    public void VerifyUserDeleteSuccessfully() {

       Response response =  given()
               .header("Authorization","Bearer 729b4b499368ff27fc26cd366161b6db88400b49b82f58751588a296c6bfd498")
                .headers("Content-Type", "application/json")
               .when()
                .delete("/users/4135111");
        response.prettyPrint();
        response.then().statusCode(204);
    }
}