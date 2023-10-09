package com.kemalo.ProductService.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("products")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    private String productId;

    @JsonProperty("productName")
    private String productName;
    @JsonProperty("productDesc")
    private String productDesc;
    @JsonProperty("productAvailable")
    private int productAvailable;
    @JsonProperty("productPrice")
    private double productPrice;
}
