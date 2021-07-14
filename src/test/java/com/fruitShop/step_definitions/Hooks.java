package com.fruitShop.step_definitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

public class Hooks {


    @Before
    public static void fruitShopInitializer() {
        // https://api.predic8.de:443
        RestAssured.baseURI = "https://api.predic8.de";
        RestAssured.basePath = "/shop/";



    }

    @After
    public static void cleanUpFruitShop() {
       // reset();
    }

}
