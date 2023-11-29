package com.kemalo.OrderService.models.dto.request;

import jakarta.annotation.Nullable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
