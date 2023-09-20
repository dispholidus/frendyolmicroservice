package com.kemalo.OrderService.controllers;

import com.kemalo.OrderService.models.dto.request.OrderRequestDTO;
import com.kemalo.OrderService.models.dto.response.OrderResponseDTO;
import com.kemalo.OrderService.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order")
@RequiredArgsConstructor
public class OrderController {
    @Autowired
    private final OrderService orderService;

    @GetMapping("/{orderId}")
    public OrderResponseDTO getOrderById(@PathVariable String orderId){
        return orderService.getOrderById(orderId);
    }
    @GetMapping("/getall")
    public List<OrderResponseDTO> getOrders(){
        return orderService.getOrders();
    }

    @PostMapping("/addorder")
    public OrderResponseDTO addOrder(@RequestBody OrderRequestDTO orderRequestDTO){
        return orderService.addOrder(orderRequestDTO);
    }
    @PutMapping("/{orderId}")
    public OrderResponseDTO setOrderStatus(@PathVariable String orderId){
        return orderService.setOrderStatus(orderId);
    }
    @GetMapping("/cleanup")
    public String deleteByOrderStatus(){
        return orderService.deleteOrdersByStatus();
    }
}
