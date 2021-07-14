package com.fruitShop.pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.javafaker.Faker;
import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductPojo {

    /*
    {
  "name": "Wildberries",
  "price": 4.99,
  "category_url": "/shop/categories/Fruits",
  "vendor_url": "/shop/vendors/672"
}
     */
    private static Faker faker = new Faker();
    private String name;
    private double price;
    private String category_url;
    private String vendor_url;


    public static Map<String,Object> getRandomUpdatedBody() {
        Map<String,Object> bodyMap=new LinkedHashMap<>();
        bodyMap.put("name",faker.food().fruit());
        bodyMap.put("price",faker.number().randomDouble(2,1,100000));

        return bodyMap ;


    }

}
