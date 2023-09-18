package com.kemalo.OrderService.services.interfaces;

import com.kemalo.OrderService.models.concrete.Order;
import com.kemalo.OrderService.models.dto.request.OrderRequestDTO;
import com.kemalo.OrderService.models.dto.response.OrderResponseDTO;

public interface OrderService {
    OrderResponseDTO addOrder(OrderRequestDTO OrderRequestDTO);
    OrderResponseDTO getOrderById(String id);

}
