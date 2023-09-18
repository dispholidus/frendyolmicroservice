package com.kemalo.OrderService.models.concrete;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @JsonProperty("productName")
    private String productName;
    @JsonProperty("productDesc")
    private String productDesc;
    @JsonProperty("productAvailable")
    private int productAvailable;
    @JsonProperty("productPrice")
    private double productPrice;
}
