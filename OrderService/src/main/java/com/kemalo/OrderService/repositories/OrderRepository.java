package com.kemalo.OrderService.repositories;

import com.kemalo.OrderService.models.entity.Order;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends MongoRepository<Order,String>{
}
