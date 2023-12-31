package com.kemalo.OrderService.models.dto.response;

import com.kemalo.OrderService.models.entity.Product;
import com.kemalo.OrderService.models.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private String orderId;
    private List<Product> products;
    private double totalPrice;
    private String username;
}
