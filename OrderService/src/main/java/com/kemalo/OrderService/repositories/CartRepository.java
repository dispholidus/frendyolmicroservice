package com.kemalo.OrderService.repositories;

import com.kemalo.OrderService.models.entity.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartRepository extends CrudRepository<Cart, String> {
    Optional<Cart> findByUsername(String username);
}
