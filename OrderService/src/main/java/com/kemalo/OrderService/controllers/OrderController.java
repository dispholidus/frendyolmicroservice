package com.kemalo.OrderService.controllers;

import com.kemalo.OrderService.mappers.OrderDTOMapper;
import com.kemalo.OrderService.models.dto.request.OrderRequestDTO;
import com.kemalo.OrderService.models.dto.response.OrderResponseDTO;
import com.kemalo.OrderService.models.entity.Cart;
import com.kemalo.OrderService.models.entity.Order;
import com.kemalo.OrderService.services.interfaces.CartService;
import com.kemalo.OrderService.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrderById(@PathVariable String orderId){
        return orderService.getOrderById(orderId);
    }
    @GetMapping
    public List<OrderResponseDTO> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping
    public OrderResponseDTO addOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return orderService.addOrder(orderRequestDTO);
    }

    @PostMapping("/checkout/{username}")
    public OrderResponseDTO checkout(@PathVariable String username){

        return orderService.checkout(username);
    }

}
