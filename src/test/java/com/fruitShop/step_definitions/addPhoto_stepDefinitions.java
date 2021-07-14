package com.fruitShop.step_definitions;

import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;
import org.junit.Assert;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class addPhoto_stepDefinitions {
   public String photo_url;
    @When("user is able to add a photo")
    public void user_is_able_to_add_a_photo() {

        ValidatableResponse response = given()
                .log().all()

                .pathParam("id", 202)
                .multiPart(new File("/Users/minibae/Desktop/Screen Shot 2021-04-02 at 1.42.58 PM.png"))

                .when()
                .put("/products/{id}/photo")
                .then()
                .statusCode(201);
       photo_url = response.extract().jsonPath().getString("photo_url");
        Assert.assertTrue(photo_url.contains("202"));



    }



    @When("user is display the photo")
    public void user_is_display_the_photo() {
given()
        .accept(ContentType.JSON)
        .pathParam("id",202)
        .when()
        .get("/products/{id}/photo")
        .then()
        .statusCode(200)
        .header("accept-ranges",is("bytes"))
        .header("Strict-Transport-Security",is("max-age=17280000; includeSubDomains"))
;


    }



    @Then("verify the photo")
    public void verify_the_photo() {

    }
}
