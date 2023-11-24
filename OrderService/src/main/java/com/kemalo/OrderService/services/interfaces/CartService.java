package com.kemalo.OrderService.services.interfaces;

import com.kemalo.OrderService.models.dto.request.CartRequestDTO;
import com.kemalo.OrderService.models.dto.response.CartResponseDTO;
import com.kemalo.OrderService.models.entity.Cart;
import com.kemalo.OrderService.models.entity.Product;
import com.kemalo.OrderService.models.entity.User;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CartService {
    CartResponseDTO addProductToCart(CartRequestDTO cartRequestDTO);
    List<CartResponseDTO> getCarts();
    Cart findCartById(String cartId);
}
