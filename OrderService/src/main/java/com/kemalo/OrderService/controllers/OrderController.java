package com.kemalo.OrderService.controllers;

import com.kemalo.OrderService.models.dto.request.OrderRequestDTO;
import com.kemalo.OrderService.models.dto.response.OrderResponseDTO;
import com.kemalo.OrderService.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService orderService;

    @GetMapping("/orders/{orderId}")
    public OrderResponseDTO getOrderById(@PathVariable String orderId){
        return orderService.getOrderById(orderId);
    }

    @PostMapping("/orders")
    public OrderResponseDTO addOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return orderService.addOrder(orderRequestDTO);
    }
}
