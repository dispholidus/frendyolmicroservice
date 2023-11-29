package com.kemalo.ProductService.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @JsonProperty("productId")
    private String productId;

    private String productName;
    private String productDesc;
    private int productAvailable;
    private double productPrice;
}
