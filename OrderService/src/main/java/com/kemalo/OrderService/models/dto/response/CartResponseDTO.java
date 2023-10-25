package com.kemalo.OrderService.models.dto.response;

import com.kemalo.OrderService.models.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartResponseDTO {
    private String cartId;
    private List<Product> products;
    private String username;
    private double totalPrice;
}
