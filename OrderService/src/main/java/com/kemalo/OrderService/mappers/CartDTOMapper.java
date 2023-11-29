package com.kemalo.OrderService.mappers;

import com.kemalo.OrderService.models.dto.request.CartRequestDTO;
import com.kemalo.OrderService.models.dto.response.CartResponseDTO;
import com.kemalo.OrderService.models.entity.Cart;
import com.kemalo.OrderService.models.entity.Product;
import com.kemalo.OrderService.models.entity.User;
import com.kemalo.OrderService.repositories.CartRepository;
import org.mapstruct.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public abstract class CartDTOMapper {
    @Value("${product.url}")
    private String PRODUCT_URL;
    @Value("${user.url}")
    private String USER_URL;
    RestTemplate restTemplate = new RestTemplate();
    @Autowired
    private CartRepository cartRepository;

    public Cart cartDTOtoCart(CartRequestDTO cartRequestDTO) {
        List<Product> products = new ArrayList<>();
        String productURL = PRODUCT_URL + "/" + cartRequestDTO.getProduct();
        Product product = restTemplate.getForObject(productURL, Product.class);
        String userURL = USER_URL + "/" + cartRequestDTO.getUsername();
        User user = restTemplate.getForObject(
                userURL, User.class);
        product.setProductBought(cartRequestDTO.getProductCount());
        Cart cart;
        Optional<Cart> cacheCart;
        if (cartRequestDTO.getCartId() != null && !cartRequestDTO.getCartId().isEmpty()) {
            cacheCart = cartRepository.findByUsername(cartRequestDTO.getCartId());
            if (cacheCart.isPresent()) {
                cart = cacheCart.get();
                products = cart.getProducts();
                cart.setProducts(checkProducts(products, product));
                cart.setTotalPrice(cart.getTotalPrice() + product.getProductPrice() * product.getProductBought());
            } else {
                throw new RuntimeException("Verilen id'de sepet bulunamadı");
            }
        } else if (cartRequestDTO.getUsername() != null && !cartRequestDTO.getUsername().isEmpty()) {

            cacheCart = cartRepository.findByUsername(user.getUsername());
            if (cacheCart.isPresent()) {
                cart = cacheCart.get();
                products = cart.getProducts();
                cart.setProducts(checkProducts(products, product));
                cart.setTotalPrice(cart.getTotalPrice() + product.getProductPrice() * product.getProductBought());
            } else {
                products.add(product);
                cart = Cart.builder()
                        .username(user.getUsername())
                        .products(products)
                        .totalPrice(product.getProductPrice() * product.getProductBought())
                        .build();
            }

        } else {
            throw new RuntimeException("Bir hata oluştu");
        }
        return cart;
    }

    public CartResponseDTO carttoCartDTO(Cart cart) {
        return CartResponseDTO.builder()
                .cartId(cart.getCartId())
                .products(cart.getProducts())
                .username(cart.getUsername())
                .totalPrice(cart.getTotalPrice())
                .build();
    }

    private List<Product> checkProducts(List<Product> products, Product product) {
        if (products.stream().anyMatch(x -> Objects.equals(x.getProductId(), product.getProductId()))) {
            products = products.stream().
                    filter(x -> Objects.equals(x.getProductId(), product.getProductId())).
                    map(y -> Product.builder().
                            productId(y.getProductId()).
                            productName(y.getProductName()).
                            productBought(y.getProductBought() + product.getProductBought()).
                            productPrice(y.getProductPrice()).
                            build()).collect(Collectors.toList());
        } else {
            products.add(product);
        }
        return products;
    }
}
