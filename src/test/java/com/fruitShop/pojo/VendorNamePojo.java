package com.fruitShop.pojo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@Getter@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@JsonIgnoreProperties(ignoreUnknown = true)
public class VendorNamePojo {

    private  String name;
  //  private String vendor_url;

   /*
    {
  "name": "Mini's SundaySchool shop",
  "vendor_url": "/shop/vendors/815"
}
    */


}
