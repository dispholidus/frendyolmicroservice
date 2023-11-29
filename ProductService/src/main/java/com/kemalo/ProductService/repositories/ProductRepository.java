package com.kemalo.ProductService.repositories;

import com.kemalo.ProductService.models.entities.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {
}
