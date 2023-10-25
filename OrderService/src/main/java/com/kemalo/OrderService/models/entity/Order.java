package com.kemalo.OrderService.models.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("orders")
public class Order {

    @Id
    private String orderId;

    private List<Product> products;
    private String username;
    private double totalPrice;
}
