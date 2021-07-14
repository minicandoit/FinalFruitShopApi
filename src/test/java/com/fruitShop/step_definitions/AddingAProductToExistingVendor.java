package com.fruitShop.step_definitions;

import com.fruitShop.pojo.ProductPojo;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

public class AddingAProductToExistingVendor {

    String vendorName;
    String productUrl;
    int productId;
    int fruitVendorId = 501;

    @Given("user has chosen a vendor")
    public void user_has_chosen_a_vendor() {

       vendorName =  given()
                .log().all()
                .contentType(ContentType.JSON)
                .pathParam("id",fruitVendorId)
                .when()
                .get("vendors/{id}")
                .jsonPath()
                .getString("name")
                ;

    }

    @When("user adds a product to chosen existing vendor")
    public void user_adds_a_product_to_chosen_existing_vendor() {

        ProductPojo newProduct1 = new ProductPojo("Passion Fruit",7.99,
                "/shop/categories/Fruits","/shop/vendors/" + fruitVendorId);

        JsonPath productUrl = given()
                //.log().uri()
                .contentType(ContentType.JSON)
                .body(newProduct1)
        .when()
                .post("products/")
                .jsonPath()
                .get("product_url")
                ;
        double price = productUrl.getInt("price");
        System.out.println("price = " + price);
    }

    @Then("user should see the added product")
    public void user_should_see_the_added_product() {

        productId = Integer.parseInt(productUrl.substring(productUrl.indexOf("8")));

        given()
                //.log().all()
                .contentType(ContentType.JSON)
                .pathParam("id",productId)
                .when()
                .get("products/{id}")
                .then()
                .statusCode(is(200))
                .body("name", is("Passion Fruit"))
                .body("price", is(equalTo(7.99)))
                .body("category_url", is("/shop/categories/Fruits"))
                .body("vendor_url", is("/shop/vendors/"+productId))
                ;


    }
}
