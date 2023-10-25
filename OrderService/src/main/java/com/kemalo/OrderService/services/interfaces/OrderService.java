package com.kemalo.OrderService.services.interfaces;

import com.kemalo.OrderService.models.dto.request.OrderRequestDTO;
import com.kemalo.OrderService.models.dto.response.OrderResponseDTO;
import com.kemalo.OrderService.models.entity.Cart;

import java.util.List;

public interface OrderService {
    OrderResponseDTO addOrder(OrderRequestDTO OrderRequestDTO);
    OrderResponseDTO getOrderById(String id);
    List<OrderResponseDTO> getOrders();
    OrderResponseDTO checkout(String cartId);
}
