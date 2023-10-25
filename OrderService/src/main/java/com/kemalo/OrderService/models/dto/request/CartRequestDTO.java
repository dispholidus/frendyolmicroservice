package com.kemalo.OrderService.models.dto.request;

import com.kemalo.OrderService.models.entity.Product;
import com.kemalo.OrderService.models.entity.User;
import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartRequestDTO {
    @Nullable
    private String cartId;
    private String product;
    private int productCount;
    @Nullable
    private String username;
}
