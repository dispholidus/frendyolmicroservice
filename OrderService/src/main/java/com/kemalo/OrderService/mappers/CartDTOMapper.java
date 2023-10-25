package com.kemalo.OrderService.mappers;

import com.kemalo.OrderService.models.dto.request.CartRequestDTO;
import com.kemalo.OrderService.models.dto.response.CartResponseDTO;
import com.kemalo.OrderService.models.entity.Cart;
import com.kemalo.OrderService.models.entity.Product;
import com.kemalo.OrderService.models.entity.User;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class CartDTOMapper {
    @Value("${product.url}")
    private String PRODUCT_URL ;
    @Value("${user.url}")
    private String USER_URL;
    RestTemplate restTemplate = new RestTemplate();
    public Cart cartDTOtoCart(CartRequestDTO cartRequestDTO){
        List<Product> products = new ArrayList<>();
        Product product = restTemplate.getForObject(PRODUCT_URL + "/" + cartRequestDTO.getProduct(),Product.class);

        product.setCount(cartRequestDTO.getProductCount());
        products.add(product);
        Cart cart;
        if (cartRequestDTO.getCartId() != null){
            cart = Cart.builder()
                    .cartId(cartRequestDTO.getCartId())
                    .products(products)
                    .build();
        }else{
            String url =USER_URL + "/" + cartRequestDTO.getUsername();
            User user = restTemplate.getForObject(
                    url , User.class);
            cart = Cart.builder()
                    .username(user.getUsername())
                    .products(products)
                    .build();
        }

        return cart;
    }
    public CartResponseDTO carttoCartDTO(Cart cart){
        double totalPrice = 0;
        for (Product product:cart.getProducts()) {
            totalPrice += product.getProductPrice() * product.getCount();

        }
        return CartResponseDTO.builder()
                .cartId(cart.getCartId())
                .products(cart.getProducts())
                .username(cart.getUsername())
                .totalPrice(totalPrice)
                .build();
    }
}
