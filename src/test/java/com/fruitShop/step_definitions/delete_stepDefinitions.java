package com.fruitShop.step_definitions;

import com.fruitShop.pojo.ProductPojo;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

public class delete_stepDefinitions {
   public String productId;
    @When("user create a product")
    public void user_create_a_product() {

        ProductPojo bodyMap=new ProductPojo("mini's Favorite Fruit",100.99,"/shop/categories/Fruits","/shop/vendors/844");

        ValidatableResponse response = given()
                .log().all()
                .contentType(ContentType.JSON)
                .body(bodyMap)
                .when()
                .post("/products/")
                .then().statusCode(201)
                .body("name", is("mini's Favorite Fruit"));
        String product_url = response.extract().jsonPath().getString("product_url");
        System.out.println("product_url = " + product_url);



        List<String>list=new ArrayList<>(Arrays.asList(product_url.split("")));
       int i=0;
       productId="";
        for (String each : list) {
            if(Character.isDigit(each.charAt(i))){
                productId+=each;
            }
        }
        System.out.println("productId = " + productId);
    }



    @When("user can delete the product")
    public void user_can_delete_the_product() {
given()
        .log().all()
        .pathParam("id",productId)
        .contentType(ContentType.JSON)
        .when()
        .delete("/products/{id}")
        .then()
        .statusCode(200);

    }



    @Then("verify the product is deleted")
    public void verify_the_product_is_deleted() {
given()
        .accept(ContentType.JSON)
        .pathParam("id",productId)
        .when()
        .get("/products/{id}")
        .then()
        .statusCode(404);
    }

}
