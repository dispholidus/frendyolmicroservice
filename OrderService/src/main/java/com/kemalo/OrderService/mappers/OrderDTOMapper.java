package com.kemalo.OrderService.mappers;

import com.kemalo.OrderService.models.dto.request.CheckoutDTO;
import com.kemalo.OrderService.models.entity.Cart;
import com.kemalo.OrderService.models.entity.Order;
import com.kemalo.OrderService.models.entity.Product;
import com.kemalo.OrderService.models.dto.response.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderDTOMapper {
    @Value("${product.url}")
    private String PRODUCT_URL;
    RestTemplate restTemplate = new RestTemplate();
    abstract public OrderResponseDTO orderToOrderDTO(Order order);
    public Order cartToOrder(Cart cart){
        List<CheckoutDTO> boughtProductsList = new ArrayList<>();
        for (Product product: cart.getProducts()) {
            boughtProductsList.add(new CheckoutDTO(product.getProductId(), product.getProductBought()));
        }
        if (restTemplate.postForObject(PRODUCT_URL+"/checkout",boughtProductsList, Boolean.class).booleanValue()){

            Order order = Order.builder().
                    username(cart.getUsername()).
                    totalPrice(cart.getTotalPrice()).
                    products(cart.getProducts()).
                    build();
            return order;
        }
        throw new RuntimeException("Hata oluştu");
    }
}
