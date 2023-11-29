package com.kemalo.ProductService.models.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CheckoutDTO {
    private String productId;
    private int productBought;

}
