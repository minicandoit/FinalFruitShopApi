package com.fruitShop.step_definitions;

import com.fruitShop.pojo.ProductPojo;
import com.fruitShop.pojo.VendorNamePojo;
import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import static io.restassured.http.ContentType.*;

import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.Assert;

import java.lang.reflect.Array;
import java.lang.reflect.MalformedParameterizedTypeException;
import java.util.*;


import static org.hamcrest.MatcherAssert.*;
import static org.hamcrest.Matchers.*;

import static io.restassured.RestAssured.*;

public class addProduct_stepDefinitions {
    Faker faker=new Faker();

//VendorNamePojo vendorNamePojo=new VendorNamePojo("Mini's Magic shop");
VendorNamePojo vendorNamePojo=new VendorNamePojo();
ProductPojo  productPojo=new ProductPojo();
Map<String,Object>bodyMap=new LinkedHashMap<>();
    Map<String,Object>addProduct=new LinkedHashMap<>();
   public String vendorId;
   public String productId;



    @When("create a vendor for the product")
    public void create_a_vendor_for_the_product() {
        bodyMap.put("name","summer Magic school");

        JsonPath jsonPath = given().
                log().all()
                .contentType(JSON)
                //   .pathParam("id", vendorId)
                .body(bodyMap)
                .when()
                .post("vendors/")
                .then()
                .statusCode(201)
                .extract()
                .jsonPath().prettyPeek()
                //.getString("name")
                ;
        VendorNamePojo vendorNamePojo = jsonPath.getObject("", VendorNamePojo.class);
        System.out.println("vendorNamePojo = " + vendorNamePojo);
//
//        Response response = given().
//                log().all()
//                .contentType(JSON)
//                //   .pathParam("id", vendorId)
//                .body(bodyMap)
//                .when()
//                .post("vendors/");
//        JsonPath jsonPath = response.jsonPath();
//       Assert.assertTrue(jsonPath.getString("vendor_url").contains("/shop/vendors/"));
////                .then()
////                .statusCode(201)
////                .extract()
////                .jsonPath().prettyPeek()
////                .getString("vendor_url")
////                ;


     //   System.out.println("string = " + string);
      String name = jsonPath.getString("name");
        System.out.println("name = " + name);

        String string1=jsonPath.getString("vendor_url");
  String[] arr=string1.split("");


  int i=0;
 vendorId="";
        for (String each : arr) {
            System.out.println("each = " + each);
            if(Character.isDigit(each.charAt(i))){
                vendorId+=each;
            }
        }
        System.out.println("vendorId = " + vendorId);


    }

    @When("user add a new product")
    public void user_add_a_new_product() {
addProduct.put("name","fruit of the holy spirit");
        addProduct.put("price",12.99);
        addProduct.put("category_url","/shop/categories/Fruits");
        addProduct.put("vendor_url","/shop/vendors/"+vendorId);
//        given().
//                log().all()
//                .contentType(JSON).
//                body(addProduct).
//                when()
//                .post("products/").
//                then()
//                .statusCode(201)
//                .extract()
//                .jsonPath().prettyPeek().getString("vendor_url").contains(vendorId)
//                 ;

        Response response = given().
                log().all()
                .contentType(JSON).
                        body(addProduct).
                        when()
                .post("products/");
        Assert.assertEquals(response.statusCode(),201);

        String productUrl=response.jsonPath().getString("product_url");
        List<String> productIdList=new ArrayList<>(Arrays.asList(productUrl.split("")));
        int i=0;
        productId="";
        for (String each : productIdList) {
            if(Character.isDigit(each.charAt(i))){
                productId+=each;
            }
        }

        Assert.assertTrue(response.jsonPath().getString("product_url").contains(productId));
        System.out.println("productId = " + productId);

    }



    @Then("user is able to verify the new")
    public void user_is_able_to_verify_the_new() {

        String Name = given().
                log().all().
                accept(JSON).
                pathParam("id", productId).
                when()
                .get("/products/{id}").then()
                .statusCode(200).extract()
                .jsonPath().getString("name");
        System.out.println("Name = " + Name);
        Double price= given().
                log().all().
                accept(JSON).
                pathParam("id", productId).
                when()
                .get("/products/{id}").then()
                .statusCode(200).extract()
                .jsonPath().getDouble("price");
        System.out.println("price = " + price);
    }



//    public static RequestSpecification RequestSpec=given().
//            log().all()
//            .contentType(ContentType.JSON);
//    public static ResponseSpecification ResponseSpec=expect().logDetail(LogDetail.ALL)
//            .statusCode(204)
//            .contentType(ContentType.JSON);

    public  Map<String,Object> getRandomUpdatedBody() {
        Map<String,Object> bodyMap=new LinkedHashMap<>();
        bodyMap.put("name",faker.food().fruit());
        bodyMap.put("price",faker.number().randomDouble(2,1,100000));
        bodyMap.put("category_url","/shop/categories/Fruits");
        bodyMap.put("vendor_url","/shop/vendors/"+848);

        return bodyMap ;


    }


    @When("user is update a  name and a price")
    public void user_is_update_a_name_and_a_price() {
    String updateBody="{\n" +
            "  \"name\": \"Fruit of the Spirit for AFCW\",\n" +
            "  \"price\": 1000.99,\n" +
            "  \"category_url\": \"/shop/categories/Fruits\",\n" +
            "  \"vendor_url\": \"/shop/vendors/672\"\n" +
            "}";
ProductPojo bodyPojo=new ProductPojo("mini Fruit",100.99,"/shop/categories/Fruits","/shop/vendors/819");

   //   Map<String,Object>updatedBody=ProductPojo.getRandomUpdatedBody();


//        given()
//                .spec(RequestSpec)
//                .body(updatedBody).log().all()
//                .pathParam("id",productId)
//        .when()
//                .put("/products/{id}").
//        then()
//                .spec(ResponseSpec);



        String updatedName = given()
                .log().all()
                .contentType(JSON).
                        body(bodyPojo).
                        pathParam("id", 485).
                        when()
                .put("/products/{id}").
                        then().statusCode(200).extract()
                .jsonPath().getString("name");
        System.out.println("updatedName = " + updatedName);
     //   System.out.println("productId = " + productId);//null
      //  System.out.println("vendorId = " + vendorId);








    }


    @Then("user is able to verify the updated name and a price")
    public void user_is_able_to_verify_the_updated_name_and_a_price() {

        JsonPath js = given().
                log().all().
                accept(JSON).
                pathParam("id", 485).
                when().
                get("/products/{id}").
                then().extract().jsonPath();

      String updatedName= js.getString("name");
      Double updatedPrice=js.getDouble("price");

        System.out.println("updatedPrice = " + updatedPrice);
        System.out.println("updatedName = " + updatedName);



        String string1=js.getString("vendor_url");
        String[] arr=string1.split("");


        int i=0;
        vendorId="";
        for (String each : arr) {
            System.out.println("each = " + each);
            if(Character.isDigit(each.charAt(i))){
                vendorId+=each;
            }
        }
        System.out.println("vendorId = " + vendorId);


    }



    @Then("list the product")
    public void list_the_product() {

        int productCount = given()
                .log().all()
                .accept(JSON)
                .when()
                .get("/products/")
                .then().statusCode(200)
                .extract().jsonPath().getInt("meta.count");

       List<Object> allList=new ArrayList<>();
        for (int i = 1; i <= productCount/10; i++) {


            List<Object> productList = given()
                    .log().all()
                    .accept(JSON)
                    .queryParam("page", i)
                    .queryParam("limit", 10)
                    .when()
                    .get("/products/")
                    .then()
                    .statusCode(200)
                    .extract()
                    .jsonPath().getList("products");
           allList.addAll(productList);
        }

        System.out.println("allList = " + allList);
    }

}
