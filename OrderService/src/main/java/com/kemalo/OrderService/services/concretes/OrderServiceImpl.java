package com.kemalo.OrderService.services.concretes;

import com.kemalo.OrderService.mappers.OrderDTOMapper;
import com.kemalo.OrderService.models.concrete.Order;
import com.kemalo.OrderService.models.dto.request.OrderRequestDTO;
import com.kemalo.OrderService.models.dto.response.OrderResponseDTO;
import com.kemalo.OrderService.repositories.OrderRepository;
import com.kemalo.OrderService.services.interfaces.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final OrderDTOMapper mapper;
    @Override
    public OrderResponseDTO addOrder(OrderRequestDTO orderRequestDTO) {
        Order order = mapper.orderDTOtoOrder(orderRequestDTO);
        return mapper.orderToOrderDTO(orderRepository.save(order));
    }

    @Override
    public OrderResponseDTO getOrderById(String id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent())
            return mapper.orderToOrderDTO(order.get());
        throw new RuntimeException("hata");
    }
}
