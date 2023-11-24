package com.kemalo.OrderService.models.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.List;

@RedisHash("Cart")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Cart {
    @Id
    private String cartId;

    public Cart(String username, List<Product> products) {
        this.products = products;
        this.username = username;
        this.totalPrice = products.get(0).getProductPrice() * products.get(0).getCount();
    }

    private List<Product> products;
    private String username;
    private double totalPrice;
}
