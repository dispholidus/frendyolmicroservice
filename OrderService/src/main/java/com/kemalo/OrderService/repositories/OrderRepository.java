package com.kemalo.OrderService.repositories;

import com.kemalo.OrderService.models.concrete.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order,String>{
    void deleteByOrderStatusEquals(boolean orderStatus);
}
