package com.kemalo.OrderService.services.concretes;

import com.kemalo.OrderService.mappers.OrderDTOMapper;
import com.kemalo.OrderService.models.entity.Cart;
import com.kemalo.OrderService.models.entity.Order;
import com.kemalo.OrderService.models.dto.request.OrderRequestDTO;
import com.kemalo.OrderService.models.dto.response.OrderResponseDTO;
import com.kemalo.OrderService.repositories.OrderRepository;
import com.kemalo.OrderService.services.interfaces.CartService;
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


    private final OrderRepository orderRepository;
    private final OrderDTOMapper mapper;
    private final CartService cartService;


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
    @Transactional
    public OrderResponseDTO checkout(String cartId) {
        Cart cart = cartService.findCartById(cartId);
        System.out.println(cart.getTotalPrice());
        Order order = mapper.cartToOrder(cart);
        OrderResponseDTO orderResponseDTO = mapper.orderToOrderDTO(orderRepository.save(order));
        cartService.deleteCartById(cartId);
        return orderResponseDTO;
    }
}
