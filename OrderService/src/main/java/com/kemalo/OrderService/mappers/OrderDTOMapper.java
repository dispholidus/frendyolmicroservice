package com.kemalo.OrderService.mappers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.kemalo.OrderService.models.concrete.Order;
import com.kemalo.OrderService.models.concrete.Product;
import com.kemalo.OrderService.models.dto.request.OrderRequestDTO;
import com.kemalo.OrderService.models.dto.response.OrderResponseDTO;
import io.swagger.v3.core.util.Json;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class OrderDTOMapper {
    @Value("${product.url}")
    private String PRODUCT_URL ;

    private final ObjectMapper mapper = new ObjectMapper();
    public Order orderDTOtoOrder(OrderRequestDTO orderRequestDTO){
        RestTemplate restTemplate = new RestTemplate();
        Order order = new Order();

        JsonNode productsJson = restTemplate.postForObject(
                PRODUCT_URL , orderRequestDTO.getProducts() , JsonNode.class);
        List<Product> products = new ArrayList<>();

        productsJson.forEach(jsonNode ->{
            try {
                products.add(mapper.readValue(jsonNode.toString(),Product.class));
            }catch (Exception e){
                System.out.println(e.toString());
            }});

        order.setProducts(products);

        double totalPrice = 0;
        for (Product product: products) {
            totalPrice += product.getProductPrice();
        }
        order.setTotalPrice(totalPrice);
        return order;
    }
    abstract public OrderResponseDTO orderToOrderDTO(Order order);
}
