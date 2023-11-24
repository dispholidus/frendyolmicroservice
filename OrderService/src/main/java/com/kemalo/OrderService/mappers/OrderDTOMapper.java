package com.kemalo.OrderService.mappers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kemalo.OrderService.models.entity.Cart;
import com.kemalo.OrderService.models.entity.Order;
import com.kemalo.OrderService.models.entity.Product;
import com.kemalo.OrderService.models.entity.User;
import com.kemalo.OrderService.models.dto.request.OrderRequestDTO;
import com.kemalo.OrderService.models.dto.response.OrderResponseDTO;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderDTOMapper {
    @Value("${product.url}")
    private String PRODUCT_URL ;
    @Value("${user.url}")
    private String USER_URL;
    RestTemplate restTemplate = new RestTemplate();
    private final ObjectMapper mapper = new ObjectMapper();
    public Order orderDTOtoOrder(OrderRequestDTO orderRequestDTO){

        Order order = new Order();

        JsonNode productsJson = restTemplate.postForObject(
                PRODUCT_URL + "/productslist", orderRequestDTO.getProducts() , JsonNode.class);
        List<Product> products = new ArrayList<>();
        String url =USER_URL + "/" + orderRequestDTO.getUsername();
        User user = restTemplate.getForObject(
                 url , User.class);
        productsJson.forEach(jsonNode ->{
            try {
                Product product = mapper.readValue(jsonNode.toString(),Product.class);
                product.setCount(5);
                products.add(product);

            }catch (Exception e){
                System.out.println(e.toString());
            }});

        order.setProducts(products);

        double totalPrice = 0;
        for (Product product: products) {
            totalPrice += product.getProductPrice();
        }
        order.setTotalPrice(totalPrice);
        order.setUsername(user.getUsername());
        return order;
    }
    abstract public OrderResponseDTO orderToOrderDTO(Order order);
    abstract public Order cartToOrder(Cart cart);
}
