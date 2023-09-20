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
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    @Autowired
    private final OrderRepository orderRepository;
    @Autowired
    private final OrderDTOMapper mapper;

    @Override
    @Transactional
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
    @Override
    public List<OrderResponseDTO> getOrders() {
        List<OrderResponseDTO> response = new ArrayList<>();
        List<Order> orders = orderRepository.findAll();
        for (Order order: orders) {
            response.add(mapper.orderToOrderDTO(order));
        }
        return response;
    }

    @Override
    public OrderResponseDTO setOrderStatus(String orderId){
        Order order = orderRepository.findById(orderId).get();
        order.setOrderStatus(false);
        return mapper.orderToOrderDTO(orderRepository.save(order));
    }

    @Override
    @Transactional
    public String deleteOrdersByStatus() {
        orderRepository.deleteByOrderStatusEquals(false);
        return "Success";
    }
}
