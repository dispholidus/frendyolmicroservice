package com.kemalo.OrderService.models.dto.response;

import com.kemalo.OrderService.models.concrete.Product;
import com.kemalo.OrderService.models.concrete.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderResponseDTO {
    private String orderId;
    private List<Product> products;
    private double totalPrice;
    private User user;
}
