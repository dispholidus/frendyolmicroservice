package com.kemalo.OrderService.controllers;

import com.kemalo.OrderService.models.dto.request.CartRequestDTO;
import com.kemalo.OrderService.models.dto.response.CartResponseDTO;
import com.kemalo.OrderService.services.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;
    @PostMapping
    public CartResponseDTO addToCart(@RequestBody CartRequestDTO cartRequestDTO){
        return cartService.addProductToCart(cartRequestDTO);
    }
    @GetMapping
    public List<CartResponseDTO> getCarts(){
        return cartService.getCarts();
    }
}
