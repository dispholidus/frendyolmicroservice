package com.kemalo.OrderService.models.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {
    @JsonProperty("productId")
    @JsonIgnore
    private String productId;
    @JsonProperty("productName")
    private String productName;
    @JsonProperty("productPrice")
    private double productPrice;
    @JsonProperty("Count")
    private int count;
}
