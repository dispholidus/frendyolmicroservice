package com.kemalo.OrderService.services.concretes;


import com.kemalo.OrderService.mappers.CartDTOMapper;
import com.kemalo.OrderService.models.dto.request.CartRequestDTO;
import com.kemalo.OrderService.models.dto.response.CartResponseDTO;
import com.kemalo.OrderService.models.entity.Cart;
import com.kemalo.OrderService.models.entity.Product;
import com.kemalo.OrderService.repositories.CartRepository;
import com.kemalo.OrderService.services.interfaces.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final CartDTOMapper mapper;
    @Override
    public CartResponseDTO addProductToCart(CartRequestDTO cartRequestDTO) {
        Cart cart = mapper.cartDTOtoCart(cartRequestDTO);
        if (cart.getCartId() == null){
            return mapper.carttoCartDTO(cartRepository.save(cart));
        }else {
            Product product = cart.getProducts().get(0);
            cart = cartRepository.findById(cart.getCartId()).get();
            cart.getProducts().add(product);

            return mapper.carttoCartDTO(cartRepository.save(cart));
        }
    }

    @Override
    public List<CartResponseDTO> getCarts() {
        Iterable<Cart> carts = cartRepository.findAll();
        List<CartResponseDTO> cartResponseDTOS = new ArrayList<>();
        for (Cart cart:carts) {
            cartResponseDTOS.add(mapper.carttoCartDTO(cart));
        }
        return cartResponseDTOS;
    }

    @Override
    public Cart findCartById(String cartId) {
        Optional<Cart> cart = cartRepository.findById(cartId);
        if(cart.isPresent()){
            Cart foo = cart.get();
            return new Cart(foo.getUsername(), foo.getProducts()) ;
        }
        throw new RuntimeException("Girdiğiniz id de bir sepet bulunmamaktadır!");
    }
}
